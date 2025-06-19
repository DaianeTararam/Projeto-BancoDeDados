package model;

public class Pagamento {
	
	private int codigo;
	private Comanda comanda = new Comanda();
	private String formaPagamento;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Comanda getComanda() {
		return comanda;
	}
	
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	
	public String getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
