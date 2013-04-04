package controller.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import controller.RCNN_Controller;

public class CloseController implements WindowListener, ActionListener{
	
	private RCNN_Controller controller;

	public CloseController(RCNN_Controller controller){
		this.controller = controller;
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		controller.setRunning(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.setRunning(false);
	}


	public void windowActivated(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
