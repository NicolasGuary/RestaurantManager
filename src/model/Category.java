package model;

public class Category {
	
	private int idCategory;
	private int idType;
	private String nameCategory;
	
	public Category(int idCategory, int idType, String nameType) {
		super();
		this.idCategory = idCategory;
		this.idType = idType;
		this.nameCategory = nameType;
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
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameType(String nameType) {
		this.nameCategory = nameType;
	}
	
	

}
