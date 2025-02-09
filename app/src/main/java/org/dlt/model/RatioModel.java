package org.dlt.model;

public class RatioModel {
    private final String name;
    private final String formulaExcel;

    public RatioModel(String name, String formulaExcel) {
        this.name = name;
        this.formulaExcel = formulaExcel;
    }

    public String getName() {
        return name;
    }

    public String getFormulaExcel() {
        return formulaExcel;
    }
}
