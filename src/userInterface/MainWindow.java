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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
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
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(5);
		splitPane.setEnabled(false);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("Play");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pause");
		panel.add(btnNewButton_1);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Load");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Settings");
		panel.add(btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		panel_2.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Nodes"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_2.add(list, "flowy,cell 0 0,grow");
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Edges"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_2.add(list_1, "flowy,cell 1 0,grow");
		
		JSeparator separator = new JSeparator();
		panel_2.add(separator, "cell 0 0,growx");
		
		JSeparator separator_1 = new JSeparator();
		panel_2.add(separator_1, "cell 1 0,growx");
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "cell 0 0,growx");
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JButton button = new JButton("+");
		panel_3.add(button);
		
		JButton button_1 = new JButton("*");
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("-");
		panel_3.add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, "cell 1 0,growx");
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton button_3 = new JButton("+");
		panel_4.add(button_3);
		
		JButton button_4 = new JButton("*");
		panel_4.add(button_4);
		
		JButton button_5 = new JButton("-");
		panel_4.add(button_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane_1.setRightComponent(tabbedPane);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Graphic Display", null, panel_5, null);
		
		
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities( glprofile );
		final GLJPanel glcanvas = new GLJPanel( glcapabilities );

		glcanvas.addGLEventListener( new GLEventListener() {
        
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
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		panel_5.add( glcanvas );
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Data Display", null, panel_6, null);

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
