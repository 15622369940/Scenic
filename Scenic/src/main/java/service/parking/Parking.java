package service.parking;

import java.util.Scanner;

import service.datastructure.myQueue;
import service.datastructure.myStack;

/*
 * Parking:ͣ����ϵͳ������
 * �����������������ͣ��λ���㡢�˳��������ȡ�����ͣ���ѵ�����
 */
public class Parking {

	private myStack<Car> enterStack;// ��Ž��������
	private myStack<Car> exitStack;// ��Żع��˳�������
	private myQueue tempParking;// ���
	private int costPerHour;// ÿСʱ�ļ۸񣨳���һСʱ��һСʱ�㣩
	private String timeRegix;// ʱ���������ʽ

	public Parking(int size, int costPerHour) {
		this.enterStack = new myStack<Car>(size);
		this.exitStack = new myStack<Car>(size);
		this.tempParking = new myQueue();
		this.costPerHour = costPerHour;
		this.timeRegix = "[0-2][0-9]+:[0-6][0-9]";
	}

	public String carEnter(int tempNum, String tempTime, int mode) {
		String temp = "";
		int num = 0;
		String arvTime = "";
		if (mode == 1) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("����Ϊ��");
			num = scan.nextInt();
			System.out.print("����ʱ��(xx:xx)��");
			arvTime = scan.next();
		} else {
			num = tempNum;
			arvTime = tempTime;
		}
		while (!arvTime.matches(timeRegix)) {// ������ʽ�жϸ�ʽ����
			System.out.print("�밴�ո�ʽ����(xx:xx)��");
			temp += "�밴�ո�ʽ����(xx:xx)��";
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			arvTime = scan.next();
		}

		if (!tempParking.isEmpty() || enterStack.isFull()) {
			// ���������ǿյĻ���ͣ�������ˣ��������ĳ��ͷ��ڱ����
			System.out.println("ͣ��������!������ʱ�����ϵȺ�!");
			temp += "ͣ��������!������ʱ�����ϵȺ�!";
			tempParking.enQueue(new Car(num, arvTime));
		} else {
			// ���ͣ����û�������ұ��ҲΪ�գ��������ĳ�ֱ�ӿ���ͣ����
			enterStack.push(new Car(num, arvTime));
			System.out.println("�ó��ѽ���ͣ������" + enterStack.getSize() + "�ų�����");
			temp += "�ó��ѽ���ͣ������" + enterStack.getSize() + "�ų�����";
		}
		return temp;
	}

	public String carExit(int tempNum, String tempTime, int mode) {
		System.out.println("����������2");
		String temp = "";
		int num = 0;
		String levTime = "";
		if (mode == 1) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("����Ϊ��");
			num = scan.nextInt();
			System.out.print("����ʱ�̣�");
			levTime = scan.next();
		} else {
			num = tempNum;
			levTime = tempTime;
		}

		while (!levTime.matches(timeRegix)) {// �������ʱ�����������
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("�밴�ո�ʽ����(xx:xx)��" + levTime);
			temp += "�밴�ո�ʽ����(xx:xx)��";
			levTime = scan.next();
		}
		boolean flag = true;
		int size = enterStack.getSize();

		while (flag && !enterStack.isEmpty()) {
			/*
			 * �г�Ҫ��������ôͣ����ǰ��ĳ����ν��� exitStack��ֱ���ó���ȥ
			 */
			Car car = enterStack.pop();
			if (car.getNumber() == num) {
				System.out.println(car.getNumber() + "���˳���");
				temp += car.getNumber() + "���˳���";
				System.out.println("��ȡ���ã�" + cost(car.getArriveTime(), levTime));
				temp += "��ȡ���ã�" + cost(car.getArriveTime(), levTime);
				flag = false;
			} else {
				exitStack.push(car);
			}
		}
		while (!exitStack.isEmpty()) {
			Car car = exitStack.pop();
			enterStack.push(car);
		}

		// ͨ�������ڳ���ǰ�������仯
		// ���ж��Ƿ��ҵ�����
		if (size == enterStack.getSize()) {
			System.out.println("������ĳ��Ʋ�����!");
			temp += "������ĳ��Ʋ�����!";
			return temp;
		}

		// ���������г���������복��
		if (!tempParking.isEmpty()) {
			Car car = tempParking.deQueue();
			car.setArriveTime(levTime);
			enterStack.push(car);
			System.out.println(car.getNumber() + "�ѽ�����");
			temp += car.getNumber() + "�ѽ�����";
		}
		return temp;
	}

	public int cost(String arvTime, String levTime) {
		int cost = 0;
		int arvH = Integer.parseInt(arvTime.split(":")[0]);
		int levH = Integer.parseInt(levTime.split(":")[0]);
		int arvM = Integer.parseInt(arvTime.split(":")[1]);
		int levM = Integer.parseInt(arvTime.split(":")[1]);
		if (arvH < levH) {
			if (arvM < levM)
				// ��1��ʾ����һ��Сʱ�İ���1Сʱ��
				cost += (levH - arvH + 1) * costPerHour;
			else
				cost += (levH - arvH) * costPerHour;
		} else if (arvH == levH) {
			/*
			 * ������ͬʱ��Ĭ���Ѿ�ͣ��һ�� ��Ϊ�������ܽ�ȥһ���Ӷ���ͣ
			 */
			return 24 * costPerHour;
		} else {
			if (arvM < levM)
				cost += (24 - arvH + levH + 1) * costPerHour;
			else
				cost += (24 - arvH + levH) * costPerHour;
		}
		return cost;
	}

	public myStack<Car> getEnterStack() {
		return this.enterStack;
	}

	public myStack<Car> getExitStack() {
		return this.exitStack;
	}

	public myQueue getTempParking() {
		return this.tempParking;
	}
}
