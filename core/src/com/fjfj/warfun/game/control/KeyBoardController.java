package com.fjfj.warfun.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyBoardController extends AbstractController {

	@Override
	public boolean isMoveLeftDown() {
		return Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.SPACE) && !Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}

	@Override
	public boolean isMoveRightDown() {
		return Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.SPACE) && !Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}
	
	@Override
	public boolean isActionLeftDown() {
		return Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isActionRightDown() {
		return Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionLeftDown() {
		return Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}

	@Override
	public boolean isSecondaryActionRightDown() {
		return Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}
	
	@Override
	public boolean isUpDown() {
		return Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isActionUpDown() {
		return Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionUpDown() {
		return Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}

	@Override
	public boolean isActionDownDown() {
		return Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionDownDown() {
		return Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}

}
