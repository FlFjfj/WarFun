package com.fjfj.warfun;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.utils.Assets;
import com.fjfj.warfun.utils.StateBasedGame;

public class MainGame extends StateBasedGame implements ApplicationListener {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static final int GAMEPLAYSTATE = 1;
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		Assets.load();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		
		super.addState(new GamePlayState(GAMEPLAYSTATE, this));
		super.enterState(GAMEPLAYSTATE);
		super.init();
	}

	@Override
	public void render () {
		
		super.update();
	
		super.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
