package com.pty.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Graph<T> {
	private LinkedHashMap<T, Vertex> vertexs;// ʹ��LinkedHashMap���ͼ�еĽ�㣬����ʶT��Ϊkey�ܿ��ٲ���ͼ���Ƿ����ĳ���
	private LinkedHashMap<T, ArrayList<Vertex>> adjList; // �����ڽ�����ķ�ʽ��ʾͼ��ÿ�����T��Ӧ��һ��ArrayList������������ڽ�㡣
	private int numOfVertex; // ͼ�Ľ����
	private int numOfEdge; // ͼ�ı���
	private boolean directed; // �Ƿ�Ϊ����ͼ

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
	 * ������ tag
	 * 
	 * @param tag
	 *            ����ʶ
	 */
	public void insertVertex(T tag) {
		vertexs.put(tag, new Vertex(tag)); // ������� tag����������д��ڸý�㽫�ᱻ�滻���µ�
		adjList.put(tag, new ArrayList<Vertex>()); // ÿ����һ����㣬���ᴴ��һ��ArrayList�б����ڴ�����������ڽӽ�㡣
		numOfVertex++; // ���������
	}

	/**
	 * ���һ����
	 * 
	 * @param start
	 *            �ߵ����
	 * @param end
	 *            �ߵ��յ�
	 * @param directed
	 *            ���Ƿ�����
	 * @return ���������ʼ��Ϸ�������ɹ�������ʧ��
	 */
	public boolean addEdges(T start, T end) {
		if (vertexs.containsKey(start) && vertexs.containsKey(end)) { // �ж�������ʼ���Ƿ�Ϸ�
			adjList.get(start).add(vertexs.get(end)); // ���Ȼ�ȡ���start������Ȼ�����ڽӽ��end��������
			if (!directed) { // ���������ͼ������ӽ����㵽��ʼ��ı�
				adjList.get(end).add(vertexs.get(start));
			}
		} else {
			System.out.println("�����㲻�Ϸ�");
			return false;
		}
		return true;
	}

	/**
	 * ��ӡͼ���ڽ�����
	 */
	public void displayGraph() {
		System.out.println("�ڽ������ʾ���£�");
		Iterator<Map.Entry<T, ArrayList<Vertex>>> iterator = adjList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<T, ArrayList<Vertex>> element = iterator.next();
			System.out.print(element.getKey() + ":");
			displayList(element.getValue());
		}
	}

	/**
	 * ��ӡ����ArrayList
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
	 * ����Դ�� start����ӡͼ�Ĺ�����ȱ�������
	 * 
	 * @param start
	 *            Դ��
	 */
	public void BFS(T start) {
		ArrayList<T> list = new ArrayList<T>(); // ��ű����������
		LinkedList<Vertex> tempList = new LinkedList<Vertex>(); // ����BFS�Ķ���
		initialize(); // ��ʼ�����н���������Ϊfalse
		tempList.add(vertexs.get(start));
		while (!tempList.isEmpty()) {
			Vertex node = tempList.poll(); // ��������ͷ
			if (!node.isVisited()) {
				node.setVisited(true);
				ArrayList<Vertex> neighbor = adjList.get(node.getLable()); // ��ȡ���node���ڽӱ�
				int size = neighbor.size();
				for (int i = 0; i < size; i++) {
					if ((!neighbor.get(i).isVisited()) && (!tempList.contains(neighbor.get(i)))) {
						tempList.offer(neighbor.get(i)); // ��δ�������Ҳ������ڸ���BFS�����еĽ��������
					}
				}
				list.add(node.getLable());
			}

		}
		/**
		 * �����������
		 */
		System.out.print("������ȣ�");
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
	 * ����Դ�� start����ӡͼ��������ȱ�������
	 * 
	 * @param start
	 *            Դ��
	 */
	public void DFS(T start) {
		ArrayList<T> list = new ArrayList<T>(); // ��ű����������
		Stack<Vertex> stack = new Stack<Vertex>(); // ����DFSջ
		initialize(); // ��ʼ�����н���������Ϊfalse
		stack.push(vertexs.get(start));
		while (!stack.isEmpty()) {
			Vertex node = stack.pop(); //����ջ��Ԫ��
			if (!node.isVisited()) {
				node.setVisited(true);
				ArrayList<Vertex> neighbor = adjList.get(node.getLable()); // ��ȡ���node���ڽӱ�
				int size = neighbor.size();
				for (int i = 0; i < size; i++) {
					if ((!neighbor.get(i).isVisited()) && (!stack.contains(neighbor.get(i)))) // ��δ�������Ҳ������ڸ���DFSջ�еĽ��ѹ��ջ
						stack.push(neighbor.get(i));
				}
				list.add(node.getLable());
			}
		}
		System.out.print("������ȣ�");
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
	 * ��ʼ���������Ϊδ����
	 */
	public void initialize() {
		Iterator<Map.Entry<T, Vertex>> iterator = vertexs.entrySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().getValue().setVisited(false);
		}
	}

	/**
	 * �ڲ��࣬��ʾ���
	 * 
	 * @author john
	 *
	 */
	public class Vertex {
		private T lable; // ��ʶ��㣬����A0��A1��A2,...����1��2��3��...
		private boolean visited; // ��ʶ����Ƿ񱻷���

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
