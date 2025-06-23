package org.persistence;

import java.util.List;

import org.model.Comanda;
import org.model.Item;
import org.model.Produto;

public interface PadariaDAO {
    public List<Item> lerTodosItens(int codigo);
    public Produto getProduto(long codigo);
    List<Item> getItens(long comanda) ;
    Comanda getComanda(long codigo);
    void guardar(Item item, long codigoComanda);
    boolean atualizar(Item item, long codigoComanda);
    boolean excluir(long codigoComanda, long codigoProduto);
    //colocar o restante aqui
}
