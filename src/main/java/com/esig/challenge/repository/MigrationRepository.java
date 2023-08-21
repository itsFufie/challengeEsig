package com.esig.challenge.repository;

import com.esig.challenge.model.Migration;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
public class MigrationRepository extends AbstractRepository<Migration> {

    public MigrationRepository() {
        this.type = Migration.class;
    }

    public Migration findByNomeUnico(String nome) {
        TypedQuery<Migration> query = manager.createQuery("select new Migration(id, nomeUnico, ran) from Migration where nomeUnico = :nome", Migration.class);
        query.setParameter("nome", nome);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
