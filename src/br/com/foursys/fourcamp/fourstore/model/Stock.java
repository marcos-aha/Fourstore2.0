package br.com.foursys.fourcamp.fourstore.model;

public class Stock {
	
	private Product product;
	private Integer quantity;
	
	
	public Product getProduct() {
		return product;
	}


	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Stock(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return product + ", Quantidade= " + quantity + ".\n";
	}

	
	
	
	
}

