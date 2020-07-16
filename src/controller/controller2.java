package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import dao.dao_pedidos;
import dto.dto_articulo;
import dto.dto_login;
import dto.dto_pedido;
import dto.dto_pedido_detalle;
import dto.dto_variables;

@WebServlet("/controller2")
public class controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	dao_pedidos metodo = new dao_pedidos();

	public controller2() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String seleccion_articulo = request.getParameter("seleccion_articulo"); // DESDE LA FUNCIÓN "BUSCAR" de
																				// control_items.jsp

		String[] parts = seleccion_articulo.split(",");
		String codigo_barra2 = parts[0];
		String codigo_barra1 = parts[1];
		String descripcion1 = parts[2];
		String cantidad1 = parts[3];
		String num_pedido = parts[4];

		System.out.println("SELECCION_ARTICULO: " + seleccion_articulo);

		// VARIABLES QUE SERAN UTILIZADAS EN EL POST PARA ACTUALIZAR EL ESTADO DEL ITEM
//		dto_pedido_detalle det = new dto_pedido_detalle();		
//		det.setCodigo_barra(Long.parseLong(codigo_barra1));
//		det.setNum_pedido(num_pedido);
//		System.out.println("NUM_PEDIDO: "+num_pedido);
		// VARIABLES QUE SERAN UTILIZADAS EN EL POST PARA ACTUALIZAR EL ESTADO DEL ITEM

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

		String seleccion = request.getParameter("seleccion");

		if (seleccion.equals("controlar")) { // Desde control_items.jsp. RECIBIENDO LOS VALORES PARA VERIFICAR LA
												// CANTIDAD INGRESADA
			
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
					cat2 = metodo.Obtener_categoria_articulo(cod2);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (cod2.equals(cod1)) {

					flag = 1; // CODIGOS IGUALES - CONFIRMACION

					try {
						metodo.actualizar_item(flag, num_ped, cod1, "", "", "", "");
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {

					flag = 2; // CODIGOS DIFERENTES - SUSTITUCION

					try {
						metodo.actualizar_item(flag, num_ped, cod1, cod2, des2, can2, cat2);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			}

			RequestDispatcher rd;

			rd = request.getRequestDispatcher("/lista_items.jsp"); // REDIRECCION
			rd.forward(request, response);

		} else { // REGRESAR A lista_items.jsp

//			dao_pedidos metodo = new dao_pedidos();
//									

//

//			
//			List<dto_pedido_detalle> lista = new ArrayList<>();
//			
//				String seleccion_trim = seleccion.trim();
//		
//				try {
//					
//					lista = metodo.Mostrar_detalle_pedido_picker(seleccion_trim); //SELECCION = NUM_PEDIDO
//					
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//					
//				}
//
//				for (dto_pedido_detalle pedido_detalle : lista) {
//
//					System.out.println(pedido_detalle);
//
//				}
//
//				request.setAttribute("lista", lista);
//
//				// LISTA DE ITEMS POR PEDIDO

			// System.out.println("Usuario: "+ controller.login.getUsuario());

			// request.setAttribute("usuario", controller.login.getUsuario()); // ENVIO DEL
			// USUARIO CONECTADO
						
			
			
			//System.out.println("VARIABLE:" +controller.login.getSql());

			// LISTA DE ITEMS POR PEDIDO
//			
//			List<dto_pedido_detalle> lista = new ArrayList<>();
//			
//			try {
//				lista = metodo.Mostrar_d_p_picker_sql();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//			
//			for (dto_pedido_detalle pedido_detalle : lista) {}

			// LISTA DE ITEMS POR PEDIDO

			

		}

	}

}
