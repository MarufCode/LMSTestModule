package org.example.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    public ExcelReader() throws FileNotFoundException {

    }


    // Workbook, Sheet, Row and Column, Cells


    static Workbook book;

    static Sheet sheet;

    public static final String FILE_NAME = System.getProperty("user.dir") + "";



    public static Object[][] getTestData(String sheetName) throws IOException {
        // Write the logic and convert it into 2D array[][]

        FileInputStream file = null;
        file = new FileInputStream(FILE_NAME);
        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();

            }
        }

        return data;
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        return getTestData("sheet1");
    }
}
