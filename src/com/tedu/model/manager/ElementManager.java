package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.GameOver;
import com.tedu.model.vo.SuperElement;

/**
 * 元素管理器
 * 
 * java设计模式-》单例模式->全局只有一个实例
 * 
 * hashCode();是Object类->集合排序（Set集合,hash散列原理）
 * 
 */
public class ElementManager {
//	集合  NPC元素，场景元素，。。。。
	Map<String,List<SuperElement>> map;//好处是什么？？？
//	private MoveType moveType;
	
//	初始化
	protected void init(){
		map=new HashMap<>();
//		-M
		map.put("a_floor",new ArrayList<>());//每一种资源类型
		map.put("b_tools", new ArrayList<>());
		map.put("c_bomb", new ArrayList<>());
		map.put("d_play", new ArrayList<>());
		map.put("e_map", new ArrayList<>());
		map.put("f_boomline", new ArrayList<>());
		map.put("z_gameover", new ArrayList<>());
		
	}
//	得到一个完整的 map集合
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	得到一个元素的集合
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}

	
//	单例：需要一个唯一的引用
	private static ElementManager elementManager;
//	构造方法私有化，就只有在本类中可以 new
	private ElementManager(){
		init();
	}
	static{//静态的语句块 是在类加载的时候就会执行
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	提供出来给予外部访问的唯一入口   synchronized 线程保护锁
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
	
	
//	加载需要的资源
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readMapPro();
		ElementLoad.getElementLoad().readMaps();
		ElementLoad.getElementLoad().readMapFloor();
		ElementLoad.getElementLoad().readPlayPro();
//		-M 加载地图函数
		loadMap();
//		-M 加载主角
		map.get("d_play").add(ElementFactory.elementFactory("onePlayer"));
		map.get("d_play").add(ElementFactory.elementFactory("twoPlayer"));
	}
	
	
	
	private void loadMap() {
		//加载地板
		List<String> MapFloor=ElementLoad.getElementLoad().getMapFloor();
		int size1 = MapFloor.size();
		for(int i=0;i<size1;i++)
		{
			String[] arr = MapFloor.get(0).split(",");
			map.get("a_floor").add(ElementFactory.elementFactory(arr[1]));
			MapFloor.remove(0);
		}
		//加载箱子
		List<String> Maps=ElementLoad.getElementLoad().getMaps();
		int size2 = Maps.size();
		for(int i=0;i<size2;i++)
		{
			String[] arr = Maps.get(0).split(",");
			if(!arr[1].equals("Floor"))
				map.get("e_map").add(ElementFactory.elementFactory(arr[1]));
			Maps.remove(0);
		}
	}
	
	public static void gameOver() {
		ImageIcon img = ElementLoad.getElementLoad().getMap().get("gameover");
		ElementManager.getManager().getElementList("z_gameover").add(GameOver.createGameOver(0, 0, 640, 480, img));
	}
}