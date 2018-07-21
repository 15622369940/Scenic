package service.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * myArrayList:����ʵ�ֵ�ArrayList��
 * ʵ����Java���ͣ�Ӧ���ڴ洢����ArcNode��·��VNode��
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class myArrayList<T> extends ArrayList {
	private Object[] elementData;// ���Object������
	private int size;// ArrayList��С

	public myArrayList() {
		this(10);// �����ָ����С��Ĭ�ϴ�СΪ10
	}

	public myArrayList(int initSize) {
		if (initSize < 0) {
			throw new IllegalArgumentException("IllegalArgument:" + initSize);// �������Ϸ����׳��쳣
		}
		elementData = new Object[initSize];
	}

	public void add(T obj) {// ��ĩβ����Ԫ��
		checkCapacity(size + 1);// ����һ��Ԫ�أ�������Ҫsize+1��С�Ŀռ�
		elementData[this.size++] = obj;
	}

	public void add(int index, T obj) {// ��index������Ԫ��obj
		RangeCheck(index);
		checkCapacity(size + 1);// ����һ��Ԫ�أ�������Ҫsize+1��С�Ŀռ�
		System.arraycopy(elementData, index, elementData, index + 1, size - index);// ��index���Ԫ�ض�����һ��λ��
		elementData[index] = obj;
		size++;
	}

	private void checkCapacity(int needCapacity) {// ���ArrayList���ٵĿռ��Ƿ��㹻������������������
		if (needCapacity > elementData.length) {// �ռ䲻�㣬����
			Object oldelementData[] = elementData;
			int newSize = this.size * 2 + 1;// ���ݵĿռ�
			elementData = new Object[newSize];
			elementData = Arrays.copyOf(oldelementData, newSize);
		}
	}

	private void RangeCheck(int index) {// ��������Ƿ�Ϸ�
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("IllegalArgument" + index);// ���磬�׳��쳣
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {// �����������ض���
		RangeCheck(index);
		return (T) elementData[index];
	}

	@SuppressWarnings("unchecked")
	public T set(int index, T obj) {// ��indexλ����Ϊobj
		RangeCheck(index);
		T oldvalue = (T) elementData[index];
		elementData[index] = obj;// ������ֵ
		return oldvalue;// ���ؾ�ֵ
	}

	@SuppressWarnings("unchecked")
	public T remove(int index) {// ɾ��index����Ԫ��
		RangeCheck(index);
		T oldValue = (T) elementData[index];
		int moveNum = size - index - 1;
		if (moveNum > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, moveNum);
		}
		elementData[--size] = null;// ����������������
		return oldValue;// ���ؾ�ֵ
	}

	public boolean remove(T obj) {// ɾ������obj��Ԫ�أ��ɹ�����true��ʧ�ܷ���false
		if (obj == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					fastremove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (obj.equals(elementData[i])) {// obj��Ϊnull,һ��Ҫ��ǰ������ʹ��elementData[i]����equals()���ܵ��¿������쳣
					fastremove(i);
					return true;
				}
			}
		}

		return false;
	}

	public void fastremove(int index) {
		int moveNum = size - index - 1;
		if (moveNum > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, moveNum);
		}
		elementData[--size] = null;// ����������������
	}

	public int size() {
		return this.size;
	}

	public int length() {
		return elementData.length;
	}
}
