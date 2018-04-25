package com.german.jsf.service;

import java.sql.SQLException;
import java.util.List;

import com.german.jsf.entidad.Persona;

public interface IPersonaService {

	public List<Persona> obtieneListaPersonas();

	public Persona obtienePorId(long id);

	public void guardaPersona(Persona o) throws SQLException;

	public Persona actualizar(Persona p) throws SQLException;

	public void eliminar(long id) throws SQLException;

}
