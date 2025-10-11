package edu.pupr.movieInventory;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieQueries {
	private static final String URL = "jdbc:mysql://localhost:3306/movies"; //This is the string used to connect to our database (jdbc, 
	  //where to connect(my machine or external IP), port, database)
	private static final String USERNAME = "spacetacos";
	private static final String PASSWORD = "Joshualopez7";

	private Connection connection = null;
	private PreparedStatement selectAllMovies = null;
	private PreparedStatement selectMovieByTitle = null;
	private PreparedStatement selectMovieByDirector = null;
	private PreparedStatement selectMovieByYear = null;
	private PreparedStatement selectMovieByTitleDirectorYear = null;
	private PreparedStatement insertNewMovie = null;
	private PreparedStatement updateMovie = null;
	
	public MovieQueries() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);	//Connects the database with our application AuthorQueries
			
			selectAllMovies = connection.prepareStatement("SELECT * FROM movies.movies");
			selectMovieByTitle = connection.prepareStatement("SELECT * FROM movies.movies WHERE title = ?"); //Used ? to prevent SQL Injection
			selectMovieByDirector = connection.prepareStatement("SELECT * FROM movies.movies WHERE director = ?"); 
			selectMovieByTitleDirectorYear = connection.prepareStatement("SELECT * FROM movies.movies WHERE (title = ?) AND (director = ?) AND (YEAR(releaseDate) = ?)");
			insertNewMovie = connection.prepareStatement("INSERT INTO movies.movies (title, director, plot, rating, budget, releaseDate) VALUES(?, ?, ?, ?, ?, ?)");
			updateMovie = connection.prepareStatement("UPDATE movies.movies SET director = ?, plot = ?, rating = ?, budget = ?, releaseDate = ?, WHERE title = ?");
			
		} catch (SQLException ex) {
			System.err.println("Connect error to DB: " + ex.getMessage());
			System.exit(1);
		}
	}
	
	
	public List<Movie> getAllMovies() {
		List<Movie> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllMovies.executeQuery();
			 results = new ArrayList<>();
			 
			 while (resultSet.next()) {;
				 results.add(new Movie(resultSet.getString("title"), resultSet.getString("director"), resultSet.getString("plot"), resultSet.getString("rating"),
						 resultSet.getDouble("budget"), resultSet.getDate("releaseDate").toLocalDate()));
			}
			 
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			System.exit(1);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return results;
	}
	
	
	public List<Movie> getMovieByTitle(String title) {
		List<Movie> results = null;
		ResultSet resultSet = null;
		
		try {
			selectMovieByTitle.setString(1, title);;
			resultSet = selectMovieByTitle.executeQuery();
			 results = new ArrayList<>();
			 
			 while (resultSet.next()) {
				 results.add(new Movie(resultSet.getString("title"), resultSet.getString("director"), resultSet.getString("plot"), resultSet.getString("rating"),
						 resultSet.getDouble("budget"), resultSet.getDate("releaseDate").toLocalDate()));
			}
			 
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return results;
	}
	
	
	public List<Movie> getMovieByDirector(String director) {
		List<Movie> results = null;
		ResultSet resultSet = null;
		
		try {
			selectMovieByDirector.setString(1, director);
			resultSet = selectMovieByDirector.executeQuery();
			 results = new ArrayList<>();
			 
			 while (resultSet.next()) {
				 results.add(new Movie(resultSet.getString("title"), resultSet.getString("director"), resultSet.getString("plot"), resultSet.getString("rating"),
						 resultSet.getDouble("budget"), resultSet.getDate("releaseDate").toLocalDate()));
			}
			 
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return results;
	}
	
	public List<Movie> getMovieByYear(Date releaseYear) {
		List<Movie> results = null;
		ResultSet resultSet = null;
		
		try {
			selectMovieByYear.setDate(1, releaseYear);
			resultSet = selectMovieByYear.executeQuery();
			 results = new ArrayList<>();
			 
			 while (resultSet.next()) {
				 results.add(new Movie(resultSet.getString("title"), resultSet.getString("director"), resultSet.getString("plot"), resultSet.getString("rating"),
						 resultSet.getDouble("budget"), resultSet.getDate("releaseDate").toLocalDate()));
			}
			 
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return results;
	}
	
	
	public List<Movie> getMovieByTitleDirectorYear(String title, String director, String year) {
		List<Movie> results = null;
		ResultSet resultSet = null;
		
		try {
			selectMovieByTitleDirectorYear.setString(1, title);
			selectMovieByTitleDirectorYear.setString(2, director);
			selectMovieByTitleDirectorYear.setString(3, year);
			resultSet = selectMovieByTitleDirectorYear.executeQuery();
			 results = new ArrayList<>();
			 
			 while (resultSet.next()) {
				 results.add(new Movie(resultSet.getString("title"), resultSet.getString("director"), resultSet.getString("plot"), resultSet.getString("rating"),
						 resultSet.getDouble("budget"), resultSet.getDate("releaseDate").toLocalDate()));
			}
			 
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return results;
	}
	
	
	public int addMovie(String title, String director, String plot, String rating, double budget, Date releaseDate) {
		int result = 0;
		
		try {
			
			insertNewMovie.setString(1, title);
			insertNewMovie.setString(2, director);
			insertNewMovie.setString(3, plot);
			insertNewMovie.setString(4, rating);
			insertNewMovie.setDouble(5, budget);
			insertNewMovie.setDate(6, releaseDate);
			
			result = insertNewMovie.executeUpdate();
			
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		}
		
		return result;
	}
	
	
	public int updateMovie(String title, String director, String plot, String rating, double budget, LocalDate releaseDate) {
		int result = 0;
		
		try {
			
			updateMovie.setString(1, title);
			updateMovie.setString(2, director);
			updateMovie.setString(3, plot);
			updateMovie.setString(4, rating);
			updateMovie.setDouble(5, budget);
			updateMovie.setDate(6, Date.valueOf(releaseDate));
			
			result = updateMovie.executeUpdate();
			
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
			close();
		}
		
		return result;
	}
	
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.err.println("Error in DB: " + ex.getMessage());
		}
	}

}
