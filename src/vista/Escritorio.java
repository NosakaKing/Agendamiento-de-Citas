package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import modelo.cls_personal;
import vista.panel.Auditoria;
import vista.panel.Factura;
import vista.panel.Inicio;
import vista.panel.Personal;
import vista.panel.Reporte;
import vista.panel.Reserva;
import vista.panel.Servicio;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Escritorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel Principal;
	@SuppressWarnings("unused")
	private JButton lastPressedButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private int x, y;
	private JLabel lblUsuario;
	public JLabel lblTipoUsuario;
	public JLabel lblNombreUsuario;
	cls_personal p = new cls_personal();
	private JLabel lblCerrarSesion;

	/**
	 * Create the frame.
	 */
	public Escritorio(String cargoUsuario) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 270, 800);
		panelMenu.setBackground(new Color(252, 129, 129));
		
		contentPane.add(panelMenu);
	        

		ImageIcon iconInicio = new ImageIcon(Escritorio.class.getResource("/images/1Inicio.png"));
		ImageIcon iconFactura = new ImageIcon(Escritorio.class.getResource("/images/2Factura.png"));
		ImageIcon iconServicio = new ImageIcon(Escritorio.class.getResource("/images/3Servicio.png"));
		ImageIcon iconReserva = new ImageIcon(Escritorio.class.getResource("/images/4Reserva.png"));
		ImageIcon iconReporte = new ImageIcon(Escritorio.class.getResource("/images/5Reporte.png"));
		ImageIcon iconPerfil = new ImageIcon(Escritorio.class.getResource("/images/6Perfil.png"));
		ImageIcon iconAuditoria = new ImageIcon(Escritorio.class.getResource("/images/8Auditoria.png"));

		if (cargoUsuario.equals("Cajero")) {
			JButton btnInicio = createButton("INICIO", iconInicio, 10, 219, panelMenu);
			@SuppressWarnings("unused")
			JButton btnReservas = createButton("FACTURA", iconFactura, 10, 339, panelMenu);
			@SuppressWarnings("unused")
			JButton btnServicio = createButton("RESERVAS", iconReserva, 10, 279, panelMenu);
			btnInicio.setBackground(new Color(232, 129, 129));
			lastPressedButton = btnInicio;

		} else if (cargoUsuario.equals("Gerente")) {
			JButton btnInicio = createButton("INICIO", iconInicio, 10, 219, panelMenu);
			@SuppressWarnings("unused")
			JButton btnInventario = createButton("SERVICIO", iconServicio, 10, 279, panelMenu);
			@SuppressWarnings("unused")
			JButton btnServicio = createButton("RESERVAS", iconReserva, 10, 339, panelMenu);
			@SuppressWarnings("unused")
			JButton btnReservas = createButton("FACTURA", iconFactura, 10, 399, panelMenu);
			@SuppressWarnings("unused")
			JButton btnPerfil = createButton("PERSONAL", iconPerfil, 10, 459, panelMenu);
			btnInicio.setBackground(new Color(232, 129, 129));
			@SuppressWarnings("unused")
			JButton btnFactura = createButton("REPORTE", iconReporte, 10, 519, panelMenu);
			@SuppressWarnings("unused")
			JButton btnAuditoria = createButton("AUDITORIA", iconAuditoria, 10, 579, panelMenu);
			lastPressedButton = btnInicio;
		}
		panelMenu.setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(43, 44, 46, 14);
		panelMenu.add(lblNewLabel);

		JPanel panelMovimiento = new JPanel();
		panelMovimiento.setBounds(44, 21, 175, 175);
		panelMovimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				x = me.getX();
				y = me.getY();
			}

		});
		panelMovimiento.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
				setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
			}
		});
		panelMovimiento.setBackground(new Color(252, 129, 129));
		panelMenu.add(panelMovimiento);

		lblNewLabel_1 = new JLabel("");
		panelMovimiento.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Escritorio.class.getResource("/images/LogoEmpresa.png")));

		lblUsuario = new JLabel("");
		lblUsuario.setBounds(85, 604, 88, 106);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setIcon(new ImageIcon(Escritorio.class.getResource("/images/UsuarioPer.png")));
		panelMenu.add(lblUsuario);

		lblTipoUsuario = new JLabel("");
		lblTipoUsuario.setBounds(10, 709, 250, 14);
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setForeground(Color.WHITE);
		lblTipoUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelMenu.add(lblTipoUsuario);

		lblNombreUsuario = new JLabel("");
		lblNombreUsuario.setBounds(10, 729, 250, 25);
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setForeground(Color.WHITE);
		lblNombreUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelMenu.add(lblNombreUsuario);
		
		lblCerrarSesion = new JLabel("Cerrar Sesion");
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
			}
		});
		lblCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrarSesion.setIcon(new ImageIcon(Escritorio.class.getResource("/images/9Salir.png")));
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCerrarSesion.setBounds(10, 764, 250, 25);
		panelMenu.add(lblCerrarSesion);

		
		Principal = new JPanel();
		Principal.setOpaque(false);
		Principal.setBackground(new Color(255, 255, 255));
		Principal.setBounds(280, 0, 990, 789);
		contentPane.add(Principal);
		Principal.setLayout(new CardLayout(0, 0));
		Inicio inicio = new Inicio();
		verform(inicio);
	}

	private JButton createButton(String text, Icon icon, int x, int y, JPanel panel) {
		JButton button = new JButton(text);
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setIcon(icon);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 14));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(252, 129, 129));
		button.setBounds(x, y, 250, 38);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(232, 129, 129));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (button != lastPressedButton) {

					button.setBackground(new Color(252, 129, 129));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (lastPressedButton != null) {

					lastPressedButton.setBackground(new Color(252, 129, 129));
				}
				button.setBackground(new Color(232, 129, 129));
				lastPressedButton = button;

				switch (text) {
				case "SERVICIO":
					verform(new Servicio());
					break;
				case "FACTURA":
					verform(new Factura());
					break;
				case "INICIO":
					verform(new Inicio());
					break;
				case "REPORTE":
					verform(new Reporte());
					break;
				case "RESERVAS":
					verform(new Reserva());
					break;
				case "PERSONAL":
					verform(new Personal());
					break;
				case "AUDITORIA":
					verform(new Auditoria());
					break;
				}
			}
		});
		panel.add(button);
		return button;

	}

	private void verform(Component com) {
		Principal.removeAll();
		Principal.add(com);
		Principal.revalidate();
		Principal.repaint();
	}
}
