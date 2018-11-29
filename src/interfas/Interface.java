package interfas;

import java.util.HashMap;

import Objetos.Empresas;
import Objetos.Videojuegos;

public interface Interface {

	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos(HashMap<Integer, Empresas> datos);

	public HashMap<Integer, Empresas> LeerDatosEmpresas();

	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos,
			HashMap<Integer, Empresas> empresas);

	public HashMap<Integer, Empresas> CopiarDatosEmpresas(HashMap<Integer, Empresas> datos);

	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> datos,
			HashMap<Integer, Empresas> empresas, String id, String nombre, String tipo, String empresa,
			String Creacion);

	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre,
			String tama�o, String pais, String capital, String director);

	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, Integer clave,
			String nombre, String empresa, String tipo, String id, String Creacion);

	HashMap<Integer, Empresas> EliminarDatosEmpresas(HashMap<Integer, Empresas> datos, Integer clave, String nombre,
			String id, String pais, String tama�o, String capital, String director);
}
