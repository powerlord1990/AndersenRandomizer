package ru.teamandersen.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ru.teamandersen.entity.Student;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorkWithExcel {
    public List<Student> readExcel(String path) {
        List<Student> students = new ArrayList<>();
        XSSFWorkbook workBook = new XSSFWorkbook();
        try {
            workBook = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("File not found");
        }
        XSSFSheet sheet = workBook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        try {
            while (rowIterator.hasNext()) {
                long id = (long) rowIterator.next().getCell(0).getNumericCellValue();
                String name = rowIterator.next().getCell(1).getStringCellValue();
                String surname = rowIterator.next().getCell(2).getStringCellValue();
                students.add(new Student.Builder(id, name, surname).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}




