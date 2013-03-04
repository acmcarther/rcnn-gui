package controller.oscilloscope;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import resources.datatypes.NodeData;

import model.RCNN_Model;


public class OscilloGLHandler {
	
	RCNN_Model model;


	public OscilloGLHandler(RCNN_Model model) {
		this.model = model;
	}

	public void setup(GL2 gl2, int width, int height) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );	
	}

	public void render(GL2 gl2, int width, int height) {
		        
        // Graph constants (Tweak at your own peril)
        final int graphWidthOffset = 100;
        final float minValue = -90.0f;
        final float maxValue = 150.0f;
        final float graphRange = maxValue - minValue;
		
        // Declare variables
        Iterator<Entry<String, NodeData>> dataSet = model.getNodeMap().entrySet().iterator();
        Entry<String,NodeData> tempEntry;
        int dataLogSize;
        float plotCount;
        int xPlotPoint,yPlotPoint;
        float activationLevel;
        int addHeight = 0;
        GLUT glut = new GLUT();
        

        
		// Initialize gl2
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
        gl2.glLoadIdentity();
        
        // loop to draw all of the oscilloscopes (not yet, we just do one) (fuck it we'll do it live)
        while(dataSet.hasNext()){
        	
        	// Grab the latest data set that we're doing
        	tempEntry = dataSet.next();
        	dataLogSize = tempEntry.getValue().getSize();
            plotCount = 0;
        
	        // draw the name of the node 
            // TODO: Read the substring in a smarter way. This wont work forever.
            	// Save our current rendering stuff
            gl2.glPushMatrix();
            	// Decide on the point to draw the node name at
            gl2.glRasterPos2i(5,addHeight + height/2);
        		// Draw the text
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, tempEntry.getKey().substring(17));

	        
	        // draw the current value of the node
            	// Decide on the point to draw the node value at
            gl2.glRasterPos2i(5,addHeight + height/2 - height/4);
            	// Draw the text
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, Float.toString(tempEntry.getValue().peekLast()));
            
            
            // Load our old rendering stuff
            gl2.glPopMatrix();
	
	        // draw a box where the oscilloscope goes
	        	// First set our drawing to line strips
	        gl2.glBegin( GL.GL_LINE_STRIP );
	        	// Set our color to white 
	        gl2.glColor3f( 1, 1, 1 );
	        	// draw point1 near the bottom left (offset for the name and stuff) 
	        gl2.glVertex2f( graphWidthOffset, 5 + addHeight );
	        	// draw point2 near the bottom right
	        gl2.glVertex2f( width - 5, 5 + addHeight );
	        	// draw point3 near top right
	        gl2.glVertex2f( width - 5, addHeight + height - 5 );
	        	// draw point4 near top left
	        gl2.glVertex2f( graphWidthOffset , addHeight + height - 5);
	        	// back to the point1
	        gl2.glVertex2f( graphWidthOffset, 5 + addHeight );
	        	// all done with this fancy box
	        gl2.glEnd();
	        
	        // plot the entire line. Yes, 500 points in this function call. We are boss of this computer.
	        // In fact, we are the boss of all the system resources. 
	        Iterator<Float> iterator = tempEntry.getValue().iterator();
	        
	        	// start drawing to line strip
	        gl2.glBegin(GL.GL_LINE_STRIP);
	        
	        	// set the color to something fancy
	        gl2.glColor3f( 0, 1, 0 );
	        
	        while(iterator.hasNext()){
	        	// TODO: Something here needs to be synchronized, pulled a
	        	/* Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
	        	at java.util.ArrayDeque$DeqIterator.next(Unknown Source)
	        	*/
	        	// In particular, it happens when you resize the window.
	        	activationLevel = iterator.next();
	        	xPlotPoint = (int) (graphWidthOffset + (plotCount*(((float) (width-graphWidthOffset-5))/((float)dataLogSize))));
	        	// TODO: fix this so huge points don't go somewhere bad, as well as tiny points
	        	yPlotPoint = (int) (-5 + addHeight + ((activationLevel-minValue)/graphRange)*(height-10));
	        	gl2.glVertex2f(xPlotPoint,yPlotPoint);
	        	
	        	plotCount++;
	        }
	        
	        	// end this thing
	        gl2.glEnd();
	        
	        // Increment the height
	        addHeight += height + 10;
	       
        }
        
        // once all oscilloscopes are drawn, scale the entire thing by the current vertical height
        // coordinate system origin at lower left with width and height same as the window
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, addHeight );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
	}

}
