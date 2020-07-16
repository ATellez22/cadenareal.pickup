package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dao_login;
import dao.dao_pedidos;
import dto.dto_login;
import dto.dto_pedido;
import dto.dto_pedido_detalle;
import dto.dto_variables;

@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String usuario_conectado = "";

	public static dto_login login = new dto_login();
	dto_pedido ped = new dto_pedido();

	public static String sql;

	public controller() {
		super();

	}

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		int con = 0;

		String seleccion = request.getParameter("seleccion");

		String[] parts = seleccion.split(",");

		if (seleccion.startsWith("lista_pedidos_picker_entrada")) { // SI COMIENZA CON 'lista_pedidos_picker', ENTONCES
																	// VIENE DE ESA PAGINA

			dao_pedidos metodo = new dao_pedidos();

			HttpSession sesion = (HttpSession) request.getSession();

			sesion.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO

			con = Integer.valueOf(parts[1]);

			if (con == 1) {

				String num_ped = parts[2].trim();

				// LISTA DE ITEMS POR PEDIDO

				List<dto_pedido_detalle> lista = new ArrayList<>();

				ped.setNum_pedido(num_ped);

				try {

					lista = dao_pedidos.Mostrar_detalle_pedido_picker(ped.getNum_pedido(), "", "", "", "", "sql", 1);

					sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs"
							+ "  FROM detalle_pedidos" + " WHERE num_pedido = '" + num_ped + "'";

					login.setSql(sql); // ALMACENAR LA CONSULTA PARA REUTILIZARLO EN EL CONTROLLER

					request.setAttribute("sql", sql); // EL SQL que permitirá retroceder sin problema a lista_items

					for (dto_pedido_detalle pedido_detalle : lista) {
					}

					request.setAttribute("lista", lista);

					// LISTA DE ITEMS POR PEDIDO

					RequestDispatcher rd;

					rd = request.getRequestDispatcher("/lista_items.jsp");
					rd.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}

				con = 0;

			} else if (con == 2) {

				String num_ped = parts[2].trim();
				String num_ped2 = parts[3].trim();

				RequestDispatcher rd;

				// LISTA DE ITEMS POR PEDIDO

				List<dto_pedido_detalle> lista = new ArrayList<>();

				try {

					lista = dao_pedidos.Mostrar_detalle_pedido_picker(num_ped, num_ped2, "", "", "", "sql", 2);

					sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs"
							+ "  FROM detalle_pedidos" + " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '"
							+ num_ped2 + "'";

					login.setSql(sql); // ALMACENAR LA CONSULTA PARA REUTILIZARLO EN EL CONTROLLER

					request.setAttribute("sql", sql); // EL SQL que permitirá retroceder sin problema a lista_items

					for (dto_pedido_detalle pedido_detalle : lista) {
					}

					request.setAttribute("lista", lista);

					// LISTA DE ITEMS POR PEDIDO

					rd = request.getRequestDispatcher("/lista_items.jsp");
					rd.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}

				con = 0;

			} else if (con == 3) {

				String num_ped = parts[2].trim();
				String num_ped2 = parts[3].trim();
				String num_ped3 = parts[4].trim();

				RequestDispatcher rd;

				// LISTA DE ITEMS POR PEDIDO

				List<dto_pedido_detalle> lista = new ArrayList<>();

				ped.setNum_pedido(num_ped);

				try {

					lista = dao_pedidos.Mostrar_detalle_pedido_picker(num_ped, num_ped2, num_ped3, "", "", "sql", 3);

					sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs"
							+ "  FROM detalle_pedidos" + " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '"
							+ num_ped2 + "' OR num_pedido = '" + num_ped3 + "'";

					request.setAttribute("sql", sql); // EL SQL que permitirá retroceder sin problema a lista_items

					for (dto_pedido_detalle pedido_detalle : lista) {
					}

					request.setAttribute("lista", lista);

					// LISTA DE ITEMS POR PEDIDO

					rd = request.getRequestDispatcher("/lista_items.jsp");
					rd.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}

				con = 0;

			} else if (con == 4) {

				String num_ped = parts[2].trim();
				String num_ped2 = parts[3].trim();
				String num_ped3 = parts[4].trim();
				String num_ped4 = parts[5].trim();

				RequestDispatcher rd;

				// LISTA DE ITEMS POR PEDIDO

				List<dto_pedido_detalle> lista = new ArrayList<>();

				ped.setNum_pedido(num_ped);

				try {

					lista = dao_pedidos.Mostrar_detalle_pedido_picker(num_ped, num_ped2, num_ped3, num_ped4, "", "sql",
							4);

					sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs"
							+ "  FROM detalle_pedidos" + " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '"
							+ num_ped2 + "' OR num_pedido = '" + num_ped3 + "' OR num_pedido = '" + num_ped4 + "'";

					request.setAttribute("sql", sql); // EL SQL que permitirá retroceder sin problema a lista_items

					for (dto_pedido_detalle pedido_detalle : lista) {
					}

					request.setAttribute("lista", lista);

					// LISTA DE ITEMS POR PEDIDO

					rd = request.getRequestDispatcher("/lista_items.jsp");
					rd.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}

				con = 0;

			} else if (con == 5) {

				String num_ped = parts[2].trim();
				String num_ped2 = parts[3].trim();
				String num_ped3 = parts[4].trim();
				String num_ped4 = parts[5].trim();
				String num_ped5 = parts[6].trim();

				RequestDispatcher rd;

				// LISTA DE ITEMS POR PEDIDO

				List<dto_pedido_detalle> lista = new ArrayList<>();

				ped.setNum_pedido(num_ped);

				try {

					lista = metodo.Mostrar_detalle_pedido_picker(num_ped, num_ped2, num_ped3, num_ped4, num_ped5, "sql",
							5);

					for (dto_pedido_detalle pedido_detalle : lista) {
					}

					sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs"
							+ "  FROM detalle_pedidos" + " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '"
							+ num_ped2 + "' OR num_pedido = '" + num_ped3 + "' OR num_pedido = '" + num_ped4
							+ "' OR num_pedido = '" + num_ped5 + "'";

					login.setSql(sql); // ALMACENAR LA CONSULTA PARA REUTILIZARLO EN EL CONTROLLER

					request.setAttribute("sql", sql); // EL SQL que permitirá retroceder sin problema a lista_items

					request.setAttribute("lista", lista);

					// LISTA DE ITEMS POR PEDIDO

					rd = request.getRequestDispatcher("/lista_items.jsp");
					rd.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}

				con = 0;

			}

		} else if (seleccion.equals("lista_items_salida")) { // AL RETROCEDER DE LA lista_items.jsp

			HttpSession sesion = (HttpSession) request.getSession();

			sesion.setAttribute("usuario", controller.login.getUsuario()); // ENVIO DEL USUARIO CONECTADO

			// LISTA DE ITEMS POR PEDIDO

			RequestDispatcher rd;

			List<dto_pedido> lista = new ArrayList<>();

			try {

				lista = dao_pedidos.Mostrar_pedido_picker(login.getUsuario());

				System.out.println("USUARIO: " + login.getUsuario());

				for (dto_pedido pedido : lista) {
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("lista", lista);

			// LISTA DE ITEMS POR PEDIDO

			rd = request.getRequestDispatcher("/lista_pedidos_picker.jsp");
			rd.forward(request, response);

		} else if (seleccion.startsWith("lista_items_entrada")) { // SELECCION DE FILA - ENVIO DE DATO DESDE
																	// lista_items.jsp HACIA control_items.jsp

			String seleccion_dividida = seleccion.replace("lista_items_entrada", "");

			String[] parts2 = seleccion_dividida.split(", ");
			String codigo_barra = parts2[0];
			String descripcion = parts2[1];
			String cantidad = parts2[2];
			String num_pedido = parts2[4];

			RequestDispatcher rd;

			request.setAttribute("codigo_barra", codigo_barra);
			request.setAttribute("descripcion", descripcion);
			request.setAttribute("cantidad", cantidad);
			request.setAttribute("num_pedido", num_pedido);

			rd = request.getRequestDispatcher("/control_items.jsp");
			rd.forward(request, response);

		} else if (seleccion.startsWith("control_items_busqueda")) {

			String seleccion_dividida = seleccion.replace("control_items_busqueda", "");

			String[] parts2 = seleccion_dividida.split(",");
			String codigo_barra2 = parts2[0];
			String codigo_barra1 = parts2[1];
			String descripcion1 = parts2[2];
			String cantidad1 = parts2[3];
			String num_pedido = parts2[4];

			dao_pedidos metodo = new dao_pedidos();

			String descripcion2 = "";

			try {
				descripcion2 = metodo.Obtener_descripcion_articulo((codigo_barra2));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("descripcion2", descripcion2);
			request.setAttribute("codigo_barra", codigo_barra1);
			request.setAttribute("descripcion", descripcion1);
			request.setAttribute("cantidad", cantidad1);
			request.setAttribute("codigo_barra2", codigo_barra2);
			request.setAttribute("num_pedido", num_pedido);

			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/control_items.jsp"); // REDIRECCION
			rd.forward(request, response);

		}

	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String seleccion = request.getParameter("seleccion");

		if (seleccion.equals("login_entrada")) {

			HttpSession sesion = request.getSession(true);

			login.setUsuario(request.getParameter("txt_usuario"));
			login.setPassword(request.getParameter("txt_pass"));

			login = new dto_login(login.getUsuario(), login.getPassword());

			request.getSession().setAttribute("usuario", login.getUsuario());

			// sesion.setAttribute("dto_login", login); // ENVIO DEL VALOR DE LA BANDERA

			dao_login metodo = new dao_login();

			try {

				String bandera = metodo.control_usuario(login.getUsuario(), login.getPassword());

				RequestDispatcher rd;

				sesion.setAttribute("bandera", bandera); // ENVIO DEL VALOR DE LA BANDERA

				if (bandera == "2") {

					sesion.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO

					// LISTA DE PEDIDOS ASIGNADOS

					dao_pedidos evento = new dao_pedidos();
					List<dto_pedido> lista = new ArrayList<>();

					try {

						lista = evento.Mostrar_pedido_picker(login.getUsuario().toString());

						for (dto_pedido pedido : lista) {
						}

					} catch (Exception e) {

						e.printStackTrace();

					}

					sesion.setAttribute("lista", lista);

					// LISTA DE PEDIDOS ASIGNADOS

					rd = request.getRequestDispatcher("/lista_pedidos_picker.jsp"); // REDIRECCIONA LA PÁGINA DEL PICKER
					rd.forward(request, response);

				} else { // RETROCESO

					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		} else if (seleccion.equals("control_items_control_cantidad_actualizar")) {

			String cod2 = "", can2 = "";

			String bandera = "0";

			can2 = request.getParameter("txt_can2").toString();

			try { // CONVERTIR A DOUBLE

				Double can2_int = Double.valueOf(can2);

				bandera = "1";

			} catch (NumberFormatException e) { // SI NO PUEDE CONVERTIR, CANTIDAD ES STRING. REGRESA A
												// control_items.jsp para
												// lanzar un ALERT.

				RequestDispatcher rd;

				request.setAttribute("bandera", bandera);

				request.setAttribute("codigo_barra", request.getParameter("txt_cod1").toString());
				request.setAttribute("descripcion", request.getParameter("txt_des1").toString());
				request.setAttribute("cantidad", request.getParameter("txt_can1").toString());
				request.setAttribute("codigo_barra2", request.getParameter("txt_cod2").toString());
				request.setAttribute("descripcion2", request.getParameter("txt_des2").toString());
				request.setAttribute("cantidad2", request.getParameter("txt_can2").toString());
				request.setAttribute("num_pedido", request.getParameter("num_pedido").toString());

				rd = request.getRequestDispatcher("/control_items.jsp"); // REDIRECCION
				rd.forward(request, response);

			}

			if (bandera == "1") { // LA CANTIDAD ES CORRECTA... AHORA SE PUEDE ACTUALIZAR EL ITEM

				dto_pedido ped = new dto_pedido();

				int flag = 0;

				String num_ped = request.getParameter("num_pedido").toString();
				String cod1 = request.getParameter("txt_cod1").toString();
				cod2 = request.getParameter("txt_cod2").toString();
				String des2 = request.getParameter("txt_des2").toString();

				String cat2 = "";
				try {
					cat2 = dao_pedidos.Obtener_categoria_articulo(cod2);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (cod2.equals(cod1)) {

					flag = 1; // CODIGOS IGUALES - CONFIRMACION

					try {
						dao_pedidos.actualizar_item(flag, num_ped, cod1, "", "", "", "");
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {

					flag = 2; // CODIGOS DIFERENTES - SUSTITUCION

					try {
						dao_pedidos.actualizar_item(flag, num_ped, cod1, cod2, des2, can2, cat2);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				
				RequestDispatcher rd;

				rd = request.getRequestDispatcher("/lista_items.jsp"); // REDIRECCION
				rd.forward(request, response);

			}
			

		} else {

			HttpSession sesion = (HttpSession) request.getSession();

			sesion.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO

			sesion.setAttribute("variable", login.getSql()); // ENVIO DEL USUARIO CONECTADO

			System.out.println("USUARIO:" + login.getUsuario());
			System.out.println("VARIABLE:" + login.getSql());

			List<dto_pedido_detalle> lista = new ArrayList<>();

			try {
				lista = dao_pedidos.Mostrar_detalle_pedido_picker("", "", "", "", "", login.getSql(), 0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(lista);

			request.setAttribute("lista", lista);

			RequestDispatcher rd;

			rd = request.getRequestDispatcher("/lista_items.jsp");
			rd.forward(request, response);

		}

	}

}
