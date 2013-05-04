package view.subviews;

import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;

import controller.forcegraph.ForceGraphHandler;

import model.RCNN_Model;

public class ViewForceGraph implements SubViewInterface, Observer {
	
	// Class Variables
	//	TODO: Remove model if unneeded
	RCNN_Model model; 
	
	GLJPanel forceCanvas;
	ForceGraphHandler forcegraph;
	SubViewInterface parentView;
	
	public ViewForceGraph(RCNN_Model model, ViewGui guiView) {
		this.model = model;
		this.parentView = guiView;
		forcegraph = new ForceGraphHandler(model);
	}
	
	public void initialize(){
		GLProfile glProfile = GLProfile.getDefault();
		GLCapabilities glCapabilities = new GLCapabilities( glProfile );

		forceCanvas = new GLJPanel( glCapabilities );
		forceCanvas.addGLEventListener(forcegraph);
	}
	public void addSubView(SubViewInterface subView, String windowTitle){
		// Cannot add subview to this view
		throw new UnsupportedOperationException();		
	}
	
	public Container getContainer(){
		return forceCanvas;
	}
	
	public boolean hasActiveSubView(SubViewInterface childView){
		// Cannot add subview to this view
		throw new UnsupportedOperationException();
	}

	public void update(Observable o, Object arg) {
		// If this view is the active view, update it's display
		if(parentView.hasActiveSubView(this)){
			// Prompt the handler to update the display
			// TODO: make this into an event, instead of a call
			forcegraph.dataNotify();
			
			// Force display of the panel
			// TODO: Determine if this is even needed
			forceCanvas.display();
		}
	}
}
