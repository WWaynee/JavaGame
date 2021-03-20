package com.tedu.main;

import com.tedu.thread.MusicThread;
import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;
import com.tedu.thread.GameStartListener;
/**
 * 面向对象中 自己的事情自己做-》明确的分工
 * 
 */
public class GameStart {
//	整个游戏的入口，启动
	public static void main(String[] args) {
//		资源加载
//		窗体加载（自动化。。）
		MusicThread bgm = new MusicThread("img/泡泡堂.mp3");
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
		GameStartListener listener2=new GameStartListener();
		jf.setKeyListener(listener);
		jf.setMouseListener(listener2);
		jf.setJp(jp);//注入
//		监听加载
		jf.addListener();
//		jf.addJPanels();//加载jp
//		游戏启动（开始）
		jf.start();
		bgm.start();
	}
	
	/**
	 * 1.定义一个 VO类，继承superElement
	 * 2.在工厂中做实例化
	 * 3.配置文件中进行配置
	 * 4.如果需要监听，请在 坚挺中写代码
	 */
}
