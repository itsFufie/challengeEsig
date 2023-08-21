package com.esig.challenge.service;

import com.esig.challenge.model.*;
import com.esig.challenge.repository.CargoVencimentoRepository;
import com.esig.challenge.repository.PessoaRepository;
import com.esig.challenge.repository.PessoaSalarioRepository;
import com.esig.challenge.repository.VencimentoRepository;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

public class PessoaService implements Serializable {

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    PessoaSalarioRepository pessoaSalarioRepository;

    @Inject
    CargoVencimentoRepository cargoVencimentoRepository;

    int maxResults = 10;

    public PessoaService() {

    }

    public List<Pessoa> readAllWithPages(int pagina) {
        return pessoaRepository.readAllWithPages(pagina, maxResults);
    }

    public List<Pessoa> readAll() {
        return pessoaRepository.readAll();
    }

    public PessoaSalario getSalario(Pessoa pessoa) {
        return pessoaSalarioRepository.readByPessoa(pessoa);
    }

    public void calcularSalarios() {
        List<Pessoa> pessoas = readAll();
        System.out.println("Iniciando calculo de salarios de: " + pessoas.size() + " funcionarios");
        for (Pessoa pessoa : pessoas) {
            Cargo cargo = pessoa.getCargo();
            if (cargo != null) {
                List<CargoVencimento> vencimentos = cargoVencimentoRepository.getAllVencimentosOfCargo(cargo);
                Double salario = (double) 0;
                for (CargoVencimento vencimento : vencimentos) {
                    salario += vencimento.getVencimento().getValor();
                }
                PessoaSalario pessoaSalario = new PessoaSalario();
                pessoaSalario.setPessoa(pessoa);
                pessoaSalario.setSalario(salario);
                pessoaSalarioRepository.save(pessoaSalario);
            }
        }
        System.out.println("Calculo finalizado");
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}
