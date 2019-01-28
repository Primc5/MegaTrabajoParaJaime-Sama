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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import Objetos.Empresas;
import Objetos.Videojuegos;
import interfaz.Interface;

public class Fichero implements Interface {
	
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
	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos(HashMap<Integer, Empresas> datos) {
		try {
			fr = new FileReader("Ficheros/Datos/yo.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		br = new BufferedReader(fr);
		int j = 1;
		try {
			while ((cadena = br.readLine()) != null) {
				v = new Videojuegos();
				int i = cadena.indexOf(':');
				String minicadena = cadena.substring(i + 1);
				minicadena = minicadena.replace(" ", "");
				v.setId(Integer.parseInt(minicadena));
				
				cadena = br.readLine();
				i = cadena.indexOf(' ');
				minicadena = cadena.substring(i + 1);
				minicadena = minicadena.replace(" ", "");
				v.setNombre(minicadena);
				cadena = br.readLine();
				i = cadena.indexOf(' ');

				minicadena = cadena.substring(i + 1);
				minicadena = minicadena.replace(" ", "");
				v.setTipo(minicadena);
				cadena = br.readLine();
				i = cadena.indexOf(' ');
				
 				minicadena = cadena.substring(i+1);
 				minicadena = minicadena.replace(" ", "");
 				System.out.println(minicadena);
 				v.setEmpresa(datos.get(Integer.parseInt(minicadena)));
 				cadena = br.readLine();
 				i = cadena.indexOf(' ');
 				
				minicadena = cadena.substring(i + 1);
				minicadena = minicadena.replace(" ", "");
				v.setCreación(minicadena);
				
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
				fr = new FileReader("Ficheros/Datos/empresas.txt");
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
					minicadena = minicadena.replace(" ", "");
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
					e.setId_Empresa(Integer.parseInt(minicadena));
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
					e.setNombre(minicadena);
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
					e.setTamaño(minicadena);
					cadena = br.readLine();
					i = cadena.indexOf(' ');

					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
					e.setPais(minicadena);
					cadena = br.readLine();

					i = cadena.indexOf(' ');
					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
					e.setCapital(Integer.parseInt(minicadena));
					cadena = br.readLine();
					
					i = cadena.indexOf(' ');
					minicadena = cadena.substring(i + 1);
					minicadena = minicadena.replace(" ", "");
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
	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, HashMap<Integer, Empresas> empresas) {
		try {
			fw = new FileWriter("Ficheros/Datos/yo.txt");
			bw = new BufferedWriter(fw);
			for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
				bw.write("ID: " + valor.getValue().getId() + " ");
				bw.newLine();
				bw.write("Nombre: " + valor.getValue().getNombre() + " ");
				bw.newLine();
				bw.write("Tipo: " + valor.getValue().getTipo() + " ");
				bw.newLine();
				bw.write("Empresa: " + valor.getValue().getEmpresa().getId_Empresa() + " ");
				bw.newLine();
				bw.write("Creación: " + valor.getValue().getCreación() + " ");
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
				fw = new FileWriter("Ficheros/Datos/empresas.txt");
				bw = new BufferedWriter(fw);
				for (Entry<Integer, Empresas> valor : datos.entrySet()) {
					bw.write("ID: " + valor.getValue().getId_Empresa() + " ");
					bw.newLine();
					bw.write("Nombre: " + valor.getValue().getNombre() + " ");
					bw.newLine();
					bw.write("Tamaño: " + valor.getValue().getTamaño() + " ");
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

	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> datos,HashMap<Integer, Empresas> empresas, String id, String nombre, String tipo, String empresa, String creacion) {
		v.setId(Integer.parseInt(id));
		v.setNombre(nombre);
		v.setTipo(tipo);
		v.setEmpresa(empresas.get(Integer.parseInt(empresa)));
		v.setCreación(creacion);
		datos.put(datos.size()+1, v);
		return datos;
	}
	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre, String tamaño, String pais, String capital, String director) {
		e.setId_Empresa(Integer.parseInt(id));
		e.setNombre(nombre);
		e.setTamaño(tamaño);
		e.setPais(pais);
		e.setCapital(Integer.parseInt(capital));
		e.setDirector(director);
		datos.put(datos.size()+1, e);
		return datos;
	}
}
