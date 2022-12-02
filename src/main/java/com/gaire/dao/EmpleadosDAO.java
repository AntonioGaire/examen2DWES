package com.gaire.dao;

import java.util.Optional;

import com.gaire.model.Empleado;

public interface EmpleadosDAO {

	public Optional<Empleado> find(int id);
	
	public void update(Empleado empleado);
}
