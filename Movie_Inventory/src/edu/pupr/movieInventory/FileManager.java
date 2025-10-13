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

public class FileManager {
	
	private File file;
	private String fileName;
	
	public FileManager() {}
	
	public FileManager(File tempFile, String fileName) {
		setFile(tempFile);
		setFileName(fileName);
	}


	public void addFile() {
		try {
		File directory = new File("PosterDirectory");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destination = new File(directory, getFile().getName());
        Files.copy(getFile().toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(destination.getPath());
        JOptionPane.showMessageDialog(null, destination.getAbsolutePath());

        Path source = destination.toPath();

        Files.move(source, source.resolveSibling(getFileName() + ".png"), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Could not add File!");
		}
	}

	public ImageIcon getFileImage(String title) {
		
		File file = new File("PosterDirectory/" + title + ".png");
		System.out.println(file.getAbsolutePath());
		setFile(file);
		ImageIcon icon = new ImageIcon(file.getAbsolutePath());
		
		return icon;
	}
	
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
