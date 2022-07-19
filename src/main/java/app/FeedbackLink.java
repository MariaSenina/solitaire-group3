package app;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeedbackLink extends JFrame {
    private String text = "Leave us you feedback here, Thank you";
    private JLabel hyperlink = new JLabel(text);
 
    public FeedbackLink() throws HeadlessException {
        super();
        setTitle("Link to Website");
 
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        hyperlink.addMouseListener(new MouseAdapter() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://solitaire-game.herokuapp.com/feedback.html"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                hyperlink.setText(text);
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
                hyperlink.setText("<html><a href=''>" + text + "</a></html>");
            }
 
        });
 
        setLayout(new FlowLayout());
        getContentPane().add(hyperlink);
 
 
        setSize(500, 150);
        add(Box.createRigidArea(new Dimension(0, 60)));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
