package a5;

public class EdgeImpl implements Edge {
    private String _src;
    private String _dest;
    private double _weight;

    public EdgeImpl(String src, String dest, double weight){
        _src = src;
        _dest = dest;
        _weight = weight;
    }

    @Override
    public String getSrc() {
        return _src;
    }

    @Override
    public String getDest() {
        return _dest;
    }

    @Override
    public double getWeight() {
        return _weight;
    }
}
