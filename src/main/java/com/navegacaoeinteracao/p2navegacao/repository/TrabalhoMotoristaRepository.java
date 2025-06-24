package com.navegacaoeinteracao.p2navegacao.repository;

import com.navegacaoeinteracao.p2navegacao.model.TrabalhoMotorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrabalhoMotoristaRepository extends JpaRepository<TrabalhoMotorista, Long> {

    List<TrabalhoMotorista> findByIdTrabalhosOrderByIdAsc(Long idTrabalhos);
    
}