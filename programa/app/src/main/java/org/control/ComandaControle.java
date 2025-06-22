package org.control;

import org.model.Comanda;
import org.model.Item;
import org.persistence.PadariaDAO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

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

    //apenas um teste pro botao salvar
    public void retorna(){
        List<Produto> lista = new ArrayList<>();
        lista = padariaDAO.getProdutos();
        lista.forEach(al -> System.out.println(al.toString()));
    }

    //tem q terminar
    public void addItem(){
        Produto produto = new Produto();
        produto = padariaDAO.getProduto(codigoProduto.get());
        if (produto != null){
            Item item = telaParaItens(produto);
            //padariaDAO.guardar(item);
            lista.add(item); //??
            //pesquisarItem(); <- ver pra q q serve
        }
    }

    public void atualizaTabela(){
        lista.clear();
        lista.addAll(padariaDAO.getItens(comandaAtual.getCodigo()));
    }

    public void remover(Item item){
        lista.remove(item);
    }

    public Item telaParaLista(){
        Item i = new Item();
        i.setQuantidade(Integer.parseInt(quantidade.get()));
        Produto p = new Produto();
        p.setCodigo(Integer.parseInt(codigoProduto.get()));
        p.setNome(nomeProduto.get());
        i.setProduto(p);
        return i;

    public Item telaParaItens(Produto produto){
        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade(quantidade.get());
        return item;
    }

    //tem q terminar
    public void itensParaTela(){

    }
    
    public void adicionarItem(){
        Item i = telaParaLista();
        lista.add(i); 
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
