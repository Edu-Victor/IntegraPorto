package com.navegacaoeinteracao.p2navegacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Comparator;

import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;
import com.navegacaoeinteracao.p2navegacao.model.Motorista;
import com.navegacaoeinteracao.p2navegacao.model.TrabalhoMotorista;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoRepository;
import com.navegacaoeinteracao.p2navegacao.repository.MotoristaRepository;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoMotoristaRepository;

@Controller
public class CadastroTrabalho {

    @Autowired
    private TrabalhoRepository trabalhoRepository;
    @Autowired
    private MotoristaRepository motoristaRepository;
    @Autowired
    private TrabalhoMotoristaRepository trabalhoMotoristaRepository;


    @GetMapping("/cadastrotrabalho")
    public String cadastroTrabalho(){
        return "cadastrotrabalho";
    }


    @PostMapping("/trabalhos/adicionar")
    public RedirectView adicionarTrabalho(@ModelAttribute Trabalhos trabalhos, @RequestParam("valor") String valorBanco, RedirectAttributes redirectAttributes) {
        trabalhos.setStatus("Em-Andamento");
        trabalhos.setValor(Double.parseDouble(valorBanco));
        Trabalhos salvarTrabalho = trabalhoRepository.save(trabalhos);
        int quantidadeContainers = trabalhos.getQt_container();
        String tipoContainer = trabalhos.getTipo_container();
        int chamarMotoristas = 0;

        if (tipoContainer.equals("20HC") || tipoContainer.equals("Reefer 20HC")) {
            chamarMotoristas = quantidadeContainers / 2;
        } else {
            chamarMotoristas = quantidadeContainers;
        }

        List<Motorista> availableDrivers = motoristaRepository.findAll();
        availableDrivers.sort(Comparator.comparing(Motorista::getUltimaAtribuicao, Comparator.nullsFirst(Comparator.naturalOrder())));


        for (int i = 0; i < chamarMotoristas; i++) {
            if (availableDrivers.isEmpty()) {
                System.out.println("Não há motoristas suficientes disponíveis para o trabalho!");
                break;
            }
            Motorista assignedDriver = availableDrivers.remove(0);
            TrabalhoMotorista trabalhoMotorista = new TrabalhoMotorista();
            trabalhoMotorista.setIdTrabalhos(salvarTrabalho.getId());
            trabalhoMotorista.setIdMotoristas(assignedDriver.getId());
            trabalhoMotoristaRepository.save(trabalhoMotorista);
            assignedDriver.setUltimaAtribuicao(LocalDateTime.now());
            motoristaRepository.save(assignedDriver);
            availableDrivers.add(assignedDriver); 
            availableDrivers.sort(Comparator.comparing(Motorista::getUltimaAtribuicao, Comparator.nullsFirst(Comparator.naturalOrder())));
        }
        redirectAttributes.addAttribute("sucesso", true);
        return new RedirectView("/cadastrotrabalho");
    }
    
    
}