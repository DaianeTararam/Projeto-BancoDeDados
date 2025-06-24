package org.model;

import java.time.LocalDate;

public class Pedido {
    private int codigo;
    private LocalDate dataHora = LocalDate.now();;
    private Float valorTotal;

    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public LocalDate getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }
    public Float getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }


}
