package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.BoomLineType;
import com.tedu.model.manager.ElementManager;

public class BoomLine extends SuperElement {
	private ImageIcon img;
	private BoomLineType boomlinetype;//水柱条的初始状态，默认为first
	private int BoomLineChangeX;//水柱条图片切换用,默认为0
	private int BoomLineChangeY;//水柱条图片切换用，默认为0
	
	private int BoomLeftLine;//炸弹实际弹道长度，从炸弹那边计算后传过来
	private int BoomUpLine;
	private int BoomRightLine;
	private int BoomDownLine;
	
	private List<String> toolLocation;//存储道具的位置
	
	public BoomLine(int x,int y,int w,int h,int BLP,int BUP,int BRP, int BDP, ImageIcon img) {
		super(x,y,w,h);
		this.img = img;
		BoomLineChangeX = 0;
		BoomLineChangeY = 0;
		boomlinetype = BoomLineType.first;
		
		BoomLeftLine = BLP;//对应传过来的BombLeftPower也即炸弹左弹道真实威力
		BoomUpLine = BUP;
		BoomRightLine = BRP;
		BoomDownLine = BDP;
		
		toolLocation=new ArrayList<>();
	}
	public static BoomLine createBoomLine(int x, int y, int w, int h,int BLP,int BUP,int BRP, int BDP, ImageIcon boomlineimg) {
		return new BoomLine(x,y,w,h,BLP,BUP,BRP,BDP,boomlineimg);
	}
	
	public void update() {
		super.update();
		updateImage();
	}
	
	private void updateImage() {
		switch (boomlinetype) {
		case first:
			BoomLineChangeX = 1;
			boomlinetype = BoomLineType.second;
			break;
		case second:
			BoomLineChangeX = 2;
			boomlinetype = BoomLineType.third;
			break;
		case third:
			BoomLineChangeX = 3;//图上是空的，让中间先消失
			boomlinetype = BoomLineType.fourth;
			break;
		case fourth:
			setVisible(false);
			for(int i=0;i<toolLocation.size();i++)
			{
				String[] arr=toolLocation.get(i).split(",");
				int x=Integer.parseInt(arr[0]);
				int y=Integer.parseInt(arr[1]);
				if(Math.random()*100>=60)
				{
					String tool = "tool"+(int)(Math.random()*5+1);
					ElementManager.getManager().getElementList("b_tools").add(Tools.createTools(x,y,tool));
				}
				
			}
			
		}
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean gamePK(SuperElement se){
		Rectangle r1=new Rectangle(getX()-BoomLeftLine*32, getY(), (BoomLeftLine+BoomRightLine+1)*32, getH());
		Rectangle r2=new Rectangle(getX(), getY()-BoomUpLine*32, getW(),(BoomUpLine+BoomDownLine+1)*32);
		Rectangle r3=new Rectangle(se.getX(), se.getY(), se.getW(), se.getH());
		if(r1.intersects(r3)||r2.intersects(r3))
		{
			if(se instanceof Box==true)
			{
				toolLocation.add(se.getX()+","+se.getY());
			}
			return true;
		}
		return false;//如果举行有交集，返回 true
	}

	@Override
	public void showElement(Graphics g) {
		DrawWaterColumn(BoomLeftLine,"left",g);
		DrawWaterColumn(BoomUpLine, "up",g);
		DrawWaterColumn(BoomRightLine, "right",g);
		DrawWaterColumn(BoomDownLine, "down",g);
		g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(), 28+BoomLineChangeX*57, 114, 68+BoomLineChangeX*57, 154, null);//画中间的,

	}
	
	//画水柱的方法，实现传入什么长度就
	private void DrawWaterColumn(int length, String direction,Graphics g) {
		for(int i=1;i<=length;i++)
		{
			switch (direction) {
				case "left":
					g.drawImage(img.getImage(), getX()-32*i, getY(), (getX()-32*i)+32, getY()+32, 270+BoomLineChangeX*58, 257, 310+BoomLineChangeX*58, 297, null);//画左边的 截取像素40*40
					break;
				case "up":
					g.drawImage(img.getImage(), getX(), getY()-32*i, getX()+32, (getY()-32*i)+32, 26+BoomLineChangeX*58, 184, 70+BoomLineChangeX*58, 228, null);//画上面的 截取像素44*44
					break;
				case "right":
					g.drawImage(img.getImage(), getX()+32*i, getY(), (getX()+32*i)+32, getY()+32, 276+BoomLineChangeX*58, 184, 316+BoomLineChangeX*58, 224, null);//画右边的 截取像素40*40
					break;
				case "down":
					g.drawImage(img.getImage(), getX(), getY()+32*i, getX()+32, (getY()+32*i)+32, 25+BoomLineChangeX*58, 257, 69+BoomLineChangeX*58, 301, null);//画下面的 截取像素44*44
					break;
				}
		}
	}
	public ImageIcon getImg() {
		return img;
	}
	public BoomLineType getBoomlinetype() {
		return boomlinetype;
	}
	public int getBoomLineChangeX() {
		return BoomLineChangeX;
	}
	public int getBoomLineChangeY() {
		return BoomLineChangeY;
	}
	public int getBoomLeftLine() {
		return BoomLeftLine;
	}
	public int getBoomUpLine() {
		return BoomUpLine;
	}
	public int getBoomRightLine() {
		return BoomRightLine;
	}
	public int getBoomDownLine() {
		return BoomDownLine;
	}


}



