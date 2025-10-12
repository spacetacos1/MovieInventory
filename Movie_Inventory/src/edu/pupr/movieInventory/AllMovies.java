package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.ScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AllMovies extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllMovies frame = new AllMovies();
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
	public AllMovies() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(85, Short.MAX_VALUE)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

/*		String[] columnNames = {"Title", "Director", "Plot", "Rating", "Budget", "Release Date", "Poster"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		
		MovieQueries movieQueries = new MovieQueries();
		List<Movie> list = movieQueries.getAllMovies();
		
		DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("LLL dd yyyy");
		
		
		for(int i = 0; i < list.size(); i++) {
			String title = list.get(i).getTitle();
			String director = list.get(i).getDirector();
			String plot = list.get(i).getPlot();
			String rating = list.get(i).getRating();
			Double budget = list.get(i).getBudget();
			LocalDate date = list.get(i).getReleaseDate();
			
			String[] data = {title, director, plot, rating, budget.toString(), date.format(formattedDate)};
			
			tableModel.addRow(data);
		}
		
		
		JTable table = new JTable(tableModel);
		
		table.setRowHeight(table.getRowHeight() + 70);
		table.setEnabled(false);
		
	
		JScrollPane allMovies = new JScrollPane(table);
		add(allMovies);
		
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent event) {
				
				try {
					String toolTipText = (String) table.getValueAt(table.rowAtPoint(event.getPoint()), table.columnAtPoint(event.getPoint()));
					
					if(toolTipText.length() > 25)
						table.setToolTipText(toolTipText);
					else
						table.setToolTipText(null);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	*/}
}
