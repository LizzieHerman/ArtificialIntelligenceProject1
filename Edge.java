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
        a = v1; //leftmost
        b = v2; //rightmost
    }

    public Vertex getFirstVert(){
        return a;
    }
    
    public Vertex getSecVert(){
        return b;
    }
    
    public boolean detectCollision(ArrayList<Edge> edges){
        for (Edge edge: edges){
            if (a<=edge.getFirstVert&&b>=edge.getSecVert //in the intersection range
                &&twoLinesCollision(edge))
                return true;
        }
        return false; //no collision
    }
    private boolean twoLinesCollision(Edge e){
        
    }
}
