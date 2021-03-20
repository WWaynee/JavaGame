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
//	private List<String> gameList;//��Ϸ�����̿���  ���˱������ֿ���
//	�Ͻ�ͷ��С��ע�⣺�����õ���  Map<String,List<ImageIcon>>
	
	private static ElementLoad load;
//	public List<String> getGameList() {
//		return gameList;
//	}
	//	pro�ļ���ȡ����
	private Properties pro;
	
	private ElementLoad(){
		//��Դ��
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
//	��ȡ����
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
	
	
//	��ȡ��������
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/play.pro");//��ȡ�����ļ�
		try {
			pro.clear();
			pro.load(in);//���ؽ�properties������
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
	
	
//	��ȡ����Ԫ����Դ�Ĵ��λ��
	public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/image.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();//key��set��Ӧ����Ԫ����
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));//key��value��ӦԪ��ͼƬ��·��
			}
			System.out.println(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�Ե�ͼ�������������
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
			Collections.sort(list);//���򣬱�֤���е�ͼƬ��Դ���Ը���ǰ�е�ͼƬ��Դ
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
	
	//��ͼ��һ��һ�еض�ȡ
	public void readMapFloor(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapB.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();//��ȡkey��set
			int i=0;
			for(Object o:set){
				String string=pro.getProperty(o.toString());//���key��Ӧ��value
				String []arr = string.split(",");//�з�value
				for(int j=0;j<arr.length;j++)//��ÿ��value���������ͣ�����ű�ʾ��
					MapFloor.add(MapPro.get("01")+","+j*32+","+i*32);//����������ͼ�������
				i++;
			}
			System.out.println(MapFloor.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ��ͼ����
	public void readMapPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapA.pro");
		try {
			pro.clear();
			pro.load(in);//���������ļ�
			Set<?> set=pro.keySet();//��ȡkey��set
			for(Object o:set){
				String string=pro.getProperty(o.toString());//��ȡvalue���ַ���
				MapPro.put(o.toString(), string);//�Ž�map����
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
