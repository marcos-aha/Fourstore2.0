package br.com.foursys.fourcamp.fourstore.communication;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.controller.TransactionController;
import br.com.foursys.fourcamp.fourstore.controller.productController;
import br.com.foursys.fourcamp.fourstore.enums.CategoryEnum;
import br.com.foursys.fourcamp.fourstore.enums.ColorEnum;
import br.com.foursys.fourcamp.fourstore.enums.DepartmentEnum;
import br.com.foursys.fourcamp.fourstore.enums.SizeEnum;
import br.com.foursys.fourcamp.fourstore.enums.TypeEnum;

public class StockMenu {

	public static void newRegister(Scanner input) {
		String option, sku, description;
		int quantity;
		Double buyPrice, sellPrice;
		System.out.println(
				"Deseja realizar cadastro do produto por sku ou de forma manual?  | 1- SKU | 2- MANUAL | 0- SAIR");
		option = input.next();
		if (option.equals("1")) {

			System.out.println("Digite sku do produto: ");
			sku = input.next();
			if (!productController.validateSku(sku)) {
				while (true) {
					try {
						System.out.println("Digite a quantidade do produto: ");
						input.nextLine();
						quantity = input.nextInt();
					} catch (InputMismatchException e) {
						System.err.println("Digite somente números!");
						continue;
					}
					System.out.println("Digite o preço de compra: ");
					buyPrice = input.nextDouble();
					System.out.println("Digite o preço de venda: ");
					sellPrice = input.nextDouble();
					System.out.println("Digite a descrição do produto: ");
					input.nextLine();
					description = input.nextLine();
					System.out.println(productController.newRegister(sku, quantity, buyPrice, sellPrice, description));
					break;
				}
			} else {
				System.out.println("Produto já possui cadastro!");
			}

		} else if (option.equals("2")) {
			newRegisterManual(input);
		}
	}

	public static void newRegisterManual(Scanner input) {
		@SuppressWarnings("unused")
		String option, optionvalidate, description;
		String sku = "";
		int quantity;
		Double buyPrice, sellPrice;
		while (true) {
			System.out.println("\n___________TIPO___________\n");
			for (TypeEnum optionEnum : TypeEnum.values()) {
				System.out.println(optionEnum.getValue() + "- " + optionEnum.getDescription());
			}
			System.out.println("___________________________");
			try {
				System.out.println("Digite uma das opções: ");
				option = input.next();
				optionvalidate = TypeEnum.getByValue(option).getValue();
				sku += option;
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida!");
				continue;
			}
		}

		while (true) {
			System.out.println("\n___________COR___________\n");
			for (ColorEnum optionEnum : ColorEnum.values()) {
				System.out.println(optionEnum.getValue() + "- " + optionEnum.getDescription());
			}
			System.out.println("__________________________");
			try {
				System.out.println("Digite uma das opções: ");
				option = input.next();
				optionvalidate = ColorEnum.getByValue(option).getValue();
				sku += option;
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida!");
				continue;
			}
		}

		while (true) {
			System.out.println("\n___________TAMANHO___________\n");
			for (SizeEnum optionEnum : SizeEnum.values()) {
				System.out.println(optionEnum.getValue() + "- " + optionEnum);
			}
			System.out.println("_____________________________");
			try {
				System.out.println("Digite uma das opções: ");
				option = input.next();
				optionvalidate = SizeEnum.getByValue(option).getValue();
				sku += option;
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida!");
				continue;
			}
		}
		while (true) {
			System.out.println("\n___________CATEGORIA___________\n");
			for (CategoryEnum optionEnum : CategoryEnum.values()) {
				System.out.println(optionEnum.getValue() + "- " + optionEnum.getDescription());
			}
			System.out.println("_________________________________");
			try {
				System.out.println("Digite uma das opções: ");
				option = input.next();
				optionvalidate = CategoryEnum.getByValue(option).getValue();
				sku += option;
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida!");
				continue;
			}
		}
		while (true) {
			System.out.println("\n___________DEPARTAMENTO___________\n");
			for (DepartmentEnum optionEnum : DepartmentEnum.values()) {
				System.out.println(optionEnum.getValue() + "- " + optionEnum.getDescription());
			}
			System.out.println("_________________________________");
			try {
				System.out.println("Digite uma das opções: ");
				option = input.next();
				optionvalidate = DepartmentEnum.getByValue(option).getValue();
				sku += option;
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida!");
				continue;
			}
		}
		if (!productController.validateSku(sku)) {
			while (true) {
				try {
					System.out.println("\nDigite a quantidade do produto: ");
					input.nextLine();
					quantity = input.nextInt();
					System.out.println("Digite o preço de compra: ");
					buyPrice = input.nextDouble();
					System.out.println("Digite o preço de venda: ");
					sellPrice = input.nextDouble();
					break;
				} catch (Exception e) {
					System.out.println("Digite valores válidos!");
					continue;
				}
			}
			System.out.println("Digite a descrição do produto: ");
			input.nextLine();
			description = input.nextLine();
			System.out.println(productController.newRegister(sku, quantity, buyPrice, sellPrice, description));
		} else {
			System.out.println("Produto já possui cadastro!");
		}

	}

	public static void productDelete(Scanner input) {
		String sku;
		String option;
		System.out.println("Digite o sku do item que deseja remover: ");
		sku = input.next();
		if (TransactionController.ProductSearchCart(sku)) {
			while (true) {
				System.out.println("Tem certeza que deseja remover produto? |1- SIM | 0- NÃO");
				option = input.next();
				if (option.equals("1")) {
					if (TransactionController.productDelete(sku)) {
						System.out.println("Produto removido!");
						break;
					} else {
						System.out.println("Não foi possível remover produto!");
						break;
					}
				} else if (option.equals("0")) {
					break;
				} else {
					System.out.println("Digite uma opção válida!");
				}
			}
		}

	}

	public static void updateProduct(Scanner input) {
		String sku, option, description = "";
		int quantity;
		Double buyPrice = 0.0, sellPrice = 0.0;
		System.out.println("Digite o sku do produto que deseja atualizar: ");
		sku = input.next();
		if (productController.validateSku(sku)) {
			System.out.println("Digite a quantidade que deseja acrescentar ao estoque: ");
			quantity = input.nextInt();
			System.out.println("Deseja atualizar o preço:  | 1- SIM | 0- NÃO ");
			option = input.next();
			if (option.equals("1")) {
				System.out.println("Digite o preço de compra: ");
				buyPrice = input.nextDouble();
				System.out.println("Digite o preço de venda: ");
				sellPrice = input.nextDouble();
				System.out.println("Digite a descrição: ");
				input.nextLine();
				description = input.nextLine();
				Menu.ThreadDelay();
			}

			if (option.equals("1")) {
				System.out.println(productController.updateProduct(sku, quantity, buyPrice, sellPrice, description));
			} else if (option.equals("0")) {
				System.out.println(productController.updateProduct(sku, quantity));
			}
		} else {
			System.out.println("Sku não encontrada!");
		}

	}

}
