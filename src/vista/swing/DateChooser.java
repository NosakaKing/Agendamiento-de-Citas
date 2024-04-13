package vista.swing;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class DateChooser extends JPanel {
	
	public JTextField getTextRefernce() {
		return textRefernce;
	}

	public void addEventDateChooser(EventDateChooser event) {
		events.add(event);
	}


	private static final long serialVersionUID = 1L;
	private vista.swing.Button cmdPrevious;
	private vista.swing.Button cmdForward;
	private vista.swing.Slider slide;
	private javax.swing.JLayeredPane MY;
	private vista.swing.Button cmdMonth;
	private vista.swing.Button cmdYear;
	private javax.swing.JPopupMenu popup;
	private JTextField textRefernce;
	private final String MONTH_ENGLISH[] = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private String dateFormat = "dd-MM-yyyy";
	private int MONTH = 1;
	private int YEAR = 2021;
	private int DAY = 1;
	private JPanel header;
	private int STATUS = 1; // 1 is day 2 is month 3 is year
	private int startYear;
	private SelectedDate selectedDate = new SelectedDate();
	private List<EventDateChooser> events;

	@SuppressWarnings("serial")
	public DateChooser() {
		slide = new vista.swing.Slider();
		slide.setBounds(0, 32, 262, 174);
		add(slide);
		slide.setLayout(new BoxLayout(slide, BoxLayout.LINE_AXIS));
		setLayout(null);
		JPanel header = new JPanel();
		header.setBounds(0, 0, 262, 32);
		header.setBackground(new Color(252, 129, 129));
		add(header);
		header.setLayout(null);

		cmdPrevious = new vista.swing.Button();
		cmdPrevious.setBounds(10, 3, 30, 27);
		cmdPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdPreviousActionPerformed(evt);
			}
		});

		cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				cmdPreviousKeyPressed(evt);
			}
		});

		cmdPrevious.setIcon(new ImageIcon(DateChooser.class.getResource("/vista/swing/previous.png")));
		cmdPrevious.setPaintBackground(false);
		header.add(cmdPrevious);

		cmdForward = new vista.swing.Button();
		cmdForward.setBounds(222, 3, 30, 27);
		cmdForward.setIcon(new ImageIcon(DateChooser.class.getResource("/vista/swing/forward.png")));
		cmdForward.setPaintBackground(false);
		header.add(cmdForward);
		cmdForward.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdForwardActionPerformed(evt);
			}
		});
		

		MY = new javax.swing.JLayeredPane();
		MY.setBounds(35, 3, 195, 27);
		header.add(MY);

		cmdMonth = new vista.swing.Button();
		cmdMonth.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdMonthActionPerformed(evt);
			}
		});
		cmdMonth.setForeground(new Color(255, 255, 255));
		cmdMonth.setFont(new java.awt.Font("SansSerif", 1, 14));
		cmdMonth.setBounds(7, 1, 93, 27);
		cmdMonth.setText("January");
		cmdMonth.setPaintBackground(false);
		MY.add(cmdMonth);

		cmdYear = new vista.swing.Button();
		cmdYear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdYearActionPerformed(evt);
			}
		});
		cmdYear.setForeground(new Color(255, 255, 255));
		cmdYear.setFont(new java.awt.Font("SansSerif", 1, 14));
		cmdYear.setBounds(110, 1, 50, 27);
		cmdYear.setText("2018");
		cmdYear.setPaintBackground(false);
		MY.add(cmdYear);

		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(100, 6, 15, 14);
		MY.add(lblNewLabel);
		
		popup = new javax.swing.JPopupMenu() {
		    @Override
		    public void paintComponent(Graphics grphcs) {
		        grphcs.setColor(new Color(114, 113, 113));
		        grphcs.fillRect(0, 0, getWidth(), getHeight());
		        grphcs.setColor(Color.WHITE);
		        grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
		    }
		};
		execute();
		
	}

	private void execute() {
		
		setForeground(new Color(204, 93, 93));
		events = new ArrayList<>();
		popup.add(this);
		toDay(false);
	}

	public void setTextRefernce(JTextField txt) {
		this.textRefernce = txt;
		this.textRefernce.setEditable(false);
		this.textRefernce.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (textRefernce.isEnabled()) {
					showPopup();
				}
			}
		});
		setText(false, 0);
	}

	private void setText(boolean runEvent, int act) {
		if (textRefernce != null) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Date date = df.parse(DAY + "-" + MONTH + "-" + YEAR);
				textRefernce.setText(new SimpleDateFormat(dateFormat).format(date));
			} catch (ParseException e) {
				System.err.println(e);
			}
		}
		if (runEvent) {
			runEvent(act);
		}
	}

	private void runEvent(int act) {
		SelectedAction action = new SelectedAction() {
			@Override
			public int getAction() {
				return act;
			}
		};
		for (EventDateChooser event : events) {
			event.dateSelected(action, selectedDate);
		}
	}

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

	public void toDay() {
		toDay(true);
	}

	private void setDateNext() {
		Dates dates = new Dates();
		dates.setForeground(getForeground());
		dates.setEvent(getEventDay(dates));
		dates.showDate(MONTH, YEAR, selectedDate);
		if (slide.slideToLeft(dates)) {
			cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
			cmdYear.setText(YEAR + "");
		}
	}

	private void setDateBack() {
		Dates dates = new Dates();
		dates.setForeground(getForeground());
		dates.setEvent(getEventDay(dates));
		dates.showDate(MONTH, YEAR, selectedDate);
		if (slide.slideToRight(dates)) {
			cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
			cmdYear.setText(YEAR + "");
		}
	}

	private void setYearNext() {
		Years years = new Years();
		years.setEvent(getEventYear());
		startYear = years.next(startYear);
		slide.slideToLeft(years);
	}

	private void setYearBack() {
		if (startYear >= 1000) {
			Years years = new Years();
			years.setEvent(getEventYear());
			startYear = years.back(startYear);
			slide.slideToLeft(years);
		}
	}

	public void showPopup(Component com, int x, int y) {
		popup.show(com, x, y);
	}

	public void showPopup() {
	    popup.setPreferredSize(new Dimension(262, 206));
		popup.show(textRefernce, 0, textRefernce.getHeight());
	}

	public void hidePopup() {
		popup.setVisible(false);
	}


	private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {
		if (STATUS == 1) { // Date
			if (MONTH == 1) {
				MONTH = 12;
				YEAR--;
			} else {
				MONTH--;
			}
			setDateBack();
		} else if (STATUS == 3) { // Year
			setYearBack();
		} else {
			if (YEAR >= 1000) {
				YEAR--;
				Months months = new Months();
				months.setEvent(getEventMonth());
				slide.slideToLeft(months);
				cmdYear.setText(YEAR + "");
			}
		}
	}

	  @SuppressWarnings("unused")
	private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {                                           
          if (STATUS == 1) {   //  Date
            if (MONTH == 12) {
                MONTH = 1;
                YEAR++;
            } else {
                MONTH++;
            }
            setDateNext();
        } else if (STATUS == 3) {    //  Year
            setYearNext();
        } else {
            YEAR++;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToLeft(months);
            cmdYear.setText(YEAR + "");
        }
    }  

	private void cmdMonthActionPerformed(java.awt.event.ActionEvent evt) {
		if (STATUS != 2) {
			STATUS = 2;
			Months months = new Months();
			months.setEvent(getEventMonth());
			slide.slideToDown(months);
		} else {
			Dates dates = new Dates();
			dates.setForeground(getForeground());
			dates.setEvent(getEventDay(dates));
			dates.showDate(MONTH, YEAR, selectedDate);
			slide.slideToDown(dates);
			STATUS = 1;
		}
	}

	private void cmdYearActionPerformed(java.awt.event.ActionEvent evt) {
		if (STATUS != 3) {
			STATUS = 3;
			Years years = new Years();
			years.setEvent(getEventYear());
			startYear = years.showYear(YEAR);
			slide.slideToDown(years);
		} else {
			Dates dates = new Dates();
			dates.setForeground(getForeground());
			dates.setEvent(getEventDay(dates));
			dates.showDate(MONTH, YEAR, selectedDate);
			slide.slideToDown(dates);
			STATUS = 1;
		}
	}

	private void cmdPreviousKeyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			Component com = slide.getComponent(0);
			if (com instanceof Dates) {
				Dates d = (Dates) com;
				d.up();
			}
		} else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			Component com = slide.getComponent(0);
			if (com instanceof Dates) {
				Dates d = (Dates) com;
				d.down();
			}
		} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			Component com = slide.getComponent(0);
			if (com instanceof Dates) {
				Dates d = (Dates) com;
				d.back();
			}
		} else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			Component com = slide.getComponent(0);
			if (com instanceof Dates) {
				Dates d = (Dates) com;
				d.next();
			}
		}
	}

	  public String getDateFormat() {
	        return dateFormat;
	    }

	    public void setDateFormat(String dateFormat) {
	        this.dateFormat = dateFormat;
	    }
	    
	public void setSelectedDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String d = df.format(date);
		DAY = Integer.valueOf(d.split("-")[0]);
		MONTH = Integer.valueOf(d.split("-")[1]);
		YEAR = Integer.valueOf(d.split("-")[2]);
		selectedDate.setDay(DAY);
		selectedDate.setMonth(MONTH);
		selectedDate.setYear(YEAR);
		Dates dates = new Dates();
		dates.setForeground(getForeground());
		dates.setEvent(getEventDay(dates));
		dates.setSelected(DAY);
		dates.showDate(MONTH, YEAR, selectedDate);
		slide.slideNon(dates);
		cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
		cmdYear.setText(YEAR + "");
		setText(true, 0);
		STATUS = 1;
	}

	public SelectedDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(SelectedDate selectedDate) {
		this.selectedDate = selectedDate;
		DAY = selectedDate.getDay();
		MONTH = selectedDate.getMonth();
		YEAR = selectedDate.getYear();
		Dates dates = new Dates();
		dates.setForeground(getForeground());
		dates.setEvent(getEventDay(dates));
		dates.setSelected(DAY);
		dates.showDate(MONTH, YEAR, selectedDate);
		slide.slideNon(dates);
		cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
		cmdYear.setText(YEAR + "");
		setText(true, 0);
		STATUS = 1;
	}

	@Override
	public void setForeground(Color color) {
		super.setForeground(color);
		if (header != null) {
			header.setBackground(color);
			toDay(false);
		}
	}


}
