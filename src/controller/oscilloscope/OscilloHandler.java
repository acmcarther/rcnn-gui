package controller.oscilloscope;


import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import model.RCNN_Model;

public class OscilloHandler implements GLEventListener {
	
	private OscilloGLHandler glHandler;
	private int mouseLevel;
	private RCNN_Model model;
	private GLJPanel glCanvas;
	//private OscilloKeyHandler keyHandler;
	//private OscilloMouseHandler mouseHandler;
	

	public OscilloHandler(RCNN_Model model) {
		this.model = model;
		glHandler = new OscilloGLHandler(model);
		mouseLevel = 100;
		//keyHandler = new OscilloKeyHandler();
		//mouseHandler = new OscilloMouseHandler();
	}

	
    public void setCanvas(GLJPanel glCanvas) {
    	// I may not need this. Probably will for the mouse event listeners
		this.glCanvas = glCanvas;
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
		System.out.println("reshape");
		//glHandler.setup(glAutoDrawable.getGL().getGL2(), width, height);
		
	}

}
