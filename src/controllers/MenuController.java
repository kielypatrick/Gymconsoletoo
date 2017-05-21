package controllers;

import java.util.Date;
import java.util.Scanner;


import controllers.GymApi;
import models.*;
import utils.Analytics;
import utils.ScannerInput;

import java.util.ArrayList;
import java.util.SortedSet;

import static utils.ScannerInput.validNextDouble;
import static utils.ScannerInput.validNextInt;
import static utils.ScannerInput.validNextString;

/**
 * This class controls the Gym application.
 * <p>
 * It displays the following basic menu for the Gym and processes the user input.
 * <p>
 * <pre>
 *
 * Gym Menu
 * ---------
 * 1) Add a member
 * 2) List all members
 * 3) Remove a member (by index)
 * 4) Number of members in the gym
 * ---------
 * 5) List gym details
 * 6) List members with ideal starting weight
 * 7) List members with a specific BMI category
 * 8) List all members stats imperially and metrically
 * 0) Exit
 * ==>>
 * </pre>
 *
 * @author Patrick Kiely
 * @version 1.0 (03/05/2017)
 */
public class MenuController {
    private Scanner input;
    private GymApi gym = new GymApi();

    /**
     * The default constructor.
     *
     * The constructor creates an instance of the Scanner class.
     *
     * It also asks the user to enter the gym name, manager name and gym phone number.  These details are used to create an instance of the Gym.
     *
     * The final task in the constructor is to run the menu.
     */


    /**
     * Main method to run the program
     *
     * @param args As required by main
     */
    public static void main(String[] args) {
        new MenuController();
    }

    public MenuController() {
        try {
            gym.loadMember();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        try {
            gym.loadTrainer();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }

        input = new Scanner(System.in);

        //read in the details....
        System.out.println("Welcome to Elite Fitness\n");


        runWelcome();

    }

    /*
     * welcomeMenu() - Ask user to login if existing member, or register if new
     * reads the menu option that the user entered and returns it.
     *
     * @return     the users menu choice
     */
    private int welcomeMenu() {
        System.out.print("Please choose: ");
        System.out.print("\n\t1: Login ");
        System.out.print("\n\t2: Register ");
        System.out.println("\n  0: Exit");
        int option = validNextInt("==>> ");
        return option;
    }

    /*
     * This is the method that controls the loop.
     */
    private void runWelcome() {
        int option = welcomeMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    runLogin();
                    break;
                case 2:
                    runRegister();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            option = welcomeMenu();

        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    /*
     * loginMenu() - Ask user to login as member or traine
     * reads the menu option that the user entered and returns it.
     *
     * @return     the users menu choice
     */
    private int loginMenu() {
        System.out.println("Are you a member or trainer?");
        System.out.println("1: Member");
        System.out.println("2: Trainer");
        System.out.println("\n  0: Exit\n");
        int option = validNextInt("==>> ");
        return option;
    }

    private void runLogin() {
        int loginOption = loginMenu();
        while (loginOption != 0) {

            switch (loginOption) {
                case 1:

                    System.out.println("Please enter email address..");
                    String emailEntered = validNextString("Enter email:");
                    //gym  call the search method in the gymapi

                    Member loggedInMember = gym.searchMembersByEmail(emailEntered);

                    //search for email in the system. Eject unknown users
                    if (gym.searchMembersByEmail(emailEntered) == null)

                    {
                        System.out.println("Access Denied");
                        System.exit(0);
                    } else {//ALLOW THE USER TO PROGRESS

                        System.out.println("\nWelcome " + gym.searchMembersByEmail(emailEntered).getMemberName() + "!");
                        runMemberMenu(loggedInMember);
                    }

                    break;
                case 2:
                    System.out.println("Please enter email address..");
                    String temailEntered = validNextString("Enter email:");
                    //gym  call the search method in the gymapi

                    Person loggedInTrainer = gym.searchTrainersByEmail(temailEntered);

                    if (gym.searchTrainersByEmail(temailEntered) == null)

                    {
                        System.out.println("Access Denied");
                        System.exit(0);
                    } else {

                        System.out.println("\nWelcome " + gym.searchTrainersByEmail(temailEntered).getMemberName() + "!");
                        runTrainerMenu(loggedInTrainer);
                    }

                    break;

                default:
                    System.out.println("Invalid option entered: " + loginOption);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            runLogin();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private int registerMenu() {
        System.out.println("Are you a new member or trainer?");
        System.out.println("1: New Member");
        System.out.println("2: New Trainer");
        System.out.println("\n  0: Exit");

        int option = validNextInt("==>> ");
        return option;
    }


    private void runRegister() {
        int registerOption = registerMenu();
        while (registerOption != 0) {

            switch (registerOption) {

                case 1:

                    String memberType = validNextString("Do you have a valid Student ID? (Y/N)");
                    emailCheck();
                    String name = validNextString("Name:");
                    String address = validNextString("Address:");
                    String gender = validNextString("Gender: (m/f)");
                    Double height = validNextDouble("Height:");
                    Double weight = validNextDouble("Weight:");
                    String packag = validNextString("Package:");
                    if (memberType.equals("Y") || memberType.equals("y")) {
                        String college = validNextString("College:");
                        int studentId = validNextInt("Student ID Number:");
                        gym.addMember(new StudentMember(emailCheck(), name, address, gender, height, weight, packag, studentId, college));
                    } else {
                        gym.addMember(new PremiumMember(emailCheck(), name, address, gender, height, weight, packag));
                    }
                    try {
                        gym.saveMember();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }

                    break;
                case 2:
                    emailCheck();
                    String tname = validNextString("Name:\t");
                    String taddress = validNextString("Address:\t");
                    String tgender = validNextString("Gender: (m/f)\t");
                    String specialty = validNextString("Specialty:\t ");
                    gym.addTrainer(new Trainer(emailCheck(), tname, taddress, tgender, specialty));
                    try {
                        gym.saveTrainer();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }

                    break;
                default:
                    System.out.println("Invalid option entered: " + registerOption);
                    break;

            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            runWelcome();

        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private int memberMenu() {
        System.out.println("\t1. View profile\n" +
                "\t2. Update profile\n" +
                "\t3. View Progress\n" +
                "\t4. View Package details" +
                "\n\t0. Logout");
        int memberMenuOption = validNextInt("==>> ");
        return memberMenuOption;
    }

    private void runMemberMenu(Member member) {
        int option = memberMenu();
        while (option != 0) {


            switch (option) {

                case 1:
                    double bmi = Analytics.calculateBMI(member);
                    if (member.sortedAssessmentDates().size() > 0)
                    {
                        System.out.print("Name:\t" + member.getMemberName()
                                + "\nHeight:\t" + member.getHeight()
                                + "\nBMI:\t" + Analytics.calculateBMI(member)
                                + "\nBMI Category:\t" + Analytics.determineBMICategory(bmi)
                                + "\nCurrent Weight:\t" + member.lastAssessment().getWeight()
                                + "\nIdeal Weight:\t" + Analytics.isIdealBodyWeight(member)
                                + "\nCurrent Trainer:\t" + member.lastAssessment().getTrainer().getMemberName()
                                + "\nPackage:\tPackage " + member.getChosenPackage().toUpperCase()
                        );
                    }
                    else
                    {
                        System.out.print("Name:\t" + member.getMemberName()
                                + "\nHeight:\t" + member.getHeight()
                                + "\nBMI:\t" + Analytics.calculateBMI(member)
                                + "\nBMI Category:\t" + Analytics.determineBMICategory(bmi)

                                + "\nIdeal Weight:\t" + Analytics.isIdealBodyWeight(member)

                                + "\nPackage:\tPackage " + member.getChosenPackage()
                        );
                    }



                    break;

                case 2:
                    runMemberUpdate(member);

                    break;

                case 3:
                    runMemberProgress(member);

                case 4:
                    if (member.getChosenPackage().contains("1")){
                        System.out.println("\nAllowed access anytime to gym.\nFree access to all classes." +
                                "\nAccess to all changing areas including deluxe changing rooms.");
                    }
                    else if (member.getChosenPackage().contains("2")){
                        System.out.println("\nAllowed access anytime to gym.\n€3 fee for all classes." +
                            "\nAccess to all changing areas including deluxe changing rooms.");
                    }
                    else if (member.getChosenPackage().contains("3")){
                        System.out.println("\nAllowed access to gym at off-peak times.\n€5 fee for all classes. " +
                                "\nAccess to all changing areas including deluxe changing rooms.");
                    }
                    else if (member.getChosenPackage().toUpperCase().contains("WIT")){
                        System.out.println("\nAllowed access to gym during term time.\n€4 fee for all classes. " +
                                "\nNo access to deluxe changing rooms.");
                    }
                    else if (member.getChosenPackage() == null){
                        System.out.println("No infortmation available");
                    }

                    else{
                        System.out.println("No information available");
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runMemberMenu(member);
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private int memberUpdate() {
        System.out.println("What do you wish to update?");
        System.out.println("\t1. Name\n" +
                "\t2. Address\n" +
                "\t3. Gender\n" +
                "\t4. Email\n" +
                "\t5. Gym Package\n" +
                "\n\t 0. Return the Member Menu");
        int memberUpdateOption = validNextInt("==>> ");
        return memberUpdateOption;
    }

    private void runMemberUpdate(Member member) {
        int option = memberUpdate();
        while (option != 0) {


            switch (option) {

                case 1:
                    String newName = validNextString(" New Name?");
                    member.setMemberName(newName);

                    break;

                case 2:
                    String newAddress = validNextString(" New Address:");
                    member.setMemberAddress(newAddress);
                    break;

                case 3:

                    String newGender = validNextString(" New Gender:");
                    member.setGender(newGender);
                    break;

                case 4:
                    String newEmail = validNextString(" New email:");
                    member.setMemberEmail(newEmail);
                    break;
                case 5:
                    String newPackage = validNextString(" New Package:");
                    member.setChosenPackage(newPackage);
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            System.out.println("SAVED!!");
            try {
                gym.saveMember();
            } catch (Exception e) {
                System.err.println("Error writing to file: " + e);
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runMemberMenu(member);
        }
    }

    private int progressMenu(Member member) {
        System.out.println("\n" + member.getMemberName() + "; view progress by: ");
        System.out.println("  1) Weight");
        System.out.println("  2) Body Mass Index(BMI)");
        System.out.println("  3) Chest Measurement");
        System.out.println("  4) Thigh Measurement");
        System.out.println("  5) Upper Arm Measurement");
        System.out.println("  6) Waist Measurement");
        System.out.println("  7) Hips Measurement");
        System.out.println(" \n 0) Back");

        int option = validNextInt("==>> ");
        return option;
    }

    private void runMemberProgress(Member member) {
        if (member.sortedAssessmentDates().size() > 0) {

            int option = progressMenu(member);
            while (option != 0) {
                switch (option) {
                    case 1:
                        System.out.println("Starting weight: " + member.getStartingWeight() + " kgs");

                        member.weightProgress();
                        break;

                    case 2:
                        System.out.println("Starting BMI: " + toTwoDecimalPlaces(member.getStartingWeight() / member.getHeight() * member.getHeight()));
                        System.out.println("Current BMI: " + Analytics.calculateBMI(member));
                        break;
                    case 3:
                        member.chestProgress();
                        break;
                    case 4:
                        member.thighProgress();
                        break;
                    case 5:
                        member.upperArmProgress();
                        break;
                    case 6:
                        member.waistProgress();
                        break;
                    case 7:
                        member.hipsProgress();
                        break;
                    case 8:
                        member.weightTrend();
                        break;


                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }
                    //pause the program so that the user can read what we just printed to the terminal window
                    System.out.println("\nPress any key to continue...");
                    input.nextLine();
                    input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

                    runMemberMenu(member);
                }




        } else {
            System.out.println("No completed assessments yet!");
        }

        //pause the program so that the user can read what we just printed to the terminal window
        System.out.println("\nPress any key to continue...");

        input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

        runMemberMenu(member);
    }




    private int trainerMenu() {
        System.out.println("\t1. Add Member\n" +
                "\t2. List ALL Members\n" +
                "\t3. Search Members by Email\n" +
                "\t4. Search Members by Name\n" +
                "\t5. List Members with Ideal Body Weight\n" +
                "\t6. List Members by BMI Category\n" +
                "\t7. Assessments\n" +
                "\t8. Reports\n" +

                "\n\t0. Logout");

        int trainerMenuOption =  validNextInt("==>> ");
        return trainerMenuOption;
    }

    private void runTrainerMenu(Person trainer) {
        int trainerOption = trainerMenu();
        while (trainerOption != 0) {


            switch (trainerOption) {

                case 1:
                    emailCheck();
                    String name = validNextString("Name:\t");
                    String address = validNextString("Address:\t");
                    String gender = validNextString("Gender: (m/f)\t"	);
                    Double height = validNextDouble("Height:\t");
                    Double weight = validNextDouble("Weight:\t");
                    String packag = validNextString("Package:\t");
                    String memberType = validNextString("Is the member a Student? (Y/N)");
                    if (memberType.equals("Y") || memberType.equals("y")) {
                        String college = validNextString("College:\t");
                        Integer studentId = validNextInt("Student ID Number:\t");
                        gym.addMember(new StudentMember(emailCheck(), name, address, gender, height, weight, packag, studentId, college));
                    } else {
                        gym.addMember(new PremiumMember(emailCheck(), name, address, gender, height, weight, packag));
                    }
                    try {
                        gym.saveMember();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }

                    break;

                case 2:
                    System.out.println(gym.getMembers());


                    break;

                case 3:
                    String searchEmail = validNextString("Enter email:\t");
                    System.out.println(gym.searchMembersByEmail(searchEmail));

                    break;

                case 4:
                    String searchName = validNextString("Enter Name:\t");
                    System.out.println(gym.searchMembersByName(searchName));

                    break;

                case 5:

                    System.out.println(gym.listMembersWithIdealWeight());

                    break;

                case 6:
                    runBMICatSearch(trainer);

                    break;

                case 7:
                    runAssessments(trainer);
                    break;

                case 8:
                    runReports(trainer);
                    break;


                default:
                    System.out.println("Invalid option entered: " + trainerOption);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runTrainerMenu(trainer);
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private int BMICatSearch(){
        System.out.println("Select a category:");
        System.out.println("\t1. VERY Severely Underweight\t\t11. ALL underweight members\n" +
                "\t2. Severely Underweight\n" +
                "\t3. Underweight\n" +
                "\t4. Normal\n" +
                "\t5. Overweight\n" +
                "\t6. Moderately Obese\n" +
                "\t7. Severely Obese\n" +
                "\t8. VERY Severely Obese\t\t88. ALL obese members\n");

        int BMICatOption = validNextInt("==>> ");
        return BMICatOption;
    }

    private void runBMICatSearch(Person trainer){
        int option = BMICatSearch();
        while (option != 0) {


            switch (option) {

                case 1:
                    // String category = "VERY SEVERELY UNDERWEIGHT";
                    System.out.println(gym.listBySpecificBMICategory("VERY SEVERELY UNDERWEIGHT"));

                    break;

                case 2:
                    System.out.println(gym.listBySpecificBMICategory("SEVERELY UNDERWEIGHT"));
                    break;

                case 3:
                    System.out.println(gym.listBySpecificBMICategory("UNDERWEIGHT"));
                    break;

                case 4:
                    System.out.println(gym.listBySpecificBMICategory("NORMAL"));
                    break;

                case 5:
                    System.out.println(gym.listBySpecificBMICategory("OVERWEIGHT"));
                    break;

                case 6:
                    System.out.println(gym.listBySpecificBMICategory("MODERATELY OBESE"));
                    break;

                case 7:
                    System.out.println(gym.listBySpecificBMICategory("SEVERELY OBESE"));
                    break;

                case 8:
                    System.out.println(gym.listBySpecificBMICategory("VERY SEVERELY OBESE"));
                    break;

                case 11:
                    System.out.println(gym.listBySpecificBMICategory("UNDER"));
                    break;

                case 88:
                    System.out.println(gym.listBySpecificBMICategory("OBESE"));
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runTrainerMenu(trainer);
        }
    }

    private int assessments(){

        System.out.println("Select a category:");
        System.out.println("\t1. Add a Member Assessment\n" +
                "\t2. Update a comment on an assessment for a member\n");
        System.out.print("==>> ");
        int assOption = input.nextInt();
        return assOption;
    }

    private void runAssessments(Person trainer) {
        //System.out.println(gym.getMembers()); might be useful given a small membership
        int option = assessments();
        while (option != 0) {


            switch (option) {

                case 1:
                    String name = validNextString("Name:\t");
                    System.out.println(gym.searchMembersByName(name));
                    String memberAss = validNextString("Please enter email of member for assessment..\t");
                    Member member = (gym.searchMembersByEmail(memberAss));
                    gym.addAssessment(trainer, member);
                    System.out.println("Assessment logged for " + member.getMemberName());
                    try {
                        gym.saveMember();
                    }
                    catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }

                    break;


                case 2:
                    String name2 = validNextString("Name:\t");
                    System.out.println(gym.searchMembersByName(name2));
                    String memberAss2 = validNextString("Please enter email of member for assessment..\t");
                    Member member2 = (gym.searchMembersByEmail(memberAss2));
                    if (member2.sortedAssessmentDates().size() > 0) {
                        System.out.println(member2.lastAssessment().toString());
                        String newComment = validNextString("\nEnter New Comment" );
                        member2.lastAssessment().setComment(newComment);
                        System.out.println("\nNEW output to " + member2.getMemberName() + "\n" + member2.lastAssessment().toString());

                    }
                    else {
                        System.out.println("\nNo data recorded for " + member2.getMemberName());
                    }
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runTrainerMenu(trainer);
        }
    }

    public String emailCheck() {
        String email;
        while (true) {
            email = validNextString("Enter Email: ");
            if ((gym.searchTrainersByEmail(email) == null) && (gym.searchMembersByEmail(email) == null)) {
                break;
            } else {
                System.out.println("Email already in use! ");
                System.out.println("\nHave we met? :-P Please try again\n");
            }
        }
        return email;
    }

    private int reports(){

        System.out.println("Select a category:");
        System.out.println("\t1. View Individual Member Reports (search by email)\n" +
                "\t2. View Individual Member Reports (search by name)\n"  +
                "\t3. Overall Report\n" +
                "\n0. Return to trainer menu\n");
        System.out.print("==>> ");
        int repOption = input.nextInt();
        return repOption;
    }

    private void runReports(Person trainer) {
        //System.out.println(gym.getMembers()); might be useful given a small membership
        int option = reports();
        while (option != 0) {


            switch (option) {

                case 1:
                    String memberRep = validNextString("Please enter email of member for assessment..\t");
                    Member member = (gym.searchMembersByEmail(memberRep));
                    runMemberProgress(member);

                    break;

                case 2:
                    String name = validNextString("Name:\t");
                    System.out.println(gym.searchMembersByName(name));
                    String memberAss = validNextString("Please enter email of member for assessment..\t");
                    Member member1 = (gym.searchMembersByEmail(memberAss));
                    runMemberProgress(member1);
                    break;
                case 3:
                    System.out.println(gym.listMemberGoalAchievement());
                    break;


                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runTrainerMenu(trainer);
        }
    }



    //unused. Not running as hoped. Runs twice
    public Member findMember() {
        String name = validNextString("Name:\t");
        System.out.println(gym.searchMembersByName(name));
        String memberAss = validNextString("Please enter email of member for assessment..\t");
        return (gym.searchMembersByEmail(memberAss));
    }

    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }
}







    

