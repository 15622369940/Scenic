/**
 * 
 */package service.algorithm;

import service.datastructure.myArrayList;

/*
 * myInsertSort:���ò�������Ծ��㰴�ջ�ӭ�Ƚ�������
 */
public class myInsertSort {
	/**
	 * sort_welcome����������ʵ���˲�������
	 * 
	 * @param result ȫ��������Ϣ
	 * @return ������
	 */
	public static myArrayList<String[]> InsertSort(myArrayList<String[]> result) {
		int n = result.size();// ����
		int i;

		for (i = 1; i < n; i++) {
			insert(result, i);
		}
		return result;
	}

	/**
	 * InsertSort��������
	 * 
	 * @param result ȫ��������Ϣ
	 * @param i ѭ������
	 */
	private static void insert(myArrayList<String[]> result, int i) {
		String[] target = new String[5];
		System.arraycopy(result.get(i), 0, target, 0, 5);

		int j = i;
		while (Integer.parseInt(result.get(j - 1)[2]) > Integer.parseInt(target[2])) {
			System.arraycopy(result.get(j - 1), 0, result.get(j), 0, 5);
			j--;
			if (j == 0)
				break;
		}
		System.arraycopy(target, 0, result.get(j), 0, 5);
	}

}
