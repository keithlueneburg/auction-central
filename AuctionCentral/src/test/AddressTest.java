
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bidding.Address;

/**
 * Class: AddressTest
 * 
 * Contains tests for class: Address
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 */
public class AddressTest {
	
	/** First address. */
	private Address one = new Address("123", 1, "k", "p", 123, null);
	
	/** Second address. */
	private Address two = new Address("123", 1, "k", "p", 123, null);
	
	/** Third address. */
	private Address three = new Address("1234", 2, "p", "k", 22, null);

	@Test
	/**
	 * A class is equal to itself.
	 */
	public void testEqualToSelf() {
	
	    assertTrue("Class equal to itself.", one.equals(one));
	}
	
	/**
	 * one.equals(WrongType) must return false;
	 */
	@Test
	public void testPassIncompatibleType() {
	    assertFalse("Passing incompatible object to equals should return false", one.equals("string"));
	}
	
	/**
	 * one.equals(null) must return false;
	 */
	@Test
	public void testNullReference() {
	    assertFalse("Passing null to equals should return false", one.equals(null));
	}
	
	/**
	 * Tests if two addresses are equal.
	 */
	@Test
	public void testEquals() {
	    assertTrue("Not the same.", one.equals(two));
	    assertTrue("Not the same.", two.equals(one));
	}
	
	/**
	 * Tests if two addresses are equal.
	 */
	@Test
	public void testDifferent() {
	    assertFalse("Should not be the same.", one.equals(three));
	    assertFalse("Should not be the same.", two.equals(three));
	}	
}
