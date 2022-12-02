package com.gaire.servlet;


import com.gaire.dao.*;
import com.gaire.model.Departamento;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepartamentosServlet
 */
@WebServlet("/departamentos/*")
public class DepartamentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartamentosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		String pathInfo = request.getPathInfo(); 
		
		System.out.println(pathInfo);
					
		if (pathInfo == null || "/".equals(pathInfo)) {
			DepartamentosDAO depDAO = new DepartamentosDAOImp();
			
			request.setAttribute("listaDepartamentos", depDAO.listAll());		
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
		
		}else {
			
			pathInfo = pathInfo.replaceAll("/$", "");
			String[] pathParts = pathInfo.split("/");
			
			if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
												
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/crear-departamento.jsp");
        												
			}else {
			
				System.out.println("Opción POST no soportada.");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
		
			}
		}
		
		dispatcher.forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String __method__ = request.getParameter("__method__");
		
		if (__method__ == null) {
			// Crear uno nuevo
			DepartamentosDAO depDAO = new DepartamentosDAOImp();
			
			String nombre = request.getParameter("nombre");
			Double presupuesto = Double.parseDouble(request.getParameter("presupuesto"));
			Double gastos = Double.parseDouble(request.getParameter("gastos"));
			
			Departamento nuevodep = new Departamento();
			
			nuevodep.setNombre(nombre);
			nuevodep.setPresupuesto(presupuesto);
			nuevodep.setGastos(gastos);
			
			depDAO.create(nuevodep);			
		}
		
		response.sendRedirect("/web_empleados/departamentos");
			
	}

}
