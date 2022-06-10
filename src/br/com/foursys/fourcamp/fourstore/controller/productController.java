package br.com.foursys.fourcamp.fourstore.controller;

import br.com.foursys.fourcamp.fourstore.enums.ColorEnum;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.service.StockService;

public class productController {
	@SuppressWarnings("unused")
	private int quantity;
	@SuppressWarnings("unused")
	private String sku;
	@SuppressWarnings("unused")
	private ColorEnum colorEnum;
	


	public productController(String sku, int quantity) {
		this.sku = sku;
		this.quantity = quantity;
	}
	
	public static String newRegister(String sku, int quantity, Double buyPrice, Double sellPrice, String description) {
		if (sku == null || quantity == 0 || buyPrice == null || sellPrice == null || description == null) {
			return "Cadastro não realizado, possui valores nulos!";
		} else {
			Product product = new Product(sku, buyPrice, sellPrice, description);
			return StockService.insertProduct(product, quantity);
		}
		
	}
	
	public static String findProduct(String sku) {
		if(sku != null && sku.length() == 10) {
		return StockService.findAll(sku);
		} else {
			return "Sku não encontrado!";
		}
		
	}

	public static Product findByProduct(String sku) {
		return StockService.findByProduct(sku);
	}
	public static String updateProduct(String sku, int quantity) {

		if(sku != null && sku.length() == 10 && quantity >= 0) {
			return StockService.updateProduct(sku, quantity);
		}
		return"Quantidade inserida menor que zero!";
	}
	
	public static String updateProduct(String sku, int quantity, Double buyPrice, Double sellPrice, String description) {
		if(sku !=null && sku.length() == 10 && quantity >= 0 && buyPrice > 0 && buyPrice <= sellPrice) {
			Product product = new Product(sku,buyPrice, sellPrice, description);
			return StockService.updateProduct(product, quantity);
		}
		
		return "Dados inseridos inválidos!";
	}
	
	public static String listAll() {
		return StockService.listAll();
		
	}
	
	public static Boolean validateSku(String sku) {
		if(sku != null && sku.length() == 10) {
		return StockService.validateSku(sku);
		}
		return false;
	}
	
	public static String deleteProduct(String sku) {
		if(sku != null && sku.length() == 10) {
			return StockService.deleteProduct(sku);
		}
		return "Não foi possivel deletar produto";
	}
	
	public static Boolean checkQuantity(String sku, int quantity) {
		return StockService.checkQuantity(sku, quantity);
	}
	
	public static int getQuantity (String sku) {
		return StockService.getQuantity(sku);
	}
	
}
