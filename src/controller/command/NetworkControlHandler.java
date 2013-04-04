package controller.command;

import controller.RCNN_Controller;
import resources.datatypes.ControlData;

public class NetworkControlHandler implements ControlListenerInterface {
	
	RCNN_Controller controller;
	
	public NetworkControlHandler(RCNN_Controller controller){
		this.controller = controller;
	}
	
	public boolean execute(ControlData data){
		System.out.println("asdf");
		controller.getNetwork().setConnecting(true);
		return true;
	}
}
