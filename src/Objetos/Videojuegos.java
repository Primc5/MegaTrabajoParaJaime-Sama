package Objetos;

import java.util.Date;

public class Videojuegos {

	private String nombre, tipo, creaci�n;
	private int id;
	private Empresas empresa;
	
	public Videojuegos() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Empresas getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}
	public String getCreaci�n() {
		return creaci�n;
	}
	public void setCreaci�n(String creacion) {
		this.creaci�n = creacion;
	}
}
