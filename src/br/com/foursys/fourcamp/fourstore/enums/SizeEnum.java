package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {

	PP("22"),
	P("23"),
	M("24"),
	G("25"),
	GG("26");

	private final String value;
	SizeEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	private static final Map<String, SizeEnum> Lookup = new HashMap<String, SizeEnum>();

	static {
		for (SizeEnum keyValue : EnumSet.allOf(SizeEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static String get(String key) {
		return Lookup.get(key).toString();
	}
	public static SizeEnum getByValue(String value) {
		for (SizeEnum keyValue : EnumSet.allOf(SizeEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
}
