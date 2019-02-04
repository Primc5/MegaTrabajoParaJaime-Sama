package mongo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Objetos.Empresas;
import Objetos.Videojuegos;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

public class MongoConection {
	private String mongodb, col;
	private String port;
	private static MongoClient mongoClient;
	private static MongoDatabase db;
	private static DB database;
	private static MongoCollection<Document> collection;
	private static MongoCursor<Document> cursor;
	private static DBCollection dbCollection;
	private static Empresas empresas;
	private static Videojuegos videojuegos;
	private static JSONObject obj;
	private static JSONArray arr;

	public MongoConection() {
		// Conexión con MongoDB
		getMongoInit();
		try {
			mongoClient = new MongoClient("localhost", Integer.parseInt(port));
			db = mongoClient.getDatabase(mongodb);
			// database = (DB) mongoClient.getDatabase("games");
			collection = db.getCollection(col);
			// dbCollection = database.getCollection("games");
			System.out.println("connected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void getMongoInit() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		OutputStream salida = null;
		try {
			File miConfig = new File("Ficheros/Config/mongo_conf.ini");
			if (miConfig.exists()) {
				entrada = new FileInputStream(miConfig);
				salida = new FileOutputStream(miConfig);
				// cargamos el archivo de propiedades
				propiedades.load(entrada);
				if (miConfig.length() == 0) {
					propiedades.setProperty("DataBase", "games");
					propiedades.setProperty("collection", "games");
					propiedades.setProperty("puerto", "27017");
					propiedades.store(salida, "Archivo de Configuración de Mongo");
				} else {
					System.out.println(miConfig.lastModified() + "\n" + miConfig.length());
				}

				// obtenemos las propiedades y las imprimimos
				mongodb = propiedades.getProperty("DataBase");
				col = propiedades.getProperty("collection");
				port = propiedades.getProperty("puerto");

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

	// -------------Leer Datos---------------
	public ArrayList<Empresas> LeerDatosEmpresas() {
		String id, nombre, tamano, pais, capital, director;
		ArrayList<Empresas> empresa = new ArrayList<Empresas>();
		for (Document document : collection.find()) {
			obj = (JSONObject) JSONValue.parse(document.toJson().toString());
			Empresas empresas = new Empresas();
			id = (String) obj.get("id");
			nombre = (String) obj.get("nombre");
			tamano = (String) obj.get("tamano");
			pais = (String) obj.get("pais");
			capital = (String) obj.get("capital");
			director = (String) obj.get("director");

			empresas.setId_Empresa(Integer.parseInt(id));
			empresas.setNombre(nombre);
			empresas.setTamaño(tamano);
			empresas.setPais(pais);
			empresas.setCapital(Integer.parseInt(capital));
			empresas.setDirector(director);
			empresa.add(empresas);
		}
		return empresa;
	}

	public ArrayList<Videojuegos> LeerDatosVideojuegos(ArrayList<Empresas> empresas) {
		System.out.println("COÑO");
		ArrayList<Videojuegos> videojuego = new ArrayList<Videojuegos>();
		for (Document document : collection.find()) {
			obj = (JSONObject) JSONValue.parse(document.toJson().toString());
			arr = (JSONArray) obj.get("videojuegos");
			for (int i = 0; i < arr.size(); i++) {
				videojuegos = new Videojuegos();
				JSONObject row = (JSONObject) arr.get(i);
				String idGame = row.get("id").toString();
				String nombreGame = row.get("nombre").toString();
				String tipoGame = row.get("tipo").toString();
				Empresas empresaGame = empresas.get(i);
				System.out.println("DAME LA PUTA IDDDD" + empresaGame.getId_Empresa());
				String creacionGame = row.get("creacion").toString();

				// metiendo los datos en el ArrayList
				videojuegos.setId(Integer.parseInt(idGame));
				videojuegos.setNombre(nombreGame);
				videojuegos.setTipo(tipoGame);
				videojuegos.setEmpresa(empresaGame);
				videojuegos.setCreación(creacionGame);
				videojuego.add(videojuegos);
			}
		}
		return videojuego;
	}

	// -------------Insertar Datos---------------
	public boolean AnadirDatosEmpresas(String id, String nombre, String tamano, String pais, String capital,
			String director) {
		Document user = new Document();
		JSONArray array = new JSONArray();

		user.append("id", id).append("nombre", nombre).append("tamano", tamano).append("pais", pais)
				.append("capital", capital).append("director", director).append("videojuegos", array);

		collection.insertOne(user);
		return false;
	}

	public boolean AnadirDatosVideojuegos(String id, String nombre, String tipo, String creacion, String empresa) {
		try {
			BasicDBObject match = new BasicDBObject();
			match.put("id", id);

			BasicDBObject video = new BasicDBObject();
			video.put("id", id);
			video.put("nombre", nombre);
			video.put("tipo", tipo);
			video.put("creacion", creacion);

			BasicDBObject update = new BasicDBObject();
			update.put("$push", new BasicDBObject("videojuegos", video));
			collection.updateOne(match, update);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// -------------Eliminar Datos---------------
	public boolean EliminarDatosEmpresas(ArrayList<Empresas> empresas, int clave) {
		try {
			Document user = new Document();
			JSONArray array = new JSONArray();

			user.append("id", empresas.get(clave).getId_Empresa()).append("nombre", empresas.get(clave).getNombre())
					.append("tamano", empresas.get(clave).getTamaño()).append("pais", empresas.get(clave).getPais())
					.append("capital", empresas.get(clave).getCapital())
					.append("director", empresas.get(clave).getDirector()).append("videojuegos", array);

			collection.deleteOne(user);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean EliminarDatosVideojuegos(String nombreContacto, String username) {

		try {
			BasicDBObject match = new BasicDBObject();
			match.put("id", username);

			BasicDBObject contact = new BasicDBObject();
			contact.put("nombre", nombreContacto);

			BasicDBObject update = new BasicDBObject();
			update.put("$pull", new BasicDBObject("videojuegos", contact));
			collection.updateOne(match, update);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// -------------Modificar Datos---------------
	public boolean modificarEmpresaMongo(String username, Empresas contacto, String nombreActual) {
		try {
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean modificarVideojuegoMongo(String username, Empresas contacto, String nombreActual) {
		try {
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
}
