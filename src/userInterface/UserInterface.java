package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;

import javax.swing.JSplitPane;

import controller.cmdStopSim;

//import java.util.Timer;

// TODO: Updating functions, user actions

public class UserInterface {

	private JFrame mainFrame;

	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		
		// TODO: Fix look and feel
		
		// TODO: Compose each tab group together as a single object
		
		// Build the main panel
		mainFrame = new JFrame();
		mainFrame.setResizable(true);
		mainFrame.setTitle("RCNN GUI");
		mainFrame.setBounds(100, 100, 550, 520);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
		
		// Build the split pane
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame.getContentPane().add(splitPane);
		
		// Build the tab pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		/**
		 * Initialize the contents of the Configure tab.
		 */
		ExtendedPanel configPanel = new ConfigPanel(new JPanel());
		tabbedPane.addTab("Configure Objects", null, configPanel.getPanel(), null);

		
		/**
		 * Initialize the contents of the Node tab.
		 */
		
		ExtendedPanel nodePanel = new NodePanel(new JPanel());
		tabbedPane.addTab("Node Overview", null, nodePanel.getPanel(), null);
		
		/**
		 * Initialize the contents of the Network tab.
		 */
		ExtendedPanel networkPanel = new NetworkPanel(new JPanel());
		tabbedPane.addTab("Network Settings", null, networkPanel.getPanel(), null);
		
		/**
		 * Initialize the contents of the extended bar.
		 */
		ExtendedBar toolBar = new ExtendedBar(new JToolBar(), new JMenuBar());
		splitPane.setLeftComponent(toolBar.getToolBar());
		mainFrame.setJMenuBar(toolBar.getMenuBar());

		// Display the window
		mainFrame.setVisible(true);
		
        mainFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
				// Sloppy way to ensure that simulation is stopped
				new cmdStopSim().execute();
				
				// Close the main window
                mainFrame.dispose();
                System.exit( 0 );
            }
        });
        

	}
}
