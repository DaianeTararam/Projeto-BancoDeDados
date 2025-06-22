package org.persistence;

import java.sql.SQLException;
import java.util.List;

import org.model.Item;
import org.model.Produto;

public interface PadariaDAO {
    public List<Produto> getProdutos() throws SQLException;
    public List<Item> lerTodosItens(int codigo) throws SQLException;
    public Produto getProduto(long codigo) throws SQLException;
    //colocar o restante aqui
}
