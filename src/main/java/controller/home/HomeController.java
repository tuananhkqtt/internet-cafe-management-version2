package controller.home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.HomeContainer;

public class HomeController implements ActionListener{
	private HomeContainer homeContainer = new HomeContainer();
	
	public HomeController(HomeContainer homeContainer) {
		super();
		this.homeContainer = homeContainer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
	}
}


