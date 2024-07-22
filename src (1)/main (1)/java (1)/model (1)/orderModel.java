package model;

import java.time.LocalDate;

public class orderModel {
	private String orderId;
	private LocalDate orderDate;
	private int orderTotal;
	private String invoiceId;
	private String userId;
	
	public orderModel() {}

	public orderModel(String orderId, LocalDate orderDate, int orderTotal, String invoiceId, String userId) {
		super();
		this.setOrderId(orderId);
		this.setOrderDate(orderDate);
		this.setOrderTotal(orderTotal);
		this.setInvoiceId(invoiceId);
		this.setUserId(userId);
		
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal2) {
		this.orderTotal = orderTotal2;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
