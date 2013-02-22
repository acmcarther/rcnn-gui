package view.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

import resources.datatypes.Node;

import view.RCNN_View;

import controller.RCNN_Controller;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditNodeDialog {
	private Node editNode;
	private RCNN_Controller controller;
	private JDialog mainDialog;
	private JTextField textField_1;

	public EditNodeDialog(RCNN_View mainFrame, RCNN_Controller controller, Node editNode) {
		this.controller = controller;
		this.editNode = editNode;
		initialize(mainFrame);
	}
	
	public void initialize(JFrame mainFrame){
		mainDialog = new JDialog(mainFrame,"Add New Node", true);
		mainDialog.setType(Type.POPUP);
		mainDialog.setAlwaysOnTop(true);
		mainDialog.setResizable(false);
		mainDialog.setTitle("Create New Node");
		mainDialog.setBounds(100, 100, 257, 130);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.getContentPane().setLayout(new BoxLayout(mainDialog.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainDialog.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isValid;
				isValid = controller.editNodeEvent(editNode);
				if(isValid){
					mainDialog.dispose();
				}
				else{
					JOptionPane.showMessageDialog(mainDialog, "Invalid Data Entered");
				}
			}
		});
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnAdd);
		
		Component horizontalStrut = Box.createHorizontalStrut(105);
		panel.add(horizontalStrut);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainDialog.dispose();
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnCancel);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setLeftComponent(panel_1);
		
		JLabel lblNodeName = new JLabel("Node Name:");
		lblNodeName.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNodeName);
		
		JLabel lblProperName;
		if(editNode == null) {
			lblProperName = new JLabel("INVALID NODE");
		} else {
			lblProperName = new JLabel(editNode.getName());
		}

		panel_1.add(lblProperName);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		
		JLabel lblActivationLevel = new JLabel("Activation Level:");
		lblActivationLevel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblActivationLevel);
		
		textField_1 = new JTextField();
		
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textField_1);
		textField_1.setColumns(3);
	}

	public void setVisible(boolean setting){
		mainDialog.setVisible(setting);
	}
}