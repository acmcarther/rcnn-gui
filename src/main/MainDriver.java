package main;

import controller.RCNN_Controller;

import model.RCNN_Model;

import view.RCNN_View;

public class MainDriver {
	public static void main(String[] args) throws InterruptedException {

		// Instantiate Model
		RCNN_Model model = new RCNN_Model();
					
		// Instantiate View
		RCNN_View view = new RCNN_View();
					
		// Instantiate Controller
		RCNN_Controller controller = new RCNN_Controller();
					
		// Register MVC components so they can interact
		model.registerView(view);
		view.registerModel(model);
		view.registerController(controller);
		controller.registerModel(model);
		controller.registerView(view);
		
		// The following stuff HAS to go in this order ( or you get some null pointers )
		
		// Initialize model
		model.initialize();
		
		// Initialize the controller
		controller.initialize();
		
		//controller.getNetwork().setAddress("http://207.197.58.26");
		
		// Inititalize the view
		view.initialize();
					
		// Enable the view
		view.setVisible(true);
		
		// Loop to continuously update the data
		while(view.isRunning()){
			// NEW IN THIS VERSION: Doesn't refresh screen so fast, but gives me the data fast.
			Thread.sleep(17);
			controller.updateData();
			
			// TEMP THINGS IN THIS AREA GUYS
			//model.doTempThings();
			view.forceGLUpdate();
		}
		
	}
}
