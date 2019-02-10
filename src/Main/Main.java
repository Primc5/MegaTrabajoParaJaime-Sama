package Main;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import Controlador.Controlador;
import Hibernate.AccesHibernate;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Videojuegos;
import Vistas.AñadirBBDDEmpresas;
import Vistas.AñadirBBDDVideojuegos;
import Vistas.AñadirFicheroEmpresas;
import Vistas.AñadirFicheroVideojuegos;
import Vistas.AñadirHibernateEmpresas;
import Vistas.AñadirHibernateVideojuegos;
import Vistas.AñadirJsonEmpresas;
import Vistas.AñadirJsonVideojuegos;
import Vistas.AñadirMongoEmpresas;
import Vistas.AñadirMongoVideojuegos;
import Vistas.ModificarMongoEmpresas;
import Vistas.ModificarMongoVideojuegos;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.VerHibernate;
import Vistas.VerJson;
import Vistas.VerMongo;
import auxiliares.MontarTablas;
import json.AccesoJSONRemoto;
import mongo.MongoConection;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		BaseDeDatos baseDatos = new BaseDeDatos();
		Fichero fichero = new Fichero();
		AccesHibernate accesoh = new AccesHibernate();
		AccesoJSONRemoto accesoJ = new AccesoJSONRemoto();
		MongoConection mongo = new MongoConection();
		Controlador controlador = new Controlador();
		MontarTablas montarTablas = new MontarTablas();

		Principal prin = new Principal();
		VerBBDD verBase = new VerBBDD();
		AñadirBBDDVideojuegos abbddVideojuegos = new AñadirBBDDVideojuegos();
		AñadirFicheroVideojuegos aFicheroVideojuegos = new AñadirFicheroVideojuegos();
		AñadirFicheroEmpresas aFicheroEmpresas = new AñadirFicheroEmpresas();
		AñadirBBDDEmpresas abbddEmpresas = new AñadirBBDDEmpresas();
		AñadirHibernateVideojuegos ahibernateVideojuegos = new AñadirHibernateVideojuegos();
		AñadirHibernateEmpresas ahibernateEmpresas = new AñadirHibernateEmpresas();
		VerFichero verfichero = new VerFichero();
		VerHibernate verhibernate = new VerHibernate();
		VerJson verjson = new VerJson();
		AñadirJsonVideojuegos aJsonVideojuegos = new AñadirJsonVideojuegos();
		AñadirJsonEmpresas aJsonEmpresas = new AñadirJsonEmpresas();
		VerMongo verMongo = new VerMongo();
		AñadirMongoVideojuegos aMongoVideojuegos = new AñadirMongoVideojuegos();
		AñadirMongoEmpresas aMongoEmpresas = new AñadirMongoEmpresas();
		ModificarMongoVideojuegos vMongoVideojuegos = new ModificarMongoVideojuegos();
		ModificarMongoEmpresas vMongoEmpresas = new ModificarMongoEmpresas();

		vMongoEmpresas.setControlador(controlador);
		vMongoVideojuegos.setControlador(controlador);
		aMongoEmpresas.setControlador(controlador);
		aMongoVideojuegos.setControlador(controlador);
		ahibernateVideojuegos.setControlador(controlador);
		ahibernateEmpresas.setControlador(controlador);
		aFicheroVideojuegos.setControlador(controlador);
		aFicheroEmpresas.setControlador(controlador);
		verhibernate.setControlador(controlador);
		verhibernate.setModelo(accesoh);
		verBase.setModelo(baseDatos);
		verBase.setControlador(controlador);
		prin.setControlador(controlador);
		abbddVideojuegos.setModelo(baseDatos);
		abbddVideojuegos.setControlador(controlador);
		abbddEmpresas.setControlador(controlador);
		abbddEmpresas.setModelo(baseDatos);
		verfichero.setModelo(fichero);
		verfichero.setControlador(controlador);
		verfichero.setmTablas(montarTablas);
		aJsonVideojuegos.setControlador(controlador);
		aJsonEmpresas.setControlador(controlador);
		verjson.setControlador(controlador);
		verjson.setModelo(accesoJ);
		verjson.setmTablas(montarTablas);
		verMongo.setControlador(controlador);
		verMongo.setModelo(mongo);
		verMongo.setmTablas(montarTablas);

		controlador.setvMongoEmpresas(vMongoEmpresas);
		controlador.setvMongoVideojuegos(vMongoVideojuegos);
		controlador.setaMongoEmpresas(aMongoEmpresas);
		controlador.setaMongoVideojuegos(aMongoVideojuegos);
		controlador.setMongo(mongo);
		controlador.setVerMongo(verMongo);
		controlador.setVerhibernate(verhibernate);
		controlador.setHibernateEmpresas(ahibernateEmpresas);
		controlador.setHibernateVideojuegos(ahibernateVideojuegos);
		controlador.setFichero(fichero);
		controlador.setHibernate(accesoh);
		controlador.setModelo(baseDatos);
		controlador.setPrincipal(prin);
		controlador.setVerbbdd(verBase);
		controlador.setaBBDD(abbddVideojuegos);
		controlador.setaBBDDEmpresas(abbddEmpresas);
		controlador.setVerfichero(verfichero);
		controlador.setaFichero(aFicheroVideojuegos);
		controlador.setaFicheroEmpresas(aFicheroEmpresas);
		controlador.setaJsonEmpresas(aJsonEmpresas);
		controlador.setaJsonVideojuegos(aJsonVideojuegos);
		controlador.setVerjson(verjson);
		controlador.setJson(accesoJ);
		controlador.setMontarTablas(montarTablas);
	}
}