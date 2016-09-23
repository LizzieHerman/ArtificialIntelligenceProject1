package graphcoloring;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Lizzie Herman
 */
public class Vertex {
    private ArrayList<Vertex> connections;
    private Point p;
    private int color;
    private ArrayList<Integer> possibleColors;
    
    public Vertex(Point p1){
        p = p1;
        color = -1; // color has not been assigned
        connections = new ArrayList();
        possibleColors = new ArrayList();
    }
    
    public void addEdge(Vertex v){
        connections.add(v);
    }
    
    public int assignColor(int a){
        int prevColor = color;
        color = a;
        return prevColor; // return what the previous color was
    }
    
    public Point getPoint(){
        return p;
    }
    
    public ArrayList<Vertex> getConnections(){
        return connections;
    }

    public void setConnections(ArrayList<Vertex> connect){
        connections=connect;
    }
    
    public int getColor(){
        return color;
    }
    
    public ArrayList<Integer> getPossColors(){
        return possibleColors;
    }
    
    public boolean removePossColor(Integer a){
        if( possibleColors.contains(a)){
            possibleColors.remove(a);
            return true;
        }
        return false; // return whether the color was removed
    }
    
    public void setPossColors(ArrayList<Integer> col){
        possibleColors = col;
    }
    
    public boolean addPossColor(Integer a){
        if( possibleColors.contains(a)){
            return false;   // return whether color was added
        }
        possibleColors.add(a);
        return true;
    }
    
    // override equals method so that it just checks whether or not vertexes are int the same position
    public boolean equals(Vertex v){
        return p.equals(v.getPoint());
    }
}
