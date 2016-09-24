package graphcoloring;

import java.util.Random;
import java.util.ArrayList;

public class GeneticAlgo extends Solver{
    boolean terminate=false;
    int popSize = 10;
    int selectionSize = 5;
    int crossoverSize;
    int mutationRate = 1; //one in n mutated
    int[] scores = new int[popSize];
    int chromeSize;
    private Random randGen = new Random();
    public Graph solve(Graph g, int k){
        count=0;
        chromeSize=g.getVertices().size();
        crossoverSize=chromeSize/3;
        //ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        ArrayList<ArrayList<Vertex>> coloredVerts = new ArrayList<ArrayList<Vertex>>();
        ArrayList<Vertex> parentA = new ArrayList<Vertex>();
        ArrayList<Vertex> parentB = new ArrayList<Vertex>();
        for(int i=0; i<popSize; i++){
            coloredVerts.add(randomColoring(g,k));
        }
        
        //ArrayList<Vertex> chosenOne;
        
        parentA=coloredVerts.get(0);
        parentB=coloredVerts.get(1);
        for (int rounds=0; rounds<1000; rounds++){
        	//System.out.println(coloredVerts.get(0).get(0).getColor());
        	//System.out.println(coloredVerts.get(1).get(0).getColor());
        	//System.out.println(coloredVerts.get(3).get(0).getColor());
            for(int i=0; i<popSize; i++){
                g.setColoredVerts(coloredVerts.get(i));
                for(int j=0; j<chromeSize; j++){
                	g.updateConnectionColors(coloredVerts.get(i).get(j), j);
                }
                count++;
                scores[i]=numConflicts(g);
                //System.out.println(scores[i]); scores are same for each generation
                if(scores[i]==0){
                    return g;
                }
            }
            //System.out.println(scores[0]+" "+scores[1]);
            count++;
            if (!penalty(parentA, scores)){ //keep the same parents
            	parentA=coloredVerts.get(selection(selectionSize));
            	parentB=coloredVerts.get(selection(selectionSize));
            }
            count++;
            //ArrayList<Vertex> chosenOne;
            ArrayList<Vertex> chosenOne=new ArrayList<Vertex>(crossover(parentA,parentB));
            
            //coloredVerts.set(0,chosenOne);
            ArrayList<ArrayList<Vertex>> children = new ArrayList<ArrayList<Vertex>>();
            children.add(chosenOne);
            //System.out.println(children.get(0).get(2).getColor());
            //System.out.print(coloredVerts.get(2).get(2).getColor()+" ");
            for (int i=1; i<popSize; i++){
                count++;
                ArrayList<Vertex> mutant = new ArrayList<Vertex>(mutation(chosenOne,k));
                //mutant=mutation(chosenOne,k);
                children.add(mutant);
                System.out.println(children.get(1).get(2).getColor()+" "+i);
            }
            //System.out.println(children.get(0).get(2).getColor());
            System.out.println(children.get(1).get(2).getColor());
            coloredVerts=children;
            System.out.print("\n"+coloredVerts.get(2).get(2).getColor()+" ");
            System.out.println(" "+coloredVerts.get(3).get(2).getColor());
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
        Vertex temp;
        for(int i=0; i<chromeSize; i++){
            if(randGen.nextInt(mutationRate)==0){
            	//System.out.print(v.get(i).getColor()+" ");
            	temp=v.get(i);
            	temp.assignColor(randGen.nextInt(k));
                v.set(i, temp);
                //System.out.println(v.get(i).getColor());
            }
        }
        return v;
    }
}