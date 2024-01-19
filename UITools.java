
import java.util.Scanner;


/**
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

public class UITools {
    //User UI methods

    public static void validateUser(User user, int userID, String password) {
        if (user.validateUser(userID, password)) {
            System.out.println("User Login Successful");
        } else {
            System.out.println("User Login Unsuccessful");
        }
    }

    public static void addPlan(User user, MobilePlan plan) {
        if (user.addPlan(plan)) {
            System.out.println("The plan has been added successfully");
        } else {
            System.out.println("The plan can't be added ID already exists");
        }
    }

    public static void addBusinessPlan(User user, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, int numOfEmployees, int ABN) throws PlanException {
        if (user.createBusinessPlan(userName, planID, mobile, internetQuota, capLimit, expiryDate, numOfEmployees, ABN)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added. A plan with the same ID exists");
        }
    }

    public static void addPersonalPlan(User user, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        if (user.createPersonalPlan(userName, planID, mobile, internetQuota, capLimit, expiryDate, city)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added. A plan with the same ID exists");
        }
    }

    //Company UI methods

    public static void validateAdmin(MobileCompany mobileCompany, String username, String password) {
        if (mobileCompany.validateAdmin(username, password)) {
            System.out.println("Admin Login Successful");
        } else {
            System.out.println("Admin Login Unsuccessful");
        }
    }

    public static void addUser(MobileCompany mobileCompany, User user) {
        if (mobileCompany.addUser(user)) {
            System.out.println("The user has been added successfully");
        } else {
            System.out.println("The user can't be added ID already exists");
        }
    }

    public static void addUser(MobileCompany mobileCompany, String name, int id, Address address, String password) {
        if (mobileCompany.addUser(name, id, address, password)) {
            System.out.println("The user has been added successfully");
        } else {
            System.out.println("The user can't be added ID already exists");
        }
    }

    public static void addPlan(MobileCompany mobileCompany, int userID, MobilePlan plan) {
        if (mobileCompany.addPlan(userID, plan)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added. A plan with the same ID exists");
        }
    }

    public static void addBusinessPlan(MobileCompany mobileCompany, int userID, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, int numOfEmployees, int ABN) throws PlanException {
        if (mobileCompany.createBusinessPlan(userID, userName, planID, mobile, internetQuota, capLimit, expiryDate, numOfEmployees, ABN)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added. A plan with the same ID exists");
        }
    }

    public static void addPersonalPlan(MobileCompany mobileCompany, int userID, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        if (mobileCompany.createPersonalPlan(userID, userName, planID, mobile, internetQuota, capLimit, expiryDate, city)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added. A plan with the same ID exists");
        }
    }

    public static void mobilePriceRise(MobileCompany mobileCompany, int userID, double risePercent) {
        if (mobileCompany.mobilePriceRise(userID, risePercent)) {
            System.out.println("The price rise has been applied successfully");
        } else {
            System.out.println("The user has not been found");
        }
    }

    //scanner and user input methods

    public static Address getAddress() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Address:");
        System.out.println("Enter Street Number:");
        int steetNum = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Street Name:");
        String streetName = sc.nextLine();
        System.out.println("Enter Suburb:");
        String suburb = sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.nextLine();
        return new Address(steetNum, streetName, suburb, city);
    }

    public static User getUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create User---");
        System.out.println("Enter User ID:");
        int userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        Address address = UITools.getAddress();
        return new User(userID, name, address, password);
    }

    public static MobilePhone getMobilePhone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Mobile Phone:");
        System.out.println("Enter Model:");
        String model = sc.nextLine();
        System.out.println("Enter Type a number between 0-2");
        System.out.println("0. Android");
        System.out.println("1. IOS");
        System.out.println("2. Windows");
        int type = sc.nextInt();
        MobileType phoneType = MobileType.values()[type];
        //or this one by reading the string and not a number
        // phoneType=MobileType.valueOf(sc.next());
        System.out.println("Enter Memory Size:");
        int memorySize = sc.nextInt();
        System.out.println("Enter Price:");
        double price = sc.nextInt();
        MobilePhone mobilePhone = new MobilePhone(model, phoneType, memorySize, price);
        return new MobilePhone(model, phoneType, memorySize, price);
    }

    public static MyDate getDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        sc.nextLine();
        return new MyDate(year, month, day);
    }

    public static PersonalPlan getPersonalPlan() throws PlanException {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Personal Plan---");
        System.out.println("Enter Plan Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        MobilePhone mobilePhone = UITools.getMobilePhone();
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        MyDate expiryDate = UITools.getDate();
        System.out.println("Enter City:");
        String city = sc.nextLine();
        return new PersonalPlan(userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, city);
    }

    public static BusinessPlan getBusinessPlan() throws PlanException {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Business Plan---");
        System.out.println("Enter Plan Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        MobilePhone mobilePhone = UITools.getMobilePhone();
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        MyDate expiryDate = UITools.getDate();
        System.out.println("Enter Number Of Employees:");
        int numOfEmployees = sc.nextInt();
        System.out.println("Enter ABN:");
        int ABN = sc.nextInt();
        return new BusinessPlan(userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, numOfEmployees, ABN);
    }
}

