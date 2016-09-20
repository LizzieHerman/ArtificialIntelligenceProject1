package graphcoloring;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

/**
 *
 * @author Lizzie Herman
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    
    public Graph(int n){
        //TO-DO make Graph
        vertices = new ArrayList();
        edges = new ArrayList();
        this.scatterVertices(n);
    }
    
    public void addEdge(Vertex a, Vertex b){
        Edge e = new Edge(a,b);
        edges.add(e);
        a.addEdge(b);
        b.addEdge(a);
    }
    
    public void scatterVertices(int n){
        Random randGen = new Random();
        for(int i = 0; i < n; i++){
            int x = randGen.nextInt(100);
            int y = randGen.nextInt(100);
            Point p = new Point(x,y);
            Vertex v = new Vertex(p);
            if(vertices.contains(v)) i--; // make sure the vertex you are adding does not already exist
            else vertices.add(v);
        }
    }
    
    public Vertex findNearestPoint(Vertex v1){
        Vertex v2 = null;
        double shortest = 200;
        for(Vertex v: vertices){
            if(v.equals(v1)) continue;
            if(v1.getConnections().contains(v)) continue;
            double current = v.getPoint().distance(v1.getPoint());
            if(current < shortest){
                shortest = current;
                v2 = v;
            }
        }
        return v2; // returns null if no next closest
    }
    
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }
    
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    
    public void giveVertPossColors(int n){
        for(Vertex v: vertices){
            ArrayList<Integer> intArray = new ArrayList();
            for(int i = 0; i < n; i++){
                intArray.add(i);
            }
            v.setPossColors(intArray);
        }
    }
}
