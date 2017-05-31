package com.pty.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Graph<T> {
	private LinkedHashMap<T, Vertex> vertexs;// 使用LinkedHashMap存放图中的结点，结点标识T作为key能快速查找图中是否包含某结点
	private LinkedHashMap<T, ArrayList<Vertex>> adjList; // 采用邻接链表的方式表示图，每个结点T对应于一个ArrayList链表，存放其相邻结点。
	private int numOfVertex; // 图的结点数
	private int numOfEdge; // 图的边数
	private boolean directed; // 是否为有向图

	public Graph(boolean directed) {
		vertexs = new LinkedHashMap<T, Vertex>();
		numOfVertex = 0;
		adjList = new LinkedHashMap<T, ArrayList<Vertex>>();
		this.directed = directed;
	}

	public LinkedHashMap<T, Vertex> getVertexs() {
		return vertexs;
	}

	public void setVertexs(LinkedHashMap<T, Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public LinkedHashMap<T, ArrayList<Vertex>> getAdjList() {
		return adjList;
	}

	public void setAdjList(LinkedHashMap<T, ArrayList<Vertex>> adjList) {
		this.adjList = adjList;
	}

	public int getNumOfVertex() {
		return numOfVertex;
	}

	public void setNumOfVertex(int numOfVertex) {
		this.numOfVertex = numOfVertex;
	}

	public int getNumOfEdge() {
		return numOfEdge;
	}

	public void setNumOfEdge(int numOfEdge) {
		this.numOfEdge = numOfEdge;
	}

	/**
	 * 插入结点 tag
	 * 
	 * @param tag
	 *            结点标识
	 */
	public void insertVertex(T tag) {
		vertexs.put(tag, new Vertex(tag)); // 新增结点 tag，如果定点中存在该结点将会被替换最新的
		adjList.put(tag, new ArrayList<Vertex>()); // 每新增一个结点，将会创建一个ArrayList列表，用于存放新增结点的邻接结点。
		numOfVertex++; // 结点数自增
	}

	/**
	 * 添加一条边
	 * 
	 * @param start
	 *            边的起点
	 * @param end
	 *            边的终点
	 * @param directed
	 *            边是否有向
	 * @return 如果输入起始点合法则操作成功，否则失败
	 */
	public boolean addEdges(T start, T end) {
		if (vertexs.containsKey(start) && vertexs.containsKey(end)) { // 判断输入起始点是否合法
			adjList.get(start).add(vertexs.get(end)); // 首先获取结点start的链表，然后将其邻接结点end加入其中
			if (!directed) { // 如果是无向图，则添加结束点到开始点的边
				adjList.get(end).add(vertexs.get(start));
			}
		} else {
			System.out.println("输入结点不合法");
			return false;
		}
		return true;
	}

	/**
	 * 打印图的邻接链表
	 */
	public void displayGraph() {
		System.out.println("邻接链表表示如下：");
		Iterator<Map.Entry<T, ArrayList<Vertex>>> iterator = adjList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<T, ArrayList<Vertex>> element = iterator.next();
			System.out.print(element.getKey() + ":");
			displayList(element.getValue());
		}
	}

	/**
	 * 打印链表ArrayList
	 * 
	 * @param list
	 */
	public void displayList(ArrayList<Vertex> list) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.print(list.get(i).getLable());
			if (i < size - 1) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}

	/**
	 * 给定源点 start，打印图的广度优先遍历序列
	 * 
	 * @param start
	 *            源点
	 */
	public void BFS(T start) {
		ArrayList<T> list = new ArrayList<T>(); // 存放遍历结点数列
		LinkedList<Vertex> tempList = new LinkedList<Vertex>(); // 辅助BFS的队列
		initialize(); // 初始化所有结点访问属性为false
		tempList.add(vertexs.get(start));
		while (!tempList.isEmpty()) {
			Vertex node = tempList.poll(); // 弹出队列头
			if (!node.isVisited()) {
				node.setVisited(true);
				ArrayList<Vertex> neighbor = adjList.get(node.getLable()); // 获取结点node的邻接表
				int size = neighbor.size();
				for (int i = 0; i < size; i++) {
					if ((!neighbor.get(i).isVisited()) && (!tempList.contains(neighbor.get(i)))) {
						tempList.offer(neighbor.get(i)); // 将未被访问且不包含在辅助BFS队列中的结点存入队列
					}
				}
				list.add(node.getLable());
			}

		}
		/**
		 * 输出遍历序列
		 */
		System.out.print("广度优先：");
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.print(list.get(i));
			if (i < size - 1) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}

	/**
	 * 给定源点 start，打印图的深度优先遍历序列
	 * 
	 * @param start
	 *            源点
	 */
	public void DFS(T start) {
		ArrayList<T> list = new ArrayList<T>(); // 存放遍历结点数列
		Stack<Vertex> stack = new Stack<Vertex>(); // 辅助DFS栈
		initialize(); // 初始化所有结点访问属性为false
		stack.push(vertexs.get(start));
		while (!stack.isEmpty()) {
			Vertex node = stack.pop(); //弹出栈顶元素
			if (!node.isVisited()) {
				node.setVisited(true);
				ArrayList<Vertex> neighbor = adjList.get(node.getLable()); // 获取结点node的邻接表
				int size = neighbor.size();
				for (int i = 0; i < size; i++) {
					if ((!neighbor.get(i).isVisited()) && (!stack.contains(neighbor.get(i)))) // 将未被访问且不包含在辅助DFS栈中的结点压入栈
						stack.push(neighbor.get(i));
				}
				list.add(node.getLable());
			}
		}
		System.out.print("深度优先：");
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.print(list.get(i));
			if (i < size - 1) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}

	/**
	 * 初始化结点属性为未访问
	 */
	public void initialize() {
		Iterator<Map.Entry<T, Vertex>> iterator = vertexs.entrySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().getValue().setVisited(false);
		}
	}

	/**
	 * 内部类，表示结点
	 * 
	 * @author john
	 *
	 */
	public class Vertex {
		private T lable; // 标识结点，比如A0，A1，A2,...或者1，2，3，...
		private boolean visited; // 标识结点是否被访问

		public Vertex(T tag) {
			lable = tag;
			visited = false;
		}

		public T getLable() {
			return lable;
		}

		public void setLable(T lable) {
			this.lable = lable;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	}
}
