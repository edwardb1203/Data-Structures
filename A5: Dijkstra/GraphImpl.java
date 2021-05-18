package a5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //do not delete, use this field to store your nodes
                             // key: name of node. value: a5.Node object associated with name
    private int totaledges;

    public GraphImpl() {
        nodes = new HashMap<>();
        totaledges = 0;
    }

    @Override
    public boolean addNode(String name) {
        // DONE
        //first check if the name is unique
        if(nodes.containsKey(name) || name == null){
            return false;
        }
        //if it is unique, then we can create a node with it and add it to our map
        Node newnode = new NodeImpl(name);
        nodes.put(name, newnode);
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        //DONE
        //first access our node in the map by its source name
        Node ournode = nodes.get(src);
        //next check if weight is less then 0
        if (weight < 0){
            return false;
        }
        // create list of all outgoing edges, then check if the destination is already there (this means an edge exists)
        ArrayList<String> isedgethere = ournode.getoutgoingedge();
        if(isedgethere.contains(dest)){
            return false;
        }
        //finally, if the map contains node and dest, then we can add an edge
        if (nodes.containsKey(src) && nodes.containsKey(dest)){
            Edge newedge = new EdgeImpl(src, dest, weight);
            ournode.addedge(newedge);
            totaledges++;
            return true;
        }
        //otherwise it didn't contain node or dest so we return false
        return false;
    }

    @Override
    public boolean deleteNode(String name) {
        //DONE
        if(nodes.containsKey(name)){
            ArrayList neighbors = nodes.get(name).getedges();
            // first we need to remove all edges that pointed to the node
            for(int i=0;i<neighbors.size();i++){
               String killit = nodes.get(name).getedges().get(i).getDest();
               Node nodetokill = nodes.get(killit);
              ArrayList node2neighbors = nodetokill.getedges();
              for(int j=0;j<node2neighbors.size();j++){
                  if(nodetokill.getedges().get(i).getDest() == name){
                      nodetokill.getedges().remove(i);
                  }
              }
            }
            // then remove target node and return true
            nodes.remove(name);
            return true;
        }
        // else it wasn't there and return false
        return false;
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        //DONE
        //see if the source node exists
            if(nodes.containsKey(src)){
                // if the source node exists, lets check to see if it has an edge
                // UPDATE: only need to check the sources edge list, and then just remove from there
                Node ournode = nodes.get(src);
               ArrayList ouredges = ournode.getedges();
               //iterate through to see if the edge is there
               for(int i=0;i<ouredges.size();i++){
                   if(ournode.getedges().get(i).getDest() == dest){
                       ournode.getedges().remove(i);
                       totaledges--;
                       // edge was removed, return true
                       return true;
                   }
               }
            }
        // edge was not removed return false
        return false;
    }

    @Override
    public int numNodes() {
        //DONE
        return nodes.size();
    }

    @Override
    public int numEdges() {
        //DONE
        return totaledges;
//        int total = 0;
//        for (Map.Entry elt : nodes.entrySet()) {
//            String key = (String) elt.getKey();
//
//           ArrayList edges = nodes.get(key).getedges();
//           int toadd = edges.size();
//           total = total + toadd;
//        }
//        return total;
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        //DONE
        PriorityQueue<Node> processing = new PriorityQueue<>();
        ArrayList<Node> visited = new ArrayList<>();
        Map<String, Double> shortestnodes = new HashMap<>();
        Node srcsnode = nodes.get(start);
        // fill the node distances as infinity
        for(Node n: nodes.values()){
            n.adddist(Double.POSITIVE_INFINITY);
        }
        // add in the first node to the queue, set its distance to 0
        srcsnode.adddist(0);
        processing.add(srcsnode);
        // while all nodes not visited
        while (processing.size() > 0) {
            // look at the top of pq
            Node firstvalue = processing.peek();
            double firstd = firstvalue.distfromsrc();
            //remove first item
            processing.poll();
            //now check adj nodes
            if(! visited.contains(firstvalue) ){
                visited.add(firstvalue);
                for(Edge e: firstvalue.getedges()){
                    Node ourd = nodes.get(e.getDest());
                    if(firstd+e.getWeight() < ourd.distfromsrc()){
                        ourd.adddist(firstd+e.getWeight());
                        processing.add(ourd);
                    }
                }
                shortestnodes.put(firstvalue.getName(),firstd);
            }
        }
        return shortestnodes;
    }



    public java.util.Set<String> getallnodes() {
        return nodes.keySet();
    }

    public Node getnode(String key) {
        return nodes.get(key);
    }
}
