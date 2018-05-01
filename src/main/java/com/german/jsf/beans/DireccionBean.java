package com.german.jsf.beans;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Direccion;
import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IDireccionService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@ManagedBean
@ViewScoped
@Slf4j
public class DireccionBean {

	@Autowired
	private IDireccionService idireccionService;

	@Setter
	@Getter
	private Direccion dir;

	@Getter
	@Setter
	private String accion;

	@PostConstruct
	public void init() {
		dir = new Direccion();
	}

	public void insertarDireccion() {
		FacesMessage message = null;
		try {
			idireccionService.insertar(dir);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccion", "Guardada con Exito");
			PrimeFaces.current().executeScript("PF('wdialogoDireccion').hide();");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Direccion", "Error al Insertar Direccion, verifique Datos");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void actualizarDireccion() {
		FacesMessage message = null;
		try {
			idireccionService.actualizar(this.dir);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizada Correctamente", "Direccion");
			PrimeFaces.current().executeScript("PF('wdialogoDireccion').hide();");
		} catch (SQLException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, verifique Datos", "Direccion");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		// return "index?faces-redirect=true";
	}

	public void editaDireccion(Direccion d) {
		this.dir = d;
		setAccion("A");
		// RequestContext req = RequestContext.getCurrentInstance();
		// req.execute("PF('wdialogoDireccion').show();");

		PrimeFaces.current().executeScript("PF('wdialogoDireccion').show();");
	}

	public void eliminaDireccion(Direccion d) throws SQLException {
		idireccionService.eliminar(d.getIdDireccion());
		// return "index?faces-redirect=true";
	}

	public void agregaDireccion(Persona p) {
		dir.setCalle(null);
		dir.setCiudad(null);
		dir.setCodigoPostal(null);
		dir.setColonia(null);
		dir.setMunicipio(null);
		dir.setTelefono(null);
		dir.setIdDireccion(0);
		dir.setIdPersona(p.getIdPersona());
		setAccion("I");
		PrimeFaces.current().executeScript("PF('wdialogoDireccion').show();");

		log.info("Dir persona es: " + dir.getIdPersona());
		log.info("Bean Persona es: {} ", p);
		log.info("Bean Dir es: {} ", dir);
	}

	public void validaPersona() {
		if (dir.getIdPersona() == 0) {
			try {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione una Direccion"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
