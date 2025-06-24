package com.navegacaoeinteracao.p2navegacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoRepository;

@Controller
public class ConcluidosController {

    @Autowired
    TrabalhoRepository trabalhosRepository;


    @GetMapping("/concluidos")
    public String concluidos(Model model){
        List<Trabalhos>trabalhosEmAndamento = trabalhosRepository.findByStatusOrderByIdAsc("Concluido");
        model.addAttribute("trabalhos", trabalhosEmAndamento);
        return "concluidos";
    }


    @PostMapping("/trabalho/concluidos/editar/{id}")
    public RedirectView editarMotorista(@PathVariable Long id, @ModelAttribute Trabalhos trabalhoAtualizado) {
        Trabalhos trabalho = trabalhosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de Motorista inválido:" + id));
        trabalho.setTransportadora(trabalhoAtualizado.getTransportadora().toUpperCase());
        trabalho.setBooking(trabalhoAtualizado.getBooking().toUpperCase());
        trabalho.setNavio(trabalhoAtualizado.getNavio().toUpperCase());
        trabalho.setOperacao(trabalhoAtualizado.getOperacao());
        trabalho.setEntrega(trabalhoAtualizado.getEntrega());
        trabalho.setColeta(trabalhoAtualizado.getColeta());
        trabalho.setDt_recebimento(trabalhoAtualizado.getDt_recebimento());
        trabalho.setDt_termino(trabalhoAtualizado.getDt_termino());
        trabalho.setTipo_container(trabalhoAtualizado.getTipo_container());
        trabalho.setQt_container(trabalhoAtualizado.getQt_container());
        trabalho.setValor(trabalhoAtualizado.getValor());
        trabalho.setJanelas(trabalhoAtualizado.getJanelas());
        trabalho.setStatus(trabalhoAtualizado.getStatus());
        trabalhosRepository.save(trabalho);
        return new RedirectView("/concluidos");
    }


    @PostMapping("/trabalho/concluidos/excluir/{id}")
    public RedirectView excluirMotorista(@PathVariable Long id) {
        trabalhosRepository.deleteById(id);
        return new RedirectView("/concluidos");
    }
    
}
