package userInterface;

import javax.swing.JFrame;

public class NewNodeDialog {
	private JFrame mainFrame;

	public NewNodeDialog() {
		initialize();
	}
	
	public void initialize(){
		mainFrame = new JFrame();
		mainFrame.setResizable(true);
		mainFrame.setTitle("Create New Node");
		mainFrame.setBounds(100, 100, 300, 160);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
