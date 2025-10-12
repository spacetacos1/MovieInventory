package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.pupr.movieInventory.MovieQueries;
import edu.pupr.movieInventory.Movie;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class ModifyMovie extends JFrame {

	private JPanel contentPane;
	private JTextField directorTextField;
	private JTextField titleTextField;
	private JButton askMovieButton;
	private JButton updateButton;
	private JButton closeButton;
	private JLabel ratingLabel;
	private JComboBox ratingComboBox;
	private JLabel dateLabel;
	private JLabel dayLabel;
	private JTextField dayTextField;
	private JLabel monthLabel;
	private JComboBox monthComboBox;
	private JLabel yearLabel;
	private JTextField yearTextField;
	private JTextArea plotTextArea;
	private JLabel budgetLabel;
	private JTextField budgetTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyMovie frame = new ModifyMovie();
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
	public ModifyMovie() {
		setTitle("Modify Movie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel idLabel = new JLabel("Title:");
		idLabel.setBounds(23, 27, 63, 14);
		
		JLabel fNameLabel = new JLabel("Director:");
		fNameLabel.setBounds(23, 65, 63, 14);
		
		JLabel lNameLabel = new JLabel("Plot:");
		lNameLabel.setBounds(23, 164, 49, 14);
		
		titleTextField = new JTextField();
		titleTextField.setEditable(false);
		titleTextField.setBounds(90, 24, 434, 20);
		titleTextField.setColumns(10);
		
		directorTextField = new JTextField();
		directorTextField.setBounds(90, 62, 273, 20);
		directorTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setBounds(405, 253, 119, 32);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MovieQueries movieQueries = new MovieQueries();
				String title = titleTextField.getText();
				String director = directorTextField.getText();
				LocalDate releaseDate = LocalDate.of(Integer.parseInt(yearTextField.getText()),monthComboBox.getSelectedIndex() + 1,Integer.parseInt(dayTextField.getText()));
				String plot = plotTextArea.getText();
				String rating = getRating(ratingComboBox.getSelectedIndex());
				double budget = Double.parseDouble(budgetTextField.getText());
				
				int result = movieQueries.updateMovie(title, director, plot,rating, budget, releaseDate);
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Successfully Updated Record!");
				} else {
					JOptionPane.showMessageDialog(null, "Unsuccessfully Updated Record!");
				}
			}
		});
	
		
		closeButton = new JButton("Close");
		closeButton.setBounds(405, 291, 119, 32);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		
		askMovieButton = new JButton("Search Movie");
		askMovieButton.setBounds(405, 213, 119, 32);
		askMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			
				JTextField askTitleTextField = new JTextField();
				JTextField askDirectorTextField = new JTextField();
				JTextField askReleaseYearTextField = new JTextField();
				
				Object[] fields = {
					"Title: ", askTitleTextField,
					"Director: ", askDirectorTextField,
					"Release Year: ", askReleaseYearTextField
				};
				
				int selections = JOptionPane.showConfirmDialog(null, fields, "Enter Movie Details", JOptionPane.OK_CANCEL_OPTION);
				if(selections == JOptionPane.OK_OPTION) {
					try {
					String title = askTitleTextField.getText();
					String director = askDirectorTextField.getText();
					String year = askReleaseYearTextField.getText();
					
					Integer.parseInt(year);
					
				MovieQueries movieQueries = new MovieQueries();
				List<Movie> list = movieQueries.getMovieByTitleDirectorYear(title, director, year);
				Movie movie = (Movie)list.get(0);
				
				titleTextField.setText(movie.getTitle().toString());
				directorTextField.setText(movie.getDirector());
				plotTextArea.setText(movie.getPlot());
				yearTextField.setText(String.valueOf(movie.getReleaseDate().getYear()));
				monthComboBox.setSelectedIndex(movie.getReleaseDate().getMonthValue() - 1);
				dayTextField.setText(String.valueOf(movie.getReleaseDate().getDayOfMonth()));
				budgetTextField.setText(String.valueOf(movie.getBudget()));
				
				ratingComboBox.setSelectedIndex(getRatingIndex(movie.getRating()));

				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid Year Input!");
				}catch (IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "That Movie was not found!");
				}
				}else {
				}
			}  
		});
		contentPane.setLayout(null);
		contentPane.add(fNameLabel);
		contentPane.add(idLabel);
		contentPane.add(titleTextField);
		contentPane.add(directorTextField);
		contentPane.add(lNameLabel);
		contentPane.add(askMovieButton);
		contentPane.add(updateButton);
		contentPane.add(closeButton);
		
		ratingLabel = new JLabel("Rating:");
		ratingLabel.setBounds(23, 101, 49, 14);
		contentPane.add(ratingLabel);
		
		ratingComboBox = new JComboBox();
		ratingComboBox.setModel(new DefaultComboBoxModel(new String[] {"G", "PG", "PG-13", "R", "NC-17"}));
		ratingComboBox.setBounds(90, 97, 82, 22);
		contentPane.add(ratingComboBox);
		
		dateLabel = new JLabel("Release Date:");
		dateLabel.setBounds(372, 84, 119, 14);
		contentPane.add(dateLabel);
		
		dayLabel = new JLabel("Day:");
		dayLabel.setBounds(372, 109, 49, 14);
		contentPane.add(dayLabel);
		
		dayTextField = new JTextField();
		dayTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(dayTextField.getText().length() > 0 && !(updateButton.isSelected())) {
					verifyInput(dayTextField);
					}
			}
		});
		dayTextField.setBounds(423, 106, 101, 20);
		contentPane.add(dayTextField);
		dayTextField.setColumns(10);
		
		monthLabel = new JLabel("Month:");
		monthLabel.setBounds(372, 138, 49, 14);
		contentPane.add(monthLabel);
		
		monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		monthComboBox.setBounds(423, 134, 101, 22);
		contentPane.add(monthComboBox);
		
		yearLabel = new JLabel("Year:");
		yearLabel.setBounds(372, 172, 49, 14);
		contentPane.add(yearLabel);
		
		yearTextField = new JTextField();
		yearTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(yearTextField.getText().length() > 0 && !(updateButton.isSelected())) {
					verifyInput(yearTextField);
					}
			}
		});
		yearTextField.setBounds(423, 169, 101, 20);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
		
		plotTextArea = new JTextArea();
		plotTextArea.setWrapStyleWord(true);
		plotTextArea.setLineWrap(true);
		plotTextArea.setBounds(67, 159, 257, 164);
		contentPane.add(plotTextArea);
		
		budgetLabel = new JLabel("Budget:");
		budgetLabel.setBounds(192, 101, 49, 14);
		contentPane.add(budgetLabel);
		
		budgetTextField = new JTextField();
		budgetTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(budgetTextField.getText().length() > 0 && !(updateButton.isSelected())) {
					verifyInput(budgetTextField);
					}
			}
		});
		budgetTextField.setBounds(246, 98, 96, 20);
		contentPane.add(budgetTextField);
		budgetTextField.setColumns(10);
	}
	
	private boolean verifyInput(JTextField textField) {
		boolean flag = true;
		
		try {
			Double.parseDouble(textField.getText());
		}catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Must enter numbers!");
			textField.requestFocus();
			textField.selectAll();
			flag = false;
		}
		return flag;
	}
	
	private int getRatingIndex(String ratingValue) {
		switch(ratingValue) {
		case "G":
			return 0;
		case "PG":
			return 1;
		case "PG-13":
			return 2;
		case "R":
			return 3;
		case "CN-17":
			return 4;
		default:
			return 0;
		}
	}
	
	private String getRating(int ratingValue) {
		switch(ratingValue) {
		case 0:
			return "G";
		case 1:
			return "PG";
		case 2:
			return "PG-13";
		case 3:
			return "R";
		case 4:
			return "CN-17";
		default:
			return "G";
		}
	}
	
	public void pressAsk() {
		askMovieButton.doClick();
	}
	
}