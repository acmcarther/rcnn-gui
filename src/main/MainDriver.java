package main;

import java.awt.EventQueue;

import controller.RCNN_Controller;

import model.RCNN_Model;

import view.RCNN_View;

public class MainDriver {
	public static void main(String[] args) {

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
					
		// Enable the view
		view.setVisible(true);

		
		// Initialize model
		model.initialize();
		
		
		// Initialize the controller
		controller.initialize();
		
		while(true){
			//controller.updateData();
		}
		
	}
}
