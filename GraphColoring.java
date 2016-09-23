package graphcoloring;

/**
 *
 * @author Lizzie Herman and Connor O'Leary
 */
 
import java.util.*;

public class GraphColoring {
    public static void main(String[] args) {
        boolean run = true;
        while(run){
            Solver solve  = new BackTrackingS();
            System.out.println("Select which type of algorithm you want to use\nMin Conflicts: 0; Simple Backtracking: 1; Forward Checking: 2; MAC: 3; Genetic: 4; Quit: Else");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            switch(number){
                case 0:
                    solve = new MinConflicts();
                    break;
                case 1:
                    solve = new BackTrackingS();
                    break;
                case 2:
                    solve = new BackTrackingFC();
                    break;
                case 3:
                    solve = new BackTrackingMAC();
                    break;
                case 4:
                    solve = new GeneticAlgo();
                    break;
                default:
                    run = false;
                    break;
            }
            if(run){
                for(int i = 10; i <= 100; i += 10){
                    Graph g = new Graph(i);
                    g = solve.solve(g, 3);
                    System.out.print("Number of Vertices: " + i + " Number of Colors: 3 Assignment Count: " + solve.count + " Coloring: ");
                    if(g.getVertices() == null){
                        System.out.print("Not Possible");
                    }else{
                        if(solve.evaluate(g)) System.out.print("Correct");
                        else System.out.print("Error");
                    }
                    System.out.println("\n");
                    g = new Graph(i);
                    g = solve.solve(g, 4);
                    System.out.print("Number of Vertices: " + i + " Number of Colors: 4 Assignment Count: " + solve.count + " Coloring: ");
                    if(g.getVertices() == null){
                        System.out.print("Not Possible");
                    }else{
                        if(solve.evaluate(g)) System.out.print("Correct");
                        else System.out.print("Error");
                    }
                    System.out.println("\n");
                }
            }
        }
    }   
}
