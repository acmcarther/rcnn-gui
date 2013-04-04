package controller.command;

import resources.datatypes.ControlData;
import controller.RCNN_Controller;

public class NewNodeHandler implements ControlListenerInterface {
	
	RCNN_Controller controller;
	
	public NewNodeHandler(RCNN_Controller controller){
		this.controller = controller;
	}

	public boolean execute(ControlData data){
		return controller.addNode(data.getNode1(), data.getAL());
	}
}
