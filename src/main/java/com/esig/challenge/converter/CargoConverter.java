package com.esig.challenge.converter;

import com.esig.challenge.model.Cargo;
import com.esig.challenge.model.Pessoa;
import com.esig.challenge.repository.CargoRepository;
import com.esig.challenge.service.CargoService;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter<Cargo> {


    @Override
    public Cargo getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            return CDI.current().select(CargoService.class).get().findById(Long.valueOf(s));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " não é um id valido"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Cargo cargo) {
        if (cargo == null) {
            return "";
        }

        return String.valueOf(cargo.getId());
    }
}
