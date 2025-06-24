package org.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {
    @Override
    public void start(Stage stage) {
        Button btnComanda = new Button("Criar novo pedido");
        //Button btnFinalizar = new Button("Finalizar Pagamento");
        Button btnHistorico = new Button("Acessar Histórico");

        String aparencia = "-fx-font-size: 16px; -fx-pref-width: 250px;";
        btnComanda.setStyle(aparencia);
        //btnFinalizar.setStyle(aparencia);
        btnHistorico.setStyle(aparencia);

        VBox tela = new VBox(15, btnComanda, btnHistorico);
        tela.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(tela);

        Scene cena = new Scene(pane, 800, 600);
        stage.setTitle("Menu Principal");
        stage.setScene(cena);
        stage.show();

        // Aqui fiz a conexão dos botões
        btnComanda.setOnAction(e -> {
            try {
                new AtendenteFronteira().start(new Stage());
                stage.close();           
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnHistorico.setOnAction(e -> {
            stage.close();
        });

    }
    //public static void main(String[] args) {
        //launch(args);
    //}
}
