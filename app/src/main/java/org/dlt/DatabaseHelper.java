package org.dlt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final Logger logger = LogManager.getLogger(DatabaseHelper.class);
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS enterprises("+
                    "id INTEGER PRIMARY KEY, tin_head TEXT, tin_number TEXT, name_kh TEXT, name_en TEXT)";
            stmt.executeUpdate(createTableSQL);

            // Check if data exists
            String checkDataSQL = "SELECT COUNT(*) FROM enterprises";
            ResultSet rs = stmt.executeQuery(checkDataSQL);
            if(rs.next() && rs.getInt(1) == 0) {
                String insertDataSQL = "INSERT INTO enterprises (tin_head, tin_number, name_kh, name_en) "+
                        "VALUES ('L001','100145574','បេ តាហ្គ្រោ (ខេ មបូឌា) ខមភេ នី លីមីធីត','BETAGRO (CAMBODIA) COMPANY LIMITED'),"+
                        "('L001','902101371','អ៊ិនណូមេដ ហ៊ែលឃែរ ប្រូដាក (ខេមបូឌា) ឯ.ក','INNOMED HEALTHCARE PRODUCTS (CAMBODIA) CO., LTD.'),"+
                        "('L001','902100300','អង្គរ ហ្គោវ រីសត ឯ.ក','ANGKOR GOLF RESORT CO., LTD.'),"+
                        "('L001','901900604','ឡាយហ្វេង អៅដរ (ខេមបូឌា) ឯ.ក','LIEFENG OUTDOORS (CAMBODIA) CO., LTD.')";;
                stmt.execute(insertDataSQL);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static List<String> getEnterprises() {
        List<String> enterpriseList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name_en FROM enterprises ORDER BY name_en ASC")) {
            while (rs.next()) {
                enterpriseList.add(rs.getString("name_en"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return enterpriseList;
    }
}
