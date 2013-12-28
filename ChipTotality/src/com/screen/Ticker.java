package com.screen;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class Ticker extends Widget {
	public int maxMessages;
	private final int speed;
	private int x;
	private final int y;
	BitmapFont font = AssetsLoader.getFont();

	private final LinkedList<String> messages;
	private String info;

	public Ticker(int y, int x, int maxMessages, int speed) {
		font = AssetsLoader.getFont();
		this.maxMessages = maxMessages;
		this.speed = speed;
		this.y = y;
		this.x = x;
		messages = new LinkedList<String>();
		info = "";
	}

	public void addMessage(String message) {
		if (messages.size() == maxMessages)
			messages.removeFirst();

		messages.addLast(message);

		info = "";
		for (String singleMessage : messages) {
			info += " [*] ";
			info += singleMessage;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, info, x, y);
		x -= Gdx.graphics.getDeltaTime() * speed;

		// resets position
		if ((x + font.getBounds(info).width) < 0)
			x = Settings.WIDTH;
	}

	
	@Override
	public float getMaxHeight() {
		return font.getCapHeight();
	}

	@Override
	public float getMaxWidth() {
		return x;
	}

	@Override
	public float getMinHeight() {
		return font.getCapHeight();
	}

	@Override
	public float getMinWidth() {
		return x;
	}

	@Override
	public float getPrefHeight() {
		return font.getCapHeight();
	}

	@Override
	public float getPrefWidth() {
		return x;
	}

}
