package br.com.foursys.fourcamp.fourstore.enums;

public enum MenuEnum {
	VENDA("1", "Venda"),
	ESTOQUE("2", "Estoque"),
	SAIR("0", "Sair");
	
	private final String opcao;
	private final String description;
	
	MenuEnum(String opcao, String description) {
		this.opcao = opcao;
		this.description = description;
	}
	
	
	public String getOpcao() {
		return opcao;
	}
	
	public String getDescription() {
		return description;
	}


}
