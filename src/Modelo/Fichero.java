package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import Objetos.Empresas;
import Objetos.Videojuegos;
import interfas.Interface;

public class Fichero implements Interface {
	
	private DefaultTableModel miTabla;
	
	private String cadena;
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private BufferedWriter bw;
	private HashMap<Integer, Videojuegos> videojuegos = new HashMap<Integer, Videojuegos>();
	private HashMap<Integer, Empresas> empresas = new HashMap<Integer, Empresas>();
	private Videojuegos v = new Videojuegos();
	private Empresas e = new Empresas();
	// lee videojuegos del fichero 
	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos() {
		try {
			fr = new FileReader("src/yo.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		br = new BufferedReader(fr);
		int j = 1;
		try {
			cadena = br.readLine();
			while (cadena != null) {
				v = new Videojuegos();
				int i = cadena.indexOf(':');
				String minicadena = cadena.substring(i + 1);
				v.setId(minicadena);
				
				cadena = br.readLine();
				i = cadena.indexOf(' ');
				minicadena = cadena.substring(i + 1);
				v.setNombre(minicadena);
				cadena = br.readLine();
				i = cadena.indexOf(' ');

				minicadena = cadena.substring(i + 1);
				v.setTipo(minicadena);
				cadena = br.readLine();
				i = cadena.indexOf(' ');
				
 				minicadena = cadena.substring(i + 1);
 				v.setEmpresa(LeerDatosEmpresas().get(j));
 				cadena = br.readLine();
 				i = cadena.indexOf(' ');
 				
				minicadena = cadena.substring(i + 1);
				v.setCreaci�n(minicadena);
				cadena = br.readLine();
				
				videojuegos.put(j, v);
				j++;
			}
			// valores.remove(0);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videojuegos;
	}
	// lee empresas del fichero 
		public HashMap<Integer, Empresas> LeerDatosEmpresas() {
			try {
				fr = new FileReader("src/empresas.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			br = new BufferedReader(fr);
			int j = 1;
			try {
				cadena = br.readLine();
				while (cadena != null) {
					e = new Empresas();
					int i = cadena.indexOf(':');
					String minicadena = cadena.substring(i + 1);
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					e.setId_Empresa(minicadena);
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					e.setNombre(minicadena);
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					e.setTama�o(minicadena);
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					e.setPais(minicadena);
					cadena = br.readLine();

					i = cadena.indexOf(' ');
					minicadena = cadena.substring(i + 1);
					e.setCapital(minicadena);
					cadena = br.readLine();
					
					i = cadena.indexOf(' ');
					minicadena = cadena.substring(i + 1);
					e.setDirector(minicadena);
					cadena = br.readLine();

					empresas.put(j, e);
					j++;
				}
				
				// valores.remove(0);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			return empresas;
		}

	// mete en el fichero los valores que le pase por un hashmap de videojuegos
	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos) {
		try {
			fw = new FileWriter("src/yo.txt");
			bw = new BufferedWriter(fw);
			for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
				bw.write("ID: " + valor.getValue().getId() + " ");
				bw.newLine();
				bw.write("Nombre: " + valor.getValue().getNombre() + " ");
				bw.newLine();
				bw.write("Tipo: " + valor.getValue().getTipo() + " ");
				bw.newLine();
				bw.write("Empresa: " + valor.getValue().getEmpresa() + " ");
				bw.newLine();
				bw.write("Creaci�n: " + valor.getValue().getCreaci�n() + " ");
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	// mete en el fichero los valores que le pase por un hashmap de empresas
		public HashMap<Integer, Empresas> CopiarDatosEmpresas(HashMap<Integer, Empresas> datos) {
			try {
				fw = new FileWriter("src/empresas.txt");
				bw = new BufferedWriter(fw);
				for (Entry<Integer, Empresas> valor : datos.entrySet()) {
					bw.write("ID: " + valor.getValue().getId_Empresa() + " ");
					bw.newLine();
					bw.write("Nombre: " + valor.getValue().getNombre() + " ");
					bw.newLine();
					bw.write("Tama�o: " + valor.getValue().getTama�o() + " ");
					bw.newLine();
					bw.write("Pais: " + valor.getValue().getPais() + " ");
					bw.newLine();
					bw.write("Capital: " + valor.getValue().getCapital() + " ");
					bw.newLine();
					bw.write("Director: " + valor.getValue().getDirector() + " ");
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return datos;
		}

	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, Integer clave) {
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			if (valor.getKey() == clave) {
				datos.remove(clave);
			} 
		}
		return datos;
	}
	public HashMap<Integer, Empresas> EliminarDatosEmpresas(HashMap<Integer, Empresas> datos, Integer clave) {
		for (Entry<Integer, Empresas> valor : datos.entrySet()) {
			if (valor.getKey() == clave) {
				datos.remove(clave);
			} 
		}
		return datos;
	}

	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> datos, String id, String nombre, String tipo, String empresa, String Creacion) {
		v.setId(id);
		v.setNombre(nombre);
		v.setTipo(tipo);
		for (Entry<Integer, Empresas> valor : LeerDatosEmpresas().entrySet()) {
			v.setEmpresa(valor.getValue());
		}
		v.setCreaci�n(Creacion);
		datos.put(datos.size()+1, v);
		return datos;
	}
	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre, String tama�o, String pais, String capital, String director) {
		e.setId_Empresa(id);
		e.setNombre(nombre);
		e.setTama�o(tama�o);
		e.setPais(pais);
		e.setCapital(capital);
		e.setDirector(director);
		datos.put(datos.size()+1, e);
		return datos;
	}
	public void MostrarDatosVideojuegos(HashMap<Integer, Videojuegos> datos) {
		int numColumnas = 5;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id";
		cabecera[1] = "nombre";
		cabecera[2] = "tipo";
		cabecera[3] = "Empresa";
		cabecera[4] = "Creacion";
		int fila = 0, col;
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			col = 0;
			contenido[fila][col] = valor.getValue().getId();
			col++;
			contenido[fila][col] = valor.getValue().getNombre();
			col++;
			contenido[fila][col] = valor.getValue().getTipo();
			col++;
			contenido[fila][col] = valor.getValue().getEmpresa().getId_Empresa();
			col++;
			contenido[fila][col] = valor.getValue().getCreaci�n();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}
	public void MostrarDatosEmpresas(HashMap<Integer, Empresas> datos) {
		int numColumnas = 6;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id_Empresa";
		cabecera[1] = "Nombre";
		cabecera[2] = "Tama�o";
		cabecera[3] = "Pais";
		cabecera[4] = "Capital";
		cabecera[5] = "Director";
		int fila = 0, col;
		for (Entry<Integer, Empresas> valor : datos.entrySet()) {
			col = 0;
			contenido[fila][col] = valor.getValue().getId_Empresa();
			col++;
			contenido[fila][col] = valor.getValue().getNombre();
			col++;
			contenido[fila][col] = valor.getValue().getTama�o();
			col++;
			contenido[fila][col] = valor.getValue().getPais();
			col++;
			contenido[fila][col] = valor.getValue().getCapital();
			col++;
			contenido[fila][col] = valor.getValue().getDirector();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}

	public DefaultTableModel getTabla() {
		return miTabla;
	}
}
