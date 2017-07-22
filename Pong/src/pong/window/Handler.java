package pong.window;

import java.awt.Graphics;
import java.util.LinkedList;

import pong.frameWorks.GameObject;
import pong.frameWorks.ObjectId;
import pong.objects.PlayField;

public class Handler {
	
	public LinkedList <GameObject> object  = new LinkedList<GameObject>();
	private GameObject tempObject;

	public void tick(){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	public void render(Graphics g){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void createLevel(){
		for(int i = 0; i < Game.WIDTH; i += 32){
			addObject(new PlayField((0 + i), 0, ObjectId.PlayField));
		}
		for(int i = 0; i < Game.WIDTH; i += 32){
			addObject(new PlayField((0 + i), Game.HEIGHT - 32, ObjectId.PlayField));
		}
	}
}