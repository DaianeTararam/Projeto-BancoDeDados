package org.view;

import java.time.LocalDate;
import java.util.List;

import org.model.ProdutoPedido;

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

    private ObservableList<ProdutoPedido> listaHistorico = FXCollections.observableArrayList();
    private TableView<ProdutoPedido> tabela = new TableView<>();
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
                List<ProdutoPedido> filtrados = listaHistorico.stream()
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

    @SuppressWarnings("unchecked")
    private void criarTabela() {
        TableColumn<ProdutoPedido, String> col1 = new TableColumn<>("Código");
        col1.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getProduto().getCodigo())));

        TableColumn<ProdutoPedido, String> col2 = new TableColumn<>("Produto");
        col2.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProduto().getNome()));

        TableColumn<ProdutoPedido, String> col3 = new TableColumn<>("Quantidade");
        col3.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getQuantidade())));

        TableColumn<ProdutoPedido, String> col4 = new TableColumn<>("Valor Unit.");
        col4.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("R$ %.2f", c.getValue().getProduto().getValorUnitario())));

        TableColumn<ProdutoPedido, String> col5 = new TableColumn<>("Total");
        col5.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("R$ %.2f", c.getValue().getProduto().getValorUnitario() * c.getValue().getQuantidade()))
        );

        tabela.getColumns().addAll(col1, col2, col3, col4, col5);
        tabela.setItems(listaHistorico);
    }

    private void atualizarTotal(List<ProdutoPedido> itens) {
        double total = itens.stream()
                .mapToDouble(i -> i.getProduto().getValorUnitario() * i.getQuantidade())
                .sum();
        lblTotal.setText(String.format("Total vendido: R$ %.2f", total));
    }
}
