package com.gaire.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaire.dao.DepartamentosDAO;
import com.gaire.dao.DepartamentosDAOImp;
import com.gaire.dao.EmpleadosDAO;
import com.gaire.dao.EmpleadosDAOImp;
import com.gaire.model.Empleado;

/**
 * Servlet implementation class EmpleadosServlet
 */
@WebServlet("/empleados/*")
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadosServlet() {
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
			
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
		
		}else {
			
			pathInfo = pathInfo.replaceAll("/$", "");
			String[] pathParts = pathInfo.split("/");
			
			
			if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
				
				EmpleadosDAO empDAO = new EmpleadosDAOImp();
				
				try {
					request.setAttribute("empleado",empDAO.find(Integer.parseInt(pathParts[2])));
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editar-empleado.jsp");
					        								
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fabricantes.jsp");
				}
        												
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
	
		if (__method__ != null && "put".equalsIgnoreCase(__method__)) {			
			doPut(request, response);
		}
		response.sendRedirect("/web_empleados/departamentos");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmpleadosDAO empDAO = new EmpleadosDAOImp();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nif = request.getParameter("nif");
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		int Id_departamento = Integer.parseInt(request.getParameter("Id_departamento"));
		
		Empleado emp = new Empleado();
		
		try {
			
			emp.setId(id);
			emp.setNif(nif);
			emp.setNombre(nombre);
			emp.setApellido1(apellido1);
			emp.setApellido2(apellido2);
			emp.setId_departamento(Id_departamento);
			
			empDAO.update(emp);
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		
	}

}
