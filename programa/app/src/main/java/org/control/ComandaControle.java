package org.control;

import org.model.Comanda;
import org.model.Item;
import org.persistence.PadariaDAO;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComandaControle {
    private ObservableList<Item> lista = FXCollections.observableArrayList();

    //private PadariaDAO padariaDAO = new PadariaDAO();
    private StringProperty codigoProduto = new SimpleStringProperty("");
    private StringProperty nomeProduto = new SimpleStringProperty("");
    private StringProperty quantidade = new SimpleStringProperty("");
    private Comanda comandaAtual;

    // public Comanda buscarCriarComanda(int codigo){
        
    // }

    public void addItem(int codigo){
        //
    }

    public void remover(Item item){
        lista.remove(item);
    }

    public Item telaParaLista(){
        Item i = new Item();
        //...
        return i;
    }



    public StringProperty codigoProdutoProperty(){
        return this.codigoProduto;
    }

    public StringProperty nomeProdutoProperty(){
        return this.nomeProduto;
    }

    public StringProperty quantidadeProperty(){
        return this.quantidade;
    }

    public ObservableList<Item> listaProperty(){
        return this.lista;
    }
}
