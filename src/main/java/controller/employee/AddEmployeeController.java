package controller.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import view.mainContainerView.cardsContainerView.EmployeeManagement.AddEmployeeDialog;

public class AddEmployeeController implements ActionListener{
	private AddEmployeeDialog addEmployeeDialog;

	public AddEmployeeController(AddEmployeeDialog addEmployeeDialog) {
		super();
		this.addEmployeeDialog = addEmployeeDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			addEmployeeDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			addEmployeeDialog.cancel();
		}
	}
	
}