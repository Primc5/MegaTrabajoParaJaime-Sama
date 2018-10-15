package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Principal extends JFrame{

	private Controlador controlador;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextPane txtpnEstoEsUn;
	private JButton btnNewButton;
	private JTextPane txtpnEscribeElFeedback;
	private JButton btnEscribirFeedback;
	private JLabel label;
	
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Principal() {
		setVisible(true);
		getContentPane().setBackground(new Color(102, 153, 204));
		
		panel = new JPanel();
		panel.setBounds(22, 120, 808, 180);
		panel.setBackground(Color.WHITE);
		
		panel_2 = new JPanel();
		panel_2.setBounds(22, 328, 808, 202);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		
		txtpnEscribeElFeedback = new JTextPane();
		txtpnEscribeElFeedback.setText("Realizar operaciones del fichero como ver el contenido, a\u00F1adir datos o pasar la informaci\u00F3n del fichero a la base de datos");
		txtpnEscribeElFeedback.setForeground(Color.BLACK);
		txtpnEscribeElFeedback.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnEscribeElFeedback.setBackground(Color.WHITE);
		txtpnEscribeElFeedback.setBounds(12, 13, 784, 57);
		panel_2.add(txtpnEscribeElFeedback);
		
		
		btnEscribirFeedback = new JButton("Fichero");
		btnEscribirFeedback.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnEscribirFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.VerFichero();
			}
		});
		btnEscribirFeedback.setForeground(Color.BLACK);
		btnEscribirFeedback.setBackground(Color.ORANGE);
		btnEscribirFeedback.setBounds(12, 83, 784, 106);
		panel_2.add(btnEscribirFeedback);
		
		lblNewLabel = new JLabel("Ficheros VS Base de datos");
		lblNewLabel.setBounds(255, 42, 363, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);
		panel.setLayout(null);
		
		txtpnEstoEsUn = new JTextPane();
		txtpnEstoEsUn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnEstoEsUn.setBackground(Color.WHITE);
		txtpnEstoEsUn.setForeground(new Color(0, 0, 0));
		txtpnEstoEsUn.setText("Realizar operaciones de la base de datos como ver el contenido, a\u00F1adir datos o pasar la informaci\u00F3n de la base de datos  al fichero");
		txtpnEstoEsUn.setBounds(24, 13, 772, 50);
		panel.add(txtpnEstoEsUn);
		
		btnNewButton = new JButton("Base de datos");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.VerBase();
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(12, 70, 784, 97);
		panel.add(btnNewButton);
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(panel);
		getContentPane().add(panel_2);
		
		label = new JLabel("");
		label.setBounds(0, 0, 841, 621);
		getContentPane().add(label);
		setBounds(50, 100, 860, 660);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}