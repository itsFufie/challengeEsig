package com.esig.challenge.repository;

import com.esig.challenge.model.Vencimento;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public class VencimentoRepository extends AbstractRepository<Vencimento> implements Serializable {

    public VencimentoRepository(EntityManager manager) {
        super(manager);
        this.type = Vencimento.class;
    }
}
