package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarPanel extends JPanel {

  /**
   * Required for serializable class (extends JPanel).
   */
  private static final long serialVersionUID = 8363319605363170281L;

  static private int CALENDAR_WIDTH = 7;
  
  static private int CALENDAR_HEIGHT = 5;
  
  static private String[] months = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};
  
  private int my_current_month;
  
  private int my_current_year;
  
  
  
  public CalendarPanel() {
    Calendar todays_date = Calendar.getInstance();
    my_current_month = todays_date.get(Calendar.MONTH);
    my_current_year = todays_date.get(Calendar.YEAR);
    
    JPanel info_panel = getInfoPanel();
    JPanel calendar_display_panel = getCalendarDisplayPanel();
    
    add(info_panel, BorderLayout.NORTH);
    add(calendar_display_panel, BorderLayout.CENTER);
    
    //repaint();
  }



  private JPanel getCalendarDisplayPanel() {
    JPanel calendar = new JPanel();
    
    calendar.setPreferredSize(new Dimension(824, 580));
    
    calendar.setLayout(new GridLayout(CALENDAR_WIDTH, CALENDAR_HEIGHT));
    
    
    return calendar;
  }



  private JPanel getInfoPanel() {
    JPanel info = new JPanel();
    info.setPreferredSize(new Dimension(824, 100));
    info.setLayout(new FlowLayout());
    
    add(new JButton("<"));
    
    add(new JLabel(months[my_current_month] + " " + my_current_year));
    
    add(new JButton(">"));
    
    return info;
  }
}
