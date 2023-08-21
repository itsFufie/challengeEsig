package com.esig.challenge.view;

import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.PessoaSalario;
import com.esig.challenge.repository.PessoaRepository;
import com.esig.challenge.service.PessoaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class PessoasView implements Serializable {

    @Inject
    PessoaService pessoaService;
    List<Pessoa> pessoas;


    public PessoasView() {
    }

    public List<Pessoa> getPessoas() {
        return pessoaService.readAll();
    }

    public Double getSalario(Pessoa pessoa) {
        PessoaSalario salario = pessoaService.getSalario(pessoa);
        return salario == null ? Double.valueOf(0) : salario.getSalario();
    }

    public void calcularSalarios() {
        System.out.println("Calculando salarios");
        pessoaService.calcularSalarios();
    }
}
