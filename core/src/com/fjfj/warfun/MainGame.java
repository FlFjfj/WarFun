package com.fjfj.warfun;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.menu.MainMenuState;
import com.fjfj.warfun.utils.Assets;
import com.fjfj.warfun.utils.StateBasedGame;

public class MainGame extends StateBasedGame implements ApplicationListener {
	
	public static final int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		Assets.load();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		
		super.addState(new GamePlayState(GAMEPLAYSTATE, this));
		super.addState(new MainMenuState(MAINMENUSTATE, this));
		super.enterState(MAINMENUSTATE);
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
	public void resize(int width, int height) {
		super.resize(width, height);
		HEIGHT = (int)(MainGame.WIDTH / (float)width * height);
	}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
