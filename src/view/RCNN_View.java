package view;

import javax.swing.JOptionPane;

import view.subviews.ViewActivationMap;
import view.subviews.ViewForceGraph;
import view.subviews.ViewGui;
import view.subviews.ViewOscilloscope;
import controller.command.CloseController;
import controller.command.ControlListenerInterface;
import controller.command.NetworkControlHandler;
import model.RCNN_Model;


/** View Component of MVC
 * 
 * @author Alex
 * 
 *
 */
public class RCNN_View  {
	/** Model registered to this view*/
	//private RCNN_Model model;
	
	
	// View Subcomponents
	private ViewGui guiView;
	private ViewActivationMap mapView;
	private ViewOscilloscope oscilloView;
	private ViewForceGraph forceView;
	
	public RCNN_View(RCNN_Model model) {
		guiView = new ViewGui(model);
		mapView = new ViewActivationMap(model, guiView);
		oscilloView = new ViewOscilloscope(model, guiView);
		forceView = new ViewForceGraph(model, guiView);
		//this.model = model;
		
		// Initialize all of the views
		guiView.initialize();
		oscilloView.initialize();
		mapView.initialize();
		guiView.addSubView(oscilloView, "Oscilloscope");
		guiView.addSubView(mapView, "Map (WIP)");
		guiView.addSubView(forceView, "Force Graph");
		
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
	

	public void registerModel(RCNN_Model model) {
		//this.model = model;
	}

	public void registerCloseHandler(CloseController windowCloseHandler) {
		guiView.registerCloseHandler(windowCloseHandler);
	}

	public void registerNewNodeHandler(ControlListenerInterface nodeInputHandler) {
		guiView.registerNewNodeHandler(nodeInputHandler);
		
	}
	
	public void registerDelNodeHandler(ControlListenerInterface nodeInputHandler) {
		guiView.registerDelNodeHandler(nodeInputHandler);
		
	}

	public void registerNetworkControlHandler(
			NetworkControlHandler netControlHandler) {
		guiView.registerNetworkControlHandler(netControlHandler);
		
	}


}
