package controller.forcegraph;

import java.util.Iterator;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import resources.datatypes.PhysicsNode;
import resources.datatypes.Vector;

import model.RCNN_Model;

public class ForceGraphHandler implements GLEventListener {
	
	// Class Variables
	RCNN_Model model;
	ForceGraphPHYS graphPhysics;
	ForceGraphGFX graphGFX;
	
	public ForceGraphHandler(RCNN_Model model){
		this.model = model;
		this.graphPhysics = new ForceGraphPHYS(model);
		this.graphGFX = new ForceGraphGFX();
	}

	@Override
	public void display(GLAutoDrawable glAutoDrawable) {
		// Function Variables 
		GL2 gl2 = glAutoDrawable.getGL().getGL2();
		int width = glAutoDrawable.getWidth();
		int height = glAutoDrawable.getHeight();
		PhysicsNode[] nodeList = graphPhysics.getPhysicsNodes();
		Iterator<PhysicsNode> childIterator;
		PhysicsNode childNode;
		Vector parentPos, childPos;
		int scaleSize = Math.max(width, height);
		double widthFactor = width/((double)scaleSize);
		double heightFactor = height/((double)scaleSize);
		Vector offsetVector =new Vector(250*widthFactor,250*heightFactor);
		
		// Initialize OpenGL
		graphGFX.initialize(gl2);
		
		// Draw Map Grid
		graphGFX.drawGraphGrid(gl2, width, height);		
	
		// Draw all of the node data
		for(int index = 0; index < nodeList.length; index++){
			
			// Get child list
			childIterator = nodeList[index].getChildNodeIterator();
			
			// Set the parent pos (offset toward the middle of the canvas)
			parentPos = new Vector(nodeList[index].getPos());
			parentPos.addVector(offsetVector);
			
			// Draw all of the edges for the node data
			while(childIterator.hasNext()){
				
				// Get the next child
				childNode = childIterator.next();

				// Set the child pos (offset toward the middle of the canvas)
				childPos = new Vector(childNode.getPos());
				childPos.addVector(offsetVector);
				
				// Draw an arrow between the nodes
				graphGFX.drawArrow(gl2, width, height, parentPos, childPos);
			}
			
		}
		
		// Draw all of the edges
		for(int index = 0; index < nodeList.length; index++){

			// Set the parent pos (offset toward the middle of the canvas)
			parentPos = new Vector(nodeList[index].getPos());
			parentPos.addVector(offsetVector);

			// Draw the node
			graphGFX.drawNode(gl2, width, height, parentPos, nodeList[index].getName(),nodeList[index].getAL());
			
		}
		
		// Scale the viewport
		graphGFX.scaleViewport(gl2, scaleSize, scaleSize);
		
		// Flush the buffer
		graphGFX.flush(gl2);
		
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
