package view.subviews;

import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;

import controller.activationmap.ActivMapHandler;


import model.RCNN_Model;

public class ViewActivationMap implements SubViewInterface, Observer {

	// TODO: If we end up not needing this, remove it.
	RCNN_Model model;
	SubViewInterface parentView;
	GLJPanel amapCanvas;
	ActivMapHandler amap;
	
	public ViewActivationMap(RCNN_Model model, SubViewInterface parentView){
		this.model = model;
		this.parentView = parentView;
		amap = new ActivMapHandler(model);
	}

	public void initialize() {
		GLProfile glProfile = GLProfile.getDefault();
		GLCapabilities glCapabilities = new GLCapabilities( glProfile );
		amapCanvas = new GLJPanel( glCapabilities );
		amapCanvas.addGLEventListener(amap);
	}

	public void addSubView(SubViewInterface subView, String windowTitle) {
		// Cannot add subview to this view
		throw new UnsupportedOperationException();
	}

	public void update(Observable o, Object arg) {
		if(parentView.hasActiveSubView(this)){
			amap.dataNotify();
			amapCanvas.display();
		}
		
	}

	public Container getContainer() {
		// TODO Auto-generated method stub
		return amapCanvas;
	}

	@Override
	public boolean hasActiveSubView(SubViewInterface childView) {
		// Cannot add subview to this view
		throw new UnsupportedOperationException();
	}

}
