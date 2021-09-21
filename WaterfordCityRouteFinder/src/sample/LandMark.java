package sample;

public class LandMark {
    public String landMarkId;
    public String landMarkName;
    public String xCoord;
    public String yCoord;
    public GraphNode graphNode = new GraphNode();

    public LandMark(String landMarkId, String landMarkName, String xCoord, String yCoord) {
        this.landMarkId = landMarkId;
        this.landMarkName = landMarkName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.graphNode = graphNode;
    }

    public LandMark() {

    }


    public String getLandMarkId() {
        return landMarkId;
    }

    public void setLandMarkId(String landMarkId) {
        this.landMarkId = landMarkId;
    }

    public String getLandMarkName() {
        return landMarkName;
    }

    public void setLandMarkName(String landMarkName) {
        this.landMarkName = landMarkName;
    }

    public String getxCoord() {
        return xCoord;
    }

    public void setxCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public String getyCoord() {
        return yCoord;
    }

    public void setyCoord(String yCoord) {
        this.yCoord = yCoord;
    }

}


