
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

public class TestCase {
    public static void testCode(MobileCompany mobileCompany) throws PlanException {
        double flatRate = 12;
        System.out.println("---Test Code---");

        MobilePhone mobilePhone = new MobilePhone("Galaxy S10", MobileType.Android, 8, 500);
        MobilePhone mobilePhone1 = new MobilePhone("Iphone X", MobileType.IOS, 4, 500);
        MobilePhone mobilePhone2 = new MobilePhone("LG S50", MobileType.Windows, 16, 500);

        //Initialize plan objects
        MobilePlan plan0 = new PersonalPlan("OP123", 122, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong");
        MobilePlan plan1 = new PersonalPlan("Sara12", 345, mobilePhone, 30, 38, new MyDate(1999, 4, 34), "Sydney");
        MobilePlan plan2 = new PersonalPlan("John342", 435, mobilePhone1, 100, 20, new MyDate(2004, 3, 23), "Dubbo");
        MobilePlan plan3 = new BusinessPlan("Alex123", 679, mobilePhone1, 50, 80, new MyDate(2020, 6, 21), 20, 123568);
        MobilePlan plan4 = new BusinessPlan("Gh546", 356, mobilePhone2, 20, 30, new MyDate(2021, 7, 29), 10, 666555);
        MobilePlan plan5 = new BusinessPlan("S9845", 457, mobilePhone2, 200, 46, new MyDate(2024, 2, 17), 200, 222333);

        User user0 = new User(143543, "John Smith", new Address(12, "Princs Hwy", "Fairy Meadow", "Wollongong"), "password1");
        User user1 = new User(265756, "Sara Lawson", new Address(43, "Illawara Avenue", "Gwynneville", "Wollongong"), "password1");
        User user2 = new User(387899, "Robert London", new Address(22, "Edward st", "Coniston", "Wollongong"), "123");
        User user3 = new User(489123, "Alex Niton", new Address(330, "Smith st", "Liverpool", "Sydney"), "12345");
        User user4 = new User(565768, "Joe Tomson", new Address(20, "Rose st", "North Sydney", "Sydney"), "123");
        User user5 = new User(676767, "Allison Bird", new Address(41, "Grey st", "Monavale", "Melbourne"), "password123");



        //lab1 ---Test the Plan Class---------------------------------------------------------------------------------

        ArrayList<MobilePlan> plans = new ArrayList<>(); //ArrayList of parent class
        plans.add(plan1); //adding plans to the list
        plans.add(plan2);
        plans.add(plan3);
        plans.add(plan4);
        plans.add(plan5);

        for (MobilePlan plan : plans) {
            plan.print();                 //print using print method
        }

        for (MobilePlan plan : plans) {
            System.out.println(plan); //print using toString method
        }

        double total = 0;
        for (MobilePlan plan : plans) {
            total += plan.calcPayment(flatRate);
        }
        System.out.println("Total pay = " + total);

        //lab2 -----------Test the User class-----------------------------------------------------------------------

        //add plans to user
        UITools.addPlan(user1, plan1);
        UITools.addPlan(user1, plan2);
        UITools.addPlan(user1, plan3);
        //call print() method for user1
        user1.print();
        //print user1 using toString
        System.out.println(user1.toString());
        //Find a plan by using findPlan() to invalid ID
        MobilePlan foundPlan = user1.findPlan(12345);
        if (foundPlan == null)//---------------------12345 is not valid
        {
            System.out.println("Plan has not been found");
        } else {
            foundPlan.print();
        }

        foundPlan = user1.findPlan(122);
        if (foundPlan == null) {
            System.out.println("Plan has not been found");
        } else {
            foundPlan.mobilePriceRise(0.1);
            foundPlan.setUsername("Robert");
            foundPlan.setHandsetModel("Iphone X MAX");
            foundPlan.print();
        }

        user1.setCity("Wollongong"); //instead of user1.getAddress().setCity("Wollongong");
        user1.print();
        //ask customer to enter a new address
        //COMENTED FOR ASSIGNEMNT I
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter the new street number: ");
//        int newStreetNum = scan.nextInt();
//        System.out.print("Enter the new street name: ");
//        String newStreetName = scan.next();
//        System.out.print("Enter the new suburb: ");
//        String newSuburb = scan.next();
//        System.out.print("Enter the new city: ");
//        String newCity = scan.next();
//        Address newAddress = new Address(newStreetNum, newStreetName, newSuburb, newCity); // by using scanner
        Address newAddress = new Address(31, "Gray Street", "Liverpool", "Sydney");// hardcoded for assigment I
        user1.setAddress(newAddress);
        user1.print();
        //print the total monthly payment of the user
        System.out.printf("The total monthly payment of user: %.3f%n", user1.calcTotalPayments(flatRate));
        //add 10% to the price of mobile phones for all the plans this user owns and print again
        user1.mobilePriceRiseAll(0.1);
        System.out.printf("The new total monthly payment of user: %.3f%n", user1.calcTotalPayments(flatRate));

        //ask customer to enter a mobileModel
        //System.out.print("Enter a mobile model: ");
        String userMobileModel = "Iphone";//scan.next(); // change scanner to hardcoded

        //call filterByMobileModel method for the user and store the filtered list
        HashMap<Integer, MobilePlan> filteredPlans = user1.filterByMobileModel(userMobileModel);
        //print the filtered list by calling the static method inside MobilePlan
        MobilePlan.printPlans(filteredPlans);


        // ------ lab 3 testing the company --------------------------------------------------------------------------------

        //one  object of MobileCompany class
        //MobileCompany mobileCompany = new MobileCompany("Hooman Company", "admin", "admin", 20); // we get it as an input parameter now

        // Log in once successfull and once not successfull
        UITools.validateAdmin(mobileCompany, "andy", "andy12"); // not successfull login

        if (!mobileCompany.validateAdmin("admin", "admin")) {
            System.out.println("Admin Login unsuccessful");
        } else // login successfull
        {
            System.out.println("------------------------------------");
            // Add users to the mobile company with both methods

            //Add users
            UITools.addUser(mobileCompany, user3);
            UITools.addUser(mobileCompany, user4);
            UITools.addUser(mobileCompany, user5);
            UITools.addUser(mobileCompany, user0);
            UITools.addUser(mobileCompany, user1);
            UITools.addUser(mobileCompany, user2);
            UITools.addUser(mobileCompany, user0);
            UITools.addUser(mobileCompany, "Mark", 56236, new Address(9, "Wind St", "West Wollongong", "Wollongong"), "pass123");

            System.out.println("-------------------------------------");

            //Add plans to users

//            mobileCompany.addPlan(1, plan1);  //wrong not checkign the output of the boolean method
//            mobileCompany.addPlan(33, plan4); // wrong not checking the output

            //correct way of adding plans as below

            //User0
            UITools.addPlan(mobileCompany, user0.getID(), plan0);
            UITools.addPlan(mobileCompany, user0.getID(), plan2);


            //User1
            UITools.addPlan(mobileCompany, user1.getID(), plan3);
            UITools.addPlan(mobileCompany, user1.getID(), plan5);

            //User2
            UITools.addPlan(mobileCompany, user2.getID(), plan1);
            UITools.addPlan(mobileCompany, user2.getID(), plan4);

            UITools.addPlan(mobileCompany, 489123, plan3); //direct userID
            UITools.addPlan(mobileCompany, 12345, plan3); //wrong userID

            // Add several plans to some users by calling createPersonalPlan() and createBusinessPlan()

//            mobileCompany.createPersonalPlan(1, "User3", 333, mobilePhone, 29, 11, new MyDate(2019, 9, 14), "Wollongong");// wrong
//            mobileCompany.createBusinessPlan(1, "User2", 111, mobilePhone, 233, 2, new MyDate(2019, 9, 14), 75, 123);     // wrong

            //correct ways by using the UI methods to check the Boolean

            UITools.addPersonalPlan(mobileCompany, user3.getUserID(), "Frank", 12314, mobilePhone, 25, 2, new MyDate(2019, 9, 14), "Churchville");
            UITools.addPersonalPlan(mobileCompany, user3.getUserID(), "Wanqing", 90909, mobilePhone1, 30, 50, new MyDate(2021, 10, 18), "Springfield");
            UITools.addPersonalPlan(mobileCompany, user4.getUserID(), "Natasha", 88978, mobilePhone2, 140, 57, new MyDate(2021, 11, 22), "Wuhan");

            UITools.addBusinessPlan(mobileCompany, user4.getUserID(), "Edward", 89900, mobilePhone1, 8, 2, new MyDate(2019, 8, 11), 150, 1212121);
            UITools.addBusinessPlan(mobileCompany, user5.getUserID(), "Micheal", 45674, mobilePhone, 200, 34, new MyDate(2020, 9, 31), 4000, 3434344);
            UITools.addBusinessPlan(mobileCompany, user5.getUserID(), "Mary", 67656, mobilePhone2, 5000, 80, new MyDate(2020, 12, 1), 2000, 5656565);

            //Duplicate Plan
            UITools.addBusinessPlan(mobileCompany, user0.getUserID(), "qw123", 122, mobilePhone, 25, 2, new MyDate(2020, 9, 14), 150, 1212121);

            System.out.println();

            System.out.println("--------------------------------------");

            // Ask customer to enter a userID and and print the user and all of his plans by using methods inside mobileCompany
            System.out.println("--------------------------------------");
            //Scanner scan = new Scanner(System.in);
            //System.out.print("Enter a userID: ");
            int userID = 143543; //scan.nextInt();//change it to hardcoded rather than scanner
            mobileCompany.printPlans(userID);

            //or finding the user and user methods

            //Test user ID
            System.out.println("User ID find test and print");
//            int testUserID = user0.getUserID();
//            User user = mobileCompany.findUser(testUserID); // to get user ID
            User user = mobileCompany.findUser(143543); //direct numebr for userID

            if (user != null) {
                user.print();
                user.printPlans(flatRate);
                System.out.println();
            } else {
                System.out.println("User has not been found");
            }

            /*
            Ask customer to enter a userID and planID and find a plan with the given planID for that userID by calling findPlan (int userID ,int policyID) and then print the plan.
            */

            System.out.println("--------------------------------------");
            System.out.println("Find plan Test");
            System.out.print("Enter a userID: ");
            userID = 143543;//scan.nextInt();
            System.out.print("Enter a planID: ");
            int planID = 122; //scan.nextInt();
            foundPlan = mobileCompany.findPlan(userID, planID);

            if (foundPlan == null) {
                System.out.println("Plan has not been found");
            } else {
                foundPlan.print();
            }

            System.out.println("--------------------------------------");
            // print all users inside the company
            System.out.println("Print the Company including All users");
            mobileCompany.print();
            System.out.println();

            System.out.println("Print company information with ToString()");
            System.out.println(mobileCompany);
            System.out.println();

            // Print the total payments for a given userID by calling calcTotalPayments (int userID)
            // System.out.print("Enter a userID: ");
            // int userID = scan.nextInt();
            // System.out.printf("Total payments for this user: %.2f%n", mobileCompany.calcTotalPayments(userID));

            System.out.println("Total payments for user 489123");
            System.out.printf("Total payments for this user: %.2f%n", mobileCompany.calcTotalPayments(489123));
            System.out.println();

            // Print the total payments for all users in the company
            System.out.println("Total monthly payment for of all users inside the company: ");
            //System.out.println(mobileCompany.calcTotalPayments());
            System.out.printf("Total payments for all users in company: %.2f\n", mobileCompany.calcTotalPayments());
            // you also can move these messages and prints to a method inside UITOOLS

            System.out.println();

            System.out.println("----------------All Plans----------------------");
            // Call allPlans() for the mobileCompany and store it in an ArrayList and print the list by using MobilePlan.printPlans()
            HashMap<Integer, MobilePlan> mobileCompPlans = mobileCompany.allPlans();
            MobilePlan.printPlans(mobileCompPlans);

            System.out.println("--------------------------------------");
            mobileCompany.mobilePriceRise(0.1);
            System.out.printf("The TotalMonthlyPayment of users after 10 percent rise: %.2f%n", mobileCompany.calcTotalPayments());
            System.out.println("Print All the users after mobile price rise");
            mobileCompany.print();

            System.out.println("Print a user after mobile price rise");
//            mobileCompany.mobilePriceRise(user0.getUserID(),0.1); //wrong as it does not check the output
//            UITools.mobilePriceRise(mobileCompany,489123,0.1); //or
            UITools.mobilePriceRise(mobileCompany, user3.getUserID(), 0.1);
            user3.print();

            //For a given userID and expiry date call filterByExpiryDate (int userID, MyDate date), store the filtered list and print the list by using MobilePlan.printPlans()
            System.out.println("-----------------filter a user plans by Expiry Date--------------------");
            HashMap<Integer, MobilePlan> filteredByExpiryDatePlans1 = mobileCompany.filterByExpiryDate(user3.getUserID(), new MyDate(2020, 7, 15));
            MobilePlan.printPlans(filteredByExpiryDatePlans1);
            System.out.println();

            // For a given mobile model and userID call mobileCompany.filterByMobileModel (String mobileModel) and print the filtered list
            System.out.println("-----------------filter a user plans by Mobile Model------------------");
            MobilePlan.printPlans(mobileCompany.filterByMobileModel(user4.getUserID(), "Iphone")); //Without the seperate list
            System.out.println();

            //For the whole company call filterByExpiryDate
            HashMap<Integer, MobilePlan> filteredByExpriyDatePlans = mobileCompany.filterByExpiryDate(new MyDate(2015, 6, 15));
            System.out.println("Filter By Expiry Date");
            MobilePlan.printPlans(filteredByExpriyDatePlans);//or no seperate list and call it directly
            System.out.println();

            //For the whole company call filterByExpiryDate and print the list
            HashMap<Integer, MobilePlan> mobileModelPlans = mobileCompany.filterByMobileModel("Galaxy S10");
            System.out.println("Plans by Mobile Model");
            MobilePlan.printPlans(mobileModelPlans);
            System.out.println();

            // Ask user to enter a date (year, month, and day) and call filterByExpiryDate (MyDate date) and print the filtered list
            //commneted for assigment I
//            System.out.println("--------------------------------------");
//            System.out.print("Enter a year: ");
//            int year = scan.nextInt();
//            System.out.print("Enter a month: ");
//            int month = scan.nextInt();
//            System.out.print("Enter a day: ");
//            int day = scan.nextInt();
//            MyDate userDate = new MyDate(year, month, day);
//            ArrayList<MobilePlan> filteredList = mobileCompany.filterByExpiryDate(userDate);
//            MobilePlan.printPlans(filteredList);


            // Find a user with the given ID (valid) and save it in a user object. Ask user to provide a new address and change the current address for the given user
            System.out.println("--------------------------------------");
            User foundUser = mobileCompany.findUser(489123);
            Address address = new Address(114, "Sara St", "Bondi", "Sydney");// instead of scanner
            if (foundUser != null) {
                System.out.println("Found User:" + foundUser.getUserID());
                foundUser.setAddress(address);
                System.out.println("New Address:" + foundUser.getAddress());
                System.out.println();
            } else {
                System.out.println("User has not been found");
            }

            // standard test

            //Populate Distinct City Names
            System.out.println("---PopulateDistinctCityNames---");
            ArrayList<String> uniqueCities = mobileCompany.populateDistinctCityNames();

            for (String city : uniqueCities) {
                System.out.print(city + " , ");
            }
            System.out.println();

            //Get the total payments for a city
            System.out.println("---Get Total Payment For a City (Wollongong)---");
            double totalPaymentForACity = mobileCompany.getTotalPaymentForCity("Wollongong");
            System.out.println("Total payments for City Wollongong:" + totalPaymentForACity);
            System.out.println();

            //Get the total payments for each city in a list
            System.out.println("---Get Total Payment for all City---");
            //Uses unique cities form test above
            ArrayList<Double> totalPaymentForAllCity = mobileCompany.getTotalPaymentPerCity(uniqueCities);

            //Prints the city payment report
            System.out.println("---Report Total Payments for all Cities---");
            mobileCompany.reportPaymentPerCity(uniqueCities, totalPaymentForAllCity);

            //use the other report method without the need to send the seperate list
            System.out.println("---Report Total Payments for all Cities report method with no parameters---");
            mobileCompany.reportPaymentPerCity();

            //Advanced Test Case

            //Populate Distinct Mobile Models for all users inside the company
            System.out.println("---Populate Distinct Mobile Models---");
            ArrayList<String> uniqueModels = mobileCompany.populateDistinctMobileModels();
            for (String model : uniqueModels) {
                System.out.print(model + " , ");
            }

            System.out.println();

            //Get the total payments for each model in a list
            System.out.println("---Get Total Payment for all models---");
            //Uses unique models form test above
            ArrayList<Double> totalPaymentForAllModels = mobileCompany.getTotalPaymentPerMobileModel(uniqueModels);

            //Get the count for each model in a list
            System.out.println("---Get count for all models---");
            //Uses unique models form test above
            ArrayList<Integer> countForAllModels = mobileCompany.getTotalCountPerMobileModel(uniqueModels);


            //Prints the city payment report
            System.out.println("---Report Total and average Payments for all Models---");
            mobileCompany.reportPaymentsPerMobileModel(uniqueModels, countForAllModels, totalPaymentForAllModels);

            // or using the report with no parameter
            System.out.println("---Report Total and average Payments for all Models with the report method with no parameters---");
            mobileCompany.reportPaymentsPerMobileModel();

            //Populate Distinct Mobile Models for one user
            System.out.println("---Populate Distinct Mobile Models for user 3 ---");
            uniqueModels = user3.populateDistinctMobileModels();

            for (String model : uniqueModels) {
                System.out.print(model + " , ");
            }

            System.out.println();

            //Get the total payments for a mobile model
            System.out.println("---Get Total Payment For a mobile model user 3---");
            double totalPaymentForAModel = user3.getTotalPaymentForMobileModel("Iphone X", flatRate);
            System.out.println("Total payment for Iphone X : user 3 : " + totalPaymentForAModel);
            System.out.println();

            //Get the total count for a mobile model
            System.out.println("---Get the count For a mobile model user 3---");
            int countForAModel = user3.getTotalCountForMobileModel("Iphone X");
            System.out.println("Count for Iphone X : user 3 : " + countForAModel);
            System.out.println();

            //Get the total payments for each model in a list
            System.out.println("---Get Total Payment for all models -  user3: ---");
            //Uses unique models form test above
            totalPaymentForAllModels = user3.getTotalPaymentPerMobileModel(uniqueModels, flatRate);

            //Get the count for each model in a list
            System.out.println("---Get count for all models - user3: ---");
            //Uses unique models form test above
            countForAllModels = user3.getTotalCountPerMobileModel(uniqueModels);

            //Prints the city payment report
            System.out.println("---Report Total and average Payments for all mobile models user 3: ---");
            user3.reportPaymentsPerMobileModel(uniqueModels, countForAllModels, totalPaymentForAllCity);

            // or using the report with no parameter
            System.out.println("---Report Total and average Payments for all mobile models user 3: with the report method with no parameters---");
            user3.reportPaymentsPerMobileModel(flatRate);
        }//everything inside else for admin login
    }
}

