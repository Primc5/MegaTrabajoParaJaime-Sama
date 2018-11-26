package json;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import Objetos.Empresas;
import Objetos.Videojuegos;

public class Intermediario {
	AccesoJSONRemoto acceso;

	public Intermediario() {
		this.acceso = new AccesoJSONRemoto();
	}

	private HashMap<Integer, Videojuegos> leeVideojuegos() {

		HashMap<Integer, Videojuegos> hmAux = acceso.leeVideojuegos();

		return hmAux;

	}

	private void pintaVideojuegos(HashMap<Integer, Videojuegos> map) {

		// Recorre el hashmap y va pintando los jugadores (utiliza el m�todo
		// toString de la clase Jgador
		for (Map.Entry<Integer, Videojuegos> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}

	}
	
	private HashMap<Integer, Empresas> leeEquipos() {

		HashMap<Integer, Empresas> hmAux = acceso.leeEmpresas();

		return hmAux;

	}

	private void pintaEquipos(HashMap<Integer, Empresas> map) {

		// Recorre el hashmap y va pintando los jugadores (utiliza el m�todo
		// toString de la clase Jgador
		for (Map.Entry<Integer, Empresas> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}

	}



	private Videojuegos crearJugador() {

		String nombre;
		int numero;
		int equipoFK;
		Videojuegos jAux = null;
		
			/*System.out.println("Escriba el nombre del jugador a a�adir");
			nombre = teclado.nextLine();
			System.out.println("Escriba el n�mero del jugador a a�adir");
			numero = Integer.parseInt(teclado.nextLine());

			Aqu� lo l�gico ser�a mostrar el listado de equipos y poder
			seleccionar uno

			System.out.println("Escriba el equipo del jugador a a�adir");
			equipoFK = Integer.parseInt(teclado.nextLine());*/

			jAux = new Videojuegos();

		return jAux;

	}
}
