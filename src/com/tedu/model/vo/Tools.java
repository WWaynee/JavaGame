package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Tools extends SuperElement{
	private String name;
	private ImageIcon img;
	private int moveX;
	private int time=1;

	public Tools(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.moveX=0;
		// TODO 自动生成的构造函数存根
	}
	
	public static Tools createTools(int x,int y,String str) {
		ImageIcon img=ElementLoad.getElementLoad().getMap().get(str);
		Tools tools = new Tools(x, y, 32, 32, img);
		tools.setName(str);
		return tools;
	}

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img.getImage(),
				getX(), getY(),
				getX()+getW(), getY()+getH(),
				32*moveX, 0, 
				32*(moveX+1), 49, 
				null);
	}
	
	public void update() {
		super.update();
		updateImg();
	}

	private void updateImg() {
		// TODO 自动生成的方法存根
		if(time%3==0) {
			moveX++;
			if(moveX>3)
				moveX=0;
		}
		time++;	
		
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

}
