package interfas;

import java.util.HashMap;

import Objetos.Videojuegos;

public interface Interface {

	public HashMap<Integer, Videojuegos> LeerDatos();
	public HashMap<Integer, Videojuegos> CopiarDatos(HashMap<Integer, Videojuegos> datos);
	public HashMap<Integer, Videojuegos> EliminarDatos(HashMap<Integer, Videojuegos> datos, Integer clave);
	public HashMap<Integer, Videojuegos> AnadirDatos(HashMap<Integer, Videojuegos> datos, String id, String nombre, String tipo, String empresa, String Creacion);
}
