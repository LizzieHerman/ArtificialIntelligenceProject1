package graphcoloring;

import java.util.*;

/**
 *
 * @author Lizzie Herman
 */
public abstract class Solver {
    Comparator connections = new Comparator<Vertex>(){ // used to sort vertex list by highest num connections
        public int compare(Vertex v1, Vertex v2){
            if(v1.getConnections().isEmpty() && ! v2.getConnections().isEmpty()) return 1;
            if(v2.getConnections().isEmpty() && ! v1.getConnections().isEmpty()) return -1;
            if(v1.getConnections().isEmpty() && v2.getConnections().isEmpty()) return 0;
            return v2.getConnections().size() - v1.getConnections().size();
        }
    };
    Comparator possColors = new Comparator<Vertex>(){ // used to sort vertex list by lowest num of poss colors
        public int compare(Vertex v1, Vertex v2){
            if(v1.getPossColors().isEmpty() && ! v2.getPossColors().isEmpty()) return -1;
            if(v2.getPossColors().isEmpty() && ! v1.getPossColors().isEmpty()) return 1;
            if(v1.getPossColors().isEmpty() && v2.getPossColors().isEmpty()) return 0;
            return v1.getPossColors().size() - v2.getPossColors().size();
        }
    };
    
    public abstract Graph solve(Graph g, int k); // solve method changes for each algorithm
    
    public boolean evaluate(Graph g){
        for(Edge e: g.getEdges()){
            int a = e.getFirstVert().getColor();
            int b = e.getSecVert().getColor();
            if(a == b) return false; // sees if any edge has the same color
        }
        return true;
    }
}
