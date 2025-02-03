package org.dlt.view;

import org.dlt.controller.EnterpriseController;
import org.dlt.model.Enterprise;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnterpriseView {
    private JFrame frame;
    private JTextField tinNumberField;
    private JTextField nameEnField;
    private JButton addButton, deleteButton;
    private DefaultListModel<String> listModel;
    private JList<String> userList;

    public EnterpriseView() {
        frame = new JFrame("Swing MVC ORM CRUD");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Tin Number:"));
        tinNumberField = new JTextField();
        formPanel.add(tinNumberField);
        formPanel.add(new JLabel("Name EN:"));
        nameEnField = new JTextField();
        formPanel.add(nameEnField);

        addButton = new JButton("Add Enterprise");
        deleteButton = new JButton("Delete Selected");
        formPanel.add(addButton);
        formPanel.add(deleteButton);

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        loadEnterprises();

        addButton.addActionListener(e -> {
            EnterpriseController.createEnterprise("L001", tinNumberField.getText(), nameEnField.getText(), nameEnField.getText());
            loadEnterprises();
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = userList.getSelectedIndex();
            if (selectedIndex != -1) {
                int userId = Integer.parseInt(userList.getSelectedValue().split(" - ")[0]);
                EnterpriseController.deleteEnterprise(userId);
                loadEnterprises();
            }
        });

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(userList), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void loadEnterprises() {
        listModel.clear();
        List<Enterprise> enterprises = EnterpriseController.getEnterprises();
        for (Enterprise user : enterprises) {
            listModel.addElement(user.getId() + " - " + user.getFullTinNumber() + " (" + user.getNameEn() + ")");
        }
    }
}
