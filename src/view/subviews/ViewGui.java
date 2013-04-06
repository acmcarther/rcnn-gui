package view.subviews;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import controller.command.CloseController;
import controller.command.ControlListenerInterface;
import controller.command.NetworkControlHandler;
import controller.command.NewNodeHandler;

import model.RCNN_Model;
import net.miginfocom.swing.MigLayout;

import resources.datatypes.ControlData;
import resources.datatypes.Edge;
import resources.datatypes.Node;
import view.dialogs.NewNodeDialog;

public class ViewGui implements SubViewInterface, Observer {
	
	// Static settings
	static int updateInterval = 200;
	
	// Persistent GUI Components
	
	ControlListenerInterface newNodeHandler;
	ControlListenerInterface delNodeHandler;	
	ControlListenerInterface netControlHandler;
	
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
	
	JTabbedPane tpDisplays = new JTabbedPane(JTabbedPane.TOP);

	// TODO: If we end up not needing this, remove it.
	RCNN_Model model;
	
	int updateTick;
	
	public ViewGui(RCNN_Model model){
		this.model = model;
	}

	// Set main window properties
	public void initialize(){
		
		// Prep to refresh on first update
		updateTick = updateInterval;
		
		// Build main frame
		main.setResizable(true);
		main.setTitle("RCNN GUI");
		main.setBounds(100, 100, 750, 500);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(), BoxLayout.X_AXIS));
		main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
        	UIManager.setLookAndFeel ("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

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
		
		// Assemble
		main.getContentPane().add(spTools);
		spTools.setLeftComponent(pnlToolbar);
		spTools.setRightComponent(pnlMain);
		spData.setLeftComponent(pnlLists);
		spData.setRightComponent(tpDisplays);

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
		
		
		// TODO: Action Listeners
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(netControlHandler != null){
					netControlHandler.execute(null);
				}
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

	public void addSubView(SubViewInterface subView, String windowTitle) {
		// Add the subview into the tab group
		tpDisplays.addTab(windowTitle, null, subView.getContainer(), null);
	}
	
	public void promptNewNode(){
		 NewNodeDialog dialog = new NewNodeDialog(this);
		 dialog.setVisible(true);
	}
	public void promptEditNode(){
		//EventQueue.invokeLater((Runnable) new EditNodeDialog(this, controller, lstNodes.getSelectedValue()));
	}
	public void promptDeleteNode(){
		// TODO: Make this work when richard makes it work
		if(delNodeHandler != null){
			delNodeHandler.execute(new ControlData(getSelectedNode().getName()));
		}
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

	public void update(Observable o, final Object arg) {
		
		// We got an update, increment counter
		updateTick++;
		
		// If we've updated updateInterval times, update the lists
		if(updateTick > updateInterval){
			// Refresh the node list
			lstNodes.setModel(new AbstractListModel<Node>() {
				Node[] nodeList = ((RCNN_Model) arg).getNodeList();
				public int getSize() {
					return nodeList.length;
				}
				public Node getElementAt(int index) {
					return nodeList[index];
				}
				
			});
		
			// Set the counter back to zero
			updateTick = 0;
		}
	}

	public Container getContainer() {
		return main;
	}

	public void registerCloseHandler(CloseController windowCloseHandler) {
		main.addWindowListener(windowCloseHandler);
	}

	public void registerNewNodeHandler(ControlListenerInterface nodeInputHandler) {
		newNodeHandler = nodeInputHandler;
		
	}
	
	public void registerDelNodeHandler(ControlListenerInterface nodeInputHandler) {
		delNodeHandler = nodeInputHandler;
		
	}


	public void registerNetworkControlHandler(
			ControlListenerInterface netControlHandler) {
		this.netControlHandler = netControlHandler;
		
	}
	
	public boolean addNode(ControlData controlData) {
		
		if(newNodeHandler != null){
			return newNodeHandler.execute(controlData);
		}
		return true;
	}

	@Override
	public boolean hasActiveSubView(SubViewInterface childView) {	
		if(childView.getContainer() == tpDisplays.getSelectedComponent()){
			return true;
		}
		return false;
	}


	
}
