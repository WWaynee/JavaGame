package com.tedu.model.manager;

import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Box;
import com.tedu.model.vo.Floor;
import com.tedu.model.vo.HighTree;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

/**
 * �������ݲ�����ͬ���Զ���ȡ ��Դ����� vo�������ݣ��洢�� Ԫ�ع�����
 * 	���������ã����Ƚϸ��ӵ� ���췽ʽ ���з�װ
 */
public class ElementFactory {
	
	public static SuperElement elementFactory(String name){
		List<String> Maps = ElementLoad.getElementLoad().getMaps();
		List<String> MapFloor = ElementLoad.getElementLoad().getMapFloor();
		Map<String, List<String>> playmap=ElementLoad.getElementLoad().getPlaymap();
		List<String> list=playmap.get(name);
		switch(name){
		case "Floor":
			//playerA,playFire,150,300,40,40
			return Floor.createFloor(MapFloor.get(0));
		case "Box":
			return Box.createBox(Maps.get(0));
		case "HighTree":
			return HighTree.createHighTree(Maps.get(0));
		case "Map":
			return com.tedu.model.vo.Map.createMap(Maps.get(0));
		case "onePlayer":		 
			return Player.createPlayer(list.get(1));// playerA playFire 150 300 40 40
		case "twoPlayer":
			return Player.createPlayer(list.get(0));// playerB playFire 150 300 40 40
		}
		return null;
	}
	
	
}
