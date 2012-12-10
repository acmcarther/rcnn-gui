package main;

import java.awt.EventQueue;

import userInterface.NodeDisplay;
import userInterface.UserInterface;

public class MainDriver {
	public static void main(String[] args) {
		
		// Build the views
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create the user interface
					UserInterface userInterface = new UserInterface();
					NodeDisplay nodeDisplay = new NodeDisplay();
				} catch (Exception e) {
					// Print error and quit if cannot display window
					System.out.println("Could not build window");
	                System.exit( 0 );
				}
			}
		});
		
		// TODO: Build the model
		
		
		// TODO: Build the controller
		
		
	}
}
