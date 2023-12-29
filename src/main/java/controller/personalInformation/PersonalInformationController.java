package controller.personalInformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import view.mainContainerView.cardsContainerView.PersonalInformationContainer;

public class PersonalInformationController implements ActionListener, KeyListener{
	private PersonalInformationContainer personalInformationCardContainer;

	public PersonalInformationController(PersonalInformationContainer personalInformationCardContainer) {
		super();
		this.personalInformationCardContainer = personalInformationCardContainer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Save")) {
			personalInformationCardContainer.save();
		} else if(e.getActionCommand().equals("Reset")) {
			personalInformationCardContainer.reset();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() instanceof JTextField) {
			personalInformationCardContainer.setNewPasswordEditable();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() instanceof JTextField) {
			personalInformationCardContainer.setNewPasswordEditable();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() instanceof JTextField) {
			personalInformationCardContainer.setNewPasswordEditable();
		}
	}
	
}
