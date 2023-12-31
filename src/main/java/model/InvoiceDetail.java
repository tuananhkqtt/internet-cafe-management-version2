package model;

import dao.ProductDAO;

public class InvoiceDetail implements Comparable<InvoiceDetail>{
	private int invoiceId;
	private int productId;
	private int quantity;
	private int price;
	private int amount;
	
	public InvoiceDetail() {
		super();
	}
	
	public InvoiceDetail(int invoiceId, int productId, int quantity, int price, int amount) {
		super();
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Object[] toArray() {
		Product product = new Product(productId);
		product = ProductDAO.getInstance().selectById(product);
		return new Object[] {
				product.getId(), product.getName(), quantity, price, amount
		};
	}
	@Override
	public int compareTo(InvoiceDetail o) {
		// TODO Auto-generated method stub
		return 1;
	}
}
