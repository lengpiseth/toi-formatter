package org.dlt.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dlt.App;
import org.dlt.controller.EnterpriseController;
import org.dlt.model.Enterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EnterpriseView {
    private static final Logger log = LogManager.getLogger(EnterpriseView.class);

    private JFrame frame;
    private JTextField tinNumberField, nameEnField, fullAddressField;
    private JButton addButton, deleteButton, detailsButton;
    private DefaultListModel<Enterprise> listModel;
    private JList<Enterprise> enterpriseList;

    public EnterpriseView() {
        frame = new JFrame("Swing MVC ORM CRUD");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Tin Number:"));
        tinNumberField = new JTextField();
        formPanel.add(tinNumberField);
        formPanel.add(new JLabel("Name EN:"));
        nameEnField = new JTextField();
        formPanel.add(nameEnField);
        formPanel.add(new JLabel("Full Address:"));
        fullAddressField = new JTextField();
        formPanel.add(fullAddressField);

        addButton       = new JButton("Add Enterprise");
        deleteButton    = new JButton("Delete Selected");
        detailsButton   = new JButton("View Details");
        formPanel.add(addButton);
        formPanel.add(deleteButton);
        formPanel.add(detailsButton);

        listModel = new DefaultListModel<>();
        enterpriseList = new JList<>(listModel);
        enterpriseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enterpriseList.setVisibleRowCount(5);

        loadEnterprises(); // Load data from database

        addButton.addActionListener(e -> {
            EnterpriseController.createEnterprise("L001", tinNumberField.getText(), nameEnField.getText(), nameEnField.getText());
            loadEnterprises();
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

        frame.add(formPanel, BorderLayout.NORTH);
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
