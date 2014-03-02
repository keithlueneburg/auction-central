import gui.ApplicationFrame;

import java.awt.EventQueue;

import user.AbstractUser;
import user.AuctionCentralStaff;
import user.User;

public final class Main {
  //TODO: Remove from final build
  private static User DEFAULT_USER = new AuctionCentralStaff("xx_username_xx",
      "password", "John", "Smith"); 
  
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
        final ApplicationFrame gui = new ApplicationFrame(DEFAULT_USER);
        gui.start();
      }
    });
  }
}
