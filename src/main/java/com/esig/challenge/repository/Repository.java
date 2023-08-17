package com.esig.challenge.repository;

import com.esig.challenge.model.Model;
import jakarta.persistence.EntityManager;

import java.util.List;


public interface Repository<T extends Model> {

    public T save(T object);

    public T readById(Long id);

    public List<T> readAll();

    public void delete(T object);

}
