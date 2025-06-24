package com.navegacaoeinteracao.p2navegacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Optional;
import com.navegacaoeinteracao.p2navegacao.model.Motorista;
import com.navegacaoeinteracao.p2navegacao.repository.MotoristaRepository;

@Controller
public class CadastroMotorista {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @GetMapping("/cadastromotorista")
    public String showCadastroMotorista(Model model) {
        Sort vagaCrescente = Sort.by(Sort.Direction.ASC, "vaga");
        model.addAttribute("motoristas", motoristaRepository.findAll(vagaCrescente));
        return "cadastromotorista";
    }

    @PostMapping("/motoristas/adicionar")
    public RedirectView adicionarMotorista(@ModelAttribute Motorista motorista, RedirectAttributes redirectAttributes) {
        motorista.setNome(motorista.getNome().toUpperCase());
        motorista.setPlacaCaminhao(motorista.getPlacaCaminhao().toUpperCase());
        motorista.setPlacaCarreta(motorista.getPlacaCarreta().toUpperCase());
        motoristaRepository.save(motorista);
        redirectAttributes.addAttribute("sucesso", true);
        return new RedirectView("/cadastromotorista");
    }

    @GetMapping("/api/motoristas/{id}")
    @ResponseBody
    public Optional<Motorista> getMotoristaById(@PathVariable Long id) {
        return motoristaRepository.findById(id);
    }

    @PostMapping("/motoristas/editar/{id}")
    public RedirectView editarMotorista(@PathVariable Long id, @ModelAttribute Motorista motoristaAtualizado) {
        Motorista motorista = motoristaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de Motorista inválido:" + id));
        motorista.setVaga(motoristaAtualizado.getVaga());
        motorista.setNome(motoristaAtualizado.getNome().toUpperCase());
        motorista.setCpf(motoristaAtualizado.getCpf());
        motorista.setCnh(motoristaAtualizado.getCnh());
        motorista.setPlacaCaminhao(motoristaAtualizado.getPlacaCaminhao().toUpperCase());
        motorista.setPlacaCarreta(motoristaAtualizado.getPlacaCarreta().toUpperCase());
        motorista.setCelular1(motoristaAtualizado.getCelular1());
        motorista.setCelular2(motoristaAtualizado.getCelular2());
        motoristaRepository.save(motorista);
        return new RedirectView("/cadastromotorista");
    }
    
    @PostMapping("/motoristas/excluir/{id}")
    public RedirectView excluirMotorista(@PathVariable Long id) {
        motoristaRepository.deleteById(id);
        return new RedirectView("/cadastromotorista");
    }
}