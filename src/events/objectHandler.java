package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import events.buttons.buttonCommand;

public class objectHandler{
	JButton button = null;
	buttonCommand command;
	
	public objectHandler(JButton newButton){
		button = newButton;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command != null){
					command.execute();
				}
			}
		});
	}
	
	public void setCommand(buttonCommand newCommand){
		command = newCommand;
	}
	
	public void forceExecute(){
		command.execute();
	}
}
