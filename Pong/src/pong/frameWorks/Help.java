package pong.frameWorks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.window.Game;

public class Help {
	
	public void helpMenu(Graphics g){
		
		
		Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Controlls", Game.WIDTH/2 - 100, Game.HEIGHT/3);
		Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 30);
		g.setFont(fnt1);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(backButton);
		g.drawString("Red Player", Game.WIDTH/2 - 200, Game.WIDTH/3 - 50);
		g.drawString("Blue Player", Game.WIDTH/2 + 50, Game.WIDTH/3 - 50);
		Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.HANGING_BASELINE, 20);
		g.setFont(fnt2);
		
		g.drawString("Up = A", Game.WIDTH/2 - 200, Game.WIDTH/3 - 20);
		g.drawString("Down = D", Game.WIDTH/2 - 200, Game.WIDTH/3 );
		g.drawString("Up = A", Game.WIDTH/2 + 50, Game.WIDTH/3 - 20);
		g.drawString("Down = D", Game.WIDTH/2 + 50, Game.WIDTH/3 );
		g.setFont(fnt1);
		g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
		
	}
}