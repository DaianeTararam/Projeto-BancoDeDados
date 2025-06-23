package org.view;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.geometry.Insets;

import java.sql.SQLException;

import org.control.ComandaControle;

public class AtendenteFronteira extends Application{
    private ComandaControle controle = new ComandaControle();
    private TextField txtCodigoProduto = new TextField();
    private TextField txtQuantidade = new TextField();
    private TextField txtCodigoComanda = new TextField();
    private TextField txtCodigoFormaPagamento = new TextField();

    public void bindings(){
        Bindings.bindBidirectional(txtCodigoProduto.textProperty(), controle.codigoProdutoProperty().asObject(), new LongStringConverter());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), controle.quantidadeProperty().asObject(), new IntegerStringConverter());
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

        RowConstraints titulo = new RowConstraints();
        titulo.setPrefHeight(40);
        RowConstraints campos = new RowConstraints();
        campos.setPrefHeight(20);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(20);
        paneForm.getColumnConstraints().addAll(c1, c1);

        Scene scn = new Scene(panePrincipal, 800, 600);

        txtCodigoComanda.setMaxWidth(100);
        txtCodigoProduto.setMaxWidth(100);
        txtQuantidade.setMaxWidth(100);
        txtCodigoFormaPagamento.setMaxWidth(100);
        
        Label comandaAtual = new Label("Digite o id da comanda atual:");
        comandaAtual.setStyle("-fx-font-weight: bold;");
        paneForm.add(comandaAtual, 0, 0);
        paneForm.add(new Label("Codigo da comanda:"), 0, 1);
        paneForm.add( txtCodigoComanda, 1, 1);

        Label addItens = new Label("Adicionar produto");
        addItens.setStyle("-fx-font-weight: bold;");
        paneForm.add(addItens, 0, 3);
        paneForm.add( new Label("Codigo do produto:"), 0, 4);
        paneForm.add( txtCodigoProduto, 1, 4);
        paneForm.add( new Label("Quantidade:"), 0, 5);
        paneForm.add( txtQuantidade, 1, 5);

        Label addFormaPagamento = new Label("Digite o id da forma de pagamento:");
        addFormaPagamento.setStyle("-fx-font-weight: bold;");
        paneForm.add(addFormaPagamento, 0, 6);
        paneForm.add( new Label("Codigo da forma de pagamento"), 0, 7);
        paneForm.add( txtCodigoFormaPagamento, 1, 7);

        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction( evento -> {

            new Alert(AlertType.INFORMATION, 
                "Pedido finalizado com sucesso!", 
                            ButtonType.OK).show();
        });

        paneBotoes.getChildren().addAll(btnSalvar);

        paneForm.add( paneBotoes, 0, 9, 2, 1);
        
        panePrincipal.setTop( paneForm );

        stage.setScene(scn);
        stage.setTitle("Tela de pagamento");
        stage.show();
    }
	public static void main(String[] args) {
        Application.launch(AtendenteFronteira.class, args);
    }
    
}
