package org.dlt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dlt.view.EnterpriseView;

public class App {
    public static String filePath = null;
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        if (args.length > 0) {
            logger.info("Formatting TOI");
            filePath = args[0];

            TOI toi = new TOI();
            toi.init(filePath).format();
        } else if (args.length == 0) {
            new EnterpriseView();
        }


        /*
        Minute minute = new Minute();
        minute.init();
        */

//        SwingUtilities.invokeLater(App::showStartupScreen);
    }
/*
    public static void showStartupScreen() {
        DatabaseHelper.initializeDatabase();
        List<String> enterprises = DatabaseHelper.getEnterprises();

        // Create JFrame
        JFrame frame = new JFrame("Product Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        // UI Components
        JLabel label = new JLabel("Select an enterprise:");
        JComboBox<String> productComboBox = new JComboBox<>(enterprises.toArray(new String[0]));

        // Add components to frame
        frame.add(label);
        frame.add(productComboBox);

        // Show frame
        frame.setVisible(true);
    }*/
}
