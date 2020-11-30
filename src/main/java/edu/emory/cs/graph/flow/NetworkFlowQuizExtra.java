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
        List<Integer> isVisited = new ArrayList<>();
        Queue<Subgraph> queue = new LinkedList<>();
        Set<Subgraph> allPaths = new HashSet<>();

        for (Edge edge: graph.getIncomingEdges(target)){
            Subgraph path = new Subgraph();
            path.addEdge(edge);
            queue.add(path);
        }

        while(! queue.isEmpty()){
            Subgraph path = queue.remove(); //remove head

            Edge min = findMinEdge2(path, target, graph);

            if (min.getSource() == source) {
                allPaths.add(path);
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

    //need to find a better way to find the last edge...the last edge is not always the min edge
    public Edge findMinEdge(Subgraph path){
        Edge min = new Edge(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Edge edge: path.getEdges()){
            if(edge.getSource() < min.getSource()){
                min = edge;
            }
        }
        return min;
    }

    public Edge findMinEdge2(Subgraph path, int target, Graph graph){
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