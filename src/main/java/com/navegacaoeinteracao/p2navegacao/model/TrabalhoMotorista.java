package com.navegacaoeinteracao.p2navegacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trabalhos_motoristas")
public class TrabalhoMotorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idTrabalhos;
    private Long idMotoristas;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdTrabalhos() {
        return idTrabalhos;
    }
    public void setIdTrabalhos(Long idTrabalhos) {
        this.idTrabalhos = idTrabalhos;
    }
    public Long getIdMotoristas() {
        return idMotoristas;
    }
    public void setIdMotoristas(Long idMotoristas) {
        this.idMotoristas = idMotoristas;
    }
}