package userInterface;

import javax.swing.JPanel;

public class NodePanel implements ExtendedPanel {
	JPanel basePanel;
	
	public NodePanel(JPanel basePanel) {
		this.basePanel = basePanel;
		initialize();
	}
	
	private void initialize() {
		
	}

	public JPanel getPanel() {
		return basePanel;
	}
}
