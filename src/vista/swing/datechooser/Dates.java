package vista.swing.datechooser;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.cls_reserva;

public class Dates extends JPanel {

    private static final long serialVersionUID = 1L;
    private String dateFormat = "dd-MM-yyyy";
    private vista.swing.datechooser.Button cmdMo;
    private vista.swing.datechooser.Button cmdTu;
    private vista.swing.datechooser.Button cmdWe;
    private vista.swing.datechooser.Button cmdTh;
    private vista.swing.datechooser.Button cmdFr;
    private vista.swing.datechooser.Button cmdSa;
    private vista.swing.datechooser.Button cmdSu;
    private vista.swing.datechooser.Button cmd1;
    private vista.swing.datechooser.Button cmd2;
    private vista.swing.datechooser.Button cmd3;
    private vista.swing.datechooser.Button cmd4;
    private vista.swing.datechooser.Button cmd5;
    private vista.swing.datechooser.Button cmd6;
    private vista.swing.datechooser.Button cmd7;
    private vista.swing.datechooser.Button cmd8;
    private vista.swing.datechooser.Button cmd9;
    private vista.swing.datechooser.Button cmd10;
    private vista.swing.datechooser.Button cmd11;
    private vista.swing.datechooser.Button cmd12;
    private vista.swing.datechooser.Button cmd13;
    private vista.swing.datechooser.Button cmd14;
    private vista.swing.datechooser.Button cmd15;
    private vista.swing.datechooser.Button cmd16;
    private vista.swing.datechooser.Button cmd17;
    private vista.swing.datechooser.Button cmd18;
    private vista.swing.datechooser.Button cmd19;
    private vista.swing.datechooser.Button cmd20;
    private vista.swing.datechooser.Button cmd21;
    private vista.swing.datechooser.Button cmd22;
    private vista.swing.datechooser.Button cmd23;
    private vista.swing.datechooser.Button cmd24;
    private vista.swing.datechooser.Button cmd25;
    private vista.swing.datechooser.Button cmd26;
    private vista.swing.datechooser.Button cmd27;
    private vista.swing.datechooser.Button cmd28;
    private vista.swing.datechooser.Button cmd29;
    private vista.swing.datechooser.Button cmd30;
    private vista.swing.datechooser.Button cmd31;
    private vista.swing.datechooser.Button cmd32;
    private vista.swing.datechooser.Button cmd33;
    private vista.swing.datechooser.Button cmd34;
    private vista.swing.datechooser.Button cmd35;
    private vista.swing.datechooser.Button cmd36;
    private vista.swing.datechooser.Button cmd37;
    private vista.swing.datechooser.Button cmd38;
    private vista.swing.datechooser.Button cmd39;
    private vista.swing.datechooser.Button cmd40;
    private vista.swing.datechooser.Button cmd41;
    private vista.swing.datechooser.Button cmd42;
    private String Fecha;
    public static JLabel lblReservado;

    private Event event;
    private final int MONTH;
    private final int YEAR;
    private final int DAY;
    private int m;
    private int y;
    private int selectDay = 0;
    private int startDate;
    private int max_of_month;

    public Dates() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(7, 7));

        cmdMo = new vista.swing.datechooser.Button();
        cmdMo.setForeground(new java.awt.Color(118, 118, 118));
        cmdMo.setText("Mo");
        add(cmdMo);

        cmdTu = new vista.swing.datechooser.Button();
        cmdTu.setForeground(new java.awt.Color(118, 118, 118));
        cmdTu.setText("Tu");
        add(cmdTu);

        cmdWe = new vista.swing.datechooser.Button();
        cmdWe.setForeground(new java.awt.Color(118, 118, 118));
        cmdWe.setText("We");
        add(cmdWe);

        cmdTh = new vista.swing.datechooser.Button();
        cmdTh.setForeground(new java.awt.Color(118, 118, 118));
        cmdTh.setText("Th");
        add(cmdTh);

        cmdFr = new vista.swing.datechooser.Button();
        cmdFr.setForeground(new java.awt.Color(118, 118, 118));
        cmdFr.setText("Fr");
        add(cmdFr);

        cmdSa = new vista.swing.datechooser.Button();
        cmdSa.setForeground(new java.awt.Color(118, 118, 118));
        cmdSa.setText("Sa");
        add(cmdSa);

        cmdSu = new vista.swing.datechooser.Button();
        cmdSu.setForeground(new java.awt.Color(252, 129, 129));
        cmdSu.setText("Su");
        add(cmdSu);

        cmd1 = new vista.swing.datechooser.Button();
        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setName("day");
        add(cmd1);

        cmd2 = new vista.swing.datechooser.Button();
        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setName("day");
        add(cmd2);

        cmd3 = new vista.swing.datechooser.Button();
        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("1");
        cmd3.setName("day");
        add(cmd3);

        cmd4 = new vista.swing.datechooser.Button();
        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("2");
        cmd4.setName("day");
        add(cmd4);

        cmd5 = new vista.swing.datechooser.Button();
        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("3");
        cmd5.setName("day");
        add(cmd5);

        cmd6 = new vista.swing.datechooser.Button();
        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("4");
        cmd6.setName("day");
        add(cmd6);

        cmd7 = new vista.swing.datechooser.Button();
        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("5");
        cmd7.setName("day");
        add(cmd7);

        cmd8 = new vista.swing.datechooser.Button();
        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("6");
        cmd8.setName("day");
        add(cmd8);

        cmd9 = new vista.swing.datechooser.Button();
        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("7");
        cmd9.setName("day");
        add(cmd9);

        cmd10 = new vista.swing.datechooser.Button();
        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("8");
        cmd10.setName("day");
        add(cmd10);

        cmd11 = new vista.swing.datechooser.Button();
        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("9");
        cmd11.setName("day");
        add(cmd11);

        cmd12 = new vista.swing.datechooser.Button();
        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("10");
        cmd12.setName("day");
        add(cmd12);

        cmd13 = new vista.swing.datechooser.Button();
        cmd13.setForeground(new java.awt.Color(75, 75, 75));
        cmd13.setText("11");
        cmd13.setName("day");
        add(cmd13);

        cmd14 = new vista.swing.datechooser.Button();
        cmd14.setForeground(new java.awt.Color(75, 75, 75));
        cmd14.setText("12");
        cmd14.setName("day");
        add(cmd14);

        cmd15 = new vista.swing.datechooser.Button();
        cmd15.setForeground(new java.awt.Color(75, 75, 75));
        cmd15.setText("13");
        cmd15.setName("day");
        add(cmd15);

        cmd16 = new vista.swing.datechooser.Button();
        cmd16.setForeground(new java.awt.Color(75, 75, 75));
        cmd16.setText("14");
        cmd16.setName("day");
        add(cmd16);

        cmd17 = new vista.swing.datechooser.Button();
        cmd17.setForeground(new java.awt.Color(75, 75, 75));
        cmd17.setText("15");
        cmd17.setName("day");
        add(cmd17);

        cmd18 = new vista.swing.datechooser.Button();
        cmd18.setForeground(new java.awt.Color(75, 75, 75));
        cmd18.setText("16");
        cmd18.setName("day");
        add(cmd18);

        cmd19 = new vista.swing.datechooser.Button();
        cmd19.setForeground(new java.awt.Color(75, 75, 75));
        cmd19.setText("17");
        cmd19.setName("day");
        add(cmd19);

        cmd20 = new vista.swing.datechooser.Button();
        cmd20.setForeground(new java.awt.Color(75, 75, 75));
        cmd20.setText("18");
        cmd20.setName("day");
        add(cmd20);

        cmd21 = new vista.swing.datechooser.Button();
        cmd21.setForeground(new java.awt.Color(75, 75, 75));
        cmd21.setText("19");
        cmd21.setName("day");
        add(cmd21);

        cmd22 = new vista.swing.datechooser.Button();
        cmd22.setForeground(new java.awt.Color(75, 75, 75));
        cmd22.setText("20");
        cmd22.setName("day");
        add(cmd22);

        cmd23 = new vista.swing.datechooser.Button();
        cmd23.setForeground(new java.awt.Color(75, 75, 75));
        cmd23.setText("21");
        cmd23.setName("day");
        add(cmd23);

        cmd24 = new vista.swing.datechooser.Button();
        cmd24.setForeground(new java.awt.Color(75, 75, 75));
        cmd24.setText("22");
        cmd24.setName("day");
        add(cmd24);

        cmd25 = new vista.swing.datechooser.Button();
        cmd25.setForeground(new java.awt.Color(75, 75, 75));
        cmd25.setText("23");
        cmd25.setName("day");
        add(cmd25);

        cmd26 = new vista.swing.datechooser.Button();
        cmd26.setForeground(new java.awt.Color(75, 75, 75));
        cmd26.setText("24");
        cmd26.setName("day");
        add(cmd26);

        cmd27 = new vista.swing.datechooser.Button();
        cmd27.setForeground(new java.awt.Color(75, 75, 75));
        cmd27.setText("25");
        cmd27.setName("day");
        add(cmd27);

        cmd28 = new vista.swing.datechooser.Button();
        cmd28.setForeground(new java.awt.Color(75, 75, 75));
        cmd28.setText("26");
        cmd28.setName("day");
        add(cmd28);

        cmd29 = new vista.swing.datechooser.Button();
        cmd29.setForeground(new java.awt.Color(75, 75, 75));
        cmd29.setText("27");
        cmd29.setName("day");
        add(cmd29);

        cmd30 = new vista.swing.datechooser.Button();
        cmd30.setForeground(new java.awt.Color(75, 75, 75));
        cmd30.setText("28");
        cmd30.setName("day");
        add(cmd30);

        cmd31 = new vista.swing.datechooser.Button();
        cmd31.setForeground(new java.awt.Color(75, 75, 75));
        cmd31.setText("29");
        cmd31.setName("day");
        add(cmd31);

        cmd32 = new vista.swing.datechooser.Button();
        cmd32.setForeground(new java.awt.Color(75, 75, 75));
        cmd32.setText("30");
        cmd32.setName("day");
        add(cmd32);

        cmd33 = new vista.swing.datechooser.Button();
        cmd33.setForeground(new java.awt.Color(75, 75, 75));
        cmd33.setText("31");
        cmd33.setName("day");
        add(cmd33);

        cmd34 = new vista.swing.datechooser.Button();
        cmd34.setForeground(new java.awt.Color(75, 75, 75));
        cmd34.setName("day");
        add(cmd34);

        cmd35 = new vista.swing.datechooser.Button();
        cmd35.setForeground(new java.awt.Color(75, 75, 75));
        cmd35.setName("day");
        add(cmd35);

        cmd36 = new vista.swing.datechooser.Button();
        cmd36.setForeground(new java.awt.Color(75, 75, 75));
        cmd36.setName("day");
        add(cmd36);

        cmd37 = new vista.swing.datechooser.Button();
        cmd37.setForeground(new java.awt.Color(75, 75, 75));
        cmd37.setName("day");
        add(cmd37);

        cmd38 = new vista.swing.datechooser.Button();
        cmd38.setForeground(new java.awt.Color(75, 75, 75));
        cmd38.setName("day");
        add(cmd38);

        cmd39 = new vista.swing.datechooser.Button();
        cmd39.setForeground(new java.awt.Color(75, 75, 75));
        cmd39.setName("day");
        add(cmd39);

        cmd40 = new vista.swing.datechooser.Button();
        cmd40.setForeground(new java.awt.Color(75, 75, 75));
        cmd40.setName("day");
        add(cmd40);

        cmd41 = new vista.swing.datechooser.Button();
        cmd41.setForeground(new java.awt.Color(75, 75, 75));
        cmd41.setName("day");
        add(cmd41);

        cmd42 = new vista.swing.datechooser.Button();
        cmd42.setForeground(new java.awt.Color(75, 75, 75));
        cmd42.setName("day");
        add(cmd42);

        JLabel lblReservado = new JLabel("Reservado");
        lblReservado.setBounds(0, 0, 70, 14); // Ajusta los valores según sea necesario

    }

    public void showDate(int month, int year, SelectedDate select) {
        m = month;
        y = year;
        // selectDay = 0;
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (start == 1) {
            start += 7;
        }
        clear();
        start += 5;
        startDate = start;
        cls_reserva cls_reserva = new cls_reserva();
        for (int i = 1; i <= max_of_month; i++) {
            Button cmd = (Button) getComponent(start);
            cmd.setColorSelected(getForeground());
            cmd.setText(String.valueOf(i));

            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(YEAR + "-" + month + "-" + i);
                String formattedDate = new SimpleDateFormat(dateFormat).format(date);

                // Obtener todas las fechas de reserva
                Object[][] reservas = cls_reserva.consultarReservaCalendario();
                // Crear un conjunto para almacenar las fechas reservadas
                Set<String> fechasReservadas = new HashSet<>();
                // Recorrer todas las reservas y agregar las fechas al conjunto
                cmd.setLayout(new BoxLayout(cmd, BoxLayout.Y_AXIS));

                // Establecer el layout del contenedor como BoxLayout en dirección vertical
                cmd.setLayout(new BoxLayout(cmd, BoxLayout.Y_AXIS));

                // Espacio en blanco inicial con una dimensión de 5 píxeles en la dirección y
                cmd.add(Box.createVerticalStrut(11));
                if (reservas != null) {
                    for (Object[] reserva : reservas) {
                        if (formattedDate.equals(getFecha()) || formattedDate.equals(reserva[3].toString())) {
                            JLabel lblReservado = new JLabel(reserva[4].toString());
                            lblReservado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            lblReservado.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    JOptionPane.showMessageDialog(null,
                                            "Reserva Numero: " + reserva[0].toString() + "\nCedula del Cliente: "
                                            + reserva[1].toString() + "\nFecha de reserva: " + reserva[2].toString()
                                            + "\nFecha de Cita: " + reserva[5].toString()
                                            + "\nEstado del Servicio: " + reserva[4].toString());
                                }
                            });

                            // Añadir el JLabel al contenedor
                            cmd.add(lblReservado);

                            // Añadir un espacio en blanco adicional entre cada JLabel
                            cmd.add(Box.createVerticalStrut(5));

                            // Agregar la fecha de reserva al conjunto
                            fechasReservadas.add(reserva[3].toString());
                        }
                    }
                } else {
                    // Manejar el caso en el que no hay reservas
                }

            } catch (ParseException e) {
                System.err.println(e);
            }
            if (i == DAY && month == MONTH && year == YEAR) {
                cmd.setBackground(new Color(224, 214, 229));
            } else {
                cmd.setBackground(Color.WHITE);
            }
            if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {
                cmd.setBackground(getForeground());
                cmd.setForeground(new Color(255, 255, 255));
            }
            start++;
        }
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFecha() {
        return Fecha;
    }

    private void clear() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((JButton) getComponent(i)).setText("");
        }
    }

    public void clearSelected() {
        for (int i = 7; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.valueOf(cmd.getText()) == DAY) {
                cmd.setBackground(new Color(224, 214, 229));
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            } else {
                cmd.setBackground(Color.WHITE);
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            }
        }
        selectDay = 0;
    }

    private void addEvent() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    public void setSelected(int index) {
        selectDay = index;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public void next() {
        if (selectDay == max_of_month) {
            selectDay = 0;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);
        String n = cmd.getText();
        if (!n.equals("") && Integer.valueOf(n) <= max_of_month) {
            selectDay++;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void back() {
        if (selectDay <= 1) {
            selectDay = max_of_month + 1;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay--;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void up() {
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay -= 7;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void down() {
        if (getComponents().length > startDate - 1 + selectDay + 7) {
            JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);
            String n = cmd.getText();
            if (!n.equals("") && cmd.getName() != null) {
                selectDay += 7;
                event.execute(null, selectDay);
                cmd.setBackground(new Color(206, 110, 245));
            }
        }
    }

}
