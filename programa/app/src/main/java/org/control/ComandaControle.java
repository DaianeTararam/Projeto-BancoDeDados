package org.control;

import org.model.ProdutoPedido;
import org.model.Produto;
import org.persistence.PadariaDAO;
import org.persistence.PadariaDAOImplementation;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComandaControle {
    private ObservableList<ProdutoPedido> lista = FXCollections.observableArrayList();

    private PadariaDAO padariaDAO = new PadariaDAOImplementation();
    private LongProperty codigoProduto = new SimpleLongProperty(0);
    private StringProperty nomeProduto = new SimpleStringProperty("");
    private IntegerProperty quantidade = new SimpleIntegerProperty(0);

    //metodo para validar comanda atual

    public ProdutoPedido telaParaItens(Produto produto){
        ProdutoPedido ProdutoPedido = new ProdutoPedido();
        ProdutoPedido.setProduto(produto);
        ProdutoPedido.setQuantidade(quantidade.get());
        return ProdutoPedido;
    }

    public void itensParaTela(ProdutoPedido ProdutoPedido){
        codigoProduto.set(ProdutoPedido.getProduto().getCodigo());
        nomeProduto.set(ProdutoPedido.getProduto().getNome());
        quantidade.set(ProdutoPedido.getQuantidade());

    }

    public LongProperty codigoProdutoProperty(){
        return this.codigoProduto;
    }

    public StringProperty nomeProdutoProperty(){
        return this.nomeProduto;
    }

    public IntegerProperty quantidadeProperty(){
        return this.quantidade;
    }

    public ObservableList<ProdutoPedido> listaProperty(){
        return this.lista;
    }
}
