import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;
import java.util.Map;
public class MainProgram{

    public static void main(String[] args)throws IOException {
        SortedArrayList<Activity> activities = new SortedArrayList<>();
        SortedArrayList<Customer> customers = new SortedArrayList<>();
        String menu =
                """
                f - to finish running the program.
                a - to display on the screen information about all the activities.
                c - to display on the screen information about all the customers.
                t - to update the stored data when tickets are bought by one of the registered customers.
                r - to update the stored data when a registered customer cancels tickets for a booking.
                """;
        System.out.println(menu);
        Scanner sc = new Scanner(System.in);
        try{
            Scanner read_input = new Scanner(new File("input.txt"));
            int number_of_activities = Integer.parseInt(read_input.nextLine());
            String activity_name;
            int ticket_available;
            for(int i =0;i<number_of_activities;i++){
                activity_name = read_input.nextLine();
                ticket_available = Integer.parseInt(read_input.nextLine());
                activities.insert(new Activity(activity_name,ticket_available));
            }
            int number_of_customer = Integer.parseInt(read_input.nextLine());
            String firstName;
            String sureName;
            for(int i =0;i<number_of_customer;i++){
                firstName = read_input.next();
                sureName = read_input.next();
                customers.insert(new Customer(firstName,sureName,new ArrayList<>()));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        boolean quit = false;
        while(!quit){
            String choice = sc.nextLine();
            switch (choice){
                case "f":
                    quit = true;
                    break;
                case "a":
                    System.out.println(activities);
                    break;
                case "c":
                    System.out.println(customers);
                    break;
                case "t":
                    Scanner input_name = new Scanner(System.in);
                    System.out.println("Please enter customer name:");
                    String check_c = input_name.nextLine();
                    Customer validCustomer = null;
                    boolean isValidCustomer = false;
                    boolean isValidActivity = false;
                    boolean isValidQuantity = false;
                    boolean isValidTicket = true;
                    for(int i =0;i<customers.size();i++){
                        if(check_c.equals(customers.get(i).getName())){
                            isValidCustomer = true;
                            validCustomer = customers.get(i);
                            if(validCustomer.ticket.size()==3){
                                isValidTicket = false;
                                break;
                            }
                        }
                    }
                    if(!isValidTicket){
                        System.out.println("You can't book any orders!!!");
                        break;
                    }
                    if(!isValidCustomer){
                        System.out.println("Not valid customer!!!");
                        break;
                    }
                    System.out.println("Please enter the activity you want to order:");
                    Scanner input_activity = new Scanner(System.in);
                    String check_a = input_activity.nextLine();
                    Activity validActivity = null;
                    for(int i =0;i<activities.size();i++){
                        if(check_a.equals(activities.get(i).getActivity())){
                            isValidActivity = true;
                            validActivity = activities.get(i);
                        }
                    }
                    if(!isValidActivity){
                        System.out.println("Not valid activity!!!");
                        break;
                    }
                    System.out.println("Please enter the quantity of tickets:");
                    Scanner input_quantity = new Scanner(System.in);
                    int quantity = input_quantity.nextInt();
                    if(quantity<=validActivity.ticket_left){
                        isValidQuantity = true;
                        validActivity.ticket_left-=quantity;
                        validCustomer.order(validActivity.getActivity(),quantity);
                        System.out.println("Order succeed!!!");
                    }
                    PrintWriter out = new PrintWriter("letters.txt");
                    out.println("Tickets are not available!!!");
                    out.close();
                    if(!isValidQuantity){
                        System.out.println("Ticket not enough!!!");
                        break;
                    }
                    break;
                case "r":
                    Scanner cancelName = new Scanner(System.in);
                    System.out.println("Please enter customer name:");
                    String check_C = cancelName.nextLine();
                    Customer valid_Customer = null;
                    boolean isValid_Customer = false;
                    boolean isValid_Activity = false;
                    boolean isValid_Quantity = false;
                    for(int i =0;i<customers.size();i++){
                        if(check_C.equals(customers.get(i).getName())){
                            isValid_Customer = true;
                            valid_Customer = customers.get(i);
                        }
                    }
                    if(!isValid_Customer){
                        System.out.println("Not valid customer!!!");
                        break;
                    }
                    System.out.println("Please enter the activity you want to cancel:");
                    Scanner cancelActivity = new Scanner(System.in);
                    String check_A = cancelActivity.nextLine();
                    int a =0;
                    for(int i =0;i<valid_Customer.ticket.size();i++){
                        if(valid_Customer.ticket.get(i).containsKey(check_A)){
                            isValid_Activity =true;
                            a=i;
                        }
                    }
                    if(!isValid_Activity){
                        System.out.println("Not valid activity!!!");
                        break;
                    }
                    System.out.println("Please enter the quantity of tickets you want to cancel:");
                    Scanner cancelQuantity = new Scanner(System.in);
                    int Quantity = cancelQuantity.nextInt();
                    int maxQuantity = valid_Customer.ticket.get(a).get(check_A);
                    int finalQuantity = maxQuantity-Quantity;
                    if(Quantity<=maxQuantity){
                        isValid_Quantity = true;
                        for(Activity activity :activities){
                            if(activity.getActivity().equals(check_A)){
                                activity.ticket_left+=Quantity;
                                valid_Customer.ticket.get(a).put(check_A,finalQuantity);
                                System.out.println("Cancel succeed!!!");
                            }
                        }
                    }
                    if(!isValid_Quantity){
                        System.out.println("Fail!!!Please adjust the quantity");
                        break;
                    }
                    break;
            }
        }
    }
}
