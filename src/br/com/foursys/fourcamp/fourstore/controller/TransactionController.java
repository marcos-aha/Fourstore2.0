package br.com.foursys.fourcamp.fourstore.controller;

import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.model.Customer;
import br.com.foursys.fourcamp.fourstore.model.Stock;
import br.com.foursys.fourcamp.fourstore.service.StockService;
import br.com.foursys.fourcamp.fourstore.service.TransactionService;
import br.com.foursys.fourcamp.fourstore.util.regex;

public class TransactionController {

	public static Boolean validationSell(String sku, int quantity) {
		if (StockService.validateSku(sku) && StockService.checkQuantity(sku, quantity)) {
			return true;
		}
		return false;
	}

	public static Boolean ProductSearchCart(String sku) {
		if (TransactionService.getProductCart(sku) == null) {
			return false;
		} else {
			return true;
		}
	}

	public static Boolean changeProductQuantity(String sku, int quantity) {
		Stock stock = new Stock(TransactionService.getProductCart(sku).getKey().getProduct(), quantity);
		TransactionService.productdelete(sku);
		if (TransactionService.storeProduct(stock)) {
			return true;
		}
		return false;
	}

	public static String storeProduct(String sku, int quantity) {
		Stock stock = new Stock(TransactionService.getProductStock(sku), quantity);
		if (TransactionService.storeProduct(stock)) {
			return "Produto adicionado ao carrinho!\n\"Ao final da compra será possivel modificar a quantidade.\n";
		}
		return "Não foi possivel adicionar o produto!";
	}

	public static Boolean productDelete(String sku) {
		return TransactionService.productdelete(sku);
	}
	
	public static String customerData(String name, String cpf, String payment, String card) {
		if (name != null && payment != null && regex.validateCpf(cpf)) {
			Customer customer = new Customer(name, cpf, payment, TransactionService.listCart());
			String dice = "";
			if (!card.isEmpty()) {
				customer.setCard(card);
				if (payment.equals("1") || payment.equals("4") ) {
					dice += "Cartão: " + customer.getCard();
				} else if (payment.equals("3")) {
					dice += "Chave Pix: " + customer.getCard();
					
				}
			}
			if (TransactionService.stockPurchase(customer)) {
				dice += "\nPagamento: " + customer.getPayment() +"\nNome: " + customer.getName() +"\nCpf: " + customer.getCpf() 
				+ "\nData e Horário: " + customer.getDate() + " às " + customer.getHour(); 
				return dice;
			}
		}
		return null;
	}
	
	public static Double totalPriceCart() {
		return TransactionService.totalPriceCart();
	}
	
	public static String customerData(String name, String payment, String card) {
		String dice = "";
		if (name != null && payment != null ) {
			Customer customer = new Customer(name, payment, TransactionService.listCart());
			if (!card.isEmpty()) {
				customer.setCard(card);
				if (payment.equals("1") || payment.equals("4") ) {
					dice += "Cartão: " + customer.getCard();
				} else if (payment.equals("3")) {
					dice += "Chave Pix: " + customer.getCard();
				}
			}
			if (TransactionService.stockPurchase(customer)) {
				dice += "\nPagamento: " + customer.getPayment() +"\nNome: " + customer.getName()  
				+ "\nData e Horário: " + customer.getDate() + " às " + customer.getHour(); 
				return dice;
			}
		}
		return null;
	}

	public static String listCart() {
		String descriptonFormat = "";
		String product = "";
		Double valorTotal = 0.0;
		HashMap<Stock, Double> listCart = TransactionService.listCart();
		for (HashMap.Entry<Stock, Double> list : listCart.entrySet()) {
			valorTotal = list.getValue();
			product = "Sku: " + list.getKey().getProduct().getSku() + ", " + list.getKey().getProduct().getType()
					+ ", cor: " + list.getKey().getProduct().getColor() + ", tam: "
					+ list.getKey().getProduct().getSize();
			descriptonFormat += String.format("%s, valor unitário: %.2f x%s; valor total: R$%.2f\n", product,
					list.getKey().getProduct().getSellPrice(), list.getKey().getQuantity(), valorTotal);
		}
		return descriptonFormat;
	}
	
	public static void reduceProductStock() {
		HashMap<Stock, Double> cart = TransactionService.listCart();
		StockService.reduceProductStock(cart);
	}
	
	public static String listHistory() {
		 HashMap<Integer, Customer> list =  TransactionService.listHistory();
		 String dice = "";
		 for ( HashMap.Entry<Integer, Customer> product: list.entrySet()) {
			 dice += "Id compra: " + product.getKey() + ", Valor total: R$"+ product.getValue().getTotalPrice() +"\n";
		 }
		return dice;
	}

	
public static String purchaseHistoryId(Integer id) {

		if (TransactionService.PurchaseverifyId(id)) {
			String dice = "";
			Customer customer = TransactionService.productHistorytId(id);
			dice +="\n\n____________________CARRINHO__________________\n";
			dice += customer.getCart();
			dice += "\nValor total: R$" + customer.getTotalPrice();
			dice +="\n______________________________________________\n";
			if (customer.getPayment().equals("Débito") || customer.getPayment().equals("Cartão de crédito") ) {
				dice += "Cartão: " + customer.getCard();
			} else if (customer.getPayment().equals("Pix")) {
				dice += "Chave Pix: " + customer.getCard();
				
			}
			if (customer.getCpf() == null) {
				dice += "\nPagamento: " + customer.getPayment() +"\nNome: " + customer.getName()  
				+ "\nData e Horário: " + customer.getDate() + " às " + customer.getHour(); 
				return dice;
			} else {
				dice += "\nPagamento: " + customer.getPayment() +"\nNome: " + customer.getName() +"\nCpf: " + customer.getCpf() 
				+ "\nData e Horário: " + customer.getDate() + " às " + customer.getHour(); 
				return dice;
			}
		}
		return "Compra não encontrada!";	
	}

	public static void cartClear() {
		TransactionService.cartClear();
	}
}
