import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public abstract class MobilePlan implements Cloneable, Comparable<MobilePlan>, Serializable {
    protected String userName;
    protected int id;
    protected MobilePhone handset;
    protected int internetQuota;
    protected int capLimit;
    protected MyDate expiryDate;

    public MobilePlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate myDate) throws PlanException {
        this.userName = userName;
        if (id < 3000000 || id > 3999999) {
            throw new PlanException();
        }
        this.id = id;
        this.handset = handset;
        this.internetQuota = internetQuota;
        this.capLimit = capLimit;
        this.expiryDate = myDate;
    }

    public double getHandsetPrice() {
        return handset.getPrice();
    }

    public String getHandsetModel() {
        return handset.getModel();
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHandset(MobilePhone handset) {
        this.handset = handset;
    }

    public void setInternetQuota(int internetQuota) {
        this.internetQuota = internetQuota;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public String getUsername() {
        return userName + " ";
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setHandsetModel(String model) {
        handset.setModel(model);
    }

    public void setCapLimit(int capLimit) {
        this.capLimit = capLimit;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public abstract double calcPayment(double flatRate);

    public void mobilePriceRise(double risePercent) {
        handset.priceRise(risePercent);
    }

    public void print() {
        System.out.print(">>>>>>>>ID:" + id + " Username:" + userName);
        handset.print();
        System.out.print(" InternetQuota: " + internetQuota + " CapLimit:" + capLimit + "\n");
        //System.out.println(" Expiry Date: " + expiryDate);
        System.out.print("EXP:");
        expiryDate.print();
    }

    public void print(double flatRate) {
        print();
        System.out.print("Monthly Payment: " + calcPayment(flatRate) + "\n");
    }

    public String toString() {
        return "ID:" + id + " Username:" + userName + " Handset:" + handset + " InternetQuota: " + internetQuota + " CapLimit: " + capLimit + " Expiry Date: " + expiryDate;
    }

//    public static void printPlans(ArrayList<MobilePlan> plans) {
//        for (MobilePlan plan : plans) {
//            plan.print();
//        }
//    }


//    public static void printPlans(ArrayList<MobilePlan> plans, double flatRate) {
//        for (MobilePlan plan : plans) {
//            plan.print(flatRate);
//        }
//    }

//    public static double calcTotalPayments(ArrayList<MobilePlan> plans, double flatRate) {
//        double totalMonthlyPayments = 0;
//        for (MobilePlan plan : plans) {
//            totalMonthlyPayments += plan.calcPayment(flatRate);
//        }
//        return totalMonthlyPayments;
//    }

//    public static void mobilePriceRiseAll(ArrayList<MobilePlan> plans, double risePercent) {
//        for (MobilePlan plan : plans) {
//            plan.mobilePriceRise(risePercent);
//        }
//    }

//    public static ArrayList<MobilePlan> filterByMobileModel(ArrayList<MobilePlan> plans, String mobileModel) {
//        ArrayList<MobilePlan> filteredPlans = new ArrayList<MobilePlan>();
//        for (MobilePlan plan : plans) {
//            //if(plan.handset.getModel().contains(mobileModel)) // bad way
//            if (plan.getHandsetModel().contains(mobileModel)) {
//                filteredPlans.add(plan);
//            }
//        }
//        return filteredPlans;
//    }

//    public static ArrayList<MobilePlan> filterByExpiryDate(ArrayList<MobilePlan> plans, MyDate date) {
//        ArrayList<MobilePlan> filteredPlans = new ArrayList<MobilePlan>();
//        for (MobilePlan plan : plans) {
//            if (plan.expiryDate.isExpired(date)) {
//                filteredPlans.add(plan);
//            }
//        }
//        return filteredPlans;
//    }

    //---------------------------------------------Lab_04--------------------------------------------------

    public MobilePlan(MobilePlan mobilePlan) {
        this.userName = mobilePlan.userName;
        this.id = mobilePlan.id;
        this.handset = mobilePlan.handset;
        this.internetQuota = mobilePlan.internetQuota;
        this.capLimit = mobilePlan.capLimit;
        this.expiryDate = mobilePlan.expiryDate;
    }

    public MobilePlan clone() throws CloneNotSupportedException {
        MobilePlan plan = (MobilePlan) super.clone();
        plan.expiryDate = this.expiryDate.clone();
        plan.handset = this.handset.clone();
        return plan;
    }

//    public static ArrayList<MobilePlan> shallowCopy(ArrayList<MobilePlan> plans) {
//        ArrayList<MobilePlan> shallowCopy = new ArrayList<>();
//        for (MobilePlan plan : plans) {
//            shallowCopy.add(plan);
//        }//method ham darim
//        return shallowCopy;
//    }

//    public static ArrayList<MobilePlan> deepCopy(ArrayList<MobilePlan> plans) throws CloneNotSupportedException {
//        ArrayList<MobilePlan> deepCopy = new ArrayList<>();
//        for (MobilePlan plan : plans) {
//            deepCopy.add(plan.clone());// :)
//        }
//        return deepCopy;
//    }

//    public int compareTo(MobilePlan other) {
//        return expiryDate.isExpired(other.expiryDate) ? 1 : -1;
//    }

    //********************************************************************************************************
    //_____________________LAB 5_______________________LAB 5 ________________________________LAB 5____________
    //********************************************************************************************************

    public static void printPlans(HashMap<Integer, MobilePlan> plans) {
        for (MobilePlan plan : plans.values()) {
            plan.print();
        }
    }

    public static HashMap<Integer, MobilePlan> filterByMobileModel(HashMap<Integer, MobilePlan> plans, String mobileModel) {
        HashMap<Integer, MobilePlan> filteredPlans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            //if(plan.handset.getModel().contains(mobileModel)) // bad way
            if (plan.getHandsetModel().contains(mobileModel)) {
                filteredPlans.put(plan.getId(), plan);
            }
        }
        return filteredPlans;
    }

    public static HashMap<Integer, MobilePlan> filterByExpiryDate(HashMap<Integer, MobilePlan> plans, MyDate date) {
        HashMap<Integer, MobilePlan> filteredPlans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            if (plan.expiryDate.isExpired(date)) {
                filteredPlans.put(plan.getId(), plan);
            }
        }
        return filteredPlans;
    }

    public static double calcTotalPayments(HashMap<Integer, MobilePlan> plans, double flatRate) {
        double totalMonthlyPayments = 0;
        for (MobilePlan plan : plans.values()) {
            totalMonthlyPayments += plan.calcPayment(flatRate);
        }
        return totalMonthlyPayments;
    }

    public static void mobilePriceRiseAll(HashMap<Integer, MobilePlan> plans, double risePercent) {
        for (MobilePlan plan : plans.values()) {
            plan.mobilePriceRise(risePercent);
        }
    }

    public static void printPlans(HashMap<Integer, MobilePlan> plans, double flatRate) {
        for (MobilePlan plan : plans.values()) {
            plan.print(flatRate);
        }
    }

    public static ArrayList<MobilePlan> shallowCopy(HashMap<Integer, MobilePlan> plans) {
        ArrayList<MobilePlan> shallowCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<MobilePlan> deepCopy(HashMap<Integer, MobilePlan> plans) throws CloneNotSupportedException {
        ArrayList<MobilePlan> deepCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            deepCopy.add(plan.clone());
        }
        return deepCopy;
    }

    public static HashMap<Integer, MobilePlan> shallowCopyHashMap(HashMap<Integer, MobilePlan> plans) {
        HashMap<Integer, MobilePlan> shallowCopyHashMap = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopyHashMap.put(plan.getId(), plan);
        }
        return shallowCopyHashMap;
    }

    public static HashMap<Integer, MobilePlan> deepCopyHashMap(HashMap<Integer, MobilePlan> plans) throws CloneNotSupportedException {
        HashMap<Integer, MobilePlan> deepCopyHashMap = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            deepCopyHashMap.put(plan.getId(), plan.clone());
        }
        return deepCopyHashMap;
    }

    //----------------------------------------------Lab_6------------------------------------------------------------
    //load binary file
    public static HashMap<Integer, MobilePlan> load(String fileName) throws IOException {
        HashMap<Integer, MobilePlan> load = new HashMap<Integer, MobilePlan>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in create/open file. ");
            System.exit(1);
        }
        try {
            while (true) {
                MobilePlan plan = (MobilePlan) input.readObject();
                load.put(plan.getId(), plan);
            }
        } catch (EOFException e) {
            System.err.println("No more records!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error in wrong class");
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
        return load;
    }

    //save binary
    public static Boolean save(HashMap<Integer, MobilePlan> records, String fileName) throws IOException {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("Error in create/open file. ");
            System.exit(1);
        }
        try {
            for (MobilePlan plan : records.values()) {
                output.writeObject(plan);
            }
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            System.exit(1);
        }
        try {
            if (output != null)
                output.close();
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            System.exit(1);
        }
        return true;
    }

    public String toDelimitedString() {
        return userName + "," + id + "," + handset.toDelimitedString() + "," + internetQuota + "," + capLimit + "," + expiryDate.toDelimitedString();
    }

    //load text file
    public static HashMap<Integer, MobilePlan> loadTextFile(String fileName) throws IOException, PlanException {
        HashMap<Integer, MobilePlan> loads = new HashMap<Integer, MobilePlan>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            line = line.trim();
            String[] fields = line.split(",");
            switch (fields[0]) {
                case "PP":
                    String userName = fields[1];
                    int id = Integer.parseInt(fields[2]);
                    String model = fields[3];
                    String type = fields[4];
                    MobileType typeEnum = null;
                    if (type.equalsIgnoreCase("ANDROID")) {
                        typeEnum = MobileType.Android;
                    } else if (type.equalsIgnoreCase("IOS")) {
                        typeEnum = MobileType.IOS;
                    } else if (type.equalsIgnoreCase("WINDOWS")) {
                        typeEnum = MobileType.Windows;
                    }
                    int memorySize = Integer.parseInt(fields[5]);
                    double price = Double.parseDouble(fields[6]);
                    int internetQuota = Integer.parseInt(fields[7]);
                    int capLimit = Integer.parseInt(fields[8]);
                    int day = Integer.parseInt(fields[9]);
                    int month = Integer.parseInt(fields[10]);
                    int year = Integer.parseInt(fields[11]);
                    String city = fields[12];

                    PersonalPlan plan = new PersonalPlan(userName, id, new MobilePhone(model, typeEnum, memorySize, price),
                            internetQuota, capLimit, new MyDate(year, month, day), city);
                    loads.put(plan.getId(), plan);
                    break;

                case "BP":
                    String userNameBp = fields[1];
                    int idBp = Integer.parseInt(fields[2]);
                    String modelBp = fields[3];
                    String typeBp = fields[4];
                    MobileType typeEnumBp = null;
                    if (typeBp.equalsIgnoreCase("ANDROID")) {
                        typeEnumBp = MobileType.Android;
                    } else if (typeBp.equalsIgnoreCase("IOS")) {
                        typeEnumBp = MobileType.IOS;
                    } else if (typeBp.equalsIgnoreCase("WINDOWS")) {
                        typeEnumBp = MobileType.Windows;
                    }
                    int memorySizeBp = Integer.parseInt(fields[5]);
                    double priceBp = Double.parseDouble(fields[6]);
                    int internetQuotaBp = Integer.parseInt(fields[7]);
                    int capLimitBp = Integer.parseInt(fields[8]);
                    int dayBp = Integer.parseInt(fields[9]);
                    int monthBp = Integer.parseInt(fields[10]);
                    int yearBp = Integer.parseInt(fields[11]);
                    int numberOfEmployees = Integer.parseInt(fields[12]);
                    int ABN = Integer.parseInt(fields[13]);

                    BusinessPlan planBp = new BusinessPlan(userNameBp, idBp, new MobilePhone(modelBp, typeEnumBp,
                            memorySizeBp, priceBp), internetQuotaBp, capLimitBp, new MyDate(yearBp, monthBp, dayBp), numberOfEmployees, ABN);
                    loads.put(planBp.getId(), planBp);
                    break;
            }
            line = reader.readLine();
        }
        reader.close();
        return loads;
    }

//    public static HashMap<Integer, MobilePlan> loadTextFile(String fileName) throws IOException {
//        HashMap<Integer, MobilePlan> loads = new HashMap<Integer, MobilePlan>();
//        BufferedReader reader = new BufferedReader(new FileReader(fileName));
//        String line = reader.readLine();
//        while (line != null) {
//            line = line.trim();
//            String[] fields = line.split(",");
//        }
//    }


    //save txt file
    public static Boolean saveTextFile(HashMap<Integer, MobilePlan> saves, String fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (MobilePlan plan : saves.values()) {
                writer.write(plan.toDelimitedString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in reading file");
            return false;
        }
        return true;
    }

    //lab 8-----------------------------------------------------------------------------------------------
    public static void printPlans(ArrayList<MobilePlan> plans) {
        plans.forEach(System.out::println);
    }

    public static double calcTotalPayments(ArrayList<MobilePlan> plans, double flatRate) {
        return plans.stream().map(x -> x.calcPayment(flatRate)).reduce(0.0, (x, y) -> x + y);
    }

    public static ArrayList<MobilePlan> filterByMobileModel (ArrayList<MobilePlan> plans, String mobileModel)
    {
        return (ArrayList<MobilePlan>)(plans.stream()
                .filter(x->x.getHandsetModel().contains(mobileModel))
                .collect(Collectors.toList()));
    }

    public static void mobilePriceRiseAll(ArrayList<MobilePlan> plans, double risePercent) {
        plans.forEach(x -> x.mobilePriceRise(risePercent));
    }

    public static ArrayList<MobilePlan> shallowCopy(ArrayList<MobilePlan> plans) throws CloneNotSupportedException {
        return (ArrayList<MobilePlan>) plans.stream().map(x -> x).collect(Collectors.toList());
    }

    public static ArrayList<MobilePlan> deepCopy(ArrayList<MobilePlan> plans) throws CloneNotSupportedException {
        return (ArrayList<MobilePlan>) plans.stream().map(x -> {
            try {
                return x.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static ArrayList<MobilePlan> filterByExpiryDate(ArrayList<MobilePlan> plans, MyDate date) {
        return (ArrayList<MobilePlan>) plans.stream().filter(x -> x.getExpiryDate().isExpired(date)).collect(Collectors.toList());
    }

    public int compareTo(MobilePlan other){
        return userName.compareTo(other.userName);
    }
}

