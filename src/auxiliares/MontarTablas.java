package auxiliares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import Objetos.Empresas;
import Objetos.Videojuegos;

public class MontarTablas {

	private DefaultTableModel miTabla;
	
	//Montado de tablas con base de datos irrelacionales
	public void MostrarDatosVideojuegosMongo(ArrayList<Videojuegos> datos) {
		int numColumnas = 5;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id";
		cabecera[1] = "nombre";
		cabecera[2] = "tipo";
		cabecera[3] = "Empresa";
		cabecera[4] = "Creacion";
		int fila = 0, col;
		for (int i = 0; i < datos.size(); i++) {
			col = 0;
			contenido[fila][col] = datos.get(i).getId();
			col++;
			contenido[fila][col] = datos.get(i).getNombre();
			col++;
			contenido[fila][col] = datos.get(i).getTipo();
			col++;
			contenido[fila][col] = datos.get(i).getEmpresa().getId_Empresa();
			col++;
			contenido[fila][col] = datos.get(i).getCreación();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}
	public void MostrarDatosEmpresasMongo(ArrayList<Empresas> datos) {
		int numColumnas = 6;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id_Empresa";
		cabecera[1] = "Nombre";
		cabecera[2] = "Tamaño";
		cabecera[3] = "Pais";
		cabecera[4] = "Capital";
		cabecera[5] = "Director";
		int fila = 0, col;
		for (int i = 0; i < datos.size(); i++) {
			col = 0;
			contenido[fila][col] = datos.get(i).getId_Empresa();
			col++;
			contenido[fila][col] = datos.get(i).getNombre();
			col++;
			contenido[fila][col] = datos.get(i).getTamaño();
			col++;
			contenido[fila][col] = datos.get(i).getPais();
			col++;
			contenido[fila][col] = datos.get(i).getCapital();
			col++;
			contenido[fila][col] = datos.get(i).getDirector();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}
	
	//Montado de tablas con base de datos relacionales
	public void MostrarDatosVideojuegos(HashMap<Integer, Videojuegos> datos) {
		int numColumnas = 5;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id";
		cabecera[1] = "nombre";
		cabecera[2] = "tipo";
		cabecera[3] = "Empresa";
		cabecera[4] = "Creacion";
		int fila = 0, col;
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			col = 0;
			contenido[fila][col] = valor.getValue().getId();
			col++;
			contenido[fila][col] = valor.getValue().getNombre();
			col++;
			contenido[fila][col] = valor.getValue().getTipo();
			col++;
			contenido[fila][col] = valor.getValue().getEmpresa().getId_Empresa();
			col++;
			contenido[fila][col] = valor.getValue().getCreación();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}
	public void MostrarDatosEmpresas(HashMap<Integer, Empresas> datos) {
		int numColumnas = 6;
		int numFilas = datos.size();

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		cabecera[0] = "id_Empresa";
		cabecera[1] = "Nombre";
		cabecera[2] = "Tamaño";
		cabecera[3] = "Pais";
		cabecera[4] = "Capital";
		cabecera[5] = "Director";
		int fila = 0, col;
		for (Entry<Integer, Empresas> valor : datos.entrySet()) {
			col = 0;
			contenido[fila][col] = valor.getValue().getId_Empresa();
			col++;
			contenido[fila][col] = valor.getValue().getNombre();
			col++;
			contenido[fila][col] = valor.getValue().getTamaño();
			col++;
			contenido[fila][col] = valor.getValue().getPais();
			col++;
			contenido[fila][col] = valor.getValue().getCapital();
			col++;
			contenido[fila][col] = valor.getValue().getDirector();
			fila++;
			}
		miTabla = new DefaultTableModel(contenido, cabecera);
		}

	public DefaultTableModel getTabla() {
		return miTabla;
	}
}
