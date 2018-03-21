/**
 * 
 */
package nhs.cardiff.genetics.ngssamplesheets.gui;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Rhys Cooper
 * @Date 17/02/2017
 * @version 1.0.1
 * 
 */
public class Main {

	/**
	 * @param args None
	 */
	public static void main(String[] args) throws InterruptedException,
			MessagingException {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("NGS Sample Sheet Generator - Version 1.0.1");
				frame.setSize(400, 200);
				frame.setDefaultCloseOperation(3);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
			}
		});
	}
}
