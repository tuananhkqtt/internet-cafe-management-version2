package controller.computer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.computerManagement.AddComputerDialog;

public class AddComputerController implements ActionListener{
	private AddComputerDialog addComputerDialog;

	public AddComputerController(AddComputerDialog addComputerDialog) {
		super();
		this.addComputerDialog = addComputerDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			addComputerDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			addComputerDialog.cancel();
		}
	}
}
