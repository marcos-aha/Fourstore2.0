package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DepartmentEnum {
	CLOTHING("61", "Vestuário"),
	SHOES("62", "Calçados"),
	PERFUMERY("63", "Perfumaria"),
	ACCESSORIES("64", "Acessórios");
	
	private final String value;
	private final String description;
	

	DepartmentEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}


	public String getValue() {
		return value;
	}


	public String getDescription() {
		return description;
	}
	
	private static final Map<String, DepartmentEnum> Lookup = new HashMap<String, DepartmentEnum>();

	static {
		for (DepartmentEnum keyValue : EnumSet.allOf(DepartmentEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static DepartmentEnum get(String key) {
		return Lookup.get(key);
	}
	public static DepartmentEnum getByDescription(String description) {
		for (DepartmentEnum keyValue : EnumSet.allOf(DepartmentEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static DepartmentEnum getByValue(String value) {
		for (DepartmentEnum keyValue : EnumSet.allOf(DepartmentEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
}
