package mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
        //Conexión con MongoDB
        try {
            mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDatabase("games");
            //database = (DB) mongoClient.getDatabase("games");
            collection = db.getCollection("games");
            //dbCollection = database.getCollection("games");
            System.out.println("connected");
            verMongoEmpresas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    
    //-------------Leer Datos---------------
    public ArrayList<Empresas> verMongoEmpresas() {
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
    
    public ArrayList<Videojuegos> verMongoVideojuegos() {
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
                String creacionGame = row.get("creacion").toString();

                //metiendo los datos en el ArrayList
                videojuegos.setId(Integer.parseInt(idGame));
                videojuegos.setNombre(nombreGame);
                videojuegos.setTipo(tipoGame);
                videojuegos.setCreación(creacionGame);
                videojuego.add(videojuegos);
            }
        }
        return videojuego;
    }

    
    
    //-------------Insertar Datos---------------
    public boolean guardarEmpresa(Empresas empresas, String username){
        try {
            BasicDBObject match = new BasicDBObject();
            match.put( "nombre", username);

            BasicDBObject contact = new BasicDBObject();
            contact.put( "nombre", empresas.getNombre());

            BasicDBObject update = new BasicDBObject();
            update.put( "$push", new BasicDBObject("contacto", contact));
            collection.updateOne( match, update );

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    
    //-------------Eliminar Datos---------------
    public boolean eliminarContacto(String nombreContacto, String username){

        try {
            BasicDBObject match = new BasicDBObject();
            match.put( "nombre", username);

            BasicDBObject contact = new BasicDBObject();
            contact.put( "nombre", nombreContacto);

            BasicDBObject update = new BasicDBObject();
            update.put( "$pull", new BasicDBObject("contacto", contact));
            collection.updateOne( match, update );

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    
    //-------------Modificar Datos---------------
    public boolean modificarContacto(String username, Empresas contacto, String nombreActual){
        try{
            eliminarContacto(nombreActual,username);
            guardarEmpresa(contacto,username);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
