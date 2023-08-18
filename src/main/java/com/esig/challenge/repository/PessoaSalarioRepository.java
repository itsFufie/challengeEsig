package com.esig.challenge.repository;

import com.esig.challenge.model.PessoaSalario;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class PessoaSalarioRepository extends AbstractRepository<PessoaSalario> implements Serializable {

    public PessoaSalarioRepository(EntityManager manager) {
        super(manager);
        this.type = PessoaSalario.class;
    }

    public PessoaSalarioRepository() {
        this.type = PessoaSalario.class;
    }
}
