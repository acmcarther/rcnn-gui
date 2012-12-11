package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;

import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;

import controller.cmdChgNode;
import controller.cmdDeleteNode;
import controller.cmdNewNode;
import controller.cmdResetVisual;
import controller.cmdStopSim;
import controller.objectHandler;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		mainFrame.setBounds(100, 100, 550, 478);
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
		
		// TODO: Put this panel back into its own class once finished configuring it
		//ExtendedPanel nodePanel = new NodePanel(new JPanel());
		//tabbedPane.addTab("Node Overview", null, nodePanel.getPanel(), null);
		JPanel basePanel = new JPanel();
		tabbedPane.addTab("Node Overview", null, basePanel, null);
		basePanel.setLayout(new MigLayout("", "[grow][][][][][][][][]", "[grow][][][][][][][][][][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		basePanel.add(scrollPane, "cell 0 0 9 10,grow");
		
		String[] columnNames = {"Node","Activation Level"};
		String[][] data = {{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"},{"1","2"}};
		JTable tblNodeData = new JTable(data, columnNames);
		TableColumn column = null;
		// This loop is pretty hardcore
		for (int i = 0; i < 1; i++) {
		    column = tblNodeData.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(20); 
		    } else {
		        column.setPreferredWidth(100);
		    }
		}
		tblNodeData.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblNodeData);
		
		JButton btnRebootVisualization = new JButton("Reboot Visualization");
		objectHandler rstrtHandler = new objectHandler(btnRebootVisualization);
		rstrtHandler.setCommand(new cmdResetVisual());
		basePanel.add(btnRebootVisualization, "cell 0 10");
		
		/**
		 * Initialize the contents of the Network tab.
		 */
		// TODO: Put this panel back into its own class once finished configuring it
		JPanel basePanel2 = new JPanel();
		//ExtendedPanel networkPanel = new NetworkPanel(panel);
		basePanel2.setLayout(new MigLayout("", "[grow][][][][][][][][][][][]", "[grow][][][][][][][][][][]"));
		tabbedPane.addTab("Network Settings", null, basePanel2, null);
		
		JLabel lblDidntGetTo = new JLabel("didnt get to this, but it contains stuff like ip and port to connect to,");
		basePanel2.add(lblDidntGetTo, "cell 2 4");
		
		JLabel lblAsWellAs = new JLabel("as well as refresh rate, ect");
		basePanel2.add(lblAsWellAs, "cell 2 5");
		
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
