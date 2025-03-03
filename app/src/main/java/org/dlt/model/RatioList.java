package org.dlt.model;

import java.util.ArrayList;
import java.util.List;

public class RatioList {
    private final List<Ratio> list = new ArrayList<>();

    public RatioList() {
        this.generateList();
    }

    public List<Ratio> getList() {
        return list;
    }

    public List<Ratio> generateList() {
        Ratio ratio1 = new Ratio();
        Ratio ratio2 = new Ratio();
        Ratio ratio3 = new Ratio();
        Ratio ratio4 = new Ratio();
        Ratio ratio5 = new Ratio();
        Ratio ratio6 = new Ratio();
        Ratio ratio7 = new Ratio();
        Ratio ratio8 = new Ratio();
        Ratio ratio9 = new Ratio();
        Ratio ratio10 = new Ratio();
        Ratio ratio11 = new Ratio();
        Ratio ratio12 = new Ratio();
        Ratio ratio13 = new Ratio();
        Ratio ratio14 = new Ratio();
        Ratio ratio15 = new Ratio();
        Ratio ratio16 = new Ratio();
        Ratio ratio17 = new Ratio();
        Ratio ratio18 = new Ratio();
        Ratio ratio19 = new Ratio();
        Ratio ratio20 = new Ratio();
        Ratio ratio21 = new Ratio();
        Ratio ratio22 = new Ratio();

        ratio1.setName("Gross Profit Margin (BANK)")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C13/(Step5!C6+Step5!C14+Step5!C21)")
                .setDescription("Refers to the percentage of a company's sales revenue that remains after subtracting the cost of goods sold (COGS)");
        ratio2.setName("Gross Profit Margin")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C13/(Step5!C6+Step5!C14)")
                .setDescription("Refers to the percentage of a company's sales revenue that remains after subtracting the cost of goods sold (COGS).");
        ratio3.setName("Net Profit Margin")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C52/Step5!C6")
                .setDescription("Measures net income generated by each dollar of sales");
        ratio4.setName("Utilities to Turnover")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step5!C30+Step6!C15)/Step5!C6")
                .setDescription("refers to a company in the utilities sector's (Asset Turnover Ratio), which indicates how effectively the company generates revenue from its assets.");
        ratio5.setName("Maintenance/Repair to Turnover")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C34/Step5!C6")
                .setDescription("This ratio indicates how much of a company's revenue is spent on maintaining and repairing its assets relative to the total revenue generated.");
        ratio6.setName("Salary to Turnover")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step5!C29+Step6!C13)/Step5!C6")
                .setDescription("AKA Labor to Revenue ratio, measures the percentage of a company's total revenue that is spent on employee salaries, essentially showing how much of the income generated is directly attributed to payroll costs.");
        ratio7.setName("Return on Equity (ROE)")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C54/Step4!C35")
                .setDescription("Return on Equity is an important financial metric used to assess a company's efficiency in generating profits from its shareholders' equity");
        ratio8.setName("Current Ratio")
                .setExcelFormat("#,##0.00")
                .setExcelFormula("Step4!C19/Step4!C48")
                .setDescription("Measures short-term debt-paying ability");
        ratio9.setName("Debt to Equity")
                .setExcelFormat("#,##0.00")
                .setExcelFormula("(Step4!C43+Step4!C48)/Step4!C35")
                .setDescription("A Debt to Equity ratio interprets how much of a company's funding comes from debt compared to shareholder equity; a higher ratio indicates a company relies more heavily on debt financing.");
        ratio10.setName("Interest Coverage")
                .setExcelFormat("#,##0.00")
                .setExcelFormula("Step5!C48/(Step5!C49+Step5!C50)")
                .setDescription("An Interest Coverage Ratio (ICR) interprets how easily a company can cover its interest expenses with its operating income, essentially indicating its ability to service its debt; a higher ratio signifies better financial health.");
        ratio11.setName("Return on Capital Employed (ROCE)")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C48/(Step4!C6-Step4!C48)")
                .setDescription("ROCE - indicates how efficiently a company utilizes its capital to generate profits, essentially showing how much profit is generated for each dollar of capital employed; a higher ROCE signifies better capital usage and is generally considered more favorable.");
        ratio12.setName("Inventory Turnover")
                .setExcelFormat("#,##0.00")
                .setExcelFormula("(Step5!C10+Step5!C11)/((SUM(Step4!D20:D23)+SUM(Step4!C20:C23))/2)")
                .setDescription("Measures liquidity of inventory");
        ratio13.setName("Quick Ratio (Acid Test)")
                .setExcelFormat("#,##0.00")
                .setExcelFormula("(Step4!C19-SUM(Step4!C20:C23))/Step4!C48")
                .setDescription("Measures immediate short-term liquidity");
        ratio14.setName("VAT Credit to Ending Inventory")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step4!C30/SUM(Step4!C20:C23)")
                .setDescription("Normally VAT credit should equal to 10% of ending inventory if there is no sale occurs.");
        ratio15.setName("Return on Asset")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C54/Step4!C6")
                .setDescription("ROA - is a profitability ratio that shows how much profit a company is generating from its assets.");
        ratio16.setName("Asset Turnover")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C6/Step4!C6")
                .setDescription("This ratio measures how effectively a company utilizes its assets to generate revenue.");
        ratio17.setName("Working Capital Turnover")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C6/(Step4!C19-Step4!C48)")
                .setDescription("The NWC ratio interprets how efficiently a company uses its working capital to generate sales.");
        ratio18.setName("Interest Income to Total Loan")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step5!C6+Step5!C21)/Step4!C24")
                .setDescription("The interest income to total loan ratio compares a bank's interest income to its total loans. This ratio can help determine how efficiently a bank is using its funds to earn interest.");
        ratio19.setName("Interest Income to Operating Expense")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step5!C6+Step5!C21)/Step5!C28")
                .setDescription("The interest coverage ratio and the operating expense ratio are both financial ratios that help assess a company's financial health.");
        ratio20.setName("Loan from Related Party to Total Loan")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step4!C44+Step4!C51)/(Step4!C43-Step4!C46-Step4!C54)")
                .setDescription("This ratio indicates the proportion of a company's overall loan portfolio that is made up of loans extended to entities considered \"related parties,\" like subsidiaries, parent companies, or key executives, essentially highlighting the level of financial exposure a company has to its connected entities compared to its total lending activities.");
        ratio21.setName("Management/Technical Fee to Revenue")
                .setExcelFormat("0.00%")
                .setExcelFormula("Step5!C39/Step5!C6")
                .setDescription("It indicates the proportion of a company's total revenue that is derived from technical fees, essentially showing how much of their income is directly generated from technical services or expertise they provide; a lower ratio signifies that technical fees represent a smaller portion of overall revenue.");
        ratio22.setName("Transport Exp. to Revenue")
                .setExcelFormat("0.00%")
                .setExcelFormula("(Step5!C32+Step6!C8)/Step5!C6")
                .setDescription("This ratio indicates the percentage of a company's revenue that is spent on transportation costs, essentially showing how much of each sales dollar is dedicated to moving goods from one location to another; a lower ratio means a company is spending a smaller proportion of its revenue on transportation.");

        this.list.add(ratio1);
        this.list.add(ratio2);
        this.list.add(ratio3);
        this.list.add(ratio4);
        this.list.add(ratio5);
        this.list.add(ratio6);
        this.list.add(ratio7);
        this.list.add(ratio8);
        this.list.add(ratio9);
        this.list.add(ratio10);
        this.list.add(ratio11);
        this.list.add(ratio12);
        this.list.add(ratio13);
        this.list.add(ratio14);
        this.list.add(ratio15);
        this.list.add(ratio16);
        this.list.add(ratio17);
        this.list.add(ratio18);
        this.list.add(ratio19);
        this.list.add(ratio20);
        this.list.add(ratio21);
        this.list.add(ratio22);

        return this.list;
    }
}
