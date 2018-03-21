/**
 * 
 */
package nhs.cardiff.genetics.ngssamplesheets.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nhs.cardiff.genetics.ngssamplesheets.ImportWorksheet;

/**
 * @author Rhys Cooper
 * @Date 17/02/2017
 * @version 1.1.1
 * 
 */
public class Gui extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 */
	public Gui() {
		Dimension size = getPreferredSize();
		size.width = 200;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder(""));
		JLabel worksheetLabel = new JLabel(
				"Enter your NGS worksheet number then click go or press enter");
		final JTextField inputField = new JTextField(8);
		final JTextField inputFieldTwo = new JTextField(8);
		JButton worksheetButton = new JButton("Go");
		final JLabel infoField = new JLabel(
				"Non shire worksheets and non-NGS worksheets will be rejected");

		worksheetButton.setVisible(true);
		inputField.setVisible(true);
		inputFieldTwo.setVisible(true);
		inputField.setToolTipText("All NGS Worksheets");
		inputFieldTwo.setToolTipText("Haloplex Wotksheets only");
		infoField.setVisible(false);

		inputField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					infoField.setText("");
					String input = inputField.getText();
					ImportWorksheet worksheet = new ImportWorksheet();
					try {
						worksheet.imports(input, infoField, inputField);
					} catch (Exception localException) {
					}
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}
		});
		
		worksheetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoField.setText("");
				String input = inputField.getText();
				ImportWorksheet worksheet = new ImportWorksheet();
				try {
					worksheet.imports(input, infoField, inputField);

				} catch (Exception localException) {
				}
			}

		});	
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = 10;
		gbc.weightx = 0.5D;
		gbc.weighty = 0.5D;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(worksheetLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(inputField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(worksheetButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(infoField, gbc);
	}
}
