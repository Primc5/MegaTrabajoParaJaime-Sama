package Controlador;

import java.text.ParseException;
import java.util.HashMap;

import Hibernate.AccesHibernate;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Empresas;
import Objetos.Videojuegos;
import Vistas.A�adirBBDDEmpresas;
import Vistas.A�adirBBDDVideojuegos;
import Vistas.A�adirFicheroEmpresas;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.A�adirFicheroVideojuegos;
import Vistas.A�adirHibernateEmpresas;
import Vistas.A�adirHibernateVideojuegos;
import Vistas.A�adirJsonEmpresas;
import Vistas.A�adirJsonVideojuegos;
import Vistas.A�adirMongoEmpresas;
import Vistas.A�adirMongoVideojuegos;
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
	private A�adirMongoVideojuegos aMongoVideojuegos;
	private A�adirMongoEmpresas aMongoEmpresas;
	private VerJson verjson;
	private A�adirJsonVideojuegos aJsonVideojuegos;
	private A�adirJsonEmpresas aJsonEmpresas;
	private VerHibernate verhibernate;
	private A�adirHibernateVideojuegos aHibernateVideojuegos;
	private A�adirHibernateEmpresas aHibernateEmpresas;
	private A�adirBBDDVideojuegos aBBDDVideojuegos;
	private A�adirBBDDEmpresas aBBDDEmpresas;
	private VerBBDD verbbdd;
	private Principal principal;
	private VerFichero verfichero;
	private A�adirFicheroVideojuegos aFicheroVideojuegos;
	private A�adirFicheroEmpresas aFicheroEmpresas;
	private MontarTablas montarTablas;

	// pedida de datos de vista a modelo(mongoDB)
	public void VerDatosVideojuegosMongo() {
		montarTablas.MostrarDatosVideojuegosMongo(mongo.LeerDatosVideojuegos(mongo.LeerDatosEmpresas()));
	}

	public void VerDatosEmpresasMongo() {
		montarTablas.MostrarDatosEmpresasMongo(mongo.LeerDatosEmpresas());
	}

	public boolean A�adirDatosVideojuegosMongo(String id, String nombre, String tipo,
			String empresa, String creacion) {
		return mongo.AnadirDatosVideojuegos(id, nombre, tipo, creacion, empresa);
	}

	public boolean A�adirDatosEmpresasMongo(String id, String nombre, String tama�o, String pais,
			String capital, String director) {
		return mongo.AnadirDatosEmpresas(id, nombre, tama�o, pais, capital, director);
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

	public HashMap<Integer, Videojuegos> A�adirDatosVideojuegosJson(String id, String nombre, String tipo,
			String empresa, String creacion) {
		return json.AnadirDatosVideojuegos(json.LeerDatosVideojuegos(json.LeerDatosEmpresas()),
				json.LeerDatosEmpresas(), id, nombre, tipo, empresa, creacion);
	}

	public HashMap<Integer, Empresas> A�adirDatosEmpresasJson(String id, String nombre, String tama�o, String pais,
			String capital, String director) {
		return json.AnadirDatosEmpresas(json.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director);
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

	public HashMap<Integer, Empresas> A�adirDatosHibernateEmpresas(String id, String nombre, String tama�o, String pais,
			String capital, String director) {
		return hibernate.AnadirDatosEmpresas(hibernate.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital,
				director);
	}

	public HashMap<Integer, Videojuegos> A�adirDatosHibernateVideojuegos(String id, String nombre, String tipo,
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

	public HashMap<Integer, Videojuegos> A�adirDatosBaseDeDatosVideojuegos(String id, String nombre, String tipo,
			String empresa, String Creacion) {
		return modelo.AnadirDatosVideojuegos(modelo.LeerDatosVideojuegos(modelo.LeerDatosEmpresas()),
				modelo.LeerDatosEmpresas(), id, nombre, tipo, empresa, Creacion);
	}

	public HashMap<Integer, Empresas> EliminarDatosBaseDeDatosEmpresas(Integer clave) {
		return modelo.EliminarDatosEmpresas(modelo.LeerDatosEmpresas(), clave);
	}

	public HashMap<Integer, Empresas> A�adirDatosBaseDeDatosEmpresas(String id, String nombre, String tama�o,
			String pais, String capital, String director) {
		return modelo.AnadirDatosEmpresas(modelo.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director);
	}

	// pedida de datos de vista a modelo(fichero)
	public void VerDatosVideojuegos() {
		montarTablas.MostrarDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()));
	}

	public void VerDatosEmpresas() {
		montarTablas.MostrarDatosEmpresas(fichero.LeerDatosEmpresas());
	}

	public HashMap<Integer, Videojuegos> A�adirDatosVideojuegos(String id, String nombre, String tipo, String empresa,
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

	public HashMap<Integer, Empresas> A�adirDatosEmpresas(String id, String nombre, String tama�o, String pais,
			String capital, String director) {
		return fichero.CopiarDatosEmpresas(
				fichero.AnadirDatosEmpresas(fichero.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director));
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

	public void A�adirMongoVideojuegos() {
		verhibernate.setVisible(false);
		aMongoVideojuegos.setVisible(true);
	}

	public void VolverA�adirMongoVideojuegos() {
		aMongoVideojuegos.setVisible(false);
		verMongo.setVisible(true);
	}

	public void A�adirMongoEmpresas() {
		verhibernate.setVisible(false);
		aMongoEmpresas.setVisible(true);
	}

	public void VolverA�adirMongoEmpresass() {
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

	public void A�adirHibernateVideojuegos() {
		verhibernate.setVisible(false);
		aHibernateVideojuegos.setVisible(true);
	}

	public void VolverA�adirHibernateVideojuegos() {
		aHibernateVideojuegos.setVisible(false);
		verhibernate.setVisible(true);
	}

	public void A�adirHibernateEmpresas() {
		verhibernate.setVisible(false);
		aHibernateEmpresas.setVisible(true);
	}

	public void VolverA�adirHibernateEmpresas() {
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

	public void A�adirBase() {
		verbbdd.setVisible(false);
		aBBDDVideojuegos.setVisible(true);
	}

	public void A�adirFichero() {
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

	public void A�adirEmpresaBBDD() {
		verbbdd.setVisible(false);
		aBBDDEmpresas.setVisible(true);
	}

	public void VolverA�adirEmpresaBBDD() {
		aBBDDEmpresas.setVisible(false);
		verbbdd.setVisible(true);
	}

	public void A�adirEmpresaFichero() {
		verfichero.setVisible(false);
		aFicheroEmpresas.setVisible(true);
	}

	public void VolverA�adirEmpresaFichero() {
		aFicheroEmpresas.setVisible(false);
		verfichero.setVisible(true);
	}

	// setters
	public void setaBBDD(A�adirBBDDVideojuegos aBBDD) {
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

	public void setaFichero(A�adirFicheroVideojuegos aFichero) {
		this.aFicheroVideojuegos = aFichero;
	}

	public void setaBBDDEmpresas(A�adirBBDDEmpresas aBBDDEmpresas) {
		this.aBBDDEmpresas = aBBDDEmpresas;
	}

	public void setaFicheroEmpresas(A�adirFicheroEmpresas aFicheroEmpresas) {
		this.aFicheroEmpresas = aFicheroEmpresas;
	}

	public void setVerhibernate(VerHibernate verhibernate) {
		this.verhibernate = verhibernate;
	}

	public void setHibernateVideojuegos(A�adirHibernateVideojuegos aHibernateVideojuegos) {
		this.aHibernateVideojuegos = aHibernateVideojuegos;
	}

	public void setHibernateEmpresas(A�adirHibernateEmpresas aHibernateEmpresas) {
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

	public void setaJsonVideojuegos(A�adirJsonVideojuegos aJsonVideojuegos) {
		this.aJsonVideojuegos = aJsonVideojuegos;
	}

	public void setaJsonEmpresas(A�adirJsonEmpresas aJsonEmpresas) {
		this.aJsonEmpresas = aJsonEmpresas;
	}

	public void setMongo(MongoConection mongo) {
		this.mongo = mongo;
	}

	public void setVerMongo(VerMongo verMongo) {
		this.verMongo = verMongo;
	}

	public void setaMongoVideojuegos(A�adirMongoVideojuegos aMongoVideojuegos) {
		this.aMongoVideojuegos = aMongoVideojuegos;
	}

	public void setaMongoEmpresas(A�adirMongoEmpresas aMongoEmpresas) {
		this.aMongoEmpresas = aMongoEmpresas;
	}

	public void setMontarTablas(MontarTablas montarTablas) {
		this.montarTablas = montarTablas;
	}

}
