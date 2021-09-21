package sample;

public class GraphLink {
    public GraphNode<?> destNode; //Could also store source node if required
    public int cost; //Other link attributes could be similarly stored
    public String id;
    public String roadName;

    public GraphLink(GraphNode<?> destNode,int cost,String id, String roadName) {
        this.destNode=destNode;
        this.cost=cost;
        this.id=id;
        this.roadName=roadName;
    }

    public GraphNode<?> getDestNode() {
        return destNode;
    }

    public void setDestNode(GraphNode<?> destNode) {
        this.destNode = destNode;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    @Override
    public String toString() {
        return "GraphLink{" +
                "destNode=" + destNode +
                ", cost=" + cost +
                ", id='" + id + '\'' +
                ", roadName='" + roadName + '\'' +
                '}';
    }
}
