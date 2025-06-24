package com.navegacaoeinteracao.p2navegacao.controller;

import com.navegacaoeinteracao.p2navegacao.model.Aviso;
import com.navegacaoeinteracao.p2navegacao.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private AvisoRepository avisoRepository;

    @GetMapping("/index")
    public String showHomePage(Model model) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC, "id");
        List<Aviso> avisos = avisoRepository.findAll(sortByIdDesc);
        model.addAttribute("avisos", avisos);
        return "index";
    }

    @PostMapping("/avisos/adicionar")
    public RedirectView adicionarAviso(@ModelAttribute Aviso aviso) {
        avisoRepository.save(aviso);
        return new RedirectView("/index");
    }

    @GetMapping("/api/avisos/{id}")
    @ResponseBody
    public Optional<Aviso> getAvisoById(@PathVariable Long id) {
        return avisoRepository.findById(id);
    }

    @PostMapping("/avisos/editar/{id}")
    public RedirectView editarAviso(@PathVariable Long id, @ModelAttribute Aviso avisoAtualizado) {
        Optional<Aviso> optionalAviso = avisoRepository.findById(id);
        if (optionalAviso.isPresent()) {
            Aviso aviso = optionalAviso.get();
            aviso.setTitulo(avisoAtualizado.getTitulo());
            aviso.setMensagem(avisoAtualizado.getMensagem());
            avisoRepository.save(aviso);
        }
        return new RedirectView("/index");
    }

    @PostMapping("/avisos/excluir/{id}")
    public RedirectView excluirAviso(@PathVariable Long id) {
        avisoRepository.deleteById(id);
        return new RedirectView("/index");
    }
}