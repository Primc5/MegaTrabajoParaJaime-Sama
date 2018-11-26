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
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.VerHibernate;
import Vistas.VerJson;
import json.AccesoJSONRemoto;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		BaseDeDatos baseDatos = new BaseDeDatos();
		Fichero fichero = new Fichero();
		AccesHibernate accesoh = new AccesHibernate();
		AccesoJSONRemoto accesoJ = new AccesoJSONRemoto();
		Controlador controlador = new Controlador();
		
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
		AñadirJsonVideojuegos aJsonVideojuego = new AñadirJsonVideojuegos();
		AñadirJsonEmpresas aJsonEmpresas = new AñadirJsonEmpresas();
		
		
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
		aJsonVideojuego.setControlador(controlador);
		aJsonEmpresas.setControlador(controlador);
		verjson.setControlador(controlador);
		verjson.setModelo(accesoJ);
		

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
		
	}
}