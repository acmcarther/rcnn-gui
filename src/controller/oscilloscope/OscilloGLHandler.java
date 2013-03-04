package controller.oscilloscope;

import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import resources.datatypes.NodeData;

import model.RCNN_Model;

import controller.RCNN_Controller;

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
		
		// some init stuff
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
        gl2.glLoadIdentity();
        
        // temp shit here yo
        NodeData temp = model.getTempNodeData();
        int size = temp.getSize();
        float counter = 0;
        int xpos,ypos;
        float value;
        
        // loop to draw all of the oscilloscopes (not yet, we just do one)
        
        // draw the name of the node (this text stuff i cant do yet)
        
        // draw the current value of the node

        // draw a box where the oscilloscope goes
        	// First set our drawing to line strips
        gl2.glBegin( GL.GL_LINE_STRIP );
        	// Set our color to white 
        gl2.glColor3f( 1, 1, 1 );
        	// draw point1 near the top left (offset for the name and stuff) 
        gl2.glVertex2f( 50, 5 );
        	// draw point2 near the top right
        gl2.glVertex2f( width - 5, 5 );
        	// draw point3 near bottom right
        gl2.glVertex2f( width - 5, height - 5 );
        	// draw point4 near bottom left
        gl2.glVertex2f( 50 , height - 5);
        	// back to the point1
        gl2.glVertex2f( 50, 5 );
        	// all done with this fancy box
        gl2.glEnd();
        
        // plot the entire line. Yes, 500 points in this function call. We are boss of this computer.
        Iterator<Float> iterator = temp.iterator();
        
        	// start drawing to line strip
        gl2.glBegin(GL.GL_LINE_STRIP);
        
        	// set the color to something fancy
        gl2.glColor3f( 0, 1, 0 );
        
        while(iterator.hasNext()){
        	value = iterator.next();
        	xpos = (int) (50 + (counter*(((float) (width-55))/((float)size))));
        	// fix this so huge points don't go somewhere bad
        	ypos = (int) (5 + ((value+70)/220)*(height-10));
        	gl2.glVertex2f(xpos,ypos);
        	counter++;
        }
        
        	// end this thing
        gl2.glEnd();
        
        
        // once all oscilloscopes are drawn, scale the entire thing by the current vertical height
	}

}
