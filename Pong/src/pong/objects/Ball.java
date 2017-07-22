package pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import pong.frameWorks.GameObject;
import pong.frameWorks.ObjectId;
import pong.frameWorks.Texture;
import pong.window.Game;
import pong.window.Handler;

public class Ball extends GameObject{
	
	private Random r = new Random();
	private int startingSide = r.nextInt(2);
	private float randomSvalue = 1 + (3 - 1) * r.nextFloat();
	private static int width = 20;
	private static int height = 20;
	public static boolean load = false;
	private float startingRightVel = 1;
	private float startingLeftVel = -1;
	private float startingYvel = randomSvalue;
	private float velX, velY;
	private ObjectId id;
	private Handler handler;
	private float maxVelY = 7;
	private File ballHit = new File("res/BallHitSound.wav");
	private File IngameMusic = new File("res/InGameMusic.wav");
	
	
	public Ball(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(!load){
			setY(0);
			setX(0);
			velX = 0;
			velY = 0;
		}
		if(!load && (startingSide == 0)){
			velX = startingRightVel;
			velY = startingYvel;
			load = true;
			startingSide = r.nextInt(2);
		}
		if(!load && (startingSide == 1)){
			velX = startingLeftVel;
			velY = startingYvel;
			load = true;
			startingSide = r.nextInt(2);
		}
		y += velY;
		x += velX;
		collision();
		if(velY >= maxVelY){
			velY = maxVelY;
		}

		//check for player playerRed round win
		if((getX()  > Game.WIDTH/2 + 100)){
			load = false;
			Game.state = Game.STATE.BLUEPLAYERLOSS;
			setX(0);
			setY(0);
			Game.playMusic(IngameMusic, false);
		}
		//check for player playerBlue round win
		if((getX() < -1*Game.WIDTH/2 - 100)){
			load = false;
			Game.state = Game.STATE.REDPLAYERLOSS;
			setX(0);
			setY(0);
			Game.playMusic(IngameMusic, false);	
		}
	}
	
	private void collision() {
		float randomYvalue = -2 + (4 - -2) * r.nextFloat();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			//PlayerOne Collision
			if(tempObject.getId() == ObjectId.PlayerOne){
				if(getBounds().intersects(tempObject.getBoundsRight())){
					Game.playSound(ballHit);
					if(tempObject.getVelY() > 0){
						if(velY <= 2 && velY >= -2){
							velY = velY*-1*randomYvalue;
						}
					}
					if(tempObject.getVelY() < 0){
						if(velY <= 1.5 && velY >= -1.5){
							velY = velY*1*randomYvalue;
						}
					}
					velX = 8*1;
				}
			}
			//PlayerTwo Collison
			if(tempObject.getId() == ObjectId.PlayerTwo){
				if(getBounds().intersects(tempObject.getBoundsLeft())){
					Game.playSound(ballHit);
					if(tempObject.getVelY() > 0){
						velY = velY*-1;
					}
					if(tempObject.getVelY() < 0){
						velY = velY*1;
					}
					velX = 8*-1;
				}
			}
			//UpperWall Collision
			if(tempObject.getId() == ObjectId.PlayField){
				if(getBoundsTop().intersects(tempObject.getBoundsTop())){
					y = tempObject.getY() -275;
					velY = getVelY()*-1;
				}
			}
			//LowerWall Collsion
			if(tempObject.getId() == ObjectId.PlayField){
				if(getBoundsBottom().intersects(tempObject.getBoundsTop())){
					y = tempObject.getY() - 335;
					velY = getVelY()*-1;
				}
			}
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillOval((int) x + Game.WIDTH/2, (int) y + Game.HEIGHT/2, width, height);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		//g2d.draw(getBounds());
		g2d.setColor(Color.BLUE);
		//g2d.draw(getBoundsTop());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsRight());
		g2d.setColor(Color.green);
		//g2d.draw(getBoundsBottom());

	}

	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + Game.WIDTH/2 - 4, (int) y + Game.HEIGHT/2 - 4, width + 8, height - 5 );
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + Game.WIDTH/2 -4 , (int) y + Game.HEIGHT/2 + 10, width + 8, height - 6);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + Game.WIDTH/2, (int) y + Game.HEIGHT/2 + 2, 4 , height - 4);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + Game.WIDTH/2 + width - 4, (int) y + Game.HEIGHT/2 + 2, 4 , height - 4);
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x + Game.WIDTH/2, (int) y + Game.HEIGHT/2, width, height);
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ObjectId getId() {
		return id;
	}
}
