package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.CargoVencimento;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public class CargoVencimentoRepository extends AbstractRepository<CargoVencimento> implements Serializable {


    public CargoVencimentoRepository(EntityManager manager) {
        super(manager);
        this.type = CargoVencimento.class;
    }


}
