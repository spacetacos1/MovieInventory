package edu.pupr.movieInventory;

import java.io.File;
import java.time.LocalDate;

import javax.swing.JFileChooser;

public class Movie {
	private String title;
	//private JFileChooser poster;
	private String director;
	private String plot;
	private String rating;
	private double budget;
	private LocalDate releaseDate;
	private File posterFile;
	
	public Movie() {}
	
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
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public File getPosterFile() {
		return posterFile;
	}

	public void setPosterFile(File posterFile) {
		this.posterFile = posterFile;
	}
	
	
	
	
}
