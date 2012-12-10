package userInterface;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import controller.cmdChgNode;
import controller.cmdDeleteNode;
import controller.cmdNewNode;
import controller.objectHandler;

public class ConfigPanel implements ExtendedPanel {

	JPanel basePanel;
	
	public ConfigPanel(JPanel basePanel) {
		this.basePanel = basePanel;
		initialize();
	}
	
	protected void initialize() {
		basePanel.setLayout(new MigLayout("", "[162px][43px][8px][][][][13px][][][][][grow][][][][][][][][grow][1px][46px][4px][91px]", "[14px][6px][][10px][][6px][][][][][][][][][][][][][][]"));
		
		// Build all of the labels
		JLabel lblNewNode = new JLabel("Create New Node");
		JLabel lblNewParents = new JLabel("Parents:");
		JLabel lblNewChildren = new JLabel("Children:");
		JLabel lblActivationLevel = new JLabel("Activation Level:");
		JLabel lblManageNode = new JLabel("Manage Existing Nodes");	
		JLabel lblOldParents = new JLabel("Parents:");
		JLabel lblOldChildren = new JLabel("Children:");
		JLabel lblActivationLevel_1 = new JLabel("Activation Level:");
		basePanel.add(lblActivationLevel_1, "cell 1 15");
		basePanel.add(lblOldChildren, "cell 1 14,growx,aligny center");
		basePanel.add(lblActivationLevel, "cell 1 4");
		basePanel.add(lblOldParents, "cell 1 13,growx,aligny center");
		basePanel.add(lblManageNode, "cell 1 11,alignx left,aligny top");
		basePanel.add(lblNewChildren, "cell 1 3,alignx left,aligny center");
		basePanel.add(lblNewParents, "cell 1 2,alignx left,aligny center");
		basePanel.add(lblNewNode, "cell 1 0 21 1,alignx left,aligny top");
	
		// Build the node list scroll pane
		JScrollPane scrollPane = new JScrollPane();
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
			// TODO: figure out what type to make this list
			//       -made it string, might revise to node later.
		JList<String> nodeManageList = new JList<String>();
		scrollPane.setViewportView(nodeManageList);
		basePanel.add(scrollPane, "cell 0 0 1 20,grow,aligny center");
	
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
		basePanel.add(txtfldOldParents, "cell 4 13 19 1,growx");
		basePanel.add(txtfldOldChildren, "cell 4 14 19 1,growx");
		basePanel.add(textfldOldActivLevel, "cell 4 15 5 1,growx");
		basePanel.add(textfldNewActivLevel, "cell 4 4 5 1,growx");
		basePanel.add(txtfldNewParents, "cell 4 2 19 1,growx");
		basePanel.add(txtfldNewChildren, "cell 4 3 19 1,growx");
		
		
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
	
		// Buttons: place in window
		basePanel.add(btnSaveNew, "cell 1 7,growx,aligny top");
		basePanel.add(btnSaveOld, "cell 1 19,growx,aligny top");
		basePanel.add(btnDeleteNode, "cell 4 19 6 1,growx,aligny top");
	}

	public JPanel getPanel() {
		return basePanel;
	}
}
