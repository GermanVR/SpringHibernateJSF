package com.german.jsf.entidad;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T, ID extends Serializable> {

	public T obtenerPorId(ID id);

	public List<T> obtenerTodos();

	public void insertar(T entidad) throws SQLException;

	public T actualizar(T entidad) throws SQLException;

	public void eliminar(ID id) throws SQLException;

}
