package view;

import java.util.concurrent.Semaphore;

import controller.ThreadSistemaBanco;

public class Principal {

	public static void main(String[] args) {
		
		int permissaoSaque = 1;
		int permissaoDeposito = 1;
		Semaphore semaforoSaque = new Semaphore(permissaoSaque);
		Semaphore semaforoDeposito = new Semaphore(permissaoDeposito);
		
		for (int numConta = 1; numConta <= 20; numConta++) {
			Thread tTransacao = new ThreadSistemaBanco(numConta, semaforoSaque, semaforoDeposito);
			tTransacao.start();
		}

	}

}
