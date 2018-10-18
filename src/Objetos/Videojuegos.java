package Objetos;

public class Videojuegos {

	private String id, nombre, tipo, empresa, creación;
	
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCreación() {
		return creación;
	}
	public void setCreación(String creación) {
		this.creación = creación;
	}
}
