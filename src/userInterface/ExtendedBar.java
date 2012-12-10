package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.cmdPauseSim;
import controller.cmdRunSim;
import controller.cmdStopSim;
import controller.objectHandler;

public class ExtendedBar {
	
	JToolBar baseToolBar;
	JMenuBar baseMenuBar;

	public ExtendedBar(JToolBar baseToolBar, JMenuBar baseMenuBar) {
		this.baseToolBar = baseToolBar;
		this.baseMenuBar = baseMenuBar;
		initialize();
	}
	
	public void initialize(){

		baseToolBar.setEnabled(false);
		baseToolBar.setFloatable(false);
		
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
		
		btnStart.setIcon(new ImageIcon(UserInterface.class.getResource("/resources/start.png")));
		btnStop.setIcon(new ImageIcon(UserInterface.class.getResource("/resources/stop.png")));
		btnPause.setIcon(new ImageIcon(UserInterface.class.getResource("/resources/pause.png")));

		baseToolBar.add(btnStart);
		baseToolBar.add(btnPause);
		baseToolBar.add(btnStop);
		
		JMenu mnFile = new JMenu("File");
		baseMenuBar.add(mnFile);
		
		JMenuItem mntmNewConfig = new JMenuItem("New Config");
		JMenuItem mntmSaveConfig = new JMenuItem("Save Config");
		JMenuItem mntmLoadConfig = new JMenuItem("Load Config");
		mnFile.add(mntmNewConfig);
		mnFile.add(mntmSaveConfig);
		mnFile.add(mntmLoadConfig);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		baseMenuBar.add(mnEdit);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnEdit.add(mntmSettings);
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Migrate this functionality to the controller
				
				// Sloppy way to ensure that simulation is stopped
				new cmdStopSim().execute();
				
				// Close the main window
                System.exit( 0 );
			}
		});	
	}
	
	public JToolBar getToolBar(){
		return baseToolBar;
	}
	
	public JMenuBar getMenuBar(){
		return baseMenuBar;
	}
	
}
