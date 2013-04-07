package main;

import controller.RCNN_Controller;

import model.RCNN_Model;

import view.RCNN_View;

public class MainDriver {
	public static void main(String[] args) throws InterruptedException {

		// Instantiate Model
		RCNN_Model model = new RCNN_Model(2000);
					
		// Instantiate View
		RCNN_View view = new RCNN_View(model);
					
		// Instantiate Controller
		RCNN_Controller controller = new RCNN_Controller(model, view);
		
		// Loop to continuously update the data
		while(controller.isRunning()){
			Thread.sleep(50);
			controller.updateData();
		}
	}
}
