package service;

import java.util.Scanner;

import service.algorithm.myHamilton;
import service.datastructure.myArrayList;
import service.graph.Graph;
import service.parking.Parking;

/*
 * Menu:�˵��࣬������ͨ�������н������ϵͳ
 * ��Ϊ��ϵͳ�������к���վ���ֽ�����ʽ���У������ڵ�����ط���ʱ����int mode ��һ����
 * ȷ�������ֽ���ģʽ��modeΪ0ʱ������վ������Ϊ1ʱ���������н�����
 */
public class Menu {
	public static void showMenu(Graph myGraph) {
		while (true) {
			System.out.println("===================================");
			System.out.println("      ��ӭʹ�þ�����Ϣ����ϵͳ           ");
			System.out.println("        ***ѡ��˵�***             ");
			System.out.println("===================================");
			System.out.println("1.�����������ֲ�ͼ");
			System.out.println("2.�������");
			System.out.println("3.��������");
			System.out.println("4.Ѱ�����·��");
			System.out.println("5.չʾȫ������");
			System.out.println("6.չʾȫ����·");
			System.out.println("7.չʾ����ͼ");
			System.out.println("8.ͣ��������ϵͳ");
			System.out.println("9.����Ա��¼");
			System.out.println("0.�˳�ϵͳ");
			System.out.println("===================================");

			System.out.println("����������ѡ��");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			dealChoice(choice, myGraph);
		}
	}

	/*����˵�����*/
	private static void dealChoice(int choice, Graph myGraph) {
		switch (choice) {
		case 1:
			ch1_ini(myGraph);
			break;
		case 2:
			ch2_search(myGraph);
			break;
		case 3:
			ch3_sort(myGraph);
			break;
		case 4:
			ch4_shortest(myGraph);
			break;
		case 5:
			ch5_showAll(myGraph);
			break;
		case 6:
			ch6_showAllEdges(myGraph);
			break;
		case 7:
			ch7_showTourGraph(myGraph);
			break;
		case 8:
			ch8_parking(myGraph);
			break;
		case 9:
			ch9_managerMenu(myGraph);
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("����������������롣");
			clearConsole();
			break;
		}
	}

	private static void ch1_ini(Graph myGraph) {
		myGraph.printMatrix();
		System.out.println("===================================");
		clearConsole();
	}

	private static void ch2_search(Graph myGraph) {
		System.out.println("������������ݣ�");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String item = scan.nextLine();
		myArrayList<String> result = myGraph.search(item);
		System.out.println("[��" + result.size() / 5 + "����Ͻ��]");
		System.out.println("���" + "\t" + "����" + "\t" + "��ӭ��" + "\t" + "��Ϣ��" + "\t" + "����" + "\t" + "���" + "\t");
		for (int i = 0; i < result.size(); i += 5) {
			System.out.print("No." + (i + 5) / 5 + "\t");
			System.out.print(result.get(i) + "\t");
			System.out.print(result.get(i + 2) + "\t");
			System.out.print((result.get(i + 3).equals("0") ? "��" : "��") + "\t");// ��Ԫ����ʽ
			System.out.print((result.get(i + 4).equals("0") ? "��" : "��") + "\t");
			System.out.print(result.get(i + 1) + "\t");
			System.out.println();
		}
		System.out.println("===================================");
		clearConsole();
	}

	private static void ch3_sort(Graph myGraph) {
		System.out.println("����������������������������������������������������������������������");
		System.out.println("              ����ʽ                       ");
		System.out.println("����������������������������������������������������������������������");
		System.out.println("1.��ӭ������(��������)              ");
		System.out.println("2.��·������(��������)              ");
		System.out.println("3.�����ϼ�                                             ");
		System.out.println("����������������������������������������������������������������������");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		System.out.println("����������������������������������������������������������������������");
		switch (choice) {
		case 1:
			myArrayList<String[]> result1 = myGraph.sort_welcome();
			System.out.println("[��" + result1.size() + "����Ͻ��]");
			System.out.println("���" + "\t" + "����" + "\t" + "��ӭ��" + "\t" + "��Ϣ��" + "\t" + "����" + "\t" + "���" + "\t");
			for (int i = result1.size() - 1; i > -1; i--) {
				System.out.print("No." + (result1.size() - i) + "\t");
				System.out.print(result1.get(i)[0] + "\t");
				System.out.print(result1.get(i)[2] + "\t");
				System.out.print((result1.get(i)[3].equals("0") ? "��" : "��") + "\t");// ��Ԫ����ʽ
				System.out.print((result1.get(i)[4].equals("0") ? "��" : "��") + "\t");
				System.out.print(result1.get(i)[1] + "\t");
				System.out.println();
			}
			break;
		case 2:
			myArrayList<String[]> result2 = myGraph.sort_degree();
			System.out.println("[��" + result2.size() + "����Ͻ��]");
			System.out.println(
					"���" + "\t" + "����" + "\t" + "��·��" + "\t" + "��ӭ��" + "\t" + "��Ϣ��" + "\t" + "����" + "\t" + "���" + "\t");
			for (int i = result2.size() - 1; i > -1; i--) {
				System.out.print("No." + (result2.size() - i) + "\t");
				System.out.print(result2.get(i)[0] + "\t");
				System.out.print(result2.get(i)[5] + "\t");
				System.out.print(result2.get(i)[2] + "\t");
				System.out.print((result2.get(i)[3].equals("0") ? "��" : "��") + "\t");// ��Ԫ����ʽ
				System.out.print((result2.get(i)[4].equals("0") ? "��" : "��") + "\t");
				System.out.print(result2.get(i)[1] + "\t");
				System.out.println();
			}
			break;
		case 3:
			clearConsole();
			showMenu(myGraph);
			break;
		default:
			System.out.println("����������������롣");
			clearConsole();
			break;
		}
		System.out.println("����������������������������������������������������������������������");
		clearConsole();
	}

	private static void ch4_shortest(Graph myGraph) {
		myGraph.findShortest("", "", 1);
		clearConsole();
	}

	private static void ch5_showAll(Graph myGraph) {
		myGraph.showAll();
		clearConsole();
	}

	private static void ch6_showAllEdges(Graph myGraph) {
		myGraph.showAllEdges();
		clearConsole();
	}

	private static void ch7_showTourGraph(Graph myGraph) {
		myHamilton.findCircle(myGraph.getAdjMatrix(), myGraph);
		clearConsole();
	}

	private static void ch8_parking(Graph myGraph) {
		Parking parking = new Parking(2, 5);
		while (true) {
			System.out.println("===================================");
			System.out.println("       ��ӭʹ��ͣ��������ϵͳ           ");
			System.out.println("        ***����Ա�˵�***             ");
			System.out.println("===================================");
			System.out.println("1.��������");
			System.out.println("2.����������");
			System.out.println("3.�Զ�ģ��");
			System.out.println("0.�����ϲ�");
			System.out.println("===================================");

			System.out.println("����������ѡ��");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				parking.carEnter(0, "", 1);
				break;
			case 2:
				parking.carExit(0, "", 1);
				break;
			case 3:

				break;
			case 0:
				showMenu(myGraph);
				clearConsole();
				break;
			default:
				System.out.println("����������������롣");
				clearConsole();
				break;
			}
		}
	}

	private static void ch9_managerMenu(Graph myGraph) {
		while (true) {
			System.out.println("===================================");
			System.out.println("      ��ӭʹ�þ�����Ϣ����ϵͳ           ");
			System.out.println("        ***����Ա�˵�***             ");
			System.out.println("===================================");
			System.out.println("1.��������");
			System.out.println("2.ɾ������");
			System.out.println("3.����·��");
			System.out.println("4.ɾ��·��");
			System.out.println("5.����֪ͨ����");
			System.out.println("0.�����ϲ�");
			System.out.println("===================================");

			System.out.println("����������ѡ��");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			dealManager(choice, myGraph);
		}
	}

	private static void dealManager(int choice, Graph myGraph) {
		FileManager file = new FileManager();
		switch (choice) {
		case 1:
			file.addNode(myGraph);
			break;
		case 2:
			file.removeNode(myGraph, "", 1);
			break;
		case 3:
			file.addEdge(myGraph, "", "", 0, 1);
			break;
		case 4:
			file.removeEdge(myGraph, "", "", 1);
			break;
		case 5:
			break;
		case 0:
			showMenu(myGraph);
			clearConsole();
			break;
		default:
			clearConsole();
			System.out.println("����������������롣");
			break;
		}
	}

	/* ���п��У�����ʹconsole������Ѻ� */
	private static void clearConsole() {
		for (int i = 0; i <= 3; i++) {
			System.out.println();
		}
	}
}
