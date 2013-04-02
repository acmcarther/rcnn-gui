package controller;

import java.util.concurrent.Semaphore;

import javax.media.opengl.awt.GLJPanel;

import controller.networking.NetworkController;
import controller.oscilloscope.OscilloHandler;

import view.RCNN_View;
import model.RCNN_Model;

public class RCNN_Controller {
	private RCNN_Model model;
	private RCNN_View view;
	private NetworkController network;
	private OscilloHandler oscilloscope;
	private final Semaphore dataSemaphore;
	private GLJPanel oscilloCanvas;
	
	public RCNN_Controller(){
		dataSemaphore = new Semaphore(1);
	}

	public void registerModel(RCNN_Model model) {
		this.model = model;
		
	}

	public void registerView(RCNN_View view) {
		this.view = view;
	}

	public boolean addNode(String name, String al) {
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

	public void initialize() {
		// Create the network and define default parameters
		network = new NetworkController(view, model);
		network.setAddress("http://localhost");
		network.setPort("9000");
		
		// Initialize the oscilloscope 
		oscilloscope = new OscilloHandler(model);
	}
	
	public void renderData(){
		//oscilloscope.display(glAutoDrawable)
	}
	
	public OscilloHandler getOscilloscopeHandler(){
		return oscilloscope;
	}
	
	public NetworkController getNetwork(){
		return network;
	}

	public void setCanvas(GLJPanel glCanvas) {
		// TODO Auto-generated method stub
		oscilloCanvas = glCanvas;
		oscilloCanvas.addGLEventListener(oscilloscope);
		oscilloCanvas.addMouseListener(oscilloscope);
		oscilloCanvas.addMouseMotionListener(oscilloscope);
		oscilloCanvas.addKeyListener(oscilloscope);
		oscilloCanvas.addMouseWheelListener(oscilloscope);
	}
	
	// Volatile data access here
	public void updateData(){
		// TODO: Switch this to asynchronous updateStream()

		try {
			dataSemaphore.acquire();
			network.updateSnapshot();
			dataSemaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void forceGLUpdate() {
		// TODO: Figure out why this exception might happen
		try {
			dataSemaphore.acquire();
			oscilloCanvas.display();
			dataSemaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
