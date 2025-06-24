package com.navegacaoeinteracao.p2navegacao.repository;

import com.navegacaoeinteracao.p2navegacao.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {

}