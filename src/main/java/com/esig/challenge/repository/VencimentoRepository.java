package com.esig.challenge.repository;

import com.esig.challenge.model.Vencimento;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class VencimentoRepository extends AbstractRepository<Vencimento> implements Serializable {

    public VencimentoRepository(EntityManager manager) {
        super(manager);
        this.type = Vencimento.class;
    }

    public VencimentoRepository() {
        this.type = Vencimento.class;
    }
}
