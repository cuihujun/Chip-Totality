package com.screen;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class Ticker {
	public int maxMessages;
	private int speed;
	private int x;
	private int y;	
	
	
	private LinkedList<String> messages;
	private String info;	
	
	
	public Ticker(int y, int x, float scale, int maxMessages, int speed){
		this.maxMessages=maxMessages;
		this.speed=speed;
		this.y=y;
		this.x = x;		
		messages = new LinkedList<String>();
		info="";
	}
	
	public void addMessage(String message){
		if(messages.size()==maxMessages)
			messages.removeFirst();
		
		messages.addLast(message);
		
		info="";
		for (String singleMessage : messages) {
			info+=" [*] ";
			info+=singleMessage;
		}
	}
	
	public void draw(ChipTotality game){
		BitmapFont font = AssetsLoader.getFont();		
		font.draw(game.batch, info, x, y);
		x-=Gdx.graphics.getDeltaTime()*speed;
			
		//resets position
		if((x+font.getBounds(info).width)<-50)
			x=Settings.WIDTH;
	}
	
}


