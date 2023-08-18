package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.CargoVencimento;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class CargoVencimentoRepository extends AbstractRepository<CargoVencimento> implements Serializable {


    public CargoVencimentoRepository(EntityManager manager) {
        super(manager);
        this.type = CargoVencimento.class;
    }

    public CargoVencimentoRepository() {
        this.type = CargoVencimento.class;
    }
}
