package br.com.foursys.fourcamp.fourstore.service;

import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.data.ProductData;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Stock;

public class StockService {

	public static String insertProduct(Product product, Integer quantity) {
		ProductData list = new ProductData();
		Stock stock = new Stock(product, quantity);
		for (HashMap.Entry<Product, Integer> sku : list.listAll().entrySet()) {
			if (sku.getKey().getSku().equals(stock.getProduct().getSku())) {
				return "Produto já está cadastrado!";
			}
		}
		list.save(stock);
		return "Produto cadastrado com sucesso!";
	}

	public static String listAll() {
		String description = "";
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			description += findSku.getKey().toString() + ", Quantidade no estoque: " + findSku.getValue() + ".\n";
		}
		return description;
	}

	public static String findAll(String sku) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				return findSku.getKey().toString() + ", Quantidade no estoque: " + findSku.getValue() + ".";
			}
		}
		return "Produto não encontrado!";
	}

	public static String updateProduct(String sku, int quantity) {
		boolean alternative = true;
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				Stock stock = new Stock(findSku.getKey(), (findSku.getValue() + quantity));
				findSku.getKey().setDescription(findSku.getKey().getDescription());
				list.update(stock, alternative);
				return "Quantidade atualizada com sucesso!";
			}
		}
		return "Produto não possui cadastro!";
	}

	public static String updateProduct(Product product, int quantity) {
		boolean alternative = false;
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(product.getSku())) {
				Stock stock = new Stock(product, findSku.getValue() + quantity);
				list.update(stock, alternative);
				return "Produto atualizado com sucesso!\n" + stock.toString();
			}
		}
		return "Produto não possui cadastro!";
	}

	public static Boolean validateSku(String sku) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				return true;
			}
		}
		return false;
	}

	public static String deleteProduct(String sku) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				list.delete(findSku.getKey());
				return "Produto removido com sucesso!";
			}
		}
		return "Falha ao remover produto!";
	}

	public static Boolean checkQuantity(String sku, int quantity) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku) && findSku.getValue() >= quantity) {
				return true;
			}
		}
		return false;
	}

	public static int getQuantity(String sku) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				return findSku.getValue();
			}
		}
		return 0;
	}

	public static Product findByProduct(String sku) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> findSku : list.listAll().entrySet()) {
			if (findSku.getKey().getSku().equals(sku)) {
				return findSku.getKey();
			}
		}
		return null;
	}

	public static void reduceProductStock(HashMap<Stock, Double> cart) {
		ProductData list = new ProductData();
		for (HashMap.Entry<Product, Integer> productStock : list.listAll().entrySet()) {
			for (HashMap.Entry<Stock, Double> productCart : cart.entrySet()) {
				if (productStock.getKey().getSku().equals(productCart.getKey().getProduct().getSku())) {
					list.reduceProductStock(productStock.getKey(),
							productStock.getValue() - productCart.getKey().getQuantity());
				}
			}
		}
	}
}
