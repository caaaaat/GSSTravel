package controller;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class readExcel {
	public static void excel(String traFile) {
		try {
			Workbook workbook = Workbook.getWorkbook(new File("C:/travel/"+traFile+".xls"));
			Sheet sheet = workbook.getSheet("MySheet");
			for (int i = 0; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					if (j == 0) {
						System.out.print(sheet.getCell(j, i).getContents() + ":");
					} else {
						System.out.print(sheet.getCell(j, i).getContents());
					}
				}
				System.out.println();
			}
			workbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
