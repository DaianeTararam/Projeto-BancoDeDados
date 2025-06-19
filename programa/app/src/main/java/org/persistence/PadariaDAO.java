package org.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.model.Produto;

public class PadariaDAO {
	
	private GenericDAO gDao;
	private Connection c;
	
	public PadariaDAO(GenericDAO gDao) {
		this.gDao = gDao;
	}
	
	public List<Produto> getProdutos() throws ClassNotFoundException, SQLException {
		String sql = "SELECT codigo, nome, valorUnitario FROM Produto";
		c = gDao.getConnection("padariaBD");
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

}
