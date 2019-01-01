package graph;

/* See restrictions in Graph.java. */

import java.util.TreeSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Cassidy
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _dist = new double[_G.maxVertex() + 1];
        _spset = new boolean[_G.maxVertex() + 1];
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        Set<Integer> set = new TreeSet<>(new CompareVertices());
        Set<Integer> closedlist = new HashSet<>();
        for (Integer i : _G.vertices()) {
            setWeight(i, Double.POSITIVE_INFINITY);
        }
        set.add(_source);
        setWeight(_source, 0);
        while (!set.isEmpty()) {
            Integer curr = ((TreeSet<Integer>) set).pollFirst();
            if (curr == _dest) {
                break;
            }
            closedlist.add(curr);
            for (Integer next : _G.successors(curr)) {
                if (closedlist.contains(next)) {
                    continue;
                }
                double tempcost = getWeight(curr, next)
                        + estimatedDistance(next) + getWeight(curr);
                if (!set.contains(next)) {
                    setWeight(next, tempcost
                            - estimatedDistance(next));
                    set.add(next);
                    setPredecessor(next, curr);
                } else {
                    if ((tempcost - estimatedDistance(next))
                            < getWeight(next)) {
                        set.remove(next);
                        setWeight(next, tempcost
                                - estimatedDistance(next));
                        set.add(next);
                        setPredecessor(next, curr);
                    }
                }

            }

        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);


    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        ArrayList<Integer> path = new ArrayList<>();
        int temp = v;
        path.add(temp);
        while (temp != getSource()) {
            path.add(getPredecessor(temp));
            temp = getPredecessor(temp);

        }
        Collections.reverse(path);
        return path;
    }


    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }



    /** My comparator to be used. */
    private class CompareVertices implements Comparator<Integer> {


        @Override
        public int compare(Integer a, Integer b) {
            double v = estimatedDistance(a) + getWeight(a);
            double r = estimatedDistance(b) + getWeight(b);
            if (r < v) {
                return 1;
            }
            if (r == v) {
                if (a < b) {
                    return 1;
                }
                if (a == b) {
                    return 0;
                }
                return -1;
            }
            return -1;
        }
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** My current distances. */
    private double[] _dist;
    /** My _spset. */
    private boolean[] _spset;


}
