package graphcoloring;

import java.util.Random;
import java.util.ArrayList;

public class MinConflicts extends Solver{
    private int numConflicts;
    public Graph solve(Graph g, int k){
        ArrayList<Vertex> current = randomColoring(g,k);
        g.setColoredVerts(current);
        
        int loc;
        for (int i=0; i<2000; i++){
            //g.setColoredVerts(current);
            numConflicts=numConflicts(g);
            if (numConflicts==0){
                return g;
            }
            loc = selectConflict(g);
            g=changeVar(g, loc,k);
        }
        g.setColoredVerts(null);
        return g;
    }
    private Graph changeVar(Graph g, int loc, int k){
    	//System.out.println("this shit "+k);
        int[] arr = new int[k];
        ArrayList<Vertex> verticies = g.getVertices();
        Vertex v = verticies.get(loc);
        ArrayList<Vertex> connect=v.getConnections();
        for(Vertex vConnect:connect){
            arr[vConnect.getColor()]++;
        }
        int newColor=0; //find color with least conflicts
        for(int i=1; i<k; i++){
            if (arr[i]<arr[newColor])
                newColor=i;
        }
        verticies.get(loc).assignColor(newColor);
        g.setColoredVerts(verticies);
        return g;
    }
    private int selectConflict(Graph g){
        Random randGen = new Random();
        int rounds = 0;
        while(rounds<500){
            int i=0;
            rounds++;
            for(Vertex v: g.getVertices()){
                i++;
                for (Vertex v2:v.getConnections()){
                    if(v.getColor() == v2.getColor()){
                    	//System.out.println(i);
                        if(randGen.nextInt(numConflicts*2)==0){
                        	
                            return i-1;
                        }
                    } 
                }
            }
        }
        System.out.println("something is wrong");
        return -1;
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
    public ArrayList<Vertex> randomColoring(Graph g, int k){ //TODO change connections
    	ArrayList<Vertex> colors=g.getVertices();
        Random randGen = new Random();
         for(int i=0; i<colors.size(); i++){
             colors.get(i).assignColor(randGen.nextInt(k));
             
         }
         return colors;
    }