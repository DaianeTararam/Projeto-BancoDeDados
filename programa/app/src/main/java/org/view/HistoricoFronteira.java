package org.view;

import java.time.LocalDate;
import java.util.List;

import org.model.Item;
import org.model.Produto;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HistoricoFronteira {

    private ObservableList<Item> listaHistorico = FXCollections.observableArrayList();
    private TableView<Item> tabela = new TableView<>();
    private Label lblTotal = new Label("Valor Total:");
    DatePicker dataInicio = new DatePicker();
    DatePicker dataFim = new DatePicker();
    Button btnFiltrar = new Button("Filtrar");

    public void show() {
        Stage stage = new Stage();
        BorderPane principal = new BorderPane();
        GridPane painelTopo = new GridPane();
        HBox painelRodape = new HBox();

        painelTopo.addRow(0, new Label("De:"), dataInicio, new Label("Até:"), dataFim, btnFiltrar);
        painelTopo.setPadding(new Insets(15));
        painelTopo.setVgap(10);
        painelTopo.setHgap(10);

        criarTabela();
        itensParaTeste(); // só pra simular por enquanto

        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        atualizarTotal(listaHistorico);

        painelRodape.setPadding(new Insets(10));
        painelRodape.getChildren().add(lblTotal);

        principal.setTop(painelTopo);
        principal.setCenter(tabela);
        principal.setBottom(painelRodape);

        btnFiltrar.setOnAction(e -> {
            LocalDate inicio = dataInicio.getValue();
            LocalDate fim = dataFim.getValue();

            if (inicio != null && fim != null) {
                List<Item> filtrados = listaHistorico.stream()
                .filter(item -> !item.getDataVenda().isBefore(inicio) && !item.getDataVenda().isAfter(fim))
                .toList();

                tabela.setItems(FXCollections.observableArrayList(filtrados));
                atualizarTotal(filtrados);
            } 
        });

        Scene cena = new Scene(principal, 600, 400);
        stage.setScene(cena);
        stage.setTitle("Histórico");
        stage.show();
    }

    private void criarTabela() {
        TableColumn<Item, String> col1 = new TableColumn<>("Código");
        col1.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getProduto().getCodigo())));

        TableColumn<Item, String> col2 = new TableColumn<>("Produto");
        col2.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProduto().getNome()));

        TableColumn<Item, String> col3 = new TableColumn<>("Quantidade");
        col3.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getQuantidade())));

        TableColumn<Item, String> col4 = new TableColumn<>("Valor Unit.");
        col4.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("R$ %.2f", c.getValue().getProduto().getValorUnitario())));

        TableColumn<Item, String> col5 = new TableColumn<>("Total");
        col5.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("R$ %.2f", c.getValue().getProduto().getValorUnitario() * c.getValue().getQuantidade()))
        );

        tabela.getColumns().addAll(col1, col2, col3, col4, col5);
        tabela.setItems(listaHistorico);
    }

    private void atualizarTotal(List<Item> itens) {
        double total = itens.stream()
                .mapToDouble(i -> i.getProduto().getValorUnitario() * i.getQuantidade())
                .sum();
        lblTotal.setText(String.format("Total vendido: R$ %.2f", total));
    }

    private void itensParaTeste() {
        listaHistorico.addAll(
            new Item(new Produto(101, "Pão francês", 0.80), 10, LocalDate.of(2024, 6, 1)),
            new Item(new Produto(102, "Pão de queijo", 2.50), 3, LocalDate.of(2024, 6, 1)),
            new Item(new Produto(103, "Bolo de cenoura", 4.50), 2, LocalDate.of(2024, 6, 2)),
            new Item(new Produto(104, "Café expresso", 3.00), 1, LocalDate.of(2024, 6, 2)),
            new Item(new Produto(105, "Sonho de padaria", 4.20), 1, LocalDate.of(2024, 6, 3)),
            new Item(new Produto(106, "Cappuccino", 5.00), 2, LocalDate.of(2024, 6, 3)),
            new Item(new Produto(107, "Pastel de queijo", 5.00), 1, LocalDate.of(2024, 6, 3)),
            new Item(new Produto(108, "Fatias de bolo de limão", 4.80), 2, LocalDate.of(2024, 6, 4))
        );
    }
}
