package com.gaire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.gaire.model.Empleado;

public class EmpleadosDAOImp  extends AbstractDAOImpl implements EmpleadosDAO{

	@Override
	public void update(Empleado empleado) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = connectDB();
        	
        	System.out.println();
        	
        	ps = conn.prepareStatement("UPDATE empleado set nif = ?, nombre = ?, apellido1 = ?, apellido2 = ?, id_departamento = ? where id = ?");
        	int idx = 1;
        	ps.setString(idx++, empleado.getNif());
        	ps.setString(idx++, empleado.getNombre());
        	ps.setString(idx++, empleado.getApellido1());
        	ps.setString(idx++, empleado.getApellido2());
        	ps.setInt(idx++, empleado.getId_departamento());
        	ps.setInt(idx, empleado.getId());
        	
        	int rows = ps.executeUpdate();
        	
        	if (rows == 0) 
        		System.out.println("Update de empleado con 0 registros actualizados.");
        	
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
		
	}

	@Override
	public Optional<Empleado> find(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = connectDB();
        	
        	ps = conn.prepareStatement("SELECT id, nombre, apellido1, apellido2, id_departamento FROM empleado WHERE id = ?");
        	
        	int idx =  1;
        	ps.setInt(idx, id);
        	
        	rs = ps.executeQuery();
        	
        	if (rs.next()) {
        		Empleado emp = new Empleado();
        		idx = 1;
        		emp.setId(id);
        		emp.setNif(rs.getString(idx++));
        		emp.setNombre(rs.getString(idx++));
        		emp.setApellido1(rs.getString(idx++));
        		emp.setApellido2(rs.getString(idx++));
        		emp.setId_departamento(rs.getInt(idx++));
        		
        		return Optional.of(emp);
        	}
        	
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
        
        return Optional.empty();
	}

}
