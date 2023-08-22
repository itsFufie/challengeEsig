package com.esig.challenge;

import com.esig.challenge.model.*;
import com.esig.challenge.repository.*;
import com.esig.challenge.service.PessoaService;
import com.opencsv.CSVReader;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import jakarta.inject.Inject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class LoadOnStartUp implements ServletContextListener {

    String nomeUnicoFreshDatabase = "freshDataBase";
    Migration migration;
    @Inject
    CargoRepository cargoRepository;
    @Inject
    VencimentoRepository vencimentoRepository;
    @Inject
    PessoaRepository pessoaRepository;
    @Inject
    CargoVencimentoRepository cargoVencimentoRepository;
    @Inject
    PessoaService pessoaService;
    @Inject
    MigrationRepository migrationRepository;

    public void contextInitialized(ServletContextEvent sce) {

        if (isFreshDatabase()) {
            loadMigration();
            iniciarTabelaCargos();
            iniciarTabelaVencimentos();
            iniciarTabelaPessoas();
            iniciarTabelaCargosVencimentos();
            markMigrationRan();
        } else {
            System.out.println("Database ja populada, pulando migration inicial!");
        }
        calcularSalarios();

    }

    public boolean isFreshDatabase() {
        migration = migrationRepository.findByNomeUnico(nomeUnicoFreshDatabase);
        if (migration != null) {
            return !migration.isRan();
        }
        return true;
    }

    public void loadMigration() {
        migration = new Migration();
        migration.setNomeUnico(nomeUnicoFreshDatabase);
        migration.setRan(false);
        migration = migrationRepository.save(migration);
    }

    public void markMigrationRan() {
        migration.setRan(true);
        migrationRepository.save(migration);
    }


    public void iniciarTabelaCargos() {

        try {
            String resourceName = "/initial-database/Cargos.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                Cargo cargo = new Cargo();
                cargo.setNomeCargo(nextLine[1]);
                cargoRepository.save(cargo);
            }
            Cargo cargo = new Cargo();
            cargo.setNomeCargo("Sem cargo");
            cargoRepository.save(cargo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniciarTabelaVencimentos() {
        try {
            String resourceName = "/initial-database/Vencimentos.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                Vencimento vencimento = new Vencimento();
                vencimento.setDescricao(nextLine[1]);
                vencimento.setValor(Double.valueOf(nextLine[2]));
                vencimento.setTipo(nextLine[3]);
                vencimentoRepository.save(vencimento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarTabelaPessoas() {
        try {
            String resourceName = "/initial-database/Pessoas.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nextLine[1]);
                pessoa.setCidade(nextLine[2]);
                pessoa.setEmail(nextLine[3]);
                pessoa.setCEP(nextLine[4]);
                pessoa.setEnderco(nextLine[5]);
                pessoa.setPais(nextLine[6]);
                pessoa.setUsuario(nextLine[7]);
                pessoa.setTelefone(nextLine[8]);
                pessoa.setDataNascimento(nextLine[9]);
                Long id = obterLong(nextLine[10]);
                if (id != null) {
                    pessoa.setCargo(cargoRepository.readById(id));
                } else {
                    pessoa.setCargo(cargoRepository.semCargo());
                }
                pessoaRepository.save(pessoa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniciarTabelaCargosVencimentos() {
        try {
            String resourceName = "/initial-database/Cargo_Vencimentos.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                CargoVencimento cargoVencimento = new CargoVencimento();
                cargoVencimento.setCargo(cargoRepository.readById(obterLong(nextLine[1])));
                cargoVencimento.setVencimento(vencimentoRepository.readById(obterLong(nextLine[2])));
                cargoVencimentoRepository.save(cargoVencimento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void calcularSalarios() {
        try {
            pessoaService.calcularSalarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CSVReader getCsvReader(String resourceName) throws FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(resourceName);
        FileReader fileReader = new FileReader(resource.getFile());
        return new CSVReader(fileReader);
    }

    public Long obterLong(String valor) {
        try {
            return Long.valueOf(valor);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
