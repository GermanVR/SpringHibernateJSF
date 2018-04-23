package com.german.jsf.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IPersonaService;

@Component
@ManagedBean
//@ViewScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = -6909520688534663536L;

	@Autowired
	@Qualifier("personaService")
	private IPersonaService peps;

	private List<Persona> lPersona;

	public void obtieneTodos() {
		this.lPersona = peps.obtieneListaPersonas();
	}

	public List<Persona> getlPersona() {
		return lPersona;
	}

	public static void main(String[] args) {

	}

}
