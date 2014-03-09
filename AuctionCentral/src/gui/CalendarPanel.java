//TODO: Need to track past auctions too

//TODO: Get rid of my_current_month/year fields, and replace with Calendar field (if time)

package gui;

import auction.Auction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

  /**
   * Width of the calendar display, in pixels.
   */
  private static final int CALENDAR_DISPLAY_WIDTH = 824;

  /**
   * Height of the calendar display, in pixels.
   */
  private static final int CALENDAR_DISPLAY_HEIGHT = 580;

  /**
   * Height of the navigation panel, in pixels.
   */
  private static final int NAV_PANEL_HEIGHT = 50;

  /**
   * Width of auction buttons.
   */
  private static final int AUCTION_BUTTON_WIDTH = 95;

  /**
   * Height of auction buttons.
   */
  private static final int AUCTION_BUTTON_HEIGHT = 20;

  /**
   * Number of days wide to make the calendar.
   */
  private static int CALENDAR_WIDTH = 7;

  /**
   * Number of days tall to make the calendar.
   */
  private static int CALENDAR_HEIGHT = 6;

  /**
   * Array of month names. 0 indexed (i.e. element 0 is "January", and 12 is
   * "Decemeber".
   */
  private static String[] months = {"January", "February", "March", "April",
    "May", "June", "July", "August", "September", "October", "November",
    "December" };

  /**
   * Current month being displayed on the calendar.
   */
  private int my_current_month;

  /**
   * Current year being displayed on the calendar.
   */
  private int my_current_year;

  /**
   * The system instance the program is running with.
   */
  private AuctionCentralSystem my_system;

  /**
   * Panel containing month navigation buttons, and current month/year.
   */
  private JPanel my_nav_panel;

  /**
   * Panel containing the actual calendar display.
   */
  private JPanel my_calendar_display_panel;

  /**
   * Label containing the month and year.
   */
  private JLabel my_title_label;

  /**
   * The parent frame of this panel.
   */
  private ApplicationFrame my_app_frame;

  /**
   * Create a CalendarPanel instance.
   * 
   * @param the_system
   *          The system object program is running with.
   * @param the_app_frame
   *          The parent frame.
   */
  public CalendarPanel(final AuctionCentralSystem the_system,
      final ApplicationFrame the_app_frame) {
    my_system = the_system;
    my_app_frame = the_app_frame;

    final Calendar todays_date = Calendar.getInstance();
    my_current_month = todays_date.get(Calendar.MONTH);
    my_current_year = todays_date.get(Calendar.YEAR);

    my_nav_panel = getNavPanel();
    my_calendar_display_panel = getCalendarDisplayPanel(todays_date);

    add(my_nav_panel, BorderLayout.NORTH);
    add(my_calendar_display_panel, BorderLayout.CENTER);
  }

  /**
   * Get a panel containing the display of the month, including linked auction buttons.
   * 
   * @param the_calendar A date (day is disregarded) in the month/year to be displayed.
   * @return A panel with the calendar display.
   */
  private JPanel getCalendarDisplayPanel(final Calendar the_calendar) {
    final JPanel calendar = new JPanel();

    calendar.setPreferredSize(new Dimension(CALENDAR_DISPLAY_WIDTH, CALENDAR_DISPLAY_HEIGHT));

    calendar.setLayout(new GridLayout(CALENDAR_HEIGHT, CALENDAR_WIDTH));

    final Calendar first_day_of_month = new GregorianCalendar();
    first_day_of_month.set(Calendar.YEAR, my_current_year);
    first_day_of_month.set(Calendar.MONTH, my_current_month);
    first_day_of_month.set(Calendar.DAY_OF_MONTH, 0);

    final int first_day_of_week = first_day_of_month.get(Calendar.DAY_OF_WEEK);

    // TODO: month specific stuff here
    // Add an empty JPanel to the grid, for days before the month starts.
    for (int i = 0; i < first_day_of_week; i++) {
      final JPanel empty_panel = new JPanel();
      empty_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      calendar.add(empty_panel);
    }

    final int days_in_month = the_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    
    // Fill in the days that are actually in the month
    for (int day = 1; day <= days_in_month; day++) {
      final Calendar panel_date = new GregorianCalendar();
      panel_date.set(Calendar.YEAR, my_current_year);
      panel_date.set(Calendar.MONTH, my_current_month);
      panel_date.set(Calendar.DAY_OF_MONTH, day);
      calendar.add(getDayPanel(panel_date));
    }

    // Add empty JPanels for any day after end of month.
    for (int i = 0; i < CALENDAR_HEIGHT * CALENDAR_WIDTH - first_day_of_week
        - days_in_month; i++) {
      final JPanel empty_panel = new JPanel();
      empty_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      calendar.add(empty_panel);
    }

    return calendar;
  }

  /**
   * Get the panel for an individual date of the calendar.
   * 
   * @param the_date The date to create a panel for.
   * @return The day panel.
   */
  private JPanel getDayPanel(final Calendar the_date) {
    final JPanel day_panel = new JPanel();
    day_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    final JPanel label_panel = new JPanel();
    label_panel.setLayout(new FlowLayout());

    final JLabel day_label = new JLabel(' '
        + new Integer(the_date.get(Calendar.DAY_OF_MONTH)).toString() + ' ');

    label_panel.add(day_label);

    day_panel.add(label_panel, BorderLayout.NORTH);

    final List<Auction> auctions_on_this_day = getAuctionsOnDay(the_date);

    for (final Auction auction : auctions_on_this_day) {
      JButton auction_button = new JButton(new AbstractAction(auction.getAuctionName()) {
        @Override
        public void actionPerformed(final ActionEvent the_event) {
          my_app_frame.showAuctionInfo(auction, false);
        }
      });
      auction_button.setPreferredSize(new Dimension(AUCTION_BUTTON_WIDTH, 
          AUCTION_BUTTON_HEIGHT));
      day_panel.add(auction_button, BorderLayout.CENTER);
    }

    return day_panel;
  }

  /**
   * Get a list of auctions on the given date.
   * 
   * @param the_date The date to check for auctions.
   * @return The list of auctions.
   */
  private List<Auction> getAuctionsOnDay(final Calendar the_date) {
    //TODO: need to check old auctions too
    final List<Auction> all_auctions = my_system.getAuctionList();
    all_auctions.addAll(my_system.getPastAuctionList());
    
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

  /**
   * Get a panel with navigation buttons and current month/year.
   * 
   * @return The navigation panel.
   */
  private JPanel getNavPanel() {
    final JPanel info = new JPanel();
    info.setPreferredSize(new Dimension(CALENDAR_DISPLAY_WIDTH, NAV_PANEL_HEIGHT));
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

  /**
   * Set the text of the navigation panel. 
   */
  private void setTitleText() {
    my_title_label.setText(months[my_current_month % months.length] + ' '
        + my_current_year);
  }

  /**
   * Replace the displayed calendar with the next month's.
   */
  protected void nextMonth() {
    // TODO Auto-generated method stub
    removeCurrentMonth();

    final Calendar current_display_calendar = new GregorianCalendar();

    my_current_month++;

    // increment year, and reset month to 0 (Jan.) if month goes past 11 (Dec.)
    if (my_current_month == 12) {
      my_current_month = 0;
      my_current_year++;
    }

    current_display_calendar.set(Calendar.YEAR, my_current_year);
    current_display_calendar.set(Calendar.MONTH, my_current_month);

    setTitleText();
    my_calendar_display_panel = getCalendarDisplayPanel(current_display_calendar);
    add(my_calendar_display_panel, BorderLayout.CENTER);
  }

  /**
   * Replace the calendar display with the previous month.
   */
  private void previousMonth() {
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
}

  /**
   * Removes the current month's display panel.
   */
  private void removeCurrentMonth() {
    remove(my_calendar_display_panel);
  }
}
