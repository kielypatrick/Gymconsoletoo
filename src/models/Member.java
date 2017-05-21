package models;

import utils.Analytics;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * This class stores information Members.This class
 * serves as a superclass for more specific person types.
 * 
 * @author Patrick Kiely
 * @version 1.0
 */

public abstract class Member extends Person {
    private double height;
    public double startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> member;


    /**
     * Constructor for objects of class Member.
     *
     * @param height         The member's height is measured in Metres.  A minimum height of
     *                       one metre (inclusive) is allowed and a maximum height of three metres (inclusive).
     * @param startingWeight The member's weight upon joining the gym (in kgs).  A minimum
     *                       weight of 35kg (inclusive) and a max of 250kg (inclusive) is permitted in the gym.
     */

    public Member(String memberEmail, String memberName, String memberAddress,
                  String gender, double height, double startingWeight, String chosenPackage) {

        super(memberEmail, memberName, memberAddress, gender);
        if ((height >= 1) && (height <= 3)) {
            this.setHeight(height);
        } else {
            this.setHeight(0);
        }

        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.setStartingWeight(startingWeight);
        } else {
            this.setStartingWeight(0);
        }
        this.chosenPackage = chosenPackage;
        member = new HashMap<>();
    }


    public String getChosenPackage() {
        return chosenPackage;
    }


    public abstract void chosenPackage(String chosenPackage);


    public double getStartingWeight() {
        return startingWeight;
    }

    /**
     * Updates the  startingWeight
     *
     * @param startingWeight The new Member starting weight
     */

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Updates the  member height
     *
     * @param height The new Member height
     */

    public void setHeight(double height) {
        this.height = height;
    }


    /**
     * This method returns the member weight converted from KGs to pounds.
     *
     * @return member weight converted from KGs to pounds.
     * The number returned is truncated to two decimal places.
     **/
    public double convertWeightKGtoPounds() {
        return Analytics.toTwoDecimalPlaces(startingWeight * 2.2);
    }


    /**
     * Updates the Members chosen package.
     *
     * @param chosenPackage The Members chosen package.
     */

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    /**
     * Updates the Members Assessments and Dates
     *
     * //@param member The Members Assessments and Dates.
     */

    public void setMember(Date date, Assessment assessment) {
        member.put(date, assessment);
    }

    public SortedSet<Date> sortedAssessmentDates(){
        SortedSet<Date> assessment = new TreeSet<>(member.keySet());

        return assessment;
    }

    /**
     * Returns the latest assessment based on the last entry(by calendar date).
     *
     * @return The latest assessment based on the last entry(by calendar date).
     */
    public Assessment lastAssessment()
    {
        return member.get(sortedAssessmentDates().last());
    }

    public void chestProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Chest: " + dateAssessmentEntry.getValue().getChest() + "cm\n");

        }
    }
    //ref https://www.youtube.com/watch?v=3hultwSw_nU

    public void waistProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Waist: " + dateAssessmentEntry.getValue().getWaist() + "cm\n");

        }
    }
    public void hipsProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Hips: " + dateAssessmentEntry.getValue().getHips() + "cm\n");

        }
    }
    public void thighProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Thigh: " + dateAssessmentEntry.getValue().getThigh() + "cm\n");

        }
    }
    public void upperArmProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Upper Arm: " + dateAssessmentEntry.getValue().getUpperArm() + "cm\n");
        }
    }

    public void weightProgress(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {
            System.out.println(dateAssessmentEntry.getKey());
            System.out.println("Weight: " + dateAssessmentEntry.getValue().getWeight() + "kg\n");

        }
    }


    public void weightTrend(){
        Set<Map.Entry<Date, Assessment>> entries = member.entrySet();
        for (HashMap.Entry<Date, Assessment> dateAssessmentEntry : entries) {

            System.out.println(dateAssessmentEntry.getValue().getWeight());

        }
    }

   /* public Iterator<Assessment> getIterator() {

        Collection<Assessment> collection = member.values();
        Iterator<Assessment> iterator = collection.iterator();
        while (iterator.hasNext()){
         return System.out.println(collection);
        }
    }
*/




    public void addAssessment(Assessment memAssessment)
    {
        member.put(new Date(), memAssessment);
    }
    /**
     * This method returns the member height converted from metres to inches.
     *
     * @return member height converted from metres to inches using the formula: meters
     * multiplied by 39.37.  The number returned is truncated to two decimal places.
     **/
    public double convertHeightMetresToInches() {
        return Analytics.toTwoDecimalPlaces(height * 39.37);
    }




}