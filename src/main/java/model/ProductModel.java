package model;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

import util.StringUtils;

public class ProductModel {
	private String name;
	private String description;
	private int stock;
	private float price;
	private int category;
	private int productId;
	private String categoryName;
	private String ImageUrlFromPart;
	private String imageUrl;

	public ProductModel(String name, String description, int stock, float price, int category, Part imageData) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.category = category;
		this.categoryName = getCategoryName();
		this.productId = getProductId();
		this.ImageUrlFromPart = getImageUrl(imageData);
		this.imageUrl = getImageUrl();
	}

	public ProductModel() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

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

	private String getImageUrl(Part imagePart) {
		String savePath = StringUtils.SAVE_PATH;
		String fileName = null;
		try {
			String contentDisp = imagePart.getHeader("content-disposition");
			String[] items = contentDisp.split(";");
			for (String s : items) {
				if (s.trim().startsWith("filename")) {
					fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
					break;
				}
			}

			if (fileName == null || fileName.isEmpty()) {
				fileName = "default.jpg";
			}

			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdirs();
			}
			String filePath = savePath + File.separator + fileName;
			imagePart.write(filePath);

		} catch (IOException e) {
			e.printStackTrace();
			fileName = null;
		}

		return fileName;
	}

	public String getImageUrlFromPart() {
		return ImageUrlFromPart;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setImageUrlFromPart(Part part) {
		this.ImageUrlFromPart = getImageUrl(part);
	}

}
