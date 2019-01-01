package graph;

/* See restrictions in Graph.java. */

import java.util.Collections;
import java.util.LinkedList;

/** Implements a depth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit and
 *  postVisit methods, as desired (by default, they do nothing).
 *  @author Cassidy
 */
public class DepthFirstTraversal extends Traversal {

    /**
     * A depth-first Traversal of G.
     */
    protected DepthFirstTraversal(Graph G) {
        super(G, Collections.asLifoQueue(new LinkedList<>()));

    }
    @Override
    protected boolean shouldPostVisit(int v) {
        return true;
    }
}
