package org.model;

import java.util.List;
import java.util.ArrayList;

public class Comanda {
	
	private int codigo;
	private List<Item> itens = new ArrayList<>();
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	
}
