package com.german.jsf.service;

import com.german.jsf.entidad.Usuario;

public interface IUsuarioService {

	public Usuario obtieneUsuario(long id);

	public Usuario validaUsuario(Usuario u);
}
