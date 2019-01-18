/*
 * @author Nicolas GUARY
 */
package model;

/**
 * The Class Consummable.
 */
public class Consummable {

	/** The id consummable. */
	private int idConsummable;
	
	/** The id category. */
	private int idCategory;
	
	/** The name consummable. */
	private String nameConsummable;
	
	/** The price. */
	private float price;
	
	/**
	 * Instantiates a new consummable.
	 *
	 * @param idConsummable the id consummable
	 * @param nameConsummable the name consummable
	 * @param price the price
	 */
	public Consummable(int idConsummable, String nameConsummable, float price) {
		super();
		this.idConsummable = idConsummable;
		this.nameConsummable = nameConsummable;
		this.price = price;
	}

	/**
	 * Instantiates a new consummable.
	 *
	 * @param idConsummable the id consummable
	 * @param idCategory the id category
	 * @param nameConsummable the name consummable
	 * @param price the price
	 */
	public Consummable(int idConsummable, int idCategory, String nameConsummable, float price) {
		super();
		this.idConsummable = idConsummable;
		this.idCategory = idCategory;
		this.nameConsummable = nameConsummable;
		this.price = price;
	}

	/**
	 * Gets the id consummable.
	 *
	 * @return the id consummable
	 */
	public int getIdConsummable() {
		return idConsummable;
	}

	/**
	 * Sets the id consummable.
	 *
	 * @param idConsummable the new id consummable
	 */
	public void setIdConsummable(int idConsummable) {
		this.idConsummable = idConsummable;
	}

	/**
	 * Gets the id category.
	 *
	 * @return the id category
	 */
	public int getIdCategory() {
		return idCategory;
	}

	/**
	 * Sets the id category.
	 *
	 * @param idCategory the new id category
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	/**
	 * Gets the name consummable.
	 *
	 * @return the name consummable
	 */
	public String getNameConsummable() {
		return nameConsummable;
	}

	/**
	 * Sets the name consummable.
	 *
	 * @param nameConsummable the new name consummable
	 */
	public void setNameConsummable(String nameConsummable) {
		this.nameConsummable = nameConsummable;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getNameConsummable();
	}
	
	
	
	
}
