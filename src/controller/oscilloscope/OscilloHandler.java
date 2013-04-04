package controller.oscilloscope;


import java.util.Iterator;
import java.util.Map.Entry;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import resources.datatypes.NodeData;

import model.RCNN_Model;

public class OscilloHandler implements GLEventListener {
	
	private RCNN_Model model;

	public OscilloHandler(RCNN_Model model) {
		this.model = model;
	}

    // GLEventListener function calls
	public void display(GLAutoDrawable glAutoDrawable) {
        // Declare variables
		GL2 gl2 = glAutoDrawable.getGL().getGL2();
		int width = glAutoDrawable.getWidth();
		int height = glAutoDrawable.getHeight();
        Iterator<Entry<String, NodeData>> dataSet = model.getNodeMap().entrySet().iterator();
        Entry<String,NodeData> tempEntry;
        int addHeight = 0;

		// Initialize oscilloscope
        OscilloGFX.initialize(gl2, model.getDataResolution());
        
        // loop to draw all of the oscilloscopes
        while(dataSet.hasNext()){
        	
        	// Grab the latest data set that we're doing
        	tempEntry = dataSet.next();
        
            // Print all of the text on the oscilloscope
            OscilloGFX.drawTextInfo(gl2, width, height, addHeight, tempEntry);
	
            // Draw background box
            OscilloGFX.drawBGBox(gl2, width, height, addHeight);
            
	        // Draw the midpoint lines
	        OscilloGFX.drawGridLines(gl2, width, height, addHeight, model.getDataResolution());
	        
	        // Draw outline
	        OscilloGFX.drawBoxOutline(gl2, width, height, addHeight);
	
	        // Draw the line graph
	        OscilloGFX.drawLineGraph(gl2, width, height, addHeight, tempEntry.getValue(), model.getDataResolution());

	        // Increment the height
	        addHeight += height + 10;
	       
        }
        
        // once all oscilloscopes are drawn, scale the entire thing by the current vertical height
        // coordinate system origin at lower left with width and height same as the window
        OscilloGFX.scaleViewport(gl2, width, height, width, addHeight);
        
        OscilloGFX.flush(gl2);
	}

	public void dispose(GLAutoDrawable arg0) {
		// Tie up any lose ends before termination
		
	}

	public void init(GLAutoDrawable glDrawable) {
		// Initialize graphics handler (if needed)
	}

	public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width,
			int height) {
		// I dont even know what this does anymore
		
	}

}
