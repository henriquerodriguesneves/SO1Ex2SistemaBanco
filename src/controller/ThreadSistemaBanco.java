package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ThreadSistemaBanco extends Thread {
	
	protected int numConta;
	protected Semaphore semaforoSaque;
	protected Semaphore semaforoDeposito;
	protected double saldoConta;
	protected double valorTransacao;
	
	public ThreadSistemaBanco (int numConta, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.numConta = numConta;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
	}
	
	public void run() {
		recebe();
		transacao();
	}
	
	public void recebe() {
		saldoConta = ((Math.random() * 8000) + 1000);
		valorTransacao = ((Math.random() * 2000) + 100);
	}
	
	public void transacao() {
		int tipoTransacao = (int)((Math.random() * 2) + 1);
		if (tipoTransacao == 1) {
			try {
				semaforoSaque.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoSaque.release();
			}
		} 
		
		else {
			try {
				semaforoDeposito.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoDeposito.release();
			}
		}

	}
	
	public void saque() {
		
		saldoConta = saldoConta - valorTransacao;
		String vlrTransacao = new DecimalFormat("###.##").format(valorTransacao);
		String vlrSaldo = new DecimalFormat("###.##").format(saldoConta);
		//System.out.println("Conta: " + numConta + "\nSaldo de R$" + vlrSaldo);
		System.out.println("Conta: " + numConta + //"\nSaldo de R$" + vlrSaldo +
				"\nSaque no valor de R$" + vlrTransacao+ "\nSaldo atual R$" + vlrSaldo);
		System.out.println("\n");
	}
	
	public void deposito() {
		
		saldoConta = saldoConta + valorTransacao;
		String vlrTransacao = new DecimalFormat("###.##").format(valorTransacao);
		String vlrSaldo = new DecimalFormat("###.##").format(saldoConta);
		//System.out.println("Conta: " + numConta + "\nSaldo de R$" + vlrSaldo);
		//saldoConta = saldoConta + valorTransacao;
		System.out.println("Conta: " + numConta + //"\nSaldo de R$" + vlrSaldo + 
				"\nDepósito no valor de R$" + vlrTransacao + "\nSaldo atual R$" + vlrSaldo);
		System.out.println("\n");
	}
}