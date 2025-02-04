package org.dlt.view;

import org.dlt.model.Enterprise;

import javax.swing.*;
import java.awt.*;

public class EnterpriseDetailsView extends JFrame {
    private JLabel fullTinNumberLabel, nameEnLabel, fullAddressLabel;

    public EnterpriseDetailsView(Enterprise enterprise) {
        setTitle("Enterprise Details");
        setSize(600, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1));

        fullTinNumberLabel  = new JLabel("TIN: " + enterprise.getFullTinNumber());
        nameEnLabel         = new JLabel("Name EN: "+enterprise.getNameEn());
        fullAddressLabel    = new JLabel("Full Address: "+enterprise.getFullAddress());

        add(fullTinNumberLabel);
        add(nameEnLabel);
        add(fullAddressLabel);

        setVisible(true);
    }
}
