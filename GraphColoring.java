package graphcoloring;

/**
 *
 * @author Lizzie Herman
 */
public class GraphColoring {
    public static void main(String[] args) {
        for(int i = 10; i <= 100; i += 10){
            Graph g = new Graph(i);
            Solver solve = new BackTrackingS(); // change this to change what algorithm using
            g = solve.solve(g, 3);
            System.out.println("Number of Vertices: " + i + " Number of Colors: 3");
            if(g.getVertices() == null){
                System.out.print("Graph could not be colored in 3 colors.");
            }else{
                if(solve.evaluate(g)) System.out.print("The Graph was colored correctly.");
                else System.out.print("The algorithm did not work properly.");
            }
            System.out.println();
            g = new Graph(i);
            g = solve.solve(g, 4);
            System.out.println("Number of Vertices: " + i + " Number of Colors: 4");
            if(g.getVertices() == null){
                System.out.print("Graph could not be colored in 4 colors.");
            }else{
                if(solve.evaluate(g)) System.out.print("The Graph was colored correctly.");
                else System.out.print("The algorithm did not work properly.");
            }
            System.out.println();
        }
    }
}
