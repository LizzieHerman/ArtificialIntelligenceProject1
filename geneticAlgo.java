package graphcoloring;

import java.util.Random;

public class GeneticAlgo{
    boolean terminate=false;
    int popSize = 60;
    int selectionSize = 10;
    int crossoverSize;
    int mutationRate = 4 //one in n mutated
    int[] scores = new int[popSize];
    int chromeSize;
    public Graph solve(Graph g, int k){
        chromeSize=g.getVertices.get(0).size();
        crossoverSize=chromeSize/2;
        //ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        ArrayList<Vertex>[] coloredVerts = new ArrayList<Vertex>[popSize];
        ArrayList<Vertex> setVerticies = g.getVertices();
        ArrayList<Vertex>[] parents = new ArrayList<Vertex>[2];
        for(int i=0; i<popSize; i++){
            coloredVerts[i]=randomColoring(setVerticies,k+1);
        }
        ArrayList<Vertex> chosenOne;
        for (int rounds=0; rounds<1000; rounds++){
            for(int i=0; i<popSize; i++){
                scores[i]=evaluate(g.setColoredVerts(coloredVerts[i]));
                if(scores[i]==0){
                    return g;
                }
            }
            parents[0]=selection();
            parents[1]=selection();
            chosenOne=crossover(parents);
            for (int i=0; i<popSize; i++){
                coloredVerts[i]=mutation(chosenOne);
            }
        }

        g.setColoredVerts(coloredVerts);

        return g;
    }
    public ArrayList<Vertex> randomColoring(ArrayList<Vertex> colors, int k){
        Random randGen = new Random();
         for(int i=0; i<colors.size(); i++){
             colors.get(i).assignColor(randGen.nextInt(k));
         }
         return colors;
    }
    @override
    private int evaluate(Graph g){
        int i=0;
        for(Edge e: g.getEdges()){
            int a = e.getFirstVert().getColor();
            int b = e.getSecVert().getColor();
            if(a == b || a == -1 || b == -1)
                i++;; // sees if any edge has the same color
        }
        return i;
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
    private ArrayList<Vertex> crossover(ArrayList<Vertex>[] parents){
        Random randGen = new Random();
        int start=randGen.nextInt(parents[0].size());
        Vertex temp= new Vertex();
        for(int i=0; i<crossoverSize;i++){
            temp=parents[0].get((i+start)%parents[0].size());
            parents[0].get((i+start)%parents[0].size())=parents[1].get((i+start)%parents[0].size());
            parents[1].get((i+start)%parents[0].size())=temp;
        }
        return parents[0];
    }
    private ArrayList<Vertex> mutation(ArrayList<Vertex> v){
        Random randGen = new Random();
        for(int i=0; i<chromeSize; i++){
            if(randGen.nextInt(mutationRate+1)==0){
                v.get(i).assignColor(randGen.nextInt(k));
            }
        }
        return v;
    }
}