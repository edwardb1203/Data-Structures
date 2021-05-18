package a5;

import java.util.ArrayList;

public class NodeImpl implements Node {
    private ArrayList<Edge> _edges = new ArrayList<>();
    private String _name;
    private double _distfromsrcs;

    public NodeImpl(String name) {
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public ArrayList<Edge> getedges() {
        return _edges;
    }

    @Override
    public void addedge(Edge newedge) {
        _edges.add(newedge);
    }

    @Override
    public ArrayList<String> getoutgoingedge() {
        ArrayList<String> outgoingedges = new ArrayList<String>();
        if (_edges.isEmpty()) {
            return outgoingedges;
        }

        for (int i = 0; i < _edges.size(); i++) {
            outgoingedges.add(_edges.get(i).getDest());
        }
        return outgoingedges;
    }

    @Override
    public double distfromsrc() {
        return _distfromsrcs;
    }

    public void adddist(double setd) {
        _distfromsrcs = setd;
    }

    @Override
    public int compareTo(Node ournode) {
        if (this.distfromsrc() < ournode.distfromsrc()) {
            return -1;
        }
        if (this.distfromsrc() > ournode.distfromsrc()) {
            return 1;
        }
        return 0;
    }
}
