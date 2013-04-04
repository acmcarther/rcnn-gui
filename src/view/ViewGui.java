package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import model.RCNN_Model;
import net.miginfocom.swing.MigLayout;

import resources.datatypes.Edge;
import resources.datatypes.Node;
import view.gui.NewNodeDialog;

public class ViewGui implements SubViewInterface, Observer {
	
	// Persistent GUI Components
		// Main Frame
	JFrame main = new JFrame();
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

	// TODO: If we end up not needing this, remove it.
	RCNN_Model model;
	
	public ViewGui(RCNN_Model model){
		this.model = model;
	}

	// Set main window properties
	public void initialize(){
		main.setResizable(true);
		main.setTitle("RCNN GUI");
		main.setBounds(100, 100, 750, 500);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(), BoxLayout.X_AXIS));
		
		
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
		
		// TODO: generalize these tabs
		/*
		glCanvas = new GLJPanel( glCapabilities );
		JScrollPane scrlpOscillo  = new JScrollPane(glCanvas);
		scrlpOscillo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		controller.setCanvas(glCanvas);
		*/
		
		// Build dummy data
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
		main.getContentPane().add(spTools);
		spTools.setLeftComponent(pnlToolbar);
		spTools.setRightComponent(pnlMain);
		spData.setLeftComponent(pnlLists);
		spData.setRightComponent(tpDisplays);
		/*
		tpDisplays.addTab("Graphic Display", null, scrlpOscillo, null);
		tpDisplays.addTab("Data Display", null, pnlData, null);
		*/
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
		//pnlGraphics.add( glCanvas );
		
		// TODO: All the data stuff for the Data Tab
		
		// TODO: Action Listeners
		main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		main.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	// TODO: Make this into a controller function call
		        //running = false;
		    }
		});
		
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

	@Override
	public void addSubView(SubViewInterface subView) {
		// Unsupported Functionality
		
	}
	
	public void promptNewNode(){
		 //NewNodeDialog dialog = new NewNodeDialog( this,controller);
		 //dialog.setVisible(true);
	}
	public void promptEditNode(){
		//EventQueue.invokeLater((Runnable) new EditNodeDialog(this, controller, lstNodes.getSelectedValue()));
	}
	public void promptDeleteNode(){
		//controller.deleteNode(getSelectedNode().getName());
	}
	public void promptNewEdge(){
		//EventQueue.invokeLater((Runnable) new NewEdgeDialog(this, controller));
	}
	public void promptEditEdge(){
		//EventQueue.invokeLater((Runnable) new EditEdgeDialog(this, controller, lstEdges.getSelectedValue()));
	}
	public void promptDeleteEdge(){
		//controller.deleteEdge(getSelectedEdge());
	}
	
	public Node getSelectedNode() {
		return lstNodes.getSelectedValue();
	}
	
	public Edge getSelectedEdge(){
		return lstEdges.getSelectedValue();
	}
	
	public void setVisible(boolean b) {
		main.setVisible(b);
		
	}
		
	public JFrame getFrame(){
		return main;
	}

	public void updateNodeList(final Node[] nodeList){
		lstNodes.setModel(new AbstractListModel<Node>() {
			public int getSize() {
				return nodeList.length;
			}
			public Node getElementAt(int index) {
				return nodeList[index];
			}
			
		});
	}

	public void update(Observable o, Object arg) {
		// TODO: Get the nodes and edges from model
		
	}
	
}
