package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import org.junit.Test;


import java.util.*;

public class MSTHWtest2 {
    @Test
    public void test(){
        MSTAll mst = new MSTAllHW();

        Graph graph1a = getGraph1a();
        System.out.println("graph1a");
        List<SpanningTree> list1a = mst.getMinimumSpanningTrees(graph1a);
        System.out.print(list1a);
        System.out.println();

        Graph graph2a = getGraph2a();
        System.out.println("graph2a");
        List<SpanningTree> list2a = mst.getMinimumSpanningTrees(graph2a);
        System.out.print(list2a);
        System.out.println();

        Graph graph3a = getGraph3a();
        System.out.println("graph3a");
        List<SpanningTree> list3a = mst.getMinimumSpanningTrees(graph3a);
        System.out.print(list3a);
        System.out.println();


        Graph graph3b = getGraph3b();
        System.out.println("graph3b");
        List<SpanningTree> list3b = mst.getMinimumSpanningTrees(graph3b);
        System.out.print(list3b);
        System.out.println();


        Graph graph3c = getGraph3c();
        System.out.println("graph3c");
        List<SpanningTree> list3c = mst.getMinimumSpanningTrees(graph3c);
        System.out.print(list3c);
        System.out.println();

        Graph graph4a = getGraph4a();
        System.out.println("graph4a");
        List<SpanningTree> list4a = mst.getMinimumSpanningTrees(graph4a);
        System.out.print(list4a);
        System.out.println();

        Graph graph4b = getGraph4b();
        System.out.println("graph4b");
        List<SpanningTree> list4b = mst.getMinimumSpanningTrees(graph4b);
        System.out.print(list4b);
        System.out.println();

        Graph graph4c = getGraph4c();
        System.out.println("graph4c");
        List<SpanningTree> list4c = mst.getMinimumSpanningTrees(graph4c);
        System.out.print(list4c);
        System.out.println();

        Graph graph5a = getGraph5a();
        System.out.println("graph5a");
        List<SpanningTree> list5a = mst.getMinimumSpanningTrees(graph5a);
        System.out.print(list5a);
        System.out.println();

        Graph graph5b = getGraph5b();
        System.out.println("graph5b");
        List<SpanningTree> list5b = mst.getMinimumSpanningTrees(graph5b);
        System.out.print(list5b);
        System.out.println();

        Graph graph1 = getGraph1();
        System.out.println("graph1");
        List<SpanningTree> list1 = mst.getMinimumSpanningTrees(graph1);
        System.out.print(list1);
        System.out.println(list1.size());
        System.out.println();

        Graph bonus = getBonus();
        System.out.println("bonus");
        List<SpanningTree> bonuslist = mst.getMinimumSpanningTrees(bonus);
        System.out.print(bonuslist);
        System.out.println(bonuslist.size());
        System.out.println();

       /* Graph house = getHouse();
        System.out.println("house");
        List<SpanningTree> listhouse = mst.getMinimumSpanningTrees(house);
        System.out.print(listhouse);
        System.out.println(listhouse.size());
        System.out.println();

        */
    }

    Graph getGraph1() {
        Graph graph = new Graph(4);

        graph.setUndirectedEdge(0, 1, 1);
        graph.setUndirectedEdge(0, 2, 1);
        graph.setUndirectedEdge(0, 3, 1);
        graph.setUndirectedEdge(1, 2, 1);
        graph.setUndirectedEdge(1, 3, 1);
        graph.setUndirectedEdge(2, 3, 1);

        return graph;
    }

    Graph getBonus(){
        Graph graph = new Graph(12);

        graph.setUndirectedEdge(0, 1, 1);
        graph.setUndirectedEdge(1, 2, 1);
        graph.setUndirectedEdge(2, 3, 1);
        graph.setUndirectedEdge(3, 4, 1);
        graph.setUndirectedEdge(4, 5, 1);
        graph.setUndirectedEdge(5, 7, 1);
        graph.setUndirectedEdge(7, 8, 1);
        graph.setUndirectedEdge(8, 9, 1);
        graph.setUndirectedEdge(9, 10, 1);
        graph.setUndirectedEdge(10, 11, 1);
        graph.setUndirectedEdge(11, 6, 1);
        graph.setUndirectedEdge(6, 1, 1);
        graph.setUndirectedEdge(6, 2, 1);
        graph.setUndirectedEdge(6, 3, 1);
        graph.setUndirectedEdge(6, 4, 1);
        graph.setUndirectedEdge(7, 4, 1);
        graph.setUndirectedEdge(7, 3, 1);
        graph.setUndirectedEdge(7, 2, 1);
        graph.setUndirectedEdge(7, 1, 1);

        return graph;
    }

    Graph getHouse() {
        Graph quizgraph = new Graph(8);

        quizgraph.setUndirectedEdge(0, 1, 1.0);
        quizgraph.setUndirectedEdge(0, 2, 1.0);
        quizgraph.setUndirectedEdge(0, 3, 1.0);
        quizgraph.setUndirectedEdge(1, 2, 1.0);
        quizgraph.setUndirectedEdge(1, 3, 1.0);
        quizgraph.setUndirectedEdge(2, 3, 1.0);
        quizgraph.setUndirectedEdge(1, 7, 1.0);
        quizgraph.setUndirectedEdge(0, 7, 1.0);
        quizgraph.setUndirectedEdge(0, 4, 1.0);
        quizgraph.setUndirectedEdge(3, 4, 1.0);
        quizgraph.setUndirectedEdge(1, 6, 1.0);
        quizgraph.setUndirectedEdge(6, 2, 1.0);
        quizgraph.setUndirectedEdge(5, 3, 1.0);
        quizgraph.setUndirectedEdge(5, 2, 1.0);

        return quizgraph;
    }

    Graph getGraph1a() {
        return new Graph(1);
    }

    Graph getGraph2a() {
        return new Graph(2);
    }

    Graph getGraph3a() {
        Graph graph = new Graph(3);

        graph.setUndirectedEdge(0, 1, 1);
        graph.setUndirectedEdge(0, 2, 1);
        graph.setUndirectedEdge(1, 2, 2);

        return graph;
    }

    Graph getGraph3b() {
        Graph graph = new Graph(3);

        graph.setUndirectedEdge(0, 1, 2);
        graph.setUndirectedEdge(0, 2, 2);
        graph.setUndirectedEdge(1, 2, 1);

        return graph;
    }

    Graph getGraph3c() {
        Graph graph = new Graph(3);

        graph.setUndirectedEdge(0, 1, 1);
        graph.setUndirectedEdge(0, 2, 2);
        graph.setUndirectedEdge(1, 2, 3);

        return graph;
    }

    Graph getGraph4a() {
        Graph graph = new Graph(4);

        graph.setUndirectedEdge(0, 1, 2);
        graph.setUndirectedEdge(0, 3, 1);
        graph.setUndirectedEdge(1, 2, 1);
        graph.setUndirectedEdge(2, 3, 2);

        return graph;
    }

    Graph getGraph4b() {
        Graph graph = new Graph(4);

        graph.setUndirectedEdge(0, 1, 3);
        graph.setUndirectedEdge(0, 3, 3);
        graph.setUndirectedEdge(1, 2, 1);
        graph.setUndirectedEdge(1, 3, 2);
        graph.setUndirectedEdge(2, 3, 2);

        return graph;
    }

    Graph getGraph4c() {
        Graph graph = new Graph(4);

        graph.setUndirectedEdge(0, 1, 1);
        graph.setUndirectedEdge(0, 2, 2);
        graph.setUndirectedEdge(0, 3, 3);
        graph.setUndirectedEdge(1, 2, 3);
        graph.setUndirectedEdge(1, 3, 2);
        graph.setUndirectedEdge(2, 3, 1);

        return graph;
    }

    Graph getGraph5a() {
        Graph graph = new Graph(5);

        graph.setUndirectedEdge(0, 1, 3);
        graph.setUndirectedEdge(0, 2, 4);
        graph.setUndirectedEdge(0, 3, 1);
        graph.setUndirectedEdge(0, 4, 2);
        graph.setUndirectedEdge(1, 2, 2);
        graph.setUndirectedEdge(2, 3, 4);
        graph.setUndirectedEdge(3, 4, 3);

        return graph;
    }

    Graph getGraph5b() {
        Graph graph = new Graph(5);

        graph.setUndirectedEdge(0, 1, 3);
        graph.setUndirectedEdge(0, 2, 4);
        graph.setUndirectedEdge(0, 3, 1);
        graph.setUndirectedEdge(0, 4, 1);
        graph.setUndirectedEdge(1, 2, 3);
        graph.setUndirectedEdge(1, 3, 5);
        graph.setUndirectedEdge(1, 4, 4);
        graph.setUndirectedEdge(2, 3, 1);
        graph.setUndirectedEdge(2, 4, 1);
        graph.setUndirectedEdge(3, 4, 3);

        return graph;
    }
}
