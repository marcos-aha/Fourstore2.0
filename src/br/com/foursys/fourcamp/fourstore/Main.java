package br.com.foursys.fourcamp.fourstore;

import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.communication.Menu;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Seja bem vindo a FourStore!");
		Menu.menuMain(input);
		
	}
}
