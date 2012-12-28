package userInterface;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Window.Type;

public class NewNodeDialog {
	private JDialog mainFrame2;
	private JTextField textField;
	private JTextField textField_1;

	public NewNodeDialog(JFrame mainFrame) {
		initialize(mainFrame);
	}
	
	public void initialize(JFrame mainFrame){
		mainFrame2 = new JDialog();
		mainFrame2.setType(Type.POPUP);
		mainFrame2.setAlwaysOnTop(true);
		mainFrame2.setResizable(false);
		mainFrame2.setTitle("Create New Node");
		mainFrame2.setBounds(100, 100, 257, 130);
		mainFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame2.getContentPane().setLayout(new BoxLayout(mainFrame2.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame2.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnAdd);
		
		Component horizontalStrut = Box.createHorizontalStrut(105);
		panel.add(horizontalStrut);
		
		JButton btnCancel = new JButton("Cancel");
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
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(72);
		panel_1.add(horizontalStrut_1);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		
		JLabel lblActivationLevel = new JLabel("Activation Level:");
		lblActivationLevel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblActivationLevel);
		
		textField_1 = new JTextField();
		
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(50);
		panel_2.add(horizontalStrut_2);
	}
	
	public void display(){
		mainFrame2.setVisible(true);
	}
}
