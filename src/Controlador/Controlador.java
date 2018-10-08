package Controlador;

import java.util.HashMap;

import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Videojuegos;
import Vistas.AñadirBBDD;
import Vistas.Principal;
import Vistas.VerBBDD;
import Vistas.VerFichero;
import Vistas.AñadirFichero;
import interfas.Interface;

public class Controlador {

	private BaseDeDatos modelo;
	private Fichero fichero;
	
	private AñadirBBDD aBBDD;
	private VerBBDD verbbdd;
	private Principal principal;
	private VerFichero verfichero;
	private AñadirFichero aFichero;
	
	//pedida de datos de vista a modelo(base de datos)
	public HashMap<Integer, Videojuegos> EliminarDatosBaseDeDatos(Integer clave) {
		return modelo.EliminarDatos(modelo.LeerDatos(), clave);
	}
	public HashMap<Integer, Videojuegos> AñadirDatosBaseDeDatos(String id, String nombre, String tipo, String empresa, String Creacion) {
		return modelo.AnadirDatos(modelo.LeerDatos(), id, nombre, tipo, empresa, Creacion);
	}

	
	//pedida de datos de vista a modelo(fichero)
	public HashMap<Integer, Videojuegos> AñadirDatos(String id, String nombre, String tipo, String empresa, String creacion){
		return fichero.CopiarDatos(fichero.AnadirDatos(fichero.LeerDatos(), id, nombre, tipo, empresa, creacion));
	}
	public HashMap<Integer, Videojuegos> ObtenerDatosFichero() {
		return fichero.LeerDatos();
	}
	
	public HashMap<Integer, Videojuegos> EliminarDatosFichero(Integer clave) {
		return fichero.CopiarDatos(fichero.EliminarDatos(fichero.LeerDatos(), clave));
		
	}
	
	//intercambiar datos
	public void PasarDatosFichero() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatos();
		receptor.CopiarDatos(lista);
	}
	public void PasarDatosBaseDeDatos() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new Fichero();
		Interface receptor = new BaseDeDatos();
		lista = emisor.LeerDatos();
		receptor.CopiarDatos(lista);
	}
	
	//cambios de vista
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
	public void AñadirBase() {
		verbbdd.setVisible(false);
		aBBDD.setVisible(true);
	}
	public void AñadirFichero() {
		verfichero.setVisible(false);
		aFichero.setVisible(true);
	}
	public void VolverVerBase() {
		aBBDD.setVisible(false);
		verbbdd.setVisible(true);
	}
	public void VolverVerFichero() {
		aFichero.setVisible(false);
		verfichero.setVisible(true);
	}
	public void ComprobarFichero() {
		verbbdd.setVisible(false);
		verfichero.setVisible(true);
	}
	
	//setters
	public void setaBBDD(AñadirBBDD aBBDD) {
		this.aBBDD = aBBDD;
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
	public void setaFichero(AñadirFichero aFichero) {
		this.aFichero = aFichero;
	}
	
	
	
}
