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
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 806, 402);
		//scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JLabel lblNewLabel = new JLabel("Menus and How to Use Them:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Movie Menu:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JTextArea txtrMovieMenuHas = new JTextArea();
		txtrMovieMenuHas.setBackground(UIManager.getColor("Button.background"));
		txtrMovieMenuHas.setText("Movie Menu has two tabs:\r\n\t\t\r\n  - Info: Contains the options to:\r\n\r\n\t• Add a Movie\t\t- Adds a Movie to the Database\r\n\t• Modify a Movie\t- Modifies a Movie in the Database\r\n\t• Display a Movie\t- Display a window with a designated Movies info\r\n\t• Show all Movies\t- Displays a table with all of the Movies in the Database\r\n\t• Exit\t\t- Exits the program\r\n\r\n  - Help: Contains options to:\r\n\r\n\t• Help\t\t- Displays a Help Window\r\n\t• About\t\t- About the people who designed this program");
		txtrMovieMenuHas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrMovieMenuHas.setLineWrap(true);
		
		JLabel movieMenuImage = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(215)
							.addComponent(movieMenuImage, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtrMovieMenuHas, GroupLayout.PREFERRED_SIZE, 745, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtrMovieMenuHas, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(movieMenuImage, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
	}
}
