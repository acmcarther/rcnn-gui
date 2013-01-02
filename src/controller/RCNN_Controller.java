package controller;

import dataTypes.Edge;
import dataTypes.Node;
import userInterface.RCNN_View;
import nodeModel.RCNN_Model;

public class RCNN_Controller {
	private RCNN_Model model;
	private RCNN_View view;

	public void registerModel(RCNN_Model model) {
		this.model = model;
		
	}

	public void registerView(RCNN_View view) {
		this.view = view;
	}

	public boolean addNodeEvent(String node1, String al) {
		// TODO: Validate Data
		// TODO: 
		return false;
	}

	public void deleteNode(Node selectedNode) {
		// TODO Auto-generated method stub
		
	}

	public void deleteEdge(Edge selectedEdge) {
		// TODO Auto-generated method stub
		
	}

	public boolean addEdgeEvent(String node1, String node2, String pd) {
		// TODO: Validate Data (Verify existence of node1, node2, and verify that pd is
		//		 non-negative float
		// TODO:
		return false;
	}

	public boolean editNodeEvent(Node editNode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editEdgeEvent(Edge editEdge) {
		// TODO Auto-generated method stub
		return false;
	}

}
