package com.esig.challenge.repository;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.CargoVencimento;
import com.esig.challenge.model.PessoaSalario;
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
public class CargoVencimentoRepository extends AbstractRepository<CargoVencimento> implements Serializable {


    public CargoVencimentoRepository(EntityManager manager) {
        super(manager);
        this.type = CargoVencimento.class;
    }

    public CargoVencimentoRepository() {
        this.type = CargoVencimento.class;
    }

    public List<CargoVencimento> getAllVencimentosOfCargo(Cargo cargo) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<CargoVencimento> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<CargoVencimento> root = criteriaQuery.from(type);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cargo"), cargo));

        TypedQuery<CargoVencimento> query = manager.createQuery(criteriaQuery);
        return query.getResultList();


    }
}
