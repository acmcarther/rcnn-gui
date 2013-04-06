package controller.activationmap;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import resources.datatypes.Node;
import resources.datatypes.NodeData;

import com.jogamp.opengl.util.gl2.GLUT;

public class ActivMapGFX {
    final static float graphWidthOffset = 100;
    final static float graphBackOffset = 30;
	
    public static void initialize(GL2 gl2){
    	
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
        gl2.glLoadIdentity();

    }
    
   public static void drawTextInfo(GL2 gl2, int width, int height, int addHeight, Entry<String,NodeData> nodeEntry){
    	
    	// TODO: Rework Substring calculation
    	
    	// Initialize glut
        GLUT glut = new GLUT();
        
    	// Back up gl2's settings
        gl2.glPushMatrix();
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
        
    	// Set text color
        gl2.glColor3f( 0,1, 0 );

        // Position node name
        gl2.glRasterPos2i(5,addHeight + height/2);
    	// Draw the text
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, nodeEntry.getKey().substring(17));
        
        // Load gl2's settings
        gl2.glPopAttrib();
        gl2.glPopMatrix();
        
    }
   
	public static void drawGridLines(GL2 gl2, int width, int height, int addHeight, int dataLogSize, int slideLevel){
        final float graphLength = (width-graphBackOffset-graphWidthOffset-5);
        final float lineSeparation = graphLength/16;
        int widthLoc;
        
    	// Back up gl2's settings
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
        
        gl2.glBegin( GL.GL_LINES );
        gl2.glColor3f( 0, 0.2f, 0 );
        
        gl2.glVertex2f( graphWidthOffset, addHeight + height/2 );
        gl2.glVertex2f( width - 5 - graphBackOffset, addHeight + height/2 );
        
        for(int line=1;line <= 16;line++){
        	// TODO: Figure out why the lines are all jumpy
        	widthLoc = (int)(slideLevel*(graphLength)/dataLogSize);
	        gl2.glVertex2f( graphWidthOffset - widthLoc + line*lineSeparation, 5 );
	        gl2.glVertex2f( graphWidthOffset - widthLoc + line*lineSeparation, addHeight + height - 5 );
        }
        gl2.glEnd(); 
        
        // Load gl2's settings
        gl2.glPopAttrib();

	}
	
	public static void scaleViewport(GL2 gl2, int width, int height, int totalWidth, int totalHeight){
		
    	// Back up gl2's settings
        gl2.glPushMatrix();
        
        // Set matrix model
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();
        
        // initialize glu
        GLU glu = new GLU();

        glu.gluOrtho2D( 0.0f, totalWidth, 0.0f, totalHeight);

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        // Set viewport
        gl2.glViewport( 0, 0, width, height );
        
        // Load gl2's settings
        gl2.glPopMatrix();
        
	}
	
	public static void flush(GL2 gl2){
		gl2.glFinish();
	}

	public static void drawMaximas(GL2 gl2, int width, int height,
		int addHeight, NodeData nodeData, int dataResolution) {
        float plotCount =0;
        int xPlotPoint,yPlotPoint;
        float activationLevel;
        Iterator<Node> nodeDataIterator = nodeData.iterator();
        Node currentNode;
		
    	// Back up gl2's settings
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
		
    	// start drawing to line strip
		gl2.glBegin(GL.GL_POINTS);
    
    	// set the color to something fancy
		gl2.glColor3f( 1,1, 0 );
    
	    while(nodeDataIterator.hasNext()){
	    	currentNode = nodeDataIterator.next();
	    	if( currentNode.getMaxima() ){
	    		xPlotPoint = (int) (graphWidthOffset + (plotCount*(((float) (width-graphWidthOffset-5-graphBackOffset))/((float)dataResolution))));
	    		// TODO: fix this so huge points don't go somewhere bad, as well as tiny points
		    	yPlotPoint = (int) (5 + addHeight + (height/2));
	    		gl2.glVertex2f(xPlotPoint,yPlotPoint);
	    	
	    	}
	    	plotCount++;
	    }
	    
	    // end this thing
	    gl2.glEnd();
	    
        // Load gl2's settings
        gl2.glPopAttrib();
		
	}
	
	public static void drawGraphBottom(GL2 gl2, int width, int height) {
		
    	// Back up gl2's settings
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
        
    	// Set our color to white 
        gl2.glColor3f( 1, 1, 1 );
        
    	// First set our drawing to line strips
        gl2.glBegin( GL.GL_LINE_STRIP );
		
    	// draw point1 near the bottom left (offset for the name and stuff) 
        gl2.glVertex2f( graphWidthOffset, 5 );
        // draw point2 near the bottom right
        gl2.glVertex2f( width - 5 - graphBackOffset, 5 );
        
        // all done with this fancy box
        gl2.glEnd();
        
        // Load gl2's settings
        gl2.glPopAttrib();
		
	}

	public static void drawRemainingBox(GL2 gl2, int width, int height,
			int width2, int addHeight) {
		
    	// Back up gl2's settings
        gl2.glPushAttrib(GL.GL_COLOR_BUFFER_BIT);
        
    	// Set our color to white 
        gl2.glColor3f( 1, 1, 1 );
        
    	// First set our drawing to line strips
        gl2.glBegin( GL.GL_LINE_STRIP );
        
    	// draw point2 near the bottom right
        gl2.glVertex2f( width - 5 - graphBackOffset, 5 );
    	// draw point3 near top right
        gl2.glVertex2f( width - 5 - graphBackOffset, addHeight - 5  );
    	// draw point4 near top left
        gl2.glVertex2f( graphWidthOffset , addHeight - 5 );
    	// back to the point1
        gl2.glVertex2f( graphWidthOffset, 5 );
		
        // all done with this fancy box
        gl2.glEnd();
        
        // Load gl2's settings
        gl2.glPopAttrib();
	}
}
