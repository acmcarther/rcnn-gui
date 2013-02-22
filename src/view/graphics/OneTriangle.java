package view.graphics;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;


// This is a dummy openGL file to test the renderer

public class OneTriangle {
    public static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }

    public static void render( GL2 gl2, int width, int height ) {
    	/*
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

        // draw a triangle filling the window
        gl2.glLoadIdentity();
        gl2.glColor3f(1.0f, 0.0f, 0.0f);
        gl2.glRectf(-25.0f, 25.0f, 25.0f, -25.0f);
        gl2.glEnd();
        */
    }
}