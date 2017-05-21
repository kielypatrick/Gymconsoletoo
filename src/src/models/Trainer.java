package models;
public class Trainer extends Person {
		
	private String specialty;
	/**
	 * Constructor for objects of class Trainer.
	 */
	
	public Trainer(String memberEmail, String memberName, String memberAddress, 
	        String gender, String specialty){
		
		super(memberEmail, memberName, memberAddress, gender);
		this.specialty = specialty;
		
}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
