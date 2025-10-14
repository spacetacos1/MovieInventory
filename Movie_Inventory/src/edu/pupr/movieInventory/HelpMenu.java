package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

/**
 * <b><code>Program: HelpMenu.java</b></code><br>
 * <b><code>Description: Handles the Help menu</b></code><br>
 * <b><code>Date: 10/14/2025</b></code>
 */
public class HelpMenu extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpMenu frame = new HelpMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelpMenu() {
		setResizable(false);
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 670, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 636, 489);
		scrollPane.setVerticalScrollBarPolicy(21);
		scrollPane.setHorizontalScrollBarPolicy(31);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JLabel lblNewLabel = new JLabel("Help Menu:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JTextPane txtpnMovieMenuHas = new JTextPane();
		txtpnMovieMenuHas.setEditable(false);
		txtpnMovieMenuHas.setBackground(UIManager.getColor("Button.background"));
		txtpnMovieMenuHas.setText("Movie Menu has two tabs:\r\n\t\t\r\n  - Info: Contains the options to:\r\n\r\n    "
				+ "• Add a Movie\t- Adds a Movie to the Database\r\n                              "
				+ "\t*No two movies can have the same Title, Director, nor Year of release!\r\n\r\n    "
				+ "• Modify a Movie\t- Modifies a Movie in the Database\r\n                                   "
				+ "\t*Must search for a movie before modifying anything!\r\n\r\n    "
				+ "• Display a Movie\t- Display a window with a designated Movies info\r\n\r\n    "
				+ "• Show all Movies\t- Displays a table with all of the Movies in the Database\r\n                                        "
				+ "*Note: Show all movies does not have a close window button\r\n                                                    "
				+ "Please use the X on the top right of the window.\r\n\r\n    "
				+ "• Exit\t\t- Exits the program\r\n\r\n  - Help: Contains options to:\r\n\r\n    "
				+ "• Help\t\t- Displays a Help Window\r\n\r\n    "
				+ "• About\t\t- About the people who designed this program\r\n\r\n*Note: All Budgets, Days, and Years MUST be placed in Digits, no commas. All movies must have a poster!");
		txtpnMovieMenuHas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnMovieMenuHas, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtpnMovieMenuHas, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
