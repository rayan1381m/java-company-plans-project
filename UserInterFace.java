/**
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterFace // cleaned up version
{
    public static void displayMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---Main Menu---");
        System.out.println("(1) Admin Login");
        System.out.println("(2) User Login");
        System.out.println("(3) Exit");
        System.out.println("\n\nSelect and Option from 1-3");
    }

    public static void mainMenu(MobileCompany mobileCompany) throws PlanException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (input != 3) {
            displayMainMenu();
            input = sc.nextInt();
            switch (input) {
                case 1:
                    adminLogin(mobileCompany);
                    break;
                case 2:
                    userLogin(mobileCompany);
                    break;
                case 3:
                    pressEnterToContinue();
                    break;
                default:
                    System.out.println("Wrong Option. Please enter a number between 1-3");
                    pressEnterToContinue();
            }
        }
    }
    //main menu options

    public static void adminLogin(MobileCompany mobileCompany) throws PlanException {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        while (!loggedIn) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--Admin Login---");
            System.out.println("Enter Username(or Q to return to main menu) :");
            username = sc.nextLine();
            if (username.equals("Q")) return; // return to main menu again
            System.out.println("Enter Password:");
            password = sc.nextLine();
            if (mobileCompany.validateAdmin(username, password)) {
                loggedIn = true;
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Unsuccessful");
            }
        }
        //login successfull
        adminMenu(mobileCompany);
    }

    public static void userLogin(MobileCompany mobileCompany) throws PlanException {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        int userID;
        String password;
        User user = null;
        while (!loggedIn) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--User Login---");
            System.out.println("Enter UserID ( or 0 to return to main menu) : ");
            userID = sc.nextInt();
            sc.nextLine();
            if (userID == 0) return; // return to main menu again
            System.out.println("Enter Password:");
            password = sc.nextLine();
            user = mobileCompany.validateUser(userID, password);
            if (user != null) {
                loggedIn = true;
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Unsuccessful");
            }
        }
        userMainMenu(user);
    }

    public static void displayAdminMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---Admin Menu---");
        System.out.println("(1) Test Code");
        System.out.println("(2) Create User");
        System.out.println("(3) Create Personal Plan");
        System.out.println("(4) Create Business Plan");
        System.out.println("(5) Print User Information");
        System.out.println("(6) Filter By Mobile Model");
        System.out.println("(7) Filter By Expiry Date");
        System.out.println("(8) Update Address");
        System.out.println("(9) report Mobile Model, Total Monthly Payment, and Average Monthly Payment for a given user");
        System.out.println("(10) report City Name, Total Monthly Payments across all users");
        System.out.println("(11) report Mobile Model, Total Monthly Payment, and Average Monthly Payment across all users");

        System.out.println("(0) Log Out");
        System.out.println("\n\nSelect and Option from 1-11");
    }

    public static void adminMenu(MobileCompany mobileCompany) throws PlanException {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 9) {
            displayAdminMainMenu();
            option = sc.nextInt();
            switch (option) {
                case 1:
                    TestCase.testCode(mobileCompany);
                    break;
                case 2:
                    createUser(mobileCompany);
                    break;
                case 3:
                    createPersonalPlan(mobileCompany);
                    break;
                case 4:
                    createBusinessPlan(mobileCompany);
                    break;
                case 5:
                    printUserInformation(mobileCompany);
                    break;
                case 6:
                    filterByMobileModel(mobileCompany);
                    break;
                case 7:
                    filterByExpiryDate(mobileCompany);
                    break;
                case 8:
                    updateAddress(mobileCompany);
                    break;
                case 9:
                    reportPaymentsPerMobileModelUser(mobileCompany);
                    break;
                case 10:
                    totalPaymentPerCity(mobileCompany);
                    break;
                case 11:
                    reportPaymentsAllUsers(mobileCompany);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-9");
            }
            pressEnterToContinue();
        }
    }

    public static void displayUserMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---User Menu---");
        System.out.println("(1) Print All Plans and Total Monthly Payments");
        System.out.println("(2) Create Personal Plan");
        System.out.println("(3) Create Business Plan");
        System.out.println("(4) Print User Information");
        System.out.println("(5) Update Address");
        System.out.println("(6) Log Out");
        System.out.println("\n\nSelect and Option from 1-6");
    }

    public static void userMainMenu(User user) throws PlanException {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (input != 7) {
            displayUserMainMenu();
            input = sc.nextInt();

            switch (input) {
                case 1:
                    printUser(user);
                    break;
                case 2:
                    createPersonalPlan(user);
                    break;
                case 3:
                    createBusinessPlan(user);
                    break;
                case 4:
                    printUser(user);
                    break;
                case 5:
                    updateAddress(user);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-6");
            }
            pressEnterToContinue();
        }
    }

    // admin menu options


    public static void createUser(MobileCompany mobileCompany) //good way
    {
        UITools.addUser(mobileCompany, UITools.getUser());
    }

    public static void createPersonalPlan(MobileCompany mobileCompany) throws PlanException // good way
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Personal Plan---");
        System.out.println("Enter a UserID to add Plan to:");
        int userID = PlanException.generateRandomId();
        sc.nextLine();
        userID = PlanException.generateRandomId();
        System.out.println("The Plan ID was not valid and a new ID " + userID + " is generated by admin and assigned for the Plan");
        UITools.addPlan(mobileCompany, userID, UITools.getPersonalPlan());
    }

    public static void createBusinessPlan(MobileCompany mobileCompany) throws PlanException // Good way
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Business Plan---");
        System.out.println("Enter User Id to add Plan to:");
        int userID = sc.nextInt();
        sc.nextLine();
        userID = PlanException.generateRandomId();
        System.out.println("The Plan ID was not valid and a new ID " + userID + " is generated by admin and assigned for the Plan");
        UITools.addPlan(mobileCompany, userID, UITools.getBusinessPlan());
    }

    public static void printUserInformation(MobileCompany mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Print User Information---");
        System.out.println("Enter User ID:");
        int userID = sc.nextInt();
        //mobileCompany.printPlans(userID); // does not print user info
        mobileCompany.printUser(userID);// better
    }

    public static void filterByMobileModel(MobileCompany mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Mobile Model---");
        System.out.println("Enter Mobile Model:");
        String mobileModel = sc.nextLine();
        HashMap<Integer, MobilePlan> filteredPlans = mobileCompany.filterByMobileModel(mobileModel);
        System.out.println("Filtered Plans--");
        MobilePlan.printPlans(filteredPlans);
        double totalMonthlyPayments = MobilePlan.calcTotalPayments(filteredPlans, mobileCompany.getFlatRate());
        System.out.println("Total Monthly Payments:$" + totalMonthlyPayments);
    }

    public static void filterByExpiryDate(MobileCompany mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Expiry Date---");
        MyDate expiryDate = UITools.getDate();
        System.out.println("Enter User ID:");
        int userId = sc.nextInt();
        HashMap<Integer, MobilePlan> filteredPlans = mobileCompany.filterByExpiryDate(userId, expiryDate);
        System.out.println("Expired Plans--");
        MobilePlan.printPlans(filteredPlans);
    }

    public static void updateAddress(MobileCompany mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Update Address---");
        System.out.println("Enter User ID for Address to be changed:");
        int userId = sc.nextInt();
        Address newAddress = UITools.getAddress();
        User user = mobileCompany.findUser(userId);
        if (user != null) {
            user.setAddress(newAddress);
            System.out.println("New Address:");
            System.out.println(user.getAddress());
        } else {
            System.out.println("User has not been found");
        }
    }

    //User menu options
    public static void createPersonalPlan(User user) throws PlanException {
        UITools.addPlan(user, UITools.getPersonalPlan());
    }

    public static void createBusinessPlan(User user) throws PlanException {
        UITools.addPlan(user, UITools.getBusinessPlan());
    }

    public static void printUser(User user) {
        System.out.println("---User Information---");
        user.print();
    }

    public static void updateAddress(User user) {
        System.out.println("---Update Address---");
        user.setAddress(UITools.getAddress());
        System.out.println("New Address:");
        System.out.println(user.getAddress());
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter Key to Continue...");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    //Lab 5----------------------------------------------------------------------------------------

    public static void calcPaymentsPerMobileModelUser(User user) {
        HashMap<String, Integer> getTotalCountPerMobileModel = user.getTotalCountPerMobileModel();
        HashMap<String, Double> getTotalPaymentPerMobileModel = user.getTotalPaymentPerMobileModel();
        user.printPaymentsForMobileModel(getTotalCountPerMobileModel, getTotalPaymentPerMobileModel);
    }

    public static User reportPaymentsPerMobileModelUser(MobileCompany company) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id: ");
        int id = scanner.nextInt();
        User user = company.findUser(id);
        if (user != null) {
            calcPaymentsPerMobileModelUser(user);
        } else {
            System.out.println("USer not found.");
        }
        return null;
    }

    public static void totalPaymentPerCity(MobileCompany company) {
        HashMap<String, Double> totalPaymentForEachCity = company.getTotalPaymentPerCity();
        company.printPaymentByCity(totalPaymentForEachCity);
    }

    public static void reportPaymentsAllUsers(MobileCompany company) {
        HashMap<String, Integer> getTotalCountPerMobileModel = company.getTotalCountPerMobileModel();
        HashMap<String, Double> getTotalPaymentPerMobileModel = company.getTotalPaymentPerMobileModel();
        company.printPaymentByMobileModel(getTotalCountPerMobileModel, getTotalPaymentPerMobileModel);
    }
}