package controller.activationmap;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import model.RCNN_Model;
import resources.datatypes.Node;
import resources.datatypes.NodeData;
import controller.oscilloscope.OscilloGFX;

public class ActivMapHandler implements GLEventListener {

	private RCNN_Model model;
	private int slideLevel = 0;

	public ActivMapHandler(RCNN_Model model) {
		this.model = model;
	}
	
	public void dataNotify(){
    	slideLevel++;
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

		// Initialize activation map
        ActivMapGFX.initialize(gl2);
        
        // Draw bottom of the graph
        ActivMapGFX.drawGraphBottom(gl2, width, height);
        
        // Validate slide level
        if(slideLevel > model.getDataResolution()/16){
        	slideLevel = 0;
        }
        
        // Loop to draw all of the data points
        while(dataSet.hasNext()){
        	
        	// Grab the latest data set that we're doing
        	tempEntry = dataSet.next();
        
            // Print the name of the node
        	ActivMapGFX.drawTextInfo(gl2, width, height, addHeight, tempEntry);
	
	        // Plot all of the maxima points
        	ActivMapGFX.drawMaximas(gl2, width, height, addHeight, tempEntry.getValue(), model.getDataResolution());

	        // Increment the height
	        addHeight += height + 10;
	       
        }
        
        // Draw some grid lines
        ActivMapGFX.drawGridLines(gl2, width, height, addHeight, model.getDataResolution(), slideLevel);
        
        // Now draw the rest of the box around it
        ActivMapGFX.drawRemainingBox(gl2, width, height, width, addHeight);
        
        // once all oscilloscopes are drawn, scale the entire thing by the current vertical height
        // coordinate system origin at lower left with width and height same as the window
        ActivMapGFX.scaleViewport(gl2, width, height, width, addHeight);
        
        ActivMapGFX.flush(gl2);
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
