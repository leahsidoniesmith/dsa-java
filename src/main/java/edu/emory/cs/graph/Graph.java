package edu.emory.cs.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Graph {
    /**
     * A list of edge lists, where each dimension of the outer list indicates a target vertex. and
     * the inner list corresponds to the list of incoming edges to that target vertex.
     */
    private final List<List<Edge>> incoming_edges;

    public Graph(int size) {
        incoming_edges = Stream.generate(ArrayList<Edge>::new).limit(size).collect(Collectors.toList());  //creates an empty edge list for each target vertex
    }

    public Graph(Graph g) { //copies the list of edge lists from g to this graph
        incoming_edges = g.incoming_edges.stream().map(ArrayList::new).collect(Collectors.toList());
    }

    public int size() { //size of the graph is the number of vertices in the graph
        return incoming_edges.size();
    }

    public List<Edge> getIncomingEdges(int target) {
        return incoming_edges.get(target);
    }

    public List<Edge> getAllEdges() {
        return incoming_edges.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public Deque<Integer> getVerticesWithNoIncomingEdges() {
        return IntStream.range(0, size()).filter(i -> getIncomingEdges(i).isEmpty()).boxed().collect(Collectors.toCollection(ArrayDeque::new));
    }

    /**
     * @return a list of edge deque where each dimension in the outer list represents the deque of outgoing edges for
     * the corresponding source vertex.
     */
    public List<Deque<Edge>> getOutgoingEdges() {
        List<Deque<Edge>> outgoing_edges = Stream.generate(ArrayDeque<Edge>::new).limit(size()).collect(Collectors.toList());

        for (int target = 0; target < size(); target++) {
            for (Edge incoming_edge : getIncomingEdges(target))
                outgoing_edges.get(incoming_edge.getSource()).add(incoming_edge);
        }

        return outgoing_edges;
    }

    public Edge setDirectedEdge(int source, int target, double weight) {
        List<Edge> edges = getIncomingEdges(target);
        Edge edge = new Edge(source, target, weight);
        edges.add(edge);
        return edge;
    }

    public void setUndirectedEdge(int source, int target, double weight) {
        setDirectedEdge(source, target, weight);
        setDirectedEdge(target, source, weight); //add edges to both directions
    }

    public boolean containsCycle() {
        Deque<Integer> notVisited = IntStream.range(0, size()).boxed().collect(Collectors.toCollection(ArrayDeque::new)); //initially all vertices are not visited

        while (!notVisited.isEmpty()) { //iterate until all vertices are visited
            if (containsCycleAux(notVisited.poll(), notVisited, new HashSet<>())) //poll returns the head of the queue
                return true;    
        }

        return false;
    }

    private boolean containsCycleAux(int target, Deque<Integer> notVisited, Set<Integer> visited) {
        notVisited.remove(target);
        visited.add(target);

        for (Edge edge : getIncomingEdges(target)) {
            if (visited.contains(edge.getSource())) //returns true if the source vertex of this edge has seen before
                return true;

            if (containsCycleAux(edge.getSource(), notVisited, new HashSet<>(visited))) //returns true if the recursive call finds a cycle
                return true;
        }

        return false;
    }

    public List<Integer> topological_sort(boolean depth_first) {
        Deque<Integer> global = getVerticesWithNoIncomingEdges();
        List<Deque<Edge>> outgoingEdgesAll = getOutgoingEdges();
        List<Integer> order = new ArrayList<>();

        while (!global.isEmpty()) {
            Deque<Integer> local = new ArrayDeque<>();

            // add vertex to the path
            int vertex = global.poll();
            order.add(vertex);
            Deque<Edge> outgoingEdges = outgoingEdgesAll.get(vertex);

            while (!outgoingEdges.isEmpty()) {
                Edge edge = outgoingEdges.poll();

                // remove one outgoing edge at a time
                List<Edge> incomingEdges = getIncomingEdges(edge.getTarget());
                incomingEdges.remove(edge);

                // if the target vertex has no incoming edges, add it to the local queue awaited to be added to the global deque
                if (incomingEdges.isEmpty())
                    local.add(edge.getTarget());
            }

            //Transfer all vertices in local to global
            while (!local.isEmpty()) {
                if (depth_first) global.addFirst(local.removeLast());
                else global.addLast(local.removeFirst());
            }
        }

        if (!hasNoEdge()) throw new IllegalArgumentException("Cyclic graph.");
        return order;
    }

    public boolean hasNoEdge() { //returns true if all incoming edge lists are empty
        return IntStream.range(0, size()).allMatch(i -> getIncomingEdges(i).isEmpty());
    }

    public String toString() {
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < incoming_edges.size(); i++) {
            build.append(i);
            build.append(" <- ");
            build.append(incoming_edges.get(i).toString());
            build.append("\n");
        }

        return build.toString();
    }
}