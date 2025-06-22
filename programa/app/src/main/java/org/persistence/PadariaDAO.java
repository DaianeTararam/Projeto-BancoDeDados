package org.persistence;

import java.util.List;

import org.model.Comanda;
import org.model.Item;
import org.model.Produto;

public interface PadariaDAO {
    public List<Produto> getProdutos();
    public List<Item> lerTodosItens(int codigo);
    public Produto getProduto(long codigo);
    List<Item> getItens(long comanda) ;
    Comanda getComanda(long codigo);
    //colocar o restante aqui
}
