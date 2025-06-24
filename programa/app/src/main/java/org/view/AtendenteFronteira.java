package org.view;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;

import java.sql.SQLException;

import org.control.ComandaControle;
import org.model.Item;


public class AtendenteFronteira extends Application{
    private ComandaControle controle = new ComandaControle();
    private TextField txtCodigoProduto = new TextField();
    private TextField txtQuantidade = new TextField();
    private TextField txtCodigoComanda = new TextField();
    private Label lblTotal = new Label("Valor Total:");

    private TableView<Item> tabela = new TableView<>();

    public void bindings(){
        Bindings.bindBidirectional(txtCodigoProduto.textProperty(), controle.codigoProdutoProperty().asObject(), new LongStringConverter());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), controle.quantidadeProperty().asObject(), new IntegerStringConverter());
        lblTotal.textProperty().bind(Bindings.createStringBinding(() -> String.format("Total vendido: R$ %.2f", 
        controle.valorTotalProperty().get()), controle.valorTotalProperty()));
    }

    public void tableCreation(){
        TableColumn<Item, String> col1 = new TableColumn<>("Codigo");
        col1.setCellValueFactory(c -> {
            String strCodigo = Long.toString(c.getValue().getProduto().getCodigo());
            return new ReadOnlyStringWrapper(strCodigo);
        });

        TableColumn<Item, String> col2 = new TableColumn<>("Produto");
        col2.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProduto().getNome()));

        TableColumn<Item, String> col3 = new TableColumn<>("Quantidade");
        col3.setCellValueFactory(c -> {
            String strQtde = Integer.toString(c.getValue().getQuantidade());
            return new ReadOnlyStringWrapper(strQtde);
        });

        TableColumn<Item, String> col4 = new TableColumn<>("Valor Unitario");
        col4.setCellValueFactory(c -> {
            String strValor = Double.toString(c.getValue().getProduto().getValorUnitario());
            return new ReadOnlyStringWrapper(strValor);
        });
        TableColumn<Item, Void> col5 = new TableColumn<>("Opcoes");
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory
                = (tablecolumn) -> new TableCell<>() {
                    private Button btnApagar = new Button("Apagar");
                    {
                        btnApagar.setOnAction( e -> {
                            Item i = controle.listaProperty().get( getIndex() );
                            controle.remover(i);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic( btnApagar );
                        }
                    }
                };
        col5.setCellFactory( cellFactory );
        tabela.getColumns().addAll(col1, col2, col3, col4, col5);
        tabela.setItems(controle.listaProperty());

        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obs, antigo, novo) -> { 
                System.out.println("Contato selecionado ==> " + novo);
                controle.itensParaTela(novo);
            }
        );
    }

    //metodo pra deixar alguns textfields receberem apenas numeros
    private void aplicarFiltroNumerico(TextField campo){
        campo.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            return novoTexto.matches("\\d*") ? change : null;
        }));
    
    }
    private void mensagem(String msg){
        new Alert(AlertType.INFORMATION, msg, ButtonType.OK).show();
    }

    public void start(Stage stage) throws SQLException { 
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();
        HBox paneBotoes = new HBox();
        HBox painelRodape = new HBox();

        paneForm.setPadding(new Insets(15));
        paneForm.setHgap(10);
        paneForm.setVgap(10);

        aplicarFiltroNumerico(txtCodigoProduto);
        aplicarFiltroNumerico(txtQuantidade);
        aplicarFiltroNumerico(txtCodigoComanda);

        bindings();
        tableCreation();

        RowConstraints titulo = new RowConstraints();
        titulo.setPrefHeight(40);
        RowConstraints campos = new RowConstraints();
        campos.setPrefHeight(20);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(20);
        paneForm.getColumnConstraints().addAll(c1, c1);

        Scene scn = new Scene(panePrincipal, 600, 600);

        txtCodigoComanda.setMaxWidth(90);
        txtCodigoProduto.setMaxWidth(90);
        txtQuantidade.setMaxWidth(50);
        

        
        Label comandaAtual = new Label("Comanda atual:");
        comandaAtual.setStyle("-fx-font-weight: bold;");
        paneForm.add(comandaAtual, 0, 0);
        paneForm.add(new Label("Codigo Comanda"), 0, 1);
        paneForm.add( txtCodigoComanda, 1, 1);
        Button btnComanda = new Button("Carregar");
        paneForm.add( btnComanda, 0, 2);

        btnComanda.setOnAction( evento -> {
            try {
                if (controle.buscarComanda(Long.parseLong(txtCodigoComanda.getText()))){
                    controle.atualizarTabela();
                    controle.atualizarValorTotal();
                    mensagem("Comanda selecionada");
                } else {
                    mensagem("Código de comanda inválido");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
        });

        Label addItens = new Label("Adicionar item");
        addItens.setStyle("-fx-font-weight: bold;");
        paneForm.add(addItens, 0, 3);
        paneForm.add( new Label("Codigo Produto"), 0, 4);
        paneForm.add( txtCodigoProduto, 1, 4);
        paneForm.add( new Label("Quantidade:"), 0, 5);
        paneForm.add( txtQuantidade, 1, 5);

 

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( evento -> {
            if(controle.validarComanda(Long.parseLong(txtCodigoComanda.getText()))){
                if (controle.addItem()){
                    controle.atualizarValorTotal();
                    mensagem("Item gravado com sucesso");
                } else {
                    mensagem("Produto não encontrado");
                }
            } else {
                mensagem("Comanda inválida para gravar item");
            }
            
            
        });

        Button btnPesquisar = new Button("Finalizar");
        btnPesquisar.setOnAction( evento -> {
            controle.finalizarComanda();
            mensagem("Pedido finalizado");
        });

        paneBotoes.getChildren().addAll(btnSalvar, btnPesquisar);

        paneForm.add( paneBotoes, 0, 7, 2, 1);


        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        painelRodape.setPadding(new Insets(10));
        painelRodape.getChildren().add(lblTotal);
        
        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter( tabela );
        panePrincipal.setBottom(painelRodape);

        stage.setScene(scn);
        stage.setTitle("Comanda");
        stage.show();
    }
	public static void main(String[] args) {
        Application.launch(AtendenteFronteira.class, args);
    }
    
}
