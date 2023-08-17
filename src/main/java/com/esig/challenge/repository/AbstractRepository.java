package com.esig.challenge.repository;

import com.esig.challenge.model.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractRepository<T extends Model> implements Repository<T> {

    EntityManager manager;
    Class<T> type;

    public AbstractRepository(EntityManager manager) {
        this.manager = manager;
    }

    public AbstractRepository(Class<T> type) {
        this.type = type;
    }

    public AbstractRepository() {

    }

    @Override
    public T save(T object) {
        return manager.merge(object);
    }

    @Override
    public T readById(Long id) {

        return manager.find(type, id);
    }

    @Override
    public List<T> readAll() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);

        TypedQuery<T> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void delete(T object) {
        object = readById(object.getId());
        manager.remove(object);
    }
}
