import java.io.*;
import java.util.HashMap;

public class Main6 {
    public static void main(String[] args) throws PlanException, IOException {
        MobilePhone mobilePhone = new MobilePhone("Galaxy S10", MobileType.Android, 8, 500);
        MobilePhone mobilePhone1 = new MobilePhone("Iphone X", MobileType.IOS, 4, 500);
        MobilePhone mobilePhone2 = new MobilePhone("LG S50", MobileType.Windows, 16, 500);

        //Initialize plan objects
        MobilePlan plan0 = createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong");
        MobilePlan plan1 = createPersonalPlan("Sara12", 345, mobilePhone, 30, 38, new MyDate(1999, 4, 34), "Sydney");
        MobilePlan plan2 = createPersonalPlan("John342", 435, mobilePhone1, 100, 20, new MyDate(2004, 3, 23), "Dubbo");
        MobilePlan plan3 = createBusinessPlan("Alex123", 679, mobilePhone1, 50, 80, new MyDate(2020, 6, 21), 20, 123568);
        MobilePlan plan4 = createBusinessPlan("Gh546", 356, mobilePhone2, 20, 30, new MyDate(2021, 7, 29), 10, 666555);
        MobilePlan plan5 = createBusinessPlan("S9845", 457, mobilePhone2, 200, 46, new MyDate(2024, 2, 17), 200, 222333);

        User user0 = new User(143543, "John Smith", new Address(12, "Princs Hwy", "Fairy Meadow", "Wollongong"), "password1");
        User user1 = new User(265756, "Sara Lawson", new Address(43, "Illawara Avenue", "Gwynneville", "Wollongong"), "password1");
        User user2 = new User(387899, "Robert London", new Address(22, "Edward st", "Coniston", "Wollongong"), "123");
        User user3 = new User(489123, "Alex Niton", new Address(330, "Smith st", "Liverpool", "Sydney"), "12345");
        User user4 = new User(565768, "Joe Tomson", new Address(20, "Rose st", "North Sydney", "Sydney"), "123");
        User user5 = new User(676767, "Allison Bird", new Address(41, "Grey st", "Monavale", "Melbourne"), "password123");

        MobileCompany company0 = new MobileCompany("Rokham", "tra", "1234", 1);
        company0.addUser(user1);
        company0.addUser(user2);
        company0.addUser(user3);
        company0.addUser(user4);

        UITools.addPlan(user1, plan1);
        UITools.addPlan(user1, plan3);
        UITools.addPlan(user1, plan2);
        UITools.addPlan(user3, plan0);
        UITools.addPlan(user3, plan5);
        UITools.addPlan(user1, plan4);
        System.out.println("\n\n\n\n");

        //testing binary file and list of plans
//        MobilePlan.save(company0.allPlans(), "plans.ser");
//        HashMap<Integer, MobilePlan> plans = MobilePlan.load("plans.ser");
//        MobilePlan.printPlans(plans);
//        plans.put(133, createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
//        MobilePlan.save(plans, "plans.ser");
//        plans.clear();
//        plans = MobilePlan.load("plans.ser");
//        MobilePlan.printPlans(plans);
//--------------------------------------------------------------------------------------------------
        //testing binary file and list of users
//        User.save(company0.shallowCopyUsers(), "users.ser");
//        User.save(company0.getUsers(), "users.ser");
//        HashMap<Integer, User> users = User.load("users.ser");
//        User.printUsers(users);
//        User user31 = new User(489123, "Alex Niton", new Address(330, "Smith st", "Liverpool", "Sydney"), "12345");
//        user31.addPlan(createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
//        users.put(489123, user31);
//        User.save(users, "users.ser");
//        users.clear();
//        users = User.load("users.ser");
//        User.printUsers(users);

        //testing text file and list of plans with toDilimitedString
//        MobilePlan.saveTextFile(company0.allPlans(), "plans.txt");
//        HashMap<Integer, MobilePlan> plans = MobilePlan.loadTextFile("plans.txt");
//        MobilePlan.printPlans(plans);
//        plans.put(133, createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
//        MobilePlan.saveTextFile(plans, "plans.txt");
//        plans.clear();
//        plans = MobilePlan.loadTextFile("plans.txt");
//        MobilePlan.printPlans(plans);

        //testing text file and list of users with toDilimitedString
//        User.saveTextFile(company0.getUsers(), "users.txt");
//        HashMap<Integer, User> users = User.loadTextFile("users.txt");
//        User.printUsers(users);
//        User usr = new User(265756, "Sara Lawson", new Address(43, "Illawara Avenue", "Gwynneville", "Wollongong"), "password1");
//        usr.addPlan(createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
//        users.put(120, usr);
//        User.saveTextFile(users, "users.txt");
//        users.clear();
//        users = User.loadTextFile("users.txt");
//        User.printUsers(users);

        //mobileCompany and binary file
//        company0.save("company.ser");
//        MobileCompany mobileCompany1=new MobileCompany();//using default constructor
//        mobileCompany1.load("company.ser");//all users and all plans are loaded
//        System.out.println(mobileCompany1);
//        mobileCompany1.addUser(user1);
//        mobileCompany1.addPlan(265756, createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
//        mobileCompany1.save("company.ser");
//        MobileCompany mobileCompany2=new MobileCompany();
//        mobileCompany2.load("company.ser");
//        System.out.println(mobileCompany2);

        //mobileCompany and text file
        company0.saveTextFile("company.txt");
        MobileCompany mobileCompany1 = new MobileCompany();//using default constructor
        mobileCompany1.loadTextFile("company.txt");//all users and all plans are loaded
        System.out.println(mobileCompany1);
        mobileCompany1.addUser(user1);
        mobileCompany1.addPlan(265756,createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong"));
        mobileCompany1.saveTextFile("company.txt");
        MobileCompany mobileCompany2 = new MobileCompany();
        mobileCompany2.loadTextFile("company.txt");
        System.out.println(mobileCompany2);
    }

    public static PersonalPlan createPersonalPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        PersonalPlan plan0;
        try {
            plan0 = new PersonalPlan(userName, id, handset, internetQuota, capLimit, expiryDate, city);
        } catch (PlanException e) {
            plan0 = new PersonalPlan(userName, e.getNewId(), handset, internetQuota, capLimit, expiryDate, city);
            System.out.println(e);
        }
        return plan0;
    }

    public static BusinessPlan createBusinessPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException {
        BusinessPlan plan;
        try {
            plan = new BusinessPlan(userName, id, handset, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
        } catch (PlanException e) {
            plan = new BusinessPlan(userName, e.getNewId(), handset, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
            System.out.println(e);
        }
        return plan;
    }

}
