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
import java.util.*;

public class TOI {

    private static final Logger logger = LogManager.getLogger(TOI.class);

    private static final String RATIO_SHEET_NAME = "RATIO";
    private static final String KHMER_FONT_NAME = "Khmer OS Siemreap";
    private static final short DEFAULT_FONT_SIZE = 9;
    private static final String HIGHLIGHT_COLOR_HEX = "#DDD9C4";

    private static final Set<String> HIGHLIGHT_VALUES = Set.of(
            "A0", "A13", "A28", "A29", "A37", "A42",
            "B0", "B7", "B8", "B12", "B22", "B42", "B46", "B48",
            "C4", "C6", "C7", "C17", "C20", "D3", "D7", "D9"
    );

    private final Map<Integer, int[]> sheetColumnWidths = Map.ofEntries(
            Map.entry(1, new int[]{34, 34, 25, 34}),
            Map.entry(2, new int[]{5, 17, 16, 15, 6, 16, 6, 16}),
            Map.entry(3, new int[]{36, 15, 7, 18, 18}),
            Map.entry(4, new int[]{60, 6, 17, 17}),
            Map.entry(5, new int[]{60, 6, 17, 17}),
            Map.entry(6, new int[]{60, 6, 17, 17}),
            Map.entry(7, new int[]{60, 6, 17, 17}),
            Map.entry(8, new int[]{60, 6, 17, 17}),
            Map.entry(9, new int[]{12, 16, 16, 10, 10, 16, 16}),
            Map.entry(11, new int[]{18, 19, 19, 19, 19}),
            Map.entry(12, new int[]{37, 10, 10, 15, 15, 13, 13, 13, 13}),
            Map.entry(13, new int[]{5, 50, 20, 20, 20, 20}),
            Map.entry(14, new int[]{5, 18, 18, 39, 17}),
            Map.entry(15, new int[]{11, 21, 16, 16, 16, 16}),
            Map.entry(16, new int[]{60, 8, 28})
    );

    private String outputPath;
    private Workbook workbook;

    public TOI init(String filePath) {
        String baseName = FilenameUtils.getBaseName(filePath);
        String extension = FilenameUtils.getExtension(filePath);
        this.outputPath = FilenameUtils.concat(FilenameUtils.getPath(filePath),
                baseName + " - formatted." + extension);

        try (FileInputStream fis = new FileInputStream(filePath)) {
            if ("xlsx".equalsIgnoreCase(extension)) {
                this.workbook = new XSSFWorkbook(fis);
            } else {
                logger.warn("Unsupported file format: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Failed to initialize workbook from: {}", filePath, e);
        }
        return this;
    }

    public void format() {
        if (workbook == null) {
            logger.warn("Workbook is null. Nothing to format.");
            return;
        }

        try {
            processStepSheets();
            createRatioSheet();

            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
                logger.info("Successfully formatted file saved to: {}", outputPath);
            }
        } catch (IOException e) {
            logger.error("Error during formatting", e);
        } finally {
            closeWorkbook();
        }
    }

    private void processStepSheets() {
        for (Sheet sheet : workbook) {
            String sheetName = sheet.getSheetName().toLowerCase().trim();
            if (!sheetName.contains("step")) continue;

            int sheetNumber = extractSheetNumber(sheetName);
            configurePrintSetup(sheet);
            adjustSheetLayout(sheet, sheetNumber);
            adjustCellFontAndHighlight(sheet);
        }
    }

    private int extractSheetNumber(String sheetName) {
        try {
            return Integer.parseInt(sheetName.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void configurePrintSetup(Sheet sheet) {
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(false);
        sheet.setHorizontallyCenter(true);

        sheet.setMargin(PageMargin.TOP, 1.0 / 2.54);
        sheet.setMargin(PageMargin.BOTTOM, 1.0 / 2.54);
        sheet.setMargin(PageMargin.LEFT, 0.5 / 2.54);
        sheet.setMargin(PageMargin.RIGHT, 0.5 / 2.54);
        sheet.setMargin(PageMargin.HEADER, 0.5 / 2.54);
        sheet.setMargin(PageMargin.FOOTER, 0.5 / 2.54);
    }

    private void adjustSheetLayout(Sheet sheet, int sheetNumber) {
        // Apply column widths from configuration
        int[] widths = sheetColumnWidths.getOrDefault(sheetNumber, new int[0]);
        for (int i = 0; i < widths.length; i++) {
            sheet.setColumnWidth(i, 256 * widths[i]);
        }

        // Special cases
        if (sheetNumber == 10) {
            PrintSetup ps = sheet.getPrintSetup();
            ps.setLandscape(true);
            ps.setFitWidth((short) 1);
            ps.setFitHeight((short) 0);
            sheet.setAutobreaks(true);
            sheet.setFitToPage(true);
        } else if (List.of(1, 12, 13).contains(sheetNumber)) {
            sheet.getPrintSetup().setLandscape(true);
        }

        ((XSSFSheet) sheet).setTabColor(new XSSFColor());
    }

    private void adjustCellFontAndHighlight(Sheet sheet) {
        CellStyle baseStyle = createBaseCellStyle();
        CellStyle wrappedStyle = createWrappedCellStyle(baseStyle);
        CellStyle highlightStyle = createHighlightStyle();

        List<Integer> rowsToHighlight = new ArrayList<>();

        for (Row row : sheet) {
            row.setHeight((short) -1); // Auto height

            for (Cell cell : row) {
                if (cell == null) continue;

                // Apply base font style
                cell.setCellStyle(baseStyle);

                // Special wrap text for row 4, columns 2 and 3
                if (row.getRowNum() == 4 && (cell.getColumnIndex() == 2 || cell.getColumnIndex() == 3)) {
                    cell.setCellStyle(wrappedStyle);
                }

                // Collect rows to highlight
                if (cell.getCellType() == CellType.STRING) {
                    String value = cell.getStringCellValue().trim();
                    if (HIGHLIGHT_VALUES.contains(value)) {
                        rowsToHighlight.add(row.getRowNum());
                    }
                }
            }
        }

        // Apply highlight to first 4 columns of target rows
        for (int rowNum : rowsToHighlight) {
            Row row = sheet.getRow(rowNum);
            if (row == null) continue;
            for (int col = 0; col < 4; col++) {
                Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellStyle(highlightStyle);
            }
        }
    }

    private CellStyle createBaseCellStyle() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName(KHMER_FONT_NAME);
        font.setFontHeightInPoints(DEFAULT_FONT_SIZE);
        style.setFont(font);
        return style;
    }

    private CellStyle createWrappedCellStyle(CellStyle base) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(base);
        style.setWrapText(true);
        return style;
    }

    private CellStyle createHighlightStyle() {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(createBaseCellStyle());

        byte[] rgb = hexToRGB(HIGHLIGHT_COLOR_HEX);
        XSSFColor color = new XSSFColor(rgb, null); // null = no tint
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private byte[] hexToRGB(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        byte[] rgb = new byte[3];
        rgb[0] = (byte) Integer.parseInt(hex.substring(0, 2), 16);
        rgb[1] = (byte) Integer.parseInt(hex.substring(2, 4), 16);
        rgb[2] = (byte) Integer.parseInt(hex.substring(4, 6), 16);
        return rgb;
    }

    private void createRatioSheet() {
        if (workbook.getSheet(RATIO_SHEET_NAME) != null) return;

        Sheet ratioSheet = workbook.createSheet(RATIO_SHEET_NAME);
        workbook.setSheetOrder(RATIO_SHEET_NAME, 0);

        // Header
        Row header = ratioSheet.createRow(0);
        String[] headers = {"RATIO NAME", "BENCHMARK", "DATA (N)", "DATA (N-1)", "DESCRIPTION"};
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        RatioList ratioList = new RatioList();
        for (int i = 0; i < ratioList.getList().size(); i++) {
            Ratio ratio = ratioList.getList().get(i);
            Row row = ratioSheet.createRow(i + 1);

            row.createCell(0).setCellValue(ratio.getName());
            row.createCell(4).setCellValue(ratio.getDescription());

            Cell dataCell = row.createCell(2);
            dataCell.setCellFormula(ratio.getExcelFormula());

            // Apply percentage/number format
            CellStyle percentStyle = workbook.createCellStyle();
            percentStyle.setDataFormat(workbook.createDataFormat().getFormat(ratio.getExcelFormatText()));
            dataCell.setCellStyle(percentStyle);
        }

        // Set column widths
        ratioSheet.setColumnWidth(0, 256 * 40);
        ratioSheet.setColumnWidth(1, 256 * 15);
        ratioSheet.setColumnWidth(2, 256 * 15);
        ratioSheet.setColumnWidth(3, 256 * 15);
        ratioSheet.setColumnWidth(4, 256 * 50);

        workbook.setActiveSheet(0);
    }

    private void closeWorkbook() {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.warn("Error closing workbook", e);
            }
        }
    }
}