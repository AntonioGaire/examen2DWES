package com.gaire.dao;
import com.gaire.model.*;
import java.util.List;

public interface DepartamentosDAO {
	
	public List<Departamento> listAll();
	
	public Departamento getDepartamento(int id);
	
	public void update(Departamento departamento);
	
	public void delete(int id);
	
	public void create(Departamento departamento);
	
	public List<Departamento> listByPresupuestoActual(double presupuestomin, double presupuestomax);

}
