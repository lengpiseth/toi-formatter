package org.dlt.model;

import java.util.ArrayList;
import java.util.List;

public class RatioList {
    private final List<RatioModel> list = new ArrayList<>();

    public RatioList() {
        this.list.add(new RatioModel("Gross Profit Margin (BANK)","Step5!C13/(Step5!C6+Step5!C14+Step5!C21)", "0.00%"));
        this.list.add(new RatioModel("Gross Profit Margin","Step5!C13/(Step5!C6+Step5!C14)", "0.00%"));
        this.list.add(new RatioModel("Net Profit Margin","Step5!C52/Step5!C6", "0.00%"));
        this.list.add(new RatioModel("Utilities to Turnover","(Step5!C30+Step6!C15)/Step5!C6", "0.00%"));
        this.list.add(new RatioModel("Maintenance/Repair to Turnover","Step5!C34/Step5!C6", "0.00%"));
        this.list.add(new RatioModel("Salary to Turnover","(Step5!C29+Step6!C13)/Step5!C6", "0.00%"));
        this.list.add(new RatioModel("Return on Equity (ROE)","Step5!C54/Step4!C35", "0.00%"));
        this.list.add(new RatioModel("Current Ratio","Step4!C19/Step4!C48", "#,##0.00"));
        this.list.add(new RatioModel("Debt to Equity","(Step4!C43+Step4!C48)/Step4!C35", "#,##0.00"));
        this.list.add(new RatioModel("Interest Coverage","Step5!C48/(Step5!C49+Step5!C50)", "#,##0.00"));
        this.list.add(new RatioModel("Return on Capital Employed (ROCE)","Step5!C48/(Step4!C6-Step4!C48)", "0.00%"));
        this.list.add(new RatioModel("Inventory Turnover","(Step5!C10+Step5!C11)/((SUM(Step4!D20:D23)+SUM(Step4!C20:C23))/2)", "#,##0.00"));
        this.list.add(new RatioModel("Quick Ratio (Acid Test)","(Step4!C19-SUM(Step4!C20:C23))/Step4!C48", "#,##0.00"));
        this.list.add(new RatioModel("VAT Credit to Ending Inventory","Step4!C30/SUM(Step4!C20:C23)", "0.00%"));
        this.list.add(new RatioModel("Return on Asset","Step5!C54/Step4!C6", "0.00%"));
        this.list.add(new RatioModel("Asset Turnover","Step5!C6/Step4!C6", "0.00%"));
        this.list.add(new RatioModel("Working Capital Turnover","Step5!C6/(Step4!C19-Step4!C48)", "0.00%"));
        this.list.add(new RatioModel("Interest Income to Total Loan","(Step5!C6+Step5!C21)/Step4!C24", "0.00%"));
        this.list.add(new RatioModel("Interest Income to Operating Expense","(Step5!C6+Step5!C21)/Step5!C28", "0.00%"));
        this.list.add(new RatioModel("Loan from Related Party to Total Loan","(Step4!C44+Step4!C51)/(Step4!C43-Step4!C46-Step4!C54)", "0.00%"));
        this.list.add(new RatioModel("Management/Technical Fee to Revenue","Step5!C39/Step5!C6", "0.00%"));
        this.list.add(new RatioModel("Transport Exp. to Revenue","(Step5!C32+Step6!C8)/Step5!C6", "0.00%"));
    }

    public List<RatioModel> getList() {
        return list;
    }
}
