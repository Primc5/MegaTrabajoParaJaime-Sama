package Hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;

import Objetos.Empresas;
import Objetos.Videojuegos;
import interfaz.Interface;

public class AccesHibernate implements Interface{

	private String nombre, tipo, creacion, tamaño, pais, director;
	Empresas empresa;
	private int id, capital;
	private Empresas e = new Empresas();
	private Videojuegos v = new Videojuegos();
	private DefaultTableModel miTabla;
	Session session;
	private HashMap<String, String> consultas;
	
	private HashMap<Integer, Videojuegos> vj = new HashMap<Integer, Videojuegos>();
	private HashMap<Integer, Empresas> em = new HashMap<Integer, Empresas>();

	public AccesHibernate() {

		HibernateUtil util = new HibernateUtil();

		session = util.getSessionFactory().openSession();
		consultas = new HashMap<String, String>();
		consultas.put("LeerEmpresa", "select e from Empresas e");
		consultas.put("LeerVideojuegos", "select v from Videojuegos v");
	}
	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos(HashMap<Integer, Empresas> datos) {
		int i = 1;
		Query q = session.createQuery(consultas.get("LeerVideojuegos"));
		List<Videojuegos> v = q.list();
		for(Videojuegos v2:v) {
			id = v2.getId();
			nombre = v2.getNombre();
			tipo = v2.getTipo();
			empresa = v2.getEmpresa();
			creacion = v2.getCreación();
			v2.setId(id);
			v2.setNombre(nombre);
			v2.setTipo(tipo);
			v2.setEmpresa(empresa);
			v2.setCreación(creacion);
			vj.put(i, v2);
			i++;
		}
		return vj;
	}

	@Override
	public HashMap<Integer, Empresas> LeerDatosEmpresas() {
		int i = 1;
		Query q = session.createQuery(consultas.get("LeerEmpresa"));
		List<Empresas> e = q.list();
		for(Empresas e2:e) {
			id = e2.getId_Empresa();
			nombre = e2.getNombre();
			tamaño = e2.getTamaño();
			pais = e2.getPais();
			capital = e2.getCapital();
			director = e2.getDirector();
			e2.setId_Empresa(id);
			e2.setNombre(nombre);
			e2.setTamaño(tamaño);
			e2.setPais(pais);
			e2.setCapital(capital);
			e2.setDirector(director);
			em.put(i, e2);
			i++;
		}
		return em;
	}

	@Override
	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos,
			HashMap<Integer, Empresas> empresas) {
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			session.beginTransaction();
			session.save(datos.get(valor.getKey()));
			session.getTransaction().commit();
		}
		return datos;
	}

	@Override
	public HashMap<Integer, Empresas> CopiarDatosEmpresas(HashMap<Integer, Empresas> datos) {
		for (Entry<Integer, Empresas> valor : datos.entrySet()) {
			session.beginTransaction();
			session.save(datos.get(valor.getKey()));
			session.getTransaction().commit();
		}
		return datos;
	}

	@Override
	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, Integer clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Empresas> EliminarDatosEmpresas(HashMap<Integer, Empresas> datos, Integer clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> datos,
			HashMap<Integer, Empresas> empresas, String id, String nombre, String tipo, String empresa,
			String Creacion) {
		v.setId(Integer.parseInt(id));
		v.setNombre(nombre);
		v.setTipo(tipo);
		v.setEmpresa(empresas.get(Integer.parseInt(empresa)));
		v.setCreación(Creacion);
		session.beginTransaction();
		session.save(v);
		session.getTransaction().commit();
		datos.put(datos.size()+1, v);
		return datos;
	}

	@Override
	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre,
			String tamaño, String pais, String capital, String director) {
		e.setId_Empresa(Integer.parseInt(id));
		e.setNombre(nombre);
		e.setTamaño(tamaño);
		e.setPais(pais);
		e.setCapital(Integer.parseInt(capital));
		e.setDirector(director);
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		datos.put(datos.size()+1, e);
		return datos;
	}

	public void borrarDatos() {
		session.beginTransaction();
		Query q = session.createQuery("delete from Videojuegos");
		q.executeUpdate();
		q = session.createQuery("delete from Empresas");
		q.executeUpdate();
		session.getTransaction().commit();
	}
	public void cerrarSesion() {
		session.close();
	}

	public void MostrarDatos(String tabla) {
		int numColumnas = 5, fil = 0;
		Query q = session.createQuery(consultas.get(tabla));
		List<Videojuegos> v = q.list();
		for(Videojuegos v2:v) {
			fil++;
		}
		int numFilas = fil;
		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
			cabecera[0] = "id";
			cabecera[1] = "nombre";
			cabecera[2] = "tipo";
			cabecera[3] = "Empresa";
			cabecera[4] = "Creacion";
			int fila = 0, col;
			for(Videojuegos v2:v) {
				col = 0;
				contenido[fila][col] = v2.getId();
				col++;
				contenido[fila][col] = v2.getNombre();
				col++;
				contenido[fila][col] = v2.getTipo();
				col++;
				contenido[fila][col] = v2.getEmpresa().getId_Empresa();
				col++;
				contenido[fila][col] = v2.getCreación();
				fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
	}
	public void MostrarDatosEmpresas(String tabla) {
		int numColumnas = 6, fil = 0;
		Query q = session.createQuery(consultas.get(tabla));
		List<Empresas> e = q.list();
		for(Empresas e2:e) {
			fil++;
		}
		int numFilas = fil;
		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
			cabecera[0] = "id_Empresa";
			cabecera[1] = "Nombre";
			cabecera[2] = "Tamaño";
			cabecera[3] = "Pais";
			cabecera[4] = "Capital";
			cabecera[5] = "Director";
			int fila = 0, col;
			for(Empresas e2:e) {
				col = 0;
				contenido[fila][col] = e2.getId_Empresa();
				col++;
				contenido[fila][col] = e2.getNombre();
				col++;
				contenido[fila][col] = e2.getTamaño();
				col++;
				contenido[fila][col] = e2.getPais();
				col++;
				contenido[fila][col] = e2.getCapital();
				col++;
				contenido[fila][col] = e2.getDirector();
				fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
	}

	public DefaultTableModel getTabla() {
		return miTabla;
	}
}