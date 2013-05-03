package controller;

import controller.command.CloseController;
import controller.command.DelNodeHandler;
import controller.command.NetworkControlHandler;
import controller.command.NewNodeHandler;
import controller.networking.NetworkController;
import view.RCNN_View;
import model.RCNN_Model;

/**
 * RCNN_Controller is the controller component of the MVC part of the RCNN 
 * visualization. It contains:
 * <ul>
 * <li>Network Management (between the client and the server)
 * <li>Server Node Management
 * <li>User Input Management
 * <li>Graphic Management
 * </ul>
 * @author      Alexander McArther
 */
public class RCNN_Controller {
	
	private RCNN_Model model;
	//private RCNN_View view;
	private NetworkController network;
	private CloseController windowCloseHandler;
	private NewNodeHandler newNodeHandler;
	private DelNodeHandler delNodeHandler;
	private NetworkControlHandler netControlHandler;
	private boolean running;

	/**
	 * Create the controller of the RCNN Visualization. Here
	 * it generates all of the event handlers and initial 
	 * parameters for the network
	 *
	 * @param  model - The MVC model of the program
	 * @param  view  - The MVC view of the program
	 * @see RCNN_Model
	 * @see RCNN_View
	 */
	public RCNN_Controller(RCNN_Model model, RCNN_View view){
		running = true;
		this.model = model;
		//this.view = view;
		
		network = new NetworkController(model);
		network.setAddress("http://localhost");
		network.setPort("9000");
		
		netControlHandler = new NetworkControlHandler(this);
		view.registerNetworkControlHandler(netControlHandler);
		
		newNodeHandler = new NewNodeHandler(this);
		view.registerNewNodeHandler(newNodeHandler);
		
		delNodeHandler = new DelNodeHandler(this);
		view.registerDelNodeHandler(delNodeHandler);
		
		windowCloseHandler = new CloseController(this);
		view.registerCloseHandler(windowCloseHandler);
	}

	/**
	 * Prompts the network controller to ask the server to create
	 * a new node of the input name.
	 * @param  nodeName - The name of the new node.
	 * @param  al - The initial activation level of the node
	 * @return <code>true</code> if the node was added, <code>
	 * false</code> otherwise.
	 * @see NetworkController
	 */
	public boolean addNode(String nodeName, Float al) {
		// Verify that node does not exist
		if(model.hasNodeNamed(nodeName)){
			return false;
		}
		
		// Tell the network to add the new node
		network.addNode(nodeName, al);
		
		// Return successful outcome
		return true;
	}

	/**
	 * Prompts the network controller to ask the server to delete the node
	 * of the input name.
	 * @param  nodeName - The name of the node to delete.
	 * @return <code>true</code> if the node was deleted, <code>
	 * false</code> if it didn't even exist.
	 * @see NetworkController
	 */
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

	/**
	 * Prompts the network controller to ask the server to delete the edge
	 * between the two input nodes
	 * @param parentName - The name of the edge origin.
	 * @param childName - The name of the edge destination.
	 * @return <code>true</code> if the edge was deleted, <code>
	 * false</code> if it didn't even exist.
	 * @see NetworkController
	 */
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

	/**
	 * Prompts the network controller to ask the server to add the edge
	 * between the two input nodes
	 * @param parentName - The name of the edge origin.
	 * @param childName - The name of the edge destination.
	 * @return <code>true</code> if the edge was deleted, <code>
	 * false</code> if it didn't even exist.
	 * @see NetworkController
	 */
	public boolean addEdge(String parentName, String childName) {
		// Verify that nodes exists
		if(!model.hasNodeNamed(parentName) || !model.hasNodeNamed(childName)){
			return false;
		}
		
		// Tell the network to add the new edge
		network.addEdge(parentName, childName);
		return true;
		
	}
	
	/**
	 * @return The Controller's Network Controller.
	 * @see NetworkController
	 */
	public NetworkController getNetwork(){
		return network;
	}
	
	/**
	 * Prompts the network controller to ask the server for newer data.
	 * @see NetworkController
	 */	
	public void updateData(){
		// TODO: Switch this to asynchronous updateStream()
		network.updateSnapshot();
	}

	/**
	 * Prompts the network controller to ask the server for newer data.
	 * @return Boolean representing the program state.
	 */
	public boolean isRunning(){
		return running;
	}

	/**
	 * Sets the current program state. Intended to be set by the view only,
	 * but any critical component has access. 
	 * @param state - Represents the Controller state.
	 */
	public void setRunning(boolean state) {
		running = state;
	}
	

	/*
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
	
	*/
}
