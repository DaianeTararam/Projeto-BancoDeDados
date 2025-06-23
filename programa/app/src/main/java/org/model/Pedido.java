package org.model;

import java.time.LocalDate;

public class Pedido {
	
	private long codigo;
	private LocalDate dataHora = LocalDate.now();
	private Comanda comanda = new Comanda();
	private FormaPagamento formaPagamento = new FormaPagamento();
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
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
