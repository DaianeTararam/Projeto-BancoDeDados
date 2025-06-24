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
	private String userName = "usuario";
	private String password = "123456";
	private String dbName = "padariaBD";
	
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

	public void guardar(Item item, long codigoComanda){
		String sql = "INSERT INTO ItemComanda (comandaCodigo, produtoCodigo, quantidade) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoComanda);
			ps.setLong(2, item.getProduto().getCodigo());
			ps.setInt(3, item.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verificaProdutoLista(long codigoComanda, long codigoProduto){
		String sql = "SELECT i.comandaCodigo FROM ItemComanda i LEFT OUTER JOIN Produto p ON i.produtoCodigo = p.codigo " + 
					 "WHERE i.produtoCodigo = ? AND i.comandaCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoProduto);
			ps.setLong(2, codigoComanda);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void editarQuantidadeProduto(int quantidade, long codigoComanda, long codigoProduto){
		String sql = "UPDATE ItemComanda SET quantidade = ? WHERE produtoCodigo = ? AND comandaCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, quantidade);
			ps.setLong(2, codigoProduto);
			ps.setLong(3, codigoComanda);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(long codigoComanda, long codigoProduto){
		String sql = "DELETE FROM ItemComanda WHERE comandaCodigo = ? AND produtoCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoComanda);
			ps.setLong(2, codigoProduto);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto(long codigo){
		String sql = "SELECT * FROM Produto WHERE codigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
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

	private Categoria getCategoria(long codigoCategoria){
		Categoria categoria = new Categoria();
		String sql = "SELECT nome FROM Categoria WHERE codigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoCategoria);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				categoria.setCodigo(codigoCategoria);
				categoria.setNome(rs.getString("nome"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoria;
	}
	
	public Float calculaValorTotal(long codigoComanda){
		String sql = "SELECT SUM(p.valorUnitario * i.quantidade) AS total FROM Produto p LEFT OUTER JOIN ItemComanda i " + 
					 "ON p.codigo = i.produtoCodigo WHERE i.comandaCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoComanda);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Float valor = rs.getFloat("total");
				return valor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private long getCodigoPedido(){
		String sqlPedido = "INSERT INTO Pedido DEFAULT VALUES";
		try {
			PreparedStatement ps = c.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void finalizarPedido(Comanda comanda){
		List<Item> itensComanda = comanda.getItens();
		String sql = "UPDATE Pedido SET valorTotal = ? WHERE codigo = ?";
		long codigoPedido = getCodigoPedido();

		for (Item item : itensComanda) {
			gerarItemPedido(codigoPedido, item);
		}

		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, calculaValorTotal(comanda.getCodigo()));
			ps.setLong(2, codigoPedido);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		limparItensComanda(comanda);
	}

	private void gerarItemPedido(long codigoPedido, Item item){
		String sql = "INSERT INTO ItemPedido (pedidoCodigo, produtoCodigo, quantidade) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, codigoPedido);
			ps.setLong(2, item.getProduto().getCodigo());
			ps.setInt(3, item.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void limparItensComanda(Comanda comanda){
		String sql = "DELETE FROM ItemComanda WHERE comandaCodigo = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, comanda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
