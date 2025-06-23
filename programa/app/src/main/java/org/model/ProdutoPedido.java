package org.model;

import java.time.LocalDate;

public class ProdutoPedido {
	
	private Produto produto = new Produto();
	private Pedido pedido = new Pedido();
	private int quantidade;
	private LocalDate dataVenda;

    public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	//É necessário para o filtro de data
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	
}
