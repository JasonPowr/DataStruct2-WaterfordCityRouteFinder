package sample;

public class Road {

    public LandMark destNode;
    public String roadId;
    public String roadName;
    public int dist;

    public Road(LandMark destNode, int dist, String roadId, String roadName) {
        this.destNode = destNode;
        this.dist = dist;
        this.roadId = roadId;
        this.roadName = roadName;
    }


    public void setDist(int dist) {this.dist = dist;}

    public Road(int dist) {setDist(dist);}

    public Road(String id, String name) {
        this.roadId = id;
        this.roadName = name;
    }

    public String getRoadId() {return roadId;}

    public String nameAndDist() {return destNode.landMarkName + ", + cost";}

}