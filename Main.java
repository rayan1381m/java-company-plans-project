public class Main {
    public static void main(String[] args) throws PlanException {
        MobileCompany mobileCompany = new MobileCompany("HoomanCompany", "admin", "admin", 12);
        UserInterFace.mainMenu(mobileCompany);
    }
}
