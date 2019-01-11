package model;

public class Category {
	
	private int idCategory;
	private int idType;
	private String nameType;
	
	public Category(int idCategory, int idType, String nameType) {
		super();
		this.idCategory = idCategory;
		this.idType = idType;
		this.nameType = nameType;
	}
	
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
	

}
