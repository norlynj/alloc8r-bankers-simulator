package view;

import model.BankersAlgorithm;
import model.Process;
import model.Utility;
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
    private BankersAlgorithm banker;

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
        this.add(homeButton);
        this.add(stepsLabel);
        this.add(requestResourceLabel);
        this.add(safeSequenceLabel);
        this.add(safetyAlgoButton);
        this.add(resourceRequestButton);
    }

    private void addTable(int process, int resources){
        // Table
        // Create the table model and table
        String[] header = Utility.createHeadersArray(resources) ;

        processTableModel = new CustomTableModel(new String[]{"Process ID"}, process, true);
        allocationTableModel = new CustomTableModel(header, process);
        maxTableModel = new CustomTableModel(header, process);
        availableTableModel = new CustomTableModel(header, 1);
        needTableModel = new CustomTableModel(header, process);

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
        this.add(processTablePane);
        this.add(allocationTablePane);
        this.add(maxTablePane);
        this.add(availableTablePane);
        this.add(needTablePane);
    }

    private void setListeners() {
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        safetyAlgoButton.hover("buttons/safety-algo-hover.png", "buttons/safety-algo.png");
        resourceRequestButton.hover("buttons/resource-req-hover.png", "buttons/resource-req.png");
        safetyAlgoButton.addActionListener(e -> simulateSafety());
        resourceRequestButton.addActionListener(e -> simulateRequest());
    }

    public void setBankers(BankersAlgorithm banker) {
        this.banker = banker;
        setListeners();
        banker.calculateSafeSequence();
    }

    public void populateTable() {
        int processTotal = banker.getProcesses().size();
        int resourcesTotal = banker.getProcesses().get(0).getNeed().length;

        addTable(processTotal, resourcesTotal);

        requestResourceLabel.setText(Utility.arrayToString(banker.getSafeSequence()));

        if (banker.getSafeSequence() == null){
            safeSequenceLabel.setText("No safe sequence exists");
        } else {
            safeSequenceLabel.setText(Utility.arrayToString(banker.getSafeSequence()));
        }

        for (int i = 0; i < processTotal; i++) {
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

    private void simulateSafety() {
        banker.calculateSafeSequence();
    }

    private void simulateRequest() {
        banker.requestResource();

        //repaint table
        for (int i = 0; i < banker.getProcesses().size(); i++) {
            Process process = banker.getProcesses().get(i);
            processTableModel.setValueAt(process.getProcessName(), i, 0);
            for (int j = 0; j < process.getAllocation().length; j++) {
                allocationTableModel.setValueAt(process.getAllocation()[j],i, j);
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

    public void resetTables() {
        allocationTableModel.reset();
        maxTableModel.reset();
        needTableModel.reset();
        availableTableModel.reset();
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

