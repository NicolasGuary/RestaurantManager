package model;

public class Consummable {

	private int idConsummable;
	private String nameConsummable;
	private float price;
	
	public Consummable(int idConsummable, String nameConsummable, float price) {
		super();
		this.idConsummable = idConsummable;
		this.nameConsummable = nameConsummable;
		this.price = price;
	}

	public int getIdConsummable() {
		return idConsummable;
	}

	public void setIdConsummable(int idConsummable) {
		this.idConsummable = idConsummable;
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
	
	
	
	
}
