package model;

public class CartModel {
	private int user_id;
	private int quantity;
	private int product_id;
	
	public CartModel() {
	}
	
	public CartModel(int user_id, int quantity, int product_id) {
		super();
		this.user_id = user_id;
		this.quantity = quantity;
		this.product_id = product_id;
	}
	public int getUser_id() {
		return user_id;
	}

	public  void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public  int getQuantity() {
		return quantity;
	}

	public  void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public  int getProduct_id() {
		return product_id;
	}

	public  void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


}
