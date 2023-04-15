package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MenuPanel extends Panel{


    private Panel menu;
    private ImageButton getStartedButton;
    private ImageButton howItWorksButton;
    private ImageButton exitButton;
    private ImageButton musicButton;
    private ImageButton infoButton;
    private Panel infoPanel;

    public MenuPanel(){
        super("bg/menu.png");

        getStartedButton = new ImageButton("button/get-started.png");
        howItWorksButton = new ImageButton("button/how.png");
        exitButton = new ImageButton("button/quit.png");
        musicButton = new ImageButton("button/music-on.png");
        infoButton = new ImageButton("button/info.png");
        infoPanel = new Panel("bg/info-hover-label.png");

        getStartedButton.setBounds(57, 400, 373, 76);
        howItWorksButton.setBounds(57, 510, 373, 76);
        exitButton.setBounds(57, 620, 373, 76);
        musicButton.setBounds(945, 40, 47, 47);
        infoButton.setBounds(1010, 40, 47, 47);
        infoPanel.setBounds(716, 66, 320, 141);
        infoPanel.setVisible(false);

        setListeners();

        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/bg/menu.gif")));

        JLabel bgImage = new JLabel();

        bgImage.setBounds(0, 0, 1100, 800);
        bgImage.setIcon(background);
        bgImage.add(getStartedButton);
        bgImage.add(howItWorksButton);
        bgImage.add(exitButton);
        bgImage.add(musicButton);
        bgImage.add(infoButton);
        bgImage.add(infoPanel);

        this.add(bgImage);

    }

    private void setListeners(){
        getStartedButton.hover("button/get-started-hover.png", "button/get-started.png");
        howItWorksButton.hover("button/how-hover.png", "button/how.png");
        exitButton.hover("button/quit-hover.png", "button/quit.png");
        infoButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { infoPanel.setVisible(true); }
            public void mouseExited(MouseEvent e) { infoPanel.setVisible(false); }
        });
        musicButton.hover("button/music-off-hover.png", "button/music-on.png");
    }

    public static void main(String[] args) {
        MenuPanel m = new MenuPanel();
        Frame frame = new Frame("Menu Panel");
        frame.add(m);
        frame.setVisible(true);
    }

    public Panel getMenu() {
        return menu;
    }

    public ImageButton getGetStartedButton() {
        return getStartedButton;
    }

    public ImageButton getHowItWorksButton() {
        return howItWorksButton;
    }

    public ImageButton getExitButton() {
        return exitButton;
    }

    public ImageButton getInfoButton() {
        return infoButton;
    }
}
