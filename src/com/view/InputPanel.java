package view;

import model.ProcessTableModel;
import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class InputPanel extends Panel {
    private static final int FONT_SIZE = 18;
    private static final Font HEADER_FONT = new Font("Montserrat", Font.BOLD, 18);
    private static final Color HEADER_BACKGROUND_COLOR = new Color(210, 237, 232);
    private static final Color TABLE_PANE_COLOR = new Color(118, 138, 150);
    private ImageButton musicOnButton, musicOffButton, homeButton;
    private ImageButton pNPlus, pNMinus, aRNPlus, aRNMinus, runButton;
    private ImageButton resetButton, removeButton;
    private JTextField processNumField, availableReourcesNumField;
    private ProcessTableModel processTableModel;
    private DefaultTableModel allocationTableModel, maxTableModel, availableTableModel, requestResourceTableModel;
    private JTable processTable, allocationTable, maxTable, availableTable, requestResourceTable;
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
        pNMinus.setBounds(122, 217, 44, 44);
        pNPlus.setBounds(275, 217, 44, 44);


        availableReourcesNumField = new JTextField("3", 2);
        availableReourcesNumField.setName("processNumField");
        availableReourcesNumField.setBorder(null);
        availableReourcesNumField.setHorizontalAlignment(SwingConstants.CENTER);
        availableReourcesNumField.setFont(new Font("Montserrat", Font.BOLD, 20));

        aRNPlus = new ImageButton("buttons/add.png");
        aRNMinus = new ImageButton("buttons/minus.png");
        availableReourcesNumField.setBounds(468, 217, 192, 44);
        aRNMinus.setBounds(446, 217, 44, 44);
        aRNPlus.setBounds(599, 217, 139, 44);

        runButton = new ImageButton("buttons/run.png");
        runButton.setBounds(832, 218, 94, 42);


        // Table
        // Create the table model and table
        ProcessTableModel processTableModel = new ProcessTableModel(new String[]{"Process ID"}, 3);
        DefaultTableModel allocationTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 3);
        DefaultTableModel maxTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 3);
        DefaultTableModel availableTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 1);
        DefaultTableModel requestResourceTableModel = new DefaultTableModel(new String[]{"A", "B", "C"}, 1);

        JTable processTable = new JTable(processTableModel);
        JTable allocationTable = new JTable(allocationTableModel);
        JTable maxTable = new JTable(maxTableModel);
        JTable availableTable = new JTable(availableTableModel);
        JTable requestResourceTable = new JTable(requestResourceTableModel);

        processTable.setRowHeight(30);
        allocationTable.setRowHeight(30);
        maxTable.setRowHeight(30);
        availableTable.setRowHeight(30);
        requestResourceTable.setRowHeight(30);


        processTable.setFont(new Font("Montserrat", Font.PLAIN, FONT_SIZE));
        allocationTable.setFont(new Font("Montserrat", Font.PLAIN, FONT_SIZE));
        maxTable.setFont(new Font("Montserrat", Font.PLAIN, FONT_SIZE));
        availableTable.setFont(new Font("Montserrat", Font.PLAIN, FONT_SIZE));
        requestResourceTable.setFont(new Font("Montserrat", Font.PLAIN, FONT_SIZE));

        // Create the scroll pane for the table
        JScrollPane processTablePane = new JScrollPane(processTable);
        JScrollPane allocationTablePane = new JScrollPane(allocationTable);
        JScrollPane maxTablePane = new JScrollPane(maxTable);
        JScrollPane availableTablePane = new JScrollPane(availableTable);
        JScrollPane requestResourceTablePane = new JScrollPane(requestResourceTable);

        processTablePane.setBounds(71, 367, 166, 227);
        allocationTablePane.setBounds(239, 367, 266, 227);
        maxTablePane.setBounds(507, 367, 266, 227);
        availableTablePane.setBounds(775, 367, 266, 227);
        requestResourceTablePane.setBounds(100, 684, 293, 65);


        processTablePane.setBorder(BorderFactory.createEmptyBorder());
        allocationTablePane.setBorder(BorderFactory.createEmptyBorder());
        maxTablePane.setBorder(BorderFactory.createEmptyBorder());
        availableTablePane.setBorder(BorderFactory.createEmptyBorder());
        requestResourceTablePane.setBorder(BorderFactory.createEmptyBorder());

        processTablePane.getViewport().setBackground(TABLE_PANE_COLOR);
        allocationTablePane.getViewport().setBackground(TABLE_PANE_COLOR);
        maxTablePane.getViewport().setBackground(TABLE_PANE_COLOR);
        availableTablePane.getViewport().setBackground(TABLE_PANE_COLOR);
        requestResourceTablePane.getViewport().setBackground(TABLE_PANE_COLOR);

        // Set the cell renderer to center-align the cell contents
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < processTable.getColumnModel().getColumnCount(); i++) {
            processTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        for (int i = 0; i < allocationTable.getColumnModel().getColumnCount(); i++) {
            allocationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            maxTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            availableTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            requestResourceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set the font and background color of the table header
        JTableHeader processTableHeader = processTable.getTableHeader();
        processTableHeader.setFont(HEADER_FONT);
        processTableHeader.setBackground(HEADER_BACKGROUND_COLOR);

        JTableHeader allocationTableHeader = allocationTable.getTableHeader();
        allocationTableHeader.setFont(HEADER_FONT);
        allocationTableHeader.setBackground(HEADER_BACKGROUND_COLOR);

        JTableHeader maxTableHeader = maxTable.getTableHeader();
        maxTableHeader.setFont(HEADER_FONT);
        maxTableHeader.setBackground(HEADER_BACKGROUND_COLOR);

        JTableHeader availableResourcesHeader = availableTable.getTableHeader();
        availableResourcesHeader.setFont(HEADER_FONT);
        availableResourcesHeader.setBackground(HEADER_BACKGROUND_COLOR);

        JTableHeader requestResourceHeader = requestResourceTable.getTableHeader();
        requestResourceHeader.setFont(HEADER_FONT);
        requestResourceHeader.setBackground(HEADER_BACKGROUND_COLOR);

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

