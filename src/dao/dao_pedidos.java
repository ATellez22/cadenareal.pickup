package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import dto.dto_articulo;
import dto.dto_pedido;
import dto.dto_pedido_detalle;

public class dao_pedidos {

	private static Connection conexion;
	private static PreparedStatement sentencia;

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

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public List<dto_pedido> Mostrar_pedido_picker(String picker) throws SQLException { // MOSTRAR EN COLECTOR

		ResultSet resultSet = null;

		List<dto_pedido> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

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

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public List<dto_pedido_detalle> Mostrar_detalle_pedido_picker(String num_pedido) throws SQLException { // LA VENTANA
																											// PARA LA
																											// VERFICACION

		ResultSet resultSet = null;

		List<dto_pedido_detalle> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

			sql = "SELECT num_pedido, codigo_barra, descripcion, cantidad, seccion, obs" + "  FROM detalle_pedidos"
					+ " WHERE num_pedido = '" + num_pedido + "'";

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

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public String Obtener_descripcion_articulo(String codigo_barra) throws SQLException { /* MÉTODO QUE EL DEVUELVE LA DESCRIPCION SOLICITADA EN control_items.jsp */

		ResultSet resultSet = null;

		String des = "";
		
		conexion = obtenerConexion();

		try {

			String sql = "SELECT descripcion FROM articulos WHERE codigo_barra ='"+codigo_barra+"'";
						
			sentencia = conexion.prepareStatement(sql);
			
			//sentencia.setString(1, codigo_barra);	

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				des = resultSet.getString("descripcion");

			}

		} catch (SQLException e) {

			e.printStackTrace();
						
		}

		return des;

	}

	private static Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
