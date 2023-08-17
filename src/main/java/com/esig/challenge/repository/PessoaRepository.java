package com.esig.challenge.repository;

import com.esig.challenge.model.Pessoa;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public class PessoaRepository extends AbstractRepository<Pessoa> implements Serializable {


    public PessoaRepository(EntityManager manager) {
        super(manager);
        this.type = Pessoa.class;
    }


}
