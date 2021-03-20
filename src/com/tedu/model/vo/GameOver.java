package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class GameOver extends SuperElement{
	private  ImageIcon img;
	
	public GameOver(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		// TODO 自动生成的构造函数存根
	}
	
	public static GameOver createGameOver(int x, int y, int w, int h,ImageIcon img) {
		return new GameOver(x, y, w, h, img);
	}

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}
	
}
