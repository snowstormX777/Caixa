/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.caixa.controller;

import com.info.caixa.model.Extrato;
import com.info.caixa.model.Movimento;
import com.info.caixa.model.Saldo;
import com.info.caixa.repository.MovimentoRepository;
import com.info.caixa.repository.SaldoRepository;
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
    @Autowired
    SaldoRepository sr;
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
    public String montaExtrato(@ModelAttribute("extrato") Extrato extrato, Model model) {
        Date di = extrato.getDataInicial();
        Date df = extrato.getDataFinal();
        if(extrato.getDataInicial().after(extrato.getDataFinal())){
            extrato.setDataFinal(di);
            extrato.setDataInicial(df);
        }
        if (extrato.getTipo() == false) {
            extrato.setDataFinal(extrato.getDataInicial());
        } else {
            extrato.setDataFinal(extrato.getDataFinal());
        }
        Movimento saldoI = new Movimento();
        Movimento saldoF = new Movimento();
        saldoI.setData(extrato.getDataInicial());
        saldoI.setDescricao("Saldo Inicial");
        saldoI.setTipo(1);
        saldoI.setValor(sr.pegaValorAnterior(extrato.getDataInicial()).getLast());
        Double valorF = saldoI.getValor();
        saldoF.setData(extrato.getDataFinal());
        saldoF.setDescricao("Saldo Final");
        System.out.println(valorF);
        saldoF.setTipo(1);
        listaExtrato = mr.listaExtrato(extrato.getDataInicial(), extrato.getDataFinal());
        saldoF.setValor(sr.pegaValorFinal(extrato.getDataFinal()).getLast());

        listaExtrato.addFirst(saldoI);
        listaExtrato.addLast(saldoF);

        model.addAttribute("listaextrato", listaExtrato);
        return "paginas/tExtrato.html";
    }

    @PostMapping("/salvar")
    public String salvaMovimento(@ModelAttribute("movimento") Movimento movimento) {
        Saldo saldo = new Saldo();
        mr.save(movimento);
        if (sr.findByData(movimento.getData()).isEmpty()) {
            saldo.setData(movimento.getData());
            saldo.setValor(sr.pegaValorAnterior(movimento.getData()).getLast());
            sr.save(saldo);
            if (movimento.getTipo() == 0) {
                sr.atualizaSaldo(-movimento.getValor(), movimento.getData());
            } else {
                sr.atualizaSaldo(movimento.getValor(), movimento.getData());
            }
        } else {
            if (movimento.getTipo() == 0) {
                sr.atualizaSaldo(-movimento.getValor(), movimento.getData());
            } else {
                sr.atualizaSaldo(movimento.getValor(), movimento.getData());
            }
        }
        return "redirect:/movimento/";
    }
}
