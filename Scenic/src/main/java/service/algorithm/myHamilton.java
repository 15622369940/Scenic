/**
 * 
 */
package service.algorithm;

import service.graph.Graph;

/*
 * myHamilton:������С�������㷨���Dijkstra�㷨Ѱ�ҹ��ܶ���ͼ
 */
public class myHamilton {
	private static String temp;
	public static String findCircle(int[][] adjMatrix, Graph myGraph) {

		int n = adjMatrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjMatrix[i][j] = (adjMatrix[i][j] == 32767) ? -1 : 1;
			}
		}
		getHamiltonCircuit(adjMatrix, myGraph);
		return myHamilton.temp;
	}

	/*
	 * ����adjMatrix������ͼ���ڽӾ�������ֵΪ1��ʾ�������������ͨ��ֵΪ-1��ʾ�������㲻����ͨ
	 */
	public static void getHamiltonCircuit(int[][] adjMatrix, Graph myGraph) {
		boolean[] used = new boolean[adjMatrix.length]; // ���ڱ��ͼ�ж����Ƿ񱻷���
		int[] path = new int[adjMatrix.length]; // ��¼���ܶٻ�··��
		for (int i = 0; i < adjMatrix.length; i++) {
			used[i] = false; // ��ʼ�������ж����δ������
			path[i] = -1; // ��ʼ����δѡ����㼰�����κζ���
		}
		used[0] = true; // ��ʾ�ӵ�1�����㿪ʼ����
		path[0] = 0; // ��ʾ���ܶٻ�·���Ϊ��0������
		dfs(adjMatrix, path, used, 1, myGraph); // �ӵ�0�����㿪ʼ����������ȱ���,������ڹ��ܶٻ�·�����һ����·�����������
	}

	/*
	 * ����step:��ǰ���ߵĲ��������Ѿ���������ĸ���
	 */
	public static boolean dfs(int[][] adjMatrix, int[] path, boolean[] used, int step, Graph myGraph) {
		if (step == adjMatrix.length) { // ���Ѿ�������ͼ�����ж���
			if (adjMatrix[path[step - 1]][0] == 1) { // ���һ������Ķ����ܹ��ص����
				for (int i = 0; i < path.length; i++)
					System.out.print(((char) (path[i] + 'a')) + "����>");
				System.out.print(((char) (path[0] + 'a')));
				System.out.println();
				return true;
			}
			return false;
		} else {
			for (int i = 0; i < adjMatrix.length; i++) {
				if (!used[i] && adjMatrix[path[step - 1]][i] == 1) {
					used[i] = true;
					path[step] = i;
					if (dfs(adjMatrix, path, used, step + 1, myGraph))
						return true;
					else {
						used[i] = false; // ���л��ݴ���
						path[step] = -1;
					}
				}

				if (path[11] != -1) {
					step = adjMatrix.length;
					printPath(path, myGraph);
					break;
				}
			}
		}
		return false;
	}

	private static void printPath(int[] path, Graph myGraph) {
		String temp = "";
		path[12] = 2;
		for (int i = 0; i < path.length; i++) {
			if(i == path.length-1) {
				System.out.print(myGraph.getNodes().get(path[i]).getName());
				temp += myGraph.getNodes().get(path[i]).getName();
			}else {
				System.out.print(myGraph.getNodes().get(path[i]).getName() + "-->");
				temp += myGraph.getNodes().get(path[i]).getName() + "-->";
			}
		}
		//System.out.print(((char) (path[0] + 'a')));
		System.out.println();
		myHamilton.setTemp(temp);
	}
	
	public static void setTemp(String temp) {
		myHamilton.temp = temp;
	}
}
