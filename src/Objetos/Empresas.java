package Objetos;

public class Empresas {

	private String Nombre, Tama�o, Pais, Director;
	private int id_Empresa, Capital;

	public Empresas() {
		
	}
	
	public int getId_Empresa() {
		return id_Empresa;
	}

	public void setId_Empresa(int id_Empresa) {
		this.id_Empresa = id_Empresa;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTama�o() {
		return Tama�o;
	}

	public void setTama�o(String tama�o) {
		Tama�o = tama�o;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public int getCapital() {
		return Capital;
	}

	public void setCapital(int capital) {
		Capital = capital;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}
	
}
