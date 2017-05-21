package controllers;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.*;
import utils.Analytics;

import static utils.ScannerInput.validNextDouble;
import static utils.ScannerInput.validNextInt;
import static utils.ScannerInput.validNextString;

/**
 * Created by Patrick on 03/05/2017.
 */
public class GymApi
{
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

/**
 * Constructor for objects of class Gym.  Within this constructor, the members and trainers ArrayLists are instantiated.
 *
 *

 */
public GymApi(){

    members = new ArrayList<>();
    trainers = new ArrayList<>();
}

//***********************************************************************************
    //  ARRAY LIST HANDLING METHODS
    //***********************************************************************************
    /**
     *  Adds a member to the gym collection.
     *
     *  @param member The member object that will be added to the gym collection.
     */
    public void addMember(Member member)
    {
        members.add(member);
    }

    /**
     *  Adds a trainer to the gym collection.
     *
     *  @param trainer The trainer object that will be added to the gym collection.
     */
    public void addTrainer(Trainer trainer)
    {
        trainers.add(trainer);
    }

    /**
     *  Removes a member from the gym collection.
     *
     *  @param index The index number of the member object that will be removed from the gym collection.
     */
    public void removeMember(int index){
        members.remove(index);
    }

    public void removeTrainer(int index){
        trainers.remove(index);
    }
    /**
     *  Returns the number of members stored in the gym collection.
     *
     *  @return The number of the member object currently stored in the gym collection.
     */
    public int numberOfMembers(){
        return members.size();
    }

    /**
     *  Returns the number of trainers stored in the gym collection.
     *
     *  @return The number of the trainer object currently stored in the gym collection.
     */
    public int numberOfTrainers(){
        return trainers.size();
    }

    /**
     *  Returns a String representing all the members stored in the gym collection.
     *
     *  @return String representing all the members stored in the gym collection.  The String returned is similar to this structure,
     *  with the preceding number representing the index number of the member within the collection:
     *
     * <pre>
     *
     *    0: member's toString() format
     *    1: member's toString() format
     *    2: member's toString() format
     *
     * </pre>
     */
    public String getMembers(){
        if (members.size() > 0){
            String listOfMembers = "";
            for (int i = 0; i < numberOfMembers(); i++){
                listOfMembers += i + ": " + members.get(i) + "\n";
            }
            return listOfMembers;
        }
        else{
            return "There are no members in the gym";
        }
    }

    /**
     *  Returns a String representing all the trainers stored in the gym collection.
     *
     *  @return String representing all the members stored in the gym collection.  The String returned is similar to this structure,
     *  with the preceeding number representing the index number of the member within the collection:
     *
     * <pre>
     *
     *    0: trainer's toString() format
     *    1: trainer's toString() format
     *    2: trainer's toString() format
     *
     * </pre>
     */
    public String getTrainers(){
        if (trainers.size() > 0){
            String listOfTrainers = "";
            for (int i = 0; i < numberOfTrainers(); i++){
                listOfTrainers += i + ": " + trainers.get(i) + "\n";
            }
            return listOfTrainers;
        }
        else{
            return "There are no trainers in the gym";
        }
    }

    public boolean isValidMemberIndex(int index) {
        return ((index >= 0) && (index < numberOfMembers()));

    }

    public boolean isValidTrainerIndex(int index) {
        return ((index >= 0) && (index < numberOfTrainers()));

    }


    /**
     *  Method to search the member names for the string entered.
     *
     *  @param nameEntered The string being searched for in the member name field
     *  @return all members whose name contains nameEntered
     */


    public String searchMembersByName(String nameEntered){
    	 if (members.size() > 0){
             String listOfMembers = "";

             for (Member member: members){
            	 if (member.getMemberName().toUpperCase().contains(nameEntered.toUpperCase())){
            		 listOfMembers += member.toString() + "\n";
            	 }
             }
             if (listOfMembers.equals("")){
            	 return "No Matches";
             }
             	return listOfMembers;
    	}
        else{
            return "There are no members in the gym";
        }
    }

    /**
     *  Method to search the member email addresses for the string entered.
     *
     *  @param emailEntered The string being searched for in the member email field
     *  @return all members whose email contains emailEntered
     */

    public Member searchMembersByEmail(String emailEntered){

        for (Member member: members){
        	if (member.getMemberEmail().toUpperCase().equals(emailEntered.toUpperCase()))
        	{

				return member;

        	}
       
        }
		return null;            
    }


    /**
     *  Method to search the trainer email addresses for the string entered.
     *
     *  @param emailEntered The string being searched for in the trainer email field
     *  @return all trainers whose name contains emailEntered
     */
    
    public Person searchTrainersByEmail(String emailEntered){

        for (Trainer trainer: trainers){
        	if (trainer.getMemberEmail().toUpperCase().equals(emailEntered.toUpperCase()))
        	{
        		for (int i = 0; i < numberOfTrainers();)
				return trainers.get(i);
        	}
       
        }
		return null;            
    }


    @SuppressWarnings("unchecked")
    public void loadMember() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("members.xml"));
        members = (ArrayList<Member>) is.readObject();
        is.close();
    }

    public void loadTrainer() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("trainers.xml"));
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }





    public void saveMember() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("members.xml"));
        out.writeObject(members);
        out.close();
    }


    public void saveTrainer() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("trainers.xml"));
        out.writeObject(trainers);
        out.close();
    }


    //***********************************************************************************
    //  ARRAY LIST INTERROGATION AND SUMMATION METHODS
    //***********************************************************************************
    /**
     * List all the members that have an ideal starting weight.
     *
     * @return The list of members (i.e. use the toString method here) that have an ideal starting weight based on the
     * devine method.
     *
     * <pre>
     *
     * If there are no members with an ideal starting weight, the message
     *      "There are no members in the gym with an ideal weight" should be returned.
     *
     * If there are no members in the gym, the message
     *      "There are no members in the gym" should be returned.
     * </pre>
     */
    public String listMembersWithIdealWeight(){
        if (members.size() > 0){
            String listOfMembers = "";
            for (Member member: members){
                if (Analytics.isIdealBodyWeight(member)){
                    listOfMembers += member.toString() + "\n";
                }
            }
            if (listOfMembers.equals("")){
                return "There are no members in the gym with an ideal weight";
            }

            return listOfMembers;
        }
        else{
            return "There are no members in the gym";
        }
    }

    /**
     * List all the members of a specific BMI category.
     *
     * @param category - The category you wish to search members by.
     *
     * <pre>
     *
     * The specific categories are:
     *     "VERY SEVERELY UNDERWEIGHT"
     *     "SEVERELY UNDERWEIGHT"
     *     "UNDERWEIGHT"
     *     "NORMAL"
     *     "OVERWEIGHT"
     *     "MODERATELY OBESE"
     *     "SEVERELY OBESE"
     *     "VERY SEVERELY OBESE"
     *
     * This method also allows you to search by key words e.g. "OBESE" will return members in the following categories:
     *     "MODERATELY OBESE"
     *     "SEVERELY OBESE"
     *     "VERY SEVERELY OBESE"
     * Note:  In this situation, the members are NOT sorted by category, they are just displayed as is.
     *
     * </pre>
     *
     * @return The list of members whose BMI falls into the category passed as a parameter.
     * <pre>
     *
     * If there are no members in the BMI category, the message
     *      "There are no members in the gym in this BMI category" should be returned.
     *
     * If there are no members in the gym, the message
     *      "There are no members in the gym" should be returned.
     * </pre>
     */
    public String listBySpecificBMICategory(String category){
        if (members.size() > 0){
            String listOfMembers = "";
            for (Member member: members){
                if (Analytics.determineBMICategory(Analytics.calculateBMI(member )).toUpperCase().contains(category.toUpperCase())){
                    listOfMembers += member.toString() + "\n";
                }
            }
            if (listOfMembers.equals("")){
                return "There are no members in the gym in this BMI category";
            }
            return listOfMembers;
        }
        else{
            return "There are no members in the gym";
        }
    }



    /**
     * Returns a human-readable String representation of the object state.
     *
     * @return a list of all Gym members.  The String returned is similar to this structure:
     *
     * <pre>
          *
     *    List of members in the gym:
     *    (list all of the members here)
     *
     * </pre>
     */
    public String toString(){
        return ( "List of members in the gym:\n"
                + getMembers());
    }

    /**
     *  Adds an assessment to a member object collection.
     *
     *  @param member The member object having the assessment recorded.
     *
     */


    public void addAssessment(Person trainer, Member member) {
        double weight;
        while (true) {
            weight = validNextDouble("Weight: ");
            if ((weight >= 35) && (weight <= 250)) {
                break;
            } else {
                System.out.println("Invalid weight entered: " + weight);
                System.out.println("\nPlease enter a starting weight between 35 and 250 kgs");
            }
        }

        int chest = validNextInt("Chest:\t");
        int thigh = validNextInt("Thigh:\t");
        int upperArm = validNextInt("Upper Arm:\t");
        int waist = validNextInt("Waist:\t");
        int hips = validNextInt("Hips:\t");
        String comment = validNextString("Trainer Comment:\t");

        member.addAssessment(new Assessment(weight, chest, thigh, upperArm, waist, hips, comment, trainer));
        System.out.println("SAVED!!");
    }

    public boolean isValidIndex(int index)
    {
        return ( (index >= 0) && (index < members.size()) );
    }

    /**
     * List all the members' weight and height both imperially and metrically.
     *
     * @return Each member in the gym with the weight and height listed both imperially and metrically.
     *
     * <pre>
     * The format of the output is like so:
     *
     *     Joe Soap:     xx kg (xxx lbs)     x.x metres (xx inches).
     *     Joan Soap:    xx kg (xxx lbs)     x.x metres (xx inches).
     *
     * If there are no members in the gym, the message
     *      "There are no members in the gym" should be returned.
     * </pre>
     */
    public String listMemberDetailsImperialAndMetric(){
        if (members.size() > 0){
            String listOfMembers = "";
            for (Member member: members){
                listOfMembers += member.getMemberName() + ":\n\t"
                        + member.getStartingWeight() + " kg ("
                        + member.convertWeightKGtoPounds() + " lbs)\t\t"
                        + member.getHeight() + " metres ("
                        + member.convertHeightMetresToInches() + " inches)."
                        + "\n";
            }
            return listOfMembers;
        }
        else{
            return "There are no members in the gym.";
        }
    }
    /**
     * List all the members' proximity to their ideal weight.
     *
     * @return The difference betweem each member's current and ideal weight.
     *
     * <pre>
     * The format of the output is like so:
     *
     *     Joe Soap:     xxkg
     *     Joan Soap:    -xxkg
     *
     * If there are no members in the gym, the message
     *      "There are no members in the gym" should be returned.
     * </pre>
     */

    public String listMemberGoalAchievement(){
        if (members.size() > 0){
            String listOfMembers = "";
            for (Member member: members){
                listOfMembers += "\t" + member.getMemberName() + "\t" + Analytics.goalWeight(member) + "kg\n" ;
            }
            return listOfMembers;
        }
        else{
            return "There are no members in the gym.";
        }
    }







}
