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
import auxiliares.MontarTablas;

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
	private Fichero modelo;
	private MontarTablas mTablas;
	
	private JLabel lblTincas;
	private JPanel panel;
	private JLabel lblsubtitlepanel;
	private JButton btnVolver_CPAlumno;
	private JTable table;
	private JTable table_1;
	
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
	
	public void setmTablas(MontarTablas mTablas) {
		this.mTablas = mTablas;
	}

	public void setModelo(Fichero modelo) {
		this.modelo = modelo;
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public VerFichero() {
		initialize();
	}

	private void initialize() {
		setBounds(50, 100, 885, 801);
		getContentPane().setBackground(new Color(102, 153, 204));
		
		lblTincas = new JLabel("Ver fichero");
		lblTincas.setBounds(376, 38, 152, 37);
		lblTincas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTincas.setForeground(Color.WHITE);
		
		panel = new JPanel();
		panel.setBounds(63, 88, 723, 644);
		
		lblsubtitlepanel = new JLabel("Estos son los datos del fichero");
		lblsubtitlepanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnVolver_CPAlumno = new JButton("Volver al men\u00FA");
		btnVolver_CPAlumno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVolver_CPAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.VolverPrincipal2();
			}
		});
		btnVolver_CPAlumno.setForeground(Color.WHITE);
		btnVolver_CPAlumno.setBackground(Color.RED);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAadirDatos = new JButton("A\u00F1adir datos");
		btnAadirDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Videojuego", "Empresa"};
				int n = JOptionPane.showOptionDialog(panel,
				                "¿A qué fichero deseas añadir datos?",
				                "Elige una opción",
				                JOptionPane.YES_NO_OPTION,
				                JOptionPane.QUESTION_MESSAGE,
				                null,
				                options,
				                options[0]);
				if (n == JOptionPane.YES_OPTION) {
					controlador.AñadirFichero();
				}else if(n == JOptionPane.NO_OPTION) {
					controlador.AñadirEmpresaFichero();
				}
			}
		});
		btnAadirDatos.setForeground(Color.BLACK);
		btnAadirDatos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAadirDatos.setBackground(Color.GREEN);
		
		JButton btnEliminarDato = new JButton("Eliminar filas");
		btnEliminarDato.setEnabled(false);
		
		btnEliminarDato.setForeground(Color.BLACK);
		btnEliminarDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarDato.setBackground(Color.ORANGE);
		
		JButton btnCopiarDatosAl = new JButton("Copiar datos a la base de datos");
		btnCopiarDatosAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controlador.PasarDatosBaseDeDatosVideojuegos();
					controlador.PasarDatosBaseDeDatosEmpresas();
					Object[] options = {"Si, por favor", "luego"};
					int n = JOptionPane.showOptionDialog(panel,
					                "¿Quiéres ver como a quedado tu fichero?",
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
		btnCopiarDatosAl.setForeground(Color.WHITE);
		btnCopiarDatosAl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCopiarDatosAl.setBackground(Color.GRAY);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblVideojuegos = new JLabel("Videojuegos:");
		lblVideojuegos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblsubtitlepanel)
								.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
								.addComponent(btnCopiarDatosAl))
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addGap(29)
								.addComponent(btnEliminarDato, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnVolver_CPAlumno, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnAadirDatos, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblVideojuegos, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEmpresas, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCopiarDatosAl, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblsubtitlepanel)))
					.addGap(12)
					.addComponent(lblVideojuegos, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblEmpresas, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVolver_CPAlumno, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(btnAadirDatos, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminarDato, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(null);
		getContentPane().add(lblTincas);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				controlador.VerDatosEmpresas();
				table_1.setModel(mTablas.getTabla());
				controlador.VerDatosVideojuegos();
				table.setModel(mTablas.getTabla());
			}
		});
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				String fila = (String) table.getValueAt(table.getSelectedRow(), 3);
				System.out.println(fila);
				btnEliminarDato.setEnabled(true);
				btnEliminarDato.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (table.getSelectedRow() == -1) {
							btnEliminarDato.setEnabled(false);
						}
						if(isNumeric(fila) && fila != null) {
							int videojuego = Integer.parseInt(fila);
							controlador.EliminarDatosFicheroVideojuegos(videojuego);
							String tabla = "LeerBBDD";
							controlador.VerDatosVideojuegos();
							table.setModel(mTablas.getTabla());
						}else {
							System.err.println("No se ha podido eliminar la fila");
						}
					}
				});
				
			}
		});
		table_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				String fila = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				btnEliminarDato.setEnabled(true);
				btnEliminarDato.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (table_1.getSelectedRow() == -1) {
							btnEliminarDato.setEnabled(false);
						}
						if(isNumeric(fila) && fila != null) {
							int empresa = Integer.parseInt(fila);
							controlador.EliminarDatosFicheroEmpresas(empresa);
							String tabla = "LeerBBDDEmpresas";
							/*modelo.MostrarDatos(tabla);
							table_1.setModel(modelo.getTabla());*/
						}else {
							System.err.println("No se ha podido eliminar la fila");
						}
					}
				});
				
			}
		});
	}
}
