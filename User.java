
/**
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class User implements Cloneable, Comparable<User>, Serializable , Comparator<User> {

    private String name;
    // private String username; not in the spec
    private int userID;
    private String password;
    private Address address;
    //private ArrayList<MobilePlan> plans;
    private HashMap<Integer, MobilePlan> plans;
    private static int countID = 2000; //base id to be 2000

    private HashMap<Integer, Integer> uniqueID = new HashMap<Integer, Integer>();

    public User(int userID, String name, Address address, String password) //providing ID
    {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.password = password;
        //this.plans = new ArrayList<MobilePlan>();
        this.plans = new HashMap<Integer, MobilePlan>();
    }

    public User(String name, Address address, String password) //Generating ID
    {
        this.name = name;
        this.userID = countID++; //automatic ID generation
        this.address = address;
        this.password = password;
        //this.plans = new ArrayList<MobilePlan>();
        this.plans = new HashMap<Integer, MobilePlan>();
    }

    public User() {

    }

    public static void printUsers(HashMap<Integer, User> users) {
        for (User user : users.values()) {
            user.print();
        }
    }

    public int getID() {
        return userID;
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
//    public ArrayList<MobilePlan> getPlans() {
//        return plans;
//    }

    public boolean validateUser(int userID, String password) // and not username as username might not be unique as we didn't check it
    {
        if ((this.userID == userID) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    //    public MobilePlan findPlan(int planID) {
//        for (MobilePlan plan : plans) {
//            if (plan.getId() == planID) {
//                return plan;
//            }
//        }
//        return null;
//    }
//    public boolean addPlan(MobilePlan plan) {
//        if (findPlan(plan.getId()) == null) {
//            plans.add(plan);
//            return true;
//        }
//        return false;
//    }
//    public double calcTotalPayments(double flatRate) {
//        return MobilePlan.calcTotalPayments(plans, flatRate);
//    }
//
//    public void mobilePriceRiseAll(double risePercent) {
//        MobilePlan.mobilePriceRiseAll(plans, risePercent);
//    }
    public boolean createPersonalPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        return addPlan(new PersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city));
    }

    public boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException {
        return addPlan(new BusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN));
    }

    //    public ArrayList<MobilePlan> filterByMobileModel(String mobileModel) {
//        return MobilePlan.filterByMobileModel(plans, mobileModel);
//    }
//
//    public ArrayList<MobilePlan> filterByExpiryDate(MyDate date) {
//        return MobilePlan.filterByExpiryDate(plans, date);
//    }
    public void printUserInformation() {
        System.out.println("User ID:" + userID + " Name:" + name);
        System.out.println(" Address: ");
        address.print();
    }

    public void print() {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
        printUserInformation(); //instead of previous 3 lines
        System.out.println("plans:");
        MobilePlan.printPlans(plans);
        System.out.println("\n");
    }

    //    public void print(double flatRate) {
//        // System.out.println("User ID:" + userID + " Name:" + name);
//        // System.out.println(" Address: ");
//        // address.print();
//        printUserInformation(); //instead of previous 3 lines
//        MobilePlan.printPlans(plans, flatRate);
//    }
//    public String toString() {
//        String output = "User ID:" + userID + " Name:" + name + " Address: " + address.toString() + " Plans: \n";
//        for (MobilePlan plan : plans) {
//            output += plan.toString() + "\n";
//        }
//        return output;
//    }
//    public void printPlans(double flatRate) {
//        /*System.out.println("List of Plans:");
//        for(MobilePlan plan : plans)
//        {
//            System.out.println(plan);
//            System.out.println("Monthly Payment ="+ plan.calcPayment(flatRate));
//        }*/
//        MobilePlan.printPlans(plans, flatRate);
//        System.out.println("Total Monthly Payments:" + calcTotalPayments(flatRate));
//    }

    public ArrayList<String> populateDistinctMobileModels() {
        ArrayList<String> models = new ArrayList<String>();
        for (MobilePlan plan : plans.values()) {
            boolean found = false;
            for (String model : models) {
                if (plan.getHandsetModel().equals(model)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                models.add(plan.getHandsetModel());
            }
        }
        return models;
    }

    public int getTotalCountForMobileModel(String mobileModel) {
        int count = 0;
        for (MobilePlan plan : plans.values()) {
            if (plan.getHandsetModel().equals(mobileModel)) {
                count++;
            }
        }
        return count;
    }

    public double getTotalPaymentForMobileModel(String mobileModel, double flatRate) {
        double total = 0;
        for (MobilePlan plan : plans.values()) {
            if (plan.getHandsetModel().equals(mobileModel)) {
                total += plan.calcPayment(flatRate);
            }
        }
        return total;
    }

    public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String> mobileModels) {
        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
//            int count=0;
        for (String model : mobileModels) {
            //assuming that the other method is not done. This code was correct:
//                count=0;
//                for( MobilePlan plan:plans)
//                {
//                    if(plan.getHandsetModel().equals(model))
//                        count++;
//                }
//                totalCounts.add(count);

            //a better way by using the other method
            totalCounts.add(getTotalCountForMobileModel(model));
        }
        return totalCounts;
    }

    public ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String> mobileModels, double flatRate) {
        ArrayList<Double> totalPayments = new ArrayList<Double>();
//            double payment=0;
        for (String model : mobileModels) {
            //assuming that the other method is not done. The folowwing code should have been done
//            payment=0;
//            for( MobilePlan plan:plans)
//            {
//                if(plan.getHandsetModel().equals(model))
//                    payment+=plan.calcPayment(flatRate);
//            }
//            totalPayments.add(payment);

            //a better way now by using the other method
            totalPayments.add(getTotalPaymentForMobileModel(model, flatRate));
        }
        return totalPayments;
    }

    //as spec but it is not good. all lists are sent as parameters
    public void reportPaymentsPerMobileModel(ArrayList<String> mobileModels, ArrayList<Integer> counts, ArrayList<Double> monthlyPayments) {
        System.out.println("\n MobileModel \t \t \t Total Monthly Payments \t \t \tAverage Monthly Payment");
        for (int i = 0; i < counts.size(); i++) {
            System.out.println(mobileModels.get(i) + " \t \t \t " + monthlyPayments.get(i) + " \t \t \t " + monthlyPayments.get(i) / (double) counts.get(i));
        }
    }

    //a list of given models and flatRate is sent
    public void reportPaymentsPerMobileModel(ArrayList<String> mobileModels, double flatRate) {
        ArrayList<Integer> counts = getTotalCountPerMobileModel(mobileModels);
        ArrayList<Double> monthlyPayments = getTotalPaymentPerMobileModel(mobileModels, flatRate);
        reportPaymentsPerMobileModel(mobileModels, counts, monthlyPayments); // instead of doing this again as below

//            System.out.println("\n MobileModel \t \t Total Monthly Payments \t \t Average Monthly Payment");
//            for (int i=0;i<counts.size();i++)
//                System.out.println(mobileModels.get(i)+" \t \t "+monthlyPayments.get(i)+" \t \t "+monthlyPayments.get(i)/(double)counts.get(i));
    }

    public void reportPaymentsPerMobileModel(double flatRate) // for all models
    {
        ArrayList<String> mobileModels = populateDistinctMobileModels();
        reportPaymentsPerMobileModel(mobileModels, flatRate); // better than below

//        ArrayList<String> mobileModels=populateDistinctMobileModels();
//        ArrayList<Integer> counts=getTotalCountPerMobileModel(mobileModels);
//        ArrayList<Double> monthlyPayments=getTotalPaymentPerMobileModel(mobileModels, flatRate);
//        reportPaymentsPerMobileModel (mobileModels, counts, monthlyPayments); // instead of doing this again as below
//            System.out.println("\n MobileModel \t \t Total Monthly Payments \t \t Average Monthly Payment");
//            for (int i=0;i<counts.size();i++)
//                System.out.println(mobileModels.get(i)+" \t \t "+monthlyPayments.get(i)+" \t \t "+monthlyPayments.get(i)/(double)counts.get(i));
    }

    //---------------------------------------------Lab_04--------------------------------------------------
//    public User(User user) {
//        this.userID = user.userID;
//        this.password = user.password;
//        this.address = user.address;
//        this.plans = new ArrayList<MobilePlan>();
//        for (MobilePlan plan : user.plans) {
//            if (plan instanceof PersonalPlan) {
//                plans.add(new PersonalPlan((PersonalPlan) plan));
//            } else if (plan instanceof BusinessPlan) {
//                plans.add(new BusinessPlan((BusinessPlan) plan));
//            }
//        }
//    }
//    public User clone() throws CloneNotSupportedException {
//        User user = (User) super.clone();
//        user.address = this.address.clone();
//        user.plans = deepCopyPlans();
//        return user;
//    }
//    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
//        ArrayList<User> shallowCopy = new ArrayList<User>();
//        for (User user : users) {
//            shallowCopy.add(user);
//        }
//        return shallowCopy;
//    }

//    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
//        ArrayList<User> deepCopy = new ArrayList<User>();
//        for (User user : users) {
//            deepCopy.add(user.clone());
//        }
//        return deepCopy;
//    }

    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        ArrayList<User> shallowCopy = new ArrayList<User>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) {
        ArrayList<User> deepCopy = new ArrayList<User>();
        for (User user : users.values()) {
            deepCopy.add(new User(user));
        }
        return deepCopy;
    }

    public ArrayList<MobilePlan> deepCopyPlans() throws CloneNotSupportedException {
        ArrayList<MobilePlan> deepCopyPlans = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            deepCopyPlans.add(plan.clone());
        }
        return deepCopyPlans;
    }

    public ArrayList<MobilePlan> shallowCopyPlans() {
        ArrayList<MobilePlan> shallowCopyPlans = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopyPlans.add(plan);
        }
        return shallowCopyPlans;
    }


//        public int compareTo1(User other) {
//        if (other.calcTotalPayments(10) > calcTotalPayments(10)) {
//            return 1;
//        } else if (other.calcTotalPayments(10) < calcTotalPayments(10)) {
//            return -1;
//        } else {
//            return 0;
//        }
//    }
//    @Override
//    public int compareTo(User o) {
//        return this.address.getCity().compareTo(o.address.getCity());
//    }

    public ArrayList<MobilePlan> sortPlansByDate() {
        ArrayList<MobilePlan> sorted = shallowCopyPlans();
        Collections.sort(sorted);
        return sorted;
    }

    //    _________________________________________________LAb 5___________________________________________
    //LAB 5 based n hashmap
    public HashMap<Integer, MobilePlan> getPlans() {
        return plans;
    }

    public MobilePlan findPlan(int planID) {
        return plans.get(planID);
    }

    public boolean addPlan(MobilePlan plan) {
        if (findPlan(plan.getId()) == null) {
            plans.put(plan.getId(), plan);
            return true;
        } else {
            return false;
        }
    }

    public void print(double flatRate) {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
        printUserInformation(); //instead of previous 3 lines
        MobilePlan.printPlans(plans, flatRate);
    }

    public HashMap<Integer, MobilePlan> filterByMobileModel(String mobileModel) {
        return MobilePlan.filterByMobileModel(plans, mobileModel);
    }

    public HashMap<Integer, MobilePlan> filterByExpiryDate(MyDate date) {
        return MobilePlan.filterByExpiryDate(plans, date);
    }

    public double calcTotalPayments(double flatRate) {
        return MobilePlan.calcTotalPayments(plans, flatRate);
    }

    public void mobilePriceRiseAll(double risePercent) {
        MobilePlan.mobilePriceRiseAll(plans, risePercent);
    }

    public String toString() {
        String output = "User ID:" + userID + " Name:" + name + " Address: " + address.toString() + " Plans: \n";
        for (MobilePlan plan : plans.values()) {
            output += plan.toString() + "\n";
        }
        return output;
    }

    public User(User user) {
        this.userID = user.userID;
        this.password = user.password;
        this.address = user.address;
        this.plans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            if (plan instanceof PersonalPlan) {
                plans.put(plan.getId(), new PersonalPlan((PersonalPlan) plan));
            } else if (plan instanceof BusinessPlan) {
                plans.put(plan.getId(), new BusinessPlan((BusinessPlan) plan));
            }
        }
    }

    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.address = this.address.clone();
        user.plans = deepCopyPlansHashMap();
        return user;
    }

    public HashMap<Integer, MobilePlan> deepCopyPlansHashMap() throws CloneNotSupportedException {
        HashMap<Integer, MobilePlan> deepCopyPlans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            deepCopyPlans.put(plan.getId(), plan.clone());
        }
        return deepCopyPlans;
    }

    public HashMap<Integer, MobilePlan> shallowCopyPlansHashMap() {
        HashMap<Integer, MobilePlan> shallowCopyPlans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopyPlans.put(plan.getId(), plan);
        }
        return shallowCopyPlans;
    }

    public static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            deepCopy.put(user.getUserID(), user.clone());
        }
        return deepCopy;
    }

    public static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            shallowCopy.put(user.getUserID(), user);
        }
        return shallowCopy;
    }

    //data aggregation reports
    //Integer capital hast chon objecte.
    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> getTotalCountPerMobileModel = new HashMap<>();
        for (MobilePlan plan : plans.values()) {
            String model = plan.getHandsetModel();
            Integer count = getTotalCountPerMobileModel.get(model);
            if (count != null) {
                getTotalCountPerMobileModel.put(model, count + 1);
            } else {
                getTotalCountPerMobileModel.put(model, 0);
            }
        }
        return getTotalCountPerMobileModel;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        HashMap<String, Double> getTotalPaymentPerMobileModel = new HashMap<>();
        for (MobilePlan plan : plans.values()) {
            String model = plan.getHandsetModel();
            Double total = getTotalPaymentPerMobileModel.get(model);
            if (total != null) {
                getTotalPaymentPerMobileModel.put(model, total + plan.calcPayment(10.0));
            } else {
                getTotalPaymentPerMobileModel.put(model, 0.0);
            }
        }
        return getTotalPaymentPerMobileModel;
    }

    public void printPaymentsForMobileModel(HashMap<String, Integer> getTotalCountPerMobileModel, HashMap<String, Double> getTotalPaymentPerMobileModel) {
        System.out.println("MOBILE MODEL           TOTAL MONTHLY PAYMENT             AVERAGE MONTHLY PAYMENT");
        System.out.println();
        for (String model : getTotalCountPerMobileModel.keySet()) {
            String mobileModel = model;
            double totalMonthly = getTotalPaymentPerMobileModel.get(model);
            double averageMonthly = totalMonthly / getTotalCountPerMobileModel.get(model);
            System.out.println(mobileModel + "         " + totalMonthly + "        " + averageMonthly);
        }
    }

    //_____________________________________________LAB_6___________________________________________
    public static HashMap<Integer, User> load(String fileName) throws IOException {
        HashMap<Integer, User> loads = new HashMap<Integer, User>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileName));
        } catch (IOException e) {
            System.out.println("Error in create/open file. ");
            System.exit(1);
        }
        try {
            while (true) {
                User user = (User) input.readObject();
                loads.put(user.getUserID(), user);
            }
        } catch (EOFException e) {
            System.out.println("No more records!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error in wrong class");
        } catch (IOException e) {
            System.out.println("Error in add obj to file ");
            System.exit(1);
        }
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            System.out.println("Error in close file");
            System.exit(1);
        }
        return loads;
    }

    public static Boolean save(HashMap<Integer, User> users, String fileName) throws IOException {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("Error in create/open file. ");
            System.exit(1);
        }
        try {
            for (User user : users.values()) {
                output.writeObject(user);
            }
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            System.exit(1);
        }
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            System.exit(1);
        }
        return true;
    }

    public String toDelimitedString() {
        String toDelimitedString = name + "," + userID + "," + address.toDelimitedString() + "," + password + "," + plans.size();
        for (MobilePlan plan : plans.values()) {
            toDelimitedString += "," + plan.toDelimitedString();
        }
        return toDelimitedString;
    }

    public static Boolean saveTextFile(HashMap<Integer, User> users, String fileName) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            for (User user : users.values()) {
                out.write(user.toDelimitedString() + "\n");
            }
            out.close();
        } catch (IOException e) {
            System.err.println("error in closing the file ");
            return false;
        }
        return true;
    }

    //int userID, String name, Address address, String password
    //int streetNum, String street, String suburb, String city
    public static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PlanException {
        HashMap<Integer, User> loads = new HashMap<Integer, User>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");
            int userID = Integer.parseInt(field[1]);
            String name = field[0];
            int streetNum = Integer.parseInt(field[2]);
            String street = field[3];
            String suburb = field[4];
            String city = field[5];
            String password = field[6];
            int plansCount = Integer.parseInt(field[7]);
            User user = new User(userID, name, new Address(streetNum, street, suburb, city), password);
            int counter = 8;
            for (int i = 0; i < plansCount; i++) {
                switch (field[counter++]) {
                    case "PP":
                        String userName = field[counter++];
                        int id = Integer.parseInt(field[counter++]);
                        String model = field[counter++];
                        String type = field[counter++];
                        MobileType typeEnum = null;
                        if (type.equalsIgnoreCase("ANDROID")) {
                            typeEnum = MobileType.Android;
                        } else if (type.equalsIgnoreCase("IOS")) {
                            typeEnum = MobileType.IOS;
                        } else if (type.equalsIgnoreCase("WINDOWS")) {
                            typeEnum = MobileType.Windows;
                        }
                        int memorySize = Integer.parseInt(field[counter++]);
                        double price = Double.parseDouble(field[counter++]);
                        int internetQuota = Integer.parseInt(field[counter++]);
                        int capLimit = Integer.parseInt(field[counter++]);
                        int day = Integer.parseInt(field[counter++]);
                        int month = Integer.parseInt(field[counter++]);
                        int year = Integer.parseInt(field[counter++]);
                        String city1 = field[counter++];

                        PersonalPlan plan = new PersonalPlan(userName, id, new MobilePhone(model, typeEnum, memorySize, price),
                                internetQuota, capLimit, new MyDate(year, month, day), city1);
                        user.addPlan(plan);
                        break;
                    case "BP":
                        String userNameBp = field[counter++];
                        int idBp = Integer.parseInt(field[counter++]);
                        String modelBp = field[counter++];
                        String typeBp = field[counter++];
                        MobileType typeEnumBp = null;
                        if (typeBp.equalsIgnoreCase("ANDROID")) {
                            typeEnumBp = MobileType.Android;
                        } else if (typeBp.equalsIgnoreCase("IOS")) {
                            typeEnumBp = MobileType.IOS;
                        } else if (typeBp.equalsIgnoreCase("WINDOWS")) {
                            typeEnumBp = MobileType.Windows;
                        }
                        int memorySizeBp = Integer.parseInt(field[counter++]);
                        double priceBp = Double.parseDouble(field[counter++]);
                        int internetQuotaBp = Integer.parseInt(field[counter++]);
                        int capLimitBp = Integer.parseInt(field[counter++]);
                        int dayBp = Integer.parseInt(field[counter++]);
                        int monthBp = Integer.parseInt(field[counter++]);
                        int yearBp = Integer.parseInt(field[counter++]);
                        int numberOfEmployees = Integer.parseInt(field[counter++]);
                        int ABN = Integer.parseInt(field[counter++]);

                        BusinessPlan planBp = new BusinessPlan(userNameBp, idBp, new MobilePhone(modelBp, typeEnumBp,
                                memorySizeBp, priceBp), internetQuotaBp, capLimitBp, new MyDate(yearBp, monthBp, dayBp), numberOfEmployees, ABN);
                        user.addPlan(planBp);
                        break;
                }
                loads.put(user.getUserID(), user);
            }
            line = reader.readLine();
        }
        reader.close();
        return loads;
    }

    //---------------------------------------------------------Ass 2-----------------------------------------
    public int idGenerator() {
        Random random = new Random();
        int id = random.nextInt(10000, 50000);
        if (id == uniqueID.get(id)) {
            idGenerator();
        }
        return id;
    }
    //lab 8---------------------------------------------------------------------------------------------------

    public void printPlans(double flatRate) {
//        MobilePlan.printPlans(plans, flatRate);
//        System.out.println("Total Monthly Payments:" + calcTotalPayments(flatRate));
        plans.entrySet().forEach(System.out::println);
    }

    public static ArrayList<User> shallowCopy(ArrayList<User> users) throws CloneNotSupportedException {
        return (ArrayList<User>) users.stream().map(x -> x).collect(Collectors.toList());
    }

    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        return (ArrayList<User>) users.stream().map(x -> {
            try {
                return x.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    //ass 3------------------------------------------------------------------------------------------------------
    public int compareTo1(User o) {
        return name.compareTo(o.name);
    }

    @Override
    public int compareTo(User other) {
        if (other.calcTotalPayments(10) > calcTotalPayments(10)) {
            return 1;
        } else if (other.calcTotalPayments(10) < calcTotalPayments(10)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compare(User o1, User o2) {
        return o1.name.compareTo(o2.name);
    }
}
