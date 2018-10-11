package interfas;

import java.util.HashMap;

import Objetos.Empresas;
import Objetos.Videojuegos;

public interface Interface {

	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos();
	public HashMap<Integer, Empresas> LeerDatosEmpresas();
	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos);
	public HashMap<Integer, Empresas> CopiarDatosEmpresas(HashMap<Integer, Empresas> datos);
	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, Integer clave);
	public HashMap<Integer, Empresas> EliminarDatosEmpresas(HashMap<Integer, Empresas> datos, Integer clave);
	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> datos, String id, String nombre, String tipo, String empresa, String Creacion);
	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre, String tamaño, String pais, String capital, String director);
}
