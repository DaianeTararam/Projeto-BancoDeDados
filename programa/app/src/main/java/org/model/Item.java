package org.model;

import java.time.LocalDate;

public class Item {
	
	private Produto produto = new Produto();
	private int quantidade;
	private LocalDate dataVenda;
	
	public Item(Produto produto2, int i, LocalDate of) {
        //TODO Auto-generated constructor stub
    }

    public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
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
