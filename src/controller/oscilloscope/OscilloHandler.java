package controller.oscilloscope;


import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import model.RCNN_Model;

public class OscilloHandler implements GLEventListener {
	
	private OscilloGLHandler glHandler;

	public OscilloHandler(RCNN_Model model) {
		glHandler = new OscilloGLHandler(model);
	}

    // GLEventListener function calls
	public void display(GLAutoDrawable glAutoDrawable) {
		glHandler.render(glAutoDrawable.getGL().getGL2(), glAutoDrawable.getWidth(), glAutoDrawable.getHeight());
	}

	public void dispose(GLAutoDrawable arg0) {
		// Tie up any lose ends before termination
		
	}

	public void init(GLAutoDrawable glDrawable) {
		// Initialize graphics handler (if needed)
	}

	public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width,
			int height) {
		glHandler.setup(glAutoDrawable.getGL().getGL2(), width, height);
		
	}

}
