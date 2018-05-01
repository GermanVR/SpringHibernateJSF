package com.german.jsf.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
@Slf4j
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = -6909520688534663536L;

	@Autowired
	@Qualifier("personaService")
	private IPersonaService peps;

	private List<Persona> lPersona = new ArrayList<>();

	public List<Persona> getlPersona() {
		return lPersona;
	}

	private String accion;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
		if (accion.equals("I")) {
			persona = new Persona();
		}
		log.info("Accion es: " + accion);
	}

	@Getter
	private List<Direccion> lDireccion;

	@Setter
	@Getter
	private Persona persona;

	public void recarga() {
		obtieneTodos();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Lista Recargada Correctamente..."));
	}

	public void obtieneTodos() {
		log.info("Entro en ObtieneTodos");
		this.lPersona = peps.obtieneListaPersonas();
	}

	public String agregarPersona() {
		return "agregarP?faces-redirect=true";
	}

	public void eliminar(Persona p) throws SQLException {
		peps.eliminarPersona(p.getIdPersona());
//		this.obtieneTodos();
	}

	public void preparaPersona() {
		setAccion("I");
	}

	public void modificaPersona(Persona p) {
		log.info("Entro en Modificar y PErsona vale: {}", p);
		this.persona = p;
		setAccion("A");
		log.info("Accion Vale: " + accion);
		// return "modificaP?faces-redirect=true";
	}

	public List<Direccion> obtieneDireccion(Persona p) {
		lDireccion = new ArrayList<>();
		for (Direccion d : p.getDireccion()) {
			lDireccion.add(d);
		}
		return lDireccion;
	}

	public void insertarPersona() {
		try {
			peps.insertarPersona(this.persona);
			this.obtieneTodos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Persona", "Persona Insertada Correctamente"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Persona", "Error al Insertar Persona"));
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		// return "index?faces-redirect=true";
	}

	public void actualizarPersona() {
		try {
			peps.actualizarPersona(this.persona);
//			this.obtieneTodos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Persona", "Persona Actualizada Correctamente"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Persona", "Error al Actualizar Persona"));
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		// return "index?faces-redirect=true";
	}

}
