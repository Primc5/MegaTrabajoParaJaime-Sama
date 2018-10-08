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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Objetos.Videojuegos;
import interfas.Interface;

public class Fichero implements Interface {

	private Scanner sc;

	private String cadena, archivo = "src/yo.txt";
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private BufferedWriter bw;
	private HashMap<Integer, Videojuegos> valores = new HashMap<Integer, Videojuegos>();
	private Videojuegos v = new Videojuegos();;

	// lee datos del fichero
	public HashMap<Integer, Videojuegos> LeerDatos() {
		try {
			fr = new FileReader(archivo);
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
				i = cadena.indexOf(' ');

				minicadena = cadena.substring(i + 1);
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
				v.setEmpresa(minicadena);
				cadena = br.readLine();

				i = cadena.indexOf(' ');
				minicadena = cadena.substring(i + 1);
				v.setCreación(minicadena);
				cadena = br.readLine();

				valores.put(j, v);
				j++;
			}
			// valores.remove(0);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valores;
	}

	// mete en el fichero los valores que le pase por un hashmap
	public HashMap<Integer, Videojuegos> CopiarDatos(HashMap<Integer, Videojuegos> datos) {
		try {
			fw = new FileWriter(archivo);
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

	public HashMap<Integer, Videojuegos> EliminarDatos(HashMap<Integer, Videojuegos> datos, Integer clave) {
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			if (valor.getKey() == clave) {
				datos.remove(clave);
			} 
		}
		return datos;
	}

	public HashMap<Integer, Videojuegos> AnadirDatos(HashMap<Integer, Videojuegos> datos, String id, String nombre, String tipo, String empresa, String Creacion) {
		v.setId(id);
		v.setNombre(nombre);
		v.setTipo(tipo);
		v.setEmpresa(empresa);
		v.setCreación(Creacion);
		datos.put(datos.size()+1, v);
		return datos;
	}
}
