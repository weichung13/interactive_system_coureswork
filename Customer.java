/**
 * This is a class for keeping the name of customer and the information of the tickets they buy.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Customer implements Comparable<Customer> {
    String f_name;
    String s_name;
    ArrayList<HashMap<String,Integer>>ticket;

    public Customer(String f_name,String s_name,ArrayList<HashMap<String,Integer>> ticket){
        this.f_name = f_name;
        this.s_name = s_name;
        this.ticket = ticket;
    }

    /**
     * This method is used to get the name of customer.
     * @return return the customer's name.
     */
    public String getName(){
        return f_name+" "+s_name;
    }

    /**
     * This method is used to book an order of the ticket & add the ticket information of this customer
     * @param activity The activity that the customer wants to book.
     * @param quantity The number of tickets the customer wants to book.
     */
    public void order(String activity,int quantity){
        HashMap<String,Integer> ticketOrder = new HashMap<>();
        ticketOrder.put(activity,quantity);
        ticket.add(ticketOrder);
    }

    @Override
    public int compareTo(Customer c) {
        int c1 = compareName(s_name,c.s_name);
        if(c1==0){
            return compareName(f_name,c.f_name);
        }
        else{
            return c1;
        }
    }
    public int compareName(String name,String cname){
        int i = 0;
        int j = 0;
        int name_length = name.length();
        int cname_length = cname.length();
        while(i<name_length && j<cname_length){
            if(name.charAt(i)>cname.charAt(j)){
                return 1;
            }else if(name.charAt(i)<cname.charAt(j)){
                return -1;
            }
            i++;
            j++;
        }
        if(name_length>cname_length){
            return 1;
        }else if(name_length<cname_length){
            return -1;
        }else{
            return 0;
        }
    }
    public String toString(){
        return f_name + " " + s_name + ticket;
    }
}
