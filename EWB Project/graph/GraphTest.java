package graph;


import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Cassidy
 */
public class GraphTest {


    @Test
    public void removesimpletest() {
        DirectedGraph p = new DirectedGraph();
        p.add();
        p.add();
        p.add();
        p.add();
        p.add(1, 3);
        p.add(1, 2);
        p.add(1, 4);
        p.remove(1);
        assertEquals(0, p.edgeSize());
    }
    @Test
    public void removetest() {
        DirectedGraph p = new DirectedGraph();
        p.add();
        p.add();
        p.add();
        p.add();
        p.add(1, 3);
        p.add(1, 2);
        p.add(1, 4);
        p.add(4, 3);
        p.add(4, 2);
        p.remove(1, 4);
        assertEquals(4, p.edgeSize());
        p.remove(1);
        assertEquals(2, p.edgeSize());
        assertEquals(3, p.vertexSize());

    }
    @Test
    public void generaltest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 1);
        g.add(1, 3);
        assertEquals(3, g.vertexSize());
        assertEquals(2, g.outDegree(1));
        assertEquals(3, g.edgeSize());
        DirectedGraph p = g;
        g.add(1, 3);
        assertEquals(2, g.outDegree(1));
        g.remove(1, 3);
        assertEquals(p, g);
        g.remove(2);
        g.add();
        g.remove(1);
        g.add();
        g.add();
    }
    @Test
    public void vertiteratortests() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        Iteration<Integer> itr = g.vertices();
        int x = itr.next();
        int p = itr.next();
        int q = itr.next();
        assertEquals(1, x);
        assertEquals(2, p);
        assertEquals(3, q);
        g.remove(2);
        Iteration<Integer> itr2 = g.vertices();
        int r = itr2.next();
        int l = itr2.next();
        assertEquals(1, r);
        assertEquals(3, l);
    }
    @Test
    public void succiteratortests() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        Iteration<Integer> itr = g.successors(1);
        int x = itr.next();
        int p = itr.next();
        assertEquals(2, x);
        assertEquals(3, p);
    }

    @Test
    public void undirectedtests() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(1, 5);
        Iteration<Integer> itr = g.successors(1);
        int x = itr.next();
        int p = itr.next();
        assertEquals(2, x);
        assertEquals(3, p);
        Iteration<int[]> itr2 = g.edges();
        int[] r = new int[]{1, 2};
        int[] s = new int[]{1, 3};
        int[] t = new int[]{1, 4};
        assertArrayEquals(r, itr2.next());
        assertArrayEquals(s, itr2.next());
        assertArrayEquals(t, itr2.next());
    }

}
