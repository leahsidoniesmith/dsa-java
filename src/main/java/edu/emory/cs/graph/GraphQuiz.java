package edu.emory.cs.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** @author Jinho D. Choi */
public class GraphQuiz extends Graph {
    public GraphQuiz(int size) { super(size); }
    public GraphQuiz(Graph g) { super(g); }

    /** @return the total number of cycles in this graph. */
    public int numberOfCycles() {

        Deque<Integer> nodes = IntStream.range(0, size()).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        List<Integer> startVisited = new ArrayList<>(); //holds nodes which we have performed dfs on (recursed from the top)
        int[] count = new int[1];

        for(Integer start : nodes){
            List<Integer> edges = new ArrayList<Integer>(); //holds current path through the graph
            recurse(start, start, startVisited, edges, count);
            startVisited.add(start);
        }

       return count[0];
}

    //uses dfs to find all cycles that contain start node
    public void recurse(int start, int current, List<Integer> startVisited, List<Integer> edges, int[] count) {
        if (startVisited.contains(current)){ //check for duplicate cycle
            return;
        }

        if(edges.contains(current)){
            if(current == start){ //only count cycles which start and end at start node
                count[0] += 1;
            }
            return;
        }

        edges.add(current); //if cycle not found, ass current node to path

        for (Edge next : getIncomingEdges(current)) {
           recurse(start, next.getSource(), startVisited, edges, count);
        }

        edges.remove(edges.size() - 1);
    }
}