package com.navegacaoeinteracao.p2navegacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "motoristas")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vaga;
    private String nome;
    private String cpf;
    private String cnh;
    private String placaCaminhao;
    private String placaCarreta;
    private String celular1;
    private String celular2;
    private LocalDateTime ultimaAtribuicao;


    public Motorista() {
    }


    public int getVaga(){
        return vaga;
    }
    public void setVaga(int vaga){
        this.vaga = vaga;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCnh() {
        return cnh;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    public String getPlacaCaminhao() {
        return placaCaminhao;
    }
    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }
    public String getPlacaCarreta() {
        return placaCarreta;
    }
    public void setPlacaCarreta(String placaCarreta) {
        this.placaCarreta = placaCarreta;
    }
    public String getCelular1() {
        return celular1;
    }
    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }
    public String getCelular2() {
        return celular2;
    }
    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }
    public LocalDateTime getUltimaAtribuicao() {
        return ultimaAtribuicao;
    }
    public void setUltimaAtribuicao(LocalDateTime ultimaAtribuicao) {
        this.ultimaAtribuicao = ultimaAtribuicao;
    }
}