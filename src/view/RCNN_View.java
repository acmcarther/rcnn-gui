package view;

import java.awt.Component;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import controller.RCNN_Controller;
import model.RCNN_Model;
import net.miginfocom.swing.MigLayout;
import javax.swing.ListSelectionModel;

import resources.datatypes.Edge;
import resources.datatypes.Node;

import view.gui.GraphicInterface;
import view.gui.NewNodeDialog;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

		// OpenGL Components
	GLJPanel glCanvas;
	GraphicInterface graphics;
	boolean running;
	
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

	public boolean isRunning(){
		// TODO: refactor this into the controller
		return running;
	}


}
