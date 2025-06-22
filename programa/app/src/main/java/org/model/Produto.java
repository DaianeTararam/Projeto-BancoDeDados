package org.model;

public class Produto {
	
	private long codigo;
	private String nome;
	private Categoria categoria = new Categoria();
	private double valorUnitario;
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@Override
	public String toString() {
		return "Produto [codigo= " + codigo + ", nome= " + nome + ", "
				+ "valorUnitario= " + valorUnitario + "]";
	}
	
	
}
