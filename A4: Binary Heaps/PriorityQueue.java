package a4;


public class PriorityQueue implements  Queue{

    private Prioritized[] heap;
    private int size;
    private static final int arraySize = 10000; // Everything in the array will initially
    // be null. This is ok! Just build out
    // from array[1]. Don't change the value of this variable.

    public PriorityQueue() {
        heap = new Prioritized[arraySize];
        size = 0;
        heap[0] = new Prioritized(Double.NaN, Double.NaN); //0th will be unused for simplicity
                                                            //of child/parent computations
    }

    // fill in the methods below based on the descriptions in the Queue interface. Do NOT change the interface or any
    // of the method signatures

    private int parent (int sizepl) {
        return (sizepl/2);
    }


    @Override
    public void enqueue(double value, double priority) {
        size++;
        heap[size] = new Prioritized(value, priority);
        int current = size;
        while (heap[current].getPriority() > heap[parent(current)].getPriority()) {
            bubbleup(current, parent(current));
            current = parent(current);
        }
    }

    private void bubbleup (int index1, int index2){
            Prioritized temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
    }

    @Override
    public double dequeue() {
        if (size == 1){
            size--;
            return heap[1].getValue();
        }
        if (size == 0){
            return Double.NaN;
        }
        Prioritized dequeued = heap[1];
        Prioritized last = heap[size--];
        heap[1] = last;
        int current = 1;
        heapifyroot(current);
        return dequeued.getValue();
    }

    private void bubbledown (int parenti, int childi){
        Prioritized temp = heap[parenti];
        heap[parenti] = heap[childi];
        heap[childi] = temp;
    }
    private boolean leafdetect (int current){
        if ((current > size / 2) && current <= size){
            return true;
        }
        return false;
    }
    private void heapifyroot (int current) {
        if (leafdetect(current)){
            return;
        }
        if (((heap[current].getPriority() < heap[2*current].getPriority())) || (heap[current].getPriority() < heap[(2*current)+1].getPriority())) {

            if (heap[2*current].getPriority() > heap[(2*current)+1].getPriority()) {
                bubbledown(current, 2*current);
                current = (2 * current);
                heapifyroot(current);
            } else {
                bubbledown(current,(2*current)+1);
                current = ((2 * current)+1);
                heapifyroot(current);
            }
        }
    }

    @Override
    public double front() {
        if (size >= 1){
        return heap[1].getValue();
        }
        return Double.NaN;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public double[] sort() {
        int heapsize = size;
        double [] sortedheap = new double[size];
        int i = 0;
        int size2 = size-1;
        while (i < size){
            sortedheap[size2] = dequeue();
            size2--;
        }
        return sortedheap;
    }

    @Override
    public void build(Prioritized[] elements) {
        int elsiz = elements.length;
        int i = 1;
        while (i < elsiz+1){
            itemsinheap(i, elements);
            size++;
            i++;
        }
        int startingindex = (elsiz/2);
        for (int j = startingindex; j > 0; j--) {
            heapify(j);
        }
    }
private void heapify(int index) {
    int root = index;
    int leftchild = 2 * (root);
    int rightchild = (2 * (root)) + 1;

    if (heap[leftchild] != null && heap[leftchild].getPriority() > heap[root].getPriority() || heap[rightchild] != null && heap[rightchild].getPriority() > heap[root].getPriority()) {
        if (heap[rightchild] == null || heap[leftchild].getPriority() > heap[rightchild].getPriority()) {
            Prioritized temp = heap[root];
            heap[root] = heap[leftchild];
            heap[leftchild] = temp;
            heapify(leftchild);
        } else {
            Prioritized temp = heap[root];
            heap[root] = heap[rightchild];
            heap[rightchild] = temp;
            heapify(rightchild);
        }
       // return;
    }
}

private void itemsinheap(int index, Prioritized[] elts){
        heap[index] = elts[index-1];
}

        //build using enqueue below
    //        int i = 0;
//        while (i<elements.length){
//            double value = elements[i].getValue();
//            double priority = elements[i].getPriority();
//            enqueue(value, priority);
//            i++;


    // do not change
    public Prioritized[] getHeap() {
        return heap;
    }
}
