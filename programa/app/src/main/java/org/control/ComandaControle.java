package org.control;

import org.persistence.PadariaDAO;
import org.persistence.PadariaDAOImplementation;

import java.util.List;

import org.model.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ComandaControle {
    private ObservableList<Item> lista = FXCollections.observableArrayList();

    private PadariaDAO padariaDAO = new PadariaDAOImplementation();
    private StringProperty codigoProduto = new SimpleStringProperty("");
    private StringProperty nomeProduto = new SimpleStringProperty("");
    private StringProperty quantidade = new SimpleStringProperty("");
    private Comanda comandaAtual;

    // public Comanda buscarCriarComanda(int codigo){
    // }
    
    //metodo retorna() é apenas um teste
    public void retorna() throws SQLException{
        List<Produto> lista = new ArrayList<>();
        lista = padariaDAO.getProdutos();
        lista.forEach(al -> System.out.println(al.toString()));
    }

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
