package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import Objetos.Videojuegos;
import interfas.Interface;

public class BaseDeDatos implements Interface {

	private DefaultTableModel miTabla;

	private String bd, login, pwd, url, driver, nombre, tipo, empresa, creacion, id;
	private Connection conexion;
	private HashMap<String, String> consultas;
	private Videojuegos v;
	private HashMap<Integer, Videojuegos> vj = new HashMap<Integer, Videojuegos>();

	public BaseDeDatos() {
		getBBDDini();
		try {
			driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, login, pwd);

			System.out.println(" - Conexión con MySQL establecida -");

		} catch (Exception e) {
			System.out.println(" – Error de Conexión con MySQL -");
			e.printStackTrace();
		}

		consultas = new HashMap<String, String>();
		consultas.put("eliminarDeBBDD", "Delete From ficheritos where id = ?");
		consultas.put("sincronize",
				"INSERT INTO ficheritos (id, nombre, tipo, Empresa, Creacion)" + "VALUES (?, ?, ?, ?, ?);");
		consultas.put("LeerBBDD", "Select * from ficheritos");
		consultas.put("eliminarTabla", "DROP TABLE ficheritos");
		consultas.put("crearTabla",
				"Create TABLE ficheritos(id int primary key auto_increment, nombre varchar(45), tipo varchar(45), Empresa varchar(45), Creacion date)");
	}

	private void getBBDDini() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		OutputStream salida = null;
		try {
			File miConfig = new File("src/Modelo/bbdd_conf.ini");
			if (miConfig.exists()) {
				entrada = new FileInputStream(miConfig);
				salida = new FileOutputStream(miConfig);
				// cargamos el archivo de propiedades
				propiedades.load(entrada);
				if (miConfig.length() == 0) {
					propiedades.setProperty("DataBase", "ficheros");
					propiedades.setProperty("usuario", "root");
					propiedades.setProperty("contrasena", "");
					propiedades.store(salida, "Archivo de Configuración de la Base de Datos");
				} else {
					System.out.println(miConfig.lastModified() + "\n" + miConfig.length());
				}

				// obtenemos las propiedades y las imprimimos
				bd = propiedades.getProperty("DataBase");
				login = propiedades.getProperty("usuario");
				pwd = propiedades.getProperty("contrasena");
				url = "jdbc:mysql://localhost/" + bd
						+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public HashMap<Integer, Videojuegos> CopiarDatos(HashMap<Integer, Videojuegos> datos) {
		PreparedStatement stm;
		int rset = 0;
		try {
		stm = conexion.prepareStatement(consultas.get("eliminarTabla"));
		rset = stm.executeUpdate();
		stm = conexion.prepareStatement(consultas.get("crearTabla"));
		rset = stm.executeUpdate();
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			stm = conexion.prepareStatement(consultas.get("sincronize"));
			stm.setString(1, valor.getValue().getId());
			stm.setString(2, valor.getValue().getNombre());
			stm.setString(3, valor.getValue().getTipo());
			stm.setString(4, valor.getValue().getEmpresa());
			stm.setString(5, valor.getValue().getCreación());
			rset = stm.executeUpdate();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}

	public HashMap<Integer, Videojuegos> AnadirDatos(HashMap<Integer, Videojuegos> datos, String id, String nombre, String tipo, String empresa, String creacion) {
		try {
			PreparedStatement stm;
			stm = conexion.prepareStatement(consultas.get("sincronize"));
			stm.setString(1, id);
			// cambiamos el formato para la recogida en sql
			stm.setString(2, nombre);
			stm.setString(3, tipo);
			stm.setString(4, empresa);
			stm.setString(5, creacion);
			int rset = stm.executeUpdate();
			v.setId(id);
			v.setNombre(nombre);
			v.setTipo(tipo);
			v.setEmpresa(empresa);
			v.setCreación(creacion);
			datos.put(datos.size()+1, v);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}

	public HashMap<Integer, Videojuegos> LeerDatos() {
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(consultas.get("LeerBBDD"));
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				v = new Videojuegos();
				id = rset.getString("id");
				nombre = rset.getString("nombre");
				tipo = rset.getString("tipo");
				empresa = rset.getString("Empresa");
				creacion = rset.getString("Creacion");
				v.setNombre(nombre);
				v.setId(id);
				v.setTipo(tipo);
				v.setEmpresa(empresa);
				v.setCreación(creacion);
				vj.put(rset.getInt(1), v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(vj);
		return vj;
	}

	public void MostrarDatos(String tabla) {
		int numColumnas = getNumColumnas(consultas.get(tabla));
		int numFilas = getNumFilas(consultas.get(tabla));

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(consultas.get(tabla));
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		miTabla = new DefaultTableModel(contenido, cabecera);
	}

	private int getNumColumnas(String sql) {
		int num = 0;

		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	private int getNumFilas(String sql) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	public DefaultTableModel getTabla() {
		return miTabla;
	}

	@Override
	public HashMap<Integer, Videojuegos> EliminarDatos(HashMap<Integer, Videojuegos> datos, Integer clave) {
		try {
			PreparedStatement stm;
			stm = conexion.prepareStatement(consultas.get("eliminarDeBBDD"));
			stm.setLong(1, clave);
			int rset = stm.executeUpdate();
			datos.remove(clave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
}
