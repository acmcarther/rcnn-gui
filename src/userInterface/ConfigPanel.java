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
		basePanel.setLayout(new MigLayout("", "[162px][43px][8px][][][grow][][13px][][][][][grow][][][][][][][][grow][1px][46px][4px][91px]", "[14px][6px][][][][][][10px][][][][][][][][][][][][]"));
		
		// Build all of the labels
		basePanel.setLayout(new MigLayout("", "[162px][43px][8px][][][grow][][13px][][][][][grow][][][][][][][][grow][1px][46px][4px][91px]", "[14px][6px][][][][][][10px][][][][][][][][][][][][]"));
		
		// Build all of the labels
		JLabel lblNewNode = new JLabel("Create New Node");
		JLabel lblManageNode = new JLabel("Manage Existing Nodes");	
		JLabel lblOldParents = new JLabel("Parents:");
		JLabel lblOldChildren = new JLabel("Children:");
			
			JLabel lblName = new JLabel("Name:");
			basePanel.add(lblName, "cell 1 3");
			
			JTextField txtfldNewName = new JTextField();
			basePanel.add(txtfldNewName, "cell 4 3 20 1,growx");
			txtfldNewName.setColumns(10);
			JLabel lblNewParents = new JLabel("Parents:");
			basePanel.add(lblNewParents, "cell 1 4,alignx left,aligny center");
			
				// build all of the text fields
				JTextField txtfldNewParents = new JTextField();
				txtfldNewParents.setColumns(10);
				basePanel.add(txtfldNewParents, "cell 4 4 20 1,growx");
			JLabel lblNewChildren = new JLabel("Children:");
			basePanel.add(lblNewChildren, "cell 1 5,alignx left,aligny center");
			JTextField txtfldNewChildren = new JTextField();
			txtfldNewChildren.setColumns(10);
			basePanel.add(txtfldNewChildren, "cell 4 5 20 1,growx");
			JLabel lblActivationLevel = new JLabel("Activation Level:");
			basePanel.add(lblActivationLevel, "cell 1 6");
		JTextField textfldNewActivLevel = new JTextField();
		textfldNewActivLevel.setColumns(10);
		basePanel.add(textfldNewActivLevel, "cell 4 6 20 1,growx");
		// Button: save new node
		JButton btnSaveNew = new JButton("Save Node");
		objectHandler saveNewHandler = new objectHandler(btnSaveNew);
		
			// Buttons: place in window
			basePanel.add(btnSaveNew, "cell 1 8,growx,aligny top");
		JLabel lblActivationLevel_1 = new JLabel("Activation Level:");
		basePanel.add(lblActivationLevel_1, "cell 1 15");
		basePanel.add(lblOldChildren, "cell 1 14,growx,aligny center");
		basePanel.add(lblOldParents, "cell 1 13,growx,aligny center");
		basePanel.add(lblManageNode, "cell 1 11,alignx left,aligny top");
		basePanel.add(lblNewNode, "cell 1 0 22 1,alignx left,aligny top");
	
		// Build the node list scroll pane
		JScrollPane scrollPane = new JScrollPane();
			// TODO: figure out what type to make this list
			//       -made it string, might revise to node later.
		JList<String> nodeManageList = new JList<String>();
		scrollPane.setViewportView(nodeManageList);
		basePanel.add(scrollPane, "cell 0 0 1 20,aligny center,grow");
		JTextField txtfldOldChildren = new JTextField();
		JTextField txtfldOldParents = new JTextField();
		JTextField textfldOldActivLevel = new JTextField();
		txtfldOldParents.setColumns(10);
		textfldOldActivLevel.setColumns(10);
		txtfldOldChildren.setColumns(10);	
		basePanel.add(txtfldOldParents, "cell 4 13 20 1,growx");
		basePanel.add(txtfldOldChildren, "cell 4 14 20 1,growx");
		basePanel.add(textfldOldActivLevel, "cell 4 15 20 1,growx");
		
		
		/**
		 * Initialize the buttons and their handlers.
		 */
		saveNewHandler.setCommand(new cmdNewNode());
		
		// Button: save pre-existing node
		JButton btnSaveOld = new JButton("Save Node");
		objectHandler saveOldHandler = new objectHandler(btnSaveOld);
		saveOldHandler.setCommand(new cmdChgNode());
		
		// Button: delete node
		JButton btnDeleteNode = new JButton("Delete Node");
		objectHandler deleteBtnHandler = new objectHandler(btnDeleteNode);
		deleteBtnHandler.setCommand(new cmdDeleteNode());
		basePanel.add(btnSaveOld, "cell 1 19,growx,aligny top");
		basePanel.add(btnDeleteNode, "cell 4 19 7 1,growx,aligny top");


		
	}

	public JPanel getPanel() {
		return basePanel;
	}
}
