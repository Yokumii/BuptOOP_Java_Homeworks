package Dgongyuanmenpiao;

import java.util.Scanner;

interface Discount {
    int calculatePrice(int originalPrice);
}

interface Ticket {
    String getType();
}

class AdultDiscount implements Discount {
    @Override
    public int calculatePrice(int originalPrice) {
        return originalPrice;
    }
}

class StudentDiscount implements Discount {
    @Override
    public int calculatePrice(int originalPrice) {
        return originalPrice * 8 / 10;
    }
}

class ChildrenDiscount implements Discount {
    @Override
    public int calculatePrice(int originalPrice) {
        return originalPrice - 30;
    }
}

class SoldierDiscount implements Discount {
    @Override
    public int calculatePrice(int originalPrice) {
        return 0;
    }
}

class PaperTicket implements Ticket {
    @Override
    public String getType() {
        return "PaperTicket";
    }
}

class ElectronicTicket implements Ticket {
    @Override
    public String getType() {
        return "E_ticket";
    }
}

class ParkTicket {
    private Discount discount;
    private Ticket ticket;
    
    public ParkTicket(Discount discount, Ticket ticket) {
        this.discount = discount;
        this.ticket = ticket;
    }
    
    public int getPrice(int originalPrice) {
        return discount.calculatePrice(originalPrice);
    }
    
    public String getTicketType() {
        return ticket.getType();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int originalPrice = scanner.nextInt();
        int n = scanner.nextInt();
        
        while (n-- > 0) {
            String customerType = scanner.next();
            String ticketType = scanner.next();
            
            Discount discount = null;
            if (customerType.equals("student")) {
                discount = new StudentDiscount();
            } else if (customerType.equals("children")) {
                discount = new ChildrenDiscount();
            } else if (customerType.equals("soldier")) {
                discount = new SoldierDiscount();
            } else {
                discount = new AdultDiscount();
            }
            
            Ticket ticket = ticketType.equals("paper") ? 
                new PaperTicket() : new ElectronicTicket();
            
            ParkTicket parkTicket = new ParkTicket(discount, ticket);
            
            System.out.println(parkTicket.getTicketType());
            System.out.println("Price:" + parkTicket.getPrice(originalPrice));
        }
        
        scanner.close();
    }
}
