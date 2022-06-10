package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
	MALE("31", "Masculino"),
	FEMALE("32", "Feminino"),
	BABY("33", "Infantil");

	private final String value;
	private final String description;
	
	CategoryEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	private static final Map<String, CategoryEnum> Lookup = new HashMap<String, CategoryEnum>();

	static {
		for (CategoryEnum keyValue : EnumSet.allOf(CategoryEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static CategoryEnum get(String key) {
		return Lookup.get(key);
	}
	public static CategoryEnum getByDescription(String description) {
		for (CategoryEnum keyValue : EnumSet.allOf(CategoryEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static CategoryEnum getByValue(String value) {
		for (CategoryEnum keyValue : EnumSet.allOf(CategoryEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
	
	
}
