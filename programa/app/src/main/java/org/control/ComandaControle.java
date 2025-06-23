package org.control;

import java.util.ArrayList;
import java.util.List;

import org.model.Comanda;
import org.model.Item;
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
            lista.add(item); //?? ---- adiciona novo item na lista
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

    public Item telaParaItens(Produto produto){
        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade(quantidade.get());
        return item;
    }

    public void itensParaTela(Item item) {
        if (item != null) {
            Produto produto = item.getProduto();
            codigoProduto.set(produto.getCodigo());
            nomeProduto.set(produto.getNome());
            quantidade.set(item.getQuantidade());
        }
    }
    
    public void adicionarItem(){
        Produto produto = padariaDAO.getProduto(codigoProduto.get());
        if (produto != null) {
            Item i = telaParaItens(produto);
            lista.add(i);
        }
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
