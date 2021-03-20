package com.tedu.model.vo;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Bomb extends SuperElement{
	//ը��ͼƬ
	private ImageIcon img;
	private String playname;
	
	private int time=0;//ը���ȴ���ըʱ��
	private int BombimgChangeX;//ը��ͼƬ�ı�������
	private int BombPower;//����ը������
	
	public Bomb(int x, int y, int w, int h, ImageIcon img,int BombPower,String playname) {
		super(x, y, w, h);
		this.img = img;
		//ը��һ�������ǵ�һ��״̬
		BombimgChangeX = 0;
		this.BombPower = BombPower;
		this.playname=playname;
	}

	public static Bomb createBomb(int x,int y,ImageIcon bombimg,int BombPower,String playname) {
		return new Bomb(x,y,32,32,bombimg,BombPower,playname);
	}
	
	public void update() {
		super.update();
		updateImage();
	}
	
	private void updateImage() {
		time+=1;
		if(time%2==0) {
			BombimgChangeX++;
			if(BombimgChangeX>2)
				BombimgChangeX=0;
		}
		if(time==36||!isVisible()) {
			addBoomLine();
			setVisible(false);
			List<SuperElement> playlist= ElementManager.getManager().getElementList("d_play");
			for(int i=0;i<playlist.size();i++)
			{
				Player player=(Player)playlist.get(i);
				if(player.getName().equals(playname)) {
					player.setPutboomNum(player.getPutboomNum()-1);
				}
			}
		}
	}
	
	
	private void addBoomLine() {
		List<SuperElement> boomlinelist=
				ElementManager.getManager().getElementList("f_boomline");
		int BLP = getRealBombPower(getX()-32, getY(), 0,"left");
		int BUP = getRealBombPower(getX(), getY()-32, 0, "up");
		int BRP = getRealBombPower(getX()+32, getY(), 0, "right");
		int BDP = getRealBombPower(getX(), getY()+32, 0, "down");
		ImageIcon boomlineimg = ElementLoad.getElementLoad().getMap().get("boomline");
		boomlinelist.add(BoomLine.createBoomLine(getX(),getY(),getW(),getH(),BLP,BUP,BRP,BDP,boomlineimg));
	}
	

	//������addbomb�����͵���
	//����ʵ��ը��������ʲô��ʽը������,�����ϰ�ʲô�ģ���δʵ��ը�˺�ը����ը��ˢ�£�
	public int getRealBombPower(int BoomGoX, int BoomGoY, int BombRealPower,String Boomdirection) { //BoomGoX��ը�������ĺ����꣬BoomGoY��ը��������������
		List<SuperElement> maplist=
					ElementManager.getManager().getElementList("e_map");//��ȡ��ͼԪ��
		while(BombRealPower<BombPower)
		{
			for(int i=0;i<maplist.size();i++) 
			{
				int MapObjectX = maplist.get(i).getX();
				int MapObjectY = maplist.get(i).getY();
				
				if(MapObjectX==BoomGoX&&MapObjectY==BoomGoY)
				{
					
					if(maplist.get(i) instanceof Box==true) {
						System.out.println(BombRealPower);
						return BombRealPower+1;
					}
					else{
						return BombRealPower;
					}
				}
			}
			BombRealPower++;
			switch (Boomdirection) {
			case "left":
				BoomGoX-=32;
				break;
			case "up":
				BoomGoY-=32;
				break;
			case "right":
				BoomGoX+=32;
				break;
			case "down":
				BoomGoY+=32;
				break;
			}
		}
		return BombRealPower;
	}	
				
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(), 11+BombimgChangeX*73, 23, 57+BombimgChangeX*73, 69, null);
		//ը�����ˣ���¼һ������ 1��(11,23) 2��(84,23) 3��(157,23) ͼƬ���ش�Сȡ46*46
	}
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}


	public int getBombimgChangeX() {
		return BombimgChangeX;
	}

	public void setBombimgChangeX(int bombimgChangeX) {
		BombimgChangeX = bombimgChangeX;
	}


	
}
