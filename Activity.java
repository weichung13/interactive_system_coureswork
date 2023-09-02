/**
 * This is a class for keeping the activity name and the ticket left for the activity.
 */
public class Activity implements Comparable<Activity>{
    String name;
    int ticket_left;
    public Activity(String name,int ticket_left){
        this.name = name;
        this.ticket_left = ticket_left;
    }

    /**
     * This method is used to get the name of activity.
     * @return return the activity name.
     */
    public String getActivity(){
        return name;
    }
    @Override
    public int compareTo(Activity a) {
        int nl = name.length();
        int al = a.name.length();
        int i = 0;
        int j = 0;
        while(i<nl && j<al){
            if(name.charAt(i)>a.name.charAt(j)){
                return 1;
            }else if(name.charAt(i)<a.name.charAt(j)){
                return -1;
            }
            i++;
            j++;
        }
        if(nl>al){
            return 1;
        }else if(nl==al){
            return 0;
        }else {
            return -1;
        }

    }
    public String toString() {
        return name + " " + ticket_left;
    }
}
