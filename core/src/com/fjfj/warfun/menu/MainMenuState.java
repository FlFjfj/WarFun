package com.fjfj.warfun.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.MainGame;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.game.control.KeyBoardFirstController;
import com.fjfj.warfun.game.control.KeyBoardSecondController;
import com.fjfj.warfun.utils.AnimatedSprite;
import com.fjfj.warfun.utils.Assets;
import com.fjfj.warfun.utils.GameState;
import com.fjfj.warfun.utils.StateBasedGame;

public class MainMenuState extends GameState{

	public static boolean isFirstBuilder;
	public static boolean isSecondBuilder;
	
	OrthographicCamera camera;
	
	AnimatedSprite player;
	Texture back;
	Texture controller0, controller1;
	
	public static AbstractController first, second;
	
	public MainMenuState(int StateId, MainGame game) {
		super(StateId, game);
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		batch.draw(back, -400, -300, 800, 600);
		player.draw(batch);
		batch.draw(controller0, isFirstBuilder?260:-395, 200, 143, 130);
		batch.draw(controller1, isSecondBuilder?100:-250, 200, 143, 130);
		
		batch.end();
	}

	@Override
	public void update(StateBasedGame game) {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		player.update(Gdx.graphics.getDeltaTime());
		
		if(first.isActionDown() || second.isActionDown())
			game.enterState(MainGame.GAMEPLAYSTATE);
		
		if(first.isMoveRightDown())
			isFirstBuilder = true;
		if(first.isMoveLeftDown())
			isFirstBuilder = false;
		
		if(second.isMoveRightDown())
			isSecondBuilder = true;
		if(second.isMoveLeftDown())
			isSecondBuilder = false;
	}

	@Override
	public void init(StateBasedGame game) {
		camera = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
		
		player = new AnimatedSprite(0, 0, 350, 350, Assets.getTexture("player_menu"), 0);
		back = Assets.getTexture("menu_back");
		controller0 = Assets.getTexture("controller0");
		controller1 = Assets.getTexture("controller1");
		
		player.setSize(270, 300);
		player.setPosition(-400, -player.getHeigth()/2);
		
		first = new KeyBoardFirstController();
		second = new KeyBoardSecondController();
	}

	@Override
	public void dispose() {}

	@Override
	public void enter(StateBasedGame game) {
		isFirstBuilder = true;
		isSecondBuilder = false;
}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void resize(int width, int height) {}

}
