package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class AddMovie extends JFrame {

	private JPanel contentPane;
	private final JTextField titleTextField = new JTextField();
	private final JTextField directorTextField = new JTextField();
	private JTextField dayTextField;
	private JTextField yearTextField;
	private JTextField budgetTextField;
	private JButton addButton;
	private JButton exitButton;
	private JTextArea plotArea;
	private JComboBox ratingComboBox;
	private JComboBox monthComboBox;
	private JLabel imageLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovie frame = new AddMovie();
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
	public AddMovie() {
		setResizable(false);
		setTitle("Add a Movie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		titleTextField.setBounds(68, 51, 470, 20);
		titleTextField.setColumns(10);
		
		addButton = new JButton("Add Movie");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MovieQueries addMovie = new MovieQueries();
				
				try {
				String title = titleTextField.getText();
				String director = directorTextField.getText();
				String plot = plotArea.getText();
				String rating = "";
				
				switch(ratingComboBox.getSelectedIndex()) {
				case 0:
					rating = "G";
					break;
				case 1:
					rating = "PG";
					break;
				case 2:
					rating = "PG-13";
					break;
				case 3:
					rating = "R";
					break;
				case 4:
					rating = "NC-17";
					break;
				default:
					throw new InputMismatchException();
				}
				
				double budget = Double.parseDouble(budgetTextField.getText());
				
				int day = Integer.parseInt(dayTextField.getText());
				int month = monthComboBox.getSelectedIndex() + 1;
				int year = Integer.parseInt(yearTextField.getText());
				
				Date releaseDate = Date.valueOf(LocalDate.of(year,  month,  day));
				
				int result = addMovie.addMovie(title, director, plot, rating, budget, releaseDate);
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Record successfully inserted!");
				}else
					JOptionPane.showMessageDialog(null, "Record unsuccessfully inserted!");
				
			}catch (InputMismatchException ex) {
				JOptionPane.showMessageDialog(null, "Rating selection not viable!");
			}
			}
		});
		addButton.setBounds(398, 225, 126, 42);
		
		JLabel infoLabel = new JLabel("Input the information required for the new movie:");
		infoLabel.setBounds(10, 11, 416, 16);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setBounds(15, 56, 49, 14);
		
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(15, 81, 71, 14);
		directorTextField.setBounds(68, 81, 296, 20);
		directorTextField.setColumns(10);
		
		JLabel plotLabel = new JLabel("Plot:");
		plotLabel.setBounds(15, 157, 49, 14);
		
		JLabel ratingLabel = new JLabel("Rating:");
		ratingLabel.setBounds(15, 118, 49, 14);
		
		ratingComboBox = new JComboBox();
		ratingComboBox.setBounds(68, 115, 71, 20);
		ratingComboBox.setModel(new DefaultComboBoxModel(new String[] {"G", "PG", "PG-13", "R", "NC-17"}));
		contentPane.setLayout(null);
		
		plotArea = new JTextArea();
		plotArea.setWrapStyleWord(true);
		plotArea.setLineWrap(true);
		plotArea.setBounds(68, 169, 292, 191);
		contentPane.add(plotArea);
		contentPane.add(titleLabel);
		contentPane.add(directorLabel);
		contentPane.add(directorTextField);
		contentPane.add(titleTextField);
		contentPane.add(plotLabel);
		contentPane.add(ratingLabel);
		contentPane.add(ratingComboBox);
		contentPane.add(addButton);
		contentPane.add(infoLabel);
		
		JLabel dateLabel = new JLabel("Released Date:");
		dateLabel.setBounds(385, 81, 105, 14);
		contentPane.add(dateLabel);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setBounds(398, 104, 49, 14);
		contentPane.add(dayLabel);
		
		JLabel monthLabel = new JLabel("Month:");
		monthLabel.setBounds(398, 143, 49, 14);
		contentPane.add(monthLabel);
		
		JLabel yearLabel = new JLabel("Year:");
		yearLabel.setBounds(398, 182, 49, 14);
		contentPane.add(yearLabel);
		
		dayTextField = new JTextField();
		dayTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(dayTextField.getText().length() > 0 && !(addButton.isSelected())) {
					verifyInput(dayTextField);
					}
			}
		});

		dayTextField.setBounds(442, 101, 96, 20);
		contentPane.add(dayTextField);
		dayTextField.setColumns(10);
		
		yearTextField = new JTextField();
		yearTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(yearTextField.getText().length() > 0 && !(addButton.isSelected())) {
					verifyInput(yearTextField);
					}
			}
		});

		yearTextField.setBounds(442, 179, 96, 20);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
		
		JLabel budgetLabel = new JLabel("Budget:");
		budgetLabel.setBounds(162, 115, 49, 14);
		contentPane.add(budgetLabel);
		
		budgetTextField = new JTextField();
		budgetTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if(budgetTextField.getText().length() > 0 && !(addButton.isSelected())) {
					verifyInput(budgetTextField);
					}
			}
		});
		budgetTextField.setBounds(215, 112, 119, 20);
		contentPane.add(budgetTextField);
		budgetTextField.setColumns(10);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		exitButton.setBounds(397, 318, 127, 42);
		contentPane.add(exitButton);
		
		monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		monthComboBox.setBounds(442, 139, 96, 22);
		contentPane.add(monthComboBox);
		
		imageLabel = new JLabel("");
		imageLabel.setIcon(null);
		imageLabel.setBounds(548, 54, 238, 306);
		contentPane.add(imageLabel);
		
		JButton imageButton = new JButton("Change Poster");
		imageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					
						File selectedImage = ImageSelector();
						if(selectedImage != null) {
						ImageIcon image = new ImageIcon(selectedImage.getAbsolutePath());
						Image scaledImage = image.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
						
						imageLabel.setIcon(new ImageIcon(scaledImage));
						}else {
							throw new InputMismatchException();
						}
						
						
				} catch(InputMismatchException ex) {

				}
			}
		});
		imageButton.setBounds(398, 272, 126, 42);
		contentPane.add(imageButton);
	}
	
	private boolean verifyInput(JTextField textField) {
		boolean flag = true;
		
		try {
			double num = Double.parseDouble(textField.getText());
		}catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Must enter numbers!");
			textField.requestFocus();
			textField.selectAll();
			flag = false;
		}
		return flag;
	
}
	
	
	public static File ImageSelector() {
		try {
			
			JFileChooser imageChooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg");
			
			imageChooser.setFileFilter(filter);
			
			imageChooser.setCurrentDirectory(new File("."));
			
			int result = imageChooser.showOpenDialog(null);
			
			System.out.println("Result" + result);
			
			if(result == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = new File(imageChooser.getSelectedFile().getAbsolutePath());
				System.out.println("Filepath " + selectedFile);
				
				return selectedFile;

			}
			else if(result == JFileChooser.CANCEL_OPTION)
			{
				return null;
			}
			
		}catch(Exception ex) {

			return null;
		}
		return null;
	}

}
//IMPORTANT EXCEPTIONS TO BE HANDLED:
//Invalid day of month - add movie and update movie - both must be verified
//Duplicate movie tried to be added -
