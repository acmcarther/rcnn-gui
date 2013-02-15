package nodeModel;

import userInterface.RCNN_View;

public class RCNN_Model {
	private RCNN_View view;
	private boolean networkActive;

	public void registerView(RCNN_View view) {
		this.view = view;
	}

	public void initialize() {

		// Set network off
		networkActive = false;
		
	}

}
