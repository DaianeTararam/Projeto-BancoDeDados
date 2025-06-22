package org.view;

import org.control.ComandaControle;
import org.model.Item;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private TableView<Item> tabela = new TableView<>();

    public void bindings(){
        Bindings.bindBidirectional(txtCodigoProduto.textProperty(), controle.codigoProdutoProperty().asObject(), new LongStringConverter());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), controle.quantidadeProperty().asObject(), new IntegerStringConverter());
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

        TableColumn<Item, String> col4 = new TableColumn<>("Valor Unitário");
        col4.setCellValueFactory(c -> {
            String strValor = Double.toString(c.getValue().getProduto().getValorUnitario());
            return new ReadOnlyStringWrapper(strValor);
        });
        TableColumn<Item, Void> col5 = new TableColumn<>("Ações");
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
                //controle.itensParaTela(novo);
            }
        );
    }

    //metodo pra deixar alguns textfields receberem apenas numeros
    public void aplicarFiltroNumerico(TextField campo){
        campo.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            return novoTexto.matches("\\d*") ? change : null;
        }));
    
    }

    public void start(Stage stage) throws SQLException { 
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();
        HBox paneBotoes = new HBox();

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
                    new Alert(AlertType.INFORMATION, 
                "Comanda selecionada", 
                            ButtonType.OK).show();
                } else {
                    new Alert(AlertType.INFORMATION, 
                "Código de comanda inválido", 
                            ButtonType.OK).show();
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
            //metodo do controle para salvar os items 
            new Alert(AlertType.INFORMATION, 
                "Item gravado com sucesso", 
                            ButtonType.OK).show();
        });

        Button btnPesquisar = new Button("Pesquisar");

        btnPesquisar.setOnAction( evento -> {
            //metodo do controle para pesquisar um item da comanda, talvez nao seja usado
        });

        paneBotoes.getChildren().addAll(btnSalvar, btnPesquisar);

        paneForm.add( paneBotoes, 0, 7, 2, 1);
        
        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter( tabela );

        stage.setScene(scn);
        stage.setTitle("Comanda");
        stage.show();
    }
	public static void main(String[] args) {
        Application.launch(AtendenteFronteira.class, args);
    }
    
}
