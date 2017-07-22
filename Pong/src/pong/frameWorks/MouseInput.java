package pong.frameWorks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import pong.objects.Ball;
import pong.window.Game;

public class MouseInput implements MouseListener{
	
	private File buttonClick = new File("res/ClickSoundd.wav");
	private File IngameMusic = new File("res/InGameMusic.wav");
	private File MenuMusic = new File("res/MenuMusic.wav");
	private File getReadyMusic = new File("res/Pim Poy.wav");
	public static String GameMode;
	private Ball ball;;

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		
		
		if(Game.state == Game.STATE.MENU){
			int xm = e.getX();
			int ym = e.getY();
			/*
			public Rectangle playButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2 - 75 , 100, 50);
			public Rectangle helpButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2  , 100, 50);
			public Rectangle quitButton = new Rectangle(Game.WIDTH/2 - 60, Game.HEIGHT/2 + 75, 100, 50);
			public Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			public Rectangle settingsButton = new Rectangle(Game.WIDTH/2 - 65, Game.HEIGHT/2 + 150, 120, 50);
			*/
			
			if((xm >= Game.WIDTH/2 - 60) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 - 75) && (ym <= Game.HEIGHT/2 - 25)){
					//Pressed playbutton
					Game.state = Game.STATE.GAME;
					Game.playSound(buttonClick);
					Game.playMusic(MenuMusic, false);
					Game.playMusic(IngameMusic, true);
				}
			}
			if((xm >= Game.WIDTH/2 - 60) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 + 75) && (ym <= Game.HEIGHT/2 + 125)){
					Game.playSound(buttonClick);
					//Pressed quitButton
					System.exit(1);
				}
			}
			if((xm >= Game.WIDTH/2 - 60) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 ) && (ym <= Game.HEIGHT/2 + 50)){
					Game.playSound(buttonClick);
					//Pressed helpButton
					Game.state = Game.STATE.HELP;
				}
			}
			if((xm >= Game.WIDTH/2 - 65) && (xm <= Game.WIDTH/2 + 55)){
				if((ym >= Game.HEIGHT/2 + 165) && (ym <= Game.HEIGHT/2 + 215)){
					Game.playSound(buttonClick);
					//Pressed SettingsButton
					Game.state = Game.STATE.SETTINGS;
				}
			}
		}
		if(Game.state == Game.STATE.HELP){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed BackButton
					Game.state = Game.STATE.MENU;
				}
			}
		}
		if(Game.state == Game.STATE.SETTINGS){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH/2 - 80) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 - 75) && (ym <= Game.HEIGHT/2 - 25)){
					GameMode = "Best Of Three";
					Game.maxScore = 2;
					Game.state = Game.STATE.MENU;
					Game.playSound(buttonClick);
					//Pressed best of three button
				}
			}
			if((xm >= Game.WIDTH/2 - 80) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 ) && (ym <= Game.HEIGHT/2 + 50)){
					GameMode = "Best Of Five";
					Game.maxScore = 3;
					Game.state = Game.STATE.MENU;
					Game.playSound(buttonClick);
					//Pressed Best of five button
				}
			}
			if((xm >= Game.WIDTH/2 - 80) && (xm <= Game.WIDTH/2 + 40)){
				if((ym >= Game.HEIGHT/2 + 75) && (ym <= Game.HEIGHT/2 + 125)){
					GameMode = "Best Of Eleven";
					Game.maxScore = 6;
					Game.state = Game.STATE.MENU;
					Game.playSound(buttonClick);
					//Pressed Best of eleven button

				}
			}
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed BackButton
					Game.state = Game.STATE.MENU;
				}
			}
		}
		if(Game.state == Game.STATE.BLUEWINSCOREBOARD){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MENU;
					Game.playMusic(getReadyMusic, false);
					Game.playMusic(MenuMusic, true);
					Game.blueScoreCounter = 0;
					Game.redScoreCounter = 0;
				}
			}
		}
		if(Game.state == Game.STATE.REDWINSCOREBOARD){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MENU;
					Game.playMusic(getReadyMusic, false);
					Game.playMusic(MenuMusic, true);
					Game.blueScoreCounter = 0;
					Game.redScoreCounter = 0;
				}
			}
		}
		if(Game.state == Game.STATE.PAUSED){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick);
					//Pressed MenuButton
					Game.state = Game.STATE.MENU;
					Game.playMusic(MenuMusic, true);
					Game.blueScoreCounter = 0;
					Game.redScoreCounter = 0;
					Ball.load = false;		
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

}
