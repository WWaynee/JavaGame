package com.tedu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;


public class GameListener implements KeyListener{
	private List<?> list;
	
	
	@Override  //按下  坐 37 上38  右39 下40
	public void keyPressed(KeyEvent e) {
		list = ElementManager.getManager().getElementList("d_play");
		Player playerA = (Player)list.get(0);
		Player playerB = (Player)list.get(1);
		switch(e.getKeyCode()) {				
			// playerA
			case 37:playerA.setMoveType(MoveType.left);break;
			case 38:playerA.setMoveType(MoveType.top);break;
			case 39:playerA.setMoveType(MoveType.right);break;
			case 40:playerA.setMoveType(MoveType.down);break;
			case 10:playerA.setPk(true);break;
				
			// playerB
				
			case 65:playerB.setMoveType(MoveType.left);break;
			case 87:playerB.setMoveType(MoveType.top);break;
			case 68:playerB.setMoveType(MoveType.right);break;
			case 83:playerB.setMoveType(MoveType.down);break;
			case 32:playerB.setPk(true);break;
			
			//退出游戏
			case 8:GameThread.setBack(true);break;
			}
	
				
	}

	@Override //松开
	public void keyReleased(KeyEvent e) {
		list = ElementManager.getManager().getElementList("d_play");
		Player playerA = (Player)list.get(0);
		Player playerB = (Player)list.get(1);
		// playerA
		switch(e.getKeyCode()) {
		case 37:
			if(playerA.getMoveType() == MoveType.left)
				playerA.setMoveType(MoveType.stop);
			break;
		case 38:
			if(playerA.getMoveType() == MoveType.top)
				playerA.setMoveType(MoveType.stop);
			break;
		case 39:
			if(playerA.getMoveType() == MoveType.right)
				playerA.setMoveType(MoveType.stop);
			break;
		case 40:
			if(playerA.getMoveType() == MoveType.down)
				playerA.setMoveType(MoveType.stop);
			break;

		case 10:playerA.setPk(false);
		
		// playerB
		
		case 65:
			if(playerB.getMoveType() == MoveType.left)
				playerB.setMoveType(MoveType.stop);
			break;
		case 87:
			if(playerB.getMoveType() == MoveType.top)
				playerB.setMoveType(MoveType.stop);
			break;
		case 68:
			if(playerB.getMoveType() == MoveType.right)
				playerB.setMoveType(MoveType.stop);
			break;
		case 83:
			if(playerB.getMoveType() == MoveType.down)
				playerB.setMoveType(MoveType.stop);
			break;
		case 32:playerB.setPk(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
