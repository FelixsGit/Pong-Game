package pong.frameWorks;

import java.awt.image.BufferedImage;

import pong.window.BufferedImageLoader;
import pong.window.SpriteSheet;

public class Texture {
	
	private SpriteSheet fbs;
	private BufferedImage fireBall_sheet = null;
	
	public BufferedImage[] fireBall = new BufferedImage[2];

	
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
		fireBall_sheet = loader.loadImage("/FireBall_sheet.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		fbs = new SpriteSheet(fireBall_sheet);
		
		getTextures();
	}
	private void getTextures(){
		
		fireBall[1] = fbs.grabImage(0, 0, 32 , 32); //FireBall
		
	}
}
