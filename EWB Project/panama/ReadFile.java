package panama;

import graph.DirectedGraph;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import graph.DirectedGraph;
import graph.LabeledGraph;
import graph.SimpleShortestPaths;
import graph.DirectedGraph;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import static trip.Main.error;

public class ReadFile {

    ReadFile(String input) {
        readinput(input);
    }
    public void readinput(String input) {
        Scanner scnr = new Scanner(input);
        scnr.useDelimiter(",");
        while (scnr.hasNextLine()) {
            Integer id = scnr.nextInt();
            Integer numpeople =
        }
    }
    /** A labeled directed graph of Locations whose edges are labeled by
     *  Roads. */
    private static class PipeMap extends LabeledGraph<Home, Pipe> {
        /** An empty RoadMap. */
        PipeMap() {
            super(new DirectedGraph());
        }
    }
    /** Represents the network of Locations and Roads. */
    private panama.ReadFile.PipeMap _map = new panama.ReadFile.PipeMap();
    /** Mapping of Location names to corresponding map vertices. */
    private HashMap<Integer, Integer> _sites = new HashMap<>();

    private void addLocation(Integer name, double x, double y) {
        int v = _map.add(new Home(name, x, y));
        _sites.put(name, v);
    }
private Integer _housenumber;
    private Integer _long;
    private Integer _lat;
}
