package models;

/**
 * This class stores information about a person. This class
 * serves as a superclass for more specific person types.
 * 
 * @author Patrick Kiely
 * @version 1.0
 */
public abstract class Person 
{
	private String memberEmail;
	private String memberName;  
    private String memberAddress; 
    public String gender;

    /**
     * Constructor for objects of class Person.
     * 
     * 
     */
    public Person(String memberEmail, String memberName, String memberAddress, 
             String gender){
  
      this.memberEmail = memberEmail;
  
  if (memberName.length() <= 30){
      this.memberName = memberName;
  }
  else{
      this.memberName = memberName.substring(0,30);
  }
  
  

  this.memberAddress = memberAddress;

  
  if ((gender.toUpperCase().equals("M")) || (gender.toUpperCase().equals("F"))){
      this.gender = gender.toUpperCase();
  }
  else{
      this.gender = "Unspecified";
  }
}
  //***********************************************************************************
    //  GETTERS
    //***********************************************************************************
    /** 
     * Returns the email for the member.
     * @return the member's email
     */
    public String getMemberEmail(){
        return memberEmail;
    }   

    /**
     * Returns the member's name.
     * @return the member's name
     */       
    public String  getMemberName(){
        return memberName;
    }
    
    /**
     * Returns the member's address.
     * @return the member's address
    **/
    public String getMemberAddress(){
        return memberAddress;
    }
    
    /**
     * Returns the member's gender.
     * @return the member's gender
    **/
    public String getMemberGender(){
        return gender;
    }

    //***********************************************************************************
    //  SETTERS
    //*********************************************************************************** 
    /**
     * Updates the member id field.
     * 
     */
    public void setMemberEmail(String memberEmail){ 
        
            this.memberEmail = memberEmail;
        }
    
    /** 
     * Updates the member name field.
     * @param memberName The member's name should be no more than 30 characters.  If the 
     *                   entered name exceeds 30 characters, the extra characters will be 
     *                   truncated and only only the first 30 characters will be retained.        
     */
    public void  setMemberName(String memberName){
        if (memberName.length() <= 30){
            this.memberName = memberName;
        }
        else{
            this.memberName = memberName.substring(0,30);
        }
    }      

    /**
     * Updates the member address field.
     * @param memberAddress There is no validation on the member's address.
     */
    public void setMemberAddress(String memberAddress){
        this.memberAddress = memberAddress;
    }
    /** 
     * Updates the member's gender field.
     * @param gender The member's gender i.e. can be either "M" or "F". All other values are ignored.
     */
    public void setGender(String gender)
    {
        if ((gender.toUpperCase().equals("M")) || (gender.toUpperCase().equals("F"))){
            this.gender = gender.toUpperCase();
        }
    }
    
    /**
     * Returns a human-readable String representation of the object state.
     * 
     * @return a string version of the Person object.  The String returned is similar to this structure:
     * 
     * <pre>
     * 
     *     Name: Joe Soap, Email: ****@*.*, Address: 12 High Street, Waterford.
     *         Gender: M
     * </pre>
     */
    public String toString(){
        return ("Name:"  + memberName 
              + ", Email: " + memberEmail
              + "\n Address:" + memberAddress + "."
              + "\n Gender: "+ gender);
    }
    }

