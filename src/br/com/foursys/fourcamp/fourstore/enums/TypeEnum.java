package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {
	SWEATSHIRT("51", "Moletom"),
	PANTS("52", "Calça"),
	SHOES("53", "Sapatos"),
	SHIRTS("54", "Camisas"),
	UNDERWEAR("55", "Cueca"),
	DRESSES("56", "Vestidos"),
	PERFUMES("57", "Perfumes");

	private final String value;	
	private final String description;	
	
	TypeEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}
	public String getDescription() {
		return description;
	}
	
	private static final Map<String, TypeEnum> Lookup = new HashMap<String, TypeEnum>();

	static {
		for (TypeEnum keyValue : EnumSet.allOf(TypeEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static TypeEnum get(String key) {
		return Lookup.get(key);
	}
	
	public static TypeEnum getByDescription(String description) {
		for (TypeEnum keyValue : EnumSet.allOf(TypeEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static TypeEnum getByValue(String value) {
		for (TypeEnum keyValue : EnumSet.allOf(TypeEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}

}
