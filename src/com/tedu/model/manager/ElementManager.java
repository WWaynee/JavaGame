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
 * Ԫ�ع�����
 * 
 * java���ģʽ-������ģʽ->ȫ��ֻ��һ��ʵ��
 * 
 * hashCode();��Object��->��������Set����,hashɢ��ԭ��
 * 
 */
public class ElementManager {
//	����  NPCԪ�أ�����Ԫ�أ���������
	Map<String,List<SuperElement>> map;//�ô���ʲô������
//	private MoveType moveType;
	
//	��ʼ��
	protected void init(){
		map=new HashMap<>();
//		-M
		map.put("a_floor",new ArrayList<>());//ÿһ����Դ����
		map.put("b_tools", new ArrayList<>());
		map.put("c_bomb", new ArrayList<>());
		map.put("d_play", new ArrayList<>());
		map.put("e_map", new ArrayList<>());
		map.put("f_boomline", new ArrayList<>());
		map.put("z_gameover", new ArrayList<>());
		
	}
//	�õ�һ�������� map����
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	�õ�һ��Ԫ�صļ���
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}

	
//	��������Ҫһ��Ψһ������
	private static ElementManager elementManager;
//	���췽��˽�л�����ֻ���ڱ����п��� new
	private ElementManager(){
		init();
	}
	static{//��̬������ ��������ص�ʱ��ͻ�ִ��
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	�ṩ���������ⲿ���ʵ�Ψһ���   synchronized �̱߳�����
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
	
	
//	������Ҫ����Դ
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readMapPro();
		ElementLoad.getElementLoad().readMaps();
		ElementLoad.getElementLoad().readMapFloor();
		ElementLoad.getElementLoad().readPlayPro();
//		-M ���ص�ͼ����
		loadMap();
//		-M ��������
		map.get("d_play").add(ElementFactory.elementFactory("onePlayer"));
		map.get("d_play").add(ElementFactory.elementFactory("twoPlayer"));
	}
	
	
	
	private void loadMap() {
		//���صذ�
		List<String> MapFloor=ElementLoad.getElementLoad().getMapFloor();
		int size1 = MapFloor.size();
		for(int i=0;i<size1;i++)
		{
			String[] arr = MapFloor.get(0).split(",");
			map.get("a_floor").add(ElementFactory.elementFactory(arr[1]));
			MapFloor.remove(0);
		}
		//��������
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