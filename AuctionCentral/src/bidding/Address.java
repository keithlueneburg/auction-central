package bidding;

import java.util.List;

/**
 * Class: Address
 * 
 * Represents an address used for billing and to keep bidder data.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * 
 * @version 1.0 Winter 2014
 */
public final class Address {
  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";
  
  /** The Street number. */
  private String my_street;

  /** The Apartment number. */
  private int my_apt = 0;

  /** The City name. */
  private String my_city;

  /** The State name. */
  private String my_state;

  /** The Zipcode. */
  private int my_zip;

  //TODO: Should this just be a static class level list?
  /** The list of states. */
  private List<String> my_state_list;

  /**
   * @param a_street
   * @param an_apt
   * @param a_city
   * @param a_state
   * @param a_zip
   * @param a_state_list
   */
  public Address(final String a_street, final int an_apt, final String a_city, 
      final String a_state, final int a_zip, final List<String> a_state_list) {
    my_street = a_street;
    my_apt = an_apt;
    my_city = a_city;
    my_state = a_state;
    my_zip = a_zip;
    my_state_list = a_state_list;
  }

  /**
   * @return the Street number.
   */
  public String getMyStreet() {
    return my_street;
  }

  /**
   * @param a_street
   *          the myStreet to set.
   */
  public void setMyStreet(final String a_street) {
    this.my_street = a_street;
  }

  /**
   * @return the apartment.
   */
  public int getMyApt() {
    return my_apt;
  }

  /**
   * @param an_apt
   *          the apartment to set.
   */
  public void setMyApt(final int an_apt) {
    this.my_apt = an_apt;
  }

  /**
   * @return the City.
   */
  public String getMyCity() {
    return my_city;
  }

  /**
   * @param a_city
   *          the myCity to set.
   */
  public void setMyCity(final String a_city) {
    this.my_city = a_city;
  }

  /**
   * @return the State.
   */
  public String getMyState() {
    return my_state;
  }

  /**
   * @param a_state
   *          the State to set.
   */
  public void setMyState(final String a_state) {
    this.my_state = a_state;
  }

  /**
   * @return the zipcode.
   */
  public int getMyZip() {
    return my_zip;
  }

  /**
   * @param a_zip
   *          the myZip to set.
   */
  public void setMyZip(final int a_zip) {
    this.my_zip = a_zip;
  }

  /**
   * @return the myStateList.
   */
  public List<String> getMyStateList() {
    return my_state_list;
  }

  /**
   * @param a_state_list
   *          the myStateList to set
   */
  public void setMyStateList(final List<String> a_state_list) {
    this.my_state_list = a_state_list;
  }

  /**
   * Check to see if this Address matches another Address.
   * <p>
   * <dt><b> Precondition: Address has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The Address has not been changed. </b>
   * <dd>
   * 
   * @param an_object
   *          The object to compare this Address with.
   * @return True if the other object is also an Address, and it represents the
   *         same address as the address we are checking.
   */
  @Override
  public boolean equals(final Object an_object) {
    boolean is_equal = true;

    // Check for correct object type, and set flag to false if incorrect
    if (!(an_object instanceof Address)) {
      is_equal = false;
    } else {
      // If object is an Address, cast it to an Address
      final Address other = (Address) an_object;

      // Compare field by field for equality and set flag to false if
      // a field doesn't match
      if (!my_street.equals(other.getMyStreet())) {
        is_equal = false;
      } else if (my_apt != other.getMyApt()) {
        is_equal = false;
      } else if (my_city != other.getMyCity()) {
        is_equal = false;
      } else if (my_state != other.getMyState()) {
        is_equal = false;
      } else if (my_zip != (other.getMyZip())) {
        is_equal = false;
      }
    }
    return is_equal;
  }

  /**
   * Get a String representation of the Address.
   * <p>
   * <dt><b> Precondition: Address has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The Address has not been changed. </b>
   * <dd>
   * 
   * @return A string representation of the Address.
   * @author Keith Lueneburg (Minor changes)
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Address:\n");
    sb.append(my_street);
    if (my_apt > 0) {
      sb.append(" " + my_apt);
    }
    sb.append(LINE_SEPARATOR + my_city + " " + my_state + " ");
    sb.append(my_zip);
    sb.append(LINE_SEPARATOR);

    return sb.toString();
  }
}
