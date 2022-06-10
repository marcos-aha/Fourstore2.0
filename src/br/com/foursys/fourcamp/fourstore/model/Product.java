package br.com.foursys.fourcamp.fourstore.model;

import br.com.foursys.fourcamp.fourstore.enums.CategoryEnum;
import br.com.foursys.fourcamp.fourstore.enums.ColorEnum;
import br.com.foursys.fourcamp.fourstore.enums.DepartmentEnum;
import br.com.foursys.fourcamp.fourstore.enums.SizeEnum;
import br.com.foursys.fourcamp.fourstore.enums.TypeEnum;

public class Product {
	private String sku;
	private String type;
	private String size;
	private String color;
	private String category;
	private String department;
	private Double buyPrice;
	private Double sellPrice;
	private String description;
	
	public Product() {
	}

	public Product(String sku, String type, String size, String color, String category, String department,
			Double buyPrice, Double sellPrice, String description) {

		this.sku = sku;
		this.type = type;
		this.size = size;
		this.color = color;
		this.category = category;
		this.department = department;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.description = description;
	
	}

	public Product(String sku, Double buyPrice, Double sellPrice, String description) {
		this.sku = sku;

		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.description = description;
		parseSku(sku);
	}

	private void parseSku(String sku) {
		this.type = TypeEnum.get(sku.substring(0, 2)).getDescription();
		this.color = ColorEnum.get(sku.substring(2, 4)).getDescription();
		this.size = SizeEnum.get(sku.substring(4, 6));
		this.category = CategoryEnum.get(sku.substring(6, 8)).getDescription();
		this.department = DepartmentEnum.get(sku.substring(8, 10)).getDescription();
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSku() {
		return sku;
	}

	@Override
	public String toString() {
		return "Produto[Sku: " + sku +", Tipo: " + type + ", Tamanho: " + size + ", Cor: " + color + ", Categoria: " + category + 
				", Preço: R$" + sellPrice + ", Descrição: " + description + "]";
	}

}
