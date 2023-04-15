package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

public class HowPanel extends Panel{

    private ImageButton musicButton, homeButton, defButton, termsButton, backButton;
    public HowPanel() {

        super("bg/how.png");

        musicButton = new ImageButton("buttons/volume-on.png");
        homeButton = new ImageButton("buttons/home.png");
        defButton = new ImageButton("button/definition.png");
        termsButton = new ImageButton("buttons/terms.png");
        backButton = new ImageButton("buttons/back.png");

        musicButton.setBounds(945, 40, 47, 47);
        homeButton.setBounds(1010, 40, 47, 47);
        defButton.setBounds(187, 666, 353, 47);
        termsButton.setBounds(572, 666, 256, 47);
        backButton.setBounds(282, 112, 47, 47);

        backButton.setVisible(false);
        setListeners();

        this.add(musicButton);
        this.add(homeButton);
        this.add(defButton);
        this.add(termsButton);
        this.add(backButton);
    }

    private void setListeners() {
        musicButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        defButton.hover("buttons/definition-hover.png", "buttons/definition.png");
        termsButton.hover("buttons/terms-hover.png", "buttons/terms.png");
        backButton.hover("buttons/back-hover.png", "buttons/back.png");
        listenToButtonClicks();
    }

    private void listenToButtonClicks() {
        defButton.addActionListener(e -> {
            setImage("bg/how-1.png");
            backButton.setVisible(true);
            termsButton.setVisible(true);
            termsButton.setBounds(422, 635, 256, 47);
            defButton.setVisible(false);
        });
        termsButton.addActionListener(e -> {
            setImage("bg/how-2.png");
            backButton.setVisible(true);
            defButton.setVisible(true);
            defButton.setBounds(373, 680, 353, 47);
            termsButton.setVisible(false);
        });
        backButton.addActionListener(e -> {
            setImage("bg/how.png");
            defButton.setVisible(true);
            termsButton.setVisible(true);
            defButton.setBounds(187, 666, 353, 47);
            termsButton.setBounds(572, 666, 256, 47);
            backButton.setVisible(false);
        });
    }

    public static void main(String[] args) {
        HowPanel m = new HowPanel();
        Frame frame = new Frame("How Panel");
        frame.add(m);
        frame.setVisible(true);
    }

    public ImageButton getMusicButton() {
        return musicButton;
    }

    public ImageButton getHomeButton() {
        return homeButton;
    }
}
