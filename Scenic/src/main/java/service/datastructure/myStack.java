package service.datastructure;

/*
 * myStack������ʵ�ֵ�Stack��
 * ����ͣ����ջ�����ڴ洢ͣ�����ڵĳ���������ö����˳���
 */
public class myStack<E> {

	private Object[] array;
	private int currentSize;
	private int maxSize;

	public myStack(int size) {
		this.array = new Object[size];
		this.maxSize = size;
		this.currentSize = -1;
	}

	/*ѹ��ջ��*/
	public void push(E item) {
		array[++currentSize] = item;
	}

	public boolean isEmpty() {
		return currentSize == -1;
	}

	public boolean isFull() {
		return currentSize == (maxSize - 1);
	}

	public int getSize() {
		return this.currentSize;
	}

	/*����ջ��Ԫ��*/
	@SuppressWarnings("unchecked")
	public E pop() {

		return (E) array[currentSize--];
	}
}
