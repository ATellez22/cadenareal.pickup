package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import dto.dto_articulo;
import dto.dto_login;
import dto.dto_pedido;
import dto.dto_pedido_detalle;
import dto.dto_pedido_detalle2;
import dto.dto_variables;

public class dao_pedidos {

	private static Connection conexion;
	private static PreparedStatement sentencia, sentencia2, sentencia3;
	
	public static dto_login login = new dto_login();
			
	public static void borrar_detalle_pedido(String num_pedido) throws SQLException { // BORRADO TRAS DARLE GUARDAR

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			sql = "DELETE FROM detalle_pedido WHERE num_pedido = " + num_pedido;

			sentencia = conexion.prepareStatement(sql);

			sentencia.executeUpdate(sql);

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (Exception ex) {

			conexion.rollback();
			ex.printStackTrace();

		}

	}

	public void guardar_detalle_pedidos(dto_pedido_detalle detalle) throws SQLException { // INSERCIÓN DE NUEVOS VALORES

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			sql = "INSERT INTO detalle_pedidos(num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
					+ "    VALUES (?, ?, ?, ?, ?, ?)";

			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, detalle.getNum_pedido());
			sentencia.setLong(2, detalle.getCodigo_barra());
			sentencia.setString(3, detalle.getDescripcion());
			sentencia.setDouble(4, detalle.getCantidad());
			sentencia.setString(5, detalle.getObs());

			sentencia.executeUpdate(sql);

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			conexion.rollback();
			e.printStackTrace();

		}

	}

	public List<dto_pedido> Mostrar_pedido_supervisor() throws SQLException { // MOSTRAR EN PC

		ResultSet resultSet = null;

		List<dto_pedido> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			sql = "SELECT num_pedido, supervisor, picker, fecha, sucursal, estado" + "  FROM pedidos";

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				dto_pedido ped = new dto_pedido();

				ped.setNum_pedido(resultSet.getString(1));
				ped.setSupervisor((resultSet.getString(2)));
				ped.setPicker((resultSet.getString(3)));
				ped.setFecha(resultSet.getString(4));
				ped.setSucursal(resultSet.getString(5));
				ped.setEstado(resultSet.getString(6));

				listaProductos.add(ped);

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public List<dto_pedido_detalle> Mostrar_detalle_supervisor() throws SQLException { // MOSTRAR EN PC

		ResultSet resultSet = null;

		List<dto_pedido_detalle> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos";

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				dto_pedido_detalle det = new dto_pedido_detalle();

				det.setNum_pedido(resultSet.getString(1));
				det.setCodigo_barra(resultSet.getLong(2));
				det.setDescripcion(resultSet.getString(3));
				det.setCantidad(resultSet.getDouble(4));
				det.setSeccion(resultSet.getString(5));
				det.setObs(resultSet.getString(6));

				listaProductos.add(det);

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public static List<dto_pedido> Mostrar_pedido_picker(String picker) throws SQLException { // MOSTRAR EN COLECTOR

		ResultSet resultSet = null;

		List<dto_pedido> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			sql = "SELECT num_pedido, supervisor, picker, fecha, sucursal, estado" + "  FROM pedidos"
					+ " WHERE picker = '" + picker + "'";

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				dto_pedido ped = new dto_pedido();

				ped.setNum_pedido(resultSet.getString(1));
				ped.setEncargado(resultSet.getString(2));
				ped.setPicker(resultSet.getString(3));
				ped.setFecha(resultSet.getString(4));
				ped.setSucursal(resultSet.getString(5));
				ped.setEstado(resultSet.getString(6));

				listaProductos.add(ped);

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}
	

	public static List<dto_pedido_detalle> Mostrar_detalle_pedido_picker(String num_ped, String num_ped2, String num_ped3,
			String num_ped4, String num_ped5, String num_ped_consulta, int contador) throws SQLException {

		ResultSet resultSet = null;

		List<dto_pedido_detalle> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		System.out.println("CONTADOR: " + contador);
		
		try {

			conexion.setAutoCommit(false);			
			
			if (contador == 0) {
				
				sql = num_ped_consulta;				

			} else if (contador == 1) {

				sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
						+ " WHERE num_pedido = '" + num_ped + "'";

			} else if (contador == 2) {

				sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
						+ " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '" + num_ped2 + "'";

			} else if (contador == 3) {

				sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
						+ " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '" + num_ped2 + "' OR num_pedido = '"
						+ num_ped3 + "'";

			} else if (contador == 4) {

				sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
						+ " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '" + num_ped2 + "' OR num_pedido = '"
						+ num_ped3 + "' OR num_pedido = '" + num_ped4 + "'";

			} else if (contador == 5) {

				sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
						+ " WHERE num_pedido = '" + num_ped + "' OR num_pedido = '" + num_ped2 + "' OR num_pedido = '"
						+ num_ped3 + "' OR num_pedido = '" + num_ped4 + "' OR num_pedido = '" + num_ped5 + "'";

			}

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				dto_pedido_detalle det = new dto_pedido_detalle();

				det.setNum_pedido(resultSet.getString(1));
				det.setCodigo_barra(resultSet.getLong(2));
				det.setDescripcion(resultSet.getString(3));
				det.setCantidad(resultSet.getDouble(4));
				det.setSeccion(resultSet.getString(5));
				det.setObs(resultSet.getString(6));

				listaProductos.add(det);

			}

			System.out.println(sql);

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	
	/*
	 * MÉTODO QUE EL DEVUELVE LA DESCRIPCION SOLICITADA EN control_items.jsp A
	 * TRAVES DEL ESCANER O TECLADO
	 */
	public String Obtener_descripcion_articulo(String codigo_barra) throws SQLException {

		ResultSet resultSet = null;

		String des = "";

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			String sql = "SELECT descripcion FROM articulos WHERE codigo_barra ='" + codigo_barra + "'";

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				des = resultSet.getString("descripcion");

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return des;

	}

	/*
	 * MÉTODO QUE EL DEVUELVE LA CATEGORIA DEL ARTICULO SOLICITADO EN EL CONTROLLER2
	 * PARA ACTUALIZAR LOS ITEMS
	 */
	public static String Obtener_categoria_articulo(String codigo_barra) throws SQLException {

		ResultSet resultSet = null;

		String cat = "";

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			String sql = "SELECT categoria FROM articulos WHERE codigo_barra ='" + codigo_barra + "'";

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				cat = resultSet.getString("descripcion");

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return cat;

	}

	// CONTROL TOTAL PARA ACTUALIZAR ITEMS Y GUARDAR

	@SuppressWarnings({ "unused" })
	public static void actualizar_item(int flag, String num_pedido, String codigo_barra, String codigo_barra2,
			String des, Double can, String cat) throws SQLException {

		ResultSet resultSet = null;

		conexion = obtenerConexion();

		conexion.setAutoCommit(false);

		// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

		if (flag == 1) {

			try {

				PreparedStatement sentencia = conexion.prepareStatement(
						"UPDATE detalle_pedidos SET obs = ? WHERE (num_pedido = ?) AND (codigo_barra = ?)");

				sentencia.setString(1, "CONFIRMADO");
				sentencia.setString(2, num_pedido);
				sentencia.setString(3, codigo_barra);

				sentencia.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} else if (flag == 2) {

			int bandera = 0;

			try {

				PreparedStatement sentencia = conexion
						.prepareStatement("UPDATE detalle_pedidos SET obs = 'SUSTITUIDO POR: " + codigo_barra2
								+ "' WHERE codigo_barra LIKE '" + codigo_barra + "' AND num_pedido LIKE '" + num_pedido
								+ "'");

				sentencia.executeUpdate();

				bandera = 1;

			} catch (SQLException e) {

				e.printStackTrace();

			}

			if (bandera == 1) {

				try {

					PreparedStatement sentencia2 = conexion.prepareStatement(
							"INSERT INTO detalle_pedidos(num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
									+ "  VALUES (?, ?, ?, ?, ?, ?)");

					sentencia2.setString(1, num_pedido);
					sentencia2.setString(2, codigo_barra2);
					sentencia2.setString(3, des);
					sentencia2.setDouble(4, Double.valueOf(can));
					sentencia2.setString(5, cat);
					sentencia2.setString(6, "SUSTITUTO DE: " + codigo_barra);

					sentencia2.executeUpdate();

				} catch (SQLException e) {

					e.printStackTrace();

				}

			}

		}

		// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<dto_pedido_detalle2> Mostrar_d_p_picker_sql2() throws SQLException {
		
		ResultSet resultSet = null;

		List<dto_pedido_detalle2> listaProductos2 = new ArrayList<>();			

		conexion = obtenerConexion();
		
		String sql = "";
	
		try {

			conexion.setAutoCommit(false);
			
			dto_variables var = new dto_variables();

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				dto_pedido_detalle2 det = new dto_pedido_detalle2();

				det.setNum_pedido(resultSet.getString(1));
				det.setCodigo_barra(resultSet.getLong(2));
				det.setDescripcion(resultSet.getString(3));
				det.setCantidad(resultSet.getDouble(4));
				det.setSeccion(resultSet.getString(5));
				det.setObs(resultSet.getString(6));

				listaProductos2.add(det);

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos2;
	}

	
	private static Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
	
	
}


