package org.dlt;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TOI {
    private String filePath = "D:\\Project_java\\toi-formatter\\app\\src\\main\\resources\\";
    private String outputPath = "D:\\Project_java\\toi-formatter\\app\\src\\main\\resources\\";
    private Workbook workbook;

    public TOI init(String fullFilePath) {
        this.filePath = (fullFilePath == null) ? this.filePath + "toi-example.xlsx" : fullFilePath;
        try (FileInputStream fis = new FileInputStream(this.filePath)) {
            this.workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void format() {
        int sheetCount = workbook.getNumberOfSheets();
        // Loop through each sheet
        for (int i = 0; i < sheetCount; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            this.adjustSheetColumn(sheet, (i+1));
        }

        try (FileOutputStream fos = new FileOutputStream(this.outputPath + "TOI-formatted.xlsx")) {
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void adjustSheetColumn(Sheet sheet, int sheetNumber) {
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(false);

        sheet.setMargin(Sheet.TopMargin, 0.2);
        sheet.setMargin(Sheet.LeftMargin, 0.2);
        sheet.setMargin(Sheet.RightMargin, 0.2);
        sheet.setMargin(Sheet.BottomMargin, 0.2);

        switch (sheetNumber) {
            case 4: // BALANCE SHEET
                sheet.setColumnWidth(0, 256*58);
                sheet.setColumnWidth(1, 256*6);
                sheet.setColumnWidth(2, 256*16);
                sheet.setColumnWidth(3, 256*16);
                break;
            case 5:

                break;
        }
    }
}
