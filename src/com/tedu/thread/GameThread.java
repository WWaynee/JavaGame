package com.tedu.thread;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;
import com.tedu.model.vo.Tools;

//java�ǵ��̳У���ʵ�֡�ͨ�� �ڲ���ķ�ʽ���ֲ����̳е�ȱ��
public class GameThread extends Thread{
//	��ʱ����
	private int time;
	private static boolean gameStart=false;
	private static boolean back=false;
//	��������� �� ˼��Ľ��� ����ͨ���ܶ����Ŀ����
//	�����Ŀ���࣬�� �ع�����Ŀ
	public void run(){
		while(true){   //��Ϸ�������
//			��ѭ���������б�����״̬�����п���
			if(isGameStart()) {//���gameStart��true�Ϳ�ʼ
		//		1.���ص�ͼ������
				loadElement();
		//		2.��ʾ�����ͼ(����,�Զ���(�ƶ�����ײ))
				time=0;
				runGame();
		//		3.��������ͼ
				overGame();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameStart=false;
				back=false;
			}
			gameStart=isGameStart();
			System.out.println(gameStart);
		}
	}
	private void runGame() {
		while(true){  //ÿ���������ʱ���״̬
			if(back)
				break;
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
				
			for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
				List<SuperElement> list=map.get(key);
				for(int i=0;i<list.size();i++){
					list.get(i).update();
					if(!list.get(i).isVisible()){
						list.remove(i--);
					}
				}
				
			}
//			ʹ��һ�������ķ����������ж�
			PK();

			//������ͨ�ص� ���� runGame����
			List<SuperElement> playlist = ElementManager.getManager().getElementList("d_play");
			for(int i=0;i<playlist.size();i++)
			{
				Player player1 = (Player)playlist.get(i);
				Player player2 = (Player)playlist.get(1-i);
				if(player1.isAlive()==false&&player2.gamePK(player1))
					return;
			}
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
		}
	}
	private void PK() {
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("f_boomline");
		List<SuperElement> list2=
				ElementManager.getManager().getElementList("e_map");
		List<SuperElement> list3=
				ElementManager.getManager().getElementList("b_tools");
		List<SuperElement> list4=
				ElementManager.getManager().getElementList("d_play");
		List<SuperElement> list5=
				ElementManager.getManager().getElementList("c_bomb");
		listPK(list1, list3);
		listPK(list1,list2);
		listPK(list1, list4);
		listPK(list1, list5);
		
		getTool(list3,list4);
	}
	
	
	public void getTool(List<SuperElement> list3, 
			List<SuperElement> list4) {
		// TODO �Զ����ɵķ������
		for(int i=0;i<list4.size();i++)
		{
			Player player = (Player)list4.get(i);
			Player player2 = (Player)list4.get(1-i);
			if(player.isAlive()) {
				for(int j=0;j<list3.size();j++)
				{
					Tools tool = (Tools)list3.get(j);
					if(player.gamePK(tool)) {
						switch (tool.getName()) {
						case "tool1":
							player.setBombPower(player.getBombPower()+1);
							break;
						case "tool2":
							player.setConfused(true);
							break;
						case "tool3":
							player2.setConfused(true);
							break;
						case "tool4":
							player.setConfused(false);
							break;
						case "tool5":
							player.setBoomNum(player.getBoomNum()+1);
							break;
						}
						tool.setVisible(false);
					}
				}
			}
		}
	}
//	���ֵĴ��� �ǿ����ظ�ʹ�õġ�
	public void listPK(List<SuperElement> list1,
			List<SuperElement> list2){
		for(int i=0;i<list1.size();i++){
			for(int j=0;j<list2.size();j++){
				if(list1.get(i).gamePK(list2.get(j))){
					if(list2.get(j) instanceof Player)
					{
						Player player = (Player)list2.get(j);
						player.setAlive(false);
						ElementManager.gameOver();
//						gameStart=false;
					}
					else
						list2.get(j).setVisible(false);	
				}
			}
		}
	}
	
	
	
	//��Ϸ�����̿��� 
//	public void linkGame(){
//		ElementManager.getManager().linkGame(time);
//	}
	
	private void overGame() {
		// TODO Auto-generated method stub
		if(!back) {
			ElementManager.getManager().gameOver();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		//��Ϸ������
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=map.keySet();		
		for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
			List<SuperElement> list=map.get(key);
			for(int i=0;i<list.size();i++){
					list.remove(i--);
			}
			
		}
	}
//	���ƽ���,���ǣ���Ϊ ���ƣ��벻Ҫ�Ӵ� load
	private void loadElement() {
		ElementManager.getManager().load();	
	}
	//true������Ϸ��ʼ
	public static void setGameStart(boolean flag) {
		gameStart=flag;
	}
	
	public static void setBack(boolean flag) {
		back=flag;
	}
	public static boolean isGameStart() {
		return gameStart;
	}
	
}
