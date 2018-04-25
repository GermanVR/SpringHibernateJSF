package com.german.jsf.definicion;

import com.german.jsf.entidad.DAO;
import com.german.jsf.entidad.Usuario;

public interface IUsuarioDao extends DAO<Usuario, Long> {

	public Usuario validaUsuario(Usuario u);
}
