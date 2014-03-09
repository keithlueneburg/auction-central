//TODO: Need to track past auctions too

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auction.Auction;

import system.AuctionCentralSystem;

/**
 * Class: CalendarPanel
 * 
 * The JPanel that contains the calendar view for the AuctionCentral program.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * 
 * @version 3/8/2014
 */
public class CalendarPanel extends JPanel {

  /**
   * Required for serializable class (extends JPanel).
   */
  private static final long serialVersionUID = 8363319605363170281L;

  static private int CALENDAR_WIDTH = 7;
  
  static private int CALENDAR_HEIGHT = 6;
  
  static private String[] months = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};
  
  private int my_current_month;
  
  private int my_current_year;

  private AuctionCentralSystem my_system;

  private JPanel my_info_panel;

  private JPanel my_calendar_display_panel;

  private JLabel my_title_label;

  private ApplicationFrame my_app_frame;
  
  
  
  public CalendarPanel(final AuctionCentralSystem the_system, final ApplicationFrame the_app_frame) {
    my_system = the_system;
    my_app_frame = the_app_frame;
    
    Calendar todays_date = Calendar.getInstance();
    my_current_month = todays_date.get(Calendar.MONTH);
    my_current_year = todays_date.get(Calendar.YEAR);
    
    my_info_panel = getInfoPanel();
    my_calendar_display_panel = getCalendarDisplayPanel(todays_date);
    
    add(my_info_panel, BorderLayout.NORTH);
    add(my_calendar_display_panel, BorderLayout.CENTER);
  }

  private JPanel getCalendarDisplayPanel(final Calendar the_calendar) {
    JPanel calendar = new JPanel();
    
    calendar.setPreferredSize(new Dimension(824, 580));
    
    calendar.setLayout(new GridLayout(CALENDAR_HEIGHT, CALENDAR_WIDTH));
    
    
    Calendar first_day_of_month = new GregorianCalendar();
    first_day_of_month.set(Calendar.YEAR, my_current_year);
    first_day_of_month.set(Calendar.MONTH, my_current_month);
    first_day_of_month.set(Calendar.DAY_OF_MONTH, 0);
    
    int first_day_of_week = first_day_of_month.get(Calendar.DAY_OF_WEEK);
    
    // TODO: month specific stuff here
    // Add an empty JPanel to the grid, for days before the month starts.
    for (int i = 0; i < first_day_of_week; i++) {
      final JPanel empty_panel = new JPanel();
      empty_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      calendar.add(empty_panel);
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
    for (int i = 0; i < CALENDAR_HEIGHT * CALENDAR_WIDTH - first_day_of_week - days_in_month; i++) {
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
    
    JLabel day_label = new JLabel(" " + new Integer(the_date.get(Calendar.DAY_OF_MONTH)).toString() + " ");
    
    label_panel.add(day_label);
    
    day_panel.add(label_panel, BorderLayout.NORTH);
    
    List<Auction> auctions_on_this_day = getAuctionsOnDay(the_date);
    
    for (final Auction the_auction : auctions_on_this_day) {
      JButton auction_button = new JButton((new AbstractAction(the_auction.getAuctionName()) {
        @Override
        public void actionPerformed(final ActionEvent the_event) {
          my_app_frame.showAuctionInfo(the_auction, false);
        }
      }));
      auction_button.setPreferredSize(new Dimension(95, 20));
      day_panel.add((auction_button), BorderLayout.CENTER);
    }
    
    return day_panel;
  }



  private List<Auction> getAuctionsOnDay(final Calendar the_date) {
    final List<Auction> all_auctions = my_system.getAuctionList();
    
    final List<Auction> day_auctions = new ArrayList<Auction>();
    
    for (Auction auction : all_auctions) {
      if (auction.getAuctionDate().get(Calendar.YEAR) == the_date.get(Calendar.YEAR) 
          && auction.getAuctionDate().get(Calendar.MONTH) == the_date.get(Calendar.MONTH)
          && auction.getAuctionDate().get(Calendar.DAY_OF_MONTH) == the_date.get(Calendar.DAY_OF_MONTH)) {
        day_auctions.add(auction);
      }
    }
    return day_auctions;
  }



  private JPanel getInfoPanel() {
    final JPanel info = new JPanel();
    info.setPreferredSize(new Dimension(824, 50));
    info.setLayout(new FlowLayout());
    
    JButton back_button = new JButton(new AbstractAction("<") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        previousMonth();
      }
    });
    
    add(back_button);
    
    my_title_label = new JLabel();
    setTitleText();
    add(my_title_label);
    
    
    JButton next_button = new JButton(new AbstractAction(">") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        nextMonth();
      }
    });
    
    add(next_button);
    
    return info;
  }



  private void setTitleText() {
    // TODO Auto-generated method stub
    
    my_title_label.setText(months[my_current_month % 12] + " " + my_current_year);
  }



  protected void nextMonth() {
    // TODO Auto-generated method stub
    removeCurrentMonth();
    
    Calendar current_display_calendar = new GregorianCalendar();
    
    my_current_month++;
    
    if (my_current_month == 12) {
      my_current_month = 0;
      my_current_year++;
    }
    
    current_display_calendar.set(Calendar.YEAR, my_current_year);
    current_display_calendar.set(Calendar.MONTH, my_current_month);
    
    setTitleText();
    my_calendar_display_panel = getCalendarDisplayPanel(current_display_calendar);
    add(my_calendar_display_panel, BorderLayout.CENTER);
    
    //repaint();
  }

  /**
   * Replace the calendar display with the previous month. 
   */
  private void previousMonth() {
 // TODO Auto-generated method stub
    removeCurrentMonth();
    
    final Calendar current_display_calendar = new GregorianCalendar();
    
    my_current_month--;
    
    if (my_current_month == -1) {
      my_current_month = Calendar.DECEMBER;
      my_current_year--;
    }
    
    current_display_calendar.set(Calendar.YEAR, my_current_year);
    current_display_calendar.set(Calendar.MONTH, my_current_month);
    
    setTitleText();
    my_calendar_display_panel = getCalendarDisplayPanel(current_display_calendar);
    add(my_calendar_display_panel, BorderLayout.CENTER);
    
    //repaint();
  }

  /**
   * Removes the current month's display panel.
   */
  private void removeCurrentMonth() {
    remove(my_calendar_display_panel);
  }
}
