package controller;

import controller.command.CloseController;
import controller.command.DelNodeHandler;
import controller.command.NewNodeHandler;
import controller.networking.NetworkController;
import view.RCNN_View;
import model.RCNN_Model;

public class RCNN_Controller {
	private RCNN_Model model;
	private RCNN_View view;
	private NetworkController network;
	private CloseController windowCloseHandler;
	private NewNodeHandler newNodeHandler;
	private DelNodeHandler delNodeHandler;
	private boolean running;

	public RCNN_Controller(RCNN_Model model, RCNN_View view){
		running = true;
		this.model = model;
		this.view = view;
	}
	
	public void initialize() {
		// Create the network and define default parameters
		network = new NetworkController(model);
		network.setAddress("http://localhost");
		network.setPort("9000");
		
		newNodeHandler = new NewNodeHandler(this);
		view.registerNewNodeHandler(newNodeHandler);
		
		delNodeHandler = new DelNodeHandler(this);
		view.registerDelNodeHandler(delNodeHandler);
		
		windowCloseHandler = new CloseController(this);
		view.registerCloseHandler(windowCloseHandler);
	}

	public boolean addNode(String name, Float al) {
		// Verify that node does not exist
		if( model.hasNodeNamed(name) ){
			return false;
		}
		
		// Tell the network to add the new node
		network.addNode(name, al);
		
		// Return successful outcome
		return true;
	}

	public boolean deleteNode(String nodeName) {
		// Verify that node does exist
		if(!model.hasNodeNamed(nodeName)){
			return false;
		}
		
		// Tell the network to add the new node
		network.deleteNode(nodeName);
		
		// Return successful outcome
		return true;
		
	}

	public boolean deleteEdge(String parentName, String childName) {
		// Verify that edge does exist
		if(!model.hasEdgeBetween(parentName, childName)){
			return false;
		}
		
		// Tell the network to add the new node
		network.deleteEdge(parentName, childName);
		
		// Return successful outcome
		return true;
		
	}

	public boolean addEdge(String parentName, String childName) {
		// Verify that nodes exists
		if(!model.hasNodeNamed(parentName) || !model.hasNodeNamed(childName)){
			return false;
		}
		
		// Tell the network to add the new edge
		network.addEdge(parentName, childName);
		return true;
		
	}

	public boolean editNode(String name, float al) {
		// Verify that node exists
		if(!model.hasNodeNamed(name)){
			return false;
		}
		
		// TODO: Figure out how to set the node's AL through the network
		return true;
	}

	public boolean editEdge(String parentName, String childName) {
		// Verify that edge does exist
		if(!model.hasEdgeBetween(parentName, childName)){
			return false;
		}
		
		// TODO: Wait for propagation delay to be implemented in the network
		return true;
	}
	
	public NetworkController getNetwork(){
		return network;
	}
	
	// Volatile data access here
	public void updateData(){
		// TODO: Switch this to asynchronous updateStream()
		network.updateSnapshot();
	}

	public boolean isRunning(){
		return running;
	}

	public void setRunning(boolean b) {
		running = b;
	}
	

}
