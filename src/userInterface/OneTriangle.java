package userInterface;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;


// This is a dummy openGL file to test the renderer

public class OneTriangle {
    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }

    protected static void render( GL2 gl2, int width, int height ) {
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

        // draw a triangle filling the window
        gl2.glLoadIdentity();
        gl2.glBegin( GL.GL_LINE_LOOP );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( 0, 0 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( width, 0 );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( width / 2 + width / 4, height - 1 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( width / 2 - width / 4, height - 1);
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_LINE_LOOP );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( width/4, 0 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( 3*width/4, 0 );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( width / 2  + width / 8, height - 1 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( width / 2 - width / 8, height - 1);
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_LINE_LOOP );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( 3*width/8, 0 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( 5*width/8, 0 );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( width / 2  + width / 16, height - 1 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( width / 2 - width / 16, height - 1);
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_LINE_LOOP );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( 1*width/8, 0 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( 7*width/8, 0 );
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex2f( width / 2  + 3*width / 16, height - 1 );
        gl2.glColor3f( 0, 1, 0 );
        gl2.glVertex2f( width / 2 - 3*width / 16, height - 1);
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_LINE_LOOP );
        gl2.glColor3f( 0, 0, 1 );
        gl2.glVertex2f( width/2, 0 );
        gl2.glColor3f( 0, 0, 1 );
        gl2.glVertex2f( width/2, height-1 );
        gl2.glEnd();
    }
}