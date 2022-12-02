package com.gaire.model;

import java.util.Objects;

public class Empleado {
	private int id;
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int id_departamento;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public int getId_departamento() {
		return id_departamento;
	}
	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido1, apellido2, id, id_departamento, nif, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& id == other.id && id_departamento == other.id_departamento && Objects.equals(nif, other.nif)
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nif=" + nif + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", id_departamento=" + id_departamento + "]";
	}
	
	
}
