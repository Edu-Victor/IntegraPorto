package com.navegacaoeinteracao.p2navegacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.navegacaoeinteracao.p2navegacao.model.Motorista;
import com.navegacaoeinteracao.p2navegacao.model.TrabalhoMotorista;
import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;
import com.navegacaoeinteracao.p2navegacao.repository.MotoristaRepository;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoMotoristaRepository;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoRepository;


@Controller
public class InfoTrabalhoConcluidoController {


    @Autowired
    TrabalhoRepository trabalhos;

    @Autowired
    MotoristaRepository motoristaRepository;

    @Autowired
    TrabalhoMotoristaRepository trabalhoMotoristaRepository;


    @GetMapping("/info-trabalho-concluidos/{id}")
    public String showCadastroMotorista(@PathVariable Long id, Model model) {

        Optional<Trabalhos> selecionarTrabalho = trabalhos.findById(id);
    
        Trabalhos trabalho = selecionarTrabalho.get();
        model.addAttribute("trabalhos", trabalho);

       
        List<TrabalhoMotorista> trabalhoMotoristas = trabalhoMotoristaRepository.findByIdTrabalhosOrderByIdAsc(id);
        List<Motorista> assignedDrivers = new ArrayList<>();
        for (TrabalhoMotorista tm : trabalhoMotoristas) {
            motoristaRepository.findById(tm.getIdMotoristas()).ifPresent(assignedDrivers::add);
        }
        model.addAttribute("motoristas", assignedDrivers);
        
        return "info-trabalho-concluidos";
    }
    
}
