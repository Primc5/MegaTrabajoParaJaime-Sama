package Controlador;

import java.text.ParseException;
import java.util.HashMap;

import Hibernate.AccesHibernate;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Empresas;
import Objetos.Videojuegos;
import Vistas.AñadirBBDDEmpresas;
import Vistas.AñadirBBDDVideojuegos;
import Vistas.AñadirFicheroEmpresas;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.AñadirFicheroVideojuegos;
import Vistas.AñadirHibernateEmpresas;
import Vistas.AñadirHibernateVideojuegos;
import Vistas.AñadirJsonEmpresas;
import Vistas.AñadirJsonVideojuegos;
import Vistas.AñadirMongoEmpresas;
import Vistas.AñadirMongoVideojuegos;
import json.AccesoJSONRemoto;
import mongo.MongoConection;
import Vistas.VerHibernate;
import Vistas.VerJson;
import Vistas.VerMongo;
import auxiliares.MontarTablas;
import interfaz.Interface;

public class Controlador {
	private AccesHibernate hibernate;
	private BaseDeDatos modelo;
	private Fichero fichero;
	private AccesoJSONRemoto json;
	private MongoConection mongo;

	private VerMongo verMongo;
	private AñadirMongoVideojuegos aMongoVideojuegos;
	private AñadirMongoEmpresas aMongoEmpresas;
	private VerJson verjson;
	private AñadirJsonVideojuegos aJsonVideojuegos;
	private AñadirJsonEmpresas aJsonEmpresas;
	private VerHibernate verhibernate;
	private AñadirHibernateVideojuegos aHibernateVideojuegos;
	private AñadirHibernateEmpresas aHibernateEmpresas;
	private AñadirBBDDVideojuegos aBBDDVideojuegos;
	private AñadirBBDDEmpresas aBBDDEmpresas;
	private VerBBDD verbbdd;
	private Principal principal;
	private VerFichero verfichero;
	private AñadirFicheroVideojuegos aFicheroVideojuegos;
	private AñadirFicheroEmpresas aFicheroEmpresas;
	private MontarTablas montarTablas;

	// pedida de datos de vista a modelo(mongoDB)
	public void VerDatosVideojuegosMongo() {
		montarTablas.MostrarDatosVideojuegosMongo(mongo.LeerDatosVideojuegos(mongo.LeerDatosEmpresas()));
	}

	public void VerDatosEmpresasMongo() {
		montarTablas.MostrarDatosEmpresasMongo(mongo.LeerDatosEmpresas());
	}

	public boolean AñadirDatosVideojuegosMongo(String id, String nombre, String tipo,
			String empresa, String creacion) {
		return mongo.AnadirDatosVideojuegos(id, nombre, tipo, creacion, empresa);
	}

	public boolean AñadirDatosEmpresasMongo(String id, String nombre, String tamaño, String pais,
			String capital, String director) {
		return mongo.AnadirDatosEmpresas(id, nombre, tamaño, pais, capital, director);
	}

	public boolean EliminarDatosVideojuegosMongo(String nombre, String clave) {
		return mongo.EliminarDatosVideojuegos(nombre, clave);
	}

	public boolean EliminarDatosEmpresasMongo(String nombre, String clave) {
		return mongo.EliminarDatosEmpresas(mongo.LeerDatosEmpresas(), clave);

	}

	// pedida de datos de vista a modelo(json)
	public void VerDatosVideojuegosJSon() {
		montarTablas.MostrarDatosVideojuegos(json.LeerDatosVideojuegos(json.LeerDatosEmpresas()));
	}

	public void VerDatosEmpresasJSon() {
		montarTablas.MostrarDatosEmpresas(json.LeerDatosEmpresas());
	}

	public HashMap<Integer, Videojuegos> AñadirDatosVideojuegosJson(String id, String nombre, String tipo,
			String empresa, String creacion) {
		return json.AnadirDatosVideojuegos(json.LeerDatosVideojuegos(json.LeerDatosEmpresas()),
				json.LeerDatosEmpresas(), id, nombre, tipo, empresa, creacion);
	}

	public HashMap<Integer, Empresas> AñadirDatosEmpresasJson(String id, String nombre, String tamaño, String pais,
			String capital, String director) {
		return json.AnadirDatosEmpresas(json.LeerDatosEmpresas(), id, nombre, tamaño, pais, capital, director);
	}

	public HashMap<Integer, Videojuegos> EliminarDatosVideojuegosJson(Integer clave) {
		return json.EliminarDatosVideojuegos(json.LeerDatosVideojuegos(json.LeerDatosEmpresas()), clave);
	}

	public HashMap<Integer, Empresas> EliminarDatosEmpresasJson(Integer clave) {
		return json.EliminarDatosEmpresas(json.LeerDatosEmpresas(), clave);

	}

	// pedida de datos de vista a modelo(hibernate)
	public void VerDatosVideojuegosHibernate() {
		hibernate.MostrarDatos("LeerVideojuegos");
	}

	public void VerDatosEmpresasHibernate() {
		hibernate.MostrarDatosEmpresas("LeerEmpresa");
	}

	public HashMap<Integer, Empresas> AñadirDatosHibernateEmpresas(String id, String nombre, String tamaño, String pais,
			String capital, String director) {
		return hibernate.AnadirDatosEmpresas(hibernate.LeerDatosEmpresas(), id, nombre, tamaño, pais, capital,
				director);
	}

	public HashMap<Integer, Videojuegos> AñadirDatosHibernateVideojuegos(String id, String nombre, String tipo,
			String empresa, String Creacion) {
		return hibernate.AnadirDatosVideojuegos(hibernate.LeerDatosVideojuegos(hibernate.LeerDatosEmpresas()),
				hibernate.LeerDatosEmpresas(), id, nombre, tipo, empresa, Creacion);
	}

	public void BorrarDatosHibernate() {
		hibernate.borrarDatos();
		hibernate.cerrarSesion();
	}

	// pedida de datos de vista a modelo(base de datos)
	public HashMap<Integer, Videojuegos> EliminarDatosBaseDeDatosVideojuegos(Integer clave) {
		return modelo.EliminarDatosVideojuegos(modelo.LeerDatosVideojuegos(modelo.LeerDatosEmpresas()), clave);
	}

	public HashMap<Integer, Videojuegos> AñadirDatosBaseDeDatosVideojuegos(String id, String nombre, String tipo,
			String empresa, String Creacion) {
		return modelo.AnadirDatosVideojuegos(modelo.LeerDatosVideojuegos(modelo.LeerDatosEmpresas()),
				modelo.LeerDatosEmpresas(), id, nombre, tipo, empresa, Creacion);
	}

	public HashMap<Integer, Empresas> EliminarDatosBaseDeDatosEmpresas(Integer clave) {
		return modelo.EliminarDatosEmpresas(modelo.LeerDatosEmpresas(), clave);
	}

	public HashMap<Integer, Empresas> AñadirDatosBaseDeDatosEmpresas(String id, String nombre, String tamaño,
			String pais, String capital, String director) {
		return modelo.AnadirDatosEmpresas(modelo.LeerDatosEmpresas(), id, nombre, tamaño, pais, capital, director);
	}

	// pedida de datos de vista a modelo(fichero)
	public void VerDatosVideojuegos() {
		montarTablas.MostrarDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()));
	}

	public void VerDatosEmpresas() {
		montarTablas.MostrarDatosEmpresas(fichero.LeerDatosEmpresas());
	}

	public HashMap<Integer, Videojuegos> AñadirDatosVideojuegos(String id, String nombre, String tipo, String empresa,
			String creacion) {
		return fichero
				.CopiarDatosVideojuegos(
						fichero.AnadirDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()),
								fichero.LeerDatosEmpresas(), id, nombre, tipo, empresa, creacion),
						fichero.LeerDatosEmpresas());
	}

	public HashMap<Integer, Videojuegos> ObtenerDatosFicheroVideojuegos() {
		return fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
	}

	public HashMap<Integer, Videojuegos> EliminarDatosFicheroVideojuegos(Integer clave) {
		return fichero.CopiarDatosVideojuegos(
				fichero.EliminarDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()), clave),
				fichero.LeerDatosEmpresas());
	}

	public HashMap<Integer, Empresas> AñadirDatosEmpresas(String id, String nombre, String tamaño, String pais,
			String capital, String director) {
		return fichero.CopiarDatosEmpresas(
				fichero.AnadirDatosEmpresas(fichero.LeerDatosEmpresas(), id, nombre, tamaño, pais, capital, director));
	}

	public HashMap<Integer, Empresas> ObtenerDatosFicheroEmpresas() {
		return fichero.LeerDatosEmpresas();
	}

	public HashMap<Integer, Empresas> EliminarDatosFicheroEmpresas(Integer clave) {
		return fichero.CopiarDatosEmpresas(fichero.EliminarDatosEmpresas(fichero.LeerDatosEmpresas(), clave));

	}

	// intercambiar datos
	public void PasarDatosJsonAHibernateVideojuegos() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new AccesoJSONRemoto();
		Interface receptor = new AccesHibernate();
		lista = emisor.LeerDatosVideojuegos(json.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, json.LeerDatosEmpresas());
	}

	public void PasarDatosJsonAFicheroVideojuegos() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new AccesoJSONRemoto();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(json.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, json.LeerDatosEmpresas());
	}

	public void PasarDatosFicheroVideojuegosHiberante() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, fichero.LeerDatosEmpresas());
	}

	public void PasarDatosBBDDVideojuegosHiberante() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, fichero.LeerDatosEmpresas());
	}

	public void PasarDatosFicheroVideojuegos() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, fichero.LeerDatosEmpresas());
	}

	public void PasarDatosBaseDeDatosVideojuegos() {
		HashMap<Integer, Videojuegos> lista;

		Interface emisor = new Fichero();
		Interface receptor = new BaseDeDatos();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista, fichero.LeerDatosEmpresas());
	}

	public void PasarDatosJsonAHibernateEmpresas() {
		HashMap<Integer, Empresas> lista;

		Interface emisor = new AccesoJSONRemoto();
		Interface receptor = new AccesHibernate();
		lista = emisor.LeerDatosEmpresas();
		receptor.CopiarDatosEmpresas(lista);
	}

	public void PasarDatosJsonAFicheroEmpresas() {
		HashMap<Integer, Empresas> lista;

		Interface emisor = new AccesoJSONRemoto();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosEmpresas();
		receptor.CopiarDatosEmpresas(lista);
	}

	public void PasarDatosFicheroEmpresas() {
		HashMap<Integer, Empresas> lista;

		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosEmpresas();
		receptor.CopiarDatosEmpresas(lista);
	}

	public void PasarDatosBaseDeDatosEmpresas() {
		HashMap<Integer, Empresas> lista;

		Interface emisor = new Fichero();
		Interface receptor = new BaseDeDatos();
		lista = emisor.LeerDatosEmpresas();
		receptor.CopiarDatosEmpresas(lista);
	}

	// cambios de vista

	public void VerMongo() {
		principal.setVisible(false);
		verMongo.setVisible(true);
	}

	public void VolverVerMongo() {
		verMongo.setVisible(false);
		principal.setVisible(true);
	}

	public void AñadirMongoVideojuegos() {
		verhibernate.setVisible(false);
		aMongoVideojuegos.setVisible(true);
	}

	public void VolverAñadirMongoVideojuegos() {
		aMongoVideojuegos.setVisible(false);
		verMongo.setVisible(true);
	}

	public void AñadirMongoEmpresas() {
		verhibernate.setVisible(false);
		aMongoEmpresas.setVisible(true);
	}

	public void VolverAñadirMongoEmpresass() {
		aMongoEmpresas.setVisible(false);
		verMongo.setVisible(true);
	}

	public void VerJson() {
		principal.setVisible(false);
		verjson.setVisible(true);
	}

	public void VerHibernate() {
		principal.setVisible(false);
		verhibernate.setVisible(true);
	}

	public void VolverVerHibernate() {
		verhibernate.setVisible(false);
		principal.setVisible(true);
	}

	public void AñadirHibernateVideojuegos() {
		verhibernate.setVisible(false);
		aHibernateVideojuegos.setVisible(true);
	}

	public void VolverAñadirHibernateVideojuegos() {
		aHibernateVideojuegos.setVisible(false);
		verhibernate.setVisible(true);
	}

	public void AñadirHibernateEmpresas() {
		verhibernate.setVisible(false);
		aHibernateEmpresas.setVisible(true);
	}

	public void VolverAñadirHibernateEmpresas() {
		aHibernateEmpresas.setVisible(false);
		verhibernate.setVisible(true);
	}

	public void ComprobarBaseDeDatos() {
		verfichero.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void VerBase() {
		principal.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void VerFichero() {
		principal.setVisible(false);
		verfichero.setVisible(true);
	}

	public void VolverPrincipal() {
		verbbdd.setVisible(false);
		principal.setVisible(true);
	}

	public void VolverPrincipal2() {
		verfichero.setVisible(false);
		principal.setVisible(true);
	}

	public void VolverPrincipal3() {
		verhibernate.setVisible(false);
		principal.setVisible(true);
	}

	public void VolverPrincipal4() {
		verjson.setVisible(false);
		principal.setVisible(true);
	}
	
	public void VolverPrincipal5() {
		verMongo.setVisible(false);
		principal.setVisible(true);
	}

	public void AñadirBase() {
		verbbdd.setVisible(false);
		aBBDDVideojuegos.setVisible(true);
	}

	public void AñadirFichero() {
		verfichero.setVisible(false);
		aFicheroVideojuegos.setVisible(true);
	}

	public void VolverVerBase() {
		aBBDDVideojuegos.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void VolverVerFichero() {
		aFicheroVideojuegos.setVisible(false);
		verfichero.setVisible(true);
	}

	public void VolverAnnadirVideojuegosJson() {
		aJsonVideojuegos.setVisible(false);
		verjson.setVisible(true);
	}

	public void VolverAnnadirEmpresasJson() {
		aJsonEmpresas.setVisible(false);
		verjson.setVisible(true);
	}
	
	public void VolverAnnadirEmpresasMongo() {
		aMongoEmpresas.setVisible(false);
		verMongo.setVisible(true);
	}
	public void VolverAnnadirVideojuegosMongo() {
		aMongoVideojuegos.setVisible(false);
		verMongo.setVisible(true);
	}

	public void AnnadirVideojuegosJson() {
		verjson.setVisible(false);
		aJsonVideojuegos.setVisible(true);
	}

	public void AnnadirEmpresasJson() {
		verjson.setVisible(false);
		aJsonEmpresas.setVisible(true);
	}
	
	public void AnnadirVideojuegosMongo() {
		verMongo.setVisible(false);
		aMongoVideojuegos.setVisible(true);
	}

	public void AnnadirEmpresasMongo() {
		verMongo.setVisible(false);
		aMongoEmpresas.setVisible(true);
	}

	public void ComprobarFichero() {
		verbbdd.setVisible(false);
		verfichero.setVisible(true);
	}

	public void ComprobarFicheroJson() {
		verjson.setVisible(false);
		verfichero.setVisible(true);
	}

	public void ComprobarBBDDJson() {
		verjson.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void ComprobarHibernateJson() {
		verjson.setVisible(false);
		verhibernate.setVisible(true);
	}

	public void AñadirEmpresaBBDD() {
		verbbdd.setVisible(false);
		aBBDDEmpresas.setVisible(true);
	}

	public void VolverAñadirEmpresaBBDD() {
		aBBDDEmpresas.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void AñadirEmpresaFichero() {
		verfichero.setVisible(false);
		aFicheroEmpresas.setVisible(true);
	}

	public void VolverAñadirEmpresaFichero() {
		aFicheroEmpresas.setVisible(false);
		verfichero.setVisible(true);
	}

	// setters
	public void setaBBDD(AñadirBBDDVideojuegos aBBDD) {
		this.aBBDDVideojuegos = aBBDD;
	}

	public void setModelo(BaseDeDatos modelo) {
		this.modelo = modelo;
	}

	public void setVerbbdd(VerBBDD verbbdd) {
		this.verbbdd = verbbdd;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public void setVerfichero(VerFichero verfichero) {
		this.verfichero = verfichero;
	}

	public void setFichero(Fichero fichero) {
		this.fichero = fichero;
	}

	public void setaFichero(AñadirFicheroVideojuegos aFichero) {
		this.aFicheroVideojuegos = aFichero;
	}

	public void setaBBDDEmpresas(AñadirBBDDEmpresas aBBDDEmpresas) {
		this.aBBDDEmpresas = aBBDDEmpresas;
	}

	public void setaFicheroEmpresas(AñadirFicheroEmpresas aFicheroEmpresas) {
		this.aFicheroEmpresas = aFicheroEmpresas;
	}

	public void setVerhibernate(VerHibernate verhibernate) {
		this.verhibernate = verhibernate;
	}

	public void setHibernateVideojuegos(AñadirHibernateVideojuegos aHibernateVideojuegos) {
		this.aHibernateVideojuegos = aHibernateVideojuegos;
	}

	public void setHibernateEmpresas(AñadirHibernateEmpresas aHibernateEmpresas) {
		this.aHibernateEmpresas = aHibernateEmpresas;
	}

	public void setHibernate(AccesHibernate hibernate) {
		this.hibernate = hibernate;
	}

	public void setJson(AccesoJSONRemoto json) {
		this.json = json;
	}

	public void setVerjson(VerJson verjson) {
		this.verjson = verjson;
	}

	public void setaJsonVideojuegos(AñadirJsonVideojuegos aJsonVideojuegos) {
		this.aJsonVideojuegos = aJsonVideojuegos;
	}

	public void setaJsonEmpresas(AñadirJsonEmpresas aJsonEmpresas) {
		this.aJsonEmpresas = aJsonEmpresas;
	}

	public void setMongo(MongoConection mongo) {
		this.mongo = mongo;
	}

	public void setVerMongo(VerMongo verMongo) {
		this.verMongo = verMongo;
	}

	public void setaMongoVideojuegos(AñadirMongoVideojuegos aMongoVideojuegos) {
		this.aMongoVideojuegos = aMongoVideojuegos;
	}

	public void setaMongoEmpresas(AñadirMongoEmpresas aMongoEmpresas) {
		this.aMongoEmpresas = aMongoEmpresas;
	}

	public void setMontarTablas(MontarTablas montarTablas) {
		this.montarTablas = montarTablas;
	}

}
