package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class InputDecisionPanel extends Panel{
    private ImageButton fromATextFileButton, userDefinedButton, randomButton;
    private ImageButton musicOnButton, musicOffButton, homeButton;

    public InputDecisionPanel() {
        super("bg/input-choice-panel.png");

        fromATextFileButton = new ImageButton("buttons/fromtext.png");
        userDefinedButton = new ImageButton("buttons/user.png");
        randomButton = new ImageButton("buttons/random.png");
        musicOnButton = new ImageButton("buttons/volume-on.png");
        musicOffButton = new ImageButton("buttons/volume-off.png");
        homeButton = new ImageButton("buttons/home.png");


        fromATextFileButton.setBounds(526, 552, 385, 61);
        userDefinedButton.setBounds(462, 472, 449, 61);
        randomButton.setBounds(683, 391, 229, 61);
        musicOnButton.setBounds(945, 25, 47, 47);
        musicOffButton.setBounds(945, 25, 47, 47);
        homeButton.setBounds(1010, 25, 47, 47);

        musicOffButton.setVisible(false);


        setListeners();

        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/bg/input-choice-panel.png")));

        JLabel bgImage = new JLabel();

        bgImage.setBounds(0, 0, 1100, 800);
        bgImage.setIcon(background);
        bgImage.add(fromATextFileButton);
        bgImage.add(userDefinedButton);
        bgImage.add(randomButton);
        bgImage.add(musicOnButton);
        bgImage.add(musicOffButton);
        bgImage.add(homeButton);

        this.add(bgImage);
    }

    private void setListeners() {
        fromATextFileButton.hover("buttons/fromtext-hover.png", "buttons/fromtext.png");
        userDefinedButton.hover("buttons/user-hover.png", "buttons/user.png");
        randomButton.hover("buttons/random-hover.png", "buttons/random.png");
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
    }

    public boolean processInput(InputPanel inputPanel) {
        String resourcePath = "/resources/text/";
        URL resourceUrl = InputDecisionPanel.class.getResource(resourcePath);

        // Convert the URL to a file object
        assert resourceUrl != null;
        File resourceFile = new File(resourceUrl.getPath());
        JFileChooser fileChooser = new JFileChooser(resourceFile);
        fileChooser.setDialogTitle("Select text file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                // Read the input file and store the values in a 2-dimensional array
                String inputFileName = fileChooser.getSelectedFile().getPath();

                try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
                    String line;
                    int lineNum = 1;
                    int processNum = 0;
                    int resourcesNum = 0;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) {
                            continue;
                        }
                        if (lineNum == 1) {
                            processNum = Integer.parseInt(line.split(": ")[1]);
                            inputPanel.getProcessNumField().setText(String.valueOf(processNum));
                            lineNum++;
                            continue;
                        } else if (lineNum == 2) {
                            resourcesNum = Integer.parseInt(line.split(": ")[1]);
                            inputPanel.getAvailableResourcesNumField().setText(String.valueOf(resourcesNum));
                            lineNum++;
                            continue;
                        } else if (line.startsWith("[allocation]")) {
                            for (int i = 0; i < processNum; i++) {
                                String[] values = br.readLine().split(",");
                                for (int j = 0; j < resourcesNum; j++) {
                                    inputPanel.getAllocationTableModel().setValueAt(Integer.parseInt(values[j]), i, j);
                                }
                            }
                            lineNum++;
                            continue;
                        } else if (line.startsWith("[max]")) {
                            for (int i = 0; i < processNum; i++) {
                                String[] values = br.readLine().split(",");
                                for (int j = 0; j < resourcesNum; j++) {
                                    inputPanel.getMaxTableModel().setValueAt(Integer.parseInt(values[j]), i, j);
                                }
                            }
                            lineNum++;
                            continue;
                        } else if (line.startsWith("[available]")) {
                            // Read values for available table
                            String[] values = br.readLine().split(",");
                            for (int i = 0; i < resourcesNum; i++) {
                                inputPanel.getAvailableTableModel().setValueAt(Integer.parseInt(values[i]), 0, i);
                            }
                            lineNum++;
                            continue;
                        } else if (line.startsWith("[request]")) {
                            // Read values for request table
                            String[] values = br.readLine().split(",");
                            for (int i = 0; i < resourcesNum; i++) {
                                inputPanel.getRequestResourceTableModel().setValueAt(Integer.parseInt(values[i]), 0, i);
                            }
                            lineNum++;
                            continue;
                        }
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Error reading file. Please make sure you followed the right formatting");
                    return false;
                }
            }
            if (!inputPanel.getRunButton().isEnabled()) {
                JOptionPane.showMessageDialog(null, "Error reading file. Please make sure\nyou followed the right formatting or that alloc < max ");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected");
            return false;
        }
        return true;
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
        InputDecisionPanel m = new InputDecisionPanel();
        Frame frame = new Frame("Input Decision Panel");
        frame.add(m);
        frame.setVisible(true);
    }

    public ImageButton getFromATextFileButton() {
        return fromATextFileButton;
    }

    public ImageButton getUserDefinedButton() {
        return userDefinedButton;
    }

    public ImageButton getRandomButton() {
        return randomButton;
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
