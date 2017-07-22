package pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import pong.frameWorks.GameObject;
import pong.frameWorks.ObjectId;
import pong.window.Game;
import pong.window.Handler;

public class PlayerTwo extends GameObject{
	
	private static int width = 16;
	private static int height = 128;
	private float x;
	private float y;
	private float velX, velY;
	private ObjectId id;
	private Handler handler;
	private boolean wallHit = false;
	
	public PlayerTwo(float x, float y, Handler handler, ObjectId id){
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	public void tick(LinkedList<GameObject> object) {
		y += velY;
		collision();
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayField){
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() - Game.HEIGHT/2 + 96;
					velY = 0;
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					y = tempObject.getY() - Game.HEIGHT/2 - 65;
					velY = 0;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x + Game.WIDTH - width, (int) y + Game.HEIGHT/2 - height/2, width, height);
		g.setColor(Color.black);
		g.drawRect((int) x + Game.WIDTH - width, (int) y + Game.HEIGHT/2 - height/2, width, height);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		//g2d.draw(getBoundsTop());
		//g2d.draw(getBoundsBottom());
		//g2d.draw(getBoundsLeft());
	}

	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + Game.WIDTH - width + 4, (int) y + Game.HEIGHT/2 - height/2, width - 8, height/2);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + Game.WIDTH - width + 4, (int) y + Game.HEIGHT/2 , width -8, height/2);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x + Game.WIDTH - width, (int) y + Game.HEIGHT/2 - height/2, 4, height);
	}
	public Rectangle getBounds() {
		return null;
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
	public boolean getWallHit(){
		return wallHit;
	}

	public ObjectId getId() {
		return id;
	}

	public Rectangle getBoundsRight() {
		return null;
	}


}
