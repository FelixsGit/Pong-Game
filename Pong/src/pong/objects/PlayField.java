package pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import pong.frameWorks.GameObject;
import pong.frameWorks.ObjectId;


public class PlayField extends GameObject{
	
	private float x;
	private float y;
	private float width = 32;
	private float height = 32;
	private ObjectId id;
	
	public PlayField(float x, float y, ObjectId id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		
	}
	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {

		g.setColor(Color.black);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.red);
		//g2d.draw(getBounds());
		g.setColor(Color.GREEN);
		//g2d.draw(getBoundsBottom());
		g.setColor(Color.red);
		//g2d.draw(getBoundsTop());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x, (int) y , (int) width, (int) height/2 + 6 );
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x, (int) y + 16 , (int) width, (int) height/2 + 8);
	}
	public Rectangle getBoundsRight() {
		return null;
	}
	
	public Rectangle getBoundsLeft() {
		return null;
	}

}