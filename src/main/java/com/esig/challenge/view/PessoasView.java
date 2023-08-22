package com.esig.challenge.view;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.PessoaSalario;
import com.esig.challenge.service.CargoService;
import com.esig.challenge.service.PessoaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;


@Named
@ViewScoped
public class PessoasView implements Serializable {

    @Inject
    PessoaService pessoaService;

    @Inject
    CargoService cargoService;

    List<Pessoa> pessoas;
    List<Cargo> cargos;

    public List<Pessoa> getPessoasFiltro() {
        return pessoasFiltro;
    }

    public void setPessoasFiltro(List<Pessoa> pessoasFiltro) {
        this.pessoasFiltro = pessoasFiltro;
    }

    List<Pessoa> pessoasFiltro;

    public Pessoa getNovaPessoa() {
        return novaPessoa;
    }

    public void setNovaPessoa(Pessoa novaPessoa) {
        this.novaPessoa = novaPessoa;
    }

    Pessoa novaPessoa = new Pessoa();

    public PessoasView() {
    }


    public void init() {
        pessoas = pessoaService.readAll();
        cargos = cargoService.getAllCargos();
    }


    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public String getSalario(Pessoa pessoa) {
        PessoaSalario salario = pessoaService.getSalario(pessoa);
        return salario == null ? "Salario não calculado" : "R$ " + salario.getSalario();
    }

    public String getNomeCargo(Pessoa pessoa) {
        return pessoa.getCargo().getNomeCargo();
    }


    public void onRowEdit(RowEditEvent<Pessoa> event) {

        Pessoa pessoa = event.getObject();
        pessoaService.saveERecalculeSalario(pessoa);

        FacesMessage msg = new FacesMessage("Cadastro editado", String.valueOf(pessoa.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void salvar() {
        pessoaService.saveERecalculeSalario(novaPessoa);
        FacesMessage msg = new FacesMessage("Cadastro efetuado", String.valueOf(novaPessoa.getNome()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }


    public List<Cargo> getCargos() {
        return cargos;
    }


    public void deletar(Pessoa pessoa) {
        pessoaService.delete(pessoa);

        FacesMessage msg = new FacesMessage("Cadastro deletado", String.valueOf(pessoa.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }


        Pessoa pessoa = (Pessoa) value;
        String nomeCargo = pessoa.getCargo() == null ? "" : pessoa.getCargo().getNomeCargo();
        return pessoa.getNome().toLowerCase().contains(filterText)
                || pessoa.getPais().toLowerCase().contains(filterText)
                || nomeCargo.toLowerCase().contains(filterText)
                || pessoa.getEmail().toLowerCase().contains(filterText)
                || pessoa.getCidade().toLowerCase().contains(filterText)
                || pessoa.getUsuario().toLowerCase().contains(filterText)
                || pessoa.getTelefone().toLowerCase().contains(filterText);
    }

}
