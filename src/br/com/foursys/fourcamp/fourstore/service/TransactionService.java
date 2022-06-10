package br.com.foursys.fourcamp.fourstore.service;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import br.com.foursys.fourcamp.fourstore.data.ProductData;
import br.com.foursys.fourcamp.fourstore.data.TransactionData;
import br.com.foursys.fourcamp.fourstore.model.Customer;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Stock;

public class TransactionService {
	private static HashMap<Stock, Double> cart = new HashMap<>();
	private static TransactionData data = new TransactionData();

	public static HashMap<Stock, Double> listCart() {
		return cart;
	}

	public static Boolean searchCart(String sku) {
		for (HashMap.Entry<Stock, Double> product : cart.entrySet()) {
			if (product.getKey().getProduct().getSku().equals(sku)) {
				return true;
			}
		}
		return false;
	}

	public static Product getProductStock(String sku) {
		ProductData listStock = new ProductData();
		HashMap<Product, Integer> listProduct = listStock.listAll();
		if (!searchCart(sku)) {
			for (Entry<Product, Integer> product : listProduct.entrySet()) {
				if (product.getKey().getSku().equals(sku)) {
					return product.getKey();
				}
			}
			return null;
		}
		return null;
	}

	public static Entry<Stock, Double> getProductCart(String sku) {
		if (searchCart(sku)) {
			for (Entry<Stock, Double> product : cart.entrySet()) {
				if (product.getKey().getProduct().getSku().equals(sku)) {
					return product;
				}
			}
			return null;
		}
		return null;
	}

	public static Boolean productdelete(String sku) {
		for (Entry<Stock, Double> product : cart.entrySet()) {
			if (product.getKey().getProduct().getSku().equals(sku)) {
				cart.remove(product.getKey(), product.getValue());
				return true;
			}
		}
		return false;
	}

	public static Boolean storeProduct(Stock stock) {
		if (stock.getProduct() != null) {
			Double totalPrice = stock.getProduct().getSellPrice() * stock.getQuantity();
			cart.put(stock, totalPrice);
			return true;
		}
		return false;
	}

	public static Boolean stockPurchase(Customer customer) {
		int value;
		customer.setTotalPrice(totalPriceCart());
		while (true) {
			Random random = new Random();
			value = random.nextInt(999999999);
			if (data.listAll().containsKey(value)) {
				continue;
			} else {
				data.save(value, customer);
				break;
			}
		}
		return true;
	}

	public static Double totalPriceCart() {
		Double totalPrice = 0.0;
		for (Entry<Stock, Double> product : cart.entrySet()) {
			totalPrice += product.getValue();
		}
		return totalPrice;

	}

	public static void cartClear() {
		cart = new HashMap<>();
	}

	public static Customer productHistorytId(Integer id) {
		for (HashMap.Entry<Integer, Customer> purchase : data.listAll(). entrySet()) {
			if (purchase.getKey().equals(id)) {
				return purchase.getValue();
			}
		}
		return null;
	}
	
	public static Boolean PurchaseverifyId(Integer id) {
		return data.listAll().containsKey(id);
	}
	
	public static HashMap<Integer, Customer> listHistory() {
		return data.listAll();
	}

}
