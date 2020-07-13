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
import dto.dto_pedido;
import dto.dto_pedido_detalle;

public class dao_pedidos {

	private static Connection conexion;
	private static PreparedStatement sentencia, sentencia2, sentencia3;

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

	public List<dto_pedido> Mostrar_pedido_picker(String picker) throws SQLException { // MOSTRAR EN COLECTOR

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

	public List<dto_pedido_detalle> Mostrar_detalle_pedido_picker(String num_pedido) throws SQLException { // LA VENTANA
																											// PARA LA
		System.out.println("SELECCION: " + num_pedido); // VERFICACION

		ResultSet resultSet = null;

		List<dto_pedido_detalle> listaProductos = new ArrayList<>();

		String sql = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

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

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listaProductos;
	}

	public String Obtener_descripcion_articulo(String codigo_barra)
			throws SQLException { /* MÉTODO QUE EL DEVUELVE LA DESCRIPCION SOLICITADA EN control_items.jsp */

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

	// CONTROL TOTAL PARA ACTUALIZAR ITEMS Y GUARDAR

	// 1°- VERIFICAR UNA TABLA DETALLE TEMPORAL LIBRE....
	@SuppressWarnings({ "resource", "unused" })
	public int validar_actualizaciones_items(String num_pedido, String codigo_barra1, int flag, String codigo_barra2,
			String des, String can, String obs, String sec) throws SQLException {

		int retorno = 0;

		ResultSet resultSet = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			String sql = "select count(*) as total from detalle_temporal_1"; // EMPEZAR POR DETALLE 1

			sentencia = conexion.prepareStatement(sql);

			resultSet = sentencia.executeQuery();

			while (resultSet.next()) {

				if (resultSet.getString("total").equals("0")) { // SI NO TIENE REGISTROS, MIGRAR

					// MIGRACION
					String sql2 = "INSERT INTO detalle_temporal_1 SELECT * FROM detalle_pedidos WHERE num_pedido ='"
							+ num_pedido + "'";

					sentencia2 = conexion.prepareStatement(sql2);

					sentencia2.executeUpdate(sql2);

					conexion.commit();
					sentencia2.close();
					conexion.close();
					// MIGRACION

					// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

					if (flag == 1) { // LA PRIMERA MODIFICACION ES UNA CONFIRMACION

						String sql3 = "UPDATE detalle_temporal_1 SET obs = 'CONFIRMADO' WHERE codigo_barra = ?";

						try {

							PreparedStatement sentencia = conexion.prepareStatement(sql3);

							sentencia.setString(1, codigo_barra1);

							sentencia.executeUpdate();

						} catch (SQLException e) {

							e.printStackTrace();

						}

					} else if (flag == 2) { // LA PRIMERA MODIFICACION ES UNA SUSTITUCION

						String sql3 = "UPDATE detalle_temporal_1 SET obs = 'SUSTITUIDO POR: " + codigo_barra2
								+ " WHERE codigo_barra = ?";

						try {

							PreparedStatement sentencia = conexion.prepareStatement(sql3);

							sentencia.setString(1, codigo_barra1);

							sentencia.executeUpdate();

							String sql4 = "INSERT INTO detalle_temporal_1 (num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
									+ " VALUES (?, ?, ?, ?, ?, ?)";

							try {

								PreparedStatement sentencia2 = conexion.prepareStatement(sql4);

								sentencia2.setString(1, num_pedido);
								sentencia2.setString(2, codigo_barra2);
								sentencia2.setString(3, des);
								sentencia2.setString(4, can);
								sentencia2.setString(5, sec);
								sentencia2.setString(6, "SUSTITUTO DE: " + codigo_barra1);

								sentencia2.executeUpdate();

							} catch (SQLException e) {

								e.printStackTrace();

							}

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

					// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

				} else { // SI LOS TIENE, PREGUNTAR SI LO CARGADO CORRESPONDE AL NUM_PEDIDO

					conexion.setAutoCommit(false);

					String var;

					int bandera = 0;

					String sql3 = "SELECT num_pedido FROM detalle_temporal_1 WHERE num_pedido ='" + num_pedido + "'";

					sentencia3 = conexion.prepareStatement(sql3);

					resultSet = sentencia3.executeQuery();

					while (resultSet.next()) {

						var = resultSet.getString("descripcion");

						bandera = 1; // SI ENCONTRÓ ESE NUMERO DE PEDIDO, PROSEGUIR CON LA ACTUALIZACIÓN

					}

					if (bandera == 1) { // SI ENCONTRÓ ESE NUMERO DE PEDIDO, PROSEGUIR CON LA ACTUALIZACIÓN

						// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

						if (flag == 1) { // LA SIGUIENTE MODIFICACION ES UNA CONFIRMACION

							String sql4 = "UPDATE detalle_temporal_1 SET obs = 'CONFIRMADO' WHERE codigo_barra = ?";

							try {

								PreparedStatement sentencia = conexion.prepareStatement(sql4);

								sentencia.setString(1, codigo_barra1);

								sentencia.executeUpdate();

							} catch (SQLException e) {

								e.printStackTrace();

							}

						} else if (flag == 2) { // LA SIGUIENTE MODIFICACION ES UNA SUSTITUCION

							String sql4 = "UPDATE detalle_temporal_1 SET obs = 'SUSTITUIDO POR: " + codigo_barra2
									+ " WHERE codigo_barra = ?";

							try {

								PreparedStatement sentencia = conexion.prepareStatement(sql4);

								sentencia.setString(1, codigo_barra1);

								sentencia.executeUpdate();

								String sql5 = "INSERT INTO detalle_temporal_1 (num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
										+ " VALUES (?, ?, ?, ?, ?, ?)";

								try {

									PreparedStatement sentencia2 = conexion.prepareStatement(sql5);

									sentencia2.setString(1, num_pedido);
									sentencia2.setString(2, codigo_barra2);
									sentencia2.setString(3, des);
									sentencia2.setString(4, can);
									sentencia2.setString(5, sec);
									sentencia2.setString(6, "SUSTITUTO DE: " + codigo_barra1);

									sentencia2.executeUpdate();

								} catch (SQLException e) {

									e.printStackTrace();

								}

							} catch (SQLException e) {

								e.printStackTrace();

							}

						}

						// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

						retorno = 1; // ACTUALIZADO

					} else if (bandera == 0) { // SI NO, SIGNIFICA QUE ESTA CARGADO CON OTRO NÚMERO. PASAR AL METODO
												// DETALLE 2

						retorno = 0;

					}

				}

			}

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return retorno;

	}

	// CONTROL TOTAL PARA ACTUALIZAR ITEMS Y GUARDAR

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int metodo(String num_pedido, String codigo_barra1, int flag, String codigo_barra2, String des, String can,
			String obs, String sec) throws SQLException {

		int retorno = 0;

		ResultSet resultSet = null;

		conexion = obtenerConexion();

		try {

			conexion.setAutoCommit(false);

			String sql = "CREATE TABLE detalle_temporal_"+num_pedido 
					+ "  num_pedido character varying(100) NOT NULL,\r\n"
					+ "  codigo_barra character varying(100) NOT NULL,\r\n"
					+ "  descripcion character varying(100) NOT NULL,\r\n" 
					+ "  cantidad double precision NOT NULL,\r\n"
					+ "  seccion character varying(100) NOT NULL,\r\n"
					+ "  obs character varying(100) NOT NULL\r\n"
					+ ")"; 

			sentencia = conexion.prepareStatement(sql);

			sentencia.executeQuery(); //EN ESTE PUNTO, SI HAY UN ERROR, SIGNIFICA QUE LA TABLA YA EXISTE. LA CONTINUIDAD PASA AL CATCH
			
			

				 // SI NO TIENE REGISTROS, MIGRAR

					// MIGRACION
					String sql2 = "INSERT INTO detalle_temporal_1 SELECT * FROM detalle_pedidos WHERE num_pedido ='"
							+ num_pedido + "'";

					sentencia2 = conexion.prepareStatement(sql2);

					sentencia2.executeUpdate(sql2);

					conexion.commit();
					sentencia2.close();
					conexion.close();
					// MIGRACION

					// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

					if (flag == 1) { // LA PRIMERA MODIFICACION ES UNA CONFIRMACION

						String sql3 = "UPDATE detalle_temporal_1 SET obs = 'CONFIRMADO' WHERE codigo_barra = ?";

						try {

							PreparedStatement sentencia = conexion.prepareStatement(sql3);

							sentencia.setString(1, codigo_barra1);

							sentencia.executeUpdate();

						} catch (SQLException e) {

							e.printStackTrace();

						}

					} else if (flag == 2) { // LA PRIMERA MODIFICACION ES UNA SUSTITUCION

						String sql3 = "UPDATE detalle_temporal_1 SET obs = 'SUSTITUIDO POR: " + codigo_barra2
								+ " WHERE codigo_barra = ?";

						try {

							PreparedStatement sentencia = conexion.prepareStatement(sql3);

							sentencia.setString(1, codigo_barra1);

							sentencia.executeUpdate();

							String sql4 = "INSERT INTO detalle_temporal_1 (num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
									+ " VALUES (?, ?, ?, ?, ?, ?)";

							try {

								PreparedStatement sentencia2 = conexion.prepareStatement(sql4);

								sentencia2.setString(1, num_pedido);
								sentencia2.setString(2, codigo_barra2);
								sentencia2.setString(3, des);
								sentencia2.setString(4, can);
								sentencia2.setString(5, sec);
								sentencia2.setString(6, "SUSTITUTO DE: " + codigo_barra1);

								sentencia2.executeUpdate();

							} catch (SQLException e) {

								e.printStackTrace();

							}

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

					// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

				  // SI LOS TIENE, PREGUNTAR SI LO CARGADO CORRESPONDE AL NUM_PEDIDO

					conexion.setAutoCommit(false);

					String var;

					int bandera = 0;

					String sql3 = "SELECT num_pedido FROM detalle_temporal_1 WHERE num_pedido ='" + num_pedido + "'";

					sentencia3 = conexion.prepareStatement(sql3);

					resultSet = sentencia3.executeQuery();

					while (resultSet.next()) {

						var = resultSet.getString("descripcion");

						bandera = 1; // SI ENCONTRÓ ESE NUMERO DE PEDIDO, PROSEGUIR CON LA ACTUALIZACIÓN

					}

					if (bandera == 1) { // SI ENCONTRÓ ESE NUMERO DE PEDIDO, PROSEGUIR CON LA ACTUALIZACIÓN

						// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

						if (flag == 1) { // LA SIGUIENTE MODIFICACION ES UNA CONFIRMACION

							String sql4 = "UPDATE detalle_temporal_1 SET obs = 'CONFIRMADO' WHERE codigo_barra = ?";

							try {

								PreparedStatement sentencia = conexion.prepareStatement(sql4);

								sentencia.setString(1, codigo_barra1);

								sentencia.executeUpdate();

							} catch (SQLException e) {

								e.printStackTrace();

							}

						} else if (flag == 2) { // LA SIGUIENTE MODIFICACION ES UNA SUSTITUCION

							String sql4 = "UPDATE detalle_temporal_1 SET obs = 'SUSTITUIDO POR: " + codigo_barra2
									+ " WHERE codigo_barra = ?";

							try {

								PreparedStatement sentencia = conexion.prepareStatement(sql4);

								sentencia.setString(1, codigo_barra1);

								sentencia.executeUpdate();

								String sql5 = "INSERT INTO detalle_temporal_1 (num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)"
										+ " VALUES (?, ?, ?, ?, ?, ?)";

								try {

									PreparedStatement sentencia2 = conexion.prepareStatement(sql5);

									sentencia2.setString(1, num_pedido);
									sentencia2.setString(2, codigo_barra2);
									sentencia2.setString(3, des);
									sentencia2.setString(4, can);
									sentencia2.setString(5, sec);
									sentencia2.setString(6, "SUSTITUTO DE: " + codigo_barra1);

									sentencia2.executeUpdate();

								} catch (SQLException e) {

									e.printStackTrace();

								}

							} catch (SQLException e) {

								e.printStackTrace();

							}

						}

						// VALIDACIONES PARA CONFIRMAR O SUSTITUIR

						retorno = 1; // ACTUALIZADO

					} else if (bandera == 0) { // SI NO, SIGNIFICA QUE ESTA CARGADO CON OTRO NÚMERO. PASAR AL METODO
												// DETALLE 2

						retorno = 0;

					}

				

			

			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return retorno;

	}

	private static Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
