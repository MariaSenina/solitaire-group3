package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;
    private GUI gui;

    public JPanelWithBackground(String fileName, GUI gui) throws IOException {
    	String filepath = System.getProperty("filepath");
        String path = filepath + "\\";
    	
        this.gui = gui;
        File imageFile = new File(path + fileName);
//        URL imageFile = gui.getClass().getResource(fileName);
        
        backgroundImage = ImageIO.read(imageFile);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(backgroundImage, 0, 0, gui);
    }
}
