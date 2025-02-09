package org.dlt.model;

public class RatioModel {
    private final String name;
    private final String excelFormula;
    private final String excelFormat;

    public RatioModel(String name, String formulaExcel, String excelFormat) {
        this.name = name;
        this.excelFormula = formulaExcel;
        this.excelFormat = excelFormat;
    }

    public String getName() {
        return name;
    }

    public String getExcelFormula() {
        return excelFormula;
    }

    public String getExcelFormatText() {
        return excelFormat;
    }
}
