package view.mainContainerView;

import java.awt.CardLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import model.Account;
import view.mainContainerView.cardsContainerView.HomeContainer;
import view.mainContainerView.cardsContainerView.PersonalInformationContainer;
import view.mainContainerView.cardsContainerView.AccountManagement.AccountContainer;
import view.mainContainerView.cardsContainerView.EmployeeManagement.EmployeeContainer;
import view.mainContainerView.cardsContainerView.InvoiceManagement.InvoiceContainer;
import view.mainContainerView.cardsContainerView.ProductManagement.ProductContainer;
import view.mainContainerView.cardsContainerView.computerManagement.ComputerContainer;

public class CardsContainerView extends JPanel {
	private Account account;
	
	private CardLayout cardLayout;
	private HomeContainer homeCardContainer;
	private AccountContainer accountCardContainer;
	private ProductContainer productCardContainer;
	private EmployeeContainer employeeCardContainer;
	private InvoiceContainer invoiceCardContainer;
	private ComputerContainer computerCardContainer;
	private PersonalInformationContainer personalInformationCardContainer;

	/**
	 * Create the panel.
	 */
	public CardsContainerView(Account account) {
		this.account = account;
		
		setBounds(0, 0, (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(),
				(int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - 64);
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		
		homeCardContainer = new HomeContainer();
		add(homeCardContainer, "Home");
	}

	public void show(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Home":
			break;
		case "Account Management":
			accountCardContainer = new AccountContainer();
			add(accountCardContainer, "Account Management");
			break;
		case "Product Management":
			productCardContainer = new ProductContainer();
			add(productCardContainer, "Product Management");
			break;
		case "Employee Management":
			employeeCardContainer = new EmployeeContainer();
			add(employeeCardContainer, "Employee Management");
			break;
		case "Invoice Management":
			invoiceCardContainer = new InvoiceContainer();
			add(invoiceCardContainer, "Invoice Management");
			break;
		case "Computer Management":
			computerCardContainer = new ComputerContainer();
			add(computerCardContainer, "Computer Management");
			break;
		case "Personal Information":
			personalInformationCardContainer = new PersonalInformationContainer(account);
			add(personalInformationCardContainer, "Personal Information");
			break;
		}
		cardLayout.show(this, e.getActionCommand());
	}
}
