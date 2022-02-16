package ru.teamandersen.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ru.teamandersen.entity.Student;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WorkWithExel {

    private static List<Student.Builder> students = new ArrayList<>();

    public List<Student.Builder> readExel() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter path...");
        //String path = sc.nextLine();
        String path = "C:\\Users\\Tony\\IdeaProjects\\AndersenRandomaizer\\table.xlsx";
        XSSFWorkbook workBook = new XSSFWorkbook();

        try {
            workBook = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("File not found");
        }
        XSSFSheet sheet = workBook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        try{
            while(rowIterator.hasNext()){
                XSSFCell cell = (XSSFCell) rowIterator.next().getCell(0);
                long id = (long) cell.getNumericCellValue();
                XSSFCell cellname = (XSSFCell) rowIterator.next().getCell(1);
                String fullname = cellname.getStringCellValue();
                String name = fullname.substring(0, fullname.indexOf(" "));
                String surname = fullname.substring(fullname.indexOf(" "));
                students.add(new Student.Builder(id, name, surname));
                System.out.println(id + " " + name + " " + surname);
            }
        }
        catch (Exception e)
        {
            System.out.println("WriteFormatError");
        }
        return students;
    }


    public List<Student.Builder> getStudents(){
        return students;
    }
}




