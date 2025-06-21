package org.model;

import java.time.LocalDate;

public class Pedido {
	
	private int codigo;
	private LocalDate dataHora = LocalDate.now();;
	private double valorTotal;
	private Comanda comanda = new Comanda();
	private FormaPagamento formaPagamento = new FormaPagamento();
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Comanda getComanda() {
		return comanda;
	}
	
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
