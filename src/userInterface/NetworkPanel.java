package userInterface;

import javax.swing.JPanel;

public class NetworkPanel implements ExtendedPanel {
	
	JPanel basePanel;
	
	public NetworkPanel(JPanel basePanel) {
		this.basePanel = basePanel;
		initialize();
	}
	
	private void initialize() {
		
	}

	public JPanel getPanel() {
		return basePanel;
	}
}
