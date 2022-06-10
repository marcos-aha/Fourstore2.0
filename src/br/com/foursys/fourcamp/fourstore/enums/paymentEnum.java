package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum paymentEnum {
	DEBITO("1", "Débito"),
	DINHEIRO("2", "Dinheiro"),
	PIX("3", "Pix"),
	CARTAO_CREDITO("4", "Cartão de Crédito");
	
	private final String value;
	private final String description;
	
	paymentEnum(String value, String descrString) {
		this.value = value;
		this.description = descrString;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	private static final Map<String, paymentEnum> Lookup = new HashMap<String, paymentEnum>();

	static {
		for (paymentEnum keyValue : EnumSet.allOf(paymentEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static paymentEnum get(String key) {
		return Lookup.get(key);
	}
	public static paymentEnum getByDescription(String description) {
		for (paymentEnum keyValue : EnumSet.allOf(paymentEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static paymentEnum getByValue(String value) {
		for (paymentEnum keyValue : EnumSet.allOf(paymentEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
	
	
}

