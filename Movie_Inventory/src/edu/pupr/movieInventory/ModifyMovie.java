package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import java.time.LocalDate;

import edu.pupr.movieInventory.MovieQueries;
import edu.pupr.movieInventory.Movie;

public class ModifyMovie extends JFrame {

	private JPanel contentPane;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField idTextField;
	private JButton askIdButton;
	private JButton updateButton;
	private JButton closeButton;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel idLabel = new JLabel("ID:");
		
		JLabel fNameLabel = new JLabel("First Name:");
		
		JLabel lNameLabel = new JLabel("Last Name:");
		
		idTextField = new JTextField();
		idTextField.setEnabled(false);
		idTextField.setColumns(10);
		
		fNameField = new JTextField();
		fNameField.setColumns(10);
		
		lNameField = new JTextField();
		lNameField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MovieQueries movieQueries = new MovieQueries();
				String title = idTextField.getText();
				String director = fNameField.getText();
				//LocalDate releaseDate = LocalDate.of(lNameField.getText());
				
				LocalDate release = LocalDate.of(01,01,01); 							//TEMPORARY********* ADD DAY, MONTH, AND YEAR FIELDS FOR FULL DATE
				int result = movieQueries.updateMovie(title, director, release);		//RELEASE IS TEMPORARY **** ADD PLOT, RATING, BUDGET, AND FULL RELEASE DATE TO UPDATE MOVIE METHOD
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Successfully Updated Record!");
				} else {
					JOptionPane.showMessageDialog(null, "Unsuccessfully Updated Record!");
				}
				
				//dispose();
			}
		});
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		
		askIdButton = new JButton("Ask ID");
		askIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MovieQueries movieQueries = new MovieQueries();
				
				String title = JOptionPane.showInputDialog("Enter Movie Title: ");
				
				List<Movie> list = movieQueries.getMovieByTitle(title);
				Movie movie = (Movie)list.get(0);
				
				idTextField.setText(movie.getTitle().toString());
				fNameField.setText(movie.getDirector());
				lNameField.setText(String.valueOf(movie.getReleaseDate().getYear()));
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lNameLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fNameLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(idLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fNameField, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addComponent(lNameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addComponent(askIdButton)
					.addGap(18)
					.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(idLabel)
						.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fNameLabel)
						.addComponent(fNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lNameLabel)
						.addComponent(lNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(askIdButton))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {updateButton, closeButton, askIdButton});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {updateButton, closeButton, askIdButton});
		contentPane.setLayout(gl_contentPane);
	}
}
