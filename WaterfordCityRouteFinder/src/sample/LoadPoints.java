package sample;

import java.io.*;
import java.util.ArrayList;

public class LoadPoints {
    private File waterFordCity = new File("src/waterfordCity.txt");  //creating an instance of the text file with info about waterford city
    ArrayList<LandMark> landMarks = new ArrayList<LandMark>();  //creating an arraylist of landmarks
    ArrayList<GraphNode<LandMark>> graphNodes = new ArrayList<>();

    public void loadPoints() throws IOException {  //method to load points off of the txt file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(waterFordCity)); //to read the file

        String line;    //creating a string line
        while ((line = bufferedReader.readLine()) != null) {  //while the next line is not null
            String[] word = line.split(",");    //creates a string called word and lets it know everytime thers a , theres a new word

            if (word[0].equalsIgnoreCase("Landmark")) {     //if the first word is Landmark
                String landMarkId = word[1];
                String landMarkName = word[2];

                String xCoord = word[3];
                String yCoord = word[4];
                LandMark newLandMark = new LandMark(landMarkId, landMarkName, xCoord, yCoord);  //creates a new landmark
                landMarks.add(newLandMark); //adds it to an arraylist of landmarks
                graphNodes.add(new GraphNode<>(newLandMark));
            }

            else if(word[0].contains("Road")) {
                String roadId = word[1];
                String source = word[2];
                String destination = word[3];
                String roadName = word[5];
                int cost = Integer.parseInt(word[4]);

                GraphNode<LandMark> sourceNode = null;
                GraphNode<LandMark> destNode = null;

                for(int i  = 0 ; i < graphNodes.size(); i++){
                    if(graphNodes.get(i).data.landMarkName.equalsIgnoreCase(source)){
                        sourceNode = graphNodes.get(i);

                    }if(graphNodes.get(i).data.landMarkName.equalsIgnoreCase(destination)){
                        destNode = graphNodes.get(i);

                    }
                }
                if((sourceNode != null) && (destNode != null)){
                    sourceNode.connectToNodeUndirected(destNode,cost,roadId,roadName);
                }
            }
        }
    }

}


