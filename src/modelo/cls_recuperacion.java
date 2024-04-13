package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import conexion.cls_conexion;

public class cls_recuperacion {
	
	//Variables para el envio de correo
	private static String remitente = "pruebprogram@gmail.com";
    private static String clave_remitente = "kpfzfalkzmdeufsi";
    public static String recuperarClave(String correo) {
        String clave = null;
        try {
            cls_conexion conexionBD = new cls_conexion();
           
            Connection conexion = conexionBD.conectar();
            PreparedStatement ps = conexion.prepareStatement("select CLAVE from PERSONAL where CORREO = ? AND CARGO IN ('Gerente', 'Cajero')");
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clave = rs.getString("CLAVE");
            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cls_personal p = new cls_personal();
        clave = p.DesEncriptar(clave);
        return clave;
    }

    public static void enviarCorreo(String destinatario, String mensaje) {

         String clave = recuperarClave(destinatario);

        // Configurar el título y contenido del correo
        String titulo = "Solicitud de Recuperación de Clave";
        String contenido = "Su clave es: " + clave;

        // Configurar las propiedades de la conexión SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587"); 
        props.setProperty("mail.smtp.user", remitente);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");


        // Autenticación
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave_remitente);
            }
        });

        try {
            // Crear un mensaje
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(remitente));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            emailMessage.setSubject(titulo);
            emailMessage.setText(contenido);

            // Enviar el mensaje
            Transport.send(emailMessage);
        
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

