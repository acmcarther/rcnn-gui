package userInterface;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;

public class GraphicInterface {
	GLJPanel panel;
	
	public GraphicInterface(GLJPanel basePanel){
		panel = basePanel;
		initialize();
		render();
	}
	
	public void initialize(){
        panel.addGLEventListener( new GLEventListener() {
            
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
	}
	
	public void render(){
		
	}
}
