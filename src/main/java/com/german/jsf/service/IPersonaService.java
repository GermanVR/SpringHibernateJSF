package com.german.jsf.service;

import java.sql.SQLException;
import java.util.List;

import com.german.jsf.entidad.Persona;

public interface IPersonaService {

	public List<Persona> obtieneListaPersonas();

	public Persona obtienePersonaPorId(long id);

	public void insertarPersona(Persona o) throws SQLException;

	public Persona actualizarPersona(Persona p) throws SQLException;

	public void eliminarPersona(long id) throws SQLException;

}
