package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class Player extends SuperElement{
	private String name;
	private ImageIcon img;
	private int moveX;
	private int moveY;
	private MoveType moveType;
	private MoveType tempMoveType;
	private boolean Pk;
	private int count;
	private int putboomNum;

	private int boomNum; //人物拥有的最大炸弹数，默认为1
	private boolean confused; // 人物是否混乱了，就是吃了鬼头，方向倒置，默认为false
	private int BombPower;//设置炸弹威力

	private int time;//人物等待死亡时间
	private boolean alive;//判断玩家是否被炸弹炸到,默认为 true，被炸到则为false
	private int PPchangeX;

	
	public Player(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img = img;
		moveType = MoveType.stop;
		tempMoveType = MoveType.stop;
		moveX = 0;
		moveY = 0;
		count = 0;
		putboomNum=0;
		
		//被炸后状态
		alive = true;
		time = 0;
		PPchangeX = 0;
		
		//获得道具状态
		boomNum = 1;
		confused = false;
		BombPower=1;


		// TODO 自动生成的构造函数存根
	}
	
	public static Player createPlayer(String str) {
		String[] arr = str.split(",");// playerA,playFire,150,300,40,40
		int x = Integer.parseInt(arr[2]);
		int y = Integer.parseInt(arr[3]);
		int w = Integer.parseInt(arr[4]);
		int h = Integer.parseInt(arr[5]);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);
		Player player = new Player(x, y, w, h, img);
		player.setName(arr[0]);
		return player;
	}

	public int getCount() {
		return count;
	}
	
	public MoveType countFun() 
	{
		if(count == 0)
		{
			tempMoveType = moveType;
			return tempMoveType;
		}
		else {
			return tempMoveType;
		}
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		if(isAlive()==true) {
			g.drawImage(img.getImage(),getX(), getY(), getX()+getW(), getY()+getH(), 9+moveX*50, 20+moveY*50, 41+moveX*50, 52+moveY*50,null);
		}
		else {
//			g.drawImage(img.getImage(), getX()-4, getY()-4, getX()+getW()+4, getY()+getH()+4, 5+PPchangeX*99, 20, 93+PPchangeX*99, 108, null);
		}

	}
	
	public void update() {
		super.update();
		updateImg(countFun());
		if (isAlive()==true&&putboomNum<boomNum) {
			addBoom();
		}
	}
	
	public void addBoom() {
		/*
		 * ElementManger.getManager.get数组
		 * boom++
		 * */
		if(Pk) {
			int x,y;
			switch (moveType) {
			case top:
				x=(int)((float)getX()/(float)32)*32;
				y=(int)((float)getY()/(float)32+0.5)*32;
				break;

			case left:
				x=(int)((float)getX()/(float)32+0.5)*32;
				y=(int)((float)getY()/(float)32)*32;
				break;
			default:
				x=getX()/32*32;
				y=getY()/32*32;
			}
			List<SuperElement> bomblist=ElementManager.getManager().getElementList("c_bomb");
			for(int i=0;i<bomblist.size();i++)
			{
				if(x==bomblist.get(i).getX()&&y==bomblist.get(i).getY())
					return;
			}
			ImageIcon bombimg = ElementLoad.getElementLoad().getMap().get("bomb");
			bomblist.add(Bomb.createBomb(x, y, bombimg, BombPower,name));
			putboomNum++;
		}
			
	}
	
	private void updateImg(MoveType moveType) {
		if(isAlive()) {
			if(confused==false) {
				switch(moveType){
				case top: 		
					//判断数组左右
					moveY = 3;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case left:
					moveY = 1;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case right:
					moveY = 2;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case down:
					moveY = 0;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case stop:
					moveX = 0;
					break;
				}
			}
			else
			{
				switch(moveType){
				case down: 		
					//判断数组左右
					moveY = 3;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case right:
					moveY = 1;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case left:
					moveY = 2;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case top:
					moveY = 0;
					moveX++;
					if(moveX == 4)
						moveX = 0;
					break;
				case stop:
					moveX = 0;
					break;
				}
			}
		}
		else
		{
			this.setImg(ElementLoad.getElementLoad().getMap().get("playerC"));
			time+=1;
			if(time%3==0) {
				PPchangeX++;
				if(PPchangeX>3)
					PPchangeX=0;
			}

		}
	}
	
	@Override
	public void move() {
		// TODO 自动生成的方法存根
		if(confused==false) {
			switch(countFun()){
			case top: 		
				//判断数组左右
				if(!isStop(getX(), getY()-8))
					setY(getY()-8);
				count++;
				break;
			case left:
				if(!isStop(getX()-8, getY()))
					setX(getX()-8);
				count++;
				break;
			case right:
				if(!isStop(getX()+8, getY()))
					setX(getX()+8);
				count++;
				break;
			case down:
				if(!isStop(getX(), getY()+8))
					setY(getY()+8);
				count++;
				break;
			case stop:
				break;
			}
			if(count == 4)
				count = 0;
		}
		else {
			switch(countFun()){
			case down: 		
				//判断数组左右
				if(!isStop(getX(), getY()-8))
					setY(getY()-8);
				count++;
				break;
			case right:
				if(!isStop(getX()-8, getY()))
					setX(getX()-8);
				count++;
				break;
			case left:
				if(!isStop(getX()+8, getY()))
					setX(getX()+8);
				count++;
				break;
			case top:
				if(!isStop(getX(), getY()+8))
					setY(getY()+8);
				count++;
				break;
			case stop:
				break;
			}
			if(count == 4)
				count = 0;
		}
	}
	
//	移动前判断是否有障碍物
	public boolean isStop(int x1,int y1) {
		int i;
		List<SuperElement> maplist= 
				ElementManager.getManager().getElementList("e_map");
		List<SuperElement> bomblist=
				ElementManager.getManager().getElementList("c_bomb");
		for(i=0;i<maplist.size();i++)
		{
			int x=maplist.get(i).getX();
			int y=maplist.get(i).getY();
			int w=maplist.get(i).getW();
			int h=maplist.get(i).getH();
			Rectangle r1=new Rectangle(x, y, w, h);
			Rectangle r2=new Rectangle(x1, y1, getW(), getH());
			if(r1.intersects(r2))
				return true;
		}
		for(i=0;i<bomblist.size();i++)
		{
			int x=bomblist.get(i).getX();
			int y=bomblist.get(i).getY();
			int w=bomblist.get(i).getW();
			int h=bomblist.get(i).getH();
			Rectangle r1=new Rectangle(x, y, w, h);
			Rectangle r2=new Rectangle(x1, y1, getW(), getH());
			Rectangle r3=new Rectangle(getX(), getY(), getW(), getH());
			if(!r1.intersects(r3)&&r1.intersects(r2))
				return true;
		}
		return false;
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

	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}

	public int getMoveY() {
		return moveY;
	}

	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

	public MoveType getTempMoveType() {
		return tempMoveType;
	}

	public void setTempMoveType(MoveType tempMoveType) {
		this.tempMoveType = tempMoveType;
	}

	public boolean isPk() {
		return Pk;
	}

	public void setPk(boolean pk) {
		Pk = pk;
	}

	public int getBoomNum() {
		return boomNum;
	}

	public void setBoomNum(int boomNum) {
		this.boomNum = boomNum;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isConfused() {
		return confused;
	}

	public void setConfused(boolean confused) {
		this.confused = confused;
	}


	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getPPchangeX() {
		return PPchangeX;
	}

	public void setPPchangeX(int pPchangeX) {
		PPchangeX = pPchangeX;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getBombPower() {
		return BombPower;
	}

	public void setBombPower(int bombPower) {
		BombPower = bombPower;
	}

	public int getPutboomNum() {
		return putboomNum;
	}

	public void setPutboomNum(int putboomNum) {
		this.putboomNum = putboomNum;
	}

	
}
