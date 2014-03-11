import gui.ApplicationFrame;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.AuctionCentralSystem;
import user.Guest;
import user.User;


/**
 * Class: Main
 * 
 * Responsible for initial instantiation of GUI and program.
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
 * @author Josh Hammer edits on the login screen
 * @version 3/2/2014
 */
public final class Main {
  
  private static AuctionCentralSystem my_system;

  /**
   * A guest user for registration online
   */
  private static User DEFAULT_GUEST_USER = new Guest("New User", "password",
      "guest", "user");
  
  /**
   * Private constructor, to prevent instantiation of this class.
   */
  private Main() {
    throw new IllegalStateException();
  }

  /**
   * The main method, invokes the AuctionCentral GUI. Command line arguments are
   * ignored.
   * 
   * @param the_args Command line arguments.
   */
  public static void main(final String[] the_args) {
    EventQueue.invokeLater(new Runnable()
    {  
      @Override
      public void run() {
        try {
          my_system = new AuctionCentralSystem();
        } catch (IOException ex) {
          System.exit(0);
        }
        User login_user = null;
        
        boolean valid_login = false;
        while (!valid_login) {
          // show a JOptionPane to get the username.
          final Object[] options = {"Ok", "Cancel", "Guest"};
          final JPanel login_panel = new JPanel();
          login_panel.add(new JLabel("Enter username:"));
          final JTextField login_text_field = new JTextField(10);
          login_panel.add(login_text_field);
          final int input_username = JOptionPane.showOptionDialog(null, login_panel, 
              "AuctionCentral Login", JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
          // if JOptionPane clicks 'cancel', exit
          if (input_username == JOptionPane.NO_OPTION 
              || input_username == -1) {
            System.exit(0);
          } else if (input_username == JOptionPane.CANCEL_OPTION) {
            login_user = DEFAULT_GUEST_USER;
            valid_login = true;
          } else {
            login_user = my_system.isValidUser(login_text_field.getText());
          
            if (login_user != null) {
              valid_login = true;
            }
          }

        }
        
        final RoleNotifier user_type_notifier = new RoleNotifier(my_system);
        user_type_notifier.changeRole(login_user);
        
        final ApplicationFrame gui = new ApplicationFrame(login_user, my_system);
        gui.start();
        
       
//      while (!valid_login) {
//      // show a JOptionPane to get the username.
//      final String input_username = JOptionPane.showInputDialog(null, "Enter username: ", 
//          "AuctionCentral Login", JOptionPane.QUESTION_MESSAGE);
//      // if JOptionPane clicks 'cancel', exit
//      if (input_username == null) {
//        System.exit(0);
//      } else if (input_username.equals("staff")) {
//        user = DEFAULT_STAFF_USER;
//      } else if (input_username.equals("nonprofit")) {
//        user = DEFAULT_NPO_USER;
//      } else if (AuctionCentralSystem.isValidUser_static(input_username)) {
//      }
//        valid_login = true;
//      }
//    final ApplicationFrame gui = new ApplicationFrame(user, system);
//    gui.start();
//    }
      }
        
      });
  }
}

/**
 * Changes the user role currently logged in to the system.
 * 
 * @author Keith Lueneburg
 * @version 3/9/2014
 *
 */
final class RoleNotifier extends Observable {
  
  /**
   * Create a new notifier for the given system.
   * 
   * @param the_system The system to notify.
   */
  public RoleNotifier(final AuctionCentralSystem the_system) {
    addObserver(the_system);
  }
  
  /**
   * Change the access role to the specified user.
   * 
   * @param the_user The user to give access to.
   */
  void changeRole(final User the_user) {
    setChanged();
    notifyObservers(the_user);
  }
}

