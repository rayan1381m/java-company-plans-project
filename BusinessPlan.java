import java.io.Serializable;

public class BusinessPlan extends MobilePlan {
    protected int numberOfEmployees;
    protected int ABN;

    public BusinessPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException {
        super(userName, id, handset, internetQuota, capLimit, expiryDate);
        this.numberOfEmployees = numberOfEmployees;
        this.ABN = ABN;
    }

    public double calcPayment(double flatRate) {
        double payment = handset.getPrice() / 24 + capLimit / 10 + internetQuota * 10 + flatRate;

        if (numberOfEmployees > 10) {
            payment += (numberOfEmployees - 10) * 50;
        }
        return payment;
    }

    public void print() {
        super.print();
        System.out.println(" Number Of Employees: " + numberOfEmployees + " ABN: " + ABN);
    }

    public String toString() {
        return super.toString() + " Number Of Employees: " + numberOfEmployees + " ABN: " + ABN;
    }

    //---------------------------------------------Lab_04--------------------------------------------------

    public BusinessPlan(BusinessPlan plan) {
        super(plan);
        this.numberOfEmployees = plan.numberOfEmployees;
        this.ABN = plan.ABN;
    }

    //---------------------------------------------------------Lab 6---------------------------------------
    public String toDelimitedString() {
        return "BP" + "," + super.toDelimitedString() + ABN + "," + numberOfEmployees;
    }

//    public String toDelimitedString(){
//        return "BP: (" + super.toDelimitedString() + "," + ABN + "," + numberOfEmployees + ")";
//    }
}
