package org.dlt.model;

public class Ratio {
    private String name;
    private String excelFormula;
    private String excelFormat;
    private String description;

    public Ratio() {
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

    public String getDescription() {
        return description;
    }

    public Ratio setName(String name) {
        this.name = name;
        return this;
    }

    public Ratio setExcelFormula(String excelFormula) {
        this.excelFormula = excelFormula;
        return this;
    }

    public Ratio setExcelFormat(String excelFormat) {
        this.excelFormat = excelFormat;
        return this;
    }

    public Ratio setDescription(String description) {
        this.description = description;
        return this;
    }
}
