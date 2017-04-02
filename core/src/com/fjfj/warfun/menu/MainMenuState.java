package com.fjfj.warfun.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fjfj.warfun.MainGame;
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
		
		Array<Controller> con = Controllers.getControllers();
		
		if(con.get(0).getButton(Xbox.R_TRIGGER) || con.get(1).getButton(Xbox.R_TRIGGER))
			game.enterState(MainGame.GAMEPLAYSTATE);
		
		if(con.get(0).getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f)
			isFirstBuilder = true;
		if(con.get(0).getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f)
			isFirstBuilder = false;
		
		if(con.get(1).getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f)
			isSecondBuilder = true;
		if(con.get(1).getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f)
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
