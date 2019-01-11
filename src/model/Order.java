package model;

import java.util.ArrayList;


public class Order {

	private int idOrder;
	private float discount;
	private float price;
	private boolean paid;
	private String note;
	private ArrayList<Consummable> consummablesOrder;
	private Table table;
	
	public Order(int idOrder, float discount, float price, boolean paid, String note, ArrayList<Consummable> consummablesOrder, Table table) {
		super();
		this.idOrder = idOrder;
		this.discount = discount;
		this.price = price;
		this.paid = paid;
		this.note = note;
		this.consummablesOrder = consummablesOrder;
		this.table = table;
	}
	
	public Order(int idOrder, float discount, boolean paid, String note, ArrayList<Consummable> consummablesOrder, Table table) {
		super();
		this.idOrder = idOrder;
		this.discount = discount;
		this.paid = paid;
		this.note = note;
		this.consummablesOrder = consummablesOrder;
		this.table = table;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList<Consummable> getConsummablesOrder() {
		return consummablesOrder;
	}

	public void setConsummablesOrder(ArrayList<Consummable> consummablesOrder) {
		this.consummablesOrder = consummablesOrder;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
	//compute the price of the order and change it. Should be used anytime we add a new Consummable
	public void computePrice() {
		float result = 0;
		for (int i=0; i<this.consummablesOrder.size();i++) {
			result += this.consummablesOrder.get(i).getPrice();
		}
		//Discount to check because we have the checkbox in the view to know if it's € or % but not stored. Maybe we can say it is only % discount and that's all.
		//we would do result*(1+(discount/100))
		this.price=result;
	}	
}
