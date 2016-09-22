/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;
import java.awt.geom.Line2D;
import java.util.ArrayList;
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
            if (a.getPoint().x<=edge.getFirstVert().getPoint().x&&b.getPoint().x>=edge.getSecVert().getPoint().x //in the intersection range
                &&twoLinesCollision(edge))
                return true;
        }
        return false; //no collision
    }
    private boolean twoLinesCollision(Edge e){
        Line2D l1= new Line2D.Double(a.getPoint().x,a.getPoint().y,b.getPoint().x,b.getPoint().y);
        Line2D l2= new Line2D.Double(e.getFirstVert().getPoint().x,e.getFirstVert().getPoint().y,
            e.getSecVert().getPoint().x,e.getSecVert().getPoint().y);
        return l1.intersectsLine(l2);
    }
}
