package com.german.jsf.implementacion;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.german.jsf.definicion.IPersonaDao;
import com.german.jsf.entidad.Persona;

@Repository
public class PersonaDaoImp implements IPersonaDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session sf() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona obtenerPorId(Long id) {
		return (Persona) sf().get(Persona.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Persona> obtenerTodos() {
		return sf().createQuery("From Persona").list();
	}

	@Override
	@Transactional
	public void insertar(Persona entidad) throws SQLException {
		sf().saveOrUpdate(entidad);
	}

	@Override
	@Transactional
	public Persona actualizar(Persona entidad) throws SQLException {
		sf().update(entidad);
		return entidad;
	}

	@Override
	@Transactional
	public void eliminar(Long id) throws SQLException {
		Persona persona = (Persona) sf().load(Persona.class, id);
		if (persona != null) {
			sf().delete(persona);
		}
	}

}
