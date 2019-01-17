/*
 * 
 */
package model;

import java.util.ArrayList;


/**
 * The Class Order.
 */
public class Order {

	/** The id order. */
	private int idOrder;
	
	/** The discount. */
	private float discount;
	
	/** The price. */
	private float price;
	
	/** The paid. */
	private boolean paid;
	
	/** The note. */
	private String note;
	
	/** The consummables order. */
	private ArrayList<Consummable> consummablesOrder;
	
	/** The table. */
	private Table table;
	
	/**
	 * Instantiates a new order.
	 *
	 * @param idOrder the id order
	 * @param discount the discount
	 * @param price the price
	 * @param paid the paid
	 * @param note the note
	 * @param consummablesOrder the consummables order
	 * @param table the table
	 */
	//Full constructor
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
	
	/**
	 * Instantiates a new order.
	 *
	 * @param idOrder the id order
	 * @param discount the discount
	 * @param price the price
	 * @param paid the paid
	 * @param note the note
	 * @param table the table
	 */
	//Constructor without Consummables
	public Order(int idOrder, float discount,float price, boolean paid, String note, Table table) {
		super();
		this.idOrder = idOrder;
		this.discount = discount;
		this.price=price;
		this.paid = paid;
		this.note = note;
		this.table = table;
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param idOrder the id order
	 * @param discount the discount
	 * @param price the price
	 * @param paid the paid
	 * @param note the note
	 */
	//Constructor without Consummables and Table (fresh new order from for the create method)
	public Order(int idOrder, float discount,float price, boolean paid, String note) {
		super();
		this.idOrder = idOrder;
		this.discount = discount;
		this.price=price;
		this.paid = paid;
		this.note = note;
	}

	/**
	 * Instantiates a new order.
	 *
	 * @param discount the discount
	 * @param price the price
	 * @param paid the paid
	 * @param note the note
	 * @param consummablesOrder the consummables order
	 * @param table the table
	 */
	public Order(float discount, float price, boolean paid, String note, ArrayList<Consummable> consummablesOrder, Table table) {
	
		this.discount = discount;
		this.price = price;
		this.paid = paid;
		this.note = note;
		this.consummablesOrder = consummablesOrder;
		this.table = table;
	}
	
	/**
	 * Gets the id order.
	 *
	 * @return the id order
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * Sets the id order.
	 *
	 * @param idOrder the new id order
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Checks if is paid.
	 *
	 * @return true, if is paid
	 */
	public boolean isPaid() {
		return paid;
	}

	/**
	 * Sets the paid.
	 *
	 * @param paid the new paid
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the consummables order.
	 *
	 * @return the consummables order
	 */
	public ArrayList<Consummable> getConsummablesOrder() {
		return consummablesOrder;
	}

	/**
	 * Sets the consummables order.
	 *
	 * @param consummablesOrder the new consummables order
	 */
	public void setConsummablesOrder(ArrayList<Consummable> consummablesOrder) {
		this.consummablesOrder = consummablesOrder;
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the table.
	 *
	 * @param table the new table
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	
	/**
	 * Compute price.
	 */
	//compute the price of the order and change it. Should be used anytime we add a new Consummable
	public void computePrice() {
		float result = 0;
		for (int i=0; i<this.consummablesOrder.size();i++) {
			result += this.consummablesOrder.get(i).getPrice();
		}
		result = result - this.discount;
		this.price=result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", discount=" + discount + ", price=" + price + ", paid=" + paid
				+ ", note=" + note + ", consummablesOrder=" + consummablesOrder + ", table=" + table + "]";
	}	
	
	
}
