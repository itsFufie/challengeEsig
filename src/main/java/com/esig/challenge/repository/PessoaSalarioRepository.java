package com.esig.challenge.repository;

import com.esig.challenge.model.PessoaSalario;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public class PessoaSalarioRepository extends AbstractRepository<PessoaSalario> implements Serializable {

    public PessoaSalarioRepository(EntityManager manager) {
        super(manager);
        this.type = PessoaSalario.class;
    }
}
