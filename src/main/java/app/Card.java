package app;

import app.enums.Suit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Card extends JPanel {
    private int value;
    private Suit suit;
    private BufferedImage frontImage;
    private BufferedImage backImage;
    private boolean isReversed;
    private Point positionOffset;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
        isReversed = false;
        
        String filepath = System.getProperty("filepath");
        String path = filepath + "\\";

        try {
        	File imageFile;
        	
        	String foregroundImgPath = path + this + ".png";
            imageFile = new File(foregroundImgPath);
        	frontImage = ImageIO.read(imageFile);
            
            String backgroundImgPath = path + "back.png";
            imageFile = new File(backgroundImgPath);
            backImage = ImageIO.read(imageFile);
        	
//        	URL url = getClass().getResource("/cards/" + this + ".png");
//            frontImage = ImageIO.read(url);
//
//            url = getClass().getResource("/cards/back.png");
//            backImage = ImageIO.read(url);

            setBounds(0, 0, frontImage.getWidth(), frontImage.getHeight());
        } catch(IOException e) {
            e.printStackTrace();
        }

        positionOffset = new Point(0,0);
        setSize(new Dimension(100, 145));
        setOpaque(false);
    }

    public static String valueString(int value) {

        if(value == 12) return "J";
        if(value == 13) return "Q";
        if(value == 14) return "K";
        if(value == 1) return "A";

        // Value between 2 and 10
        return Integer.toString(value);
    }

    public String toString() {
        return valueString(value) + " of " + suit.name();
    }

    public String saveAsString() {
        return valueString(value) + " of " + suit.name() + " of " + isReversed;
    }

    public void hide() {
        isReversed = true;
    }

    public void show() {
        isReversed = false;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        BufferedImage img = frontImage;
        if(isReversed) img = backImage;

        graphics.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean reversed) {
        isReversed = reversed;
    }
}
