package graphcoloring;

import java.util.Random;

public class GeneticAlgo{
    boolean terminate=false;
    public Graph solve(Graph g, int k){
        int popSize = 30;
        int selectionSize = 10;
        //ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        ArrayList<Vertex>[] coloredVerts = new ArrayList<Vertex>[popSize];
        ArrayList<Vertex> setVerticies = g.getVertices();
        for(int i=0; i<popSize; i++){
            coloredVerts[i]=randomColoring(setVerticies,k+1);
        }

        while (!terminate){
            evaluate(ArrayList<Vertex>,k);
            selection();
            crossover();
            mutation();
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
    private int evaluate(ArrayList<Vertex> graph, int k){
        for (Vertex v:graph){
            
        };
    }
    private selection(ArrayList<Vertex> v){

    }
    private Vertex[] crossover(Vertex[] v){

    }
    private ArrayList<Vertex> mutation(ArrayList<Vertex> v){

    }
}