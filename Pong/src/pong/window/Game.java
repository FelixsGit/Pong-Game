package pong.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import pong.frameWorks.KeyInputOne;
import pong.frameWorks.Menu;
import pong.frameWorks.MouseInput;
import pong.frameWorks.ObjectId;
import pong.objects.Ball;
import pong.objects.PlayerOne;
import pong.objects.PlayerTwo;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5837746723225614173L;
	public static int WIDTH = 960;
	public static int HEIGHT = 640;
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private Menu menu;
	private BufferedImage backgroundMenu = null;
	private BufferedImage backgroundGame = null;
	private File getReadyMusic = new File("res/Pim Poy.wav");
	public static Clip MusicClip;
	public static int blueScoreCounter = 0;
	public static int redScoreCounter = 0;
	public static int maxScore;
	
	public static enum STATE{
		MENU,
		HELP,
		GAME,
		SETTINGS,
		REDPLAYERLOSS,
		BLUEPLAYERLOSS,
		GETREADY,
		BLUEWINSCOREBOARD,
		REDWINSCOREBOARD,
		PAUSED,
	}
	
	public static STATE state = STATE.MENU;
	
	private void init(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			backgroundMenu = loader.loadImage("/backgroundMenu.png");
			backgroundGame = loader.loadImage("/backgroundGame.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu = new Menu();
		WIDTH = getWidth();
		HEIGHT = getHeight();
		handler = new Handler();
		MouseInput.GameMode = "No Active";
		
	
		
		handler.createLevel();
		handler.addObject(new Ball(0, 0, handler, ObjectId.Ball));
		handler.addObject(new PlayerOne(0, 0, handler, ObjectId.PlayerOne));
		handler.addObject(new PlayerTwo(0, 0, handler, ObjectId.PlayerTwo));
		
		this.addMouseListener(new MouseInput());
		this.addKeyListener(new KeyInputOne(handler));	
		this.addKeyListener(new KeyInputTwo(handler));
	}
	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 144;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick(){
		if(state == state.GAME){
			handler.tick();
		}
		if(state == state.BLUEPLAYERLOSS){
			redScoreCounter++;
			if(redScoreCounter == maxScore){
				Game.playMusic(getReadyMusic, true);
				Game.state = Game.STATE.REDWINSCOREBOARD;
				redScoreCounter = 0;
			}else{
				Game.state = Game.STATE.GETREADY;
			}
		}
		if(state == state.REDPLAYERLOSS){
			blueScoreCounter++;
			if(blueScoreCounter == maxScore){
				Game.playMusic(getReadyMusic, true);
				Game.state = Game.STATE.BLUEWINSCOREBOARD; 
				blueScoreCounter = 0;
			}else{
				Game.state = Game.STATE.GETREADY;
			}
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		//Draw here
		if(state == STATE.GAME){
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.red);
			g.drawImage(backgroundGame, 0, 0, null);
			g.drawString(redScoreCounter + " ", Game.WIDTH/2  - 50, 75);
			g.setColor(Color.BLUE);
			g.drawString(blueScoreCounter + " ", Game.WIDTH/2 + 30, 75);
			handler.render(g);	
		}
		if(state == STATE.HELP){
			g.drawImage(backgroundMenu, 0, 0, null);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("Controlls", Game.WIDTH/2 - 100, Game.HEIGHT/3);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
			g.setFont(fnt1);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(backButton);
			g.setColor(Color.RED);
			g.drawString("Red Player", Game.WIDTH/2 - 200, Game.WIDTH/3 - 50);
			g.setColor(Color.BLUE);
			g.drawString("Blue Player", Game.WIDTH/2 + 50, Game.WIDTH/3 - 50);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 20);
			g.setFont(fnt2);
			g.setColor(Color.black);
			g.drawString("Up = E", Game.WIDTH/2 - 200, Game.WIDTH/3 - 20);
			g.drawString("Down = D", Game.WIDTH/2 - 200, Game.WIDTH/3 );
			g.drawString("Up = UP", Game.WIDTH/2 + 50, Game.WIDTH/3 - 20);
			g.drawString("Down = DOWN", Game.WIDTH/2 + 50, Game.WIDTH/3 );
			g.drawString("Esc To Pause", Game.WIDTH/2 - 75 , Game.HEIGHT/2 + 100);
			g.setFont(fnt1);
			g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
		}
		if(state == STATE.MENU){
			g.drawImage(backgroundMenu, 0, 0, null);
			menu.render(g);
		}
		if(state == STATE.SETTINGS){
			g.drawImage(backgroundMenu, 0, 0, null);
			Rectangle bestOfThree = new Rectangle(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 75 , 200, 50);
			Rectangle bestOfFive = new Rectangle(Game.WIDTH/2 - 100, Game.HEIGHT/2  , 200, 50);
			Rectangle bestOfEleven = new Rectangle(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 75, 200, 50);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(bestOfThree);
			g2d.draw(bestOfFive);
			g2d.draw(bestOfEleven);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("Choose Gamemode", Game.WIDTH/2 - 220, Game.HEIGHT/3 - 40 );
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt1);
			g.drawString("Best of Three", bestOfThree.x  + 20  , bestOfThree.y + 35);
			g.drawString("Best of Five", bestOfThree.x   + 20  , bestOfThree.y + 110);
			g.drawString("Best of Eleven", bestOfThree.x + 20  , bestOfThree.y + 185);
			g.setFont(fnt1);
			g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
		}
		if(state == STATE.GETREADY){
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.red);
			g.drawImage(backgroundGame, 0, 0, null);
			g.drawString(redScoreCounter + " ", Game.WIDTH/2  - 50, 75);
			g.setColor(Color.BLUE);
			g.drawString(blueScoreCounter + " ", Game.WIDTH/2 + 30, 75);
			g.setColor(Color.black);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 30);
			g.setFont(fnt1);
			g.drawString("Press Enter For Next Round!", Game.WIDTH/2 - 220, Game.HEIGHT/2 );
		}
		if(state == STATE.REDWINSCOREBOARD){
			g.drawImage(backgroundGame, 0, 0, null);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 50);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt0);
			g.setColor(Color.red);
			g.drawString("RED PLAYER WINS!!!", Game.WIDTH/2 - 220, Game.HEIGHT/3 - 40 );
			Graphics2D g2d = (Graphics2D) g;
			g.setFont(fnt1);
			g.setColor(Color.black);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
		}
		if(state == STATE.BLUEWINSCOREBOARD){
			g.drawImage(backgroundGame, 0, 0, null);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt0);
			g.setColor(Color.BLUE);
			g.drawString("BLUE PLAYER WINS!!!", Game.WIDTH/2 - 220, Game.HEIGHT/3 - 40 );
			Graphics2D g2d = (Graphics2D) g;
			g.setFont(fnt1);
			g.setColor(Color.black);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);	
		}
		if(state == STATE.PAUSED){
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 35);
			g.setColor(Color.black);
			g.setFont(fnt0);
			g.drawString("Paused", 50, 100);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
			g.setFont(fnt1);
			g.drawString("Press Enter To Continue...", 50, 125);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt2);
			g.setColor(Color.black);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);	
		}
		//Stop Drawing here
		g.dispose();
		bs.show();
	}
	public static void main(String[] args){
		
		File MenuMusic = new File("res/MenuMusic.wav");
		new Window(960, 640, "Pong", new Game());
		playMusic(MenuMusic, true);
	}
	public static void playSound(File Sound){
		try{
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		}catch(Exception e){
			
		}
	}
	public static void playMusic(File Sound, boolean run){
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
				
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
}