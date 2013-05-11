package controller.forcegraph;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import resources.datatypes.PhysicsNode;

import model.RCNN_Model;

public class ForceGraphHandler implements GLEventListener {
	
	// Class Variables
	RCNN_Model model;
	ForceGraphPHYS graphPhysics;
	ForceGraphGFX graphGFX;
	
	public ForceGraphHandler(RCNN_Model model){
		this.model = model;
		this.graphPhysics = new ForceGraphPHYS(model);
	}

	@Override
	public void display(GLAutoDrawable glAutoDrawable) {
		// Function Variables 
		GL2 gl2 = glAutoDrawable.getGL().getGL2();
		int width = glAutoDrawable.getWidth();
		int height = glAutoDrawable.getHeight();
		PhysicsNode[] nodeList = graphPhysics.getPhysicsNodes();
		
		// Initialize OpenGL
		graphGFX.initialize();
		
		// Draw Map Grid
		
		
		// Draw all of the edges
		
		
		// Draw all of the nodes
		for(int index = 0; index < nodeList.length; index++){
			
			
			
		}
		
		
		// Scale the viewport
		
		// Flush the buffer
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	// TODO: Extract this function into an interface
	public void dataNotify() {
		// Request a physics recalculation
		graphPhysics.updateGraph(model.getNodeList());
		graphPhysics.runPhysicsTick();
	}

}
