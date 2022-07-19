package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutDialog extends JDialog {
	  public AboutDialog() {
	    setTitle("About");
	    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

	    add(Box.createRigidArea(new Dimension(0, 10)));

	    
	    JLabel label1 = new JLabel();
	    label1.setText("<html><h2>Game instructions</h2></html>");
	    JLabel label2 = new JLabel();
	    label2.setText("<html><h3>THE PACK: </h3>Solitaire uses one 52-card pack.</html>");
	   // label2.setBounds(0, 20, 200, 50);
	    JLabel label3 = new JLabel();
	    label3.setText("<html><h3>OBJECT OF THE GAME: </h3>The object of Solitaire is to move all cards to the foundations in ascending order,"
	    		+ " beginning with the Ace and ending with the King. </html>");
	    //label3.setBounds(0, 40, 200, 50);
	   JLabel label4 = new JLabel();
	   label4.setText("<html><h3>RANK OF CARDS: </h3>The rank of cards in Solitaire "
	   		+ "games is: K (high), Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A (low).</html>");
	   
	   JLabel label5 = new JLabel();
	   label5.setText("<html><h3>Glossary: </h3>Tableau: the main playing area containing seven piles of cards<br>"
	   		+ "Foundations: four piles for four different suits, ranging from an ace to a king of a particular suit<br>\r\n"
	   		+ "Stock: remaining cards that are not used during the creation of the tableau<br>\r\n"
	   		+ "Talon: Contains stock cards that cannot be used to make legal moves</html>");
	   
	   JLabel label6 = new JLabel();
	   label6.setText("<html><h3>How to play: </h3><p>The initial tableau can be rebuilt by transferring cards among the face up cards"
	   		+ " in the tableau. When the tableau cards cannot be played at once, other blocking cards can be removed. For example, "
	   		+ "of the seven face up cards in the tableau, if one is a nine and the other is a ten, you can transfer the nine on top of the ten "
	   		+ "to start building that pile in order. Since you have moved the nine from one of the seven piles,"
	   		+ " you have now unlocked a face down card; this card can be turned over and is now in play.</p>"
	   		+ "<p>If you uncover an ace when you transfer cards to the tableau, the ace must be placed in one"
	   		+ " of the foundation piles, it will be the basis for building the foundations by suit and in order from ace to king</p>"
	   		+ "<p>Continue to transfer cards and if you can't move any more cards face up, you can use the supply by turning over the top card "
	   		+ "and playing it into the foundations or tableau. If the card has no place in the tableau or the foundation piles,"
	   		+ " it can be moved to the waste pile and turn over another card in the reserve pile.</p>"
	   		+ "<p>If a vacancy called \"space\" in the tableau is created by the removal of cards elsewhere, it can only be filled by a king."
	   		+ " Filling a space with a king could potentially unlock one of the face down cards in another tableau pile.</p>"
	   		+ "<p>Keep transferring cards into the tableau and bringing cards from the reserve pile into play "
	   		+ "until all cards are built in color sequences into the base piles to win!</p></html>");
	   JLabel label7 = new JLabel();
	   label7.setText("<html><h6>https://bicyclecards.com/how-to-play/solitaire/</h6></html>");
	    add(label1);
	    add(label2);
	    add(label3);
	    add(label4);
	    add(label5);
	    add(label6);
	    add(label7);

	    add(Box.createRigidArea(new Dimension(0, 10)));

	    JButton close = new JButton("Close");
	    close.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        dispose();
	      }
	    });

	    close.setAlignmentX(0.1f);
	    add(close);
	    setModalityType(ModalityType.APPLICATION_MODAL);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setSize(900, 1200);
	  }
}
