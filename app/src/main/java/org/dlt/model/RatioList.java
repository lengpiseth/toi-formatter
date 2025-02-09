package org.dlt.model;

import java.util.ArrayList;
import java.util.List;

public class RatioList {
    private List<RatioModel> list = new ArrayList<RatioModel>();

    public RatioList() {
        this.list.add(new RatioModel("Gross Profit Margin","Step5!C13/(Step5!C6+Step5!C14)"));
        this.list.add(new RatioModel("Net Profit Margin","Step5!C52/Step5!C6"));
        this.list.add(new RatioModel("Return on Equity (ROE)","Step5!C54/Step4!C35"));
        this.list.add(new RatioModel("Inventory Turnover","(Step5!C10+Step5!C11)/((SUM(Step4!D20:D23)+SUM(Step4!C20:C23))/2)"));
        this.list.add(new RatioModel("Quick Ratio (Acid Test)","(Step4!C19-SUM(Step4!C20:C23))/Step4!C48"));
        this.list.add(new RatioModel("VAT Credit to Ending Inventory","Step4!C30/SUM(Step4!C20:C23)"));
        this.list.add(new RatioModel("Current Ratio","Step4!C19/Step4!C48"));
        this.list.add(new RatioModel("Salary to Turnover","(Step5!C29+Step6!C13)/Step5!C6"));
    }

    public List<RatioModel> getList() {
        return list;
    }
}
