package com.esig.challenge.repository;

import com.esig.challenge.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class PessoaRepository extends AbstractRepository<Pessoa> implements Serializable {


    public PessoaRepository(EntityManager manager) {
        super(manager);
        this.type = Pessoa.class;
    }


    public PessoaRepository() {
        this.type = Pessoa.class;
    }
}
