package models;

/**
 * This class stores information Members.This class
 * serves as a superclass for more specific person types.
 * 
 * @author Patrick Kiely
 * @version 1.0
 */

public abstract class Member extends Person{
	private double height;           
    private double startingWeight;
    private String chosenPackage;




/**
 * Constructor for objects of class Member.
 * 
 *
 * @param height          The member's height is measured in Metres.  A minimum height of
 *                        one metre (inclusive) is allowed and a maximum height of three metres (inclusive).
 * @param startingWeight  The member's weight upon joining the gym (in kgs).  A minimum 
 *                        weight of 35kg (inclusive) and a max of 250kg (inclusive) is permitted in the gym.
 
 */

public Member(String memberEmail, String memberName, String memberAddress, 
        String gender, double height, double startingWeight, String chosenPackage){
	
	super(memberEmail, memberName, memberAddress, gender);
	if ((height >= 1) && (height <= 3)){
		this.setHeight(height);
	}
	else{
		this.setHeight(0);
	}

	if ((startingWeight >= 35) && (startingWeight <= 250)){
		this.setStartingWeight(startingWeight);
	}
	else{
		this.setStartingWeight(0);
	}
	this.chosenPackage(chosenPackage);
	}




public String getChosenPackage() {
	return chosenPackage;
}




public abstract void chosenPackage(String chosenPackage);



public double getStartingWeight() {
	return startingWeight;
}

public void setStartingWeight(double startingWeight) {
	this.startingWeight = startingWeight;
}

public double getHeight() {
	return height;
}

public void setHeight(double height) {
	this.height = height;
}

/**
 * This method returns the member height converted from metres to inches.
 * @return member height converted from metres to inches using the formula: meters 
 * multiplied by 39.37.  The number returned is truncated to two decimal places.
 **/   
public double convertHeightMetresToInches(){
    return toTwoDecimalPlaces(height * 39.37);
}



/**
* This method returns the member weight converted from KGs to pounds.
* @return member weight converted from KGs to pounds.
*         The number returned is truncated to two decimal places.
**/
public double convertWeightKGtoPounds(){
    return toTwoDecimalPlaces(startingWeight * 2.2);
}


/**
 * This method returns a boolean to indicate if the member has an ideal
 * body weight based on the Devine formula.
 * 
 * <pre>
 * For males, an ideal body weight is:   50 kg + 2.3 kg for each inch over 5 feet.
 * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
 * 
 * Note:  if no gender is specified, return the result of the female calculation.
 * 
 * </pre>
 * 
 * @return Returns true if the result of the devine formula is within 2 kgs (inclusive) of the 
 *         starting weight; false if it is outside this range.
 */
public boolean isIdealBodyWeight()
{
    double fiveFeet = 60.0;
    double idealBodyWeight;

    double inches = convertHeightMetresToInches();
    
    if (inches <= fiveFeet){
        if (getMemberGender().equals("M")){   
            idealBodyWeight = 50;  
        }
        else{   
            idealBodyWeight = 45.5;
        }
    }
    else{
        if (getMemberGender().equals("M")){   
            idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);  
        }
        else{   
            idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
        } 
    }
    
    return (      (idealBodyWeight <= (startingWeight + 2.0)) 
               && (idealBodyWeight >= (startingWeight - 2.0)) 
           );
}

/*
 * This is a private helper method.  It ensures that all double data returned from this class
 * is restricted to two decimal places.  Note:  this method does not round 
 */
private  double toTwoDecimalPlaces( double num){   
    return (int)(num *100 ) / 100.0; 
}    

/**
 * This method calculates the BMI value for the member.
 * 
 * The formula used for BMI is weight divided by the square of the height.
 * 
 * @return the BMI value for the member.  The number returned is truncated to two decimal places.
 **/   
public double calculateBMI(){
    if (this.height <= 0)
        return 0;
    else
        return toTwoDecimalPlaces(this.startingWeight / (this.height * this.height));
}  

/**
 * This method determines the BMI category that the member belongs to.
 * 
 * <pre>
 * 
 * The category is determined by the magnitude of the members BMI according to the following:
 * 
 *     BMI less than    15   (exclusive)                      is "VERY SEVERELY UNDERWEIGHT"
 *     BMI between      15   (inclusive) and 16   (exclusive) is "SEVERELY UNDERWEIGHT"
 *     BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
 *     BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
 *     BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
 *     BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
 *     BMI between      35   (inclusive) and 40   (exclusive) is "SEVERELY OBESE"
 *     BMI greater than 40   (inclusive)                      is "VERY SEVERELY OBESE"
 *     
 * </pre>
 * 
 * @return 
 * <pre>
 * The format of the String is similar to this (note the double quotes around the category):
 *     "NORMAL".
 * </pre>
 */
public String determineBMICategory()
{
    double bmi = calculateBMI();
    
    if      (bmi < 15)    return "\"VERY SEVERELY UNDERWEIGHT\"";
    else if (bmi < 16)    return "\"SEVERELY UNDERWEIGHT\"";
    else if (bmi < 18.5)  return "\"UNDERWEIGHT\"";
    else if (bmi < 25)    return "\"NORMAL\"";
    else if (bmi < 30)    return "\"OVERWEIGHT\"";
    else if (bmi < 35)    return "\"MODERATELY OBESE\"";
    else if (bmi < 40)    return "\"SEVERELY OBESE\"";
    else                  return "\"VERY SEVERELY OBESE\"";
}
}
