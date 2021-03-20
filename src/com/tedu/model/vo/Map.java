package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Map extends SuperElement{
	private int imgX;
	private int imgY;
	private int imgW;
	private int imgH;
	private ImageIcon img;


	public Map(int x, int y, int w, int h,int imgX,int imgY,int imgW,int imgH,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.imgX=imgX;
		this.imgY=imgY;
		this.imgW=imgW;
		this.imgH=imgH;
		// TODO Auto-generated constructor stub
	}
	
	//通过传入的字符串实例化类
	public static Map createMap(String str) {
		String [] arr=str.split(",");
		//在窗体内的位置
		int x=Integer.parseInt(arr[6]);
		int y=Integer.parseInt(arr[7]);
		int w=32;
		int h=32;
		//在图片里的位置
		int imgX=Integer.parseInt(arr[2]);
		int imgY=Integer.parseInt(arr[3]);
		int imgW=Integer.parseInt(arr[4]);
		int imgH=Integer.parseInt(arr[5]);
		ImageIcon img=ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Map(x, y, w, h, imgX, imgY, imgW, imgH, img);
	}
	
	//绘制图片
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),
				getX(), getY(),
				getX()+getW(), getY()+getH(),
				getImgX(), getImgY(), 
				getImgX()+getImgW(), getImgY()+getImgH(),
				null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
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
