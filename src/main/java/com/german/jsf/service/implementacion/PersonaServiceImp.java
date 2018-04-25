package com.german.jsf.service.implementacion;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.german.jsf.definicion.IPersonaDao;
import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IPersonaService;

@Service("personaService")
public class PersonaServiceImp implements IPersonaService {

	@Autowired
	private IPersonaDao iPersonaDao;

	@Override
	public List<Persona> obtieneListaPersonas() {
		return iPersonaDao.obtenerTodos();
	}

	@Override
	public Persona obtienePorId(long id) {
		return iPersonaDao.obtenerPorId(id);
	}

	@Override
	public Persona actualizar(Persona p) throws SQLException {
		return iPersonaDao.actualizar(p);
	}

	@Override
	public void eliminar(long id) throws SQLException {
		iPersonaDao.eliminar(id);
	}

	@Override
	public Persona obtieneDireccion(long id) {

		return null;
	}

	@Override
	public void guardaPersona(Persona o) throws SQLException {
		iPersonaDao.insertar(o);
	}

}
