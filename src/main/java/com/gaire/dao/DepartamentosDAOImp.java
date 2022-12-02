package com.gaire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gaire.model.*;

public class DepartamentosDAOImp extends AbstractDAOImpl implements DepartamentosDAO{

	@Override
	public List<Departamento> listAll() {
		
		Connection conn = null;
		Statement s = null;
        ResultSet rs = null;
        
        List<Departamento> listDep = new ArrayList<Departamento>();
        
        try {
        	conn = connectDB();

        	s = conn.createStatement();
            		
        	rs = s.executeQuery("select d.id, d.nombre, d.presupuesto, d.gastos, count(e.id) as nempleados from departamento as d left join empleado as e on e.id_departamento = d.id group by d.id");      
        	
            while (rs.next()) {
            	Departamento dep = new Departamento();
            	
            	dep.setId(rs.getInt("id"));
            	dep.setNombre(rs.getString("nombre"));
            	dep.setPresupuesto(rs.getDouble("presupuesto"));
            	dep.setGastos(rs.getDouble("gastos"));
            	dep.setNumeroempleados(rs.getInt("nempleados"));
            	
            	listDep.add(dep);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, s, rs);
        }
        
        return listDep;
	}

	@Override
	public Departamento getDepartamento(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Departamento departamento) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
        	conn = connectDB();

        	ps = conn.prepareStatement("insert into departamento (nombre, presupuesto, gastos) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            int idx = 1;
            ps.setString(idx++, departamento.getNombre());
            ps.setDouble(idx++, departamento.getPresupuesto());
            ps.setDouble(idx, departamento.getGastos());
            
            int rows = ps.executeUpdate();
            
            if (rows == 0) 
            	System.out.println("INSERT de fabricante con 0 filas insertadas.");
            
            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
            	departamento.setId(rsGenKeys.getInt(1));
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
	}
	
	@Override
	public List<Departamento> listByPresupuestoActual(double presupuestomin, double presupuestomax) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Departamento> listDep = new ArrayList<Departamento>();
        
        try {
        	conn = connectDB();

        	ps = conn.prepareStatement("select d.id, d.nombre, d.presupuesto, d.gastos, count(e.id) as nempleados from departamento as d left join empleado as e "
        			+ "	on e.id_departamento = d.id "
        			+ "    where ((d.presupuesto-d.gastos) >= ? and (d.presupuesto-d.gastos) <= ?) "
        			+ "    group by d.id;");
            		
        	int idx = 1;
        	ps.setDouble(idx, presupuestomin);
        	ps.setDouble(idx, presupuestomax);
        	
        	rs = ps.executeQuery();
        	
            while (rs.next()) {
            	Departamento dep = new Departamento();
            	
            	dep.setId(rs.getInt("id"));
            	dep.setNombre(rs.getString("nombre"));
            	dep.setPresupuesto(rs.getDouble("presupuesto"));
            	dep.setGastos(rs.getDouble("gastos"));
            	dep.setNumeroempleados(rs.getInt("nempleados"));
            	
            	listDep.add(dep);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
        
        return listDep;
	}

}
