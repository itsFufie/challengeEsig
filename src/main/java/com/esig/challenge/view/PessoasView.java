package com.esig.challenge.view;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.model.PessoaSalario;
import com.esig.challenge.repository.PessoaRepository;
import com.esig.challenge.service.CargoService;
import com.esig.challenge.service.PessoaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class PessoasView implements Serializable {

    @Inject
    PessoaService pessoaService;

    @Inject
    CargoService cargoService;

    List<Pessoa> pessoas;
    List<Cargo> cargos;


    public PessoasView() {
    }


    public void init() {
        pessoas = pessoaService.readAll();
        cargos = cargoService.getAllCargos();
    }


    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Double getSalario(Pessoa pessoa) {
        PessoaSalario salario = pessoaService.getSalario(pessoa);
        return salario == null ? Double.valueOf(0) : salario.getSalario();
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

    public List<Cargo> getCargos() {
        return cargos;
    }

}
