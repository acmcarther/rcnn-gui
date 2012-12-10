package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JList;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;

import events.*;
import events.buttons.*;

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
		 * Initialize the contents of the configure tab.
		 */
		JPanel pnlConfigure = new JPanel();
		tabbedPane.addTab("Configure Objects", null, pnlConfigure, null);
		pnlConfigure.setLayout(new MigLayout("", "[162px][43px][8px][][][][13px][][][][][grow][][][][][][][][grow][1px][46px][4px][91px]", "[14px][6px][][10px][][6px][][][][][][][][][][][][][][]"));
		
		
		// Build all of the labels
		JLabel lblNewNode = new JLabel("Create New Node");
		JLabel lblNewParents = new JLabel("Parents:");
		JLabel lblNewChildren = new JLabel("Children:");
		JLabel lblActivationLevel = new JLabel("Activation Level:");
		JLabel lblManageNode = new JLabel("Manage Existing Nodes");	
		JLabel lblOldParents = new JLabel("Parents:");
		JLabel lblOldChildren = new JLabel("Children:");
		JLabel lblActivationLevel_1 = new JLabel("Activation Level:");
		pnlConfigure.add(lblActivationLevel_1, "cell 1 15");
		pnlConfigure.add(lblOldChildren, "cell 1 14,growx,aligny center");
		pnlConfigure.add(lblActivationLevel, "cell 1 4");
		pnlConfigure.add(lblOldParents, "cell 1 13,growx,aligny center");
		pnlConfigure.add(lblManageNode, "cell 1 11,alignx left,aligny top");
		pnlConfigure.add(lblNewChildren, "cell 1 3,alignx left,aligny center");
		pnlConfigure.add(lblNewParents, "cell 1 2,alignx left,aligny center");
		pnlConfigure.add(lblNewNode, "cell 1 0 21 1,alignx left,aligny top");

		// Build the node list scroll pane
		JScrollPane scrollPane = new JScrollPane();
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
			// TODO: figure out what type to make this list
			//       -made it string, might revise to node later.
		JList<String> nodeManageList = new JList<String>();
		scrollPane.setViewportView(nodeManageList);
		pnlConfigure.add(scrollPane, "cell 0 0 1 20,grow,aligny center");

		// build all of the text fields
		JTextField txtfldNewParents = new JTextField();
		JTextField txtfldNewChildren = new JTextField();
		JTextField textfldNewActivLevel = new JTextField();
		JTextField txtfldOldChildren = new JTextField();
		JTextField txtfldOldParents = new JTextField();
		JTextField textfldOldActivLevel = new JTextField();
		txtfldNewChildren.setColumns(10);
		txtfldNewParents.setColumns(10);
		textfldNewActivLevel.setColumns(10);
		txtfldOldParents.setColumns(10);
		textfldOldActivLevel.setColumns(10);
		txtfldOldChildren.setColumns(10);	
		pnlConfigure.add(txtfldOldParents, "cell 4 13 19 1,growx");
		pnlConfigure.add(txtfldOldChildren, "cell 4 14 19 1,growx");
		pnlConfigure.add(textfldOldActivLevel, "cell 4 15 5 1,growx");
		pnlConfigure.add(textfldNewActivLevel, "cell 4 4 5 1,growx");
		pnlConfigure.add(txtfldNewParents, "cell 4 2 19 1,growx");
		pnlConfigure.add(txtfldNewChildren, "cell 4 3 19 1,growx");
		
		
		/**
		 * Initialize the buttons and their handlers.
		 */
		// Button: save new node
		JButton btnSaveNew = new JButton("Save Node");
		objectHandler saveNewHandler = new objectHandler(btnSaveNew);
		saveNewHandler.setCommand(new cmdNewNode());
		
		// Button: save pre-existing node
		JButton btnSaveOld = new JButton("Save Node");
		objectHandler saveOldHandler = new objectHandler(btnSaveOld);
		saveOldHandler.setCommand(new cmdChgNode());
		
		// Button: delete node
		JButton btnDeleteNode = new JButton("Delete Node");
		objectHandler deleteBtnHandler = new objectHandler(btnDeleteNode);
		deleteBtnHandler.setCommand(new cmdDeleteNode());
		
		// Button: run simulation
		JButton btnStart = new JButton("");
		objectHandler startBtnHandler = new objectHandler(btnStart);
		startBtnHandler.setCommand(new cmdRunSim());
		
		// Button: pause simulation
		JButton btnPause = new JButton("");
		objectHandler pauseBtnHandler = new objectHandler(btnPause);
		pauseBtnHandler.setCommand(new cmdPauseSim());
		
		// Button: stop simulation
		JButton btnStop = new JButton("");
		objectHandler stopBtnHandler = new objectHandler(btnStop);
		stopBtnHandler.setCommand(new cmdStopSim());
	
		// Buttons: place in window
		pnlConfigure.add(btnSaveNew, "cell 1 7,growx,aligny top");
		pnlConfigure.add(btnSaveOld, "cell 1 19,growx,aligny top");
		pnlConfigure.add(btnDeleteNode, "cell 4 19 6 1,growx,aligny top");
		
		/**
		 * Initialize the contents of the Network tab.
		 */
		JPanel pnlNodeOverview = new JPanel();
		tabbedPane.addTab("Node Overview", null, pnlNodeOverview, null);
		JPanel pnlNetworkSettings = new JPanel();
		tabbedPane.addTab("Network Settings", null, pnlNetworkSettings, null);
		
		/**
		 * Initialize the contents of the Node tab.
		 */
		
		/**
		 * Initialize the contents of the toolbar.
		 */
		JToolBar toolBar = new JToolBar();
		toolBar.setEnabled(false);
		toolBar.setFloatable(false);
		splitPane.setLeftComponent(toolBar);
		
		btnStart.setIcon(new ImageIcon(UserInterface.class.getResource("/icons/start.png")));
		btnStop.setIcon(new ImageIcon(UserInterface.class.getResource("/icons/stop.png")));
		btnPause.setIcon(new ImageIcon(UserInterface.class.getResource("/icons/pause.png")));

		toolBar.add(btnStart);
		toolBar.add(btnPause);
		toolBar.add(btnStop);
		
		/**
		 * Initialize the contents of the menubar.
		 */
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewConfig = new JMenuItem("New Config");
		JMenuItem mntmSaveConfig = new JMenuItem("Save Config");
		JMenuItem mntmLoadConfig = new JMenuItem("Load Config");
		mnFile.add(mntmNewConfig);
		mnFile.add(mntmSaveConfig);
		mnFile.add(mntmLoadConfig);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnEdit.add(mntmSettings);
		
		// Display the window
		mainFrame.setVisible(true);

		
		// Menu exit handler
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Sloppy way to ensure that simulation is stopped
				new cmdStopSim().execute();
				
				// Close the main window
				mainFrame.dispose();
                System.exit( 0 );
			}
		});
		
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
