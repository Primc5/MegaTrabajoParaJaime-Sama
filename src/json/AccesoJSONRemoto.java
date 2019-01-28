package json;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Objetos.Empresas;
import Objetos.Videojuegos;
import auxiliares.ApiRequests;
import auxiliares.ReadConfig;
import interfaz.Interface;

public class AccesoJSONRemoto implements Interface{

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_GAME, SET_GAME, GET_COMPANY, SET_COMPANY, DELETE_GAME, DELETE_COMPANY; // Datos de la conexion
	private Object Creacion;

	public AccesoJSONRemoto() {

		encargadoPeticiones = new ApiRequests();
		
		ReadConfig readConfig = new ReadConfig("Ficheros/Config/jsonCalls.properties");
		HashMap<String, String> datosConexion;
		datosConexion = readConfig.getHash();
		
		SERVER_PATH = datosConexion.get("SERVER_PATH");
		GET_GAME = datosConexion.get("GET_GAME");
		SET_GAME = datosConexion.get("SET_GAME");
		GET_COMPANY = datosConexion.get("GET_COMPANY");
		SET_COMPANY = datosConexion.get("SET_COMPANY");
        DELETE_GAME = datosConexion.get("DELETE_GAME");
        DELETE_COMPANY = datosConexion.get("DELETE_COMPANY");
	}

	public HashMap<Integer, Videojuegos> LeerDatosVideojuegos(HashMap<Integer, Empresas> hmEmpresas) {

		HashMap<Integer, Videojuegos> auxhm = new HashMap<Integer, Videojuegos>();

		try {
			String url = SERVER_PATH + GET_GAME; // Sacadas de configuracion

			String response = encargadoPeticiones.getRequest(url);
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) { 
					JSONArray array = (JSONArray) respuesta.get("juegos");

					if (array.size() > 0) {

						// Declaramos variables
						Videojuegos nuevoJuego;
						String nombre, tipo, creacion;
						int id;
						Empresas empresa;

						for (int i = 0; i < array.size(); i++) {
							nuevoJuego = new Videojuegos();
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("Nombre").toString();
							tipo = row.get("Tipo").toString();
							id = Integer.parseInt(row.get("Id").toString());
							empresa = hmEmpresas.get(Integer.parseInt(row.get("Empresa").toString()));
							creacion = row.get("Creacion").toString();
							
							nuevoJuego.setNombre(nombre);
							nuevoJuego.setTipo(tipo);
							nuevoJuego.setId(id);
							nuevoJuego.setEmpresa(empresa);
							nuevoJuego.setCreación(creacion);

							auxhm.put(id, nuevoJuego);
						}

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}
	public HashMap<Integer, Empresas> LeerDatosEmpresas() {

		HashMap<Integer, Empresas> auxhm = new HashMap<Integer, Empresas>();

		try {
			String url = SERVER_PATH + GET_COMPANY; // Sacadas de configuracion

			String response = encargadoPeticiones.getRequest(url);

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());


			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) { 
					JSONArray array = (JSONArray) respuesta.get("equipos");

					if (array.size() > 0) {

						// Declaramos variables
						Empresas nuevaEmpresa;
						String nombre, tamanno, pais, director;
						int id, capital;

						for (int i = 0; i < array.size(); i++) {
							nuevaEmpresa = new Empresas();
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("Nombre").toString();
							tamanno = row.get("Tamanno").toString();
							pais = row.get("Pais").toString();
							director = row.get("Director").toString();
							id = Integer.parseInt(row.get("Id").toString());
							capital = Integer.parseInt(row.get("Capital").toString());
							nuevaEmpresa.setNombre(nombre);
							nuevaEmpresa.setTamaño(tamanno);
							nuevaEmpresa.setPais(pais);
							nuevaEmpresa.setDirector(director);
							nuevaEmpresa.setId_Empresa(id);
							nuevaEmpresa.setCapital(capital);

							auxhm.put(id, nuevaEmpresa);
						}

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}

	public HashMap<Integer, Videojuegos> AnadirDatosVideojuegos(HashMap<Integer, Videojuegos> videojuegos,
			HashMap<Integer, Empresas> empresas, String id, String nombre, String tipo, String empresa,
			String Creacion) {

		try {
			JSONObject objJugador = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			
			objJugador.put("nombre", nombre);
			objJugador.put("Empresa", empresa);
			objJugador.put("tipo", tipo);
			objJugador.put("Creacion", Creacion);

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("juegoAnnadir", objJugador);
			
			String json = objPeticion.toJSONString();


			String url = SERVER_PATH + SET_GAME;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
		}
		return null;

	}
	
	public HashMap<Integer, Empresas> AnadirDatosEmpresas(HashMap<Integer, Empresas> datos, String id, String nombre,
			String tamaño, String pais, String capital, String director) {

		try {
			JSONObject objEquipo = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objEquipo.put("Nombre", nombre);
			objEquipo.put("Tamanno", tamaño);
			objEquipo.put("Pais", pais);
			objEquipo.put("Capital", capital);
			objEquipo.put("Director", director);
			

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("empresaAnnadir", objEquipo);
			
			String json = objPeticion.toJSONString();
			String url = SERVER_PATH + SET_COMPANY;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));


				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		return null;

	}
	@Override
	public HashMap<Integer, Videojuegos> CopiarDatosVideojuegos(HashMap<Integer, Videojuegos> datos,
			HashMap<Integer, Empresas> empresas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Empresas> CopiarDatosEmpresas(HashMap<Integer, Empresas> datos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegos(HashMap<Integer, Videojuegos> datos, Integer clave){
		try {
			JSONObject objJugador = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			
			objJugador.put("Id", clave);

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "delete");
			objPeticion.put("juegoBorrar", objJugador);
			
			String json = objPeticion.toJSONString();


			String url = SERVER_PATH + DELETE_GAME;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al borrar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));


				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		return null;
	}

	@Override
	public HashMap<Integer, Empresas> EliminarDatosEmpresas(HashMap<Integer, Empresas> datos, Integer clave) {
		try {
			JSONObject objEquipo = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			
			objEquipo.put("id_Empresa", clave);
			

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "delete");
			objPeticion.put("EmpresaBorrar", objEquipo);
			
			String json = objPeticion.toJSONString();
			String url = SERVER_PATH + DELETE_COMPANY;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		return null;
	}

}