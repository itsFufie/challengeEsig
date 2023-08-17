package com.esig.challenge;

import com.esig.challenge.model.CargoVencimento;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.Vencimento;
import com.esig.challenge.repository.*;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import com.esig.challenge.model.Cargo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class LoadOnStartUp implements ServletContextListener {


    CargoRepository cargoRepository;
    VencimentoRepository vencimentoRepository;
    PessoaRepository pessoaRepository;
    CargoVencimentoRepository cargoVencimentoRepository;

    public void contextInitialized(ServletContextEvent sce) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("challengeEsig");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        cargoRepository = new CargoRepository(em);
        vencimentoRepository = new VencimentoRepository(em);
        pessoaRepository = new PessoaRepository(em);
        cargoVencimentoRepository = new CargoVencimentoRepository(em);


        iniciarTabelaCargos(em);
        iniciarTabelaVencimentos(em);
        iniciarTabelaPessoas(em);
        iniciarTabelaCargosVencimentos(em);

        em.getTransaction().commit();
        em.close();
        emf.close();


    }


    public void iniciarTabelaCargos(EntityManager em) {

        try {
            String resourceName = "/initial-database/Cargos.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                Cargo cargo = new Cargo();
                cargo.setNomeCargo(nextLine[1]);
                cargoRepository.save(cargo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniciarTabelaVencimentos(EntityManager em) {
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

    public void iniciarTabelaPessoas(EntityManager em) {
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
                pessoa.setCargo(cargoRepository.readById(Long.valueOf(nextLine[10])));
                pessoaRepository.save(pessoa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniciarTabelaCargosVencimentos(EntityManager em) {
        try {
            String resourceName = "/initial-database/Cargo_Vencimentos.csv";
            CSVReader csvReader = getCsvReader(resourceName);
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                CargoVencimento cargoVencimento = new CargoVencimento();
                cargoVencimento.setCargo(cargoRepository.readById(Long.valueOf(nextLine[1])));
                cargoVencimento.setVencimento(vencimentoRepository.readById(Long.valueOf(nextLine[2])));
                cargoVencimentoRepository.save(cargoVencimento);
            }

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

}
