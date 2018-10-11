package Main;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Videojuegos;
import Vistas.A�adirBBDDEmpresas;
import Vistas.A�adirBBDDVideojuegos;
import Vistas.A�adirFicheroEmpresas;
import Vistas.A�adirFicheroVideojuegos;
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
		A�adirBBDDVideojuegos abbddVideojuegos = new A�adirBBDDVideojuegos();
		A�adirFicheroVideojuegos aFicheroVideojuegos = new A�adirFicheroVideojuegos();
		A�adirFicheroEmpresas aFicheroEmpresas = new A�adirFicheroEmpresas();
		A�adirBBDDEmpresas abbddEmpresas = new A�adirBBDDEmpresas();
		VerFichero verfichero = new VerFichero();

		aFicheroVideojuegos.setControlador(controlador);
		aFicheroEmpresas.setControlador(controlador);
		verBase.setModelo(baseDatos);
		verBase.setControlador(controlador);
		prin.setControlador(controlador);
		abbddVideojuegos.setModelo(baseDatos);
		abbddVideojuegos.setControlador(controlador);
		abbddEmpresas.setControlador(controlador);
		abbddEmpresas.setModelo(baseDatos);
		verfichero.setModelo(fichero);
		verfichero.setControlador(controlador);

		controlador.setFichero(fichero);
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