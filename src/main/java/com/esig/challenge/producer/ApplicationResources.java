package com.esig.challenge.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@ApplicationScoped
public class ApplicationResources {


    @PersistenceContext(unitName = "challengeEsig")
    private EntityManager em;

    @Produces
    @ApplicationScoped
    @Default
    public EntityManager create() {
        return em;
    }

    public void close(@Disposes @Default EntityManager em) {
        em.close();
    }
}
