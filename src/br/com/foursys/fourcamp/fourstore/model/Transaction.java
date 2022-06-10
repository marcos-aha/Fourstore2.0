package br.com.foursys.fourcamp.fourstore.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
	private HashMap<Product, Integer> list;
	private Customer constumer;
	private Double totalPrice;
	
	public Transaction(Customer constumer) {
		this.constumer = constumer;
		this.totalPrice = 0.0;
		list = new HashMap<Product, Integer>();
	}

	public Customer getConstumer() {
		return constumer;
	}

	public Double getTotalPrice() {	
		list.forEach((product, quantity) -> {
			totalPrice = product.getSellPrice() * quantity;
		});
		
		return totalPrice;
	}

	public void addProducts(Product product, Integer qty) {
		list.put(product, qty);
	}

	@Override
	public String toString() {
		return "Transaction [products=" + list.toString() + ", constumer=" + constumer + ", totalPrice="
				+ totalPrice + "]";
	}
	
}
