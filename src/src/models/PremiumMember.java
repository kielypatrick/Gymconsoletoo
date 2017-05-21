package models;
public class PremiumMember extends Member {

	/**
	 * Constructor for objects of class PremiumMember.
	 */

	public PremiumMember(String memberEmail, String memberName, String memberAddress,
						 String gender, double height, double startingWeight, String chosenPackage) {

		super(memberEmail, memberName, memberAddress, gender, height, startingWeight, chosenPackage);
	}

	@Override
	public void chosenPackage(String chosenPackage) {

	}
}
	

