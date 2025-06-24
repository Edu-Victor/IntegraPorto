package com.navegacaoeinteracao.p2navegacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalhos, Long>{

    List<Trabalhos> findByStatusOrderByIdAsc(String status);
    
}
