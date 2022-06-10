package br.com.foursys.fourcamp.fourstore.enums;

public enum EnumStock {
	CADASTRAR_PRODUTO("1", "Cadastrar produto"),
	LISTA_DE_PRODUTOS("2", "Lista de produtos"),
	PESQUISAR_PRODUTO("3", "Pesquisar produto"),
	ATUALIZAR_PRODUTO("4", "Atualizar produto"),
	REMOVER_PRODUTO("5", "Remover produto do estoque"),
	VOLTAR("0", "Voltar");
	
	private final String option, description;
	EnumStock(String option, String description) {
		this.option = option;
		this.description = description;
	}
	public String getOption() {
		return option;
	}
	public String getDescription() {
		return description;
	}
	
	
	
}
