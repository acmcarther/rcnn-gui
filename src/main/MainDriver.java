package main;

import graphics.NodeDisplayer;

import java.awt.EventQueue;

import userInterface.UserInterface;

public class MainDriver {
	public static void main(String[] args) {
		
		// Build the views
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create the user interface
					UserInterface userInterface = new UserInterface();
					//NodeDisplayer nodeDisplay = new NodeDisplayer();
				} catch (Exception e) {
					// Print error and quit if cannot display window
					e.printStackTrace();
					System.out.println("Could not build window");
	                System.exit( 0 );
				}
			}
		});
		
		// TODO: Build the model
		
		
		// TODO: Build the controller
		
		
	}
}
