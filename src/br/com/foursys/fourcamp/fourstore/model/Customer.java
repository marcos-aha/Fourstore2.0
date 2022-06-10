package br.com.foursys.fourcamp.fourstore.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.enums.paymentEnum;

public class Customer {
	private String name;
	private String cpf;
	private String payment;
	private String date;
	private String hour;
	private HashMap<Stock, Double> cart;
	private Double totalPrice;
	private String card;
	
	private Calendar dateHour = Calendar.getInstance();
	private SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yyyy ; HH:mm:ss");
	private String[] dh = dateHourFormat.format(dateHour.getTime()).split(";");
	public Customer(String name, String cpf, String payment, HashMap<Stock, Double> cart) {
		this.name = name;
		this.cpf = cpf;
		this.payment = paymentEnum.get(payment).getDescription();;
		this.date = dh[0];
		this.hour = dh[1];
		this.cart = cart;
	}

	public Customer(String name, String payment, HashMap<Stock, Double> cart) {
		this.name = name;
		this.payment = paymentEnum.get(payment).getDescription();
		this.date = dh[0];
		this.hour = dh[1];
		this.cart = cart;
	}
	
	
	
	public void setCard(String card) {
		this.card = card;
	}

	public String getCard() {
		return card;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public HashMap<Stock, Double> getCart() {
		return cart;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPayment() {
		return payment;
	}

	public String getDate() {
		return date;
	}

	public String getHour() {
		return hour;
	}

	public Customer () {}

	@Override
	public String toString() {
		return "[Valor total= R$" + totalPrice + "]";
	}
	
	
}


