package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class GameOver extends SuperElement{
	private  ImageIcon img;
	
	public GameOver(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public static GameOver createGameOver(int x, int y, int w, int h,ImageIcon img) {
		return new GameOver(x, y, w, h, img);
	}

	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}
	
}
