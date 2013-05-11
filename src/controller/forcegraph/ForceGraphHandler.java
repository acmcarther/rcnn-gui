package controller.forcegraph;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import resources.datatypes.PhysicsNode;

import model.RCNN_Model;

public class ForceGraphHandler implements GLEventListener {
	
	// Class Variables
	RCNN_Model model;
	ForceGraphPHYS graphPhysics;
	
	public ForceGraphHandler(RCNN_Model model){
		this.model = model;
		this.graphPhysics = new ForceGraphPHYS(model);
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		// Function Variables 
		PhysicsNode[] nodeList = graphPhysics.getPhysicsNodes();
		
		System.out.print("{");
		for(int i=0; i < nodeList.length;i++){
			System.out.print("{" + Math.floor(nodeList[i].getPos().getX()) + ", " + Math.floor(nodeList[i].getPos().getY()) + "},");
		}
		System.out.println("}");
		
		// Initialize OpenGL
		
		// Draw all of the nodes
		
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
