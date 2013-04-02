package controller.oscilloscope;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.Semaphore;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import model.RCNN_Model;

public class OscilloHandler implements GLEventListener,KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {
	
	private OscilloGLHandler glHandler;
	private int mouseLevel;
	//private OscilloKeyHandler keyHandler;
	//private OscilloMouseHandler mouseHandler;
	

	public OscilloHandler(RCNN_Model model) {
		glHandler = new OscilloGLHandler(model);
		mouseLevel = 100;
		//keyHandler = new OscilloKeyHandler();
		//mouseHandler = new OscilloMouseHandler();
	}

	
    public void setCanvas(GLJPanel glCanvas) {
    	// I may not need this. Probably will for the mouse event listeners
		//this.glCanvas = glCanvas;
	}

    // GLEventListener function calls
	public void display(GLAutoDrawable glAutoDrawable) {
		glHandler.render(glAutoDrawable.getGL().getGL2(), glAutoDrawable.getWidth(), glAutoDrawable.getHeight(),mouseLevel);
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

	// MouseLister & MouseMotionListener function calls
	//
	//
	//
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// KeyListener function calls
	//
	//
	//
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		System.out.println("mouseevent: " + mouseLevel);
		mouseLevel-=event.getWheelRotation()*10;
		if(mouseLevel>100){
			mouseLevel = 100;
		}
		else if(mouseLevel<0){
			mouseLevel = 0;
		}
	}
}
