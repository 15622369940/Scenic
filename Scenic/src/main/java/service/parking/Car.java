package service.parking;

/*
 * Car:���������Ϣ��
 */
public class Car {

	private Car next;// ָ����һ����������
	private int number; // ���ƺ�
	private String arriveTime; // ����ʱ��

	public Car(int num, String time) {
		this.number = num;
		this.arriveTime = time;
		this.next = null;

	}

	public Car getNext() {
		return next;
	}

	public void setNext(Car next) {
		this.next = next;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

}
