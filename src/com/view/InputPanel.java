package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;
import view.component.CustomTable;
import view.component.ProcessTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InputPanel extends Panel {
    private ImageButton musicOnButton, musicOffButton, homeButton;
    private ImageButton pNPlus, pNMinus, aRNPlus, aRNMinus, runButton;
    private ImageButton resetButton, removeButton;
    private JTextField processNumField, availableReourcesNumField;
    private ProcessTableModel processTableModel;
    private DefaultTableModel allocationTableModel, maxTableModel, availableTableModel, requestResourceTableModel;
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


        availableReourcesNumField = new JTextField("3", 2);
        availableReourcesNumField.setName("processNumField");
        availableReourcesNumField.setBorder(null);
        availableReourcesNumField.setHorizontalAlignment(SwingConstants.CENTER);
        availableReourcesNumField.setFont(new Font("Montserrat", Font.BOLD, 20));

        aRNPlus = new ImageButton("buttons/add.png");
        aRNMinus = new ImageButton("buttons/minus.png");
        availableReourcesNumField.setBounds(468, 217, 139, 44);
        aRNMinus.setBounds(424, 217, 44, 44);
        aRNPlus.setBounds(608, 217, 44, 44);

        runButton = new ImageButton("buttons/run.png");
        runButton.setBounds(832, 218, 94, 42);


        // Table
        // Create the table model and table
        ProcessTableModel processTableModel = new ProcessTableModel(new String[]{"Process ID"}, 3);
        DefaultTableModel allocationTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 3);
        DefaultTableModel maxTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 3);
        DefaultTableModel availableTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 1);
        DefaultTableModel requestResourceTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 1);

        CustomTable processTable = new CustomTable(processTableModel);
        CustomTable allocationTable = new CustomTable(allocationTableModel);
        CustomTable maxTable = new CustomTable(maxTableModel);
        CustomTable availableTable = new CustomTable(availableTableModel);
        CustomTable requestResourceTable = new CustomTable(requestResourceTableModel);

        JScrollPane processTablePane = processTable.createTablePane(71, 367, 166, 227);
        JScrollPane allocationTablePane = allocationTable.createTablePane(239, 367, 266, 227);
        JScrollPane maxTablePane = maxTable.createTablePane(507, 367, 266, 227);
        JScrollPane availableTablePane = availableTable.createTablePane(775, 367, 266, 227);
        JScrollPane requestResourceTablePane = requestResourceTable.createTablePane(100, 684, 293, 65);


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
        this.add(availableReourcesNumField);
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

    public ImageButton getMusicOnButton() {
        return musicOnButton;
    }
    public ImageButton getMusicOffButton() {
        return musicOffButton;
    }
    public ImageButton getHomeButton() {
        return homeButton;
    }
}

