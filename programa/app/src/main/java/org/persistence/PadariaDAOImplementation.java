package org.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.model.Categoria;
import org.model.Comanda;
import org.model.Item;
import org.model.Produto;


public class PadariaDAOImplementation implements PadariaDAO{
	private Connection c = null;
        private String hostName = "localhost";
	private String userName = "sa";
	private String password = "123456";
        private String dbName = "padariaBD";
	
	//carrega a database
	public PadariaDAOImplementation() {
		try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
		    c = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
						hostName, dbName, userName, password)
				);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) { 
            e.printStackTrace();
        }
	}
	
	//metodo só para o teste do botao salvar
	public List<Produto> getProdutos(){
		String sql = "SELECT codigo, nome, valorUnitario FROM Produto";
		PreparedStatement ps;
		List<Produto> produtos = new ArrayList<>();
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			Produto produto = new Produto();
			produto.setCodigo(rs.getInt("codigo"));
			produto.setNome(rs.getString("nome"));
			produto.setValorUnitario(rs.getDouble("valorUnitario"));
			
			produtos.add(produto);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return produtos;
	}

	//verifica se o codigo da comanda digitado existe e gera Comanda (gerarComanda(rs))
	public Comanda getComanda(long codigo){
		String sql = "SELECT * FROM Comanda WHERE codigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return gerarComanda(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Comanda gerarComanda(ResultSet rs){
		Comanda comanda = new Comanda();
		try {
			comanda.setCodigo(rs.getLong("codigo"));
			comanda.setItens(getItens(comanda.getCodigo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comanda;
	}
	
	//seleciona os itens vincuados ao codigo da comanda e cria uma lista de itens (o gerarItem(rs) cria Item para adicionar na lista)
	public List<Item> getItens(long comanda){
		List<Item> listaItens = new ArrayList<>();
		String sql = "SELECT produtoCodigo, quantidade FROM ItemComanda WHERE comandaCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, comanda);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				listaItens.add(gerarItem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaItens;
	}

	private Item gerarItem(ResultSet rs){
		Item item = new Item();
			try {
				item.setProduto(getProduto(rs.getLong("produtoCodigo")));
				item.setQuantidade(rs.getInt("quantidade"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return item;
	}


	//pega as informacoes do produto pelo codigo
	public Produto getProduto(long codigo){
		String sql = "SELECT * FROM Produto WHERE codigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.first()){
				return gerarProduto(rs); //metodo separado p/ gerar Produto
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; //se o codigo nao existe, retorna null
	}

	private Produto gerarProduto(ResultSet rs){
		Produto produto = new Produto();;
		try {
			produto.setCodigo(rs.getInt("codigo"));
			produto.setNome(rs.getString("nome"));
			Categoria categoria = new Categoria();
			categoria = getCategoria(rs.getLong("categoriaCodigo"));
			produto.setCategoria(categoria);
			produto.setValorUnitario(rs.getDouble("valorUnitario"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}
	//metodo auxiliar p/ criar Categoria p/ ser adicionado em Produto
	private Categoria getCategoria(long codigo){
		Categoria categoria = new Categoria();
		String sql = "SELECT nome FROM Categoria WHERE codigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.first()){
				categoria.setCodigo(codigo);
				categoria.setNome(rs.getString("nome"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoria;
	}
	//esse metodo foi adicionado antes, precisa rever a utilidade dele, provavel que seja
	//para mostrar no TableView, pq precisa pegar alguns valores especificos (codigo, nome e valor unitario do produto e a quantidade pedida) 
	public List<Item> lerTodosItens(int codigo) {
		List<Item> lista = new ArrayList<>();
		String sql = "SELECT  p.codigo, p.nome, p.valorUnitario FROM Comanda c, Produto p, Item i WHERE c.codigo = i.comandaCodigo AND p.codigo = i.produtoCodigo AND c.codigo = ?";
        PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //continuar...
		return lista;
	}
	//mesma coisa, foi adicionado antes das modificacoes, provavelmente vai ser substituido
	//a ideia é verificar se a comanda existe para poder mostrar os itens dela na tela
	public Comanda verificaComanda(int codigo){
        Comanda comanda = new Comanda();
		String sql = "SELECT codigo FROM comanda WHERE ?";
		try {
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();
			if (rs.first()){
				return comanda;//metodo que lê os itens da comanda para a tabela
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

