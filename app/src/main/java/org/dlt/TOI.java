package org.dlt;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.PageMargin;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TOI {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private String outputPath;
    private Workbook workbook;

    public TOI init(String filePath) {
        String newFileName = FilenameUtils.getBaseName(filePath)+" - formatted."+FilenameUtils.getExtension(filePath);
        this.outputPath = FilenameUtils.concat(FilenameUtils.getPath(filePath), newFileName);

        try (FileInputStream fis = new FileInputStream(filePath)) {
            if (FilenameUtils.isExtension(filePath, "xlsx")) {
                this.workbook = new XSSFWorkbook(fis);
            } else {
                logger.warn("Not an XLSX file!!!");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return this;
    }

    public void format() {
        if (workbook != null) {
            int sheetCount = workbook.getNumberOfSheets();
            // Loop through each sheet
            for (int i = 0; i < sheetCount; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                workbook.setPrintArea(i, 0, sheet.getRow(0).getLastCellNum() - 1, 0, sheet.getLastRowNum());

                this.adjustSheetColumn(sheet, (i + 1));
            }

            try (FileOutputStream fos = new FileOutputStream(this.outputPath)) {
                workbook.write(fos);
                workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void adjustSheetColumn(Sheet sheet, int sheetNumber) {
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(false);

        sheet.setMargin(PageMargin.TOP, 0.2);
        sheet.setMargin(PageMargin.LEFT, 0.2);
        sheet.setMargin(PageMargin.RIGHT, 0.2);
        sheet.setMargin(PageMargin.BOTTOM, 0.2);
        sheet.setMargin(PageMargin.HEADER, 10);
        sheet.setMargin(PageMargin.FOOTER, 20);

        switch (sheetNumber) {
            case 1: // GENERAL INFO
                printSetup.setLandscape(true);
                sheet.setColumnWidth(0, 256*34);
                sheet.setColumnWidth(1, 256*34);
                sheet.setColumnWidth(2, 256*25);
                sheet.setColumnWidth(3, 256*34);
                break;
            case 2: // SHAREHOLDERS INFO
                sheet.setColumnWidth(0, 256*5);
                sheet.setColumnWidth(1, 256*17);
                sheet.setColumnWidth(2, 256*16);
                sheet.setColumnWidth(3, 256*15);
                sheet.setColumnWidth(4, 256*6);
                sheet.setColumnWidth(5, 256*16);
                sheet.setColumnWidth(6, 256*6);
                sheet.setColumnWidth(7, 256*16);
                break;
            case 3: // OVERALL EMPLOYEES INFO
                sheet.setColumnWidth(0, 256*36);
                sheet.setColumnWidth(1, 256*15);
                sheet.setColumnWidth(2, 256*7);
                sheet.setColumnWidth(3, 256*18);
                sheet.setColumnWidth(4, 256*18);
                break;
            case 4: // BALANCE SHEET
            case 5: // INCOME STATEMENT
            case 6: // COGS (Manufacturer)
            case 7: // COGS (Non-Manufacturer)
            case 8: // TOI Adjustments
                sheet.setColumnWidth(0, 256*60);
                sheet.setColumnWidth(1, 256*6);
                sheet.setColumnWidth(2, 256*17);
                sheet.setColumnWidth(3, 256*17);
                break;
            case 9: // DONATION, INTEREST, and TAXABLE PROFIT or LOST BROUGHT FORWARD
                sheet.setColumnWidth(0, 256*12);
                sheet.setColumnWidth(1, 256*16);
                sheet.setColumnWidth(2, 256*16);
                sheet.setColumnWidth(3, 256*10);
                sheet.setColumnWidth(4, 256*16);
                sheet.setColumnWidth(5, 256*16);
                sheet.setColumnWidth(6, 256*16);
                break;
            case 10: // Tax depreciation

                printSetup.setLandscape(true);
                break;
            case 11:
                sheet.setColumnWidth(0, 256*18);
                sheet.setColumnWidth(1, 256*19);
                sheet.setColumnWidth(2, 256*19);
                sheet.setColumnWidth(3, 256*19);
                sheet.setColumnWidth(4, 256*19);
                break;
            case 12: // TAXABLE SURPLUS OR DEDUCTION FROM SELLING/DISPOSE OF LONG-TERM ASSET
                sheet.setColumnWidth(0, 256*37);
                sheet.setColumnWidth(1, 256*10);
                sheet.setColumnWidth(2, 256*10);
                sheet.setColumnWidth(3, 256*15);
                sheet.setColumnWidth(4, 256*15);
                sheet.setColumnWidth(5, 256*13);
                sheet.setColumnWidth(6, 256*13);
                sheet.setColumnWidth(7, 256*13);
                sheet.setColumnWidth(8, 256*13);
                printSetup.setLandscape(true);
                break;
            case 13: // PROVISION
                sheet.setColumnWidth(0, 256*5);
                sheet.setColumnWidth(1, 256*50);
                sheet.setColumnWidth(2, 256*20);
                sheet.setColumnWidth(3, 256*20);
                sheet.setColumnWidth(4, 256*20);
                sheet.setColumnWidth(5, 256*20);
                printSetup.setLandscape(true);
                break;
            case 14: // RELATED PARTIES' TRANSACTION TABLE
                sheet.setColumnWidth(0, 256*5);
                sheet.setColumnWidth(1, 256*18);
                sheet.setColumnWidth(2, 256*18);
                sheet.setColumnWidth(3, 256*39);
                sheet.setColumnWidth(4, 256*17);
                break;
            case 15: // FIXED ASSETS LISTING TABLE
                sheet.setColumnWidth(0, 256*11);
                sheet.setColumnWidth(1, 256*20);
                sheet.setColumnWidth(2, 256*15);
                sheet.setColumnWidth(3, 256*15);
                sheet.setColumnWidth(4, 256*15);
                sheet.setColumnWidth(5, 256*15);
                break;
            case 16: // TAX ON INCOME FROM ORE EXTRACTION ACTIVITIES
                sheet.setColumnWidth(0, 256*60);
                sheet.setColumnWidth(1, 256*8);
                sheet.setColumnWidth(2, 256*28);
                break;
        }
    }
}
