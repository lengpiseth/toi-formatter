package org.dlt;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dlt.model.Ratio;
import org.dlt.model.RatioList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            int sheetIndex = 0;
            for (Sheet sheet : workbook) {
                String sheetName = sheet.getSheetName().toLowerCase();
                if (sheetName.contains("step")) {
                    int sheetNumber = 0;
                    try {
                        sheetNumber = Integer.parseInt(sheetName.replace("step",""));
                    } catch (NumberFormatException ignored) {}

                    workbook.setPrintArea(sheetIndex, 0, sheet.getRow(0).getLastCellNum() - 1, 0, sheet.getLastRowNum());

                    this.adjustSheet(sheet, sheetNumber);
                }
            }

            // Create ratio sheet
            this.createRatioSheet();

            try (FileOutputStream fos = new FileOutputStream(this.outputPath)) {
                workbook.write(fos);
                workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void createRatioSheet() {
        String sheetName = "RATIO";
        if (this.workbook.getSheet(sheetName) == null) {
            Sheet ratioSheet = this.workbook.createSheet(sheetName);
            this.workbook.setSheetOrder(sheetName, 0);

            Row row0 = ratioSheet.createRow(0);
            row0.createCell(0).setCellValue("RATIO NAME");
            row0.createCell(1).setCellValue("BENCHMARK");
            row0.createCell(2).setCellValue("DATA (N)");
            row0.createCell(3).setCellValue("DATA (N-1)");
            row0.createCell(4).setCellValue("DESCRIPTION");

            RatioList ratioList = new RatioList();
            for (int i=0; i < ratioList.getList().size(); i++) {
                Ratio ratio = ratioList.getList().get(i);

                CellStyle percentStyle = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                percentStyle.setDataFormat(format.getFormat(ratio.getExcelFormatText()));

                Row row = ratioSheet.createRow(i+1);

                Cell ratioCell = row.createCell(0);
                ratioCell.setCellValue(ratio.getName());

                Cell ratioData = row.createCell(2);
                ratioData.setCellFormula(ratio.getExcelFormula());
                ratioData.setCellStyle(percentStyle);

                Cell ratioDescription = row.createCell(4);
                ratioDescription.setCellValue(ratio.getDescription());
            }
            ratioSheet.setColumnWidth(0, 256*40);
            ratioSheet.setColumnWidth(1, 256*15);
            ratioSheet.setColumnWidth(2, 256*15);
            ratioSheet.setColumnWidth(3, 256*15);
            ratioSheet.setColumnWidth(4, 256*50);

            this.workbook.setActiveSheet(0);
        }
    }

    private void adjustSheet(Sheet sheet, int sheetNumber) {
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(false);

        sheet.setHorizontallyCenter(true);

        double cmToInch = 2.54;

        sheet.setMargin(PageMargin.TOP, 1/cmToInch);
        sheet.setMargin(PageMargin.BOTTOM, 1/cmToInch);
        sheet.setMargin(PageMargin.LEFT, 0.5/cmToInch);
        sheet.setMargin(PageMargin.RIGHT, 0.5/cmToInch);
        sheet.setMargin(PageMargin.HEADER, 0.5/cmToInch);
        sheet.setMargin(PageMargin.FOOTER, 0.5/cmToInch);

        ((XSSFSheet) sheet).setTabColor(new XSSFColor());

        this.adjustCellFont(sheet);

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
                sheet.setColumnWidth(4, 256*10);
                sheet.setColumnWidth(5, 256*16);
                sheet.setColumnWidth(6, 256*16);
                break;
            case 10: // Tax depreciation
                printSetup.setFitWidth((short) 1);
                printSetup.setFitHeight((short) 0);
                printSetup.setLandscape(true);

                sheet.setAutobreaks(true);
                sheet.setFitToPage(true);
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
                sheet.setColumnWidth(1, 256*21);
                sheet.setColumnWidth(2, 256*16);
                sheet.setColumnWidth(3, 256*16);
                sheet.setColumnWidth(4, 256*16);
                sheet.setColumnWidth(5, 256*16);
                break;
            case 16: // TAX ON INCOME FROM ORE EXTRACTION ACTIVITIES
                sheet.setColumnWidth(0, 256*60);
                sheet.setColumnWidth(1, 256*8);
                sheet.setColumnWidth(2, 256*28);
                break;
        }
    }

    private void adjustCellFont(Sheet sheet) {
        Set<String> targetValues = Set.of("A0", "A13", "A28", "A29", "A37", "A42", "B0", "B7", "B8","B12","B22","B42","B46","B48", "C4", "C6","C7","C17","C20","D3","D7","D9");
        List<Integer> rowToHighlight = new ArrayList<>();

        for (Row row : sheet) {
//            row.setHeight((short)-1);
            for (Cell cell : row) {
                CellStyle originCellStyle = cell.getCellStyle();
                CellStyle newStyle = this.workbook.createCellStyle();
                newStyle.cloneStyleFrom(originCellStyle);

                Font font = this.workbook.createFont();
                font.setFontName("Khmer OS Siemreap");
                font.setFontHeightInPoints((short) 9);
                newStyle.setFont(font);
                cell.setCellStyle(newStyle);

                if(cell.getCellType() == CellType.STRING && targetValues.contains(cell.getStringCellValue().trim())) {
                    rowToHighlight.add(row.getRowNum());
                }
            }
        }

        for(Integer row : rowToHighlight) {
            for(int col = 0; col < 4; col++) {
                Cell hightlightCell = sheet.getRow(row).getCell(col);

                CellStyle originCellStyle = hightlightCell.getCellStyle();
                CellStyle hightlightCellStyle = this.workbook.createCellStyle();
                hightlightCellStyle.cloneStyleFrom(originCellStyle);

                String hexColor = "#DDD9C4";
                byte[] rgb = hexStringToRGB(hexColor);
                XSSFColor xssfColor = new XSSFColor(rgb);
                hightlightCellStyle.setFillForegroundColor(xssfColor);
                hightlightCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                hightlightCell.setCellStyle(hightlightCellStyle);
            }
        }
    }

    private byte[] hexStringToRGB(String hex) {
        if(hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        if(hex.length() != 6) {
            throw new IllegalArgumentException("Invalid hexadecimal string: " + hex);
        }

        byte[] rgb = new byte[3];
        rgb[0] = (byte) Integer.parseInt(hex.substring(0, 2), 16); // Red
        rgb[1] = (byte) Integer.parseInt(hex.substring(2, 4), 16); // Green
        rgb[2] = (byte) Integer.parseInt(hex.substring(4, 6), 16); // Blue

        return rgb;
    }
}
