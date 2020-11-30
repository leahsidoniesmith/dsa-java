package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import org.junit.Test;

import java.util.Set;



public class NetworkFlowQuizTest {

    @Test
    public void testNetworkFlowQuiz1() {
        Graph graph;
        graph = getGraph0();
        Set<Subgraph> augmentingPaths;
        NetworkFlowQuiz quiz = new NetworkFlowQuiz();
        augmentingPaths = quiz.getAugmentingPaths(graph, 0, graph.size() - 1);
        System.out.println("graph0");
        for (Subgraph path : augmentingPaths){
            System.out.println(path.getEdges());
        }
    }

    @Test
    public void testNetworkFlowQuiz2() {
        Graph graph;
        graph = getGraph1();
        Set<Subgraph> augmentingPaths;
        NetworkFlowQuiz quiz = new NetworkFlowQuiz();
        augmentingPaths = quiz.getAugmentingPaths(graph, 0, graph.size() - 1);
        System.out.println("graph1");
        for (Subgraph path : augmentingPaths){
            System.out.println(path.getEdges());
        }
    }



    @Test
    public void testNetworkFlowQuizBFS1() {
        Graph graph;
        graph = getGraph0();
        Set<Subgraph> augmentingPaths;
        NetworkFlowQuizExtra quiz = new NetworkFlowQuizExtra();
        augmentingPaths = quiz.getAugmentingPaths(graph, 0, graph.size() - 1);
        System.out.println("bfsgraph0");
        for (Subgraph path : augmentingPaths){
            System.out.println(path.getEdges());
        }
    }

    @Test
    public void testNetworkFlowQuizBFS2() {
        Graph graph;
        graph = getGraph1();
        Set<Subgraph> augmentingPaths;
        NetworkFlowQuizExtra quiz = new NetworkFlowQuizExtra();
        augmentingPaths = quiz.getAugmentingPaths(graph, 0, graph.size() - 1);
        System.out.println("bfsgraph1");
        for (Subgraph path : augmentingPaths){
            System.out.println(path.getEdges());
        }
    }

    Graph getGraph0() {
        Graph graph = new Graph(6);
        int s = 0, t = 5;

        graph.setDirectedEdge(s, 1, 4);
        graph.setDirectedEdge(s, 2, 2);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 2);
        graph.setDirectedEdge(2, 4, 3);
        graph.setDirectedEdge(3, 2, 1);
        graph.setDirectedEdge(3, t, 2);
        graph.setDirectedEdge(4, t, 4);

        return graph;
    }

    public Graph getGraph1() {
        Graph graph = new Graph(4);
        int s = 0, t = 3;

        graph.setDirectedEdge(2, t, 1);
        graph.setDirectedEdge(1, t, 1);
        graph.setDirectedEdge(1, 2, 1);
        graph.setDirectedEdge(s, 2, 1);
        graph.setDirectedEdge(s, 1, 1);

        return graph;
    }
}
