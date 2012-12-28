package controller;

import userInterface.RCNN_View;
import nodeModel.RCNN_Model;

public class RCNN_Controller {
	private RCNN_Model model;
	private RCNN_View view;

	public void registerModel(RCNN_Model model) {
		this.model = model;
		
	}

	public void registerView(RCNN_View view) {
		this.view = view;
	}

}
