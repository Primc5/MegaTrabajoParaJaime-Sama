package Objetos;

public class Videojuegos {

	private String id, nombre, tipo, creaci�n;
	private Empresas empresa;
	
	public Videojuegos() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public void setCreaci�n(String creaci�n) {
		this.creaci�n = creaci�n;
	}
}
