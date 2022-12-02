<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.gaire.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Departamentos</title>

<style>
	.clearfix::after {
		content: "";
		display: block;
		clear: both;
	}
</style>

</head>
<body>

	<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Departamentos</h1>
			</div>
			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
				
				<div style="position: absolute; left: 39%; top : 39%;">
					
						<form action="/web_empleados/departamentos/crear">
							<input type="submit" value="Crear">
						</form>
				</div>
				
			</div>
		</div>
		
		<div class="clearfix">
			<hr/>
		</div>
		
		<div class="clearfix">
			<div style="float: left;width: 10%">Id</div>
			<div style="float: left;width: 25%">Nombre</div>
			<div style="float: left;width: 20%">Presupuesto</div>
			<div style="float: left;width: 20%">Gastos</div>
			<div style="float: left;width: 10%">Numero de empleados</div>
			<div style="float: none;width: auto;overflow: hidden;">Acción</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
	<% 
	       if (request.getAttribute("listaDepartamentos") != null) {
	           List<Departamento> listaDepartamentos = (List<Departamento>)request.getAttribute("listaDepartamentos");
	           
	           for (Departamento departamento : listaDepartamentos) {
	   %>
	
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 10%"><%= departamento.getId()%></div>
			<div style="float: left;width: 25%"><%= departamento.getNombre()%></div>
			<div style="float: left;width: 20%"><%= departamento.getPresupuesto()%></div>
			<div style="float: left;width: 20%"><%= departamento.getGastos()%></div>
			<div style="float: left;width: 10%"><%= departamento.getNumeroempleados()%></div>
			<div style="float: none;width: auto;overflow: hidden;">
<%-- 				<form action="/web_empleados/departamentos/<%= departamento.getId()%>" style="display: inline;"> --%>
					<form action="/web_empleados/departamentos/" style="display: inline;">
	   				<input type="submit" value="Ver Detalle" />
				</form>
<%-- 				<form action="/web_empleados/departamentos/editar/<%= departamento.getId()%>" class="adminbtn"> --%>
					<form action="/web_empleados/departamentos/" style="display: inline;">
	   				<input type="submit" value="Editar" />
				</form>
<!-- 				<form action="/web_empleados/departamentos/borrar/" method="post" class="adminbtn""> -->
					<form action="/web_empleados/departamentos/" style="display: inline;">
					<input type="hidden" name="__method__" value="delete"/>
					<input type="hidden" name="id" value="<%= departamento.getId()%>"/>
	   				<input type="submit" value="Eliminar" />
				</form>
			</div>
		</div>
	
	<% 
	           }
	       } else { 
	   %>
		No hay registros de fabricante
	<% } %>
	</div>

</body>
</html>