package graphcoloring;

import java.util.Random;
import java.util.ArrayList;

public class GeneticAlgo{
    boolean terminate=false;
    int popSize = 60;
    int selectionSize = 10;
    int crossoverSize;
    int mutationRate = 4; //one in n mutated
    int[] scores = new int[popSize];
    int chromeSize;
    public Graph solve(Graph g, int k){
        chromeSize=g.getVertices().size();
        crossoverSize=chromeSize/2;
        //ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        ArrayList<ArrayList<Vertex>> coloredVerts = new ArrayList<ArrayList<Vertex>>();
        ArrayList<Vertex> setVerticies = g.getVertices();
        ArrayList<Vertex> parentA = new ArrayList<Vertex>();
        ArrayList<Vertex> parentB = new ArrayList<Vertex>();
        for(int i=0; i<popSize; i++){
            coloredVerts.add(randomColoring(setVerticies,k));
        }
        ArrayList<Vertex> chosenOne;
        for (int rounds=0; rounds<1000; rounds++){
            for(int i=0; i<popSize; i++){
                g.setColoredVerts(coloredVerts.get(i));
                scores[i]=numConflicts(g);
                if(scores[i]==0){
                    return g;
                }
            }
            parentA=coloredVerts.get(selection());
            parentB=coloredVerts.get(selection());
            chosenOne=crossover(parentA,parentB);
            for (int i=0; i<popSize; i++){
                coloredVerts.set(i,mutation(chosenOne,k));
            }
        }
        return g;
    }
    public ArrayList<Vertex> randomColoring(ArrayList<Vertex> colors, int k){ //TODO change connections
        Random randGen = new Random();
         for(int i=0; i<colors.size(); i++){
             colors.get(i).assignColor(randGen.nextInt(k));
         }
         return colors;
    }
    
    public int numConflicts(Graph g){
        int i=0;
        for(Vertex v: g.getVertices()){
            for (Vertex v2:v.getConnections()){ //TODO
                if(v.getColor() == v2.getColor())
                    i++; // sees if any edge has the same color
            }
        }
        return i/2;
    }
    private int selection(){
        Random randGen = new Random();
        int start=randGen.nextInt(popSize);
        int best = scores[start];
        int loc = start;
        for(int i=0; i<selectionSize;i++){
            if(scores[(i+start)%popSize]<best){
                best=scores[(i+start)%popSize];
                loc=(i+start)%popSize;
            }
        }
        return loc;
    }
    private ArrayList<Vertex> crossover(ArrayList<Vertex> parentA,ArrayList<Vertex> parentB){
        Random randGen = new Random();
        int start=randGen.nextInt(chromeSize);
        Vertex temp;
        for(int i=0; i<crossoverSize;i++){
            temp=parentA.get((i+start)%chromeSize);
            parentA.set((i+start)%chromeSize,parentB.get((i+start)%chromeSize));
            parentB.set((i+start)%chromeSize,temp);
        }
        return parentA;
    }
    private ArrayList<Vertex> mutation(ArrayList<Vertex> v, int k){
        Random randGen = new Random();
        for(int i=0; i<chromeSize; i++){
            if(randGen.nextInt(mutationRate+1)==0){
                v.get(i).assignColor(randGen.nextInt(k));
            }
        }
        return v;
    }
}