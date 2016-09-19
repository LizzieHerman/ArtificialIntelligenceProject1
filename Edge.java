/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

/**
 *
 * @author Lizzie Herman
 */
public class Edge {
    private Vertex a;
    private Vertex b;
    
    Edge(Vertex v1, Vertex v2){
        a = v1;
        b = v2;
    }

    public Vertex getFirstVert(){
        return a;
    }
    
    public Vertex getSecVert(){
        return b;
    }
    
    public boolean detectCollision( Edge e){
        boolean collide = true;
        // TO-DO detect to see if two edges collide
        return collide;
    }
}
