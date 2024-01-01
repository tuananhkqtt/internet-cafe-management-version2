package controller.employee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import swing.ButtonCellEditor;
import swing.Table;
import view.mainContainerView.cardsContainerView.EmployeeManagement.EmployeeContainer;

public class EmployeeController implements ActionListener, KeyListener{
	EmployeeContainer employeeContainer;

	public EmployeeController(EmployeeContainer employeeContainer) {
		super();
		this.employeeContainer = employeeContainer;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() instanceof JTextField) {
			employeeContainer.search();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("Add Employee")) {
			employeeContainer.addEmployee();
		} else {
			Table table = (Table) ((Component) e.getSource()).getParent();
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(table.getColumnName(column).equalsIgnoreCase("Edit")) {
				employeeContainer.edit();
			} else if(table.getColumnName(column).equalsIgnoreCase("Delete")) {
				employeeContainer.delete();
			}
			((ButtonCellEditor) table.getCellEditor(row, column)).fireEditingStopped();
		}
	}
}
