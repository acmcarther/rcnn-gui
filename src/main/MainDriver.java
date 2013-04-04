package main;

import controller.RCNN_Controller;

import model.RCNN_Model;

import view.RCNN_View;

public class MainDriver {
	public static void main(String[] args) throws InterruptedException {

		// Instantiate Model
		RCNN_Model model = new RCNN_Model();
					
		// Instantiate View
		RCNN_View view = new RCNN_View(model);
					
		// Instantiate Controller
		RCNN_Controller controller = new RCNN_Controller(model, view);
		
		// Initialize model
			// This parameter is the resolution of the graph
		model.initialize(200);
		
		// Initialize the controller
		controller.initialize();
		
		//controller.getNetwork().setAddress("http://207.197.58.26");
		
		// Initialize the view
		view.initialize();
		
		// Loop to continuously update the data
		while(controller.isRunning()){
			// NEW IN THIS VERSION: Doesn't refresh screen so fast, but gives me the data fast.
			Thread.sleep(17);
			controller.updateData();
		}
	}
}
