package Objetos;

import java.util.Date;

public class Videojuegos {

	private String nombre, tipo, creación;
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
	public String getCreación() {
		return creación;
	}
	public void setCreación(String creacion) {
		this.creación = creacion;
	}
}
