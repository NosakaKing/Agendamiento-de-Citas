package vista.swing.datechooser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public final class Button extends JButton {

    private static final long serialVersionUID = 1L;

    private Event event;
    private boolean paintBackground = true;
    private Color colorSelected;

    public Button() {
        setBorder(new LineBorder((new java.awt.Color(118, 118, 118)))); 
        setContentAreaFilled(false);
        setFocusable(false);
        setBackground(Color.WHITE);
        setVerticalAlignment(JButton.TOP);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (!getText().equals("") && getName() != null) {
                    if (getName().equals("day") || getName().equals("year")) {
                        event.execute(me, Integer.valueOf(getText()));
                        
                        
                    } else {
                        event.execute(me, Integer.valueOf(getName()));
                    }
                    setBackground(getColorSelected());

                    setForeground(new Color(255, 255, 255));
                }
            }
        });
    }

    public boolean isPaintBackground() {
        return paintBackground;
    }

    public void setPaintBackground(boolean paintBackground) {
        this.paintBackground = paintBackground;
    }

    
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void paint(Graphics grphcs) {
        if (paintBackground) {
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRect(0, 0, width, height); 
        }
        super.paint(grphcs);
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
       
    }
    
    
}
