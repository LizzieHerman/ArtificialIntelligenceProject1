package graphcoloring;

import java.util.Random;
import java.util.ArrayList;

public class GeneticAlgo extends Solver{
    boolean terminate=false;
    int popSize = 100;
    int selectionSize = 20;
    int crossoverSize;
    int mutationRate = 8; //one in n mutated
    int[] scores = new int[popSize];
    int chromeSize;
    public Graph solve(Graph g, int k){
        count=0;
        chromeSize=g.getVertices().size();
        crossoverSize=chromeSize/3;
        //ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        ArrayList<ArrayList<Vertex>> coloredVerts = new ArrayList<ArrayList<Vertex>>();
        ArrayList<Vertex> setVerticies = g.getVertices();
        ArrayList<Vertex> parentA = new ArrayList<Vertex>();
        ArrayList<Vertex> parentB = new ArrayList<Vertex>();
        for(int i=0; i<popSize; i++){
            coloredVerts.add(randomColoring(g,k));
        }
        
        ArrayList<Vertex> chosenOne;
        parentA=coloredVerts.get(0);
        parentB=coloredVerts.get(1);
        for (int rounds=0; rounds<1000; rounds++){
            for(int i=0; i<popSize; i++){
                g.setColoredVerts(coloredVerts.get(i));
                for(int j=0; j<chromeSize; j++){
                	g.updateConnectionColors(coloredVerts.get(i).get(j), j);
                }
                count++;
                scores[i]=numConflicts(g);
                if(scores[i]==0){
                    return g;
                }
            }
            count++;
            if (!penalty(parentA, scores)){ //keep the same parents
            	parentA=coloredVerts.get(selection(selectionSize));
            	parentB=coloredVerts.get(selection(selectionSize));
            }
            count++;
            chosenOne=crossover(parentA,parentB);
            coloredVerts.set(0,chosenOne);
            
            for (int i=1; i<popSize; i++){
                count++;
                coloredVerts.set(i,mutation(chosenOne,k));
            }
        }
        g.setColoredVerts(null);
        return g;
    }
    private boolean penalty(ArrayList<Vertex> parent, int[] scores){
    	Graph parentG = new Graph(chromeSize);
    	parentG.setColoredVerts(parent);
    	int pScore=numConflicts(parentG);
    	for(int i:scores){
    		if(pScore>i){
    			return false;
    		}
    	}
    	return true;
    }
    public ArrayList<Vertex> randomColoring(Graph g, int k){ 
    	ArrayList<Vertex> colors=g.getVertices();
        Random randGen = new Random();
        Vertex temp;
         for(int i=0; i<colors.size(); i++){
             temp=colors.get(i);
         	temp.assignColor(randGen.nextInt(k));
             colors.set(i, temp);
         }
         return colors;
    }
    
    public int numConflicts(Graph g){
        int i=0;
        for(Vertex v: g.getVertices()){
            for (Vertex v2:v.getConnections()){ 
                if(v.getColor() == v2.getColor())
                    i++; // sees if any edge has the same color
            }
        }
        return i/2;
    }
    private int selection(int n){
        Random randGen = new Random();
        int start=randGen.nextInt(popSize);
        int best = scores[start];
        int loc = start;
        for(int i=0; i<n;i++){
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
        Vertex temp;
        for(int i=0; i<chromeSize; i++){
            if(randGen.nextInt(mutationRate)==0){
            	temp=v.get(i);
            	temp.assignColor(randGen.nextInt(k));
                v.set(i, temp);
            }
        }
        return v;
    }
}