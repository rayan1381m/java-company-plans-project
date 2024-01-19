import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main8 {
    public static void main(String[] args) throws PlanException, IOException {
        MobilePhone mobilePhone = new MobilePhone("Galaxy S10", MobileType.Android, 8, 0);
        MobilePhone mobilePhone1 = new MobilePhone("Iphone X", MobileType.IOS, 4, 15);
        MobilePhone mobilePhone2 = new MobilePhone("LG S50", MobileType.Windows, 16, 0);

        //Initialize plan objects
        MobilePlan plan0 = createPersonalPlan("OP123", 133, mobilePhone, 0, 0, new MyDate(2000, 5, 14), "Wollongong");
        MobilePlan plan1 = createPersonalPlan("Sara12", 345, mobilePhone, 30, 38, new MyDate(1999, 4, 34), "Sydney");
        MobilePlan plan2 = createPersonalPlan("John342", 435, mobilePhone1, 0, 0, new MyDate(2004, 3, 23), "Dubbo");
        MobilePlan plan3 = createBusinessPlan("Alex123", 679, mobilePhone2, 0, 0, new MyDate(2020, 6, 21), 5, 123568);
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
        company0.addUser(user5);
        company0.addUser(user0);

        UITools.addPlan(user1, plan1);
        UITools.addPlan(user1, plan3);
        UITools.addPlan(user1, plan2);
        UITools.addPlan(user3, plan0);
        UITools.addPlan(user3, plan5);
        UITools.addPlan(user1, plan4);
        UITools.addPlan(user0, plan4);
        UITools.addPlan(user5, plan4);
        UITools.addPlan(user4, plan4);
        UITools.addPlan(user2, plan4);
        UITools.addPlan(user5, plan1);

        HashMap<Integer, MobilePlan> plans = company0.allPlans();
        ArrayList<MobilePlan> plansArr = company0.allPlansArrayList();


        //part a -filter username base on pattern "123"
        System.out.println("*******************************   part a  **********************************************");
        Predicate<MobilePlan> p = x -> x.getUsername().contains("123");
        plans.values().stream().filter(p).collect(Collectors.toList()).forEach(x -> x.print());

        //part b -filter username base on pattern "123" and calc total payment
        System.out.println("*******************************   part b  **********************************************");
        plans.values().stream().filter(p).collect(Collectors.toList()).forEach(x -> x.print(10));

        //part c -Find the first plan with the payment between $20 to $50 and display the username, ID and monthly payment
        System.out.println("*******************************   part c  **********************************************");
        Predicate<MobilePlan> p1 = x -> x.calcPayment(25) >= 20 && x.calcPayment(25) <= 50;
        System.out.println(plans.values().stream().filter(p1).findFirst().map(x -> String.format("%s%s%s", x.getUsername(), x.getId() + " ", x.calcPayment(10))).get());

        //part d -Find all plans with the payment between $20 to $50, sort them by ID and display the username, ID and monthly payment for each plan.
        System.out.println("*******************************   part d  **********************************************");
        plans.values().stream().filter(p1).sorted(Comparator.comparing(x -> x.getId())).forEach(x -> System.out.println(x.getUsername() + x.getId() + " " + x.calcPayment(10)));

        //part e -Calculate the total monthly payment for all plans with the payment between $20 to $50
        System.out.println("*******************************   part e  **********************************************");
        System.out.print("Total: ");
        DoubleStream.of(MobilePlan.calcTotalPayments(new ArrayList<>(plans.values().stream().filter(p1).collect(Collectors.toList())), 10)).forEach(System.out::println);

        System.out.println("*******************************   part f  **********************************************");
        Predicate<MobilePlan> c1 = x -> x.getUsername().equals("John342");
        ArrayList<MobilePlan> plans2 = filterPlans(plansArr, c1);
        MobilePlan.printPlans(plans2);

        System.out.println("*******************************   part g  **********************************************");
        System.out.println("*******************************   part h  **********************************************");
        Map<MyDate, List<MobilePlan>> groupByExpiryYear;
        groupByExpiryYear = plansArr.stream().collect(Collectors.groupingBy(MobilePlan::getExpiryDate));
        groupByExpiryYear.forEach((MyDate d, List<MobilePlan> e) ->
        {
            System.out.println("\u001B[32m" + "                                          " + d + "\u001B[0m");
            e.forEach(e1 -> System.out.println("\n" + e1));
        });

    }

    public static ArrayList<MobilePlan> filterPlans(ArrayList<MobilePlan> plans, Predicate<MobilePlan> criteria) {
        Stream<MobilePlan> plansStream = plans.stream().filter(criteria);
        List<MobilePlan> filtered = plansStream.toList();
        return new ArrayList<>(filtered);
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
