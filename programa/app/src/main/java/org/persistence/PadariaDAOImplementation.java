package org.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.model.Comanda;
import org.model.Item;
import org.model.Produto;

public class PadariaDAOImplementation implements PadariaDAO{

	private Connection c = null;
        private String hostName = "localhost";
	private String userName = "sa";
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
	
	public List<Produto> getProdutos() throws SQLException{
		String sql = "SELECT codigo, nome, valorUnitario FROM Produto";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Produto> produtos = new ArrayList<>();
		while (rs.next()) {
			Produto produto = new Produto();
			produto.setCodigo(rs.getInt("codigo"));
			produto.setNome(rs.getString("nome"));
			produto.setValorUnitario(rs.getDouble("valorUnitario"));
			
			produtos.add(produto);
		}
		rs.close();
		ps.close();
		return produtos;
	}

	public List<Item> lerTodosItens(int codigo) throws SQLException{
		List<Item> lista = new ArrayList<>();
		String sql = "SELECT  p.codigo, p.nome, p.valorUnitario FROM Comanda c, Produto p, Item i WHERE c.codigo = i.comandaCodigo AND p.codigo = i.produtoCodigo AND c.codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
        //
		return lista;
	}

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
