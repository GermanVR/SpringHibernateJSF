package com.german.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Direccion;
import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IPersonaService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@ManagedBean
@SessionScoped
@Slf4j
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = -6909520688534663536L;

	@Autowired
	@Qualifier("personaService")
	private IPersonaService peps;

	private List<Persona> lPersona;
	private List<Direccion> lDireccion;
	private Persona persona = new Persona();

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void obtieneTodos() {
		this.lPersona = peps.obtieneListaPersonas();
	}

	public String agregarPersona() {
		return "agregarP";
	}

	public void eliminar(Persona p) throws SQLException {
		peps.eliminar(p.getIdPersona());
		this.obtieneTodos();
	}

	public List<Persona> getlPersona() {
		return lPersona;
	}

	public String verificaSession() {
		return "manager";
	}

	public List<Direccion> getlDireccion() {
		return lDireccion;
	}

	public List<Direccion> obtieneDireccion(Persona p) {
		lDireccion = new ArrayList<>();
		for (Direccion d : p.getDireccion()) {
			lDireccion.add(d);
		}
		return lDireccion;
	}

	public void cerrarSession() throws IOException {
		log.info("Cerrando Session");
		if (lPersona != null) {
			lPersona.clear();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?faces-redirect=true");
		// return "login?faces-redirect=true";
	}

	public String guardaPersona() throws SQLException {
		peps.guardaPersona(this.persona);
		this.obtieneTodos();
		persona = new Persona();
		return "index?faces-redirect=true";
	}
}
