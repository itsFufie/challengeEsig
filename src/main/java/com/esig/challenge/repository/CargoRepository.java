package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import jakarta.persistence.EntityManager;

import java.io.Serializable;


public class CargoRepository extends AbstractRepository<Cargo> implements Serializable {

    private static final long serialVersionUID = 1L;

    public CargoRepository(EntityManager manager) {
        super(manager);
        this.type = Cargo.class;
    }
}
