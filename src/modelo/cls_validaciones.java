package modelo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class cls_validaciones {
	public boolean validarCedula(String txt_cedu) {
		if (txt_cedu.length() != 10) {
			return false; // Return false for invalid length
		}

		int suma = 0;

		for (int i = 0; i < txt_cedu.length() - 1; i++) {
			int numero = Integer.parseInt(String.valueOf(txt_cedu.charAt(i)));
			if (i % 2 == 0) {
				numero = numero * 2;
				if (numero > 9) {
					numero = numero - 9;
				}
			}
			suma += numero;
		}

		int resultado = 10 - (suma % 10); // Eliminamos el segundo % 10
		if (resultado == 10) {
			resultado = 0; // Si el resultado es 10, lo cambiamos a 0
		}
		int ultimoDigito = Integer.parseInt(String.valueOf(txt_cedu.charAt(9)));

		return resultado == ultimoDigito;
	}

	public boolean validacionClave(String txtClave) {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_\\-])[A-Za-z\\d@$!%*?&_\\-]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txtClave);
		return matcher.matches();
	}

	public boolean ValidacionEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean validarEdad(String fechaNacimiento) {
        try {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento, formatter);
            Period periodo = Period.between(fechaNacimientoDate, fechaActual);
            return periodo.getYears() >= 18;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
	    }
	


	public void validarSoloLetras(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
					e.consume();
				}
			}
		});
	}

	public void validarSoloNumerosEnteros(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
	}

	public void validarSoloNumerosDecimales(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
			}
		});
	}

}
