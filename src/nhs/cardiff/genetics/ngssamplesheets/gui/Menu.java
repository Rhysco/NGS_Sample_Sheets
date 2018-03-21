package nhs.cardiff.genetics.ngssamplesheets.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * @author Rhys Cooper
 * @Date 09/08/2017
 * @version 1.2
 * 
 */
public class Menu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * CONSTRUCTOR
	 * @param frame 
	 * 
	 * I5_Index_ID	index2
	 * E501			TAGATCGC
	 * E502			CTCTCTAT 
	 * E504			AGAGTAGA
	 * E505			GTAAGGAG
	 * 
	 */
	public Menu(JFrame frame){
		
		// WORKSHEET SUBMENU
		JMenu wsNo = new JMenu("Worksheets");
		JMenuItem ws1 = new JMenuItem("1 Worksheet");
		JMenuItem ws2 = new JMenuItem("2 Worksheet");
		JMenuItem ws3 = new JMenuItem("3 Worksheet");
		wsNo.add(ws1);
		wsNo.add(ws2);
		wsNo.add(ws3);
	
		// INDEX SUBMENU
		JMenu indexSelect = new JMenu("Index's");
		JMenuItem index1 = new JMenuItem("E501");
		JMenuItem index2 = new JMenuItem("E502");
		JMenuItem index3 = new JMenuItem("E504");
		JMenuItem index4 = new JMenuItem("E505");
		indexSelect.add(index1);
		indexSelect.add(index2);
		indexSelect.add(index3);
		indexSelect.add(index4);
		
		// MAIN MENU ITEM
		JMenu options = new JMenu("Options");
		options.add(wsNo);
		options.add(indexSelect);
		
		// MAIN MENU BAR
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(options);
		frame.setJMenuBar(menuBar);	
		
		// Action listeners for menu options
		index1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GuiInterface gi = new Gui();
					gi.infoUpdate("E501");
				} catch (Exception localException) {
				// DO STUFF	
				}
			}

		});	
	}
}
