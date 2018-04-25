package com.german.jsf.service.implementacion;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.german.jsf.definicion.IDireccionDao;
import com.german.jsf.entidad.Direccion;
import com.german.jsf.service.IDireccionService;

@Service
public class SDireccionImp implements IDireccionService {

	@Autowired
	private IDireccionDao iDireccionDao;

	@Override
	public Direccion obtenerPorId(Long id) {
		return iDireccionDao.obtenerPorId(id);
	}

	@Override
	public List<Direccion> obtenerTodos() {
		return iDireccionDao.obtenerTodos();
	}

	@Override
	public void insertar(Direccion entidad) throws SQLException {
		iDireccionDao.insertar(entidad);
	}

	@Override
	public Direccion actualizar(Direccion entidad) throws SQLException {
		iDireccionDao.actualizar(entidad);
		return entidad;
	}

	@Override
	public void eliminar(Long id) throws SQLException {
		iDireccionDao.eliminar(id);
	}

}
