package graphcoloring;

import java.util.*;

/**
 *
 * @author Lizzie Herman
 */
public class BackTrackingMAC extends Solver{
    private Graph g;
    public Graph solve(Graph graf, int k){
        g = graf;
        count = 0;
        g.giveVertPossColors(k);
        ArrayList<Vertex> coloredVerts = backTrack(g.getVertices(), k);
        g.setColoredVerts(coloredVerts);
        return g;
    }
    
    // returns either a colored list or null if failure
    public ArrayList<Vertex> backTrack(ArrayList<Vertex> recVerts, int num){
        ArrayList<Vertex> retVerts = new ArrayList();
        Collections.sort(recVerts, possColors);
        int a = recVerts.get(0).getConnections().size();
        for(Vertex v: recVerts){
            if(a == v.getConnections().size()) retVerts.add(v);
            else break;
        }
        Collections.sort(retVerts, connections);
        Vertex curVert = retVerts.get(0);
        retVerts = null;
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
            if(! forwardCheck(curVert.getConnections(), color)){ // ADDED FORWARD CHECKING
                curVert.removePossColor(color);
                continue;
            }
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
    
    public boolean forwardCheck(ArrayList<Vertex> verts, Integer color){
        int n = verts.size();
        boolean a = true;
        boolean[] changedPossColor = new boolean[n]; // record whether or not the assignment changed the available colors for the neighbors
        int i = 0;
        for(Vertex v: verts){
            changedPossColor[i] = v.removePossColor(color); // remove the color assigned from the connections
            i++;
            if(v.getPossColors().isEmpty()) a = false; // check to see whether solution has made an assignment impossible
        }
        //a = MACCheck(); // added MAC checking
        if(! a){ // undo change in domain if color assignment is wrong
            int j = 0;
            for(Vertex v: verts){
                if(changedPossColor[j]){ // change only the vertices that were originally changed
                    v.addPossColor(color);
                }
                j++;
            }
        }
        return a;
    }
    
    public boolean MACCheck(){
        for(Edge e: g.getEdges()){
            Vertex a = e.getFirstVert();
            Vertex b = e.getSecVert();
            int c = a.getPossColors().size();
            int d = b.getPossColors().size();
            if((c == 1) && d != 0 || (d == 1 && c != 0)){
                c = a.getPossColors().get(0);
                d = b.getPossColors().get(0);
                if(c == d){ // see if the vertexes of an edge only have the same available coloring option
                    return false;
                }
            }
        }
        return true;
    }
}
