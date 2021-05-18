package a2;
import java.util.Arrays;
import java.util.stream.*;

public class LinkedList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;


    /**
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the size of the list, throw an IndexOutOfBounds Exception
     * <p>
     * ex: list: 1 -> 2 -> 3 -> 4
     * i: 1
     * list after removeAtIndex: 1 -> 3 -> 4
     *
     * @param i - index of node to remove
     */
    public void removeAtIndex(int i) {
        int k = 0;
        Node temp = head;
        while (temp != null) {
            if (k == i) {
                remove(temp.getValue());
                break;
            } else {
                temp = temp.getNext();
                k++;
            }
        }
        if (i > k) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Compute and return the average of all the numbers in the linked list rounded down to the nearest integer
     *
     * @return an int that is the floor of the mean of the list.
     */
    public int mean() {
        int k = 0;
        Node temp = head;
        int[] nodelist = new int[size()];
        while (temp != null) {
            nodelist[k] = temp.getValue();
            temp = temp.getNext();
            k++;
        }
        if (nodelist.length == 0){
            return 0;
        }
        double sum = IntStream.of(nodelist).sum();
        double listlength = nodelist.length;
        double mean = Math.floor(sum/listlength);

        return (int) mean;
    }


    /**
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     * list2: 1 -> 4 -> 2
     * return: true
     * <p>
     * list: 1 -> 5
     * list2: 2 -> 5
     * return false;
     *
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        int[] list2asarray = list2.toArray();
        int k = 0;
        int j = 0;
        Node temp = head;
        int[] nodelist = new int[size()];
        while (temp != null) {
            nodelist[k] = temp.getValue();
            temp = temp.getNext();
            k++;
        }
        if (list2asarray.length == 0 && nodelist.length != 0){
            return false;
        }
        if (list2asarray.length != 0 && nodelist.length == 0){
            return false;
        }
        while (j < nodelist.length) {
            if ((nodelist.length) > (list2asarray.length)) {
                return false;
            }
            if ((nodelist.length) < (list2asarray.length)) {
                return false;
            }
            if (nodelist[j] == list2asarray[j]) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove all the nodes at odd indices from the list. Remember that the first Node is at index 0
     * <p>
     * ex: list: 1 -> 3 -> 4 -> 2 -> 8
     * list after removeOdds: 1 -> 4 -> 8
     */
    public void removeOdds() {
        int k = 0;
        Node temp = head;
        while (temp != null) {
            if ((k % 2) != 0) {
                remove(temp.getValue());
            }
            temp = temp.getNext();
            k++;
        }
    }

    /**
     * Return true if the list is symmetrical, false otherwise
     * ex: list: 1 -> 2 -> 3 -> 2 -> 1
     * return: true
     * <p>
     * list: 1 -> 2 -> 3 -> 4 -> 5
     * return: false
     *
     * @return true if the list is symmetrical, false otherwise
     */

    public boolean isSymmetrical() {
        // fix for even case *
        int k = 0;
        int j = 0;
        int z = 0;
        int q = 0;
        int ф = 0;
        Node temp = head;
        int[] nodelist = new int[size()];
        while (temp != null) {
            nodelist[k] = temp.getValue();
            temp = temp.getNext();
            k++;
        }
        int listlength = nodelist.length;
        if (listlength == 0){
            return true;
        }
        if (listlength % 2 != 0) {
            int[] firsthalf = new int[(listlength / 2)];
            int[] secondhalf = new int[(listlength / 2)];

            int spotcheck = (int) ((listlength) - Math.ceil((double) (listlength) / 2));
            int pyramidpoint = spotcheck - 1;
            while (j < firsthalf.length) {
                firsthalf[j] = nodelist[j];
                j++;
            }
            while (z < listlength - 1) {
                if (z <= (pyramidpoint)) {
                    z++;
                }
                if (z > (pyramidpoint)) {
                    secondhalf[q] = nodelist[z + 1];
                    z++;
                    q++;
                }
            }
            int endoflist = firsthalf.length - 1;
            while (ф < firsthalf.length) {
                if (firsthalf[ф] == secondhalf[endoflist]) {
                    endoflist--;
                    ф++;
                } else {
                    System.out.println("FALSE");
                    return false;
                }
            }
        }
        if (listlength % 2 == 0){
            int[] firsthalf = new int[(listlength / 2)];
            int[] secondhalf = new int[(listlength / 2)];

            while (j < firsthalf.length) {
                firsthalf[j] = nodelist[j];
                j++;
            }
            while (z < listlength) {
                if (z <= (firsthalf.length-1)) {
                    z++;
                }
                if (z > (firsthalf.length-1)) {
                    secondhalf[q] = nodelist[z];
                    System.out.println(secondhalf[q]);
                    z++;
                    q++;
                }
            }
            int endoflist = firsthalf.length - 1;
            while (ф < firsthalf.length) {
                if (firsthalf[ф] == secondhalf[endoflist]) {
                    endoflist--;
                    ф++;
                } else {
                    System.out.println("FALSE");
                    return false;
                }
            }
        }
        System.out.println("TRUE");
        return true;
    }



    /**
     * Stretch the list so that each element in the list is represented factor times
     * If the factor is 0 the list should be cleared (have 0 nodes)
     * ex: list: 1 -> 2 -> 3
     *     factor: 3
     *     list after multiply: 1 -> 1 -> 1 -> 2 -> 2 -> 2 -> 3 -> 3 -> 3
     *
     * @param factor the amount to multiply the number of occurrences of each element by
     */
    public void multiply(int factor) {
        Node temp = head;
        if (factor == 0){
            clear();
        }
        else {
            while (temp != null) {
                int j = 0;
                Node next = temp.getNext();
                while (j < factor - 1) {
                    Node Newnode = new NodeImpl(temp.getValue(), temp.getNext());
                    temp.setNext(Newnode);
                    j++;
                }
                temp = next;
            }
        }
    }
    /**
     * Reverse the list
     *
     * ex list:  10 -> 9 -> 8 -> 7
     *    list after reverse: 7 -> 8 -> 9 -> 10
     *
     */
    public void reverse() {
        Node temp = head;
        Node last = null;
        while (temp != null) {
            Node next = temp.getNext();
            temp.setNext(last);
            last = temp;
            temp = next;
        }
        head = last;
    }

    /**
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
            Node current = head;
            if (head == null){
                System.out.println("empty list");
            }
            else {
                while (current.getNext() != null) {
                    Node compare = current.getNext();
                    if (current.getValue() == compare.getValue()) {
                        current.setNext(compare.getNext());
                    } else {
                        current = current.getNext();
                    }
                }
            }
        }


    /**
     * Return true if the list contains a cycle, false otherwise
     * ex: list: 1 -> 2 -> 3 - > 4 --       (4 points to 2)
     *                ^              |
     *                |              |
     *                ---------------
     *      return: true
     *
     *      list: 1 -> 2 -> 3 -> 4
     *      return: false
     *
     * @return true if the list contains a cycle, false otherwise
     */
    public boolean containsCycle() {
        if (head == null){
            return false;
        }
        Node fastnode = head.getNext();
        Node slownode = head;
        while (fastnode != null && fastnode.getNext() != null){
            if (fastnode == slownode){
                return true;
            }
            fastnode = fastnode.getNext().getNext();
            slownode = slownode.getNext();
        }
        return false;
    }

    /**
     * Merge the given linked list into the current list. The 2 lists will always be
     * either the same size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        Node current = head;
        Node current2 = list2.getHead();
        if (size() == list2.size()) {
            while (current != null) {
                Node temp = current.getNext();
                Node temp2 = current2.getNext();
                current.setNext(current2);
                current.getNext().setNext(temp);
                current = temp;
                current2 = temp2;
            }
        }
        if (size() > list2.size()) {
            while (current2 != null) {
                Node temp = current.getNext();
                Node temp2 = current2.getNext();
                current.setNext(current2);
                current.getNext().setNext(temp);
                current = temp;
                current2 = temp2;
            }
        }
    }


    /* Implementation given to you. Do not modify below this. */

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
    Returns true if the list contains a node whose value matches the element parameter, false otherwise
     */
    public boolean contains(int element) {
        Node current = head;
        while(current != null) {
            if(current.getValue() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /*
    converts the linked list into an array
     */
    public int[] toArray() {
        int[] arr =  new int[size()];
        Node current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    /*
    adds a node to the end of the list
     */
    public void add(int element) {
        Node newNode = new NodeImpl(element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }

    }

    /*
    removes the element from the list
     */
    public boolean remove(int element) {
        Node current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    /*
        returns the value at the index parameter.
     */
    public int get(int index) {
        validIndex(index);
        Node current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    /*
    sets the value of the node at index to the element
     */
    public int set(int index, int element) {
        validIndex(index);
        Node current = head;
        int prevValue = 1;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue(element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue(element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    /*
    adds a node at the given index with the given element as its value
     */
    public void add(int index, int element) {
        if(index > size) {
            validIndex(index);
        }
        Node current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node newNode = new NodeImpl(element, head);
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node temp = current.getNext();
                Node newNode = new NodeImpl(element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    /*
    returns the index of the given element
     */
    public int indexOf(int element) {
        Node current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue() == element) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    /*
    returns the last index of the element
     */
    public int lastIndexOf(int element) {
        Node current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue() == element) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public Node getHead() {
        return head;
    }

    /* prints out list */
    public String toString() {
        String list = "";
        Node current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }

}
