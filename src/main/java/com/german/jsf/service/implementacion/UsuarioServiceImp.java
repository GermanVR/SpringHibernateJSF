package com.german.jsf.service.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.german.jsf.definicion.IUsuarioDao;
import com.german.jsf.entidad.Usuario;
import com.german.jsf.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private IUsuarioDao iUsuarioDao;

	@Override
	public Usuario obtieneUsuario(long id) {
		return iUsuarioDao.obtenerPorId(id);
	}

	@Override
	public Usuario validaUsuario(Usuario u) {
		Usuario uf = iUsuarioDao.validaUsuario(u);
		return uf;
	}

}
