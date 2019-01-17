/*
 * 
 */
package model;

/**
 * The Class Type.
 */
public class Type {

	/** The id type. */
	private int idType;
	
	/** The name type. */
	private String nameType;
	
	/**
	 * Instantiates a new type.
	 *
	 * @param idType the id type
	 * @param nameType the name type
	 */
	public Type(int idType, String nameType) {
		super();
		this.idType = idType;
		this.nameType = nameType;
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
	 * Gets the name type.
	 *
	 * @return the name type
	 */
	public String getNameType() {
		return nameType;
	}
	
	/**
	 * Sets the name type.
	 *
	 * @param nameType the new name type
	 */
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
	
}
