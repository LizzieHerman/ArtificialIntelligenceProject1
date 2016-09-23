package graphcoloring;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

/**
 *
 * @author Lizzie Herman and Connor O'Leary
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    
    public Graph(int n){
        vertices = new ArrayList();
        edges = new ArrayList();
        this.scatterVertices(n);
        for(Vertex v: vertices){ // find the connections for every vertex in graph
            ArrayList<Vertex> collisions = new ArrayList(); // keep track of what vertices can't be connected to
            Vertex connect = findNearestPoint(v,collisions); // find the nearest point
            while(connect != null){ // keep finding nearest point until none left
                boolean edgeMade = addEdge(v, connect);
                if(!edgeMade){
                    collisions.add(connect);
                }
                connect = findNearestPoint(v,collisions);
            }
        }
    }
    
    public boolean addEdge(Vertex a, Vertex b){
        Edge e;
        if(a.getPoint().x > b.getPoint().x){ //first coord needs to be leftmost
            e = new Edge(b,a);
        }
        else{
            e = new Edge(a,b);
        }
        if(e.detectCollision(edges)){
            return false; // this edge could not be made
        }
        edges.add(e);
        a.addEdge(b);
        b.addEdge(a);
        return true; // this edge was made
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
    
    public Vertex findNearestPoint(Vertex v1, ArrayList<Vertex> collisions){
        Vertex v2 = null;
        double shortest = 200;
        for(Vertex v: vertices){
            if(v.equals(v1)) continue; // don't return the same vertex
            if(v1.getConnections().contains(v)) continue; // son't return a point that has already been returned
            if(collisions.contains(v)) continue; // don't return a point that can't be connected to
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
    
    public void setColoredVerts(ArrayList<Vertex> coloredVerts){
        vertices = coloredVerts;
    }
    public void updateConnectionColors(Vertex vert, int i){
    	ArrayList<Vertex> connections = vert.getConnections();
    	int j=0;
        for(Vertex v: connections){
            for(Vertex v2: vertices){
            	if(v.equals(v2)){
            		connections.set(j, v2);
            		vert.setConnections(connections);
            		vertices.set(i,vert);
            	}
            }
            j++;
        }
    }
}
