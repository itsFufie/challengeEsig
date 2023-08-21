package com.esig.challenge.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "migration")
public class Migration implements Serializable, Model {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeUnico;
    private boolean ran;

    public Migration() {

    }

    public Migration(Long id, String nomeUnico, boolean ran) {
        this.id = id;
        this.nomeUnico = nomeUnico;
        this.ran = ran;
    }

    public boolean isRan() {
        return ran;
    }

    public void setRan(boolean ran) {
        this.ran = ran;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Migration)) return false;
        Migration migration = (Migration) o;
        return Objects.equals(getNomeUnico(), migration.getNomeUnico());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomeUnico());
    }

    public String getNomeUnico() {
        return nomeUnico;
    }

    public void setNomeUnico(String nomeUnico) {
        this.nomeUnico = nomeUnico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
