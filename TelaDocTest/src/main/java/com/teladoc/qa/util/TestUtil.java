package com.teladoc.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.teladoc.qa.base.TestBase;

public class TestUtil extends TestBase {
	
		public static long PAGE_LOAD_TIMEOUT = 20;
		public static long IMPLICIT_WAIT = 20;
		public static String TESTDATA_SHEET_PATH = "/src/main/java/com/teladoc/qa/testdata/ExcelTestData.xlsx";
		public static Workbook book;
		public static Sheet sheet;
		

		public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
			FileInputStream file = null;
			try {
				file = new FileInputStream(System.getProperty("user.dir") + TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
			}
			return data;
		}		
		
}
