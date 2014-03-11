package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import auction.Auction;
import system.AuctionCentralSystem;
import user.AuctionCentralStaff;
import user.Bidder;
import user.Guest;
import user.User;

/**
 * Class: MenuPanel
 * 
 * A JPanel which contains the software logo, as well as navigation buttons.
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
 * @version 3/2/2014
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

  /**
   * Default width of the menu panel.
   */
  private static final int DEFAULT_WIDTH = 200;

  /**
   * Default height of the menu panel.
   */
  private static final int DEFAULT_HEIGHT = 680;

  /**
   * Default logo height.
   */
  private static final int LOGO_HEIGHT = 200;

  /**
   * Default label panel height.
   */
  private static final int LABEL_PANEL_HEIGHT = 1150;

  /**
   * Path to the logo image file.
   */
  private static final String LOGO_IMAGE_PATH = "images/logo.gif";

  /**
   * Height of the button panel.
   */
  private static final int BUTTON_PANEL_HEIGHT = 365;

  /**
   * Number of buttons to create spots for in menu.
   */
  private static final int MAX_BUTTON_SLOTS = 5;

  /**
   * Menu button for displaying auction list.
   */
  private JButton my_auctions_button;

  /**
   * Menu button for displaying calendar.
   */
  private JButton my_calendar_button;
  
  //////////////////////////////////////
  /// Bidder Button
  //////////////////////////////////////
  private JButton my_bids_button;

  /**
   * Button for exiting program.
   */
  private JButton my_exit_button;
  
  /**
   * The parent container of this panel.
   */
  private ApplicationFrame my_application_frame;
  
  /**
   * The auction central system.
   */
  private AuctionCentralSystem my_system;

  private JButton my_register_button;

  /**
   * Instantiate a new menu panel object.
   * 
   * @param a_user
   *          The user type that is logged in to the program.
   * @param an_application_frame 
   * @param a_system the system
   */
  public MenuPanel(final User a_user, final ApplicationFrame an_application_frame,
      final AuctionCentralSystem a_system) {
    my_system = a_system;
    my_application_frame = an_application_frame;
    setup(a_user);
    setButtonVisibility(a_user);
  }

  /**
   * Set up various properties of the menu panel, buttons, and logo.
   * 
   * @param a_user
   *          The user type that is logged in to the program.
   */
  private void setup(final User a_user) {
    
    setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    //setLayout(new GridLayout(3, 1));

    final JPanel logo_panel = getLogoPanel();

    final JPanel button_panel = getButtonPanel();

    final JPanel label_panel = getLabelPanel(a_user);
    
    add(logo_panel, BorderLayout.NORTH);
    add(button_panel, BorderLayout.CENTER);
    add(label_panel, BorderLayout.SOUTH);
  }

  /**
   * Gets the JPanel containing navigation buttons.
   * 
   * @return the button panel.
   */
  private JPanel getButtonPanel() {
    //TODO: Clean up once menu panel finalized
    
    final JPanel button_panel = new JPanel();
    button_panel.setPreferredSize(new Dimension(DEFAULT_WIDTH, BUTTON_PANEL_HEIGHT));
    button_panel.setLayout(new GridLayout(MAX_BUTTON_SLOTS, 1));
    
    createAuctionsButton();
    createCalendarButton();
    createBidsButton();
    createRegisterButton();
    createExitButton();
    
    button_panel.add(my_calendar_button);
    button_panel.add(my_auctions_button);
    button_panel.add(my_bids_button);
    button_panel.add(my_register_button);
    button_panel.add(my_exit_button);
    
    return button_panel;
  }

  /**
   * Initializes the exit button. 
   */
  private void createExitButton() {
    my_exit_button = new JButton(new AbstractAction("Exit") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_system.savingData();
        my_application_frame.dispose();
      }
    });
  }

  /**
   * Initializes the auctions button.
   */
  private void createAuctionsButton() {
    my_auctions_button = new JButton(new AbstractAction("Auctions") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_application_frame.showAuctionList();

      }
    });
  }
  
  /**
   * Initializes calendar button.
   */
  private void createCalendarButton() {
    my_calendar_button = new JButton(new AbstractAction("Calendar") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_application_frame.showCalendar();
      }
    });
  }
  
  /////////////////////////
  //////
  ////////////////////////
  private void createBidsButton() {
    my_bids_button = new JButton(new AbstractAction("My Bids") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_application_frame.showBidList();
      }
    });
  }
  
  /**
   * Initializes register button.
   */
  private void createRegisterButton() {
    my_register_button = new JButton(new AbstractAction("Register") {
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_application_frame.showRegistration();
      }
    });
  }
  
  /**
   * Gets the JPanel containing the user info labels.
   * 
   * @param a_user
   *          The user type that is logged in to the program.
   * 
   * @return the label panel.
   */
  private JPanel getLabelPanel(final User a_user) {
    final JPanel label_panel = new JPanel();
    label_panel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
        LABEL_PANEL_HEIGHT));
    
    label_panel.setBackground(Color.LIGHT_GRAY);
    
    label_panel.add(new JLabel("User: " + a_user.getUsername()), BorderLayout.SOUTH);
    label_panel.add(new JLabel("Role: " + a_user.getClass().getSimpleName()),
        BorderLayout.NORTH);
    return label_panel;
  }

  /**
   * Gets a JPanel containing the logo.
   * 
   * @return the JPanel containing the logo
   */
  private JPanel getLogoPanel() {
    final JPanel logo_panel = new JPanel();
    logo_panel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
        LOGO_HEIGHT));

    logo_panel.setBackground(Color.BLACK);

    final JLabel logo_image = new JLabel();
    logo_image.setIcon(new ImageIcon(LOGO_IMAGE_PATH));

    logo_image.setVerticalAlignment(SwingConstants.CENTER);
    logo_image.setHorizontalAlignment(SwingConstants.CENTER);

    logo_panel.add(logo_image, BorderLayout.NORTH);

    return logo_panel;
  }
  
  /**
   * Sets button visibility to false if they should not be available to the user
   * type that is logged in.
   * 
   * @param a_user
   *          The user that determines what buttons are available.
   */
  private void setButtonVisibility(final User a_user) {
    my_register_button.setVisible(false);
    if (!(a_user instanceof AuctionCentralStaff)) {
      my_calendar_button.setVisible(false);
    }
    if ((a_user instanceof Bidder) || (a_user instanceof Guest)) {
      my_register_button.setVisible(true);
    }
    if (!(a_user instanceof Bidder)) {
      my_bids_button.setEnabled(false);
      my_bids_button.setVisible(false);
    }
  }

  public void disableRegistration() {
    // TODO Auto-generated method stub
    my_register_button.setEnabled(false);
  }
}
