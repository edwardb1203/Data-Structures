package a5;

import java.util.ArrayList;

public interface Node extends Comparable<Node> {

     /**
      * @return the name of the node
      */
     String getName();
     ArrayList<Edge> getedges();
     void addedge(Edge newedge);
     ArrayList<String> getoutgoingedge();
     double distfromsrc();
     void adddist(double setd);
     int compareTo(Node ournode);

}
