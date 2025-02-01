package org.dlt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public static String filePath = null;
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        if (args.length > 0) {
            logger.info("Formatting TOI");
            filePath = args[0];

            TOI toi = new TOI();
            toi.init(filePath).format();
        }

        Minute minute = new Minute();
        minute.init();
    }
}
