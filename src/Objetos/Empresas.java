package Objetos;

public class Empresas {

	private String Nombre, Tamaño, Pais, Director;
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

	public String getTamaño() {
		return Tamaño;
	}

	public void setTamaño(String tamaño) {
		Tamaño = tamaño;
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
