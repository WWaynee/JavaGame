package com.tedu.main;

import com.tedu.thread.MusicThread;
import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;
import com.tedu.thread.GameStartListener;
/**
 * ��������� �Լ��������Լ���-����ȷ�ķֹ�
 * 
 */
public class GameStart {
//	������Ϸ����ڣ�����
	public static void main(String[] args) {
//		��Դ����
//		������أ��Զ���������
		MusicThread bgm = new MusicThread("img/������.mp3");
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
		GameStartListener listener2=new GameStartListener();
		jf.setKeyListener(listener);
		jf.setMouseListener(listener2);
		jf.setJp(jp);//ע��
//		��������
		jf.addListener();
//		jf.addJPanels();//����jp
//		��Ϸ��������ʼ��
		jf.start();
		bgm.start();
	}
	
	/**
	 * 1.����һ�� VO�࣬�̳�superElement
	 * 2.�ڹ�������ʵ����
	 * 3.�����ļ��н�������
	 * 4.�����Ҫ���������� ��ͦ��д����
	 */
}
