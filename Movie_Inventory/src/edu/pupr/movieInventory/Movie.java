package edu.pupr.movieInventory;

import java.io.File;
import java.time.LocalDate;

import javax.swing.JFileChooser;


/**
 * <b><code>Program: Movie.java</code></b></br>
 * <b><code>Description: Class for Movie Object</b></code><br>
 * <b><code>Date: 10/14/2025</b></code>
 */
public class Movie {
	private String title;
	private String director;
	private String plot;
	private String rating;
	private double budget;
	private LocalDate releaseDate;
	private File posterFile;
	
	public Movie() {}
	
	/**
	 * Constructor for the Movie Object
	 * @param title
	 * @param director
	 * @param plot
	 * @param rating
	 * @param budget
	 * @param releaseDate
	 */
	public Movie(String title, String director, String plot, String rating, double budget, LocalDate releaseDate)
	{
		setTitle(title);
		setDirector(director);
		setPlot(plot);
		setRating(rating);
		setBudget(budget);
		setReleaseDate(releaseDate);
	}

	//Getters and Setters
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setPosterFile(File posterFile) {
		this.posterFile = posterFile;
	}
	
	
	//Getters
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getPlot() {
		return plot;
	}
	
	public String getRating() {
		return rating;
	}
	
	public double getBudget() {
		return budget;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public File getPosterFile() {
		return posterFile;
	}
}
