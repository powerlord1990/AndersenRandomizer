package ru.teamandersen.service;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.teamandersen.entity.Student;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {
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
                rowIterator.forEachRemaining(
                        x -> students.add(
                                new Student.Builder(
                                        (long) x.getCell(0).getNumericCellValue(),
                                        x.getCell(1).getStringCellValue(),
                                        x.getCell(2).getStringCellValue())
                                        .build()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}




