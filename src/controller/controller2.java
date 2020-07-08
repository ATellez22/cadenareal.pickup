package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dao_pedidos;
import dto.dto_articulo;
import dto.dto_pedido;

@WebServlet("/controller2")
public class controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public controller2() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String seleccion_articulo = request.getParameter("seleccion_articulo");

		System.out.println("SELECCION ARTICULO: " + seleccion_articulo);

		String[] parts = seleccion_articulo.split(",");
		String codigo_barra2 = parts[0];
		String codigo_barra1 = parts[1];
		String descripcion1 = parts[2];
		String cantidad1 = parts[3];

		System.out.println("CODIGO_BARRA2:" + codigo_barra2);

		RequestDispatcher rd;

		request.setAttribute("codigo_barra", codigo_barra1);
		request.setAttribute("descripcion", descripcion1);
		request.setAttribute("cantidad", cantidad1);
		request.setAttribute("codigo_barra2", codigo_barra2);

		dao_pedidos metodo = new dao_pedidos();

		String descripcion2 = "";

		try {
			descripcion2 = metodo.Obtener_descripcion_articulo((codigo_barra2));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("DESCRIPCION OBTENIDA: " + descripcion2);

		request.setAttribute("descripcion2", descripcion2);

		rd = request.getRequestDispatcher("/control_items.jsp"); // REDIRECCION
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
