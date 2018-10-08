package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.text.Caret;
import javax.swing.GroupLayout.Alignment;

import Controlador.Controlador;
import Modelo.BaseDeDatos;
import Modelo.Fichero;
import Objetos.Videojuegos;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;

public class VerFichero extends JFrame {

	private Controlador controlador;
	private JLabel lblVerCuestionariosRealizados;
	private JPanel panel;
	private JLabel lblConsultaLosCuestionarios;
	private JButton btnSalir;

	public static boolean isNumeric(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	public String CargarFichero(HashMap<Integer, Videojuegos> datos) {
		String texto = "";
		for (Entry<Integer, Videojuegos> valor : datos.entrySet()) {
			texto += "ID: " + valor.getValue().getId() + " \n";
			texto += "Nombre: " + valor.getValue().getNombre() + " \n";
			texto += "Tipo: " + valor.getValue().getTipo() + " \n";
			texto += "Empresa: " + valor.getValue().getEmpresa() + " \n";
			texto += "Creacion: " + valor.getValue().getCreación() + " \n";
		}
		return texto;

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public VerFichero() {
		initialize();
	}

	private void initialize() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setBounds(50, 100, 860, 660);
		getContentPane().setBackground(new Color(102, 153, 204));

		lblVerCuestionariosRealizados = new JLabel("Ver Fichero");
		lblVerCuestionariosRealizados.setBounds(344, 31, 151, 28);
		lblVerCuestionariosRealizados.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblVerCuestionariosRealizados.setForeground(Color.WHITE);

		panel = new JPanel();
		panel.setBounds(53, 72, 738, 499);

		lblConsultaLosCuestionarios = new JLabel("Estos son los datos del fichero");
		lblConsultaLosCuestionarios.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnSalir = new JButton("Volver al men\u00FA");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.VolverPrincipal2();
			}
		});
		btnSalir.setBackground(Color.RED);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnAadirAlFichero = new JButton("A\u00F1adir al fichero");
		btnAadirAlFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.AñadirFichero();
			}
		});
		btnAadirAlFichero.setForeground(Color.WHITE);
		btnAadirAlFichero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAadirAlFichero.setBackground(Color.GREEN);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(null);
		getContentPane().add(lblVerCuestionariosRealizados);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				textPane.setText(CargarFichero(controlador.ObtenerDatosFichero()));
			}
		});
		JButton btnEliminarDelFichero = new JButton("Eliminar de fichero");
		btnEliminarDelFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valor = 9;
				JFrame frame = new JFrame("¿Introduzca la posicion que desea eliminar");
				String name = JOptionPane.showInputDialog(frame, "¿Cual desea eliminar?");
				if (isNumeric(name)) {
					valor = Integer.parseInt(name);
					textPane.setText(CargarFichero(controlador.EliminarDatosFichero(valor)));
				}else {
					System.out.println("El dato introducido no es un número");
				}
			}
		});
		btnEliminarDelFichero.setForeground(Color.WHITE);
		btnEliminarDelFichero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarDelFichero.setBackground(Color.ORANGE);

		JButton btnCopiarabasededatos = new JButton("Copiar datos a la base de datos");
		btnCopiarabasededatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.PasarDatosBaseDeDatos();
				Object[] options = {"Si, por favor", "luego"};
				int n = JOptionPane.showOptionDialog(panel,
				                "¿Quiéres ver como a quedado tu base de datos?",
				                "Elige una opción",
				                JOptionPane.YES_NO_OPTION,
				                JOptionPane.QUESTION_MESSAGE,
				                null,
				                options,
				                options[0]);
				if (n == JOptionPane.YES_OPTION) {
					controlador.ComprobarBaseDeDatos();
				}
			}
		});
		btnCopiarabasededatos.setForeground(Color.WHITE);
		btnCopiarabasededatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCopiarabasededatos.setBackground(Color.GRAY);

		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addGap(68)
				.addComponent(btnEliminarDelFichero, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btnAadirAlFichero, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(79, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblConsultaLosCuestionarios)
						.addPreferredGap(ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
						.addComponent(btnCopiarabasededatos).addGap(59))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(39, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE)
						.addGap(35)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(22).addComponent(lblConsultaLosCuestionarios))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnCopiarabasededatos,
								GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						.addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAadirAlFichero, GroupLayout.PREFERRED_SIZE, 51,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminarDelFichero, GroupLayout.PREFERRED_SIZE, 51,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(25, Short.MAX_VALUE)));
	}
}
