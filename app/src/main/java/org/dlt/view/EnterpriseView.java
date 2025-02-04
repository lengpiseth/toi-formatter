package org.dlt.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dlt.controller.EnterpriseController;
import org.dlt.model.Enterprise;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnterpriseView {
    private static final Logger log = LogManager.getLogger(EnterpriseView.class);

    private JFrame frame;
    private JLabel tinLabel, nameLabel, addressLabel;
    private JTextField tinNumberField, nameEnField, fullAddressField;
    private JButton addButton, deleteButton, detailsButton;
    private DefaultListModel<Enterprise> listModel;
    private JList<Enterprise> enterpriseList;

    public EnterpriseView() {
        frame = new JFrame("ENTERPRISE LIST");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        tinLabel     = new JLabel("TIN Number");
        nameLabel    = new JLabel("Enterprise Name");
        addressLabel = new JLabel("Enterprise Address");

        tinNumberField   = new JTextField(10);
        nameEnField      = new JTextField(10);
        fullAddressField = new JTextField(10);

        addButton       = new JButton("Add Enterprise");
        deleteButton    = new JButton("Delete Selected");
        detailsButton   = new JButton("View Details");

        JPanel myPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(myPanel);
        myPanel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        // Set up the horizontal group
        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(tinLabel)
            .addComponent(nameLabel)
            .addComponent(addressLabel));
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(tinNumberField)
            .addComponent(nameEnField)
            .addComponent(fullAddressField));
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(addButton)
            .addComponent(deleteButton)
            .addComponent(detailsButton));
        groupLayout.setHorizontalGroup(hGroup);

        // Set up the vertical group
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(tinLabel)
            .addComponent(tinNumberField)
            .addComponent(addButton));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(nameLabel)
            .addComponent(nameEnField)
            .addComponent(deleteButton));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(addressLabel)
            .addComponent(fullAddressField)
            .addComponent(detailsButton));
        groupLayout.setVerticalGroup(vGroup);

//        JPanel formPanel = new JPanel(new GridLayout(4, 2));
//        formPanel.setToolTipText("Enterprise list.....");
//        formPanel.add(new JLabel("Tin Number:"));
//        tinNumberField = new JTextField();
//        formPanel.add(tinNumberField);
//        formPanel.add(new JLabel("Enterprise Name (En):"));
//        nameEnField = new JTextField();
//        formPanel.add(nameEnField);
//        formPanel.add(new JLabel("Registered Address:"));
//        fullAddressField = new JTextField();
//        formPanel.add(fullAddressField);

//        addButton       = new JButton("Add Enterprise");
//        deleteButton    = new JButton("Delete Selected");
//        detailsButton   = new JButton("View Details");
//        formPanel.add(addButton);
//        formPanel.add(deleteButton);
//        formPanel.add(detailsButton);

        listModel = new DefaultListModel<>();
        enterpriseList = new JList<>(listModel);
        enterpriseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enterpriseList.setVisibleRowCount(5);

        loadEnterprises(); // Load data from database

        addButton.addActionListener(e -> {
            Enterprise newEnterprise = new Enterprise();
            newEnterprise
                    .setTinHead("L001")
                    .setTinNumber(tinNumberField.getText())
                    .setNameEn(nameEnField.getText())
                    .setFullAddress(fullAddressField.getText());
            if (EnterpriseController.createEnterprise(newEnterprise)) {
                loadEnterprises();
                tinNumberField.setText("");
                nameEnField.setText("");
                fullAddressField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = enterpriseList.getSelectedIndex();
            if (selectedIndex != -1) {
                long enterpriseId = enterpriseList.getSelectedValue().getId();
                EnterpriseController.deleteEnterprise(enterpriseId);
                loadEnterprises();
            }
        });

        detailsButton.addActionListener(e -> showEnterpriseDetails());


        frame.add(myPanel, BorderLayout.NORTH);
//        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(enterpriseList), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void loadEnterprises() {
        listModel.clear();
        List<Enterprise> enterprises = EnterpriseController.getEnterprises();
        for (Enterprise enterprise : enterprises) {
            listModel.addElement(enterprise);
        }
    }

    private void showEnterpriseDetails() {
        log.info(enterpriseList.getSelectedValue());
        Enterprise selectedEnterprise = enterpriseList.getSelectedValue();
        if(selectedEnterprise != null) {
            new EnterpriseDetailsView(selectedEnterprise);
        }
    }
}
