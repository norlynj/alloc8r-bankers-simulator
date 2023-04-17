package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

public class OutputPanel extends Panel{
    private ImageButton musicButton;
    private ImageButton homeButton;
    public OutputPanel() {
        super("bg/input-panel.png");

        musicButton = new ImageButton("buttons/volume-on.png");
        homeButton = new ImageButton("buttons/home.png");

        musicButton.setBounds(945, 40, 47, 47);
        homeButton.setBounds(1010, 40, 47, 47);

        setListeners();

        this.add(musicButton);
        this.add(homeButton);
    }

    private void setListeners() {
        musicButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
    }

    public static void main(String[] args) {
        OutputPanel m = new OutputPanel();
        Frame frame = new Frame("Output Panel");
        frame.add(m);
        frame.setVisible(true);
    }

}

