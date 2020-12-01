
package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.*;

/** @author Jinho D. Choi */
public class MSTAllHW implements MSTAll {
    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {
        MST kruskal = new MSTKruskal();
        SpanningTree mstKruskal = kruskal.getMinimumSpanningTree(graph);
        double weight = mstKruskal.getTotalWeight(); // weight of every mst
        int mstNumEdges = graph.size() - 1; // mst must have v-1 edges
        HashSet<Integer> vertices = new HashSet<>(); // mst must include all vertices
        for(Edge edge: graph.getAllEdges()){
            vertices.add(edge.getSource());
            vertices.add(edge.getTarget());
        }

        // put matching incoming/outgoing edges together into sets of edges
        List<Set<Edge>> edgePairs = getEdgePairs(graph);

        // generate all possible combinations of graphs that have mstNumEdges
        List<int[]> combinations = getEdgeCombs(edgePairs.size(), mstNumEdges, edgePairs, graph);

        // generate all MSTS using Kruskal's
        List<SpanningTree> result = getGraphs(combinations, mstNumEdges, edgePairs, graph, vertices, weight);

        return result;
    }

    // ---------------- Recursive Implementation of n choose r notation ---------------------- //
    public void recurse(List<int[]> combinations, int[] edgePairs, int start, int end, int index){
        if (index == edgePairs.length) {
            int[] combination = edgePairs.clone();
            combinations.add(combination);
        } else {
            int max = Math.min(end, end + 1 - edgePairs.length + index);
            for(int i = start; i <= max; i++) {
                edgePairs[index] = i;
                recurse(combinations, edgePairs, i + 1, end, index + 1);
            }
        }
    }

    public List<int[]> getEdgeCombs(int numEdgePairs, int mstSize, List<Set<Edge>> edgePairs, Graph graph) {
        List<int[]> combinations = new ArrayList<>();
        recurse(combinations, new int[mstSize], 0, numEdgePairs - 1, 0);
        return combinations;
    }

    // ---------------- Applying Kruskal's to each graph ---------------------- //
    public List<SpanningTree> getGraphs(List<int[]> combinations, int mstSize, List<Set<Edge>> edgePairs, Graph graph, HashSet<Integer> vertices, double weight){
        List<SpanningTree> result = new ArrayList<>();
        MST kruskal = new MSTKruskal();

        for (int[] combination : combinations) {
           Graph tempGraph = new Graph(mstSize + 1);
            for (int i = 0; i < combination.length; i++){
                Set<Edge> tempEdge = edgePairs.get(combination[i]);
                for (Edge edge: tempEdge){
                    tempGraph.setDirectedEdge(edge.getSource(), edge.getTarget(), edge.getWeight());
                }
            }

            SpanningTree mst = kruskal.getMinimumSpanningTree(tempGraph);

            // check st is an mst
            if(mst.getTotalWeight() != weight){ continue;}

            // check mst has all vertices
            HashSet<Integer> verticesTemp = new HashSet<>();
            for(Edge edge: mst.getEdges()){
                verticesTemp.add(edge.getSource());
                verticesTemp.add(edge.getTarget());
            }
            if( ! vertices.equals(verticesTemp)) { continue;}

            // check mst is connected
            List<Edge> edges = mst.getEdges();
            if(edges.size() != mstSize) {continue;}

            result.add(mst);

        }
        return result;
    }

    // ---------------- Collecting Undirected Edges ---------------------- //
    public List<Set<Edge>> getEdgePairs(Graph graph){
        List<Set<Edge>> edgePairs = new ArrayList<>();
        List<Edge> edgeSingles = graph.getAllEdges();
        Set<Edge> edgeSinglesTemp = new HashSet<>(edgeSingles);
        while ( ! edgeSinglesTemp.isEmpty() ){
            for (int i = 0; i < edgeSingles.size() - 1; i++){
                for (int j = 1; j < edgeSingles.size(); j++){
                    if (edgeSingles.get(i).getSource() == edgeSingles.get(j).getTarget() &&
                            edgeSingles.get(i).getTarget() == edgeSingles.get(j).getSource()){
                        if (edgeSinglesTemp.contains(edgeSingles.get(i)) && edgeSinglesTemp.contains(edgeSingles.get(j))) {
                            Set<Edge> temp = new HashSet<>();
                            temp.add(edgeSingles.get(i));
                            temp.add(edgeSingles.get(j));
                            edgeSinglesTemp.remove(edgeSingles.get(i));
                            edgeSinglesTemp.remove(edgeSingles.get(j));
                            edgePairs.add(temp);
                        }
                    }
                }
            }
        }
        return edgePairs;
    }
}


