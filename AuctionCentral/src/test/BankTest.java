package test;



import static org.junit.Assert.*;

import bidding.Bank;
import org.junit.Test;



/**
 * Contains tests for class: Bank
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
public class BankTest {

  /** First bank. */
  private Bank my_one = new Bank("two");
  
  /** Second bank. */
  private Bank my_two = new Bank("one");
  
  /** Third bank. */
  private Bank my_three = new Bank("");
  
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
        "string".equals(my_one));
  }
  
  /**
   * one.equals(null) must return false.
   */
  @Test
  public void testNullReference() {
    assertFalse("Passing null to equals should return false", my_one == null);
  }
  
  /**
   * Tests if two banks are equal.
   */
  @Test
  public void testEquals() {
    assertTrue("Not the same.", my_one.equals(my_one));
    assertTrue("Not the same.", my_two.equals(my_two));
  }
  
  /**
   * Tests if two banks are equal.
   */
  @Test
  public void testDifferent() {
    assertFalse("Should not be the same.", my_one.equals(my_three));
    assertFalse("Should not be the same.", my_two.equals(my_three));
  }
  
}


