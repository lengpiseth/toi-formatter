package org.dlt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.dlt.model.Enterprise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Minute {
    private String title;
    private static final Logger logger = LogManager.getLogger(Minute.class);

    private final String[] placeholders = {
            "[NAME_KH]",
            "[NAME_EN]",
            "[TIN_NUMBER]",
            "[BUSINESS_ACTIVITY]",
            "[FULL_ADDRESS]"
    };
    private String[] replacements = null;

    public void Minute(String title) {
        this.title = title; // No usage whatsoever
        this.init();
    }

    public void init() {
        Enterprise enterprise = new Enterprise();
        enterprise
                .setTinHead("L001")
                .setTinNumber("105006282")
                .setNameKh("ម៉េង យី ហ្គាមេន ឯ.ក")
                .setNameEn("Meng Yee Garment Co., Ltd.")
                .addBusinessActivity("Steel Smelting Factory")
                .setHouseNumber("168").setStreet("396").setCity("Phnom Penh");

        this.createReport302FromTemplate(enterprise);
    }

    private void createReport302FromTemplate(Enterprise enterprise) {
        String inputFile    = "S:\\Project_Java\\toi-formatter\\app\\my_resources\\report-302-template.docx";
        String outputFile   = "S:\\Project_Java\\toi-formatter\\app\\my_resources\\report-302-generated.docx";

        replacements = new String[]{
                enterprise.getNameKh(),
                enterprise.getNameEn(),
                enterprise.getFullTinNumber(),
                enterprise.getPrimaryBusinessActivity(),
                enterprise.getFullAddress()
        };

        try (FileInputStream fis = new FileInputStream(inputFile);
             XWPFDocument docx = new XWPFDocument(fis);) {
            logger.info("Reading DOCX file = " + inputFile);

            for (int i = 0; i < placeholders.length; i++) {
                String searchStr = placeholders[i];
                String replaceStr = replacements[i];

                logger.info("placeholder=" + searchStr);

                for (XWPFParagraph paragraph : docx.getParagraphs()) {
                    String text = paragraph.getText();

                    logger.info("text=" + text);

                    if (text.contains(searchStr)) {
                        for (XWPFRun run : paragraph.getRuns()) {
                            String runText = run.getText(0);
                            logger.info("runText=" + runText);
                            if (runText != null && runText.contains(searchStr)) {
                                logger.info("replacement=" + replaceStr);
                                runText = runText.replace(searchStr, replaceStr);
                                run.setText(runText, 0);
                                run.setBold(true);
                            }
                        }
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                docx.write(fos);
                logger.info("Writing DOCX file = " + outputFile);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}