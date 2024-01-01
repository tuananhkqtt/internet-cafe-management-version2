package view.mainContainerView.cardsContainerView.InvoiceManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.invoice.EditInvoiceController;
import dao.EmployeeDAO;
import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import model.BillStatus;
import model.Employee;
import model.Invoice;
import model.InvoiceDetail;
import swing.Table;

import javax.swing.JScrollPane;

public class EditInvoiceDialog extends JDialog {
	private InvoiceContainer invoiceContainer;
	private Invoice invoice;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JTextField textField_accountId;
	private JTextField textField_computerId;
	private JTextField textField_total;
	private JTextField textField_createdAt;
	private JTextField textField_createdBy;
	private JComboBox comboBox;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditInvoiceDialog dialog = new EditInvoiceDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditInvoiceDialog(InvoiceContainer invoiceContainer, Invoice invoice) {
		this.invoiceContainer = invoiceContainer;
		this.invoice = invoice;
		
		setModal(true);
		setBounds(100, 100, 1000, 600);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(20);
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(10, 20));
		{
			JPanel panel_jLabel = new JPanel();
			contentPanel.add(panel_jLabel, BorderLayout.NORTH);
			{
				JLabel lblEditinvoice = new JLabel("Edit invoice");
				lblEditinvoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(lblEditinvoice);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			contentPanel.setPreferredSize(new Dimension(500, 0)); // Thiết lập chiều rộng là 50
			panel.setLayout(new BorderLayout(20, 0));
			{
				JPanel panel_jLabels = new JPanel();
				panel.add(panel_jLabels, BorderLayout.WEST);
				panel_jLabels.setLayout(new GridLayout(0, 1, 0, 30));
				{
					JLabel lable_id = new JLabel("Id");
					panel_jLabels.add(lable_id);
				}
				{
					JLabel lable_username = new JLabel("Account Id");
					panel_jLabels.add(lable_username);
				}
				{
					JLabel lblComputerId = new JLabel("Computer Id");
					panel_jLabels.add(lblComputerId);
				}
				{
					JLabel lblTotal = new JLabel("Total");
					panel_jLabels.add(lblTotal);
				}
				{
					JLabel lblCreatedAt = new JLabel("Created At");
					panel_jLabels.add(lblCreatedAt);
				}
				{
					JLabel lblNewLabel = new JLabel("Status");
					panel_jLabels.add(lblNewLabel);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Created By");
					panel_jLabels.add(lblNewLabel_1);
				}
			}
			{
				JPanel panel_jTextFields = new JPanel();
				panel.add(panel_jTextFields, BorderLayout.CENTER);
				panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 30));
				{
					textField_id = new JTextField();
					textField_id.setColumns(10);
					panel_jTextFields.add(textField_id);
				}
				{
					textField_accountId = new JTextField();
					textField_accountId.setColumns(10);
					panel_jTextFields.add(textField_accountId);
				}
				{
					textField_computerId = new JTextField();
					textField_computerId.setColumns(10);
					panel_jTextFields.add(textField_computerId);
				}
				{
					textField_total = new JTextField();
					textField_total.setEditable(false);
					textField_total.setColumns(10);
					panel_jTextFields.add(textField_total);
				}
				{
					textField_createdAt = new JTextField();
					textField_createdAt.setColumns(10);
					panel_jTextFields.add(textField_createdAt);
				}
				{
					String[] billStatus = {"uncompleted", "completed", "rejected"};
					comboBox = new JComboBox(billStatus);
					panel_jTextFields.add(comboBox);
				}
				{
					textField_createdBy = new JTextField();
					textField_createdBy.setColumns(10);
					panel_jTextFields.add(textField_createdBy);
				}
			}
		}
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(20);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.setBackground(Color.decode("#831843"));
				okButton.setForeground(Color.WHITE);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new EditInvoiceController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new EditInvoiceController(this));
			}
		}
		setLocationRelativeTo(null);
		showinvoiceInfor();
	}
	
	private void showinvoiceInfor() {
		textField_id.setText(invoice.getId()+"");
		textField_id.setEditable(false);
		
		textField_accountId.setText(invoice.getAccountId()+"");
		textField_accountId.setEditable(false);
		
		textField_computerId.setText(invoice.getComputerId()+"");
		textField_computerId.setEditable(false);
		
		textField_total.setText(invoice.getTotal()+"");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdAt = simpleDateFormat.format(invoice.getCreatedAt());
		textField_createdAt.setText(createdAt);
		textField_createdAt.setEditable(false);
		
		if(invoice.getStatus().equals(BillStatus.uncompleted))
			comboBox.setSelectedIndex(0);
		else if(invoice.getStatus().equals(BillStatus.completed))
			comboBox.setSelectedIndex(1);
		else if(invoice.getStatus().equals(BillStatus.rejected))
			comboBox.setSelectedIndex(2);
		
		Employee employee = new Employee(invoice.getCreatedBy());
		
		employee = EmployeeDAO.getInstance().selectById(employee);
		textField_createdBy.setText(employee.getName());
		textField_createdBy.setEditable(false);
		
		showInvoiceDetail();
	}
	
	private void showInvoiceDetail() {
		String[] columnName = new String[] {"Product Id", "Product Name", "Quantity", "Unit Price", "Amount"};
		Table table = new Table(columnName, null, null, false);
		TreeSet<InvoiceDetail> invoiceDetails = InvoiceDetailDAO.getInstance().selectByInvoiceId(invoice.getId());
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			((DefaultTableModel) table.getModel()).addRow(invoiceDetail.toArray());
		}
		table.setCenterColumn(0);
		scrollPane.setViewportView(table);
	}
	
	public void ok() {
		BillStatus status = null;
		if(comboBox.getSelectedIndex() == 0) {
			status = BillStatus.uncompleted;
		} else if(comboBox.getSelectedIndex() == 1) {
			status = BillStatus.completed;
		} else if(comboBox.getSelectedIndex() == 2) {
			status = BillStatus.rejected;
		}
		
		invoice.setStatus(status);
		InvoiceDAO.getInstance().update(invoice);
		invoiceContainer.reloadTable();
		JOptionPane.showMessageDialog(this, "The invoice edited successfully", "", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	public void cancel() {
		dispose();
	}
}
