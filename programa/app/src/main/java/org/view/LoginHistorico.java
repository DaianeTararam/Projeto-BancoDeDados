package org.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginHistorico{

    public void show() {
        Stage stage = new Stage();

        Label lblTitulo = new Label("Acesso ao Controle de Vendas");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblUsuario = new Label("Código:");
        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Digite seu código");
        txtUsuario.setTextFormatter(new TextFormatter<>(change ->
            change.getControlNewText().matches("\\d*") ? change : null
        ));

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Digite sua senha");

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setPrefWidth(200);
        btnEntrar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblMensagem = new Label();

        btnEntrar.setOnAction(e -> {
            String codigo = txtUsuario.getText();
            String senha = txtSenha.getText();

            try {
                int cod = Integer.parseInt(codigo);
                if (cod == 123 && senha.equals("admin")) {
                    try {
                        HistoricoFronteira historico = new HistoricoFronteira();
                        historico.show();
                        stage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    lblMensagem.setText("Código ou senha inválidos");
                    lblMensagem.setStyle("-fx-text-fill: red;");
                }
            } catch (NumberFormatException ex) {
                lblMensagem.setText("Digite um código numérico válido");
                lblMensagem.setStyle("-fx-text-fill: red;");
            }
        });

        VBox tela = new VBox(10, lblTitulo, lblUsuario, txtUsuario,
                             lblSenha, txtSenha, btnEntrar, lblMensagem);
        tela.setAlignment(Pos.CENTER);
        tela.setPadding(new Insets(20));
        tela.setPrefWidth(300);

        BorderPane pane = new BorderPane();
        pane.setCenter(tela);
        pane.setPrefSize(400, 350);

        Scene scene = new Scene(pane);
        stage.setTitle("Login - Controle de Vendas");
        stage.setScene(scene);
        stage.show();
    }
}


