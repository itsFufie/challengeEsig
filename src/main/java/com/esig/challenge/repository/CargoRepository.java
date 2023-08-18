package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class CargoRepository extends AbstractRepository<Cargo> implements Serializable {

    private static final long serialVersionUID = 1L;

    public CargoRepository(EntityManager manager) {
        super(manager);
        this.type = Cargo.class;
    }

    public CargoRepository() {
        this.type = Cargo.class;
    }
}
