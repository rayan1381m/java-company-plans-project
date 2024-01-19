
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MobileCompany implements Cloneable, Serializable {
    private String name;
    private String adminUsername;
    private String adminPassword;
    private double flatRate;
    //private ArrayList<User> users;
    private HashMap<Integer, User> users;

    public MobileCompany(String name, String adminUsername, String adminPassword, double flatRate) {
        this.name = name;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
//      this.users = new ArrayList<User>();
        this.users = new HashMap<Integer, User>();
    }

    public double getFlatRate() {
        return flatRate;
    }

    public HashMap<Integer, User> getUsers() {
        return this.users;
    }

    public String getName() {
        return name;
    }

    public boolean validateAdmin(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public User validateUser(int userID, String password) {

//        for(User user : users) // a bad way
//        {
//            if(user.validateUser(userID, password))
//            {
//                return user;
//            }
//        }
//        return null;

        // Correct way by using findUser
        User user = findUser(userID);
        if ((user != null) && user.validateUser(userID, password)) {
            return user;
        } else
            return null;
    }

//    public User findUser(int userID) {
//        for (User user : users) {
//            if (user.getUserID() == userID) {
//                return user;
//            }
//        }
//        return null;
//    }

//    public boolean addUser(User user) {
//        if (findUser(user.getUserID()) == null) {
//            users.add(user);
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean addUser(String name, int userID, Address address, String password) {
        User user = new User(userID, name, address, password);
        return addUser(user);
    }

    public boolean addUser(String name, Address address, String password) //automatic ID generation
    {
        User user = new User(name, address, password); // user constructor to generate ID automatically
        return addUser(user);
    }

    public boolean addPlan(int userID, MobilePlan plan) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPlan(plan);
        }
        return false;
    }

    public MobilePlan findPlan(int userID, int planID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPlan(planID);
        } else
            return null;
    }

    public void printPlans(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.printPlans(flatRate);
        }
    }

    public void printUser(int userID) //added for UI
    {
        User user = findUser(userID);
        if (user != null) {
            user.print(flatRate);
        }
    }

    public boolean createPersonalPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int caplimit, MyDate expiryDate, String city) throws PlanException {
        User user = findUser(userID);
        if (user != null) {
            return user.createPersonalPlan(username, id, mobilePhone, internetQuota, caplimit, expiryDate, city);
        }
        return false;
    }

    public boolean createBusinessPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int caplimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException {
        User user = findUser(userID);
        if (user != null) {
            return user.createBusinessPlan(username, id, mobilePhone, internetQuota, caplimit, expiryDate, numberOfEmployees, ABN);
        }
        return false;
    }

    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPayments(flatRate);
        }
        return 0;
    }

//    public double calcTotalPayments() {
//        double totalPayment = 0;
//        for (User user : users) {
//            totalPayment += user.calcTotalPayments(flatRate);
//        }
//        return totalPayment;
//    }

    public boolean mobilePriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            user.mobilePriceRiseAll(risePercent);
            return true;
        }
        return false;
    }

//    public void mobilePriceRise(double risePercent) {
//        for (User user : users) {
//            user.mobilePriceRiseAll(risePercent);
//        }
//    }

    public ArrayList<MobilePlan> allPlansArrayList() {
        ArrayList<MobilePlan> allUserPlans = new ArrayList<MobilePlan>();
        for (User user : users.values()) {
            for (MobilePlan plan : user.getPlans().values()) {
                allUserPlans.add(plan); // you are not allowded to user addAll to undestand the algorithm better
            }
        }
        return allUserPlans;
    }

//    public ArrayList<MobilePlan> filterByMobileModel(int userID, String mobileModel) {
//        User user = findUser(userID);
//        if (user != null) {
//            return user.filterByMobileModel(mobileModel);
//        }
//        return new ArrayList<MobilePlan>();
//    }

    //    public ArrayList<MobilePlan> filterByExpiryDate(int userID, MyDate date) {
//        User user = findUser(userID);
//        if (user != null) {
//            return user.filterByExpiryDate(date);
//        }
//        return new ArrayList<MobilePlan>();
//    }
//
//    public ArrayList<MobilePlan> filterByMobileModel(String mobileModel) {
//        ArrayList<MobilePlan> filteredPlans = new ArrayList<MobilePlan>();
//        for (User user : users) {
//            ArrayList<MobilePlan> userFilteredPlans = user.filterByMobileModel(mobileModel);
//            for (MobilePlan plan : userFilteredPlans) {
//                filteredPlans.add(plan);
//            }
//        }
//        return filteredPlans;
//    }
//
//    public ArrayList<MobilePlan> filterByExpiryDate(MyDate date) {
//        ArrayList<MobilePlan> filteredPlans = new ArrayList<MobilePlan>();
//        for (User user : users) {
//            ArrayList<MobilePlan> userFilteredPlans = user.filterByExpiryDate(date);
//            for (MobilePlan plan : userFilteredPlans) {
//                filteredPlans.add(plan);
//            }
//        }
//        return filteredPlans;
//    }
//
//    public void print() {
//        System.out.println("Company name: " + name + " Flat Rate: " + flatRate);
//        for (User user : users) {
//            //user.print(); // WRONG not based on the spec
//
//            // user.printUserInformation() ; // correct but duplicate
//            // user.printPlans(flatRate);
//
//            user.print(flatRate); // add this to user and make it better than previous 2 lines
//        }
//    }
//
//    public String toString() {
//        String printString = "Company name: " + name + " Flat Rate: " + flatRate + "\n";
//        for (User user : users) {
//            printString += user.toString() + "\n";
//        }
//        return printString;
//    }
//
//    public ArrayList<String> populateDistinctCityNames() {
//        ArrayList<String> cities = new ArrayList<String>();
//        for (User user : users) {
//            boolean found = false;
//            for (String city : cities) {
//                if (user.getCity().equals(city)) {
//                    found = true;
//                    break;
//                }
//            }
//            if (!found)
//                cities.add(user.getCity());
//        }
//        return cities;
//    }
//
//    public double getTotalPaymentForCity(String city) {
//        double totalPaymentForCity = 0;
//        for (User user : users) {
//            if (user.getCity().equals(city)) {
//                totalPaymentForCity += user.calcTotalPayments(flatRate);
//            }
//        }
//        return totalPaymentForCity;
//    }
//
    public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
        ArrayList<Double> totalPerCity = new ArrayList<>();
        for (String city : cities) {
            totalPerCity.add(getTotalPaymentForCity(city));
        }
        return totalPerCity;
    }

    public void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments) // it is in the spec but not good
    {
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }

    public void reportPaymentPerCity(ArrayList<String> cities) // for a list of given cities
    {
        ArrayList<Double> payments = getTotalPaymentPerCity(cities);
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }

    public void reportPaymentPerCity() // for all cities
    {
        ArrayList<String> cities = populateDistinctCityNames();
        ArrayList<Double> payments = getTotalPaymentPerCity(cities);
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }
//
//    public ArrayList<String> populateDistinctMobileModels() {
//        ArrayList<String> allModels = new ArrayList<String>();
//        for (User user : users) {
//            ArrayList<String> userModels = user.populateDistinctMobileModels();
//            for (String userModel : userModels) {
//                boolean found = false;
//                for (String model : allModels) {
//                    if (model.equals(userModel)) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found)
//                    allModels.add(userModel);
//            }
//        }
//        return allModels;
//    }

//    public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String> mobileModels) {
//        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
//        int count = 0;
//        for (String model : mobileModels) {
//            count = 0;
//            for (User user : users) {
//                count += user.getTotalCountForMobileModel(model); // by calling this method which is not the same as spec
//            }
//            totalCounts.add(count);
//        }
//        return totalCounts;
//    }

//    public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String> mobileModels) {
//        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
//        for (int i = 0; i < mobileModels.size(); i++) {
//            totalCounts.add(0);// initial values with 0
//        }
//
//        for (User user : users) {
//            ArrayList<Integer> userCounts = user.getTotalCountPerMobileModel(mobileModels);
//            for (int i = 0; i < userCounts.size(); i++) {
//                totalCounts.set(i, totalCounts.get(i) + userCounts.get(i));//for each element of total add the user count
//            }
//        }
//        return totalCounts;
//    }

//    public ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String> mobileModels) {
//        ArrayList<Double> totalPayments = new ArrayList<Double>();
//        for (int i = 0; i < mobileModels.size(); i++) {
//            totalPayments.add(0.0);// initial values with 0
//        }
//
//        for (User user : users) {
//            ArrayList<Double> userTotalPayments = user.getTotalPaymentPerMobileModel(mobileModels, flatRate);
//            for (int i = 0; i < userTotalPayments.size(); i++) {
//                totalPayments.set(i, totalPayments.get(i) + userTotalPayments.get(i));
//            }
//        }
//        return totalPayments;
//    }

    //as spec but it is not good. all lists are sent as parameters
    public void reportPaymentsPerMobileModel(ArrayList<String> mobileModels, ArrayList<Integer> counts, ArrayList<Double> monthlyPayments) {
        System.out.println("\n MobileModel \t \t \t Total Monthly Payments \t \t \t Average Monthly Payment");
        for (int i = 0; i < counts.size(); i++)
            System.out.println(mobileModels.get(i) + " \t \t \t " + monthlyPayments.get(i) + " \t \t \t " + monthlyPayments.get(i) / (double) counts.get(i));
    }

    //a list of given models
    public void reportPaymentsPerMobileModel(ArrayList<String> mobileModels) {
        ArrayList<Integer> counts = getTotalCountPerMobileModel(mobileModels);
        ArrayList<Double> monthlyPayments = getTotalPaymentPerMobileModel(mobileModels);
        reportPaymentsPerMobileModel(mobileModels, counts, monthlyPayments);
    }

    public void reportPaymentsPerMobileModel() // for all models
    {
        ArrayList<String> mobileModels = populateDistinctMobileModels();
        reportPaymentsPerMobileModel(mobileModels);
    }

    //---------------------------------------------Lab_04--------------------------------------------------

//    public MobileCompany(MobileCompany mobileCompany) {
//        this.name = mobileCompany.name;
//        this.adminUsername = mobileCompany.adminUsername;
//        this.adminPassword = mobileCompany.adminPassword;
//        this.flatRate = mobileCompany.flatRate;
//        this.users = new ArrayList<User>();
//        for (User user : mobileCompany.users) {
//            users.add(new User(user));
//        }
//    }

    //    public MobileCompany clone() throws CloneNotSupportedException {
//        MobileCompany company = (MobileCompany) super.clone();
//        company.users = deepCopyUsersHashMap();
//        return company;
//    }
//
    public ArrayList<User> deepCopyUsers() {
        ArrayList<User> deepCopy = new ArrayList<User>();
        for (User user : users.values()) {
            deepCopy.add(new User(user));
        }
        return deepCopy;
    }

    public ArrayList<User> shallowCopyUsers() {
        ArrayList<User> shallowCopy = new ArrayList<User>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public ArrayList<User> sortUsers() {
        ArrayList<User> sortUsers = shallowCopyUsers();
        Collections.sort(sortUsers);
        return sortUsers;
    }

    //_____________________________________________LAb 5________________________________________________________
    public User findUser(int userID) {
        return users.get(userID);
    }

    public boolean addUser(User user) {
        if (findUser(user.getUserID()) == null) {
            users.put(user.getUserID(), user);
            return true;
        } else {
            return false;
        }
    }

    public double calcTotalPayments() {
        double totalPayment = 0;
        for (User user : users.values()) {
            totalPayment += user.calcTotalPayments(flatRate);
        }
        return totalPayment;
    }

    public void mobilePriceRise(double risePercent) {
        for (User user : users.values()) {
            user.mobilePriceRiseAll(risePercent);
        }
    }

    public HashMap<Integer, MobilePlan> allPlans() {
        HashMap<Integer, MobilePlan> allUserPlans = new HashMap<Integer, MobilePlan>();
        for (User user : users.values()) {
            for (MobilePlan plan : user.getPlans().values()) {
                allUserPlans.put(plan.getId(), plan); // you are not allowded to user addAll to undestand the algorithm better
            }
        }
        return allUserPlans;
    }

    public HashMap<Integer, MobilePlan> filterByMobileModel(int userID, String mobileModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByMobileModel(mobileModel);
        }
        return new HashMap<Integer, MobilePlan>();
    }

    public HashMap<Integer, MobilePlan> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new HashMap<Integer, MobilePlan>();
    }

    public HashMap<Integer, MobilePlan> filterByMobileModel(String mobileModel) {
        HashMap<Integer, MobilePlan> filteredPlans = new HashMap<Integer, MobilePlan>();
        for (User user : users.values()) {
            HashMap<Integer, MobilePlan> userFilteredPlans = user.filterByMobileModel(mobileModel);
            for (MobilePlan plan : userFilteredPlans.values()) {
                filteredPlans.put(plan.getId(), plan);
            }
        }
        return filteredPlans;
    }

    public HashMap<Integer, MobilePlan> filterByExpiryDate(MyDate date) {
        HashMap<Integer, MobilePlan> filteredPlans = new HashMap<Integer, MobilePlan>();
        for (User user : users.values()) {
            HashMap<Integer, MobilePlan> userFilteredPlans = user.filterByExpiryDate(date);
            for (MobilePlan plan : userFilteredPlans.values()) {
                filteredPlans.put(plan.getId(), plan);
            }
        }
        return filteredPlans;
    }

    public void print() {
        System.out.println("Company name: " + name + " Flat Rate: " + flatRate);
        for (User user : users.values()) {
            //user.print(); // WRONG not based on the spec

            // user.printUserInformation() ; // correct but duplicate
            // user.printPlans(flatRate);

            user.print(flatRate); // add this to user and make it better than previous 2 lines
        }
    }

    public String toString() {
        String printString = "Company name: " + name + " Flat Rate: " + flatRate + "\n";
        for (User user : users.values()) {
            printString += user.toString() + "\n";
        }
        return printString;
    }

    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            boolean found = false;
            for (String city : cities) {
                if (user.getCity().equals(city)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                cities.add(user.getCity());
        }
        return cities;
    }

    public double getTotalPaymentForCity(String city) {
        double totalPaymentForCity = 0;
        for (User user : users.values()) {
            if (user.getCity().equals(city)) {
                totalPaymentForCity += user.calcTotalPayments(flatRate);
            }
        }
        return totalPaymentForCity;
    }

    public ArrayList<String> populateDistinctMobileModels() {
        ArrayList<String> allModels = new ArrayList<String>();
        for (User user : users.values()) {
            ArrayList<String> userModels = user.populateDistinctMobileModels();
            for (String userModel : userModels) {
                boolean found = false;
                for (String model : allModels) {
                    if (model.equals(userModel)) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    allModels.add(userModel);
            }
        }
        return allModels;
    }

    public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String> mobileModels) {
        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
        for (int i = 0; i < mobileModels.size(); i++) {
            totalCounts.add(0);// initial values with 0
        }

        for (User user : users.values()) {
            ArrayList<Integer> userCounts = user.getTotalCountPerMobileModel(mobileModels);
            for (int i = 0; i < userCounts.size(); i++) {
                totalCounts.set(i, totalCounts.get(i) + userCounts.get(i));//for each element of total add the user count
            }
        }
        return totalCounts;
    }

    public ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String> mobileModels) {
        ArrayList<Double> totalPayments = new ArrayList<Double>();
        for (int i = 0; i < mobileModels.size(); i++) {
            totalPayments.add(0.0);// initial values with 0
        }

        for (User user : users.values()) {
            ArrayList<Double> userTotalPayments = user.getTotalPaymentPerMobileModel(mobileModels, flatRate);
            for (int i = 0; i < userTotalPayments.size(); i++) {
                totalPayments.set(i, totalPayments.get(i) + userTotalPayments.get(i));
            }
        }
        return totalPayments;
    }

    //LAb 4 modify copy contractor, clone, deep and shallow copies base on Hashmap

    public MobileCompany(MobileCompany mobileCompany) {
        this.name = mobileCompany.name;
        this.adminUsername = mobileCompany.adminUsername;
        this.adminPassword = mobileCompany.adminPassword;
        this.flatRate = mobileCompany.flatRate;
        this.users = new HashMap<Integer, User>();
        for (User user : mobileCompany.users.values()) {
            users.put(user.getID(), new User(user));
        }
    }

    public MobileCompany clone() throws CloneNotSupportedException {
        MobileCompany company = (MobileCompany) super.clone();
        company.users = deepCopyUsersHashMap();
        return company;
    }

    public HashMap<Integer, User> deepCopyUsersHashMap() {
        HashMap<Integer, User> deepCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            deepCopy.put(user.getID(), new User(user));
        }
        return deepCopy;
    }

    public HashMap<Integer, User> shallowCopyUsersHashMap() {
        HashMap<Integer, User> shallowCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            shallowCopy.put(user.getID(), user);
        }
        return shallowCopy;
    }

    public HashMap<String, Double> getTotalPaymentPerCity() {
        HashMap<String, Double> cities = new HashMap<String, Double>();
        for (User user : users.values()) {
            String city = user.getAddress().getCity();
            Double total = cities.get(city);
            if (total != null) {
                cities.put(city, total + user.calcTotalPayments(flatRate));
            } else {
                cities.put(city, user.calcTotalPayments(flatRate));
            }
        }
        return cities;
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> getTotalCountPerMobileModel = new HashMap<String, Integer>();
        for (User user : users.values()) {
            for (MobilePlan plan : user.shallowCopyPlans())
                getTotalCountPerMobileModel.put(plan.getHandsetModel(), getTotalCountPerMobileModel.values().size());
        }
        return getTotalCountPerMobileModel;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        HashMap<String, Double> getTotalPaymentPerMobileModel = new HashMap<String, Double>();
        for (User user : users.values()) {
            for (MobilePlan plan : user.shallowCopyPlans())
                getTotalPaymentPerMobileModel.put(plan.getHandsetModel(), plan.calcPayment(flatRate));
        }
        return getTotalPaymentPerMobileModel;
    }

    public void printPaymentByCity(HashMap<String, Double> cities) {
        System.out.println("*City Name*     *Total Monthly Payment*\n");
        for (String city : cities.keySet()) {
            System.out.println(city + "  " + cities.get(city));
        }
    }

    public void printPaymentByMobileModel(HashMap<String, Integer> getTotalCountPerMobileModel,
                                          HashMap<String, Double> getTotalPaymentPerMobileModel) {
        System.out.println("MOBILE MODEL     *      TOTAL MONTHLY PAYMENT       *      AVERAGE MONTHLY PAYMENT\n");
        for (String model : getTotalCountPerMobileModel.keySet()) {
            String mobileModel = model;
            double totalMonthly = getTotalPaymentPerMobileModel.get(model);
            double averageMonthly = totalMonthly / getTotalCountPerMobileModel.get(model);
            System.out.println(mobileModel + "         " + totalMonthly + "        " + averageMonthly);
        }
    }

    //_____________________________________________LAB_6___________________________________
    //load binary file
    public Boolean load(String fileName) {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            return false;
        }
        MobileCompany company = null;
        try {
            company = (MobileCompany) input.readObject();
        } catch (EOFException ex) {
            System.out.println("no more record!");
        } catch (ClassNotFoundException ex) {
            System.err.println("error in wrong class in the file ");
            return false;
        } catch (IOException ex) {
            System.err.println("error in add object to the file ");
            return false;
        }
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            System.err.println("error in close the file ");
            return false;
        }
        this.adminUsername = company.adminUsername;
        this.adminPassword = company.adminPassword;
        this.users = company.users;
        this.flatRate = company.flatRate;
        this.name = company.name;
        return true;
    }

    public MobileCompany() {

    }

    //save binary file
    public Boolean save(String fileName) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            return false;
        }
        try {
            output.writeObject(this);
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            return false;
        }
        try {
            if (output != null)
                output.close();
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            return false;
        }
        return true;
    }

    public String toDelimitedString() {
        String toDelimitedString = name + "," + adminUsername + "," + adminPassword + "," + flatRate + "," + users.size();
        for (User user : users.values()) {
            toDelimitedString += "," + user.toDelimitedString();
        }
        return toDelimitedString;
    }

    public Boolean saveTextFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(this.toDelimitedString() + "\n");
        writer.close();
        return true;
    }

    //String name, String adminUsername, String adminPassword, double flatRate
    public Boolean loadTextFile(String fileName) throws IOException, PlanException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");
            this.name = field[0];
            this.adminUsername = field[1];
            this.adminPassword = field[2];
            this.flatRate = Double.parseDouble(field[3]);
            users = new HashMap<Integer, User>();
            int userCount = Integer.parseInt(field[4]);
            int counter = 5;
            //MobileCompany company = new MobileCompany(name, adminUsername, adminPassword, flatRate);
            for (int i = 0; i < userCount; i++) {
                String name1 = field[counter++];
                int userID = Integer.parseInt(field[counter++]);
                int streetNum = Integer.parseInt(field[counter++]);
                String street = field[counter++];
                String suburb = field[counter++];
                String city = field[counter++];
                String password = field[counter++];
                int plansCount = Integer.parseInt(field[counter++]);
                User user = new User(userID, name1, new Address(streetNum, street, suburb, city), password);
                for (i = 0; i < plansCount; i++) {
                    String test = field[counter++];
                    switch (test) {
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
                }
                this.users.put(user.getUserID(), user);
            }
            line = reader.readLine();
        }
        reader.close();
        return true;
    }

    //------------------------------------------------ass 2---------------------------------------------------

//    public HashMap<String, ArrayList<User>> getUsersPerCity() {
//        HashMap<String, ArrayList<User>> usersPerCity = new HashMap<String, ArrayList<User>>();
//        ArrayList<User> users = new ArrayList<User>();
//
//    }

//    public class Comparator implements java.util.Comparator<User> {
//
//        @Override
//        public int compare(User o1, User o2) {
//            double payment1 = o1.getTotalPaymentForCity();
//        }
//    }

    //lab 8 -----------------------------------------------------------------------------------------------------
    public double calcTotalPaymentsLambda() {
        return users.values().stream().map(x -> x.calcTotalPayments(flatRate)).reduce(0.0, (x, y) -> x + y);
    }

    public void mobilePriceRiseLambda(double risePercent) {
        users.values().stream().forEach(x -> MobilePlan.mobilePriceRiseAll(x.getPlans(), risePercent));
    }
    public ArrayList<MobilePlan> filterByMobileModelLambda(String mobileModel) {
        return (ArrayList<MobilePlan>) (allPlans().values().stream().filter(x -> x.getHandsetModel().contains(mobileModel)).collect(Collectors.toList()));
    }

    public HashMap<String, Double> getTotalPaymentPerCityLambda() {
        Map<String, Double> map = new HashMap<String, Double>();
        users.values().forEach(x -> map.put(x.getCity(), x.calcTotalPayments(flatRate)));
        return (HashMap<String, Double>) map;
    }

    // ass 3 -----------------------------------------------------------------------------------------------------------

    public static MobileCompany load() throws IOException, ClassNotFoundException {
        ObjectInputStream inputst = new ObjectInputStream(Files.newInputStream(Paths.get("company.ser")));
        MobileCompany company = (MobileCompany) inputst.readObject();
        inputst.close();
        return company;
    }

    public void save() throws IOException {
        ObjectOutputStream outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("company.ser")));
        outputst.writeObject(this);
        outputst.close();
    }
}

