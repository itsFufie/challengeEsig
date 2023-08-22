package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.Vencimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Cargo> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<Cargo> root = criteriaQuery.from(type);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("nomeCargo"), "Sem cargo"));

        TypedQuery<Cargo> query = manager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

}
