package controllers;

import java.util.Scanner;


import controllers.GymApi;
import models.Member;
import models.Person;
import models.PremiumMember;
import models.StudentMember;
import utils.Analytics;
import utils.ScannerInput;

import java.util.ArrayList;

import static utils.ScannerInput.validNextDouble;
import static utils.ScannerInput.validNextInt;
import static utils.ScannerInput.validNextString;

/**
 * This class controls the Gym application.
 *
 * It displays the following basic menu for the Gym and processes the user input.
 *
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
 *
 */
public class MenuController
{
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
	 * @param args As required by main
	 */
    	public static void main(String[] args) {
		new MenuController();
	}

    public MenuController()
    {
        try {
            gym.loadMember();
        }
        catch (Exception e) {System.err.println("Error reading from file: " + e);
        }

        input = new Scanner(System.in);

        //read in the details....
        System.out.println("Welcome to Elite Fitness\n");

        gym.addMembers();
        gym.addTrainers();

        runWelcome();

    }

    /*
     * welcomeMenu() - Ask user to login if existing member, or register if new
     * reads the menu option that the user entered and returns it.
     *
     * @return     the users menu choice
     */
    private int welcomeMenu()
    {
        System.out.print("Please choose: ");
        System.out.print("\n\t1: Login ");
        System.out.print("\n\t2: Register ");
        System.out.println("\n  0: Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }

    /*
     * This is the method that controls the loop.
     */
    private void runWelcome()
    {
        int option = welcomeMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:    
                			runLogin();
                    break;
                case 2:
    						runRegister();
                    break;
                default:   System.out.println("Invalid option entered: " + option);
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
    
    private int loginMenu()
    {
    	System.out.println("Are you a member or trainer?");
		System.out.println("1: Member");
		System.out.println("2: Trainer");
        System.out.println("\n  0: Exit\n");
        int option = validNextInt("==>> ");
        return option;
    }
    
    private void runLogin()
    {
        int loginOption = loginMenu();
        while (loginOption != 0)
        {

            switch (loginOption)
            {
                case 1:

                    System.out.println("Please enter email address..");
                    String emailEntered = validNextString("Enter email:");
                    //gym  call the search method in the gymapi

                    Member loggedInMember = gym.searchMembersByEmail(emailEntered);

                    if (gym.searchMembersByEmail(emailEntered) == null)

                    {
                        System.out.println("Access Denied");
                        System.exit(0);
                    } else {

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

        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private int registerMenu()
    {
        System.out.println("Are you a new member or trainer?");
        System.out.println("1: New Member");
        System.out.println("2: New Trainer");
        System.out.println("\n  0: Exit");

        int option = validNextInt("==>> ");
        return option;
    }
    
            
    private void runRegister()
    {
            int registerOption = registerMenu();
            while (registerOption != 0)
            {

                switch (registerOption) {

                    case 1:

                        String memberType = validNextString("Do you have a valid Student ID? (Y/N)");
                        String email = validNextString("Enter email");
                        String name = validNextString("Name:");
                        String address = validNextString("Address:");
                        String gender = validNextString("Gender: (m/f)");
                        Double height = validNextDouble("Height:");
                        Double weight = validNextDouble("Weight:");
                        String packag = validNextString("Package:");
                        if (memberType.equals("Y") || memberType.equals("y")) {
                            String college = validNextString("College:");
                            Integer studentId = validNextInt("Student ID Number:");
                            gym.addMember(new StudentMember(email, name, address, gender, height, weight, packag, studentId, college));
                            }
                        else {
                            gym.addMember(new PremiumMember(email, name, address, gender, height, weight, packag));
                        }
                        try {
                            gym.saveMember();
                        } catch (Exception e) {
                            System.err.println("Error writing to file: " + e);
                        }

                            break;
                    case 2:
                                System.out.println("Please enter email address..");
                                System.out.print("\tEmail: ");
                                String temail = input.nextLine();
                                //gym  call the search method in the gymapi
                                gym.searchTrainersByEmail(temail);

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
        System.out.println("\t1.View profile\n" +
                "\t2. Update profile\n" +
                "\t3. View Progress:" +
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

                    System.out.print("Name:\t" + member.getMemberName()
                            + "\nHeight:\t" + member.getHeight()
                            + "\nBMI:\t" + Analytics.calculateBMI(member)
                            + "\nBMI Category:\t" + Analytics.determineBMICategory(bmi)
                            + "\nIdeal Weight:\t" + Analytics.isIdealBodyWeight(member)
                    );

                    break;

                case 2:
                    runMemberUpdate(member);

                    break;

                case 3:
                    //runMemberProgress(member);
                 //   System.out.println(member.assessments.size());


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
                "\n\t 0. Return the Member Menu");
        int memberUpdateOption = validNextInt("==>> ");
        return memberUpdateOption;
    }

    private void runMemberUpdate(Member member)
    {
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

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            try {
                gym.saveMember();
            }
            catch (Exception e) {
                System.err.println("Error writing to file: " + e);
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            runMemberMenu(member);
        }
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

                    String email = validNextString("Enter email:\t");
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
                        gym.addMember(new StudentMember(email, name, address, gender, height, weight, packag, studentId, college));
                    } else {
                        gym.addMember(new PremiumMember(email, name, address, gender, height, weight, packag));
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
                    //runBMICatSearch(trainer);

                    break;

                case 7:
                    //runAssessments(trainer);
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




}

    

