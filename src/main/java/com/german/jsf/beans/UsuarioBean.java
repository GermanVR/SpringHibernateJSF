package com.german.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Usuario;
import com.german.jsf.service.IUsuarioService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ManagedBean
@Component
@SessionScoped
@Slf4j
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Setter
	@Getter
	private Usuario usuario = null;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String validaUsuario() {
		Usuario usuario = iUsuarioService.validaUsuario(this.usuario);

		if (usuario != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Bienvenido: " + usuario.getName()));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "index?faces-redirect=true";
		}
		this.limpiaUsuario();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login", "Usuario o Contraeña incorrecta"));
		return "login";
	}

	public void validaSession() {
		if ((usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")) != null) {
			log.info("Session Correcta");

			// return true;
		} else {
			cerrarSession();
		}
		// log.info("Session Erronea... redirigiendo...");
		// return false;
	}

	public void cerrarSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().invalidateSession();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Session", "Session ha terminado"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.getExternalContext().redirect("login.xhtml?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void limpiaUsuario() {
		log.info("Antes de la limpieza {} ", usuario);
		if (usuario != null) {
			this.usuario.setUser(null);
			this.usuario.setPassword(null);
			log.info("usuario Limpiado");
		}else {
			usuario = new Usuario();
		}
	}

}
