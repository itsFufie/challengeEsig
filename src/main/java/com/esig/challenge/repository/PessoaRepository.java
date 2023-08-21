package com.esig.challenge.repository;

import com.esig.challenge.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public class PessoaRepository extends AbstractRepository<Pessoa> implements Serializable {


    public PessoaRepository(EntityManager manager) {
        super(manager);
        this.type = Pessoa.class;
    }


    public PessoaRepository() {
        this.type = Pessoa.class;
    }

    public List<Pessoa> readAllWithPages(int page, int max) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<Pessoa> root = criteriaQuery.from(type);
        criteriaQuery.select(root);

        TypedQuery<Pessoa> query = manager.createQuery(criteriaQuery);
        query.setFirstResult((page - 1) * max);
        query.setMaxResults(max);
        return query.getResultList();
    }
}
