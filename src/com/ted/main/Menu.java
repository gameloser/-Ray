package com.ted.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ted.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Image image;
	
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu){
			//play button
			if(mouseOver(mx, my, 220, 130, 200, 64)){
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			//Help button
			if(mouseOver(mx, my, 220, 230, 200, 64)){
				Game.gameState = STATE.Help;
			}
			
			
			
			//quit button
			if(mouseOver(mx, my, 220, 330, 200, 64)){
				System.exit(1);
				
			}
		}	
		
			//Back button for Help
			if(Game.gameState == STATE.Help){
				if(mouseOver(mx, my, 220, 330, 200, 64)){
					Game.gameState = STATE.Menu;
					return;
				}
			}
			
			//Back button for Game
			if(Game.gameState == STATE.End){
				if(mouseOver(mx, my, 220, 330, 200, 64)){
					Game.gameState = STATE.Game;
					hud.setLevel(1);
					hud.setScore(0);
					handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
					handler.clearEnemys();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
			}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(Game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 40);
			
			Image image = Toolkit.getDefaultToolkit().getImage("sas.png");
			g.drawImage(image, 50, 160, 150, 182, null);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Rays", 240, 70);
		
			g.setFont(fnt2);
			g.drawRect(220, 130, 200, 64);
			g.drawString("Start", 265, 180);
		
			g.drawRect(220, 230, 200, 64);
			g.drawString("Help", 265, 280);
		
			g.drawRect(220, 330, 200, 64);
			g.drawString("Exit", 265, 380);
		}else if(Game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 45);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 260, 60);
			
			g.setFont(fnt2);
			g.drawString("There are many rays in universe", 50, 120);
			g.drawString("Use arrow key to dodge rays", 50, 220);
			g.drawString("Boss", 50, 320);
			
			g.setFont(fnt);
			g.drawRect(220, 330, 200, 64);
			g.drawString("Back", 265, 380);
		}else if(Game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("verdana", 1, 60);
			Font fnt3 = new Font("verdana", 1, 38);
			
			g.setFont(fnt);
			Color cl2 = new Color(216, 191, 216);
			g.setColor(cl2);
			g.drawString("You Lose", 180, 75);
			
			g.setFont(fnt2);
			g.drawString("Final Score: " + hud.getScore(), 100, 210);
			
			/*
			ImageIcon i = new ImageIcon("shiwang.png");
			image = i.getImage();
			g.drawImage(image, 300, 300, 300, 300, null);
			*/
			
			g.setFont(fnt3);
			g.drawRect(220, 330, 200, 64);
			g.drawString("Play Again", 245, 375);
			
		}
		
	}


}








