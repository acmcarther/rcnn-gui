package graphics;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;


public class NodeDisplayer {

	public NodeDisplayer(){
		initialize();
	}
	
	private void initialize(){
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities( glprofile );
		final GLCanvas glcanvas = new GLCanvas( glcapabilities );

		glcanvas.addGLEventListener( new GLEventListener() {
        
			public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
				OneTriangle.setup( glautodrawable.getGL().getGL2(), width, height );
			}
        
			public void init( GLAutoDrawable glautodrawable ) {
			}
        
			public void dispose( GLAutoDrawable glautodrawable ) {
			}
        
			public void display( GLAutoDrawable glautodrawable ) {
				OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight() );
			}
		});

		final JFrame jframe = new JFrame( "Node Display" ); 
		jframe.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent windowevent ) {
				jframe.dispose();
			}
		});

		jframe.getContentPane().add( glcanvas, BorderLayout.CENTER );
		jframe.setSize( 640, 480 );
		jframe.setVisible( true );
	}
}
