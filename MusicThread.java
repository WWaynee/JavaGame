package com.scnu.thread;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * �����ּ����̡߳�
 * 1.����mp3��ʽ�ļ�
 * 2.ʵ��ѭ������
 * (�����ⲿ����jl)��������ܼ���mp3��ʽ�ļ�
 */
public class MusicThread extends Thread{
	Player player;
	String music;
	public MusicThread(String file) {
		this.music = file;
	}
	
	public void run() {
		try {
			play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void play() throws JavaLayerException, FileNotFoundException {
		do {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
			player = new Player(buffer);
			player.play();
		}while(true);
		
	}
}
