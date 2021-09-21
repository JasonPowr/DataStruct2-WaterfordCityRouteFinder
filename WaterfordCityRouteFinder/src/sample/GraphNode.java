package sample;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T data;
    public List<GraphLink> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
    //Could use any concrete List implementation
    public GraphNode(T data) {
        this.data=data;
    }

    public GraphNode() {

    }


    public void connectToNodeDirected(GraphNode<T> destNode,int cost,String id, String roadName) {
        adjList.add(new GraphLink(destNode,cost, id,roadName));//Add new link object to source adjacency list
    }

    public void connectToNodeUndirected(GraphNode<T> destNode,int cost,String id, String roadName) {
        adjList.add( new GraphLink(destNode,cost, id,roadName)); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLink(this, cost, id,roadName)) ; //Add new link object to destination adjacency list
    }

    public static void traverseGraphDepthFirst(GraphNode<LandMark> from, List<GraphNode<LandMark>> encountered){
        System.out.println(from.data);
        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);
        for(GraphLink adjLink : from.adjList)
            if(!encountered.contains(adjLink.destNode)) traverseGraphDepthFirst((GraphNode<LandMark>) adjLink.destNode,encountered);
    }


    @Override
    public String toString() {
        return "GraphNode{" +
                "data=" + data +
                ", adjList=" + adjList +
                '}';
    }
}
