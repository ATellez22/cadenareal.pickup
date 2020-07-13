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

import dao.dao_login;
import dao.dao_pedidos;
import dto.dto_login;
import dto.dto_pedido;
import dto.dto_pedido_detalle;

@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String usuario_conectado = "";

	dto_login login = new dto_login();
	dto_pedido ped = new dto_pedido();
	dao_pedidos metodo = new dao_pedidos();

	public controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String seleccion_pedido = request.getParameter("seleccion_pedido");
		
		
		while(seleccion_pedido.startsWith("lista_pedidos_picker")) {
			
			
			System.out.println("SELECCION PEDIDO: "+seleccion_pedido);
			
		}

//		try { 
//
//			Long.valueOf(seleccion_pedido); // CONVERTIR A LONG. SI SE PUEDE, CONTINUA EL PROCESO. SI NO, PASA AL CATCH
//											// COMO STRING. SI SE PUEDE, ENTONCES EL VALOR VIENE DE lista_pedidos_picker.jsp
//
//			RequestDispatcher rd;
//
//			request.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO
//
//			// LISTA DE ITEMS POR PEDIDO
//
//			List<dto_pedido_detalle> lista = new ArrayList<>();
//
//			System.out.println(seleccion_pedido);
//
//			ped.setNum_pedido(seleccion_pedido);
//
//			try {
//
//				lista = metodo.Mostrar_detalle_pedido_picker(ped.getNum_pedido());
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
//
//				rd = request.getRequestDispatcher("/lista_items.jsp");
//				rd.forward(request, response);
//
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//
//		} catch (NumberFormatException e1) { // NO SE PUEDE CONVERTIR
//
//			if (seleccion_pedido.equals("cancelar_lista")) { // AL RETROCEDER DE LA LISTA DE ITEMS
//
//				request.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO
//
//				// LISTA DE ITEMS POR PEDIDO
//
//				RequestDispatcher rd;
//
//				List<dto_pedido> lista = new ArrayList<>();
//
//				try {
//
//					lista = metodo.Mostrar_pedido_picker(login.getUsuario());
//
//					System.out.println(ped.getNum_pedido());
//
//					for (dto_pedido pedido : lista) {
//
//						System.out.println(lista);
//
//					}
//
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				request.setAttribute("lista", lista);
//
//				// LISTA DE ITEMS POR PEDIDO
//
//				rd = request.getRequestDispatcher("/lista_pedidos_picker.jsp");
//				rd.forward(request, response);
//
//			} else  { // SELECCION DE FILA - ENVIO DE DATO - DESDE lista_items.jsp HACIA control_items.jsp
//
//				System.out.println("SELECCION PEDIDO: " + seleccion_pedido);
//
//				String[] parts = seleccion_pedido.split(", ");
//				String codigo_barra = parts[0];
//				String descripcion = parts[1];
//				String cantidad = parts[2];
//				String num_pedido = parts[4];
//								
//				RequestDispatcher rd;
//				
//				request.setAttribute("codigo_barra", codigo_barra);
//				request.setAttribute("descripcion", descripcion);
//				request.setAttribute("cantidad", cantidad);
//				request.setAttribute("num_pedido", num_pedido);
//				
//				rd = request.getRequestDispatcher("/control_items.jsp"); // REDIRECCION
//				rd.forward(request, response);
//
//			}
//
//		}		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String seleccion = request.getParameter("seleccion");

		if (seleccion.equals("ingresar")) {

			login = new dto_login();

			login.setUsuario(request.getParameter("txt_usuario"));
			login.setPassword(request.getParameter("txt_pass"));

			dao_login metodo = new dao_login();

			try {

				int bandera = metodo.control_usuario(login.getUsuario(), login.getPassword());

				RequestDispatcher rd;

				request.setAttribute("bandera", bandera); // ENVIO DEL VALOR DE LA BANDERA

				if (bandera == 2) {

					request.setAttribute("usuario", login.getUsuario()); // ENVIO DEL USUARIO CONECTADO

					// LISTA DE PEDIDOS ASIGNADOS

					dao_pedidos evento = new dao_pedidos();
					List<dto_pedido> lista = new ArrayList<>();

					try {

						lista = evento.Mostrar_pedido_picker(login.getUsuario().toString());

						for (dto_pedido pedido : lista) {
							System.out.println(pedido);
						}

					} catch (Exception e) {

						e.printStackTrace();

					}

					request.setAttribute("lista", lista);

					// LISTA DE PEDIDOS ASIGNADOS

					rd = request.getRequestDispatcher("/lista_pedidos_picker.jsp"); // REDIRECCIONA LA PÁGINA DEL PICKER
					rd.forward(request, response);

				} else {

					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}


		} else  if (seleccion.equals("controlar")) {
			
			//
			
		}
		
	}

}
