package com.tedu.model.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

public class ElementLoad {
	private Map<String,ImageIcon> map;
	private List<String> Maps;
	private List<String> MapFloor;
	private Map<String, String> MapPro;
	private Map<String, List<String>>playmap;
//	private List<String> gameList;//游戏的流程控制  敌人兵力出现控制
//	合金弹头的小组注意：你们用的是  Map<String,List<ImageIcon>>
	
	private static ElementLoad load;
//	public List<String> getGameList() {
//		return gameList;
//	}
	//	pro文件读取对象
	private Properties pro;
	
	private ElementLoad(){
		//资源库
		map=new HashMap<>();
		Maps=new ArrayList<>();
		MapFloor=new ArrayList<>();
		MapPro=new HashMap<>();
		pro=new Properties();
		playmap=new HashMap<>();
	}
	public static synchronized ElementLoad getElementLoad(){
		if(load==null){
			load=new ElementLoad();
		}
		return load;
	}
//	读取流程
//	public void readGamepro(){
//		InputStream in=ElementLoad.class.getClassLoader()
//				.getResourceAsStream("com/tedu/pro/GameRunA.pro");
//		pro.clear();
//		try {
//			pro.load(in);
//			for(Object o:pro.keySet()){
//				String str=pro.getProperty(o.toString());
//				gameList.add(str);
//			}
//			System.out.println(gameList);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
//	读取主角配置
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/play.pro");//读取配置文件
		try {
			pro.clear();
			pro.load(in);//加载进properties类里面
			List<String> list=new ArrayList<>();
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(playmap);
	}
	
	
//	读取各类元素资源的存放位置
	public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/image.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();//key的set对应的是元素名
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));//key的value对应元素图片的路径
			}
			System.out.println(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//对地图数据排序后重整
	public void readMaps(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapB.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<Object> set=pro.keySet();
			List<String> list=new ArrayList<>();
			for (Object o : set) {
			    if (o instanceof String) {
			        list.add((String)o);
			    }
			}
			Collections.sort(list);//排序，保证后行的图片资源可以覆盖前行的图片资源
			int i=0;
			for(String o:list){
				String string=pro.getProperty(o);
				String []arr = string.split(",");
				for(int j=0;j<arr.length;j++)
					Maps.add(MapPro.get(arr[j])+","+j*32+","+i*32);
				i++;
			}
			System.out.println(Maps.get(Maps.size()-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//地图是一行一行地读取
	public void readMapFloor(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapB.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();//获取key的set
			int i=0;
			for(Object o:set){
				String string=pro.getProperty(o.toString());//获得key对应的value
				String []arr = string.split(",");//切分value
				for(int j=0;j<arr.length;j++)//把每个value的箱子类型（用序号表示）
					MapFloor.add(MapPro.get("01")+","+j*32+","+i*32);//存放箱子类型及其坐标
				i++;
			}
			System.out.println(MapFloor.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取地图配置
	public void readMapPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapA.pro");
		try {
			pro.clear();
			pro.load(in);//加载配置文件
			Set<?> set=pro.keySet();//获取key的set
			for(Object o:set){
				String string=pro.getProperty(o.toString());//获取value的字符串
				MapPro.put(o.toString(), string);//放进map里面
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readMapPro();
		ElementLoad.getElementLoad().readMaps();
		ElementLoad.getElementLoad().readMapFloor();
	}
	
	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public List<String> getMaps() {
		return Maps;
	}
	public Map<String, String> getMapPro() {
		return MapPro;
	}
	public List<String> getMapFloor() {
		return MapFloor;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
}
