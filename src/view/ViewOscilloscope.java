package view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.oscilloscope.OscilloHandler;

import model.RCNN_Model;

public class ViewOscilloscope implements SubViewInterface, Observer {

	// TODO: If we end up not needing this, remove it.
	RCNN_Model model;
	
	GLJPanel oscilloCanvas;
	JScrollPane scrlpOscillo;
	OscilloHandler oscilloscope;
	int nodeCount = -1;
	
	public ViewOscilloscope(RCNN_Model model){
		this.model = model;
		oscilloscope = new OscilloHandler(model);
	}
	
	public void initialize() {

		GLProfile glProfile = GLProfile.getDefault();
		GLCapabilities glCapabilities = new GLCapabilities( glProfile );
		oscilloCanvas = new GLJPanel( glCapabilities );
		scrlpOscillo  = new JScrollPane(oscilloCanvas);
		scrlpOscillo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		oscilloCanvas.addGLEventListener(oscilloscope);
	}

	public void addSubView(SubViewInterface subView, String windowTitle) {
		// Cannot add subview to this view
		throw new UnsupportedOperationException();
	}

	public void update(Observable o, Object arg) {
		
		int newNodeCount = ((RCNN_Model) arg).getNodeCount();
		
		// TODO: Get the nodes history from model
		
		if(newNodeCount != nodeCount){
			oscilloCanvas.setPreferredSize(new Dimension(200, ((RCNN_Model) arg).getNodeCount()*100));
			oscilloCanvas.revalidate();
			nodeCount = newNodeCount;
		}
		
		oscilloCanvas.display();
	}

	public Container getContainer() {
		return scrlpOscillo;
	}
	
}
