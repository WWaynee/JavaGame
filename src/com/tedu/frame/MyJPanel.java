package com.tedu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.SuperElement;

public class MyJPanel extends JPanel implements Runnable{
	/**
	 * 1.���paint���� �ײ��Զ����õģ�������д����ķ���
	 * 2.�������ֻ��ִ��1�Σ�������������ã��Ͳ������ִ��
	 * 
	 * ֡: 50-100����ÿ֡    20-10֡/��
	 */
	  //������ ������ʾ
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon img = new ImageIcon("img/Title.png");
		g.drawImage(img.getImage(), 0, 0, this);
		this.setBackground(Color.black);
//		��һ���ж�ֵ  Ҳ����ʹ��ö��
//		1.ǰ����
//		2.gameRuntime
		gameRunTime(g);//Graphics ����
//		3.�νӶ���
	}

	private void gameRunTime(Graphics g) {
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=map.keySet();
		List<String> list=new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);//��Ȼ˳��
//		֪ʶ�㣺java���϶����������� ��2���ӿ��й�
		for(String key:list){
			List<SuperElement> list1=map.get(key);
			for(int i=0;i<list1.size();i++){
				list1.get(i).showElement(g);
			}
		}
	}
	
	/**
	 * ʲô����д��
	 * 1.���м̳й�ϵ�� ������֮����﷨����(��̬��һ��ʵ��)
	 * 2.��д�ķ�������� ����ķ�����ǩ��һ��(����ֵ���������ƣ���������)
	 * 3.��д�ķ����������η�ֻ���Աȸ���ĸ��ӿ��ţ������Աȸ�����ӷ��
	 * 4.��д�ķ����׳��쳣�����Ա� ����ĸ��ӿ���
	 */
	@Override
	public void run(){
		while(true){//��ѭ��:����᲻ֹͣ��ˢ��
//			�̵߳�����
			try {
				Thread.sleep(100);//����
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();//Ҫ�� ����ٴ�ˢ��
		}
	}	
}