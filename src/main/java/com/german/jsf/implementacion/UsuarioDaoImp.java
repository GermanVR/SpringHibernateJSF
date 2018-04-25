package com.german.jsf.implementacion;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.german.jsf.definicion.IUsuarioDao;
import com.german.jsf.entidad.Usuario;

@Repository
public class UsuarioDaoImp implements IUsuarioDao {

	@Autowired
	private SessionFactory sessinFactory;

	public Session sf() {
		return sessinFactory.getCurrentSession();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario obtenerPorId(Long id) {
		return (Usuario) sf().get(Usuario.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuario> obtenerTodos() {
		return sf().createQuery("From Usuario Where status Not In ('I')").list();
	}

	@Override
	@Transactional
	public void insertar(Usuario entidad) throws SQLException {
		sf().save(entidad);
	}

	@Override
	@Transactional
	public Usuario actualizar(Usuario entidad) throws SQLException {
		Usuario u = (Usuario) sf().get(Usuario.class, entidad.getId());
		if (u != null) {
			sf().update(u);
		}
		return u;
	}

	@Override
	@Transactional
	public void eliminar(Long id) throws SQLException {
		Usuario u = (Usuario) sf().get(Usuario.class, id);
		if (u != null) {
			sf().delete(u);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario validaUsuario(Usuario u) {
		@SuppressWarnings("unchecked")
		List<Usuario> lu = sf().createQuery("From Usuario where user = :user and password = :password and status = :status ").
				setString("user", u.getUser()).
				setString("password", u.getPassword()).
				setString("status", "A").
				list();
		if (lu.size()>0) {
			return lu.get(0);
		}
		
		return null;
	}
}
