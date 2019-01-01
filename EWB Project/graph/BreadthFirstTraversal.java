package graph;

/* See restrictions in Graph.java. */

import java.util.Queue;
import java.util.LinkedList;

/** Implements a breadth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit method as desired
 *  (by default, it does nothing).
 *  @author Cassidy
 */
public class BreadthFirstTraversal extends Traversal {

    /** A breadth-first Traversal of G. */
    protected BreadthFirstTraversal(Graph G) {
        super(G, _fifo);
    }
    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }
    /** A breadth-first Traversal of G. */
    private static Queue<Integer> _fifo = new LinkedList<>();
}
