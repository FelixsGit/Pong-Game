package pong.frameWorks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import pong.objects.Ball;
import pong.window.Game;
import pong.window.Handler;


public class KeyInputOne extends KeyAdapter {
	
	private Handler handler;
	private File IngameMusic = new File("res/InGameMusic.wav");
	Ball ball;
	
	public KeyInputOne(Handler handler){
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
				//playerOne
				if(tempObject.getId() == ObjectId.PlayerOne){
					if(keyOne == KeyEvent.VK_D)
						tempObject.setVelY(5);
					
					if(keyOne == KeyEvent.VK_E)
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
		 //check if players are ready to unpause
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
			//playerOne
			if(tempObject.getId() == ObjectId.PlayerOne){
				if(keyOne == KeyEvent.VK_D){
					tempObject.setVelY(0);
				}
				if(keyOne == KeyEvent.VK_E){
					tempObject.setVelY(0);
				}		
				if(keyOne == KeyEvent.VK_ESCAPE){
					
				}
			}
		}
	}	
}
