package userInterface;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;

import controller.cmdStopSim;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.AbstractListModel;


// TODO: Updating functions, user actions

public class MainWindow {

	private JFrame mainFrame;

	public MainWindow() {
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
		mainFrame.setBounds(100, 100, 750, 500);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane spTools = new JSplitPane();
		spTools.setDividerSize(5);
		spTools.setEnabled(false);
		spTools.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame.getContentPane().add(spTools);
		
		JPanel pnlToolbar = new JPanel();
		spTools.setLeftComponent(pnlToolbar);
		pnlToolbar.setLayout(new BoxLayout(pnlToolbar, BoxLayout.X_AXIS));
		
		JButton btnStart = new JButton("Start");
		pnlToolbar.add(btnStart);
		
		JButton btnPause = new JButton("Pause");
		pnlToolbar.add(btnPause);
		
		Component toolPanelSpacer = Box.createHorizontalGlue();
		pnlToolbar.add(toolPanelSpacer);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		pnlToolbar.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		pnlToolbar.add(btnLoad);
		
		JButton btnSettings = new JButton("Settings");
		pnlToolbar.add(btnSettings);
		
		JPanel pnlMain = new JPanel();
		spTools.setRightComponent(pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
		
		JSplitPane spData = new JSplitPane();
		pnlMain.add(spData);
		
		JPanel pnlLists = new JPanel();
		spData.setLeftComponent(pnlLists);
		pnlLists.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));
		
		JList lstNodes = new JList();
		lstNodes.setModel(new AbstractListModel() {
			String[] values = new String[] {"Nodes"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		pnlLists.add(lstNodes, "flowy,cell 0 0,grow");
		
		JList lstEdges = new JList();
		lstEdges.setModel(new AbstractListModel() {
			String[] values = new String[] {"Edges"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		pnlLists.add(lstEdges, "flowy,cell 1 0,grow");
		
		JSeparator nodeSeparator = new JSeparator();
		pnlLists.add(nodeSeparator, "cell 0 0,growx");
		
		JSeparator edgeSeparator = new JSeparator();
		pnlLists.add(edgeSeparator, "cell 1 0,growx");
		
		JPanel pnlNodeButtons = new JPanel();
		pnlLists.add(pnlNodeButtons, "cell 0 0,growx");
		pnlNodeButtons.setLayout(new BoxLayout(pnlNodeButtons, BoxLayout.X_AXIS));
		
		JButton btnNodeAdd = new JButton("+");
		btnNodeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewNodeDialog nodeInterface = new NewNodeDialog();
			}
		});
		pnlNodeButtons.add(btnNodeAdd);
		
		JButton btnNodeEdit = new JButton("*");
		pnlNodeButtons.add(btnNodeEdit);
		
		JButton btnNodeDelete = new JButton("-");
		pnlNodeButtons.add(btnNodeDelete);
		
		JPanel pnlEdgeButtons = new JPanel();
		pnlLists.add(pnlEdgeButtons, "cell 1 0,growx");
		pnlEdgeButtons.setLayout(new BoxLayout(pnlEdgeButtons, BoxLayout.X_AXIS));
		
		JButton btnEdgeAdd = new JButton("+");
		pnlEdgeButtons.add(btnEdgeAdd);
		
		JButton btnEdgeEdit = new JButton("*");
		pnlEdgeButtons.add(btnEdgeEdit);
		
		JButton btnEdgeDelete = new JButton("-");
		pnlEdgeButtons.add(btnEdgeDelete);
		
		JTabbedPane tpDisplays = new JTabbedPane(JTabbedPane.TOP);
		spData.setRightComponent(tpDisplays);
		
		JPanel pnlGraphics = new JPanel();
		tpDisplays.addTab("Graphic Display", null, pnlGraphics, null);
		
		
		GLProfile glProfile = GLProfile.getDefault();
		GLCapabilities glCapabilities = new GLCapabilities( glProfile );
		final GLJPanel glCanvas = new GLJPanel( glCapabilities );

		glCanvas.addGLEventListener( new GLEventListener() {
        
			public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
				OneTriangle.setup( glautodrawable.getGL().getGL2(), width, height );
			}
        
			public void init( GLAutoDrawable glautodrawable ) {
			}
        
			public void dispose( GLAutoDrawable glautodrawable ) {
			}
        
			public void display( GLAutoDrawable glautodrawable ) {
				OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight() );
			}
		});
		pnlGraphics.setLayout(new BoxLayout(pnlGraphics, BoxLayout.X_AXIS));
		
		pnlGraphics.add( glCanvas );
		
		JPanel pnlData = new JPanel();
		tpDisplays.addTab("Data Display", null, pnlData, null);

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
