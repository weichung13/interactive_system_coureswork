/**
 * This is a subclass of Arraylist n such a way that the items of a sorted ArrayList are sorted in ascending order.
 */

import java.util.ArrayList;
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {
    /**
     * This is a method that allow user to insert an element into a sorted arraylist.
     * @param element any element.
     */
    public void insert(E element){
        if(size()==0){
            add(element);
            return;
        }
        int left = 0;
        int right = this.size()-1;
        if(element.compareTo(get(left))<0){
            add(0,element);
            return;
        }
        if(element.compareTo(get(right))>0){
            add(element);
            return;
        }
        int mid = (left+right)/2;
        while(left<=right){
            if(mid==size()-1){
                add(element);
                break;
            }
            if(element.compareTo(get(mid))>0 && element.compareTo(get(mid+1))<0){
                add(mid+1,element);
                break;
            }
            else if(element.compareTo(get(mid))>0){
                left = mid+1;
            }else if (element.compareTo(get(mid))<0){
                right = mid-1;
            }
            mid = (left+right)/2;
        }

    }

}
