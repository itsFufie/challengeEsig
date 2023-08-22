package com.esig.challenge.service;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.repository.CargoRepository;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

public class CargoService implements Serializable {

    @Inject
    CargoRepository cargoRepository;

    public CargoService() {
    }

    public List<Cargo> getAllCargos() {
        return cargoRepository.readAll();
    }

    public Cargo findById(Long id) {
        return cargoRepository.readById(id);
    }

}
