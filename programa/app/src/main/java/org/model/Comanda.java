package org.model;

import java.util.List;
import java.util.ArrayList;


public class Comanda {
	private long codigo;
	private List<Item> itens = new ArrayList<>();
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	
}
