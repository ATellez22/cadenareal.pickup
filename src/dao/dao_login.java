package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class dao_login {

	private Connection conexion;
	private PreparedStatement sentencia, sentencia2;

	public static int contador = 0; // CONTEO
	static Calendar fecha = new GregorianCalendar(); // FECHA ACTUAL
	public static String date, hour;

	public static String tipo; // Tipo de usuario
	public static String sucursal; // Sucursal del usuario
	static int bandera_control_cuenta = 0;

	// Obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

	public int control_usuario(String usuario, String clave) throws SQLException {

		String sql = null;
		String sql2 = null;

		int bandera = 0;

		conexion = obtenerConexion();

		try {

			sql = "select * from usuarios where usuario = ?";

			conexion.setAutoCommit(false);

			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, usuario);			

			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {

				bandera = 1; // SI EXISTE EL USUARIO

			}

			if (bandera == 1) {

				try {

					sql2 = "select * from usuarios where pass = ?";

					conexion.setAutoCommit(false);

					sentencia2 = conexion.prepareStatement(sql2);

					sentencia2.setString(1, clave);
					
					ResultSet resultado2 = sentencia2.executeQuery();

					while (resultado2.next()) {

						bandera = 2; // CONTRASEÑA CORRECTA

					}

				} catch (SQLException e) {

					System.out.println("Espacio vacio");

					e.printStackTrace();

				}

			}
			
			conexion.commit();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {

			System.out.println("Espacio vacio");

			e.printStackTrace();

		}

		return bandera;

	}

}
