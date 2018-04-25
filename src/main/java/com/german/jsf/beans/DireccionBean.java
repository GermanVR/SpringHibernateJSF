package com.german.jsf.beans;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Direccion;
import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IDireccionService;

import lombok.Getter;
import lombok.Setter;

@Component
@ManagedBean
@SessionScoped
public class DireccionBean {

	@Autowired
	private IDireccionService idireccionService;

	@Setter
	@Getter
	Direccion dir = new Direccion();

	public String insertarDireccion() {
		FacesMessage message = null;
		try {
			idireccionService.insertar(this.dir);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccion Guardada con Exito", "Session no Iniciada");

		} catch (SQLException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Insertar Direccion, verifique Datos", "Session no Iniciada");
			return "direccionP";
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "index";
	}

	public String guardaDireccion() {
		FacesMessage message = null;
		try {
			idireccionService.actualizar(this.dir);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccion Actualizada Correctamente", "Session no Iniciada");
		} catch (SQLException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Guardar Direccion, verifique Datos", "Session no Iniciada");
			return "direccionP";
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "index?faces-redirect=true";
	}

	public String editaDireccion(Direccion d) {
		this.dir = d;
		return "direccionP";
	}

	public String eliminaDireccion(Direccion d) throws SQLException {
		idireccionService.eliminar(d.getIdDireccion());
		return "index";
	}

	public String agregaDireccion(Persona p) {
		Direccion d = new Direccion();
		d.setIdPersona(p.getIdPersona());
		d.setIdDireccion(0);
		this.dir = d;
		return "direccionP";
	}
}
