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
import interfas.Interface;
import Vistas.VerHibernate;

public class Controlador {
	private AccesHibernate hibernate;
	private BaseDeDatos modelo;
	private Fichero fichero;
	
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
	
	//pedida de datos de vista a modelo(hibernate)
	public void VerDatosVideojuegosHibernate(){
		hibernate.MostrarDatos("LeerVideojuegos");
	}
	public void VerDatosEmpresasHibernate(){
		hibernate.MostrarDatosEmpresas("LeerEmpresa");
	}
	
	public HashMap<Integer, Empresas> A�adirDatosHibernateEmpresas(String id, String nombre, String tama�o, String pais, String capital, String director) {
		return hibernate.AnadirDatosEmpresas(hibernate.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director);
	}
	public HashMap<Integer, Videojuegos> A�adirDatosHibernateVideojuegos(String id, String nombre, String tipo, String empresa, String Creacion) {
		return hibernate.AnadirDatosVideojuegos(hibernate.LeerDatosVideojuegos(hibernate.LeerDatosEmpresas()), hibernate.LeerDatosEmpresas(), id, nombre, tipo, empresa, Creacion);
	}
	public void BorrarDatosHibernate() {
		hibernate.borrarDatos();
		try {
			hibernate.IntroducirDatos();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hibernate.cerrarSesion();
	}
	//pedida de datos de vista a modelo(base de datos)
	public HashMap<Integer, Videojuegos> EliminarDatosBaseDeDatosVideojuegos(Integer clave) {
		return modelo.EliminarDatosVideojuegos(modelo.LeerDatosVideojuegos(modelo.LeerDatosEmpresas()), clave);
	}
	public HashMap<Integer, Videojuegos> A�adirDatosBaseDeDatosVideojuegos(String id, String nombre, String tipo, String empresa, String Creacion) {
		return modelo.AnadirDatosVideojuegos(modelo.LeerDatosVideojuegos(modelo.LeerDatosEmpresas()), modelo.LeerDatosEmpresas(), id, nombre, tipo, empresa, Creacion);
	}
	
	public HashMap<Integer, Empresas> EliminarDatosBaseDeDatosEmpresas(Integer clave) {
		return modelo.EliminarDatosEmpresas(modelo.LeerDatosEmpresas(), clave);
	}
	public HashMap<Integer, Empresas> A�adirDatosBaseDeDatosEmpresas(String id, String nombre, String tama�o, String pais, String capital, String director) {
		return modelo.AnadirDatosEmpresas(modelo.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director);
	}

	
	//pedida de datos de vista a modelo(fichero)
	public void VerDatosVideojuegos(){
		fichero.MostrarDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()));
	}
	public void VerDatosEmpresas(){
		fichero.MostrarDatosEmpresas(fichero.LeerDatosEmpresas());
	}
	public HashMap<Integer, Videojuegos> A�adirDatosVideojuegos(String id, String nombre, String tipo, String empresa, String creacion){
		return fichero.CopiarDatosVideojuegos(fichero.AnadirDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()),fichero.LeerDatosEmpresas(), id, nombre, tipo, empresa, creacion),fichero.LeerDatosEmpresas());
	}
	public HashMap<Integer, Videojuegos> ObtenerDatosFicheroVideojuegos() {
		return fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
	}
	
	public HashMap<Integer, Videojuegos> EliminarDatosFicheroVideojuegos(Integer clave) {
		return fichero.CopiarDatosVideojuegos(fichero.EliminarDatosVideojuegos(fichero.LeerDatosVideojuegos(fichero.LeerDatosEmpresas()), clave),fichero.LeerDatosEmpresas());
		
	}
	
	public HashMap<Integer, Empresas> A�adirDatosEmpresas(String id, String nombre, String tama�o, String pais, String capital, String director){
		return fichero.CopiarDatosEmpresas(fichero.AnadirDatosEmpresas(fichero.LeerDatosEmpresas(), id, nombre, tama�o, pais, capital, director));
	}
	public HashMap<Integer, Empresas> ObtenerDatosFicheroEmpresas() {
		return fichero.LeerDatosEmpresas();
	}
	
	public HashMap<Integer, Empresas> EliminarDatosFicheroEmpresas(Integer clave) {
		return fichero.CopiarDatosEmpresas(fichero.EliminarDatosEmpresas(fichero.LeerDatosEmpresas(), clave));
		
	}
	
	//intercambiar datos
	public void PasarDatosFicheroVideojuegosHiberante() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista,fichero.LeerDatosEmpresas());
	}
	public void PasarDatosBBDDVideojuegosHiberante() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista,fichero.LeerDatosEmpresas());
	}
	public void PasarDatosFicheroVideojuegos() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new BaseDeDatos();
		Interface receptor = new Fichero();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista,fichero.LeerDatosEmpresas());
	}
	public void PasarDatosBaseDeDatosVideojuegos() {
		HashMap<Integer, Videojuegos> lista;
		
		Interface emisor = new Fichero();
		Interface receptor = new BaseDeDatos();
		lista = emisor.LeerDatosVideojuegos(fichero.LeerDatosEmpresas());
		receptor.CopiarDatosVideojuegos(lista,fichero.LeerDatosEmpresas());
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
	
	//cambios de vista
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
	public void ComprobarFichero() {
		verbbdd.setVisible(false);
		verfichero.setVisible(true);
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
	
	//setters
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
	
	
	
}
