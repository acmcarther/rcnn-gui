package view;

import java.util.Observable;
import java.util.Observer;

import model.RCNN_Model;

public class ViewActivationMap implements SubViewInterface, Observer {

	// TODO: If we end up not needing this, remove it.
	RCNN_Model model;
	
	public ViewActivationMap(RCNN_Model model){
		this.model = model;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void addSubView(SubViewInterface subView) {
		// Cannot add subview to this view
		throw new UnsupportedOperationException();
	}

	public void update(Observable o, Object arg) {
		// TODO Request necessary data from the model.
		
	}

}
