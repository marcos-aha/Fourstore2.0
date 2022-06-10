package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {
	RED("11", "Vermelho"),
	YELLOW("12", "Amarelo"),
	BLUE("13", "Azul"),
	GREEN("14", "Verde"),
	BROWN("15", "Marrom"),
	PINK("16", "Rosa"),
	ORANGE("17", "Laranja"),
	BLACK("18", "Preto"),
	GREY("19", "Cinza"),
	PURPLE("20", "Roxo"),
	WHITE("22", "Branco");

	private final String value;
	private final String description;
	ColorEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public String  getValue() {
		return value;
	}
	public String  getDescription() {
		return description;
	}
	
	private static final Map<String, ColorEnum> Lookup = new HashMap<String, ColorEnum>();

	static {
		for (ColorEnum keyValue : EnumSet.allOf(ColorEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static ColorEnum get(String key) {
		return Lookup.get(key);
	}
	public static ColorEnum getByDescription(String description) {
		for (ColorEnum keyValue : EnumSet.allOf(ColorEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static ColorEnum getByValue(String value) {
		for (ColorEnum keyValue : EnumSet.allOf(ColorEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
	
	
}
