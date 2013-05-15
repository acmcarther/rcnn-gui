package controller.command;

import controller.RCNN_Controller;

public class SettingsHandler {
	
	RCNN_Controller controller;
	
	public SettingsHandler(RCNN_Controller controller){
		this.controller = controller;
	}
	
	public boolean execute(String address){
		return controller.setAddress(address);
	}
}
