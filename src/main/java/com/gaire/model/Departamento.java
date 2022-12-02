package com.gaire.model;

import java.util.Objects;

public class Departamento {
	
	private int id;
	private String nombre;
	private double presupuesto;
	private double gastos;
	
	private int numeroempleados;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}
	public double getGastos() {
		return gastos;
	}
	public void setGastos(double gastos) {
		this.gastos = gastos;
	}
	public int getNumeroempleados() {
		return numeroempleados;
	}
	public void setNumeroempleados(int numeroempleados) {
		this.numeroempleados = numeroempleados;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gastos, id, nombre, numeroempleados, presupuesto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Double.doubleToLongBits(gastos) == Double.doubleToLongBits(other.gastos) && id == other.id
				&& Objects.equals(nombre, other.nombre) && numeroempleados == other.numeroempleados
				&& Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto);
	}
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", gastos=" + gastos
				+ ", numeroempleados=" + numeroempleados + "]";
	}

}
