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
	public Persona obtienePersonaPorId(long id) {
		return iPersonaDao.obtenerPorId(id);
	}

	@Override
	public Persona actualizarPersona(Persona p) throws SQLException {
		return iPersonaDao.actualizar(p);
	}

	@Override
	public void eliminarPersona(long id) throws SQLException {
		iPersonaDao.eliminar(id);
	}


	@Override
	public void insertarPersona(Persona o) throws SQLException {
		iPersonaDao.insertar(o);
	}

}
