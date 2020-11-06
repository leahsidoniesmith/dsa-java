package edu.emory.cs.graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class GraphTest {
    @Test
    public void testContainsCycle() {
        Graph graph = new Graph(5);

        graph.setDirectedEdge(0, 1, 1);
        graph.setDirectedEdge(0, 2, 1);
        graph.setDirectedEdge(2, 1, 1);
        graph.setDirectedEdge(2, 3, 1);
        graph.setDirectedEdge(3, 4, 1);
        graph.setDirectedEdge(4, 2, 1);

        System.out.println("testContainsCycle: ");
        System.out.println(graph.toString());
        assertTrue(graph.containsCycle());
    }

    @Test
    public void testTopologicalSort() {
        Graph graph = new Graph(8);

        graph.setDirectedEdge(0, 3, 1);
        graph.setDirectedEdge(0, 4, 1);
        graph.setDirectedEdge(1, 3, 1);
        graph.setDirectedEdge(2, 4, 1);
        graph.setDirectedEdge(3, 5, 1);
        graph.setDirectedEdge(3, 6, 1);
        graph.setDirectedEdge(3, 7, 1);
        graph.setDirectedEdge(4, 6, 1);
        graph.setDirectedEdge(2, 7, 1);

        System.out.println("testTopologicalSort: ");
        System.out.println(graph.toString());

        assertEquals("[0, 1, 2, 3, 4, 5, 7, 6]", new Graph(graph).topological_sort(false).toString());
        assertEquals("[0, 1, 3, 5, 2, 4, 6, 7]", new Graph(graph).topological_sort(true).toString());
    }


    @Test
    public void testGraphQuiz() {

        //test0
        GraphQuiz test0 = new GraphQuiz(5);
        test0.setDirectedEdge(0,2, 1);
        test0.setDirectedEdge(0, 1, 1);
        test0.setDirectedEdge(2, 1, 1);
        test0.setDirectedEdge(2, 3, 1);
        test0.setDirectedEdge(3, 4, 1);
        test0.setDirectedEdge(4, 2, 1);

        System.out.println("test0GraphQuiz: ");
        System.out.println(test0.toString());
        System.out.println(test0.numberOfCycles());

        //test1
        GraphQuiz test1 = new GraphQuiz(5);
        test1.setDirectedEdge(0,2, 1);
        test1.setDirectedEdge(1, 0, 1);
        test1.setDirectedEdge(2, 1, 1);
        test1.setDirectedEdge(2, 3, 1);
        test1.setDirectedEdge(3, 4, 1);
        test1.setDirectedEdge(4, 2, 1);

        System.out.println("test1GraphQuiz: ");
        System.out.println(test1.toString());
        System.out.println(test1.numberOfCycles());

        //test2
        GraphQuiz test2 = new GraphQuiz(6);
        test2.setDirectedEdge(0,2, 1);
        test2.setDirectedEdge(1, 0, 1);
        test2.setDirectedEdge(2, 1, 1);
        test2.setDirectedEdge(3, 4, 1);
        test2.setDirectedEdge(4, 5, 1);
        test2.setDirectedEdge(5, 3, 1);

        System.out.println("test2GraphQuiz: ");
        System.out.println(test2.toString());
        System.out.println(test2.numberOfCycles());

        //test3
        GraphQuiz test3 = new GraphQuiz(5);
        test3.setDirectedEdge(0,1, 1);
        test3.setDirectedEdge(1, 2, 1);
        test3.setDirectedEdge(2, 0, 1);
        test3.setDirectedEdge(1, 3, 1);
        test3.setDirectedEdge(3, 4, 1);
        test3.setDirectedEdge(4, 2, 1);

        System.out.println("test3GraphQuiz: ");
        System.out.println(test3.toString());
        System.out.println(test3.numberOfCycles());

        //test4
        GraphQuiz test4 = new GraphQuiz(6);
        test4.setDirectedEdge(0,1, 1);
        test4.setDirectedEdge(1, 2, 1);
        test4.setDirectedEdge(2, 0, 1);
        test4.setDirectedEdge(1, 3, 1);
        test4.setDirectedEdge(3, 4, 1);
        test4.setDirectedEdge(4, 1, 1);
        test4.setDirectedEdge(4, 5, 1);
        test4.setDirectedEdge(5, 2, 1);
        test4.setDirectedEdge(2, 4, 1);

        System.out.println("test4GraphQuiz: ");
        System.out.println(test4.toString());
        System.out.println(test4.numberOfCycles());

        //test5
        GraphQuiz test5 = new GraphQuiz(5);
        test5.setDirectedEdge(0,1, 1);
        test5.setDirectedEdge(1, 2, 1);
        test5.setDirectedEdge(2, 3, 1);
        test5.setDirectedEdge(3, 0, 1);
        test5.setDirectedEdge(1, 0, 1);
        test5.setDirectedEdge(0, 3, 1);
        test5.setDirectedEdge(3, 2, 1);
        test5.setDirectedEdge(2, 1, 1);

        System.out.println("test5GraphQuiz: ");
        System.out.println(test5.toString());
        System.out.println(test5.numberOfCycles());

        //test6
        GraphQuiz test6 = new GraphQuiz(6);
        test6.setDirectedEdge(0,1, 1);
        test6.setDirectedEdge(0,4, 1);
        test6.setDirectedEdge(1, 2, 1);
        test6.setDirectedEdge(1, 4, 1);
        test6.setDirectedEdge(2, 0, 1);
        test6.setDirectedEdge(2, 5, 1);
        test6.setDirectedEdge(5, 4, 1);
        test6.setDirectedEdge(4, 2, 1);
        test6.setDirectedEdge(4, 3, 1);
        test6.setDirectedEdge(3, 0, 1);

        System.out.println("test6GraphQuiz: ");
        System.out.println(test6.toString());
        System.out.println(test6.numberOfCycles());


    }


}