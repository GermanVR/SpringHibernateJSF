package com.german.jsf.implementacion;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.german.jsf.definicion.IDireccionDao;
import com.german.jsf.entidad.Direccion;

@Repository
public class DireccionDaoImp implements IDireccionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true)
	public Direccion obtenerPorId(Serializable id) {
		return (Direccion) sessionFactory.getCurrentSession().get(Direccion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Direccion> obtenerTodos() {
		return sessionFactory.getCurrentSession().createQuery("From Direccion").list();
	}

	@Override
	@Transactional
	public void insertar(Direccion entidad) throws SQLException {
		sessionFactory.getCurrentSession().save(entidad);
	}

	@Override
	@Transactional
	public Direccion actualizar(Direccion entidad) throws SQLException {
		sessionFactory.getCurrentSession().update(entidad);
		return entidad;
	}

	@Override
	@Transactional
	public void eliminar(Serializable id) throws SQLException {
		Direccion d = (Direccion) sessionFactory.getCurrentSession().get(Direccion.class, id);
		if (d != null) {
			sessionFactory.getCurrentSession().delete(d);
		}
	}

}
