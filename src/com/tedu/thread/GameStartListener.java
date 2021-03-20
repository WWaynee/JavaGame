package com.tedu.thread;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.tedu.frame.MyJFrame;

public class GameStartListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getX()>505&&arg0.getX()<505+125&&arg0.getY()>454&&arg0.getY()<454+40)
			{
				GameThread.setGameStart(true);
				System.out.println(1);
				System.out.println(arg0.getX()+" "+arg0.getY());
			}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
