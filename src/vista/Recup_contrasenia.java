package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.cls_recuperacion;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Recup_contrasenia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtcorreo;
	private JLabel txt_confirmacion;
	

	/**
	 * Create the frame.
	 */
	public Recup_contrasenia() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 259);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtcorreo = new JTextField();
		txtcorreo.setBounds(20, 112, 215, 20);
		txtcorreo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtcorreo.setBorder(null);
		txtcorreo.setBackground(new Color(241, 241, 241));
		contentPane.add(txtcorreo);
		txtcorreo.setColumns(10);
		
		JButton btn_enviar = new JButton("Enviar Solicitud");
		btn_enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String correo = txtcorreo.getText();
                String clave = cls_recuperacion.recuperarClave(correo);
                if (clave != null) {
                    cls_recuperacion.enviarCorreo(correo, "Tu contraseña es: " + clave);
                    txt_confirmacion.setText("La contraseña ha sido enviada al correo.");
                } else {
                    txt_confirmacion.setText("No se encontró ninguna contraseña de este correo.");
                }
		        }
		});
		btn_enviar.setBounds(267, 112, 122, 25);
		btn_enviar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btn_enviar.setBorder(UIManager.getBorder("Button.border"));
		btn_enviar.setBackground(new Color(241, 241, 241));
		btn_enviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btn_enviar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnRegresar.setBounds(322, 223, 89, 25);
		btnRegresar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRegresar.setBorder(UIManager.getBorder("Button.border"));
		btnRegresar.setBackground(new Color(241, 241, 241));
		btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnRegresar);
		
		txt_confirmacion = new JLabel("");
		txt_confirmacion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txt_confirmacion.setBounds(20, 170, 373, 20);
		contentPane.add(txt_confirmacion);
		
		JLabel lblRecuperacionDeContrasea = new JLabel("Recuperacion de Contraseña");
		lblRecuperacionDeContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecuperacionDeContrasea.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblRecuperacionDeContrasea.setBounds(0, 11, 411, 36);
		contentPane.add(lblRecuperacionDeContrasea);
		
		JLabel lblIngreseSuCorreo = new JLabel("Ingrese su correo electronico:");
		lblIngreseSuCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblIngreseSuCorreo.setBounds(20, 60, 230, 36);
		contentPane.add(lblIngreseSuCorreo);
		
		JLabel lblCorreo = new JLabel("__________________________");
		lblCorreo.setForeground(Color.GRAY);
		lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorreo.setBounds(20, 116, 218, 20);
		contentPane.add(lblCorreo);
	}
}
