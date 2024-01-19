import java.util.HashMap;

public class Main5 {
    public static void main(String[] args) throws PlanException {
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

//        ArrayList<MobilePlan> plans = new ArrayList<MobilePlan>();
//        plans.add(plan0);
//        plans.add(plan1);
//        plans.add(plan2);
//        plans.add(plan3);
//        plans.add(plan4);
//        plans.add(plan5);

        UITools.addPlan(user1, plan1);
        UITools.addPlan(user1, plan3);
        UITools.addPlan(user1, plan2);
        UITools.addPlan(user3, plan0);
        UITools.addPlan(user3, plan5);
        UITools.addPlan(user1, plan4);
        System.out.println("\n\n\n\n");


        HashMap<String, Integer> getTotalCountPerMobileModel = user1.getTotalCountPerMobileModel();
        HashMap<String, Double> getTotalPaymentPerMobileModel = user1.getTotalPaymentPerMobileModel();
        user1.printPaymentsForMobileModel(getTotalCountPerMobileModel, getTotalPaymentPerMobileModel);


        HashMap<String, Integer> gg = company0.getTotalCountPerMobileModel();
        HashMap<String, Double> tt = company0.getTotalPaymentPerMobileModel();
        company0.printPaymentByMobileModel(gg, tt);
        System.out.println("***************************************************************************");

        HashMap<String, Double> ee = company0.getTotalPaymentPerCity();
        company0.printPaymentByCity(ee);


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
