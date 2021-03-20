package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Box extends SuperElement{
	private int imgX;
	private int imgY;
	private int imgW;
	private int imgH;
	private ImageIcon img;
	
	
	

	public Box(int x, int y, int w, int h ,int imgX,int imgY,int imgW,int imgH,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.imgX=imgX;
		this.imgY=imgY;
		this.imgW=imgW;
		this.imgH=imgH;
		// TODO 自动生成的构造函数存根
	}

	public static Box createBox(String str) {
		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[6]);
		int y=Integer.parseInt(arr[7]);
		int w=32;
		int h=32;
		int imgX=Integer.parseInt(arr[2]);
		int imgY=Integer.parseInt(arr[3]);
		int imgW=Integer.parseInt(arr[4]);
		int imgH=Integer.parseInt(arr[5]);
		ImageIcon img=ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Box(x, y, w, h, imgX, imgY, imgW, imgH, img);
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img.getImage(),
				getX(), getY(),
				getX()+getW(), getY()+getH(),
				getImgX(), getImgY(), 
				getImgX()+getImgW(), getImgY()+getImgH(),
				null);
	}
	
	public void update() {
		super.update();

	}
	
	
	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	public int getImgX() {
		return imgX;
	}

	public void setImgX(int imgX) {
		this.imgX = imgX;
	}

	public int getImgY() {
		return imgY;
	}

	public void setImgY(int imgY) {
		this.imgY = imgY;
	}

	public int getImgW() {
		return imgW;
	}

	public void setImgW(int imgW) {
		this.imgW = imgW;
	}

	public int getImgH() {
		return imgH;
	}

	public void setImgH(int imgH) {
		this.imgH = imgH;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	
}
