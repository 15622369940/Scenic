package service.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import service.algorithm.*;
import service.datastructure.myArrayList;

/*
 * Graph:ͼ�࣬�洢ͼ��ȫ���ڵ㣨���㣩�ͱߣ�·����
 * ���⻹�����ǳ����õ��ڽӾ���
 */
public class Graph {
	private static myArrayList<ArcNode> nodes;// �ڵ��б�
	private static myArrayList<VNode> edges;// ���б�
	private int V;// �ڵ���
	private int E;// ����
	private int[][] adjMatrix;// �ڽӾ���
	private static Set<String> nodeSet = null;// ���������б�

	/* ��ʼ������ */
	public void iniAll() {
		String[] srcData = readIn();// ��edge.dat�ļ���ȡ�����ݣ�ÿ����Ϊ�����һ��
		String[] srcData2 = readNode();// ��node.dat�ļ���ȡ�������ݣ�ÿ����Ϊ�����һ��
		edges = iniEdges(srcData);// ��ʼ��ͼ��ı�
		Graph.nodeSet = findNode(srcData2);// �����ļ����ݣ��ҵ�ȫ�����������
		this.setV(nodeSet.size());// ����ͼ��V
		nodes = iniArc(nodeSet);// ����ͼ�Ľڵ��б�nodes��Ԫ��˳����nodeSet��һ�µģ�Ҳ��������ڽӾ���adjMatrix��Ԫ��˳����һ�µ�
		for (int i = 0; i < nodes.size(); i++) {
			findNext(nodes.get(i), srcData, nodeSet);
		} // ���þ�������ı���Ϣ
		adjMatrix = iniAdjMat(nodeSet);// �����ڽӾ���
		moreInfo();// �ḻ������Ϣ
		System.out.println("---��ʼ�����---");
	}

	public Object[] findShortest(String name1, String name2, int mode) {
		String preName = "";
		String nextName = "";
		if(mode == 1) {
			// ����������·��������Ϣ
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("��������Ҫ���ҵ�·������1");
			preName = scan.nextLine();
			System.out.println("��������Ҫ���ҵ�·������2");
			nextName = scan.nextLine();
		}else if(mode == 0) {
			preName = name1;
			nextName = name2;
		}

		myDijkstra myDijkstra = new myDijkstra();
		Object[] temp = myDijkstra.run(adjMatrix, nodeSet, preName, nextName);
		
		return temp;

	}

	/**
	 * չʾ��ǰȫ��������Ϣ
	 */
	public void showAll() {
		myArrayList<String> result = new myArrayList<String>();
		int i = 0;
		while (i < nodes.size()) {
			result.add(nodes.get(i).getName());
			result.add(nodes.get(i).getDes());
			result.add(nodes.get(i).getWelcome());
			result.add(nodes.get(i).getRest());
			result.add(nodes.get(i).getToilet());
			i++;
		}
		System.out.println("Total:" + i);
		System.out.println("[��ǰ����" + result.size() / 5 + "������]");
		System.out.println("���" + "\t" + "����" + "\t" + "��ӭ��" + "\t" + "��Ϣ��" + "\t" + "����" + "\t" + "���" + "\t");
		for (int j = 0; j < result.size(); j += 5) {
			System.out.print("No." + (j + 5) / 5 + "\t");
			System.out.print(result.get(j) + "\t");
			System.out.print(result.get(j + 2) + "\t");
			System.out.print((result.get(j + 3).equals("0") ? "��" : "��") + "\t");// ��Ԫ����ʽ
			System.out.print((result.get(j + 4).equals("0") ? "��" : "��") + "\t");
			System.out.print(result.get(j + 1) + "\t");
			System.out.println();
		}
	}

	/**
	 * �������ܣ��õ����ַ�����indexOf�������жϾ������ƺ��������Ƿ���ڹؼ���
	 * state����������ǵ�ǰ�����Ƿ��Ѿ����������������Ϊ���ƺͼ��ͬʱ���йؼ��ʶ����ظ����
	 * 
	 * @param �ؼ�������
	 * @return �������
	 */
	public myArrayList<String> search(String item) {
		myArrayList<String> result = new myArrayList<String>();// ���ؽ��
		int i = 0;

		while (i < nodes.size()) {
			int state = 0;// ������Ϊ���ƺͼ���ﶼ�йؼ��ֶ��ظ���ӡ
			if (nodes.get(i).getName().indexOf(item) != -1) {
				result.add(nodes.get(i).getName());
				result.add(nodes.get(i).getDes());
				result.add(nodes.get(i).getWelcome());
				result.add(nodes.get(i).getRest());
				result.add(nodes.get(i).getToilet());
				state = 1;
			}
			if (nodes.get(i).getDes().indexOf(item) != -1 && state == 0) {
				result.add(nodes.get(i).getName());
				result.add(nodes.get(i).getDes());
				result.add(nodes.get(i).getWelcome());
				result.add(nodes.get(i).getRest());
				result.add(nodes.get(i).getToilet());
			}
			i++;
		}

		return result;
	}

	/**
	 * ������-���ݻ�ӭ������-���������㷨
	 * 
	 * @return result ������
	 */
	public myArrayList<String[]> sort_welcome() {
		myArrayList<String[]> result = new myArrayList<String[]>();// ���ؽ��

		int i = 0;
		while (i < nodes.size()) {
			String[] tempNode = new String[5];
			tempNode[0] = nodes.get(i).getName();
			tempNode[1] = nodes.get(i).getDes();
			tempNode[2] = nodes.get(i).getWelcome();
			tempNode[3] = nodes.get(i).getRest();
			tempNode[4] = nodes.get(i).getToilet();
			result.add(tempNode);
			i++;
		}
		result = myInsertSort.InsertSort(result);
		return result;
	}

	/**
	 * ������-���ݲ�·��������ȣ�����-���������㷨
	 * 
	 * @return result ������
	 */
	public myArrayList<String[]> sort_degree() {
		myArrayList<String[]> result = new myArrayList<String[]>();// ���ؽ��

		int i = 0;
		while (i < nodes.size()) {
			String[] tempNode = new String[6];
			tempNode[0] = nodes.get(i).getName();
			tempNode[1] = nodes.get(i).getDes();
			tempNode[2] = nodes.get(i).getWelcome();
			tempNode[3] = nodes.get(i).getRest();
			tempNode[4] = nodes.get(i).getToilet();
			tempNode[5] = String.valueOf(nodes.get(i).getDegree());
			result.add(tempNode);
			i++;
		}
		result = myQuickSort.QuickSort(result);
		return result;
	}

	/* ��ȡ�ڵ��ļ� */
	private static String[] readNode() {
		File firstFile = new File("./src/main/java/service/node.dat");
		String[] srcData = new String[20];
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(firstFile), "utf-8"));
			String line = "";
			int i = 0;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				srcData[i] = line;
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("file is not fond");
		} catch (IOException e) {
			System.out.println("Read or write Exceptioned");
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return srcData;
	}

	// ����node.dat�ļ�������ݲ�ȫ�������Ϣ
	private static void moreInfo() {
		String[] srcData = readNode();

		int i = 0;
		while (i < nodes.size()) {
			String[] tempString = null;
			tempString = srcData[i].split("����");
			ArcNode currentNode = getNode(tempString[0]);
			currentNode.setName(tempString[0]);
			currentNode.setDes(tempString[1]);
			currentNode.setWelcome(tempString[2]);
			currentNode.setRest(tempString[3]);
			currentNode.setToilet(tempString[4]);
			i++;
		}
	}

	// moreInfo()�ĸ�����������λ��ǰ����
	private static ArcNode getNode(String name) {
		int i = 0;
		while (i < nodes.size()) {
			if (nodes.get(i).getName().equals(name)) {
				return nodes.get(i);
			}
			i++;
		}
		return null;
	}

	// ��ӡ�ڽӾ�����ⲿ�ӿ�
	public void printMatrix() {
		print(this.adjMatrix, nodeSet);
	}

	/* ��ӡ�ڽӾ��� */
	private static void print(int[][] adjMatrix, Set<String> nodeSet) {
		/* ��ӡ������ */
		System.out.print("\t");
		Iterator<String> it = nodeSet.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + "\t");
		}
		System.out.println();
		Object[] tempArr = nodeSet.toArray();// ת��Ϊ���鷽������ʹ��

		/* ��ӡ���� */
		for (int i = 0; i < nodes.size(); i++) {
			System.out.print(tempArr[i] + "\t");
			for (int j = 0; j < nodes.size(); j++) {
				System.out.print(adjMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/* ��ʼ���ڽӾ��� */
	public int[][] iniAdjMat(Set<String> nodeSet) {
		int size = nodeSet.size();
		int[][] adjMatrix = new int[size][size];

		/* ����û����ͨ�ߵĵ�֮��ľ���32767 */
		for (int i = 0; i < nodeSet.size(); i++) {
			for (int j = 0; j < nodeSet.size(); j++) {
				adjMatrix[i][j] = 32767;
			}
		}

		/* ������֮��ľ���0 */
		for (int i = 0; i < nodeSet.size(); i++) {
			for (int j = 0; j < nodeSet.size(); j++) {
				if (i == j)
					adjMatrix[i][j] = 0;
			}
		}

		/* ��������ͨ�ߵĵ�֮��ľ��� */
		for (int i = 0; i < nodeSet.size(); i++) {
			for (int j = 0; j < nodes.get(i).getNodes().size(); j++) {
				adjMatrix[i][nodes.get(i).getNodes().get(j).getIndex()] = nodes.get(i).getNodes().get(j).getDis();
			}
		}
		return adjMatrix;
	}

	/* �ҵ�ÿһ���������ͨ�� */
	private static void findNext(ArcNode tempNode, String[] srcData, Set<String> nodeSet) {
		int i = 0;
		while (srcData[i] != null) {
			String[] tempString = null;
			tempString = srcData[i].split("����");
			if (tempNode.getName().equals(tempString[0])) {
				tempNode.getNodes().add(new VNode(tempString[1], Integer.parseInt(tempString[2]), nodeSet));
			}
			if (tempNode.getName().equals(tempString[1])) {
				tempNode.getNodes().add(new VNode(tempString[0], Integer.parseInt(tempString[2]), nodeSet));
			}
			i++;
		}
		tempNode.setDegree(tempNode.getNodes().size());// ���ý��Ķ�
	}

	/* �����ļ���ʼ�� ArcNode �� ArrayList */
	private static myArrayList<ArcNode> iniArc(Set<String> nodeSet) {
		myArrayList<ArcNode> ArcList = new myArrayList<ArcNode>();
		Iterator<String> it = nodeSet.iterator();
		while (it.hasNext()) {
			ArcList.add(new ArcNode(it.next()));
		}
		return ArcList;
	}

	/* �����ļ���ʼ�� Graph �� edges */
	private static myArrayList<VNode> iniEdges(String[] srcData) {
		myArrayList<VNode> VList = new myArrayList<VNode>();
		int i = 0;
		while (srcData[i] != null) {
			String[] tempString = new String[2];
			tempString = srcData[i].split("����");
			VNode tempEdge = new VNode(tempString[0], tempString[1], Integer.parseInt(tempString[2]));
			VList.add(tempEdge);
			i++;
		}
		return VList;
	}

	/* չʾ���бߵ��ⲿ�ӿ� */
	public void showAllEdges() {
		printEdges();
	}

	/* չʾ���б� */
	private static void printEdges() {
		System.out.println("[��" + edges.size() + "����Ͻ��]");
		System.out.println("���" + "\t" + "����1" + "\t" + "����2" + "\t" + "����");
		for (int i = 0; i < edges.size(); i++) {
			System.out.print("No." + (i + 1) + "\t" + edges.get(i).getPreName() + "\t" + edges.get(i).getNextName()
					+ "\t" + edges.get(i).getDis());
			System.out.println();
		}
	}

	/* �����ļ� */
	private static String[] readIn() {
		File firstFile = new File("./src/main/java/service/edge.dat");
		String[] srcData = new String[20];
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(firstFile), "UTF-8"));
			String line = "";
			int i = 0;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				srcData[i] = line;
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("file is not fond");
		} catch (IOException e) {
			System.out.println("Read or write Exceptioned");
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return srcData;
	}

	/**
	 * ��ȡ�ҵ����о���
	 * 
	 * @param srcData
	 */
	private static Set<String> findNode(String[] srcData) {
		int i = 0;
		Set<String> nodeSet = new HashSet<String>();
		while (srcData[i] != null) {
			String[] tempString = null;
			tempString = srcData[i].split("����");
			nodeSet.add(tempString[0]);
			i++;
		}
		return nodeSet;
	}

	public int getV() {
		return this.V;
	}

	public void setV(int V) {
		this.V = V;
	}
	
	public int getE() {
		return this.E;
	}

	public void setE(int E) {
		this.E = E;
	}

	public myArrayList<ArcNode> getNodes() {
		return Graph.nodes;
	}

	public myArrayList<VNode> getEdges() {
		return Graph.edges;
	}

	public Set<String> getNodeSet() {
		return Graph.nodeSet;
	}

	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}
	
	public int[][] getAdjMatrix(){
		return this.adjMatrix;
	}

}
