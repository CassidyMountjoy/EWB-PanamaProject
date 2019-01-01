package graph;

import org.junit.Test;
import ucb.junit.textui;

import java.util.ArrayList;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your graph package per se (that is, it must be
 * possible to remove them and still have your package work). */

/** Unit tests for the graph package.  This class serves to dispatch
 *  other test classes, which are listed in the argument to runClasses.
 *  @author Cassidy
 */
public class UnitTest {

    /**
     * Run all JUnit tests in the graph package.
     */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(graph.GraphTest.class));
    }

    /*===== Miscellaneous Definitions =====*/

    /**
     * Abbreviation for infinity.
     */
    static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Abbreviation for DirectedGraph.
     */
    private static class DG extends DirectedGraph {
    }

    /**
     * Abbreviation for UndirectedGraph.
     */
    private static class UG extends UndirectedGraph {
    }

    @Test
    public void dfstest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 4);
        g.add(1, 2);
        g.add(2, 3);
        g.add(3, 4);
        g.add(2, 5);
        g.add(3, 6);
        g.add(5, 6);
        Traversal p = new DepthFirstTraversal(g);
        ArrayList<Integer> r = new ArrayList<>();
        r.add(1);
        p.traverse(1);
    }

    @Test
    public void bfstest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 4);
        g.add(1, 2);
        g.add(2, 3);
        g.add(3, 4);
        g.add(2, 5);
        g.add(3, 6);
        g.add(5, 6);
        Traversal p = new BreadthFirstTraversal(g);
        ArrayList<Integer> r = new ArrayList<>();
        r.add(1);
        p.traverse(1);
    }

    @Test
    public void shortestpathstest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 8);
        g.add(2, 3);
        g.add(3, 4);
        g.add(4, 5);
        g.add(4, 6);
        g.add(3, 6);
        g.add(5, 6);
        g.add(3, 9);
        g.add(9, 7);
        g.add(6, 7);
        g.add(8, 9);
        g.add(7, 8);
        g.add(8, 2);
    }
}
