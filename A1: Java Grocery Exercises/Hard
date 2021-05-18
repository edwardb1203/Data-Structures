package a1;

import java.util.*;

public class A1Jedi {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        // for this program, we will set up two hashmaps
        // one for every item scanned in, goes into an hashmap that will have all the key items- but no values yet
        // now everytime the item appears in a customers order, we will simply add +1 to the keys int value
        // by the end of our program we will have a filled hashmap with keys and values according to how many times an item appeared (unique customers)
        // the second hashmap will also have the items as strings, and the values will be empty
        // this one will keep track of the total number of that item

        LinkedHashMap<String, Integer> uniquecustomers = new LinkedHashMap<String, Integer>();
        LinkedHashMap<String, Integer> howmanypurchased = new LinkedHashMap<String, Integer>();

        int num_of_item = scan.nextInt();
        int i = 0;
        int j = 0;
        int z = 0;

        while (i<num_of_item) {
            String item = scan.next();
            scan.nextDouble();
            uniquecustomers.put(item, 0);
            howmanypurchased.put(item, 0);
            i++;
        }

        int num_of_c = scan.nextInt();

        while (j<num_of_c) {
            scan.next();
            scan.next();
            int itemsbought = scan.nextInt();
            String[] itemspercustomer = new String[itemsbought];
            while (z<itemsbought){
                // create an array to keep track of items bought for each customer
                // check array to see if item already appears, if it does then do not add to uniquec
                // if it doesnt, then add to array and update uniquec
                int numofthing = scan.nextInt();
                String nameofthing = scan.next();
                for(Map.Entry<String, Integer> entry: uniquecustomers.entrySet()) {
                    String key = entry.getKey();
                    if (nameofthing.equals(key)) {
                        if (Arrays.asList(itemspercustomer).contains(nameofthing)) {
                            howmanypurchased.put(key, howmanypurchased.get(key) + numofthing);
                            z++;
                        }
                        else {
                            itemspercustomer[z] = nameofthing;
                            uniquecustomers.put(key,uniquecustomers.get(key) + 1);
                            howmanypurchased.put(key, howmanypurchased.get(key) + numofthing);
                            z++;
                        }
                    }
                }
            }
            z=0;
            j++;
        }

        for(Map.Entry<String, Integer> entry: uniquecustomers.entrySet()) {
            String key = entry.getKey();
            int value = uniquecustomers.get(key);
            if (value == 0) {
                System.out.println("No customers bought" + " " + key);
            }
            else {
                System.out.println(value +" " + "customers bought" + " " + howmanypurchased.get(key) + " " + " " + key);
            }
        }
    }
}
