package Vistas;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

import Controlador.Controlador;
import Modelo.BaseDeDatos;

@SuppressWarnings("serial")
public class AñadirJsonEmpresas extends JFrame{	
		private BaseDeDatos modelo;
		private Controlador controlador;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JButton btnAnadir;
		private JButton btnCancelar;
		private JLabel lblNuevaEmpresa;
		private JLabel lblNumero_convenio;
		private JLabel lblFecha_convenio;
		private JLabel lblEstadoEntrevista;
		private JPanel panel;
		private JLabel lblFechaCurso;
		private JTextField textField_3;
		private JTextField textField_5;
		
		final static String DATE_FORMAT = "yyyy-MM-dd";
		private JTextField textField_4;
		
		public AñadirJsonEmpresas(){
			initialize();
		}
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

		public void setModelo(BaseDeDatos modelo) {
			this.modelo = modelo;
		}
		public void setControlador(Controlador controlador) {
			this.controlador = controlador;
		}

		private void VisibleAnadir() {
			if (!textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("") && !textField_5.getText().equals("")) {
				btnAnadir.setEnabled(true);
			} else
				btnAnadir.setEnabled(false);
		}
		
		private void initialize() {
			setBounds(50, 100, 860, 660);
			getContentPane().setBackground(new Color(102, 153, 204));
			
			lblEstadoEntrevista = new JLabel("A\u00D1ADIR DATOS EMPRESAS");
			lblEstadoEntrevista.setBounds(293, 84, 300, 34);
			lblEstadoEntrevista.setForeground(Color.WHITE);
			lblEstadoEntrevista.setFont(new Font("Georgia", Font.PLAIN, 23));
			
			panel = new JPanel();
			panel.setBounds(168, 145, 542, 409);
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
			textField = new JTextField();
			textField.setColumns(10);
			
			lblNuevaEmpresa = new JLabel("ID:");
			lblNuevaEmpresa.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			lblNumero_convenio = new JLabel("Nombre:");
			lblNumero_convenio.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			
			
			lblFecha_convenio = new JLabel("Tama\u00F1o:");
			lblFecha_convenio.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			lblFechaCurso = new JLabel("Pa\u00EDs:");
			lblFechaCurso.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			
			
			btnAnadir = new JButton("Aplicar");
			btnAnadir.setEnabled(false);
			btnAnadir.setFont(new Font("Georgia", Font.BOLD, 15));
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(isNumeric(textField.getText()) && isNumeric(textField_4.getText())) {
					controlador.AñadirDatosBaseDeDatosEmpresas(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText());
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					controlador.VolverAñadirEmpresaBBDD();
					}else {
						System.out.println("El id y capital deben ser numéricos");
					}
				}
			});
			btnAnadir.setBackground(Color.YELLOW);
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("Georgia", Font.BOLD, 15));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					controlador.VolverAñadirEmpresaBBDD();
				}
			});
			
			btnCancelar.setBackground(Color.ORANGE);
			
			JLabel lblCaracteristica = new JLabel("Director:");
			lblCaracteristica.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					VisibleAnadir();
				}
			});
			textField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					VisibleAnadir();
				}
			});
			textField_2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					VisibleAnadir();
				}
			});
			textField_3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					VisibleAnadir();
				}
			});
			textField_5.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					VisibleAnadir();
				}
			});
			
			JLabel lblCapital = new JLabel("Capital:");
			lblCapital.setFont(new Font("Georgia", Font.PLAIN, 15));
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(67, Short.MAX_VALUE)
						.addComponent(btnAnadir, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addGap(215)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addGap(48))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(44)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblFechaCurso)
								.addContainerGap())
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblFecha_convenio)
									.addContainerGap())
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNuevaEmpresa)
										.addComponent(lblNumero_convenio)
										.addComponent(lblCaracteristica))
									.addGap(73)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
									.addGap(103)))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblCapital)
								.addContainerGap())))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(25)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNuevaEmpresa)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNumero_convenio)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblFecha_convenio)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFechaCurso)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCapital, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCaracteristica, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAnadir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGap(35))
			);
			panel.setLayout(gl_panel);
			getContentPane().setLayout(null);
			getContentPane().add(lblEstadoEntrevista);
			getContentPane().add(panel);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(0, 0, 844, 621);
			getContentPane().add(lblNewLabel_1);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
