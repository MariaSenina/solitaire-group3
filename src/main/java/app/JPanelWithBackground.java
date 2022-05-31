package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;
    private GUI gui;

    public JPanelWithBackground(String fileName, GUI gui) throws IOException {
        this.gui = gui;
        URL urlToImage = gui.getClass().getResource(fileName);
        backgroundImage = ImageIO.read(urlToImage);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(backgroundImage, 0, 0, gui);
    }
}
