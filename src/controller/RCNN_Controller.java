package controller;

import java.net.MalformedURLException;
import java.net.URL;

import resources.datatypes.Edge;
import resources.datatypes.Node;

import controller.networking.NetworkController;

import view.RCNN_View;
import model.RCNN_Model;

public class RCNN_Controller {
	private RCNN_Model model;
	private RCNN_View view;
	private NetworkController network;

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

	public void initialize() {
		network = new NetworkController();
		try {
			network.setAddress("http://localhost");
			network.setPort("9000");
			network.addNode("TestNode");
			network.updateSnapshot(new URL("http://localhost:9000/stream"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
