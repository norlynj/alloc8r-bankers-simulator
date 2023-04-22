package view;

import model.BankersAlgorithm;
import model.Process;
import model.Step;
import model.Utility;
import view.component.*;
import view.component.Frame;
import view.component.Label;
import view.component.Panel;
import view.component.CustomTableModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class OutputPanel extends Panel{
    private ImageButton musicOnButton, musicOffButton, homeButton;
    private ImageButton safetyAlgoButton, resourceRequestButton;
    private CustomTableModel processTableModel, allocationTableModel, maxTableModel, availableTableModel, needTableModel;
    private CustomTable processTable, allocationTable, maxTable, availableTable, needTable;
    private JScrollPane processTablePane, allocationTablePane, maxTablePane, availableTablePane, needTablePane;
    private Label stepsLabel, requestResourceLabel, safeSequenceLabel, stepDescriptionLabel;
    private BankersAlgorithm banker;
    Timer timer1, timer2, timer3;
    private int currentRow = 0, stepsCount = 0;
    private boolean repaintInProgress;

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
        stepDescriptionLabel = new Label(("Calculating need"), true, SwingConstants.CENTER);
        stepsLabel.setForeground(Color.white);
        stepDescriptionLabel.setForeground(Color.black);
        requestResourceLabel = new Label("1, 2, 3");
        safeSequenceLabel = new Label("", false, SwingConstants.CENTER);

        stepsLabel.setBounds(144, 467, 444, 134);
        stepDescriptionLabel.setBounds(133, 427, 462, 27);
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
        this.add(homeButton);
        this.add(stepsLabel);
        this.add(stepDescriptionLabel);
        this.add(requestResourceLabel);
        this.add(safeSequenceLabel);
        this.add(safetyAlgoButton);
        this.add(resourceRequestButton);
    }

    private void addTable(int process, int resources){
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
        stepsLabel = new Label(("The step by step execution is shown here."), true, SwingConstants.LEFT);
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

    private void setListeners() {
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        safetyAlgoButton.hover("buttons/safety-algo-hover.png", "buttons/safety-algo.png");
        resourceRequestButton.hover("buttons/resource-req-hover.png", "buttons/resource-req.png");
        safetyAlgoButton.addActionListener(e -> {
            safetyAlgoButton.hover("buttons/safety-algo.png", "buttons/safety-algo-hover.png");
            simulateSafety();

        });
        resourceRequestButton.addActionListener(e -> {
            resourceRequestButton.hover("buttons/resource-req.png", "buttons/resource-req-hover.png");
            simulateRequest();
        });
    }

    public void setBankers(BankersAlgorithm banker) {
        this.banker = banker;
        setListeners();
        banker.calculateSafeSequence();
    }

    public void populateTable() {
        int processTotal = banker.getProcesses().size();
        int resourcesTotal = banker.getProcesses().get(0).getNeed().length;

        processTableModel.setNumRows(processTotal);
        allocationTableModel.setNumRows(processTotal);
        allocationTableModel.setColumnCount(resourcesTotal);
        maxTableModel.setNumRows(processTotal);
        maxTableModel.setColumnCount(resourcesTotal);
        needTableModel.setNumRows(processTotal);
        needTableModel.setColumnCount(resourcesTotal);
        availableTableModel.setColumnCount(resourcesTotal);
        allocationTable.setCenter();
        maxTable.setCenter();
        availableTable.setCenter();
        needTable.setCenter();

        repaintTables();
    }

    private void simulateSafety() {
        resetTables();
        banker.calculateSafeSequence();
        currentRow = 0;
        startNeedCalculationTimer();

    }

    private void startNeedCalculationTimer() {
        if (timer1 != null && timer1.isRunning()) {
            timer1.stop();
        }

        timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow < banker.getProcesses().size()) {
                    Process process = banker.getProcesses().get(currentRow);
                    stepDescriptionLabel.setText("Step 1: Calculating the need matrix");
                    stepsLabel.setText("Need = Max - Allocation<br>" + Arrays.toString(process.getMaximumClaim()) + " - " + Arrays.toString(process.getAllocation()) + " = " + Arrays.toString(process.getNeed()));
                    simulateProcess(currentRow);
                    currentRow++;

                    //disallow simulating both algorithms
                    resourceRequestButton.setEnabled(false);
                } else {
                    timer1.stop();
                    startSafetyTimer();
                    currentRow = 0;
                }
            }
        });
        timer1.start();

    }

    private void startSafetyTimer() {
        if (timer2 != null && timer2.isRunning()) {
            timer2.stop();
        }

        timer2 = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Step> steps = banker.getSafeSequenceSteps();
                if (currentRow < banker.getProcesses().size() && banker.getSafeSequenceSteps() != null && stepsCount < steps.size()) {
                    // simulate process i
                    stepDescriptionLabel.setText("Step 2: Determining the safe sequence");
                    stepsLabel.setText(steps.get(stepsCount).getText());
                    highlightRows(steps.get(stepsCount).getProcessNumber());
                    safeSequenceLabel.setText(safeSequenceLabel.getText() + " " + steps.get(stepsCount).getSafeSequence());
                    currentRow++;
                    stepsCount++;
                    if ((currentRow) == banker.getProcesses().size()) {
                        currentRow = 0;
                    }
                } else {
                    timer2.stop();
                    currentRow = 0;
                    stepsCount = 0;
                    safetyAlgoButton.hover("buttons/safety-algo-hover.png", "buttons/safety-algo.png");
                    resourceRequestButton.setEnabled(true);
                    if (banker.getSafeSequence() == null){
                        safeSequenceLabel.setText("No safe sequence exists");
                        resourceRequestButton.setEnabled(false);
                    } else {
                        safeSequenceLabel.setText(Utility.arrayToString(banker.getSafeSequence()));
                    }
                }
            }
        });
        timer2.start();
    }

    private void simulateRequest() {
        repaintTables();
        banker.requestResource();
        startRequestTimer();
    }

    private void startRequestTimer() {
        currentRow = 0;
        if (timer3 != null && timer3.isRunning()) {
            timer3.stop();
        }
        timer3 = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow < banker.getProcesses().size() && stepsCount < banker.getRequestSequenceSteps().size()) {
                    Step step = banker.getRequestSequenceSteps().get(stepsCount);

                    stepDescriptionLabel.setText("Step 1: Checking if resource can be granted.");
                    stepsLabel.setText(step.getText());

                    if (step.isModifyState()) {
                        banker.modifyStateFromRequest(step.getProcessNumber());
                        repaintTables();
                    }

                    highlightRows(currentRow);
                    currentRow++;
                    stepsCount++;
                    if ((currentRow) == banker.getProcesses().size()) {
                        currentRow = 0;
                    }
                    safetyAlgoButton.setEnabled(false);
                } else {
                    timer3.stop();
                    startSafetyTimer();
                    currentRow = 0;
                    stepsCount = 0;
                    safeSequenceLabel.setText("");
                    resourceRequestButton.hover("buttons/resource-req-hover.png", "buttons/resource-req.png");
                    safetyAlgoButton.setEnabled(true);
                }
            }
        });
        timer3.start();
    }

    private void highlightRows(int currentRow) {
        needTable.clearSelection();
        needTable.addRowSelectionInterval(currentRow, currentRow);
    }

    private void repaintTables() {
        for (int i = 0; i < banker.getProcesses().size(); i++) {
            Process process = banker.getProcesses().get(i);
            processTableModel.setValueAt(process.getProcessName(), i, 0);
            for (int j = 0; j < process.getAllocation().length; j++) {
                allocationTableModel.setValueAt(process.getAllocation()[j],i, j);
                maxTableModel.setValueAt(process.getMaximumClaim()[j],i, j);
                needTableModel.setValueAt(process.getNeed()[j], i, j);
                availableTableModel.setValueAt(banker.getAvailableResources()[j], 0, j);
            }
        }
        requestResourceLabel.setText(Utility.arrayToString(banker.getRequestResource()));
    }

    private void simulateProcess(int currentRow) {
        highlightRows(currentRow);
        Process process = banker.getProcesses().get(currentRow);
        processTableModel.setValueAt(process.getProcessName(), currentRow, 0);
        for (int j = 0; j < process.getAllocation().length; j++) {
            allocationTableModel.setValueAt(process.getAllocation()[j], currentRow, j);
            maxTableModel.setValueAt(process.getMaximumClaim()[j], currentRow, j);
            needTableModel.setValueAt(process.getNeed()[j], currentRow, j);
            availableTableModel.setValueAt(banker.getAvailableResources()[j], 0, j);
        }
        requestResourceLabel.setText(Utility.arrayToString(banker.getRequestResource()));
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
        safeSequenceLabel.setText("");
        requestResourceLabel.setText("");
        safetyAlgoButton.setEnabled(true);
        resourceRequestButton.setEnabled(true);
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

