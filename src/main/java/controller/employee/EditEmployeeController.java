package controller.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.EmployeeManagement.EditEmployeeDialog;

public class EditEmployeeController implements ActionListener{
	private EditEmployeeDialog editEmployeeDialog;

	public EditEmployeeController(EditEmployeeDialog editEmployeeDialog) {
		super();
		this.editEmployeeDialog = editEmployeeDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			editEmployeeDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			editEmployeeDialog.cancel();
		}
	}
	
}
