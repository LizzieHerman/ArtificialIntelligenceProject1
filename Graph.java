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
        Vertex v2 = vertices.get(0);
        // TO-DO find nearest vertex to v1
        return v2;
    }
    
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }
    
    public ArrayList<Edge> getEdges(){
        return edges;
    }
}
