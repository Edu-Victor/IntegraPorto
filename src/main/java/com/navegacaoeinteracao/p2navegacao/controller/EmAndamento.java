package com.navegacaoeinteracao.p2navegacao.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import com.navegacaoeinteracao.p2navegacao.model.Trabalhos;
import com.navegacaoeinteracao.p2navegacao.repository.TrabalhoRepository;


@Controller
public class EmAndamento {


    @Autowired
    TrabalhoRepository trabalhosRepository;


    @GetMapping("/emandamento")
    public String showCadastroMotorista(Model model) {
        List<Trabalhos>trabalhosEmAndamento = trabalhosRepository.findByStatusOrderByIdAsc("Em-Andamento");
        model.addAttribute("trabalhos", trabalhosEmAndamento);
        return "emandamento";
    }
    

    @GetMapping("/api/trabalho/{id}")
    @ResponseBody
    public Optional<Trabalhos> getTrabalhoById(@PathVariable Long id) {
        return trabalhosRepository.findById(id);
    }


    @PostMapping("/trabalho/andamento/editar/{id}")
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
        return new RedirectView("/emandamento");
    }


    @PostMapping("/trabalho/concluir/{id}")
    public String concluirTrabalho(@PathVariable("id") Long id) {
        Optional<Trabalhos> trabalhoOptional = trabalhosRepository.findById(id);
        if (trabalhoOptional.isPresent()) {
            Trabalhos trabalho = trabalhoOptional.get();
            trabalho.setStatus("Concluido");
            trabalhosRepository.save(trabalho);
        }
        return "redirect:/emandamento";
    }


    @PostMapping("/trabalho/excluir/{id}")
    public RedirectView excluirMotorista(@PathVariable Long id) {
        trabalhosRepository.deleteById(id);
        return new RedirectView("/emandamento");
    }
    
}
