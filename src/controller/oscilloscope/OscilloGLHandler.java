package controller.oscilloscope;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.media.opengl.GL2;

import resources.datatypes.NodeData;
import view.graphics.OscilloGFX;

import model.RCNN_Model;


public class OscilloGLHandler {
	
	RCNN_Model model;


	public OscilloGLHandler(RCNN_Model model) {
		this.model = model;
	}

	public void setup(GL2 gl2, int width, int height) {
		OscilloGFX.scaleViewport(gl2, width, height, width, height);	
	}

	public void render(GL2 gl2, int width, int height, int slideLevel) {
		
        // Declare variables
        Iterator<Entry<String, NodeData>> dataSet = model.getNodeMap().entrySet().iterator();
        Entry<String,NodeData> tempEntry;
        int addHeight = 0;

		// Initialize oscilloscope
        OscilloGFX.initialize(gl2);
        
        // loop to draw all of the oscilloscopes (not yet, we just do one) (fuck it we'll do it live)
        while(dataSet.hasNext()){
        	
        	// Grab the latest data set that we're doing
        	tempEntry = dataSet.next();
        
            // Print all of the text on the oscilloscope
            OscilloGFX.drawTextInfo(gl2, width, height, addHeight, tempEntry);
	
            // Draw background box
            OscilloGFX.drawBGBox(gl2, width, height, addHeight);
            
	        // Draw the midpoint lines
	        OscilloGFX.drawGridLines(gl2, width, height, addHeight);
	        
	        // Draw outline
	        OscilloGFX.drawBoxOutline(gl2, width, height, addHeight);
	
	        // Draw the line graph
	        OscilloGFX.drawLineGraph(gl2, width, height, addHeight, tempEntry.getValue().iterator(), tempEntry.getValue().getSize());

	        // Increment the height
	        addHeight += height + 10;
	       
        }
        
        // once all oscilloscopes are drawn, scale the entire thing by the current vertical height
        // coordinate system origin at lower left with width and height same as the window
        OscilloGFX.scaleViewport(gl2, width, height, width, addHeight);
	}

}
