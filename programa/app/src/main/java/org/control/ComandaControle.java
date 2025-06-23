package org.control;

import org.persistence.PadariaDAO;
import org.persistence.PadariaDAOImplementation;

import java.util.List;

import org.model.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ComandaControle {
    private ObservableList<Item> lista = FXCollections.observableArrayList();

    private PadariaDAO padariaDAO = new PadariaDAOImplementation();
    private LongProperty codigoProduto = new SimpleLongProperty(0);
    private StringProperty nomeProduto = new SimpleStringProperty("");
    private IntegerProperty quantidade = new SimpleIntegerProperty(0);
    private Comanda comandaAtual = null;

    public boolean buscarComanda(long codigo){
        Comanda teste = padariaDAO.getComanda(codigo);
        if (teste != null){
            this.comandaAtual = teste;
            return true;
        }
        
        return false;
    }

    //metodo para validar comanda atual

    public void addItem(){
        Produto produto = new Produto();
        produto = padariaDAO.getProduto(codigoProduto.get());
        if (produto != null){
            Item item = telaParaItens(produto);
            padariaDAO.guardar(item, comandaAtual.getCodigo());
            atualizaTabela();
        }
    }

    public void remover(Item item){
        padariaDAO.excluir(comandaAtual.getCodigo(), item.getProduto().getCodigo());
        atualizaTabela();
    }

    public void atualizaTabela(){
        lista.clear();
        lista.addAll(padariaDAO.getItens(comandaAtual.getCodigo()));
    }


    public Item telaParaItens(Produto produto){
        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade(quantidade.get());
        return item;
    }

    //tem q terminar
    public void itensParaTela(Item item){
        codigoProduto.set(item.getProduto().getCodigo());
        nomeProduto.set(item.getProduto().getNome());
        quantidade.set(item.getQuantidade());

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

    public ObservableList<Item> listaProperty(){
        return this.lista;
    }
}
