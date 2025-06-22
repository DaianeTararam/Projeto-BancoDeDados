package org.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.model.Item;
import org.model.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoFronteira {
    private ObservableList<Item> todosItens = FXCollections.observableArrayList();
    private TableView<Item> tabela = new TableView<>();
    private Label lblTotal = new Label("Total vendido: R$ 0,00");

    public void show() {
        Stage stage = new Stage();
        iniciarTela(stage);
    }

    private void iniciarTela(Stage stage) {
        todosItens.addAll(
            new Item(new Produto(101, "X-Burguer", 15.00), 2, LocalDate.of(2024, 6, 1)),
            new Item(new Produto(202, "Batata", 10.00), 1, LocalDate.of(2024, 6, 2)),
            new Item(new Produto(303, "Refrigerante", 5.00), 3, LocalDate.of(2024, 6, 3)),
            new Item(new Produto(404, "Pizza", 35.00), 1, LocalDate.of(2024, 5, 28))
        );

        DatePicker dataInicio = new DatePicker();
        DatePicker dataFim = new DatePicker();
        Button btnFiltrar = new Button("Filtrar por Período");

        HBox filtro = new HBox(10,
            new Label("De:"), dataInicio,
            new Label("Até:"), dataFim,
            btnFiltrar
        );
        filtro.setAlignment(Pos.CENTER_LEFT);
        filtro.setPadding(new Insets(10));

        TableColumn<Item, String> colProduto = new TableColumn<>("Produto");
        colProduto.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProduto().getNome()));

        TableColumn<Item, String> colQtd = new TableColumn<>("Quantidade");
        colQtd.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getQuantidade())));

        TableColumn<Item, String> colValor = new TableColumn<>("Valor Unit.");
        colValor.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("R$ %.2f", c.getValue().getProduto().getValorUnitario())));

        TableColumn<Item, String> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("R$ %.2f",
                c.getValue().getProduto().getValorUnitario() * c.getValue().getQuantidade())));

        TableColumn<Item, String> colData = new TableColumn<>("Data Venda");
        colData.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDataVenda().toString()));

        tabela.getColumns().addAll(colProduto, colQtd, colValor, colTotal, colData);
        tabela.setItems(todosItens);
        tabela.setPrefHeight(250);

        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        atualizarTotal(todosItens);

        btnFiltrar.setOnAction(e -> {
            LocalDate inicio = dataInicio.getValue();
            LocalDate fim = dataFim.getValue();

            if (inicio != null && fim != null) {
                List<Item> filtrados = todosItens.stream()
                    .filter(item -> !item.getDataVenda().isBefore(inicio) && !item.getDataVenda().isAfter(fim))
                    .collect(Collectors.toList());

                tabela.setItems(FXCollections.observableArrayList(filtrados));
                atualizarTotal(filtrados);
            }
        });

        VBox layout = new VBox(15, filtro, tabela, lblTotal);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 650, 400));
        stage.setTitle("Histórico das Comandas");
        stage.show();
    }

    private void atualizarTotal(List<Item> lista) {
        double total = lista.stream()
            .mapToDouble(i -> i.getProduto().getValorUnitario() * i.getQuantidade())
            .sum();
        lblTotal.setText(String.format("Total vendido: R$ %.2f", total));
    }
}
