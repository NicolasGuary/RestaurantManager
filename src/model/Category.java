/*
 * @author Nicolas GUARY
 */
package model;

/**
 * The Class Category.
 */
public class Category {
	
	/** The id category. */
	private int idCategory;
	
	/** The id type. */
	private int idType;
	
	/** The name category. */
	private String nameCategory;
	
	/**
	 * Instantiates a new category.
	 *
	 * @param idCategory the id category
	 * @param idType the id type
	 * @param nameType the name type
	 */
	public Category(int idCategory, int idType, String nameType) {
		super();
		this.idCategory = idCategory;
		this.idType = idType;
		this.nameCategory = nameType;
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
	 * Gets the id type.
	 *
	 * @return the id type
	 */
	public int getIdType() {
		return idType;
	}
	
	/**
	 * Sets the id type.
	 *
	 * @param idType the new id type
	 */
	public void setIdType(int idType) {
		this.idType = idType;
	}
	
	/**
	 * Gets the name category.
	 *
	 * @return the name category
	 */
	public String getNameCategory() {
		return nameCategory;
	}
	
	/**
	 * Sets the name type.
	 *
	 * @param nameType the new name type
	 */
	public void setNameType(String nameType) {
		this.nameCategory = nameType;
	}
	
	

}
