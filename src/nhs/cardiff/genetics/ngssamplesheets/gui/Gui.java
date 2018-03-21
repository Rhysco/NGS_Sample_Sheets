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
 * @version 1.2
 * 
 */
public class Gui extends JPanel implements GuiInterface {
	private static final long serialVersionUID = 1L;
	private JLabel infoField;
	private JLabel worksheetLabel;

	/**
	 * You don't have to put all 30 labels and text fields in one class.
	 *  Break your GUI up into many JPanels
	 *  and create a class that uses (not extends) each JPanel
	 *  
	 *  Separate this out to MVC
	 *  
	 *  Make an interface of this GUI - make a change infofield method
	 *  have menu implement the interface to be able to change it.
	 *  
	 *  
	 */
	public Gui() {
		Dimension size = getPreferredSize();
		size.width = 200;
		setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.5D;
		gbc.weighty = 0.5D;
	
		worksheetLabel = new JLabel("Enter your NGS worksheet number then click go or press enter");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(worksheetLabel, gbc);
		
		final JTextField inputField = new JTextField(8);
		inputField.setVisible(true);
		inputField.setToolTipText("All NGS Worksheets");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(inputField, gbc);

		JButton worksheetButton = new JButton("Go");
		worksheetButton.setVisible(true);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(worksheetButton, gbc);

		infoField = new JLabel("Non shire worksheets and non-NGS worksheets will be rejected");
		infoField.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(infoField, gbc);
		
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
	}
	
	/* (non-Javadoc)
	 * @see nhs.cardiff.genetics.ngssamplesheets.gui.GuiInterface#infoUpdate(java.lang.String)
	 */
	@Override
	public void infoUpdate(String update){
		worksheetLabel.setText(update);
		worksheetLabel.repaint();
	}	
}
