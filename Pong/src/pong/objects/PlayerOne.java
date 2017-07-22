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

public class PlayerOne extends GameObject{
	
	private static int width = 16;
	private static int height = 128;
	private float x;
	private float y;
	private float velX, velY;
	private ObjectId id;
	private Handler handler;
	
	public PlayerOne(float x, float y, Handler handler, ObjectId id){
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

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y + Game.HEIGHT/2 - height/2, width, height);
		g.setColor(Color.black);
		g.drawRect((int) x, (int) y + Game.HEIGHT/2 - height/2, width, height);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		//g2d.draw(getBoundsTop());
		//g2d.draw(getBottomBottom());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBounds());
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
	
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 4, (int) y + Game.HEIGHT/2 - height/2, width - 8, height/2);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 4, (int) y + Game.HEIGHT/2 , width -8, height/2);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + width - 4, (int) y + Game.HEIGHT/2 - height/2 + 2, 4, height -4);
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y + Game.HEIGHT/2 - height/2, width, height);
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

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

}
