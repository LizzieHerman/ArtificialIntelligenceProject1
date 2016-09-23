/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.util.*;

/**
*
 * @author Lizzi
 */
public class BackTrackingS extends Solver{

    BackTrackingS() {
        
    }
    public Graph solve(Graph g, int k){
        ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        g.setColoredVerts(coloredVerts);
        return g;
    }
    
    // returns either a colored list or null if failure
    public ArrayList<Vertex> backTrack(ArrayList<Vertex> recVerts, int num){
        ArrayList<Vertex> retVerts;
        Collections.sort(recVerts, connections);
        Vertex curVert = recVerts.get(0);
        ArrayList<Integer> intArray = new ArrayList();
            for(int i = 0; i < num; i++){
                intArray.add(i);
            }
            curVert.setPossColors(intArray);
        do{
            // check if assignment impossible
            for(Vertex v: curVert.getConnections()){ // check to see whether solution has made assignment impossible
                if(curVert.getPossColors().contains(v.getColor())){
                    curVert.removePossColor(v.getColor());
                }
            }
            if(curVert.getPossColors().isEmpty()) return null;
            Integer color = curVert.getPossColors().get(0);
            curVert.assignColor(color);
            recVerts.remove(curVert);
            if(recVerts.isEmpty()){ // if there are no more vertices in the list return the current Vertex
                retVerts = new ArrayList();
                retVerts.add(curVert);
                return retVerts;
            }
            retVerts = backTrack(recVerts, num); // returns a completely colored list
            if(retVerts == null){
                curVert.removePossColor(color); // the color assigned does not work
            }
        } while(retVerts == null); // keep changing color of curVert until no problems appear
        retVerts.add(0, curVert);
        return retVerts;
    }
}
