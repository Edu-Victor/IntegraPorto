package com.navegacaoeinteracao.p2navegacao.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Trabalhos")
public class Trabalhos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String transportadora;
    private String booking;
    private String navio;
    private String operacao;
    private String entrega;
    private String coleta;
    private LocalDateTime dt_recebimento; 
    private LocalDateTime dt_termino;
    private String tipo_container;
    private int qt_container;
    private Double valor;
    private int janelas;
    private String status;

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTransportadora() {
        return transportadora;
    }
    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }
    public String getBooking() {
        return booking;
    }
    public void setBooking(String booking) {
        this.booking = booking;
    }
    public String getNavio() {
        return navio;
    }
    public void setNavio(String navio) {
        this.navio = navio;
    }
    public String getOperacao() {
        return operacao;
    }
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    public String getEntrega() {
        return entrega;
    }
    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    public String getColeta() {
        return coleta;
    }
    public void setColeta(String coleta) {
        this.coleta = coleta;
    }
    public LocalDateTime getDt_recebimento() {
        return dt_recebimento;
    }
    public void setDt_recebimento(LocalDateTime dt_recebimento) {
        this.dt_recebimento = dt_recebimento;
    }
    public LocalDateTime getDt_termino() {
        return dt_termino;
    }
    public void setDt_termino(LocalDateTime dt_termino) {
        this.dt_termino = dt_termino;
    }
    public String getTipo_container() {
        return tipo_container;
    }
    public void setTipo_container(String tipo_container) {
        this.tipo_container = tipo_container;
    }
    public int getQt_container() {
        return qt_container;
    }
    public void setQt_container(int qt_container) {
        this.qt_container = qt_container;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public int getJanelas() {
        return janelas;
    }
    public void setJanelas(int janelas) {
        this.janelas = janelas;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
