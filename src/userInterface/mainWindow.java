package userInterface;
import java.awt.EventQueue;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
import java.util.Timer;

// TODO: Updating functions, user actions

public class mainWindow {

	private JFrame mainFrame;
	private JTextField txtfldNewParents;
	private JTextField txtfldNewChildren;
	private JTextField txtfldOldChildren;
	private JTextField txtfldOldParents;
	private JTextField textfldNewActivLevel;
	private JTextField textfldOldActivLevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/*
		try {
			String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(systemLookAndFeel);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// TODO: Update the node list once per _____
		
	      Timer httpRequestTimer = new Timer();
	      httpRequestTimer.scheduleAtFixedRate(new httpRequestTask(), 0 , 1000);

		
		// TODO: Update the node graphs once per _______
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("RCNN GUI");
		mainFrame.setBounds(100, 100, 500, 440);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame.getContentPane().add(splitPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel pnlConfigure = new JPanel();
		tabbedPane.addTab("Configure Objects", null, pnlConfigure, null);
		
		JLabel lblNewNode = new JLabel("Create New Node");	
		pnlConfigure.setLayout(new MigLayout("", "[162px][43px][8px][][][][13px][][][][][grow][][][][][][][][grow][1px][46px][4px][91px]", "[14px][6px][][10px][][6px][][][][][][][][][][][][][][]"));
		

		// Layout stuff for node management tab
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JList nodeManageList = new JList();
		scrollPane.setViewportView(nodeManageList);
		pnlConfigure.add(scrollPane, "cell 0 0 1 20,grow,aligny center");
		JLabel lblNewParents = new JLabel("Parents:");	
		pnlConfigure.add(lblNewParents, "cell 1 2,alignx left,aligny center");
		
		txtfldNewParents = new JTextField();
		txtfldNewParents.setColumns(10);	
		pnlConfigure.add(txtfldNewParents, "cell 4 2 19 1,growx");
		JLabel lblNewChildren = new JLabel("Children:");
		pnlConfigure.add(lblNewChildren, "cell 1 3,alignx left,aligny center");
		txtfldNewChildren = new JTextField();
		txtfldNewChildren.setColumns(10);
		pnlConfigure.add(txtfldNewChildren, "cell 4 3 19 1,growx");
		
		JLabel lblActivationLevel = new JLabel("Activation Level:");
		pnlConfigure.add(lblActivationLevel, "cell 1 4");
		
		textfldNewActivLevel = new JTextField();
		pnlConfigure.add(textfldNewActivLevel, "cell 4 4 5 1,growx");
		textfldNewActivLevel.setColumns(10);
		
		////////////////////////////////////////////////////////////////////////////////////
		/////// BUTTONS AND BUTTON HANDLERS ////////////////////////////////////////////////
		
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
		
		pnlConfigure.add(btnSaveNew, "cell 1 7,growx,aligny top");
		JLabel lblManageNode = new JLabel("Manage Existing Nodes");	
		pnlConfigure.add(lblManageNode, "cell 1 11,alignx left,aligny top");
		JLabel lblOldParents = new JLabel("Parents:");
		pnlConfigure.add(lblOldParents, "cell 1 13,growx,aligny center");
		txtfldOldParents = new JTextField();
		txtfldOldParents.setColumns(10);
		pnlConfigure.add(txtfldOldParents, "cell 4 13 19 1,growx");
		JLabel lblOldChildren = new JLabel("Children:");
		pnlConfigure.add(lblOldChildren, "cell 1 14,growx,aligny center");
		txtfldOldChildren = new JTextField();
		txtfldOldChildren.setColumns(10);	
		pnlConfigure.add(txtfldOldChildren, "cell 4 14 19 1,growx");
		
		JLabel lblActivationLevel_1 = new JLabel("Activation Level:");
		pnlConfigure.add(lblActivationLevel_1, "cell 1 15");
		
		textfldOldActivLevel = new JTextField();
		pnlConfigure.add(textfldOldActivLevel, "cell 4 15 5 1,growx");
		textfldOldActivLevel.setColumns(10);

		pnlConfigure.add(btnSaveOld, "cell 1 19,growx,aligny top");
		pnlConfigure.add(lblNewNode, "cell 1 0 21 1,alignx left,aligny top");

		pnlConfigure.add(btnDeleteNode, "cell 4 19 6 1,growx,aligny top");
		
		JPanel pnlNetwork = new JPanel();
		tabbedPane.addTab("Network Overview", null, pnlNetwork, null);
		
		JPanel pnlNode = new JPanel();
		tabbedPane.addTab("Node Overview", null, pnlNode, null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setEnabled(false);
		toolBar.setFloatable(false);
		splitPane.setLeftComponent(toolBar);
		
		btnStart.setIcon(new ImageIcon(mainWindow.class.getResource("/icons/start.png")));
		btnStop.setIcon(new ImageIcon(mainWindow.class.getResource("/icons/stop.png")));
		btnPause.setIcon(new ImageIcon(mainWindow.class.getResource("/icons/pause.png")));

		toolBar.add(btnStart);
		toolBar.add(btnPause);
		toolBar.add(btnStop);
		
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewConfig = new JMenuItem("New Config");
		mnFile.add(mntmNewConfig);
		
		JMenuItem mntmSaveConfig = new JMenuItem("Save Config");
		mnFile.add(mntmSaveConfig);
		
		JMenuItem mntmLoadConfig = new JMenuItem("Load Config");
		mnFile.add(mntmLoadConfig);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		
		// Menu exit handler
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Sloppy way to ensure that simulation is stopped
				new cmdStopSim().execute();
				
				// Close the main window & program
				mainFrame.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnEdit.add(mntmSettings);
	}
}
