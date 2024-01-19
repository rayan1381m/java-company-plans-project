import java.io.Serializable;
import java.lang.Math;

public class PersonalPlan extends MobilePlan {
    protected String city;

    public PersonalPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        super(userName, id, handset, internetQuota, capLimit, expiryDate);
        this.city = city;
    }

    public double calcPayment(double flatRate) {
        return handset.getPrice() / 24 + capLimit / 20 + internetQuota * 5 + flatRate;
    }

    public void print() {
        super.print();
        System.out.println(" City:" + city);
    }

    public String toString() {
        return super.toString() + " City:" + city;
    }

    //---------------------------------------------Lab_04--------------------------------------------------

    public PersonalPlan(PersonalPlan plan) {
        super(plan);
        this.city = plan.city;
    }

    //-----------------------------------------lab 6------------------------------------------------------
    public String toDelimitedString() {
        return "PP" + "," + super.toDelimitedString() + "city";
    }
}