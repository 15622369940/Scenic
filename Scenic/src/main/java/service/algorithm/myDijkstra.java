/**
 * 
 */
package service.algorithm;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/*
 * myDijkstra:���õϽܿ�˹���㷨ʵ�����·��������
 */
public class myDijkstra {
	private static String pathTip = "-->";// ·�����
	private static Map<String, Object> pathInfoMap = new LinkedHashMap<String, Object>(); // ·���;�����Ϣ

	public Object[] run(int[][] adjMatrix, Set<String> nodeSet, String preName, String nextName) {
		/* �����㼯��ת��Ϊ���飬����������� */
		String[] point = new String[nodeSet.size()];
		Iterator<String> it = nodeSet.iterator();
		int i = 0;
		while (it.hasNext()) {
			point[i] = it.next();
			i++;
		}
		/* ��ʼ��ȡ���·��Ϣ */
		for (int start = 0; start < point.length; start++) {
			getPathInfo(adjMatrix, start, point);
		}
		/* ��ӡ������·�� */
		Object[] temp = printPathInfo(preName, nextName);
		return temp;

	}

	public static void getPathInfo(int[][] adjMatrix, int start, String[] point) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		int n = adjMatrix.length; // �������
		int[] shortPath = new int[n]; // ����start��������������·��
		String[] path = new String[n]; // ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++) {
			path[i] = new String(point[start] + pathTip + point[i]);
		}
		int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����
		// ��ʼ������һ�������Ѿ����
		shortPath[start] = 0;
		visited[start] = 1;
		for (int count = 1; count < n; count++) { // Ҫ����n-1������
			int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && adjMatrix[start][i] < dmin) {
					dmin = adjMatrix[start][i];
					k = i;
				}
			}
			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;
			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && adjMatrix[start][k] + adjMatrix[k][i] < adjMatrix[start][i]) {
					adjMatrix[start][i] = adjMatrix[start][k] + adjMatrix[k][i];
					path[i] = path[k] + pathTip + point[i];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			Object[] objects = new Object[2];
			objects[0] = path[i];
			objects[1] = shortPath[i];
			pathInfoMap.put(point[start] + pathTip + point[i], objects);
		}

	}

	/**
	 * ��ӡ·����Ϣ�;���
	 */
	private static Object[] printPathInfo(String preName, String nextName) {
		Object[] temp = new String[2];
		for (Entry<String, Object> entry : pathInfoMap.entrySet()) {
			String key = entry.getKey();
			Object[] objects = (Object[]) entry.getValue();
			if (key.indexOf(preName) != -1 && key.indexOf(nextName) != -1
					&& key.indexOf(preName) < key.indexOf(nextName)) {
				System.out.println(key + ":" + objects[0] + "  ·�����ȣ�" + objects[1]);
				temp[0] = objects[0];
				temp[1] = objects[1].toString();
			}
		}
		pathInfoMap.clear();// ���map
		return temp;
	}
}
