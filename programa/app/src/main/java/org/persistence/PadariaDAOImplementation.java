package org.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.model.ProdutoPedido;
import org.model.Comanda;
import org.model.Pedido;


public class PadariaDAOImplementation implements PadariaDAO{
	private Connection c = null;
    private String hostName = "localhost";
	private String userName = "sa";
	private String password = "123456";
        private String dbName = "padariaBD";
	
	//Carrega a database
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

	//--MÉTODOS QUE GUARDAM PARA SEREM IMPLEMENTADOS...

	//--Guarda a quantidade de produtos (REFERENTE A TABELA ProdutoPedido)
	public void novoProdutoPedido(ProdutoPedido produtoPedido) {
		String sql = "INSERT INTO ProdutoPedido" +
		             "VALUES (?, ?, ?)";
		System.out.println("novoProdutoPedido() SQL: " + sql);
		try{
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(1, produtoPedido.getQuantidade() );
			stm.setObject(2, produtoPedido.getProduto() );
			stm.setObject(3, produtoPedido.getPedido() );
		} catch (SQLException e) { 
            e.printStackTrace();
        }
	}


	//--Guarda um pedido (REFERENTE A TABELA Pedido)
	public void novoPedido(Pedido pedido) {
		String sql = "INSERT INTO Pedido (comandaCodigo, formaPagamentoCodigo)" +
		             "VALUES (?, ?)";
		System.out.println("novoPedido() SQL: " + sql);
		try{
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setObject(1, pedido.getComanda() );
			stm.setObject(2, pedido.getFormaPagamento() );
		} catch (SQLException e) { 
            e.printStackTrace();
        }
	}


	//--CONSULTAS PARA SEREM IMPLEMENTADAS....
	
	
	//--Valor total de uma comanda específica
	public void consultaValorTotalComanda(Comanda comanda) {
		String sql = "SELECT c.codigo, SUM(pp.quantidade * pr.valorUnitario) AS totalComanda " +
		             "FROM Produto pr INNER JOIN ProdutoPedido pp" +
					 "ON pr.codigo = pp.produtoCodigo" +
					 "INNER JOIN Pedido pe" +
					 "ON pe.codigo = pp.pedidoCodigo" +
					 "INNER JOIN Comanda c" +
					 "ON c.codigo = pe.comandaCodigo" +
					 "WHERE c.codigo = ?" +              //--Aqui deve colocar o id da comanda que se deseja saber o valor total
					 "GROUP BY c.codigo";
		System.out.println("consultaValorTotalComanda() SQL: " + sql);
		try{
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setObject(1, comanda.getCodigo() );
		} catch (SQLException e) { 
            e.printStackTrace();
        }
	}

	//--Consulta de todos os pedidos anteriores
	public void consultaTodosPedidos() {
		String sql = "SELECT pe.codigo AS codigoPedido, c.codigo AS codigoComanda," +
					 "SUM(pr.valorUnitario * pp.quantidade) AS valorTotal, fp.nome AS formaPagamento, pe.dataHora" +
					 "FROM Produto pr INNER JOIN ProdutoPedido pp" +
					 "ON pr.codigo = pp.produtoCodigo" +
					 "INNER JOIN Pedido pe" +
					 "ON pe.codigo = pp.pedidoCodigo" +
					 "INNER JOIN Comanda c" +
					 "ON c.codigo = pe.comandaCodigo" +
					 "INNER JOIN FormaPagamento fp" +
					 "ON fp.codigo = pe.formaPagamentoCodigo" +
					 "GROUP BY pe.codigo, c.codigo, fp.nome, pe.dataHora";
		System.out.println("consultaTodosPedidos() SQL: " + sql);
	}

	//--Total de vendas (R$) realizadas por todos os pedidos
	public void consultaTotalVendasPedidos() {
		String sql = "SELECT SUM(pr.valorUnitario * pp.quantidade) AS valorTotalVendas" +
					 "FROM Produto pr INNER JOIN ProdutoPedido pp" +
					 "ON pr.codigo = pp.produtoCodigo" +
					 "INNER JOIN Pedido pe" +
					 "ON pe.codigo = pp.pedidoCodigo";
		System.out.println("consultaTotalVendasPedidos() SQL: " + sql);
	}

	//--Quantidade total de produtos vendidos por categoria
	public void consultaQtdTotalProdutosVendidosCategoria() {
		String sql = "SELECT SUM(pp.quantidade) AS qtdProdutosVendidos" +
					 "FROM Categoria ct INNER JOIN Produto pr" +
					 "ON ct.codigo = pr.CategoriaCodigo" +
					 "INNER JOIN ProdutoPedido pp" +
					 "ON pr.codigo = pp.produtoCodigo" +
					 "GROUP BY ct.nome";
		System.out.println("consultaQtdTotalProdutosVendidosCategoria() SQL: " + sql);
	}
	
	//--Pedidos com valor total acima de R$20
	public void consultaPedidosValorTotalAcima() {
		String sql = "SELECT pe.codigo, SUM(pp.quantidade * pr.valorUnitario) AS totalPedido" +
					 "FROM Produto pr INNER JOIN ProdutoPedido pp" +
					 "ON pr.codigo = pp.produtoCodigo" +
					 "INNER JOIN Pedido pe" +
					 "ON pe.codigo = pp.pedidoCodigo" +
					 "GROUP BY pe.codigo" +
					 "HAVING SUM(pp.quantidade * pr.valorUnitario) > 20";
		System.out.println("consultaPedidosValorTotalAcima() SQL: " + sql);
	}
	

}