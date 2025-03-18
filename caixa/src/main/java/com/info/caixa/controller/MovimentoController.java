/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.caixa.controller;

import com.info.caixa.model.Extrato;
import com.info.caixa.model.Movimento;
import com.info.caixa.repository.MovimentoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author info20242
 */
@Controller
@RequestMapping({"/movimento"})
public class MovimentoController {

    @Autowired
    MovimentoRepository mr;
    List<Movimento> listaMovimento;
    List<Movimento> listaExtrato;
    
    @GetMapping("/")
    public String listarMovimento(Model model) {
        listaMovimento = mr.findAll();
        model.addAttribute("listamovimento", listaMovimento);
        return "paginas/index.html";
    }

    @GetMapping("/novo")
    public String novoMovimento(Model model) {
        Movimento movimento = new Movimento();
        model.addAttribute("movimento", movimento);
        return "paginas/movimento-form.html";
    }
    
    @GetMapping("/novoExtrato")
    public String novoExtrato(Model model) {
        Extrato extrato = new Extrato();
        model.addAttribute("extrato", extrato);
        return "paginas/extrato";
    }
    
    @PostMapping("/extrato")
    public String montaExtrato(@ModelAttribute("extrato") Extrato extrato, Model model){
        if(tipo)
        if(extrato.getDataInicial()!=new Date()){
            listaExtrato = mr.listaExtrato(extrato.getDataInicial(), extrato.getDataInicial());
        }
        listaExtrato = mr.listaExtrato(extrato.getDataInicial(), extrato.getDataInicial());
        model.addAttribute("listaextrato", listaExtrato);
        return "paginas/tExtrato.html";
    }

    @PostMapping("/salvar")
    public String salvaMovimento(@ModelAttribute("movimento") Movimento movimento) {
        mr.save(movimento);
        return "redirect:/movimento/";
    }
}
