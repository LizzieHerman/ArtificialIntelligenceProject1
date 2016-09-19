/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
