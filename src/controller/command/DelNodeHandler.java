package controller.command;

import resources.datatypes.ControlData;
import controller.RCNN_Controller;

public class DelNodeHandler implements ControlListenerInterface {
	
	RCNN_Controller controller;
	
	public DelNodeHandler(RCNN_Controller controller){
		this.controller = controller;
	}

	public boolean execute(ControlData data){
		return controller.deleteNode(data.getNode1());		
	}

}
