package test;

import static org.junit.Assert.*;

import bidding.Address;

import org.junit.Test;


/**
 * Contains tests for class: Address
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * @version 1.0
 * @author Casey Morrison
 */
public class AddressTest {

  /** First address. */
  private Address my_one = new Address("123", 1, "k", "p", 123);

  /** Second address. */
  private Address my_two = new Address("123", 1, "k", "p", 123);

  /** Third address. */
  private Address my_three = new Address("1234", 2, "p", "k", 22);

  /**
   * A class is equal to itself.
   */
  @Test
  public void testEqualToSelf() {

    assertTrue("Class equal to itself.", my_one.equals(my_one));
  }

  /**
   * one.equals(WrongType) must return false.
   */
  @Test
  public void testPassIncompatibleType() {
    assertFalse("Passing incompatible object to equals should return false",
        "string".equals("my_one"));
  }

  /**
   * one.equals(null) must return false.
   */
  @Test
  public void testNullReference() {
    assertFalse("Passing null to equals should return false", my_one == null);
  }

  /**
   * Tests if two addresses are equal.
   */
  @Test
  public void testEquals() {
    assertTrue("Not the same.", my_one.equals(my_two));
    assertTrue("Not the same.", my_two.equals(my_one));
  }

  /**
   * Tests if two addresses are equal.
   */
  @Test
  public void testDifferent() {
    assertFalse("Should not be the same.", my_one.equals(my_three));
    assertFalse("Should not be the same.", my_two.equals(my_three));
  }
}
