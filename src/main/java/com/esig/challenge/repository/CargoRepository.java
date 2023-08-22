package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.Vencimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

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

    public Cargo semCargo() {
        TypedQuery<Cargo> query = manager.createQuery("select Cargo() from Cargo where nomeCargo = 'Sem cargo'", Cargo.class);
        return query.getSingleResult();
    }

}
