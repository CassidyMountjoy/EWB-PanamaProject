package graph;


/* See restrictions in Graph.java. */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.Optional;
import java.util.List;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Cassidy Mountjoy
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _arenodes = new ArrayList<>(1000);
        for (int x = 0; x < 1000; x++) {
            _arenodes.add(false);
        }
        _vertexsize = 0;
        m = 100;
        _graphin = new ArrayList<>();
        _graphout = new ArrayList<>();
        _edges = new ArrayList<>();
        _edges.add(new int[]{0, 0});
        for (int x = 0; x < 1000; x++) {
            _graphout.add(new LinkedList<>());
            _graphin.add(new LinkedList<>());
        }
    }

    @Override
    public int vertexSize() {
        return _vertexsize;
    }

    @Override
    public int maxVertex() {
        return _maxvertex;
    }

    @Override
    public int edgeSize() {
        return _edges.size() - 1;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!isDirected()) {
            ArrayList<Integer> lst = new ArrayList<>();
            Optional.ofNullable(_graphout.get(v)).ifPresent(lst::addAll);
            Optional.ofNullable(_graphin.get(v)).ifPresent(lst::addAll);
            List<Integer> deduped =
                    lst.stream().distinct().collect(Collectors.toList());
            return deduped.size();
        }
        if (_graphout.get(v) == null) {
            return 0;
        }
        return _graphout.get(v).size();
    }

    @Override
    public int inDegree(int v) {
        if (!isDirected()) {
            ArrayList<Integer> lst = new ArrayList<>();
            Optional.ofNullable(_graphout.get(v)).ifPresent(lst::addAll);
            Optional.ofNullable(
                    _graphin.get(v)).ifPresent(lst::addAll);
            List<Integer> deduped =
                    lst.stream().distinct().collect(Collectors.toList());
            return deduped.size();
        }
        if (_graphin.get(v) == null) {
            return 0;
        }
        return _graphin.get(v).size();
    }

    @Override
    public boolean contains(int u) {
        return _arenodes.get(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (isDirected()) {
            for (int[] i : _edges) {
                if (i[0] == u && i[1] == v) {
                    return true;
                }
            }
            return false;
        }
        for (int[] i : _edges) {
            if ((i[0] == u && i[1] == v) || (i[1] == u && i[0] == v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        _vertexsize += 1;
        if (m < _maxvertex) {
            int i = m;
            for (m *= 2; i < m; i++) {
                _graphout.add(new LinkedList<>());
                _graphin.add(new LinkedList<>());
            }
        }
        int x = 0;
        for (int i = 1; i < _arenodes.size(); i++) {
            if (!_arenodes.get(i)) {
                x = i;
                break;
            }
        }
        if (x > _maxvertex) {
            _maxvertex = x;
        }
        _arenodes.set(x, true);
        return x;
    }

    @Override
    public int add(int u, int v) {
        if (isDirected()) {
            checkMyVertex(u);
            checkMyVertex(v);
            for (Integer i : _graphout.get(u)) {
                if (i == v) {
                    return edgeId(u, v);
                }
            }
            int[] x;
            x = new int[]{u, v};
            _edges.add(x);
            _edgesize += 1;
            _graphout.get(u).add(v);
            _graphin.get(v).add(u);
            return _edges.indexOf(x);
        } else {
            checkMyVertex(u);
            checkMyVertex(v);
            int[] x;
            x = new int[]{u, v};
            _edges.add(x);
            _edgesize += 1;
            _graphout.get(u).add(v);
            _graphin.get(v).add(u);
            return _edges.indexOf(x);
        }
    }

    @Override
    public void remove(int v) {
        checkMyVertex(v);
        _arenodes.set(v, false);
        _vertexsize -= 1;
        Iterator<LinkedList<Integer>> iter = _graphin.iterator();

        while (iter.hasNext()) {
            LinkedList<Integer> inter = iter.next();
            if (inter == null) {
                continue;
            }
            Iterator<Integer> integerIterator = inter.iterator();
            while (integerIterator.hasNext()) {
                Integer r = integerIterator.next();
                if (r == v) {
                    integerIterator.remove();
                }
            }
        }
        Iterator<LinkedList<Integer>> iter2 = _graphout.iterator();

        while (iter2.hasNext()) {
            LinkedList<Integer> inter = iter2.next();
            if (inter == null) {
                continue;
            }
            Iterator<Integer> integerIterator = inter.iterator();
            while (integerIterator.hasNext()) {
                Integer r = integerIterator.next();
                if (r == v) {
                    integerIterator.remove();
                }
            }
        }
        ArrayList<int[]> lst = new ArrayList<>();
        for (int[] i: _edges) {
            if (i[0] != v && i[1] != v) {
                lst.add(i);
            }
        }
        if (v == _maxvertex) {
            _maxvertex -= 1;
        }
        _edges = lst;
        _edgesize = lst.size();
        _graphin.set(v, null);
        _graphout.set(v, null);
    }

    @Override
    public void remove(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        _edgesize -= 1;
        for (int i = 0; i < _edges.size() - 1; i++) {
            if (_edges.get(i)[0] == u && _edges.get(i)[1] == v) {
                _edges.remove(edgeId(u, v));
            }
        }
        for (Integer i: _graphin.get(v)) {
            if (i == u) {
                _graphin.get(v).remove(i);
            }
        }
        for (Integer i: _graphout.get(u)) {
            if (i == v) {
                _graphout.get(u).remove(i);
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        int count = 0;
        ArrayList<Integer> lst = new ArrayList<>();
        for (boolean i: _arenodes) {
            if (i) {
                lst.add(count);
            }
            count += 1;

        }
        return Iteration.iteration(lst);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> lst = new ArrayList<>();
        if (!isDirected()) {
            Optional.ofNullable(_graphout.get(v)).ifPresent(lst::addAll);
            Optional.ofNullable(_graphin.get(v)).ifPresent(lst::addAll);
            List<Integer> deduped =
                    lst.stream().distinct().collect(Collectors.toList());
            return Iteration.iteration(deduped);

        }
        Optional.ofNullable(_graphout.get(v)).ifPresent(lst::addAll);
        return Iteration.iteration(lst);
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> lst = new ArrayList<>();
        if (!isDirected()) {
            Optional.ofNullable(_graphout.get(v)).ifPresent(lst::addAll);
            Optional.ofNullable(_graphin.get(v)).ifPresent(lst::addAll);
            List<Integer> deduped =
                    lst.stream().distinct().collect(Collectors.toList());
            return Iteration.iteration(deduped);

        }
        Optional.ofNullable(_graphin.get(v)).ifPresent(lst::addAll);
        return Iteration.iteration(lst);
    }

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> c = new ArrayList<>(_edges);
        c.removeIf(r -> (r[0] == 0 || r[1] == 0));
        return Iteration.iteration(c);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }


    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected()) {
            for (int[] edge : _edges) {
                if ((edge[0] == v && edge[1] == u)
                        || (edge[1] == v && edge[0] == u)) {
                    return _edges.indexOf(edge);
                }
            }
        }
        for (int[] edge : _edges) {
            if ((edge[0] == u && edge[1] == v)) {
                return _edges.indexOf(edge);
            }
        }
        return 0;
    }


/** A representation of the number of nodes.
 */
    private int _vertexsize;

/** A representation max number of nodes.
 */
    private int _maxvertex;

    /** A representation max number of nodes.
     */
    private int _edgesize;

    /** A representation the nodes, whose numbers correspond
     * to the index of the arraylist.
     */
    private ArrayList<LinkedList<Integer>> _graphout;

    /** A representation of all my vertices.
     */
    private ArrayList<LinkedList<Integer>> _graphin;

    /** A representation of all my edges.
     */
    private ArrayList<int[]> _edges;
    /** A representation of all my edges.
     */
    private ArrayList<Boolean> _arenodes;
    /** A representation of all my edges.
     */
    private int m;
    /** A representation of all my edges.
     */
    private int n;
}
