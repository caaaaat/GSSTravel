package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class writeExcel {
	public static void excel(String traNo, String traName, String traLoc, String traOn, String traOff, String traBeg,
			String traEnd, String traTotal, String traMax, String traIntr, String traCon, String traAttr, String traFile) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("C:/travel/"+traFile+".xls"));
			WritableSheet sheet = workbook.createSheet("MySheet", 0);
			// WritableFont myFont = new
			// WritableFont(WritableFont.createFont("標楷體"), 14);
			// myFont.setColour(Colour.WHITE);
			WritableCellFormat cellFormat = new WritableCellFormat();
			// cellFormat.setFont(myFont); // 指定字型
			// cellFormat.setBackground(Colour.LIGHT_BLUE); // 背景顏色
			cellFormat.setAlignment(Alignment.CENTRE); // 對齊方式
			String[] name = { "活動代碼", "活動名稱", "活動地點", "活動開始日", "活動結束日", "活動報名開始日", "活動報名結束日", "活動總人數", "活動報名上線人數(個人)",
					"活動說明", "活動內容", "活動注意事項" };
			String[] content = { traNo, traName, traLoc, traOn, traOff, traBeg, traEnd, traTotal, traMax, traIntr,
					traCon, traAttr };
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						sheet.addCell(new Label(j, i, name[i], cellFormat));
					} else if (j == 1) {
						sheet.addCell(new Label(j, i, content[i], cellFormat));
					}
				}
			}
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
