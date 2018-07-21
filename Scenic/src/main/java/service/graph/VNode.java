package service.graph;

import java.util.Iterator;
import java.util.Set;

/*
 * VNode:·����
 * ����·���Ļ�����Ϣ
 * ΪVNode�ṩ�����ֹ������ֱ�����Graph���ArcNode��Ĳ�ͬ����
 * Graph��Ҫ��·����Ҫ�������˶˵����Ϣ
 * ArcNode��Ϊ��֪һ���˵㣬����ֻ��Ҫ��������һ���˵����Ϣ������Ϊ�˲��ҷ��㣬���洢��
 * ����һ���˵��ھ����б��е�λ��
 */
public class VNode {
	private int preIndex;
	private String preName;
	private int nextIndex;// ָ��Ľڵ��λ��
	private String nextName;// ��һ���ڵ������
	private int dis;// ����

	public VNode(String preName, String nextName, int dis) {
		this.preName = preName;
		this.nextName = nextName;
		this.dis = dis;
	}

	public VNode(String name, int dis, Set<String> nodeSet) {
		this.nextName = name;
		this.dis = dis;

		int i = 0;
		Iterator<String> it = nodeSet.iterator();
		while (it.hasNext()) {
			if (it.next().equals(name)) {
				setIndex(i);
			}
			i++;
		}
	}

	public int getIndex() {
		return this.nextIndex;
	}

	public void setIndex(int index) {
		this.nextIndex = index;
	}

	public int getDis() {
		return this.dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}

	public String getNextName() {
		return this.nextName;
	}

	public void setNextName(String name) {
		this.nextName = name;
	}

	public String getPreName() {
		return this.preName;
	}

	public void setPreName(String name) {
		this.preName = name;
	}

	public int getPreIndex() {
		return this.preIndex;
	}

	public void setPreIndex(int Index) {
		this.preIndex = Index;
	}
}
