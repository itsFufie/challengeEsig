package com.esig.challenge.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable, Model {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cidade;
    private String email;
    private String CEP;
    private String enderco;
    private String pais;
    private String usuario;
    private String telefone;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEnderco() {
        return enderco;
    }

    public void setEnderco(String enderco) {
        this.enderco = enderco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            this.dataNascimento = formatter.parse(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Cargo getCargo() {
        return cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getEmail(), pessoa.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}

