package edu.pupr.movieInventory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.*;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.ScrollPane;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
		setTitle("All Movies");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		
		  String[] columnNames = {"Title", "Director", "Plot", "Rating", "Budget", "Release Date", "Poster"}; 
		  DefaultTableModel tableModel = new DefaultTableModel(columnNames,0) {
			  
			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			  @Override
			  public boolean isCellEditable(int row, int column) {
				  return false;
			  }
		  };
		  
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
		  
			  
			  
			  String posterPath = "PosterDirectory/" + title + ".png";
			  ImageIcon posterIcon = null;
		  
			  java.io.File file = new java.io.File(posterPath);
			  if(file.exists()) {
				  ImageIcon rawIcon = new ImageIcon(posterPath);
				  Image scaledImage = rawIcon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
				  posterIcon = new ImageIcon(scaledImage);
			  }
			  
			  Object[] data = {title, director, plot, rating, String.format("%,.2f", budget), date.format(formattedDate), posterIcon};
			  tableModel.addRow(data); 
		  }
		  
		  
		  JTable table = new JTable(tableModel);
		  table.setDefaultEditor(Object.class, null);
		  table.getColumnModel().getColumn(2).setCellRenderer(new WrappingTextRenderer());
		  table.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());

	        
		  table.setRowHeight(table.getRowHeight() + 200); 
		  JScrollPane allMovies = new JScrollPane(table); 
		  contentPane.add(allMovies, BorderLayout.CENTER);
		  
}


	
	class WrappingTextRenderer extends JTextArea implements TableCellRenderer {
	    public WrappingTextRenderer() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	                                                   boolean isSelected, boolean hasFocus,
	                                                   int row, int column) {
	        setText(value.toString());

	        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
	        int prefHeight = (int) (getPreferredSize().height >= 200 ? getPreferredSize().height + 1 : 200);
	        if (table.getRowHeight(row) != prefHeight) {
	            table.setRowHeight(row, prefHeight);
	        }

	        return this;
	    }
	}
	
private class ImageRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		 JLabel label = new JLabel();
	        label.setVisible(true);

	        if (value instanceof ImageIcon) {
	            label.setIcon((ImageIcon) value);
	        }

	        label.setHorizontalAlignment(JLabel.CENTER);
	        return label;
	}
}




}