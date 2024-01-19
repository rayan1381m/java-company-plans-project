import java.io.Serializable;

public class MyDate implements Cloneable, Comparable<MyDate>, Serializable {
    private int year;
    private int month;
    private int day;

    MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return year + "/" + month + "/" + day;
    }

    public void print() {
        System.out.print(" " + year + "/" + month + "/" + day + " ");
    }

    public Boolean isExpired(MyDate expiryDate) {
        if (year < expiryDate.year) {
            return false;
        } else if (year == expiryDate.year) {
            if (month < expiryDate.month) {
                return false;
            } else if (month == expiryDate.month) {
                if (day < expiryDate.day) {
                    return false;
                }
            }
        }
        return true;
    }

    //---------------------------------------------Lab_04--------------------------------------------------
    public MyDate(MyDate myDate) {
        this.day = myDate.day;
        this.month = myDate.month;
        this.year = myDate.year;
    }

    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    @Override
    public int compareTo(MyDate o) {
        if (isExpired(o) == true) {
            return -1;
        } else if (isExpired(o) == false) {
            return 0;
        } else {
            return 1;
        }
    }

    //---------------------------------------Lab-6------------------------------------
    public String toDelimitedString() {
        return year + "," + month + "," + day + ",";
    }
}
