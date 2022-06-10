package br.com.foursys.fourcamp.fourstore.communication;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.controller.TransactionController;
import br.com.foursys.fourcamp.fourstore.controller.productController;
import br.com.foursys.fourcamp.fourstore.enums.paymentEnum;


public class SellMenu {

	public static void newSell(Scanner input) {

		String sku;
		@SuppressWarnings("unused")
		Boolean validate;
		String option2 = "", option3 = "";
		int quantity;
		int option = -2;
		while (true) {
			System.out.println("Digite o sku do produto: ");
			sku = input.next();
			while (true) {
				try {
					System.out.println("Digite a quantidade: ");
					input.nextLine();
					quantity = input.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!");
					Menu.ThreadDelay();
					continue;
				}
				break;
			}
			if (TransactionController.validationSell(sku, quantity)) {
				if (TransactionController.ProductSearchCart(sku)) {
					System.out.println("Já possui este produto no carrinho!");
				} else {
					System.out.println(TransactionController.storeProduct(sku, quantity));
				}

			} else {
				System.out.println("Não foi possível adicionar o produto ao carrinho!");
				System.out.println("Quantidade disponivel: " + productController.getQuantity(sku) + "\n");
			}

			while (option != -1) {
				try {
					System.out.println("Deseja adicionar outro produto?  |1 - SIM | 2- NÃO | 0- VOLTAR");
					input.nextLine();
					option = input.nextInt();
				} catch (InputMismatchException f) {
					System.err.println("Digite apenas uma das opções!");
					continue;
				}
				if (option == 2) {
					option = -1;
					cart();
					while (true) {
						System.out.println("Deseja alterar algum item do carrinho? |1- SIM | 2- NÃO | 0- VOLTAR");
						option2 = input.next();

						if (option2.equals("1")) {
							while (true) {
								System.out.println(
										"Deseja alterar quantidade ou remover item: |1- Quantidade |2- Remover |0- Voltar");
								option3 = input.next();

								if (option3.equals("1")) {
									changePurchaseQuantity(input);

								} else if (option3.equals("2")) {
									StockMenu.productDelete(input);
								} else if (option3.equals("0")) {
									break;
								} else {
									System.out.println("Digite uma das opções!");
								}
							}
						} else if (option2.equals("2")) {
							payment(input);
							break;
						}
						if (option2.equals("0") || option2.equals("0") || option3.equals("0")) {
							break;
						}
					}
				} else if (option == 1 || option == 0) {
					break;
				} else {
					System.out.println("Digite uma das opções!");
				}
			}
			if (option == -1 || option == 0 || option2.equals("2")) {
				break;
			}
		}

	}

	public static void cart() {
		System.out.println("____________________CARRINHO__________________");
		System.out.println(TransactionController.listCart());
		System.out.printf("Valor total: R$%.2f.\n", TransactionController.totalPriceCart());
		System.out.println("______________________________________________");
	}

	public static void changePurchaseQuantity(Scanner input) {
		String sku;
		int quantity;
		System.out.println("Digite o sku do produto:");
		sku = input.next();
		if (TransactionController.ProductSearchCart(sku)) {
			while (true) {
				try {
					System.out.println("Digite a quantidade do produto que deseja comprar: ");
					quantity = input.nextInt();
					if (TransactionController.changeProductQuantity(sku, quantity)) {
						System.out.println("Quantidade do produto alterada com sucesso!");
						cart();
					} else {
						System.out.println("Não foi possivel alterar quantidade!");
					}
					break;
				} catch (Exception e) {
					System.out.println("Digite somente números inteiros!");
				}
			}
		} else {
			System.out.println("Não existe produto com essa sku no carrinho!");
		}
	}

	public static void payment(Scanner input) {
		String payment, option, option1, cpf = "", name, card = "";
		System.out.println("\n_________________FORMA DE PAGAMENTO_______________\n");
		for (paymentEnum m : paymentEnum.values()) {
			System.out.println("           " + m.getValue() + "- " + m.getDescription());
		}
		System.out.println("____________________________________________________\n");
		System.out.printf("Valor total: R$%.2f.\n", TransactionController.totalPriceCart());
		System.out.println("____________________________________________________\n");
		System.out.println("Qual a forma de pagamento? ");
		payment = input.next();

		if (payment.equals("1") || payment.equals("4")) {
			System.out.println("Informe número do cartão: ");
			card = input.next();
		} else if (payment.equals("3")) {
			Random random = new Random();
			card = String.valueOf(random.nextInt(785412354));
			System.out.println("Chave pix armazenada com sucesso!");
		}
		System.out.println("Digite o nome: ");
		input.nextLine();
		name = input.nextLine();
		while (true) {
			System.out.println("Deseja cpf na nota: |1- SIM |0- NÃO");
			option = input.next();
			if (option.equals("1")) {
				System.out.println("Digite o cpf: ");
				cpf = input.next();
				cart();
				option1 = TransactionController.customerData(name, cpf, payment, card);
				break;
			} else if (option.equals("0")) {
				cart();
				option1 = TransactionController.customerData(name, payment, card);
				break;
			} else {
				System.out.println("Digite uma opção válida!");
				continue;
			}
		}
		if (option1 == null) {
			System.out.println("Não foi possivel finalizar sua compra!");
		} else {
			System.out.println(option1);
			TransactionController.reduceProductStock();
			TransactionController.cartClear();
			System.out.println("Compra finalizada!");
		}

	}

	public static void historyDetail(Scanner input) {
		String option;
		while (true) {
			System.out.println("Para saber detalhes de uma compra digite: |1- Id de compra |0- Voltar");
			option = input.next();
			if (option.equals("1")) {
				while(true) {
					try {
					System.out.println("Digite o id de compra: ");
					int id = input.nextInt();
					System.out.println(TransactionController.purchaseHistoryId(id));
					break;
					} catch (Exception e) {
						System.out.println("Id inválido!");
					}
				}
			} else if (option.equals("0")) {
				break;
			} else {
				System.out.println("Digite uma opção válida!");
			}
		}
	}

}
