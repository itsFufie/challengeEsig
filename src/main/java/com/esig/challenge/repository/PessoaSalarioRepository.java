package com.esig.challenge.repository;

import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.PessoaSalario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class PessoaSalarioRepository extends AbstractRepository<PessoaSalario> implements Serializable {

    public PessoaSalarioRepository(EntityManager manager) {
        super(manager);
        this.type = PessoaSalario.class;
    }

    public PessoaSalarioRepository() {
        this.type = PessoaSalario.class;
    }

    public PessoaSalario readByPessoa(Pessoa pessoa) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<PessoaSalario> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<PessoaSalario> root = criteriaQuery.from(type);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("pessoa"), pessoa));

        TypedQuery<PessoaSalario> query = manager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
