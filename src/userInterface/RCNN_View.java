package userInterface;

import java.awt.Component;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import controller.RCNN_Controller;
import net.miginfocom.swing.MigLayout;
import nodeModel.RCNN_Model;
import javax.swing.ListSelectionModel;

import dataTypes.Edge;
import dataTypes.Node;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RCNN_View extends JFrame {
	// MVC Entities
	private RCNN_Model model;
	private RCNN_Controller controller;
	
	// GUI Components
		// Toolbar
	JButton btnStart = new JButton("Start");
	JButton btnPause = new JButton("Pause");
	JButton btnSave = new JButton("Save");
	JButton btnLoad = new JButton("Load");
	JButton btnSettings = new JButton("Settings");
		// Node
	JButton btnNodeAdd = new JButton("+");
	JButton btnNodeEdit = new JButton("*");
	JButton btnNodeDelete = new JButton("-");
		// Edge
	JButton btnEdgeAdd = new JButton("+");
	JButton btnEdgeEdit = new JButton("*");
	JButton btnEdgeDelete = new JButton("-");
		// Lists
	JList<Node> lstNodes = new JList<Node>();
	JList<Edge> lstEdges = new JList<Edge>();
		// OpenGL Component
	GLJPanel glCanvas;
	
	
	public RCNN_View() {
		initialize();
	}
	
	private void initialize(){

		// Set main window properties
		setResizable(true);
		setTitle("RCNN GUI");
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		
		// Build all components
		Component toolPanelSpacer = Box.createHorizontalGlue();
		JSplitPane spTools = new JSplitPane();
		JPanel pnlToolbar = new JPanel();
		JPanel pnlMain = new JPanel();
		JSplitPane spData = new JSplitPane();
		JPanel pnlLists = new JPanel();
		JSeparator nodeSeparator = new JSeparator();
		JSeparator edgeSeparator = new JSeparator();
		JPanel pnlNodeButtons = new JPanel();
		JPanel pnlEdgeButtons = new JPanel();
		JTabbedPane tpDisplays = new JTabbedPane(JTabbedPane.TOP);
		JPanel pnlGraphics = new JPanel();
		JPanel pnlData = new JPanel();
		JScrollPane scrlpNodeList = new JScrollPane(lstNodes);
		JScrollPane scrlpEdgeList = new JScrollPane(lstEdges);
		
		// Set component properties
		spTools.setDividerSize(5);
		spTools.setEnabled(false);
		spTools.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlToolbar.setLayout(new BoxLayout(pnlToolbar, BoxLayout.X_AXIS));
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
		pnlLists.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));
		lstNodes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstEdges.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		pnlNodeButtons.setLayout(new BoxLayout(pnlNodeButtons, BoxLayout.X_AXIS));
		pnlEdgeButtons.setLayout(new BoxLayout(pnlEdgeButtons, BoxLayout.X_AXIS));
		GLProfile glProfile = GLProfile.getDefault();
		GLCapabilities glCapabilities = new GLCapabilities( glProfile );
		glCanvas = new GLJPanel( glCapabilities );
		pnlGraphics.setLayout(new BoxLayout(pnlGraphics, BoxLayout.X_AXIS));
		
		// Build dummy data
		lstNodes.setModel(new AbstractListModel<Node>() {
			Node[] values = new Node[] {new Node("TestNode1", 1.0f),
										new Node("TestNode2", 1.0f)};
			public int getSize() {
				return values.length;
			}
			public Node getElementAt(int index) {
				return values[index];
			}
		});

		lstEdges.setModel(new AbstractListModel<Edge>() {
			Edge[] values = new Edge[] {new Edge("TestNode1", "TestNode2", 1.0f)};
			public int getSize() {
				return values.length;
			}
			public Edge getElementAt(int index) {
				return values[index];
			}
		});
		
		// Assemble
		getContentPane().add(spTools);
		spTools.setLeftComponent(pnlToolbar);
		spTools.setRightComponent(pnlMain);
		spData.setLeftComponent(pnlLists);
		spData.setRightComponent(tpDisplays);
		tpDisplays.addTab("Graphic Display", null, pnlGraphics, null);
		tpDisplays.addTab("Data Display", null, pnlData, null);
		pnlMain.add(spData);
		pnlToolbar.add(btnStart);
		pnlToolbar.add(btnPause);	
		pnlToolbar.add(toolPanelSpacer);
		pnlToolbar.add(btnSave);
		pnlToolbar.add(btnLoad);
		pnlToolbar.add(btnSettings);
		pnlLists.add(scrlpNodeList, "flowy,cell 0 0,grow");
		pnlLists.add(scrlpEdgeList, "flowy,cell 1 0,grow");
		pnlLists.add(nodeSeparator, "cell 0 0,growx");
		pnlLists.add(edgeSeparator, "cell 1 0,growx");
		pnlLists.add(pnlNodeButtons, "cell 0 0,growx");
		pnlLists.add(pnlEdgeButtons, "cell 1 0,growx");

		pnlNodeButtons.add(btnNodeAdd);
		pnlNodeButtons.add(btnNodeEdit);
		pnlNodeButtons.add(btnNodeDelete);
		pnlEdgeButtons.add(btnEdgeAdd);
		pnlEdgeButtons.add(btnEdgeEdit);
		pnlEdgeButtons.add(btnEdgeDelete);
		pnlGraphics.add( glCanvas );
		
		// TODO: All the data stuff for the Data Tab
		
		// TODO: Action Listeners
		btnNodeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promptNewNode();
			}
		});
		btnNodeEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptEditNode();
			}
		});
		btnNodeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptDeleteNode();
			}
		});
		btnEdgeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptNewEdge();
			}
		});
		btnEdgeEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptEditEdge();
			}
		});
		btnEdgeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptDeleteEdge();
			}
		});
	}
	
	public void showError(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public void promptNewNode(){
		NewNodeDialog newNode = new NewNodeDialog(this, controller);
		newNode.display();
	}
	public void promptEditNode(){
		//EditNodeDialog editNode = new EditNodeDialog(this, controller);
		//editNode.display();
	}
	public void promptDeleteNode(){
		controller.deleteNode(getSelectedNode());
	}
	public void promptNewEdge(){
		//NewEdgeDialog newEdge = new NewEdgeDialog(this, controller);
		//newEdge.display();
	}
	public void promptEditEdge(){
		//EditEdgeDialog editEdge = new EditEdgeDialog(this, controller);
		//editEdge.display();
	}
	public void promptDeleteEdge(){
		controller.deleteEdge(getSelectedEdge());
	}
	
	public Node getSelectedNode() {
		return lstNodes.getSelectedValue();
	}
	
	public Edge getSelectedEdge(){
		return lstEdges.getSelectedValue();
	}
	
	public RCNN_Controller getController(){
		return controller;
	}
	
	public void registerModel(RCNN_Model model) {
		this.model = model;
	}

	public void registerController(RCNN_Controller controller) {
		this.controller = controller;
	}

}
