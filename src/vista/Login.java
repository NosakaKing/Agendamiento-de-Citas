package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import modelo.cls_auditoria;
import modelo.cls_facturacion;
import modelo.cls_personal;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_correo;
    private JPasswordField txt_clave;
    int intentos = 0;
    cls_personal p = new cls_personal();
    cls_facturacion f = new cls_facturacion();
    cls_auditoria ad = new cls_auditoria();

    /**
     * Create the frame.
     */
    public Login() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        setUndecorated(true);
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 728, 415);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(252, 129, 129));
        panel.setBounds(0, 0, 346, 415);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblBienvenidosDeNuevo = new JLabel("BIENVENIDO A BRII!");
        lblBienvenidosDeNuevo.setBackground(new Color(255, 255, 255));
        lblBienvenidosDeNuevo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenidosDeNuevo.setForeground(Color.WHITE);
        lblBienvenidosDeNuevo.setBounds(0, 271, 346, 19);
        lblBienvenidosDeNuevo.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(lblBienvenidosDeNuevo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 346, 244);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/LoginFondo.png")));
        lblNewLabel_1.setBounds(0, 0, 346, 244);
        panel_1.add(lblNewLabel_1);

        JLabel lblNombreDelEstablecimiento = new JLabel("Centro Estetico");
        lblNombreDelEstablecimiento.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreDelEstablecimiento.setForeground(Color.WHITE);
        lblNombreDelEstablecimiento.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblNombreDelEstablecimiento.setBackground(Color.WHITE);
        lblNombreDelEstablecimiento.setBounds(0, 301, 346, 19);
        panel.add(lblNombreDelEstablecimiento);

        JLabel lbl_intentos = new JLabel("");
        lbl_intentos.setForeground(Color.black);
        lbl_intentos.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl_intentos.setBounds(386, 264, 283, 36);
        contentPane.add(lbl_intentos);

        JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txt_correo.getText();
                String pass = new String(txt_clave.getPassword());

                if (!txt_correo.getText().isEmpty() && !new String(txt_clave.getPassword()).isEmpty()) {
                    try {
                        if (p.Login(email, pass)) {
                            String apellidosUsuario = p.getPrimerApellido() + " " + p.getSegundoApellido();
                            String cargoUsuario = p.getCargo();
                            cls_facturacion.setpersonalAtendido(p.getIdPersonal());
                            ad.setId_Personal(p.getIdPersonal());
                            ad.insertar();
                            JOptionPane.showMessageDialog(null, "¡Bienvenido!");
                            txt_correo.setText("");
                            txt_clave.setText("");
                            dispose();
                            Escritorio es = new Escritorio(cargoUsuario);
                            es.setVisible(true);
                            es.setLocationRelativeTo(null);

                            es.lblNombreUsuario.setText(apellidosUsuario);
                            es.lblTipoUsuario.setText(cargoUsuario);

                        } else {
                            JOptionPane.showMessageDialog(null, "Error de Usuario o Contraseña");
                            intentos++;
                            int r = 3 - intentos;
                            lbl_intentos.setText("Le quedan " + r + " intentos");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,
                                "Se ha producido un error al intentar iniciar sesión: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
                }
            }
        });
        btnIniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnIniciarSesion.setBackground(new Color(232, 129, 129));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnIniciarSesion.setBackground(new Color(252, 129, 129));
            }
        });
        btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setForeground(new Color(255, 255, 255));
        btnIniciarSesion.setBounds(386, 304, 283, 41);
        btnIniciarSesion.setBackground(new Color(252, 129, 129));
        contentPane.add(btnIniciarSesion);

        txt_correo = new JTextField();
        txt_correo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txt_correo.setBounds(386, 123, 283, 36);
        contentPane.add(txt_correo);
        txt_correo.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(386, 157, 276, 2);
        contentPane.add(separator);

        JLabel lblNewLabel = new JLabel("INICIO DE SESION");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblNewLabel.setBounds(345, 24, 383, 36);
        contentPane.add(lblNewLabel);

        JLabel lblCorreoElectronico = new JLabel("USUARIO");
        lblCorreoElectronico.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblCorreoElectronico.setBounds(386, 76, 79, 36);
        contentPane.add(lblCorreoElectronico);

        JLabel lblContrasena = new JLabel("CONTRASEÑA");
        lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblContrasena.setBounds(386, 170, 104, 36);
        contentPane.add(lblContrasena);

        JLabel lblOlvidoSuContrasea = new JLabel("¿Olvidaste tu contraseña?");
        lblOlvidoSuContrasea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Recup_contrasenia rec = new Recup_contrasenia();
                rec.setLocationRelativeTo(null);
                rec.setVisible(true);
            }
        });
        lblOlvidoSuContrasea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblOlvidoSuContrasea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblOlvidoSuContrasea.setBounds(472, 356, 147, 25);
        contentPane.add(lblOlvidoSuContrasea);

        JLabel lblX = new JLabel("X");
        lblX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblX.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblX.setForeground(Color.LIGHT_GRAY);
            }
        });
        lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblX.setForeground(Color.LIGHT_GRAY);
        lblX.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblX.setBounds(696, 11, 22, 24);
        contentPane.add(lblX);

        txt_clave = new JPasswordField();
        txt_clave.setBounds(386, 217, 283, 36);
        contentPane.add(txt_clave);

        
    }

    public static void main(String[] args) {

        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }

}
