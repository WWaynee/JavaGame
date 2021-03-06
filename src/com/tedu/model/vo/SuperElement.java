package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 1. 新建类
 * 2.继承父类，重写方法
 * 3.添加到 map中的list中
 */



public abstract class SuperElement {
	private int x;//画图片的左上角x
	private int y;//画图片的左上角y
	private int w;
	private int h;
	private boolean visible;//默认为 true 代表 存活
/*
 * 	jvm给每个类都 会默认增加一个 默认无参数的构造方法
 *  但是，如果我们手动写啦一个构造方法（无论是什么构造（有参数，无参数））jvm都不会再添加 默认构造
 *  一般作为父类，如果有其他构造，最好写一个无参数构造，防止继承报错。
 */
	protected SuperElement(){}
	
	public SuperElement(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		visible=true;
	}
//	这些方法是可以有 顺序执行的。模板模式  //父类的引用指向与 子类的实体对象。
	public void update(){
		move();
		destroy();
	}
//	你是选择  this pk 参数    还是  参数PK参数
	public boolean gamePK(SuperElement se){
		Rectangle r1=new Rectangle(x, y, w, h);
		Rectangle r2=new Rectangle(se.x, se.y, se.w, se.h);
		return r1.intersects(r2);//如果举行有交集，返回 true
	}
//	public static boolean gamePK(SuperElement se1,SuperElement se2){
//		Rectangle r1=new Rectangle(se1.x, se1.y, se1.w, se1.h);
//		Rectangle r2=new Rectangle(se2.x, se2.y, se2.w, se2.h);
//		return r1.intersects(r2);//如果举行有交集，返回 true
//	}
	
	public abstract void showElement(Graphics g);
	public abstract void move();
	public abstract void destroy();
	
	public int getShowX(){//返回的是 修改的显示点; 默认为 左标准点
		return getX();
	}
	public int getShowY(){
		return getY();
	}
	public int getX() {//左标准点
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
}
