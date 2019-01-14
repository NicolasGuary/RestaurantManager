package model;

public class Consummable {

	private int idConsummable;
	private int idCategory;
	private String nameConsummable;
	private float price;
	
	public Consummable(int idConsummable, String nameConsummable, float price) {
		super();
		this.idConsummable = idConsummable;
		this.nameConsummable = nameConsummable;
		this.price = price;
	}

	public Consummable(int idConsummable, int idCategory, String nameConsummable, float price) {
		super();
		this.idConsummable = idConsummable;
		this.idCategory = idCategory;
		this.nameConsummable = nameConsummable;
		this.price = price;
	}

	public int getIdConsummable() {
		return idConsummable;
	}

	public void setIdConsummable(int idConsummable) {
		this.idConsummable = idConsummable;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameConsummable() {
		return nameConsummable;
	}

	public void setNameConsummable(String nameConsummable) {
		this.nameConsummable = nameConsummable;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String toString(){
		return this.getNameConsummable();
	}
	
	
	
	
}
