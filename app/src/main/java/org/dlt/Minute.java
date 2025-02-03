package org.dlt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.dlt.model.Enterprise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Minute {
    private String title;
    private boolean reportGenerated = false;
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
                .setFullAddress("#3, Street 112, Sangkat Psar Thmei, Khan Duan Penh, Phnom Penh");

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
//            logger.info("Reading DOCX file = " + inputFile);

            for (int i = 0; i < placeholders.length; i++) {
                String searchStr = placeholders[i];
                String replaceStr = replacements[i];

                logger.info("PLACEHOLDER LOOPER=" + searchStr);

                for (XWPFParagraph paragraph : docx.getParagraphs()) {
                    String text = paragraph.getText();

//                    logger.info("text=" + text);

                    if (text.contains(searchStr)) {
                        for (XWPFRun run : paragraph.getRuns()) {
                            String runText = run.getText(0);

//                            logger.info("runText=" + runText);

                            if (runText != null && runText.contains(searchStr)) {
//                                logger.info("replacement=" + replaceStr);
                                runText = runText.replace(searchStr, replaceStr);
                                run.setText(runText, 0);
                                run.setBold(true);
                            }
                        }
                    }
                }

                int f=0;
                // Need to loop throw 3 footers or MORE, but normally 3
                // 1. Section-based
                // 2. Even and odd pages
                // 3. First-page footer
                // 4. Regular Section Footer (not the above 3)
                for (XWPFFooter footer : docx.getFooterList()) {
                    logger.info("footer# "+f);
                    for (XWPFParagraph footerParagraph : footer.getParagraphs()) {
                        for (XWPFRun footerRun : footerParagraph.getRuns()) {
                            String footerRunText = footerRun.getText(0);
                            logger.info("footerTEXT=" + footerRunText);
                            if (footerRunText != null && footerRunText.contains(searchStr)) {
                                footerRunText = footerRunText.replace(searchStr, replaceStr);
                                footerRun.setText(footerRunText, 0);
                            }
                        }
                    }
                    f++;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                logger.info("Writing DOCX file = " + outputFile);
                docx.write(fos);

                fis.close();
                docx.close();

                this.reportGenerated = true;
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

    public boolean isReportGenerated() {
        return reportGenerated;
    }
}