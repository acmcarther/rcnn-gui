package view;


import javax.media.opengl.awt.GLJPanel;

import javax.swing.JOptionPane;

import controller.RCNN_Controller;
import model.RCNN_Model;


/** View Component of MVC
 * 
 * @author Alex
 * 
 *
 */
public class RCNN_View  {
	/** Model registered to this view*/
	private RCNN_Model model;
	private RCNN_Controller controller;
	
	// View Subcomponents
	private ViewGui guiView;
	private ViewActivationMap mapView;
	private ViewOscilloscope oscilloView;
	
	public RCNN_View(RCNN_Model model) {
		guiView = new ViewGui(model);
		mapView = new ViewActivationMap(model);
		oscilloView = new ViewOscilloscope(model);
		this.model = model;
	}
	
	public void initialize(){
		// Initialize all of the views
		guiView.initialize();
		oscilloView.initialize();
		mapView.initialize();
		guiView.addSubView(oscilloView);
		guiView.addSubView(mapView);
		
		// Add them as observers of the model
		model.addObserver(guiView);
		model.addObserver(mapView);
		model.addObserver(oscilloView);
		
		// Set the GUI to be visible
		guiView.setVisible(true);
	}
	
	// GUI Components
	
	public void showError(String error){
		// TODO: Determine if passing null here is bad
		JOptionPane.showMessageDialog(null, error);
	}
	
	public RCNN_Controller getController(){
		return controller;
	}
	
	public void registerModel(RCNN_Model model) {
		this.model = model;
	}

	public void registerController(RCNN_Controller controller) {
		this.controller = controller;
	}


}
