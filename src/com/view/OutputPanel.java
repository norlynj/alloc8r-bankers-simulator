package view;

import model.BankersAlgorithm;
import model.Process;
import view.component.*;
import view.component.Frame;
import view.component.Label;
import view.component.Panel;
import view.component.CustomTableModel;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class OutputPanel extends Panel{
    private ImageButton musicOnButton, musicOffButton, homeButton;
    private ImageButton safetyAlgoButton, resourceRequestButton;
    private CustomTableModel processTableModel, allocationTableModel, maxTableModel, availableTableModel, needTableModel;
    private CustomTable processTable, allocationTable, maxTable, availableTable, needTable;
    private JScrollPane processTablePane, allocationTablePane, maxTablePane, availableTablePane, needTablePane;
    private Label stepsLabel, requestResourceLabel, safeSequenceLabel;

    public OutputPanel() {
        super("bg/output-panel.png");

        musicOnButton = new ImageButton("buttons/volume-on.png");
        musicOffButton = new ImageButton("buttons/volume-off.png");
        homeButton = new ImageButton("buttons/home.png");

        musicOnButton.setBounds(945, 25, 47, 47);
        musicOffButton.setBounds(945, 25, 47, 47);
        homeButton.setBounds(1010, 25, 47, 47);
        musicOffButton.setVisible(false);

        safetyAlgoButton = new ImageButton("buttons/safety-algo.png");
        resourceRequestButton = new ImageButton("buttons/resource-req.png");
        safetyAlgoButton.setBounds(671, 485, 270, 43);
        resourceRequestButton.setBounds(671, 540, 270, 43);


        // Table
        // Create the table model and table
        processTableModel = new CustomTableModel(new String[]{"Process ID"}, 3, true);
        allocationTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 3);
        maxTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 3);
        availableTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 1);
        needTableModel = new CustomTableModel(new String[]{"A", "B", "C"}, 3);

        processTable = new CustomTable(processTableModel, false);
        allocationTable = new CustomTable(allocationTableModel, false);
        maxTable = new CustomTable(maxTableModel, false);
        availableTable = new CustomTable(availableTableModel, false);
        needTable = new CustomTable(needTableModel, false);

        processTablePane = processTable.createTablePane(75, 171, 137, 235);
        allocationTablePane = allocationTable.createTablePane(215, 171, 220, 235);
        maxTablePane = maxTable.createTablePane(438, 171, 220, 235);
        needTablePane = needTable.createTablePane(661, 171, 220, 235);
        availableTablePane = availableTable.createTablePane(884, 171, 185, 235);

        // Labels
        stepsLabel = new Label(("For Process 1<br>Finish[1] is true and Need < Work so P1 must be kept in the safe sequence<br>Work = Work + Allocation = 3 3 2 + 2 0 0 <br>Work = 5 3 2"), true, SwingConstants.LEFT);
        stepsLabel.setForeground(Color.white);
        requestResourceLabel = new Label("1, 2, 3");
        safeSequenceLabel = new Label("P1, P3, P4, P0, P2", false, SwingConstants.CENTER);

        stepsLabel.setBounds(144, 467, 444, 134);
        requestResourceLabel.setBounds(867, 603, 94, 21);
        safeSequenceLabel.setBounds(234, 720, 641, 34);


        setListeners();

        this.add(musicOnButton);
        this.add(musicOffButton);
        this.add(homeButton);this.add(processTablePane);
        this.add(allocationTablePane);
        this.add(maxTablePane);
        this.add(availableTablePane);
        this.add(needTablePane);
        this.add(stepsLabel);
        this.add(requestResourceLabel);
        this.add(safeSequenceLabel);
        this.add(safetyAlgoButton);
        this.add(resourceRequestButton);
    }

    private void setListeners() {
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        safetyAlgoButton.hover("buttons/safety-algo-hover.png", "buttons/safety-algo.png");
        resourceRequestButton.hover("buttons/resource-req-hover.png", "buttons/resource-req.png");
    }

    public void setBankers(BankersAlgorithm banker) {
        int processTotal = banker.getProcesses().size();
        int resourcesTotal = banker.getProcesses().get(0).getNeed().length;

        processTableModel.setRowCount(processTotal);
        allocationTableModel.setRowCount(processTotal);
        allocationTableModel.setColumnCount(resourcesTotal);
        maxTableModel.setRowCount(processTotal);
        maxTableModel.setColumnCount(resourcesTotal);
        needTableModel.setRowCount(processTotal);
        needTableModel.setColumnCount(resourcesTotal);
        availableTableModel.setColumnCount(resourcesTotal);
        requestResourceLabel.setText(Arrays.toString(banker.getRequestResource()).replace("[", "").replace("]", "").replace(",", ", "));
        safeSequenceLabel.setText(Arrays.toString(banker.getSafeSequence()).replace("[", "").replace("]", "").replace(",", ", "));

        for (int i = 0; i < allocationTableModel.getRowCount(); i++) {
            Process process = banker.getProcesses().get(i);
            processTableModel.setValueAt(process.getProcessName(), i, 0);
            for (int j = 0; j < process.getAllocation().length; j++) {
                allocationTableModel.setValueAt(process.getAllocation()[j],i, j);
                maxTableModel.setValueAt(process.getMaximumClaim()[j],i, j);
                needTableModel.setValueAt(process.getNeed()[j],i, j);
                availableTableModel.setValueAt(banker.getAvailableResources()[j], 0, j);
            }
        }
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

    public static void main(String[] args) {
        OutputPanel m = new OutputPanel();
        Frame frame = new Frame("Output Panel");
        frame.add(m);
        frame.setVisible(true);
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

