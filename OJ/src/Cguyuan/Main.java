package Cguyuan;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Employee[] staff = new Employee[n];
        
        for (int i = 0; i < n; i++) {
            int type = in.nextInt();
            String first = in.next();
            String last = in.next();
            String ssn = in.next();
            
            switch (type) {
                case 0:
                    staff[i] = new SalariedEmployee(first, last, ssn, in.nextDouble());
                    break;
                case 1:
                    staff[i] = new HourlyEmployee(first, last, ssn, 
                        in.nextDouble(), in.nextDouble());
                    break;
                case 2:
                    staff[i] = new CommissionEmployee(first, last, ssn, 
                        in.nextDouble(), in.nextDouble());
                    break;
                case 3:
                    staff[i] = new BasePlusCommissionEmployee(first, last, ssn, 
                        in.nextDouble(), in.nextDouble(), in.nextDouble());
                    break;
            }
        }
        
        int m = in.nextInt();
        while (m-- > 0) {
            int type = in.nextInt();
            String key = in.next();
            List<Employee> result = new ArrayList<>();
            
            for (Employee e : staff) {
                if ((type == 0 && e.getFirstName().equals(key)) ||
                    (type == 1 && e.getSocialSecurityNumber().equals(key))) {
                    result.add(e);
                }
            }
            
            result.sort(null);
            for (Employee e : result) {
                System.out.printf("%s; earning:%.2f\n", e, e.earning());
            }
        }
        
        in.close();
    }
}

abstract class Employee implements Comparable<Employee> {
    protected String firstName;
    protected String lastName;
    protected String socialSecurityNumber;
    
    public Employee(String first, String last, String ssn) {
        this.firstName = first;
        this.lastName = last;
        this.socialSecurityNumber = ssn;
    }
    
    public abstract double earning();
    
    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.earning(), other.earning());
    }
    
    @Override
    public String toString() {
        return String.format("firstName:%s; lastName:%s; socialSecurityNumber:%s", 
            firstName, lastName, socialSecurityNumber);
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void setFirstName(String first) { this.firstName = first; }
    public void setLastName(String last) { this.lastName = last; }
}

class SalariedEmployee extends Employee {
    private double weeklySalary;
    
    public SalariedEmployee(String first, String last, String ssn, double salary) {
        super(first, last, ssn);
        this.weeklySalary = salary;
    }
    
    @Override
    public double earning() {
        return weeklySalary * 4;
    }
    
    public double getWeeklySalary() { return weeklySalary; }
    public void setWeeklySalary(double salary) { this.weeklySalary = salary; }
}

class HourlyEmployee extends Employee {
    private double wage;
    private double hours;
    
    public HourlyEmployee(String first, String last, String ssn, double wage, double hours) {
        super(first, last, ssn);
        this.wage = wage;
        this.hours = hours;
    }
    
    @Override
    public double earning() {
        return wage * hours;
    }
    
    public double getWage() { return wage; }
    public void setWage(double wage) { this.wage = wage; }
    public double getHours() { return hours; }
    public void setHours(double hours) { this.hours = hours; }
}

class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;
    
    public CommissionEmployee(String first, String last, String ssn, 
            double sales, double rate) {
        super(first, last, ssn);
        this.grossSales = sales;
        this.commissionRate = rate;
    }
    
    @Override
    public double earning() {
        return grossSales * commissionRate;
    }
    
    public double getGrossSales() { return grossSales; }
    public void setGrossSales(double sales) { this.grossSales = sales; }
    public double getCommissionRate() { return commissionRate; }
    public void setCommissionRate(double rate) { this.commissionRate = rate; }
}

class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;
    
    public BasePlusCommissionEmployee(String first, String last, String ssn, 
            double sales, double rate, double base) {
        super(first, last, ssn, sales, rate);
        this.baseSalary = base;
    }
    
    @Override
    public double earning() {
        return super.earning() + baseSalary;
    }
    
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double base) { this.baseSalary = base; }
}
