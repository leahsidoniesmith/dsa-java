package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import org.junit.Test;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MinimumSpanningTreeTest {
    @Test
    public void testUndirected() {
       /*
        Graph graph = new Graph(5);
        graph.setUndirectedEdge(0, 1, 5 - 2);
        graph.setUndirectedEdge(0, 2, 5 - 1);
        graph.setUndirectedEdge(0, 3, 5 - 4);
        graph.setUndirectedEdge(0, 4, 5 - 3);
        graph.setUndirectedEdge(1, 2, 5 - 3);
        graph.setUndirectedEdge(2, 3, 5 - 1);
        graph.setUndirectedEdge(3, 4, 5 - 2);

        MST prim = new MSTPrim();
        MST kruskal = new MSTKruskal();
        SpanningTree tree;

        System.out.println("prim");
        tree = prim.getMinimumSpanningTree(graph);
        System.out.println(tree);
        System.out.println(tree.getTotalWeight());

        System.out.println("kruskals");
        tree = kruskal.getMinimumSpanningTree(graph);
        System.out.println(tree);
        System.out.println(tree.getTotalWeight());

        */

        //part 2 of the quiz
        System.out.println("part 2 of the quiz");

        Graph compare = new Graph(4);
        compare.setUndirectedEdge(0, 1, 4);
        compare.setUndirectedEdge(0, 2, 1);
        compare.setUndirectedEdge(0, 3, 2);
        compare.setUndirectedEdge(1, 3, 1);
        compare.setUndirectedEdge(1, 2, 3);
        compare.setUndirectedEdge(2, 3, 2);


        MST prim2 = new MSTPrim();
        MST kruskal2 = new MSTKruskal();
        SpanningTree tree2;

        System.out.println("Prim's:");
        tree2 = prim2.getMinimumSpanningTree(compare);
        System.out.println(tree2);
        System.out.println(tree2.getTotalWeight());

        System.out.println("Kruskal's:");
        tree2 = kruskal2.getMinimumSpanningTree(compare);
        System.out.println(tree2);
        System.out.println(tree2.getTotalWeight());

    }

    @Test
    public void testDirected() {
        Graph graph = new Graph(5);

        graph.setDirectedEdge(0, 1, 20);
        graph.setDirectedEdge(2, 1, 5);
        graph.setDirectedEdge(3, 1, 2);
        graph.setDirectedEdge(4, 1, 5);

        graph.setDirectedEdge(0, 2, 15);
        graph.setDirectedEdge(1, 2, 28);
        graph.setDirectedEdge(3, 2, 4);
        graph.setDirectedEdge(4, 2, 7);

        graph.setDirectedEdge(0, 3, 4);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 8);
        graph.setDirectedEdge(4, 3, 30);

        graph.setDirectedEdge(0, 4, 10);
        graph.setDirectedEdge(1, 4, 12);
        graph.setDirectedEdge(2, 4, 6);
        graph.setDirectedEdge(4, 4, 20);

        MSTEdmonds mst = new MSTEdmonds();
        SpanningTree tree = mst.getMinimumSpanningTree(graph);
        System.out.println(tree.toString());

        graph = new Graph(4);

        graph.setDirectedEdge(0, 1, 4);
        graph.setDirectedEdge(0, 2, 4);
        graph.setDirectedEdge(0, 3, 4);
        graph.setDirectedEdge(1, 3, 1);
        graph.setDirectedEdge(2, 1, 2);
        graph.setDirectedEdge(3, 2, 3);

        tree = mst.getMinimumSpanningTree(graph);
        System.out.println(tree.toString());
    }
}
