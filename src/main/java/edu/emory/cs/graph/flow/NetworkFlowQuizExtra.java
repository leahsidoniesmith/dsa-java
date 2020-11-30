package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.*;
import java.util.HashSet;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

/** @author Jinho D. Choi */
public class NetworkFlowQuizExtra {
    /**
     * Using breadth-first traverse.
     * @param graph  a directed graph.
     * @param source the source vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Queue<Subgraph> queue = new LinkedList<>();
        Set<Subgraph> allPaths = new HashSet<>();

        for (Edge edge: graph.getIncomingEdges(target)){
            Subgraph path = new Subgraph();
            path.addEdge(edge);
            queue.add(path);
        }

        while(! queue.isEmpty()){
            Subgraph path = queue.remove();

            Edge min = findMinEdge(path, target, graph);

            // path found
            if (min.getSource() == source) {
                allPaths.add(path);
                // continue bfs if path not found
            } else {
                for (Edge edge: graph.getIncomingEdges(min.getSource())) {
                    if(path.contains(edge.getSource())) continue;
                    Subgraph newPath = new Subgraph(path);
                    newPath.addEdge(edge);
                    queue.add(newPath);
                }
            }
        }

        return allPaths;
    }


    // helper method to find the most recent edge in the path
    public Edge findMinEdge(Subgraph path, int target, Graph graph){
        List<Edge> min = new ArrayList<>();
        min = path.getEdges();
        Edge end = min.get(min.size() - 1);
        for(Edge edge : graph.getIncomingEdges(end.getSource())) {
            if(!path.contains(edge.getSource())) continue;
            if(edge.getSource() == 0){
                end = edge;
                return end;
            }
            if(edge.getSource() < end.getSource()){
                end = edge;
            }
        }

        return end;
    }

}