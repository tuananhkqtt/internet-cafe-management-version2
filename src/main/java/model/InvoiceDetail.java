package model;

public class InvoiceDetail implements Comparable<InvoiceDetail>{
	private int invoiceId;
	private int productId;
	private int quantity;
	public InvoiceDetail() {
		super();
	}
	public InvoiceDetail(int invoiceId, int productId, int quantity) {
		super();
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.quantity = quantity;
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
	
	public Object[] toArray() {
		return new Object[] {
				invoiceId, productId, quantity
		};
	}
	@Override
	public int compareTo(InvoiceDetail o) {
		// TODO Auto-generated method stub
		return 1;
	}
}
