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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Empresas> verMongoEmpresas(String username) {
        ArrayList<Empresas> empresa = new ArrayList<Empresas>();

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("nombre", username);
        for (Document document : collection.find(whereQuery)) {
            obj = (JSONObject) JSONValue.parse(document.toJson().toString());
            arr = (JSONArray) obj.get("contacto");

            for (int i = 0; i < arr.size(); i++) {
                empresas = new Empresas();
                JSONObject row = (JSONObject) arr.get(i);
                String id = row.get("id").toString();
                String nombre = row.get("nombre").toString();
                String tamano = row.get("tamano").toString();
                String pais = row.get("pais").toString();
                String capital = row.get("capital").toString();
                String director = row.get("director").toString();

                //metiendo los datos en el ArrayList
                empresas.setId_Empresa(Integer.parseInt(id));
                empresas.setNombre(nombre);
                empresas.setTamaño(tamano);
                empresas.setPais(pais);
                empresas.setCapital(Integer.parseInt(capital));
                empresas.setDirector(director);
                empresas.setCapital(Integer.parseInt(capital));
                empresa.add(empresas);
            }
        }
        return empresa;
    }

    public boolean guardarEmpresa(Empresas empresas, String username){
        try {
            BasicDBObject match = new BasicDBObject();
            match.put( "nombre", username);

            BasicDBObject contact = new BasicDBObject();
            contact.put( "nombre", contacto.getNombre());
            contact.put( "direccion", contacto.getDireccion());
            contact.put( "telefono", contacto.getTelefono());

            BasicDBObject update = new BasicDBObject();
            update.put( "$push", new BasicDBObject("contacto", contact));
            collection.updateOne( match, update );

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
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

    @Override
    public boolean borrarContactos(String username){
        try {
            ArrayList<Contacto> contactos = new ArrayList<Contacto>();
            contactos = crearListaContactos(username);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("nombre", username);
            for (Contacto contactin : contactos) {
                BasicDBObject contact = new BasicDBObject();
                contact.put( "nombre", contactin.getNombre());

                BasicDBObject update = new BasicDBObject();
                update.put( "$pull", new BasicDBObject("contacto", contact));
                collection.updateOne( whereQuery, update );
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificarContacto(String username, Contacto contacto, String nombreActual){
        try{
            eliminarContacto(nombreActual,username);
            guardarContacto(contacto,username);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
