package Main;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Videojuegos;
import Vistas.A�adirBBDD;
import Vistas.A�adirFichero;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		BaseDeDatos baseDatos = new BaseDeDatos();
		Fichero fichero = new Fichero();
		
		Controlador controlador = new Controlador();
		
		Principal prin = new Principal();
		VerBBDD verBase = new VerBBDD();
		A�adirBBDD abbdd = new A�adirBBDD();
		A�adirFichero aFichero = new A�adirFichero();

		VerFichero verfichero = new VerFichero();

		aFichero.setControlador(controlador);
		verBase.setModelo(baseDatos);
		verBase.setControlador(controlador);
		prin.setControlador(controlador);
		abbdd.setModelo(baseDatos);
		abbdd.setControlador(controlador);
		verfichero.setControlador(controlador);

		controlador.setFichero(fichero);
		controlador.setModelo(baseDatos);
		controlador.setPrincipal(prin);
		controlador.setVerbbdd(verBase);
		controlador.setaBBDD(abbdd);
		controlador.setVerfichero(verfichero);
		controlador.setaFichero(aFichero);
	}
}