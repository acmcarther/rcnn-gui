package view.dialogs;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import resources.datatypes.ControlData;
import view.subviews.ViewGui;

public class SettingsDialog {
	//private RCNN_Controller controller;
	private JDialog mainDialog;
	private JTextField textField;
	private ViewGui parent;

	public SettingsDialog(ViewGui parent) {
		this.parent = parent;
		initialize(parent.getContainer());
	}
	
	public void initialize(Container container){
		mainDialog = new JDialog((Frame) container,"Network Settings", true);
		mainDialog.setType(Type.POPUP);
		mainDialog.setAlwaysOnTop(true);
		mainDialog.setResizable(false);
		mainDialog.setTitle("Change Network Settings");
		mainDialog.setBounds(100, 100, 257, 130);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.getContentPane().setLayout(new BoxLayout(mainDialog.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainDialog.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JButton btnAdd = new JButton("Save");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isValid;
				isValid = parent.setAddress(textField.getText());
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
		
		JLabel lblNodeName = new JLabel("Network IP:");
		lblNodeName.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNodeName);
		
	
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		
	}
	
	public void setVisible(boolean setting){
		mainDialog.setVisible(setting);
	}
}
