package edu.emory.cs.graph.flow;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

/** @author Jinho D. Choi */
public class NetworkFlowQuiz {
    /**
     * Using depth-first traverse.
     * @param graph  a directed graph.
     * @param source the source vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> paths = new HashSet<>();

        // dfs
        for(Edge startEdge: graph.getIncomingEdges(target)) {
            recurse(graph, source, target, startEdge, paths, new Subgraph());
        }

        return paths;
    }

    public void recurse(Graph graph, int source, int target, Edge currentEdge, Set<Subgraph> paths, Subgraph currentPath){

        if (currentPath.contains(target)) return; // cycle

        currentPath.addEdge(currentEdge);

        if (currentEdge.getSource() == source) { // path found
            paths.add(currentPath);
            return;
        }

        // dfs on the source of the current edge
        for (Edge nextEdge : graph.getIncomingEdges(currentEdge.getSource())) {
            Subgraph newPath = new Subgraph(currentPath);
            recurse(graph, source, nextEdge.getSource(), nextEdge, paths, newPath);
        }
    }

}