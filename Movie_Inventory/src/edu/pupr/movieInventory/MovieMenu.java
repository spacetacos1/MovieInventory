package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MovieMenu extends JFrame {

	private JPanel contentPane;
	private JMenuItem addMovieMenu;
	private JMenuItem modifyMovieMenu;
	private JMenuItem displayMovie;
	private JMenuItem showMoviesMenu;
	private JMenuItem exitMenu;
	private JMenuItem contentsMenu;
	private JMenuItem aboutMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieMenu frame = new MovieMenu();
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
	public MovieMenu() {
		setResizable(false);
		setTitle("Movie Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 248);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu infoMenu = new JMenu("Info");
		menuBar.add(infoMenu);
		
		addMovieMenu = new JMenuItem("Add a Movie");
		addMovieMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				AddMovie addMovie = new AddMovie();
				
				addMovie.setVisible(true);			}
		});
		infoMenu.add(addMovieMenu);
		
		modifyMovieMenu = new JMenuItem("Modify a Movie");
		modifyMovieMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				ModifyMovie modifyMovie = new ModifyMovie();
				if(modifyMovie.askMovie()) {
					modifyMovie.setFields();
					modifyMovie.setVisible(true);
				}else
					modifyMovie.dispose();
				
				
			}
		});
		infoMenu.add(modifyMovieMenu);
		
		displayMovie = new JMenuItem("Display a Movie");
		displayMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				JTextField askTitleTextField = new JTextField();
				JTextField askDirectorTextField = new JTextField();
				JTextField askReleaseYearTextField = new JTextField();
				
				Object[] fields = {
					"Title: ", askTitleTextField,
					"Director: ", askDirectorTextField,
					"Release Year: ", askReleaseYearTextField
				};


				MovieQueries movieQueries = new MovieQueries();
			 
				int selections = JOptionPane.showConfirmDialog(null, fields, "Enter Movie Details", JOptionPane.OK_CANCEL_OPTION);
				if(selections == JOptionPane.OK_OPTION) {
					try {
						ShowMovie movie = new ShowMovie();
						movie.setFields(askTitleTextField.getText(), askDirectorTextField.getText(), askReleaseYearTextField.getText());
						movie.setVisible(true);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid Year Input!");
					}catch (IndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null, "That Movie was not found!");
					}
				}
			}
		});
		infoMenu.add(displayMovie);
		
		showMoviesMenu = new JMenuItem("Show all Movies");
		showMoviesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				AllMovies allMovies = new AllMovies();
				allMovies.setVisible(true);
			}
		});
		infoMenu.add(showMoviesMenu);
		
		exitMenu = new JMenuItem("Exit");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		infoMenu.add(exitMenu);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		contentsMenu = new JMenuItem("Help Contents");
		helpMenu.add(contentsMenu);
		
		aboutMenu = new JMenuItem("About");
		helpMenu.add(aboutMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 426, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 231, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
