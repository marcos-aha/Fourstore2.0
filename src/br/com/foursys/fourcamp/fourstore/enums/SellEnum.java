package br.com.foursys.fourcamp.fourstore.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SellEnum {
	COMPRAR("1", "Comprar"),
	CARRINHO("2", "Carrinho"),
	FINALIZAR_COMPRA("3", "Finalizar compra"),
	LISTA_PRODUTOS("4", "Lista de produtos"),
	HISTORICO_VENDAS("5", "Histórico de Vendas"),
	CANCELAR_COMPRA("6", "Cancelar compra"),
	VOLTAR("0", "Voltar");

	private final String value;
	private final String description;
	
	SellEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}
	public String  getValue() {
		return value;
	}
	public String  getDescription() {
		return description;
	}
	
	private static final Map<String, SellEnum> Lookup = new HashMap<String, SellEnum>();

	static {
		for (SellEnum keyValue : EnumSet.allOf(SellEnum.class))
			Lookup.put(keyValue.getValue(), keyValue);
	}

	public static SellEnum get(String key) {
		return Lookup.get(key);
	}
	public static SellEnum getByDescription(String description) {
		for (SellEnum keyValue : EnumSet.allOf(SellEnum.class)) {
			if (keyValue.getDescription().equals(description))
				return keyValue;
		}
		return null;
	}
	public static SellEnum getByValue(String value) {
		for (SellEnum keyValue : EnumSet.allOf(SellEnum.class)) {
			if (keyValue.getValue().equals(value))
				return keyValue;
		}
		return null;
	}
}
