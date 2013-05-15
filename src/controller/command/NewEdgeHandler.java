package controller.command;

import resources.datatypes.ControlData;
import controller.RCNN_Controller;

public class NewEdgeHandler implements ControlListenerInterface {
	
	RCNN_Controller controller;
	
	public NewEdgeHandler(RCNN_Controller controller){
		this.controller = controller;
	}

	public boolean execute(ControlData data){
		return controller.addEdge(data.getNode1(), data.getNode2());
	}
}
