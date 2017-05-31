package com.pty.graph;

public class Test {
	public static void main(String[] args) {
		Graph<String> graph = new Graph<String>(true);
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.addEdges("A", "B");
		graph.addEdges("A", "D");
		graph.addEdges("B", "C");
		graph.addEdges("B", "D");
		graph.addEdges("C", "E");
		graph.addEdges("D", "C");
		graph.addEdges("D", "E");
		graph.displayGraph();
		graph.BFS("A");
		graph.DFS("A");
	}
}
