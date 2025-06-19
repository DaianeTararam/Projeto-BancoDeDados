package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Comanda {
	
	private int codigo;
	private List<Item> itens = new ArrayList<>();
	private LocalDate dataHora = LocalDate.now();
	private double valorTotal;
	
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
	
	
}
