package org.persistence;

import org.model.ProdutoPedido;
import org.model.Pedido;

public interface PadariaDAO {
    void novoProdutoPedido(ProdutoPedido produtoPedido);
    void novoPedido(Pedido pedido);
    void consultaTodosPedidos();
    void consultaTotalVendasPedidos();
    void consultaQtdTotalProdutosVendidosCategoria();
    void consultaPedidosValorTotalAcima();
}
