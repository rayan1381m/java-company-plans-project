import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main4 {
    public static void main(String[] args) throws CloneNotSupportedException, PlanException {
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

        MobileCompany company0 = new MobileCompany("Rokham", "tra", "1234", 10);
        company0.addUser(user1);
        company0.addUser(user2);
        company0.addUser(user3);
        company0.addUser(user4);

        ArrayList<MobilePlan> plans = new ArrayList<MobilePlan>();
        plans.add(plan0);
        plans.add(plan1);
        plans.add(plan2);
        plans.add(plan3);
        plans.add(plan4);
        plans.add(plan5);

        UITools.addPlan(user1, plan1);
        UITools.addPlan(user1, plan3);
        UITools.addPlan(user1, plan2);
        UITools.addPlan(user1, plan0);
        System.out.println("***********************************************************************************");
//        ArrayList<MobilePlan> shallowCopyPlans = user1.shallowCopyPlans();
//        ArrayList<MobilePlan> deepCopyPlans = user1.deepCopyPlans();
        //test shallow copy
        //print user shall and deep plans

//        for (MobilePlan plan : deepCopyPlans){
//            user1.findPlan(345).setUsername("rr");
//        }
//        for (MobilePlan plan : shallowCopyPlans) {
//            plan.print();
//        }
//
//        //test deep copy
//        /*for (MobilePlan plan : deepCopyPlans) {
//            plan.print();
//        }
//        plan1.setUsername("Rayan");
//        for (MobilePlan plan : deepCopyPlans){
//            plan.print();
//        }*/
//        user1.findPlan(345).setCapLimit(10);
//        user1.findPlan(345);
//
//        user1.sortPlansByDate();
//        for(MobilePlan plan : plans){
//            plan.print();
//        }

        //sort user base on city

//        ArrayList<User> users = company0.shallowCopyUsers();
//        for(User user : users) {
//            System.out.println(user);
//        }
//
//        Collections.sort(users);
//
//        for(User user : users) {
//            System.out.println(user);
//        }

        //sort user plan base on exp date

//        ArrayList<MobilePlan> MobilePlans = user1.shallowCopyPlans();
//        for (MobilePlan plan : MobilePlans) {
//            plan.print();
//        }
//
//        Collections.sort(MobilePlans);
//        System.out.println("*****************************************");
//
//        for (MobilePlan plan : MobilePlans) {
//            plan.print();
//        }

//        ArrayList<User> companyShallow = company0.shallowCopyUsers();
//        ArrayList<User> companyDeep = company0.deepCopyUsersHashMap();

//        User user6 = new User(858585, "Dwight Manfreddi", new Address(3, "21 bakers", "London", "London"), "8585");
//        company0.addUser(user6);
//        company0.findUser(265756).setCity("London");
//        company0.sortUsers();
//        for (User user : company0.sortUsers()){
//            user.print();
//            System.out.println("+++++++");
//        }
//
//        for (MobilePlan plan : user1.shallowCopyPlans()){
//            plan.print();
//            System.out.println("************************8");
//        }
//
//        user1.sortPlansByDate();
//        for (MobilePlan plan : user1.sortPlansByDate()){
//            plan.print();
//            System.out.println("************************8");
//        }

//        MobilePlan newPlan = new PersonalPlan("Sara12", 345, new MobilePhone("Iphone 10", MobileType.Android, 8, 500), 30, 50, new MyDate(2020, 1, 1), "NY");
//        plan1 = new MobilePlan(newPlan) {
//            @Override
//            public double calcPayment(double flatRate) {
//                return 0;
//            }
//        };
//        plan1.print();

//        company0.addUser(user5);
//        user5.setCity("London");
//        company0.sortUsers();
//
//        for (MobilePlan plan : shallowCopyPlans){
//            plan.print();
//        }
//        System.out.println("****************************************");
//        for (MobilePlan plan : deepCopyPlans){
//            plan.print();
//        }

    }
}
