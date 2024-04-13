package vista.panel;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class Inicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private vista.swing.datechooser.DateChooser DateChooser1;

	/**
	 * Create the panel.
	 */
	public Inicio() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(255, 255, 255));
		panelTablaPr.setBounds(10, 76, 970, 702);
		add(panelTablaPr);
		
		JLabel lblVisualizacionDelPersonal = new JLabel("Visualizacion de las Reservas");
		lblVisualizacionDelPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelPersonal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelPersonal.setBounds(0, 11, 944, 36);
		panelTablaPr.add(lblVisualizacionDelPersonal);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);
		
		JLabel lblSeccionDePersonal = new JLabel("Bienvenido al sistema de agendamiento de citas");
		lblSeccionDePersonal.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDePersonal.setBounds(10, 11, 467, 54);
		add(lblSeccionDePersonal);
		
		DateChooser1 = new vista.swing.datechooser.DateChooser();
		DateChooser1.setBounds(0, 58, 970, 644);	
		panelTablaPr.add(DateChooser1);

	}
}
