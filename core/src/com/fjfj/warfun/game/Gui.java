package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.MainGame;

public class Gui {

	BitmapFont font;
	
	public static int score = 0;
	
	public Gui(){
		font = new BitmapFont();
	}
	
	public void draw(SpriteBatch batch){
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 
				GamePlayState.camera.position.x - MainGame.WIDTH / 2 + 5,
				GamePlayState.camera.position.y + MainGame.HEIGHT / 2 - 5);
		font.draw(batch, "Score: " + score, GamePlayState.camera.position.x - 50,
											GamePlayState.camera.position.y + MainGame.HEIGHT / 2 - 5);
	}
	
}
