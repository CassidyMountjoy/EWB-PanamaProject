package panama;

import graph.DirectedGraph;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class MakeMap {
        public void readMap(String name) {
            Scanner inp = new Scanner(new FileReader(name));
        }
        /** A labeled directed graph of Locations whose edges are labeled by
         *  Roads. */
        private static class PipeMap extends LabeledGraph<Home, Road> {
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

    }

