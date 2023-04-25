package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

public class HowPanel extends Panel{

    private ImageButton musicOnButton, musicOffButton, homeButton, defButton, termsButton,
            choicesButton, backButton, formatButton, inputButton, outputButton;
    public HowPanel() {

        super("bg/how-1.png");

        musicOnButton = new ImageButton("buttons/volume-on.png");
        musicOffButton = new ImageButton("buttons/volume-off.png");
        homeButton = new ImageButton("buttons/home.png");
        defButton = new ImageButton("buttons/definition.png");
        termsButton = new ImageButton("buttons/terms.png");
        choicesButton = new ImageButton("buttons/choices.png");
        formatButton = new ImageButton("buttons/format.png");
        backButton = new ImageButton("buttons/back.png");
        inputButton = new ImageButton("buttons/input.png");
        outputButton = new ImageButton("buttons/output.png");

        musicOnButton.setBounds(976, 25, 47, 47);
        musicOffButton.setBounds(976, 25, 47, 47);
        homeButton.setBounds(1033, 25, 47, 47);
        defButton.setBounds(55, 180, 455, 47);
        termsButton.setBounds(525, 180, 156, 47);
        formatButton.setBounds(912, 385, 121, 40);
        backButton.setBounds(59, 720, 47, 47);
        inputButton.setBounds(242,118, 216, 47);
        outputButton.setBounds(465,118, 216, 47);

        musicOffButton.setVisible(false);
        backButton.setVisible(false);
        choicesButton.setVisible(false);


        setListeners();

        this.add(musicOnButton);
        this.add(musicOffButton);
        this.add(homeButton);
        this.add(defButton);
        this.add(termsButton);
        this.add(formatButton);
        this.add(backButton);
        this.add(choicesButton);
        this.add(inputButton);
        this.add(outputButton);
    }

    private void setListeners() {
        musicOnButton.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        musicOffButton.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        homeButton.hover("buttons/home-hover.png", "buttons/home.png");
        defButton.hover("buttons/definition-hover.png", "buttons/definition.png");
        termsButton.hover("buttons/terms-hover.png", "buttons/terms.png");
        formatButton.hover("buttons/format-hover.png", "buttons/format.png");
        backButton.hover("buttons/back-hover.png", "buttons/back.png");
        choicesButton.hover("buttons/choices-hover.png", "buttons/choices.png");
        inputButton.hover("buttons/input-hover.png", "buttons/input.png");
        outputButton.hover("buttons/output-hover.png", "buttons/output.png");
        listenToButtonClicks();
    }

    private void listenToButtonClicks() {
        defButton.addActionListener(e -> {
            setImage("bg/how-3.png");
            backButton.setVisible(false);
            termsButton.setVisible(true);
            termsButton.setBounds(55, 179, 156, 47);
            choicesButton.setVisible(true);
            choicesButton.setBounds(222, 179, 156, 47);
            defButton.setVisible(false);
            formatButton.setVisible(false);
            inputButton.setVisible(true);
            outputButton.setVisible(true);
            inputButton.setBounds(242,118, 216, 47);
            outputButton.setBounds(465,118, 216, 47);
        });
        termsButton.addActionListener(e -> {
            setImage("bg/how-2.png");
            backButton.setVisible(false);
            defButton.setVisible(true);
            defButton.setBounds(55, 179, 455, 47);
            choicesButton.setVisible(true);
            choicesButton.setBounds(525, 179, 156, 47);
            termsButton.setVisible(false);
            formatButton.setVisible(false);
            inputButton.setVisible(true);
            outputButton.setVisible(true);
            inputButton.setBounds(242,118, 216, 47);
            outputButton.setBounds(465,118, 216, 47);
        });
        formatButton.addActionListener(e -> {
            setImage("bg/how-1-1.png");
            defButton.setVisible(true);
            defButton.setBounds(55, 179, 455, 47);
            termsButton.setVisible(true);
            termsButton.setBounds(525, 179, 156, 47);
            backButton.setVisible(true);
            choicesButton.setVisible(false);
            formatButton.setVisible(false);
            inputButton.setVisible(true);
            outputButton.setVisible(true);
            inputButton.setBounds(242,118, 216, 47);
            outputButton.setBounds(465,118, 216, 47);
        });
        backButton.addActionListener(e -> {
            setImage("bg/how-1.png");
            defButton.setVisible(true);
            termsButton.setVisible(true);
            defButton.setBounds(55, 180, 455, 47);
            termsButton.setBounds(525, 180, 156, 47);
            backButton.setVisible(false);
            formatButton.setVisible(true);
            formatButton.setBounds(912, 385, 121, 40);
            inputButton.setVisible(true);
            outputButton.setVisible(true);
            choicesButton.setVisible(false);
            inputButton.setBounds(242,118, 216, 47);
            outputButton.setBounds(465,118, 216, 47);
        });
        choicesButton.addActionListener(e -> {
            setImage("bg/how-1.png");
            defButton.setVisible(true);
            termsButton.setVisible(true);
            defButton.setBounds(55, 180, 455, 47);
            termsButton.setBounds(525, 180, 156, 47);
            backButton.setVisible(false);
            formatButton.setVisible(true);
            formatButton.setBounds(912, 385, 121, 40);
            inputButton.setVisible(true);
            outputButton.setVisible(true);
            choicesButton.setVisible(false);
            inputButton.setBounds(242,118, 216, 47);
            outputButton.setBounds(465,118, 216, 47);
        });
        inputButton.addActionListener(e -> {
            setImage("bg/how-4.png");
            defButton.setVisible(true);
            termsButton.setVisible(true);
            defButton.setBounds(55, 180, 455, 47);
            termsButton.setBounds(525, 180, 156, 47);
            backButton.setVisible(false);
            formatButton.setVisible(false);
            outputButton.setVisible(true);
            choicesButton.setVisible(true);
            choicesButton.setBounds(300, 118,156, 47);
            inputButton.setVisible(false);
        });
        outputButton.addActionListener(e -> {
            setImage("bg/how-5.png");
            defButton.setVisible(true);
            termsButton.setVisible(true);
            defButton.setBounds(55, 180, 455, 47);
            termsButton.setBounds(525, 180, 156, 47);
            backButton.setVisible(false);
            formatButton.setVisible(false);
            inputButton.setVisible(true);
            inputButton.setBounds(465,118, 216, 47);
            choicesButton.setVisible(true);
            choicesButton.setBounds(300, 118,156, 47);
            outputButton.setVisible(false);
        });
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
        HowPanel m = new HowPanel();
        Frame frame = new Frame("How Panel");
        frame.add(m);
        frame.setVisible(true);
    }


    public ImageButton getHomeButton() {
        return homeButton;
    }
    public ImageButton getMusicOnButton() {
        return musicOnButton;
    }
    public ImageButton getMusicOffButton() {
        return musicOffButton;
    }

}
