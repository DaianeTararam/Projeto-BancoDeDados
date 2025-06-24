package org.persistence;

import java.util.List;

import org.model.Comanda;
import org.model.Item;
import org.model.Produto;

public interface PadariaDAO {
    public Produto getProduto(long codigo);
    List<Item> getItens(long comanda) ;
    Comanda getComanda(long codigo);
    void guardar(Item item, long codigoComanda);
    void excluir(long codigoComanda, long codigoProduto);
    boolean verificaProdutoLista(long codigoComanda, long codigoProduto);
    void editarQuantidadeProduto(int quantidade, long codigoComanda, long codigoProduto);
    Float calculaValorTotal(long codigoComanda);
    void finalizarPedido(Comanda comanda);
}
