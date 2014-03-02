package user;

import java.util.ArrayList;
import java.util.List;

import auction.Bid;
import bidding.Address;
import bidding.CreditCard;

public class Bidder extends AbstractUser
{
	private CreditCard myCard;
	private Address myAddress;
	private List<Bid> myBids;
	
	public Bidder(String aUserName, String aPassword, String aFirstName, 
			String aLastName, CreditCard aCard, Address anAddress)
	{
		super(aUserName, aPassword, aFirstName, aLastName);
		myCard = aCard;
		myAddress = anAddress;
		myBids = new ArrayList<Bid>();
	}

	public CreditCard getCard()
	{
		return myCard;
	}

	public void setCard(CreditCard aCard)
	{
		myCard = aCard;
	}

	public Address getAddress()
	{
		return myAddress;
	}

	public void setAddress(Address anAddress)
	{
		myAddress = anAddress;
	}

	public List<Bid> getBids()
	{
		return myBids;
	}
	
	public void addBid(Bid aBid)
	{
		myBids.add(aBid);
	}
}
