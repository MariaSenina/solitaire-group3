package app.listeners;

import app.GUI;
import app.AboutDialog;
import app.FeedbackLink;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiActionListener implements ActionListener {
    private GUI gui;

    public GuiActionListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JMenuItem) {
            handleMenuInteraction(e);
        }
    }

    private void handleMenuInteraction(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();

       // if(item.getText().equals(gui.getMenuOptions().get("Exit"))) {
       //     gui.dispose(); }
        if (item.getText().equals(gui.getMenuOptions().get("New"))) {
            gui.reset();
        }
        
        if (item.getText().equals(gui.getMenuOptions().get("Vegas"))) {
        	gui.resetVegas();
        }
        
        if (item.getText().equals(gui.getMenuOptions().get("About"))) {
        	//if(event.getSource()==about) {
        		//JOptionPane.showMessageDialog(about, "Game instruction");
        	//}
        	AboutDialog ad = new AboutDialog();
        	ad.setVisible(true);
		}
        
        if (item.getText().equals(gui.getMenuOptions().get("FB"))) {
        	SwingUtilities.invokeLater(new Runnable() {
       		 
                @Override
                public void run() {
                    new FeedbackLink().setVisible(true);;
                }
            });;
        }
    }
}
