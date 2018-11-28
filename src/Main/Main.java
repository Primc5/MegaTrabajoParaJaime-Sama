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
import Vistas.A�adirBBDDEmpresas;
import Vistas.A�adirBBDDVideojuegos;
import Vistas.A�adirFicheroEmpresas;
import Vistas.A�adirFicheroVideojuegos;
import Vistas.A�adirHibernateEmpresas;
import Vistas.A�adirHibernateVideojuegos;
import Vistas.A�adirJsonEmpresas;
import Vistas.A�adirJsonVideojuegos;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.VerHibernate;
import Vistas.VerJson;
import auxiliares.MontarTablas;
import json.AccesoJSONRemoto;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		BaseDeDatos baseDatos = new BaseDeDatos();
		Fichero fichero = new Fichero();
		AccesHibernate accesoh = new AccesHibernate();
		AccesoJSONRemoto accesoJ = new AccesoJSONRemoto();
		Controlador controlador = new Controlador();
		MontarTablas montarTablas = new MontarTablas();

		Principal prin = new Principal();
		VerBBDD verBase = new VerBBDD();
		A�adirBBDDVideojuegos abbddVideojuegos = new A�adirBBDDVideojuegos();
		A�adirFicheroVideojuegos aFicheroVideojuegos = new A�adirFicheroVideojuegos();
		A�adirFicheroEmpresas aFicheroEmpresas = new A�adirFicheroEmpresas();
		A�adirBBDDEmpresas abbddEmpresas = new A�adirBBDDEmpresas();
		A�adirHibernateVideojuegos ahibernateVideojuegos = new A�adirHibernateVideojuegos();
		A�adirHibernateEmpresas ahibernateEmpresas = new A�adirHibernateEmpresas();
		VerFichero verfichero = new VerFichero();
		VerHibernate verhibernate = new VerHibernate();
		VerJson verjson = new VerJson();
		A�adirJsonVideojuegos aJsonVideojuegos = new A�adirJsonVideojuegos();
		A�adirJsonEmpresas aJsonEmpresas = new A�adirJsonEmpresas();

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