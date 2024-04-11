package model;

public class ProductModel {
	String name;
	String description;
	int stock;
	float price;
	int category;
	int productId;
	String categoryName;

	public ProductModel(String name, String description, int stock, float price, int category) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.category = category;
		this.categoryName = getCategoryName();
		this.productId = getProductId();
	}

	public ProductModel() {

	};

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	};

	public String getName() {
		return name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}