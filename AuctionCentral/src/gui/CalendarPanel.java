package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.AuctionCentralSystem;

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

  private AuctionCentralSystem my_system;
  
  
  
  public CalendarPanel(final AuctionCentralSystem the_system) {
    my_system = the_system;
    
    Calendar todays_date = Calendar.getInstance();
    my_current_month = todays_date.get(Calendar.MONTH);
    my_current_year = todays_date.get(Calendar.YEAR);
    
    JPanel info_panel = getInfoPanel();
    JPanel calendar_display_panel = getCalendarDisplayPanel(todays_date);
    
    add(info_panel, BorderLayout.NORTH);
    add(calendar_display_panel, BorderLayout.CENTER);
    
    

  }



  private JPanel getCalendarDisplayPanel(final Calendar the_calendar) {
    JPanel calendar = new JPanel();
    
    calendar.setPreferredSize(new Dimension(824, 580));
    
    calendar.setLayout(new GridLayout(CALENDAR_HEIGHT, CALENDAR_WIDTH));
    
    int first_day_of_month = the_calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    // TODO: month specific stuff here
    // Add an empty JPanel to the grid, for days before the month starts.
    for (int i = 0; i < first_day_of_month; i++) {
      JPanel empty_panel = new JPanel();
      empty_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      calendar.add(empty_panel );
    }
    
    
    int days_in_month = the_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);;
    // Fill in the days that are actually in the month
    for (int day = 1; day <= days_in_month; day++) {
      Calendar panel_date = new GregorianCalendar();
      panel_date.set(Calendar.YEAR, my_current_year);
      panel_date.set(Calendar.MONTH, my_current_month);
      panel_date.set(Calendar.DAY_OF_MONTH, day);
      calendar.add(getDayPanel(panel_date));
    }
    
    // Add empty JPanels for any day after end of month.
    for (int i = 0; i < CALENDAR_HEIGHT * CALENDAR_WIDTH - first_day_of_month - days_in_month; i++) {
      JPanel empty_panel = new JPanel();
      empty_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      calendar.add(empty_panel);
    }
    
    return calendar;
  }



  private JPanel getDayPanel(Calendar the_date) {
    JPanel day_panel = new JPanel();
    day_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    JPanel label_panel = new JPanel();
    label_panel.setLayout(new FlowLayout());
    
    JLabel day_label = new JLabel(new Integer(the_date.get(Calendar.DAY_OF_MONTH)).toString());
    
    label_panel.add(day_label);
    
    day_panel.add(label_panel, BorderLayout.NORTH);
    
    day_panel.add(new JButton("Auction1"), BorderLayout.CENTER);
    day_panel.add(new JButton("Auction2"), BorderLayout.CENTER);
    //for (Auction a : my_system.getAuctionsForDay(the_date))
    
    
    
    return day_panel;
  }



  private JPanel getInfoPanel() {
    JPanel info = new JPanel();
    info.setPreferredSize(new Dimension(824, 50));
    info.setLayout(new FlowLayout());
    
    add(new JButton("<"));
    
    add(new JLabel(months[my_current_month] + " " + my_current_year));
    
    add(new JButton(">"));
    
    return info;
  }
}
