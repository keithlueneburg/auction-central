package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import user.AuctionCentralStaff;
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
   * Default button width.
   */
  private static final int BUTTON_WIDTH = 300;

  /**
   * Default button height.
   */
  private static final int BUTTON_HEIGHT = 45;

  /**
   * Height of the button panel.
   */
  private static final int BUTTON_PANEL_HEIGHT = 365;

  /**
   * Menu button for displaying auction list.
   */
  private JButton my_auctions_button;

  /**
   * Menu button for displaying calendar.
   */
  private JButton my_calendar_button;

  /**
   * Instantiate a new menu panel object.
   * 
   * @param a_user
   *          The user type that is logged in to the program.
   */
  public MenuPanel(final User a_user) {
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
    final JPanel button_panel = new JPanel();
    button_panel.setPreferredSize(new Dimension(DEFAULT_WIDTH, BUTTON_PANEL_HEIGHT));
    button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));
    
    my_auctions_button = new JButton("Auctions");
    my_auctions_button.setAlignmentX(Component.CENTER_ALIGNMENT);
    //my_auctions_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));    
    
    my_calendar_button = new JButton("Calendar");
    my_calendar_button.setAlignmentX(Component.CENTER_ALIGNMENT);
    //my_calendar_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));        
    
    button_panel.add(my_calendar_button);
    button_panel.add(my_auctions_button);
    
    return button_panel;
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
    if (!(a_user instanceof AuctionCentralStaff)) {
      my_calendar_button.setVisible(false);
    }
  }

  

}
