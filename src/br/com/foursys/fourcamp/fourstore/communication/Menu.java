package br.com.foursys.fourcamp.fourstore.communication;





import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.controller.TransactionController;
import br.com.foursys.fourcamp.fourstore.controller.productController;

import br.com.foursys.fourcamp.fourstore.enums.EnumStock;
import br.com.foursys.fourcamp.fourstore.enums.MenuEnum;
import br.com.foursys.fourcamp.fourstore.enums.SellEnum;



public class Menu {

	public static void menuMain(Scanner input) {
		String option;
		while (true) {
			System.out.println("\n_________MENU PRINCIPAL________\n");
			for (MenuEnum m : MenuEnum.values()) {
				System.out.println("           " + m.getOpcao() + "- " + m.getDescription());
			}
			System.out.println("_______________________________\n");
			System.out.print("Digite a opção desejada: ");
			option = input.next();

			if (option.equals("0")) {
				System.out.println("Sistema encerrado!");
				break;
			} else if (option.equals("1")) {
				menuSell(input);
			} else if (option.equals("2")) {
				menuStock(input);
			}
		}
	}

	private static void menuStock(Scanner input) {
		String option;
		while (true) {

			System.out.println("\n_________________MENU ESTOQUE_______________\n");
			for (EnumStock m : EnumStock.values()) {
				System.out.println("           " + m.getOption() + "- " + m.getDescription());
			}
			System.out.println("____________________________________________\n");

			System.out.print("Digite a opção desejada: ");
			option = input.next();

			if (option.equals("0")) {
				break;
			} else if (option.equals("1")) {
				StockMenu.newRegister(input);
			} else if (option.equals("2")) {
				System.out.println(productController.listAll());
			} else if (option.equals("3")) {
				System.out.println("Digite o sku do produto: ");
				String sku = input.next();
				System.out.println(productController.findProduct(sku));
			} else if (option.equals("4")) {
				StockMenu.updateProduct(input);
			} else if (option.equals("5")) {
				System.out.println("Digite o sku do produto que deseja remover do estoque: ");
				String sku = input.next();
				System.out.println("Tem certeja que deseja remover o produto: |1- SIM | 0- NÃO");
				String option3 = input.next();
				if (option3.equals("1")) {
					if (productController.validateSku(sku)) {
						System.out.println(productController.deleteProduct(sku));
					} else {
						System.out.println("Sku não encontrado!");
					}
				}
			}
		}

	}

	@SuppressWarnings("unused")
	private static void menuSell(Scanner input) {
		String option;
		while (true) {

			System.out.println("\n_________________MENU VENDAS_______________\n");
			for (SellEnum m : SellEnum.values()) {
				System.out.println("           " + m.getValue() + "- " + m.getDescription());
			}
			System.out.println("____________________________________________\n");

			System.out.print("Digite a opção desejada: ");
			option = input.next();

			if (option.equals("0")) {
				break;
			} else if (option.equals("1")) {
				SellMenu.newSell(input);
			} else if (option.equals("2")) {
				SellMenu.cart();
			} else if (option.equals("3")) {
				if (TransactionController.listCart().isEmpty()) {
					System.out.println("Não existe compra em processo!");
				} else {
					SellMenu.payment(input);
				}

			} else if (option.equals("4")) {
				System.out.println(productController.listAll());
			} else if (option.equals("5")) {
				System.out.println(TransactionController.listHistory());
				SellMenu.historyDetail(input);
			} else if (option.equals("6")) {
				if (TransactionController.listCart().isEmpty()) {
					System.out.println("Não existe compra em processo!");
				} else {
					String option1;
					while (true) {
						System.out.println("Certeza que deseja cancelar compra? |1- SIM| 0- NÃO");
						option = input.next();
						if (option.equals("1")) {
							TransactionController.cartClear();
							break;
						} else if (option.equals("0")) {
							break;
						} else {
							System.out.println("Digite uma das opções!");
						}
					}
				}

			}

		}
	}

	
	

	// para dá uma pausa quando cair no primeiro try, senão ele retorna a
	// pergunta da quantidade antes da mensagem de erro da exceção.

	public static void ThreadDelay() {
		try {
			Thread.sleep(1L);
		} catch (InterruptedException e) {
			System.out.println("Nunca vai cair aqui");
		}
	}

}
