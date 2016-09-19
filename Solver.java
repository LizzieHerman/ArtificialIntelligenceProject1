/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

/**
 *
 * @author Lizzi
 */
public abstract class Solver {
    public abstract Graph solve(Graph g, int k);
    public abstract boolean evaluate(Graph g, int k);
}
