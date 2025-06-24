package com.navegacaoeinteracao.p2navegacao.repository;

import com.navegacaoeinteracao.p2navegacao.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    
}