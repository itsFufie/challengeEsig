package com.esig.challenge.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pessoa_salario")
public class PessoaSalario implements Serializable, Model {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    private Double salario;

    public PessoaSalario() {
    }

    public PessoaSalario(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaSalario)) return false;
        PessoaSalario that = (PessoaSalario) o;
        return Objects.equals(getPessoa(), that.getPessoa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "PessoaSalario{" +
                "id=" + id +
                ", pessoa=" + pessoa +
                ", salario=" + salario +
                '}';
    }
}
