package com.esig.challenge.converter;

import com.esig.challenge.model.Pessoa;
import com.esig.challenge.service.PessoaService;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;


@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter<Pessoa> {


    @Override
    public Pessoa getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            return CDI.current().select(PessoaService.class).get().findById(Long.valueOf(s));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " não é um id valido"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Pessoa pessoa) {
        if (pessoa == null) {
            return "";
        }

        return String.valueOf(pessoa.getId());
    }
}
