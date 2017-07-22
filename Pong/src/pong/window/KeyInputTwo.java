package pong.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import pong.frameWorks.GameObject;
import pong.frameWorks.ObjectId;
import pong.objects.Ball;

public class KeyInputTwo extends KeyAdapter {

	private Handler handler;
	private File IngameMusic = new File("InGameMusic.wav");
	Ball ball;
	
	public KeyInputTwo(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		if(Game.state == Game.state.GAME){
			int keyOne = e.getKeyCode();
			if(keyOne == KeyEvent.VK_ESCAPE){
				Game.state = Game.STATE.PAUSED;
				Game.playMusic(IngameMusic, false);
			}
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				//playerTwo
				if(tempObject.getId() == ObjectId.PlayerTwo){
					if(keyOne == KeyEvent.VK_DOWN)
						tempObject.setVelY(5);
					
					if(keyOne == KeyEvent.VK_UP)
						tempObject.setVelY(-5);
				}
			}
		}
		//check if players are ready for next round
		 if(Game.state == Game.STATE.GETREADY){
			 int keyOne = e.getKeyCode();
			if(keyOne == KeyEvent.VK_ENTER){
				Game.playMusic(IngameMusic, true);
				Game.state = Game.STATE.GAME;	
			}
		 }
		 if(Game.state == Game.state.PAUSED){
			int keyOne = e.getKeyCode();
			if(keyOne == KeyEvent.VK_ENTER){
				Game.playMusic(IngameMusic, true);
				Game.state = Game.STATE.GAME;
				
			}
		 }
	}
	public void keyReleased(KeyEvent e){
		int keyOne = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			//playerTwo
			if(tempObject.getId() == ObjectId.PlayerTwo){
				if(keyOne == KeyEvent.VK_DOWN){
					tempObject.setVelY(0);
				}
				if(keyOne == KeyEvent.VK_UP){
					tempObject.setVelY(0);
				}		
				if(keyOne == KeyEvent.VK_ESCAPE){
					
				}
			}
		}
	}	
}
