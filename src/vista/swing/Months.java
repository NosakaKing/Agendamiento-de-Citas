package vista.swing;

import javax.swing.JPanel;

import java.awt.GridLayout;


public final class Months extends JPanel {
	
	private Event event;

	private static final long serialVersionUID = 1L;
	private vista.swing.Button cmd1;
	private vista.swing.Button cmd2;
	private vista.swing.Button cmd3;
	private vista.swing.Button cmd4;
	private vista.swing.Button cmd5;
	private vista.swing.Button cmd6;
	private vista.swing.Button cmd7;
	private vista.swing.Button cmd8;
	private vista.swing.Button cmd9;
	private vista.swing.Button cmd10;
	private vista.swing.Button cmd11;
	private vista.swing.Button cmd12;
	

	
	public Months() {
		setBackground(new java.awt.Color(255, 255, 255));
		setLayout(new GridLayout(4,4));
		
		cmd1 = new vista.swing.Button();
		cmd1.setForeground(new java.awt.Color(75, 75, 75));
		cmd1.setText("January");
		cmd1.setName("1");
		cmd1.setOpaque(true);
		add(cmd1);
		
		cmd2 = new vista.swing.Button();
		cmd2.setForeground(new java.awt.Color(75, 75, 75));
		cmd2.setText("February");
		cmd2.setName("2");
		cmd2.setOpaque(true);
		add(cmd2);
		
		cmd3 = new vista.swing.Button();
		cmd3.setForeground(new java.awt.Color(75, 75, 75));	
		cmd3.setText("March");
		cmd3.setName("3");
		cmd3.setOpaque(true);
		add(cmd3);
		
		cmd4 = new vista.swing.Button();
		cmd4.setForeground(new java.awt.Color(75, 75, 75));
		cmd4.setText("April");
		cmd4.setName("4");
		cmd4.setOpaque(true);
		add(cmd4);
		
		cmd5 = new vista.swing.Button();
		cmd5.setForeground(new java.awt.Color(75, 75, 75));
		cmd5.setText("May");
		cmd5.setName("5");
		cmd5.setOpaque(true);
		add(cmd5);
		
		cmd6 = new vista.swing.Button();
		cmd6.setForeground(new java.awt.Color(75, 75, 75));
		cmd6.setText("June");
		cmd6.setName("6");
		cmd6.setOpaque(true);
		add(cmd6);
		
		cmd7 = new vista.swing.Button();
		cmd7.setForeground(new java.awt.Color(75, 75, 75));
		cmd7.setText("July");
		cmd7.setName("7");
		cmd7.setOpaque(true);
		add(cmd7);	
		
		cmd8 = new vista.swing.Button();
		cmd8.setForeground(new java.awt.Color(75, 75, 75));
		cmd8.setText("August");
		cmd8.setName("8");
		cmd8.setOpaque(true);
		add(cmd8);
		
		cmd9 = new vista.swing.Button();
		cmd9.setForeground(new java.awt.Color(75, 75, 75));
		cmd9.setText("September");
		cmd9.setName("9");
		cmd9.setOpaque(true);
		add(cmd9);
		
		cmd10 = new vista.swing.Button();
		cmd10.setForeground(new java.awt.Color(75, 75, 75));
		cmd10.setText("October");
		cmd10.setName("10");
		cmd10.setOpaque(true);
		add(cmd10);
		
		cmd11 = new vista.swing.Button();
		cmd11.setForeground(new java.awt.Color(75, 75, 75));
		cmd11.setText("November");
		cmd11.setName("11");
		cmd11.setOpaque(true);
		add(cmd11);
		
		cmd12 = new vista.swing.Button();
		cmd12.setForeground(new java.awt.Color(75, 75, 75));
		cmd12.setText("December");
		cmd12.setName("12");
		cmd12.setOpaque(true);
		add(cmd12);
	}
	 private void addEvent() {
	        for (int i = 0; i < getComponentCount(); i++) {
	            ((Button) getComponent(i)).setEvent(event);
	        }
	    }
	 
	 public Event getEvent() {
	        return event;
	    }

	    public void setEvent(Event event) {
	        this.event = event;
	        addEvent();
	    }

}
