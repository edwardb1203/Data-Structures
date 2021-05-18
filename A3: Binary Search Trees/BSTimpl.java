package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(String s) {
        root = new NodeImpl(s);
        size = 1;
    }
    public void show() {
        int off=4;
        int lev=0;
        for (int k=0; k<10; k++){
            System.out.println("+");
            for (int kk=0; kk<off-1; kk++) { System.out.println("-"); }
        }
        System.out.println("+");
        show_r(this.root,lev,off);
        for (int k=0;k<10;k++){
            System.out.println("+");
            for (int kk=0; kk<off-1; kk++) { System.out.println("-"); }
        }
        System.out.println("+");
    }
    private void show_r(Node n, int lev, int off){
        if(n==null) return;
        show_r(n.getRight(), lev+off, off);
        for (int b=0;b<lev;b++) { System.out.println(" ");}
        System.out.println(n.getValue());
        show_r(n.getLeft(), lev+off, off);
    }


    // The implementation of "height" is given to you below
    // it is here to illustrate for you how to set up
    // the method implementation as recursion.
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive methods

    @Override
    public int height() { // public interface method signature
        // this method is the public interface method
        // it is not recursive, but it calls a recursive
        // private method and passes it access to the tree cells
        return height_recursive(this.root);
    }
    private int height_recursive(Node c) {
        // inner method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    public Node createNode(String value){
        Node ournewnode = new NodeImpl(null,null,value);
        return ournewnode;
    }
    @Override
    public String insert(String value) {
        // set up call to recursive method
        insert_r(value,this.root);
        size++;
        return null;
    }
    private Node insert_r(String value, Node c) {
        // write recursive method here
        if (this.root == null){
            this.root = createNode(value);
        }
        if (c == null){
            return createNode(value);
        }

        int cflag = value.compareTo(c.getValue());

        if (cflag < 0) {
            c.setLeft(insert_r(value, c.getLeft()));
        } else if (cflag > 0) {
            c.setRight(insert_r(value, c.getRight()));
        }
        return c;
    }

    // remove implementation given to you, do NOT change
    @Override
    public void remove(String value) {
        remove_r(value,this.root);
        size--;
    }
    private Node remove_r(String k, Node c) {
        if (c==null) return null; // item not found, nothing to do

        // now we know we have a real node to examine
        int cflag = k.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(k,c.getLeft()));
            return c;
        }
        else
        if (cflag>0) { // k is larger than node
            c.setRight(remove_r(k,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight()==null) return c;
        return maxCell(c.getRight());
    } ;

    @Override
    public boolean isFull() {
       return isFull_r(this.root);
    }
    private boolean isFull_r(Node node) {
        if (this.root == null) {//if tree has an empty root node, then tree is full
            return true;
        }
        if (this.root.getRight() == null && this.root.getLeft() == null) { // tree with only root, no children
            return true;
        }
        if (this.root.getRight() != null && this.root.getLeft() != null) { // tree with root and two children tells us to proceed
            if (node.getRight() != null && node.getLeft() != null) {
                return isFull_r(node.getRight()) && isFull_r(node.getLeft());
            }
            if (node.getRight() == null && node.getLeft() == null) {
               return true;
            }
        }
        return false;
    }

    @Override
    public String findMin() {
        String themin = findMin_r(this.root);
        return themin;
    }
    private String findMin_r(Node node) {
        if (this.root==null) {
            return null;
        }
        if (node.getLeft()==null){
            return node.getValue();

        }
        return findMin_r(node.getLeft());
    }

    @Override
    public String findMax() {
        String themax = findMax_r(this.root);
        return themax;
    }
    private String findMax_r(Node node) {
        if (this.root==null) {
            return null;
        }
        if (node.getRight()==null){
            return node.getValue();

        }
        return findMax_r(node.getRight());
    }

    @Override
    public boolean contains(String s) {
      return contains_r(s, this.root);
    }
    private boolean contains_r(String s, Node node) {
        if (node == null) {
            return false;
        }
        if (node.getValue() == s){
            return true;
        }
        int nodeflag = s.compareTo(node.getValue());

        if (nodeflag<0) {
           return contains_r(s, node.getLeft());
        }
        else{ return contains_r(s, node.getRight()); }
    }

    @Override
    public Node get(String s) {
        return get_r(s, this.root);
    }
    private Node get_r(String s, Node node){
        if (node == null) {
            return null;
        }
        if (node.getValue() == s){
            return node;
        }
        int nodeflag = s.compareTo(node.getValue());

        if (nodeflag<0) {
            return get_r(s, node.getLeft());
        }
        else{ return get_r(s, node.getRight()); }
    }

    @Override
    public int size() {
        return this.size;
    }

}
