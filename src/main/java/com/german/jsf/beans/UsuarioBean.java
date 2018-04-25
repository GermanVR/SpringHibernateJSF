package com.german.jsf.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.german.jsf.entidad.Usuario;
import com.german.jsf.service.IUsuarioService;

@ManagedBean
@Component
@SessionScoped
public class UsuarioBean {

	@Autowired
	private IUsuarioService iUsuarioService;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String validaUsuario() {
		FacesMessage message = null;

		Usuario usuario = iUsuarioService.validaUsuario(this.usuario);

		if (usuario != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			return "index?faces-redirect=true";
		}
		this.limpiaUsuario();
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Session", "Session no Iniciada");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "login";
	}

	public boolean validaSession() {
		// FacesMessage message = null;
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null) {
			return true;
		}
		try {
			// message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Session",
			// "Session no Iniciada");
			// FacesContext.getCurrentInstance().addMessage(null, message);
			this.limpiaUsuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void limpiaUsuario() {
		this.usuario.setName("");
		this.usuario.setPassword("");
		this.usuario.setStatus("");
		this.usuario.setId(0);
		this.usuario.setUser("");

	}

}
