// InfoTrabalhoController.java
package com.navegacaoeinteracao.p2navegacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;
import com.navegacaoeinteracao.p2navegacao.model.Motorista;
import com.navegacaoeinteracao.p2navegacao.model.TrabalhoMotorista;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoRepository;
import com.navegacaoeinteracao.p2navegacao.repository.MotoristaRepository;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoMotoristaRepository;

@Controller
public class InfoTrabalhoController {

    @Autowired
    TrabalhoRepository trabalhos;

    @Autowired
    MotoristaRepository motoristaRepository;

    @Autowired
    TrabalhoMotoristaRepository trabalhoMotoristaRepository;


    @GetMapping("/info-trabalho/{id}")
    public String showCadastroMotorista(@PathVariable Long id, Model model) {

        Optional<Trabalhos> selecionarTrabalho = trabalhos.findById(id);

        if (!selecionarTrabalho.isPresent()) {
            return "redirect:/error"; 
        }

        Trabalhos trabalho = selecionarTrabalho.get();
        model.addAttribute("trabalhos", trabalho);


        List<TrabalhoMotorista> trabalhoMotoristas = trabalhoMotoristaRepository.findByIdTrabalhosOrderByIdAsc(id);
        List<Motorista> assignedDrivers = new ArrayList<>();
        for (TrabalhoMotorista tm : trabalhoMotoristas) {
            motoristaRepository.findById(tm.getIdMotoristas()).ifPresent(assignedDrivers::add);
        }
        model.addAttribute("motoristas", assignedDrivers);
        model.addAttribute("trabalhoMotoristas", trabalhoMotoristas);

        List<Motorista> allDrivers = motoristaRepository.findAll();
        model.addAttribute("availableDrivers", allDrivers);

        return "info-trabalho";
    }



    @PostMapping("/info-trabalho/substituir-motorista")
    public String replaceDriver(@RequestParam("trabalhoMotoristaId") Long trabalhoMotoristaId,
                                @RequestParam("newMotoristaId") Long newMotoristaId,
                                RedirectAttributes redirectAttributes) {
        Optional<TrabalhoMotorista> tmOptional = trabalhoMotoristaRepository.findById(trabalhoMotoristaId);

        if (tmOptional.isPresent()) {
            TrabalhoMotorista trabalhoMotorista = tmOptional.get();
            trabalhoMotorista.setIdMotoristas(newMotoristaId);
            trabalhoMotoristaRepository.save(trabalhoMotorista);
            redirectAttributes.addFlashAttribute("successMessage", "Motorista substituído com sucesso!");
            return "redirect:/info-trabalho/" + trabalhoMotorista.getIdTrabalhos();
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao substituir motorista.");
            return "redirect:/error";
        }
    }
}