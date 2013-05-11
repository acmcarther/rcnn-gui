package controller.forcegraph;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import resources.datatypes.Vector;

public class ForceGraphGFX {

	public void initialize(GL2 gl2) {
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
        gl2.glLoadIdentity();
	}

	public void drawGraphGrid(GL2 gl2, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void drawArrow(GL2 gl2, int width, int height, Vector parentPos,
			Vector childPos) {
		
		// Back up gl2's settings
	    gl2.glPushMatrix();
	    gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
	    
		// Set our color to red ( or whatever this is )
	    gl2.glColor3f( 1, 0, 0 );
	    
	    // Draw the Line
	    gl2.glBegin(GL2.GL_LINES);
	    gl2.glVertex3d(parentPos.getX(),parentPos.getY(),0);
	    gl2.glVertex3d(childPos.getX(),childPos.getY(),0);
	    
	    // Finished line
	    gl2.glEnd();

	    // Load gl2's settings
	    gl2.glPopAttrib();
	    gl2.glPopMatrix();
		
	}

	public void drawNode(GL2 gl2, int width, int height, Vector parentPos,
			String name) {
		
		// Declare function variables
			// TODO: calibrate these values
		double circleFragment = ((double)2*Math.PI)/((double)30.0);
		int radius = 5; 
		double xPos = parentPos.getX(),yPos = parentPos.getY();
		GLUT glut = new GLUT();
		
    	// Back up gl2's settings
        gl2.glPushMatrix();
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
        
    	// Set our color to blue ( or whatever this is )
        gl2.glColor3f( 1, 1, 0 );
        
        // Draw the circle
        gl2.glBegin(GL2.GL_POLYGON);
        
        // Loop to draw all of the circle points
        for( double angle = 0; angle < 2*Math.PI; angle += circleFragment){
        	gl2.glVertex3d(radius*Math.cos(angle)+xPos, radius*Math.sin(angle)+yPos, 0);
        }     
        
        // Finished circle
        gl2.glEnd();
        
        // Draw node name
        gl2.glRasterPos2i(((int) xPos)+radius+1,(int)yPos);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, name);
        
        // Load gl2's settings
        gl2.glPopAttrib();
        gl2.glPopMatrix();

	}

	public void scaleViewport(GL2 gl2, int width, int height) {
    	// Back up gl2's settings
        gl2.glPushMatrix();
        
        // Set matrix model
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();
        
        // initialize glu
        GLU glu = new GLU();

        glu.gluOrtho2D( 0.0f, 500, 0.0f, 500);

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        // Set viewport
        gl2.glViewport( 0, 0, width, height );
        
        // Load gl2's settings
        gl2.glPopMatrix();
        
	}

	public void flush(GL2 gl2) {
		gl2.glFinish();
	}

}
