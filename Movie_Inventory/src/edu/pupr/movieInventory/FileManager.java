package edu.pupr.movieInventory;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <b><code>Program: FileManager.java</b></code><br>
 * <b><code>Description: Handles all the files to and from the PosterDirectory</b></code><br>
 * <b><code>Date: 10/14/2025</b></code>
 */
public class FileManager {
	
	private File file;		//File to manage
	private String fileName;//Name of the file
	
	/**
	 * Default constructor
	 */
	public FileManager() {}
	
	/**
	 * Parameter constructor
	 * @param tempFile
	 * @param fileName
	 */
	public FileManager(File tempFile, String fileName) {
		setFile(tempFile);
		setFileName(fileName);
	}

	/**
	 * Creates the file at the directory and assigns it as a png image
	 */
	public void addFile() {
		try {
		File directory = new File("PosterDirectory");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destination = new File(directory, getFile().getName());
        Files.copy(getFile().toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(destination.getPath());

        Path source = destination.toPath();

        Files.move(source, source.resolveSibling(getFileName() + ".png"), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Could not add File!");
		}
	}

	/**
	 * Returns the Image if needed and changes the icon at the JLabel to the poster image
	 * @param title - Name of the file to be retrieved
	 * @param imageLabel - JLabel to change
	 * @return
	 */
	public ImageIcon getFileImage(String title, JLabel imageLabel) {
		
		File file = new File("PosterDirectory/" + title + ".png");
		System.out.println(file.getAbsolutePath());
		
		ImageIcon icon = new ImageIcon(file.getAbsolutePath());
		
		Image scaledImage = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
		
		setFile(file);
		
		return new ImageIcon(scaledImage);
	}
	
	/**
	 * Resizes the image to fit the received JLabel
	 * @param imageLabel - JLabel to constraint to
	 */
	public void resizeImage(JLabel imageLabel) {
		try {
			
			File selectedImage = ImageSelector();
			if(selectedImage != null) {
			ImageIcon image = new ImageIcon(selectedImage.getAbsolutePath());
			
			Image scaledImage = image.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
			
			setFile(selectedImage);
			
			imageLabel.setIcon(new ImageIcon(scaledImage));
			
			}else {
				throw new InputMismatchException();
			}
			
	} catch(InputMismatchException ex) {
		

	}
	}
	
	/**
	 * Uses JFileChooser to select an image file (Limited to png and jpg files)
	 * @return
	 */
	public static File ImageSelector() {
		try {
			
			JFileChooser imageChooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg");
			
			imageChooser.setFileFilter(filter);
			
			imageChooser.setCurrentDirectory(new File(".")); //Sets the directory to our current directory
			
			int result = imageChooser.showOpenDialog(null);
			
			System.out.println("Result" + result);
			
			if(result == JFileChooser.APPROVE_OPTION)	//If approved a file, return that file
			{
				File selectedFile = new File(imageChooser.getSelectedFile().getAbsolutePath());
				System.out.println("Filepath " + selectedFile);
				
				return selectedFile;

			}
			else if(result == JFileChooser.CANCEL_OPTION)
			{
				System.out.println("return is null");
				return null;
			}
			
		}catch(Exception ex) {

			return null;
		}
		return null;
	}
	
	//Setters and Getters
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
}
