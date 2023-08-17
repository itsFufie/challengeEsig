package com.esig.challenge.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cargo_vencimento")
public class CargoVencimento implements Serializable, Model {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @ManyToOne
    @JoinColumn(name = "vencimento_id")
    private Vencimento vencimento;

    public void setId(Long id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public Long getId() {
        return id;
    }
}
