/**
 * 
 */package service.algorithm;

import service.datastructure.myArrayList;

/*
 * myInsertSort:���ÿ�������Ծ��㰴�ղ�·����������
 */
public class myQuickSort {
	/**
	 * sort_degree����������ʵ���˿�������
	 * 
	 * @param result ȫ��������Ϣ
	 * @return ������
	 */
	public static myArrayList<String[]> QuickSort(myArrayList<String[]> result) {
		if(result.size()>0) {  
            quick(result, 0 , result.size()-1);  
        }  
		return result;

	}
	/**
	 * QuickSort��������
	 * 
	 * @param result ȫ��������Ϣ
	 * @param i ѭ������
	 */
	private static void quick(myArrayList<String[]> result, int low, int high) {
		if( low > high) {  
            return;  
        }  
        int i = low;  
        int j = high;  
        int key = Integer.parseInt(result.get(low)[5]);  
        while( i< j) {  
            while(i<j && Integer.parseInt(result.get(j)[5]) > key){  
                j--;  
            }  
            while( i<j && Integer.parseInt(result.get(i)[5]) <= key) {  
                i++;  
            }  
            if(i<j) {
        		String[] temp = new String[6];
        		System.arraycopy(result.get(i), 0, temp, 0, 6);
        		System.arraycopy(result.get(j), 0, result.get(i), 0, 6);
        		System.arraycopy(temp, 0, result.get(j), 0, 6);
            }  
        }
        String[] temp = new String[6];
        System.arraycopy(result.get(i), 0, temp, 0, 6);
        System.arraycopy(result.get(low), 0, result.get(i), 0, 6);
        System.arraycopy(temp, 0, result.get(low), 0, 6);
        quick(result, low, i-1 );  
        quick(result, i+1, high);  
	}
}
