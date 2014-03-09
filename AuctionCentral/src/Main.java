import gui.ApplicationFrame;

import java.awt.EventQueue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Observable;

import javax.swing.JOptionPane;

import system.AuctionCentralSystem;
import user.AuctionCentralStaff;
import user.NonProfitUser;
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
 * @version 3/2/2014
 */
public final class Main {
  //TODO: Remove DEFAULT_USER from final build (should be decided by login window)
  //  f
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

        final AuctionCentralSystem system = new AuctionCentralSystem();

        //final User user = DEFAULT_STAFF_USER;
        User login_user = null;

        boolean valid_login = false;

        while (!valid_login) {
          // show a JOptionPane to get the username.
          final String input_username = JOptionPane.showInputDialog(null, "Enter username: ", 
              "AuctionCentral Login", JOptionPane.QUESTION_MESSAGE);
          // if JOptionPane clicks 'cancel', exit
          if (input_username == null) {
            System.exit(0);
          } else {
            // get the User object corresponding to the String username
            login_user = system.isValidUser(input_username);
            // if User is null, the username was not valid
            if (login_user != null) {
              valid_login = true;
            }
          }
        }

        // create a new notifier to tell system which user logs in
        final RoleNotifier user_type_notifier = new RoleNotifier(system);
        user_type_notifier.changeRole(login_user);

        //start the gui
        final ApplicationFrame gui = new ApplicationFrame(login_user, system);
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
    // TODO Auto-generated constructor stub
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

