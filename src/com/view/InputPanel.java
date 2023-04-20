package view;

import model.BankersAlgorithm;
import model.Process;
import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;
import view.component.CustomTable;
import view.component.CustomTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class InputPanel extends Panel {
    private ImageButton musicOnButton, musicOffButton, homeButton;
    private ImageButton pNPlus, pNMinus, aRNPlus, aRNMinus, runButton;
    private ImageButton resetButton, removeButton;
    private JTextField processNumField, availableResourcesNumField;
    private CustomTableModel processTableModel;
    private CustomTableModel allocationTableModel, maxTableModel, availableTableModel, requestResourceTableModel;
    private CustomTable processTable, allocationTable, maxTable, availableTable, requestResourceTable;
    private JScrollPane processTablePane, allocationTablePane, maxTablePane, availableTablePane, requestResourceTablePane;




    public InputPanel() {

        super("bg/input-panel.png");

        // nav buttons
        musicOnButton = new ImageButton("buttons/volume-on.png");
        musicOffButton = new ImageButton("buttons/volume-off.png");
        homeButton = new ImageButton("buttons/home.png");

        musicOnButton.setBounds(945, 25, 47, 47);
        musicOffButton.setBounds(945, 25, 47, 47);
        homeButton.setBounds(1010, 25, 47, 47);

        musicOffButton.setVisible(false);

        // green box area
        processNumField = new JTextField("3", 2);
        processNumField.setName("processNumField");
        processNumField.setBorder(null);
        processNumField.setHorizontalAlignment(SwingConstants.CENTER);
        processNumField.setFont(new Font("Montserrat", Font.BOLD, 20));

        pNPlus = new ImageButton("buttons/add.png");
        pNMinus = new ImageButton("buttons/minus.png");
        processNumField.setBounds(144, 217, 139, 42);
        pNMinus.setBounds(100, 217, 44, 44);
        pNPlus.setBounds(284, 217, 44, 44);


        availableResourcesNumField = new JTextField("3", 2);
        availableResourcesNumField.setName("availableResourcesNumField");
        availableResourcesNumField.setBorder(null);
        availableResourcesNumField.setHorizontalAlignment(SwingConstants.CENTER);
        availableResourcesNumField.setFont(new Font("Montserrat", Font.BOLD, 20));

        aRNPlus = new ImageButton("buttons/add.png");
        aRNMinus = new ImageButton("buttons/minus.png");
        availableResourcesNumField.setBounds(468, 217, 139, 44);
        aRNMinus.setBounds(424, 217, 44, 44);
        aRNPlus.setBounds(608, 217, 44, 44);

        runButton = new ImageButton("buttons/run.png");
        runButton.setBounds(832, 218, 94, 42);


        // Table
        // Create the table model and table
        processTableModel = new CustomTableModel(new String[]{"Process ID"}, 3, true);
        allocationTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 3);
        maxTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 3);
        availableTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 1);
        requestResourceTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 1);

        processTable = new CustomTable(processTableModel);
        allocationTable = new CustomTable(allocationTableModel);
        maxTable = new CustomTable(maxTableModel);
        availableTable = new CustomTable(availableTableModel);
        requestResourceTable = new CustomTable(requestResourceTableModel);

        processTablePane = processTable.createTablePane(71, 367, 166, 227);
        allocationTablePane = allocationTable.createTablePane(239, 367, 266, 227);
        maxTablePane = maxTable.createTablePane(507, 367, 266, 227);
        availableTablePane = availableTable.createTablePane(775, 367, 266, 227);
        requestResourceTablePane = requestResourceTable.createTablePane(100, 684, 293, 65);

        runButton.setEnabled(false); // should have inputs first

        // Reset and Remove buttons
        resetButton = new ImageButton("buttons/reset.png");
        removeButton = new ImageButton("buttons/remove.png");
        resetButton.setBounds(846, 657, 94, 42);
        removeButton.setBounds(963, 657, 94, 42);


        setListeners();

        this.add(pNMinus);
        this.add(pNPlus);
        this.add(aRNMinus);
        this.add(aRNPlus);
        this.add(processNumField);
        this.add(availableResourcesNumField);
        this.add(musicOnButton);
        this.add(musicOffButton);
        this.add(homeButton);
        this.add(processTablePane);
        this.add(allocationTablePane);
        this.add(maxTablePane);
        this.add(availableTablePane);
        this.add(requestResourceTablePane);
        this.add(resetButton);
        this.add(removeButton);
        this.add(runButton);

    }

    private void setListeners() {
        pNMinus.hover("buttons/minus-hover.png", "buttons/minus.png");
        pNPlus.hover("buttons/add-hover.png", "buttons/add.png");
        aRNMinus.hover("buttons/minus-hover.png", "buttons/minus.png");
        aRNPlus.hover("buttons/add-hover.png", "buttons/add.png");
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        resetButton.hover("buttons/reset-hover.png", "buttons/reset.png");
        removeButton.hover("buttons/remove-hover.png", "buttons/remove.png");
        runButton.hover("buttons/run-hover.png", "buttons/run.png");

        pNMinus.addActionListener(e -> processNumField.setText(String.valueOf(Integer.parseInt(processNumField.getText()) - 1)));
        pNPlus.addActionListener(e -> processNumField.setText(String.valueOf(Integer.parseInt(processNumField.getText()) + 1)));
        aRNMinus.addActionListener(e -> availableResourcesNumField.setText(String.valueOf(Integer.parseInt(availableResourcesNumField.getText()) - 1)));
        aRNPlus.addActionListener(e -> availableResourcesNumField.setText(String.valueOf(Integer.parseInt(availableResourcesNumField.getText()) + 1)));

        removeButton.addActionListener(e -> {
            int row = processTable.getSelectedRow();
            if (row > -1 && processTable.getRowCount() > 3) {
                processTableModel.removeRow(row);
                allocationTableModel.removeRow(row);
                maxTableModel.removeRow(row);
                processNumField.setText(String.valueOf(Integer.parseInt(processNumField.getText()) - 1));
            }
        });
        listenToUserInput();
    }

    private void listenToUserInput() {
        inputValidator(processNumField, 3, 30);
        inputValidator(availableResourcesNumField, 3, 10);
        allocationTableModel.addTableModelListener(e -> updateRunButton());
        maxTableModel.addTableModelListener(e -> updateRunButton());
        availableTableModel.addTableModelListener(e -> updateRunButton());
        requestResourceTableModel.addTableModelListener(e -> updateRunButton());

    }

    private void inputValidator(JTextField input, int minimum, int maximum) {
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                try {
                    String text = input.getText();
                    int value = Integer.parseInt(text);
                    if (value < minimum || value > maximum) {
                        // If the value is out of range, highlight the text field
                        input.setBackground(new Color(255, 202, 202));
                        runButton.setEnabled(false);
                    } else {
                        // Otherwise, clear the highlighting and add or remove columns/rows
                        input.setBackground(UIManager.getColor("TextField.background"));
                        if (input.getName().equals("processNumField")) {
                            processTableModel.setNumRows(value);
                            allocationTableModel.setNumRows(value);
                            maxTableModel.setNumRows(value);
                        } else {
                            allocationTableModel.setColumnCount(value);
                            maxTableModel.setColumnCount(value);
                            availableTableModel.setColumnCount(value);
                            requestResourceTableModel.setColumnCount(value);
                        }
                        if (validTable()){
                            runButton.setEnabled(true);
                        }                    }
                } catch (NumberFormatException ex) {
                    // If the input cannot be parsed as an integer, highlight the text field
                    input.setBackground(new Color(255, 202, 202));
                }
            }
        });
    }

    private void updateRunButton() {
        if (validTable()) {
            runButton.setEnabled(true);
        } else{
            runButton.setEnabled(false);
        }
    }

    private boolean validTable() {

        for (int row = 0; row < processTable.getRowCount(); row++) {
            for (int col = 0; col < processTable.getColumnCount(); col++) {
                if (allocationTable.getValueAt(row, col) == null || allocationTable.toString().trim().isEmpty() || maxTable.getValueAt(row, col) == null || maxTable.toString().trim().isEmpty()) {
                    return false;
                }
            }
        }
        for (int row = 0; row < availableTable.getRowCount(); row++) {
            for (int col = 0; col < availableTable.getColumnCount(); col++) {
                if (availableTable.getValueAt(row, col) == null || availableTable.toString().trim().isEmpty() || requestResourceTable.getValueAt(row, col) == null || requestResourceTable.toString().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public BankersAlgorithm getBankers() {
        BankersAlgorithm banker = new BankersAlgorithm();
        String processName;

        //get available and resource request
        int[] available = new int[Integer.parseInt(availableResourcesNumField.getText())];
        int[] request = new int[Integer.parseInt(availableResourcesNumField.getText())];

        //get allocation and max
        for (int i = 0; i < allocationTableModel.getRowCount(); i++) {
            int[] allocation = new int[available.length];
            int[] max = new int[available.length];
            processName = (String) processTableModel.getValueAt(i, 0);
            for (int j = 0; j < allocation.length; j++) {
                available[j] = (int) availableTableModel.getValueAt(0, j);
                request[j] = (int) requestResourceTableModel.getValueAt(0, j);
                allocation[j] = (int) allocationTableModel.getValueAt(i, j);
                max[j] = (int) maxTableModel.getValueAt(i, j);
            }
            banker.setResourcesNumber(Integer.parseInt(processNumField.getText()));
            banker.add(new Process(processName, allocation, max));
        }
        banker.setAvailableResources(available);
        banker.setRequestResource(request);
        banker.calculateSafeSequence();
        return banker;
    }

    public void resetTables() {
        System.out.println("RESET");
        allocationTableModel.reset();
        maxTableModel.reset();
        requestResourceTableModel.reset();
        availableTableModel.reset();
    }

    public static void main(String[] args) {
        InputPanel m = new InputPanel();
        Frame frame = new Frame("Input Panel");
        frame.add(m);
        frame.setVisible(true);
    }


    public void musicClick() {
        if (musicOffButton.isVisible()){
            musicOnButton.setVisible(true);
            musicOffButton.setVisible(false);
        } else {
            musicOnButton.setVisible(false);
            musicOffButton.setVisible(true);
        }
    }

    public CustomTableModel getProcessTableModel() {
        return processTableModel;
    }

    public CustomTableModel getAllocationTableModel() {
        return allocationTableModel;
    }

    public CustomTableModel getMaxTableModel() {
        return maxTableModel;
    }

    public CustomTableModel getAvailableTableModel() {
        return availableTableModel;
    }

    public CustomTableModel getRequestResourceTableModel() {
        return requestResourceTableModel;
    }

    public JTextField getProcessNumField() {
        return processNumField;
    }

    public JTextField getAvailableResourcesNumField() {
        return availableResourcesNumField;
    }

    public ImageButton getMusicOnButton() {
        return musicOnButton;
    }
    public ImageButton getMusicOffButton() {
        return musicOffButton;
    }
    public ImageButton getHomeButton() {
        return homeButton;
    }

    public ImageButton getRunButton() {
        return runButton;
    }
}

