package graphcoloring;

/**
 *
 * @author Lizzie Herman and Connor O'Leary
 */

import java.util.*;
import java.io.*;

public class GraphColoring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run Program = 0, Run Experiment = 1");
        int num = scanner.nextInt();
        if(num == 0){
            consolePrintout();
        } else {
            System.out.print("Input number of trials you wish to do: ");
            int numTrials = scanner.nextInt();
            System.out.println("Input name of file you would like the data to be recorded to: ");
            String filename = scanner.next();
            experimenter(numTrials, filename);
        }
    }   
    public static void experimenter(int numTrials, String filename){
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
            writer.append("Algorithm,Size,Colors,Solution,Correct,Decisions");
            for(int size = 10; size <= 100; size += 10){
                for(int alg = 0; alg < 5; alg++){
                    for(int k = 0; k < numTrials; k++){
                        for(int color = 3; color < 5; color++){
                            Solver solve = new MinConflicts();
                            switch(alg){
                                case 0:
                                    solve = new MinConflicts();
                                    writer.append("\nMin,");
                                    break;
                                case 1:
                                    solve = new BackTrackingS();
                                    writer.append("\nBTS,");
                                    break;
                                case 2:
                                    solve = new BackTrackingFC();
                                    writer.append("\nBFC,");
                                    break;
                                case 3:
                                    solve = new BackTrackingMAC();
                                    writer.append("\nMAC,");
                                    break;
                                case 4:
                                    solve = new GeneticAlgo();
                                    writer.append("\nGen,");
                                    break;
                                default:
                                    break;
                            }
                            writer.append(size + "," + color + ",");
                            Graph g = new Graph(size);
                            g = solve.solve(g, color);
                            if(g.getVertices() == null){
                                writer.append("no,,");
                            }else{
                                writer.append("yes,");
                                if(solve.evaluate(g)) writer.append("yes,");
                                else writer.append("no,");
                            }
                            writer.append(Integer.toString(solve.count));
                        }
                    }
                }
            }
            writer.close();
        }catch(Exception e){
            System.out.println("Error Occured");
        }
    }
    
    public static void consolePrintout(){
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
                    System.out.print("Number of Vertices: " + i + " Number of Colors: 3 Decision Count: " + solve.count + " Coloring: ");
                    if(g.getVertices() == null){
                        System.out.print("Not Possible");
                    }else{
                        if(solve.evaluate(g)) System.out.print("Correct");
                        else System.out.print("Error");
                    }
                    System.out.println("\n");
                    g = new Graph(i);
                    g = solve.solve(g, 4);
                    System.out.print("Number of Vertices: " + i + " Number of Colors: 4 Decision Count: " + solve.count + " Coloring: ");
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
