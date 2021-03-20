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

//java是单继承，多实现。通过 内部类的方式，弥补单继承的缺陷
public class GameThread extends Thread{
//	计时数据
	private int time;
	private static boolean gameStart=false;
	private static boolean back=false;
//	代码的熟练 和 思想的进步 都是通过很多的项目锻炼
//	如果项目不多，请 重构老项目
	public void run(){
		while(true){   //游戏整体进度
//			死循环，但会有变量（状态）进行控制
			if(isGameStart()) {//如果gameStart是true就开始
		//		1.加载地图，人物
				loadElement();
		//		2.显示人物地图(流程,自动化(移动，碰撞))
				time=0;
				runGame();
		//		3.结束本地图
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
		while(true){  //每个关中玩的时候的状态
			if(back)
				break;
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
				
			for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
				List<SuperElement> list=map.get(key);
				for(int i=0;i<list.size();i++){
					list.get(i).update();
					if(!list.get(i).isVisible()){
						list.remove(i--);
					}
				}
				
			}
//			使用一个独立的方法来举行判定
			PK();

			//死亡，通关等 结束 runGame方法
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
		// TODO 自动生成的方法存根
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
//	部分的代码 是可以重复使用的。
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
	
	
	
	//游戏的流程控制 
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		//游戏结束后
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=map.keySet();		
		for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
			List<SuperElement> list=map.get(key);
			for(int i=0;i<list.size();i++){
					list.remove(i--);
			}
			
		}
	}
//	控制进度,但是，作为 控制，请不要接触 load
	private void loadElement() {
		ElementManager.getManager().load();	
	}
	//true代表游戏开始
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
