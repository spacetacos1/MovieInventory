package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <b><code>Program: ShowMovie.java</b></code><br>
 * <b><code>Description: Handles displaying a movie</b></code><br>
 * <b><code>Date: 10/14/2025</b></code>
 */
public class ShowMovie extends JFrame {

	private JPanel contentPane;
	private JLabel posterLabel;
	private JTextArea plotArea;
	private JTextField titleTextField;
	private JTextField directorTextField;
	private JTextField ratingTextField;
	private JTextField budgetTextField;
	private JTextField releaseDateTextField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowMovie frame = new ShowMovie();
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
	public ShowMovie() {
		setTitle("Show a Movie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		posterLabel = new JLabel("");
		posterLabel.setBounds(10, 135, 283, 361);
		contentPane.add(posterLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setBounds(29, 30, 49, 14);
		contentPane.add(titleLabel);
		
		titleTextField = new JTextField();
		titleTextField.setEditable(false);
		titleTextField.setBounds(81, 27, 642, 20);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(29, 68, 59, 14);
		contentPane.add(directorLabel);
		
		directorTextField = new JTextField();
		directorTextField.setEditable(false);
		directorTextField.setBounds(81, 65, 642, 20);
		contentPane.add(directorTextField);
		directorTextField.setColumns(10);
		
		JLabel ratingLabel = new JLabel("Rating:");
		ratingLabel.setBounds(363, 110, 49, 14);
		contentPane.add(ratingLabel);
		
		ratingTextField = new JTextField();
		ratingTextField.setEditable(false);
		ratingTextField.setBounds(415, 107, 83, 20);
		contentPane.add(ratingTextField);
		ratingTextField.setColumns(10);
		
		JLabel posterTextLabel = new JLabel("Poster:");
		posterTextLabel.setBounds(29, 110, 49, 14);
		contentPane.add(posterTextLabel);
		
		JLabel budgetLabel = new JLabel("Budget:");
		budgetLabel.setBounds(536, 104, 49, 14);
		contentPane.add(budgetLabel);
		
		plotArea = new JTextArea();
		plotArea.setEditable(false);
		plotArea.setWrapStyleWord(true);
		plotArea.setLineWrap(true);
		plotArea.setBounds(341, 233, 363, 263);
		contentPane.add(plotArea);
		
		JLabel plotLabel = new JLabel("Plot:");
		plotLabel.setBounds(331, 208, 49, 14);
		contentPane.add(plotLabel);
		
		budgetTextField = new JTextField();
		budgetTextField.setEditable(false);
		budgetTextField.setBounds(588, 104, 96, 20);
		contentPane.add(budgetTextField);
		budgetTextField.setColumns(10);
		
		JLabel dateLabel = new JLabel("Release Date:");
		dateLabel.setBounds(321, 162, 122, 14);
		contentPane.add(dateLabel);
		
		releaseDateTextField = new JTextField();
		releaseDateTextField.setEditable(false);
		releaseDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		releaseDateTextField.setBounds(415, 159, 163, 20);
		contentPane.add(releaseDateTextField);
		releaseDateTextField.setColumns(10);
		
		btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		btnNewButton.setBounds(595, 158, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	/**
	 * Fills all the fields with the information of the selected movie
	 * @param title	- Selected movie title
	 * @param director - Selected movie director
	 * @param year - Selected movie release year
	 */
	public void setFields(String title, String director, String year) {
		
		MovieQueries movieQueries = new MovieQueries();
		List<Movie> list = movieQueries.getMovieByTitleDirectorYear(title, director, year);
		Movie movie = (Movie)list.get(0);
		
		titleTextField.setText(movie.getTitle().toString());
		directorTextField.setText(movie.getDirector());
		plotArea.setText(movie.getPlot());
		
		Integer month = movie.getReleaseDate().getMonthValue();
		Integer day = movie.getReleaseDate().getDayOfMonth();
		
		releaseDateTextField.setText(getMonth(month) + day.toString() + ", " + year);
		
		budgetTextField.setText(String.format("%,.2f", movie.getBudget()));
		ratingTextField.setText(movie.getRating());
		
		FileManager manager = new FileManager();
		posterLabel.setIcon(manager.getFileImage(title,posterLabel));	//Using manager, gets the image for the poster section
	}

	
	private String getMonth(Integer month) {

		String[] months = {"January ", "February ", "March ", "April ", "May ", "June ", "July ", "August ", "September ", "October ", "November ", "December "};

		return months[month-1];
	}
	
}
