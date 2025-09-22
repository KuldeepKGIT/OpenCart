package com.kuldeepkumar290497.utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UtilExcel {
    // Apache POI
    // Read the File - TestData.xlsX
    //  Workbook Create
    // Sheet
    // Row and Cell
    // 2D Object  - getData()

    // File -> Workbook -> Sheet-> Row -> cell -> [][]

    static Workbook book;
    static Sheet sheet;

    public static String sheet_path = System.getProperty("user.dir")+"/testData/Opencart_LoginData.xlsx";

    public static Object[][] getTestDataFromExcel(String sheetName) {
        FileInputStream file = null;

        try {
            file = new FileInputStream(sheet_path);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);
        } catch (IOException e) {
            System.out.println("Either File Not Found! or workbook not created!");
        }
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for(int i = 0; i<sheet.getLastRowNum(); i++){
            for(int j = 0; j<sheet.getRow(0).getLastCellNum();j++){
                // First row email, password -> column name - skip - header
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();

            }
        }
        return data;
    }



}