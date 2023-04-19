import dataOperations.CollectData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JButton selectFileButton;
    private JTable table;
    private CollectData collectData;
    private DefaultTableModel model;

    public GUI() {
        createButton();
        createTable();

        setTitle("Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JScrollPane(this.table), BorderLayout.CENTER);
        container.add(this.selectFileButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createButton() {
        this.selectFileButton = new JButton("Select File");
        this.selectFileButton.addActionListener(this);
    }

    private void createTable() {
        this.model = new DefaultTableModel();
        this.table = new JTable(this.model);
        this.model.addColumn("Employee ID#1");
        this.model.addColumn("Employee ID#2");
        this.model.addColumn("Project ID");
        this.model.addColumn("Days worked");
        this.table.setAutoCreateRowSorter(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.selectFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*.csv)", "csv");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                this.collectData = new CollectData(fileChooser.getSelectedFile());

                String firstEmployeeCheck = "";

                for (String employeePairsData : this.collectData.getResults()) {

                    String[] employeeData = employeePairsData.split(", ");

                    if (!firstEmployeeCheck.equals(employeeData[1])) {

                        firstEmployeeCheck = employeeData[0];

                        this.model.addRow(new Object[]{employeeData[0], employeeData[1], employeeData[2], employeeData[3]});
                    }
                }
            }
        }
    }
}