package com.esig.challenge.service;

import com.esig.challenge.model.*;
import com.esig.challenge.repository.CargoVencimentoRepository;
import com.esig.challenge.repository.PessoaRepository;
import com.esig.challenge.repository.PessoaSalarioRepository;
import com.esig.challenge.repository.VencimentoRepository;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> readAllWithPages(int pagina) {
        return pessoaRepository.readAllWithPages(pagina, maxResults);
    }

    public List<Pessoa> readAll() {
        return pessoaRepository.readAll();
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.readById(id);
    }

    public PessoaSalario getSalario(Pessoa pessoa) {
        return pessoaSalarioRepository.readByPessoa(pessoa);
    }

    public void calcularSalarios() {
        List<Pessoa> pessoas = readAll().stream().filter(pessoa -> getSalario(pessoa) == null).collect(Collectors.toList());
        System.out.println("Iniciando calculo de salarios de: " + pessoas.size() + " funcionarios");
        for (Pessoa pessoa : pessoas) {
            recalcularSalario(pessoa);
        }
        System.out.println("Calculo finalizado");
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void saveERecalculeSalario(Pessoa pessoa) {
        recalcularSalario(pessoaRepository.save(pessoa));
    }

    public void recalcularSalario(Pessoa pessoa) {
        Cargo cargo = pessoa.getCargo();
        if (cargo != null) {
            List<CargoVencimento> vencimentos = cargoVencimentoRepository.getAllVencimentosOfCargo(cargo);
            Double salario = (double) 0;
            for (CargoVencimento vencimento : vencimentos) {
                salario += vencimento.getVencimento().getValor();
            }
            PessoaSalario pessoaSalario = getPessoaSalario(pessoa);
            pessoaSalario.setPessoa(pessoa);
            pessoaSalario.setSalario(salario);
            pessoaSalarioRepository.save(pessoaSalario);
        }
    }

    public PessoaSalario getPessoaSalario(Pessoa pessoa) {
        PessoaSalario salario = pessoaSalarioRepository.readByPessoa(pessoa);

        return salario == null ? new PessoaSalario(pessoa) : salario;
    }

    public void delete(Pessoa pessoa) {
        pessoaSalarioRepository.delete(pessoaSalarioRepository.readByPessoa(pessoa));
        pessoaRepository.delete(pessoa);

    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}
