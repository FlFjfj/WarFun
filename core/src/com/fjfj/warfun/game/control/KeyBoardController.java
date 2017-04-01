package com.fjfj.warfun.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyBoardController extends AbstractController {

	@Override
	public boolean isLeftDown() {
		return Gdx.input.isKeyPressed(Keys.LEFT);
	}

	@Override
	public boolean isRightDown() {
		return Gdx.input.isKeyPressed(Keys.RIGHT);
	}

	@Override
	public boolean isUpDown() {
		return Gdx.input.isKeyPressed(Keys.UP);
	}

	@Override
	public boolean isActionDown() {
		return Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isChangeDown() {
		return Gdx.input.isKeyPressed(Keys.ALT_LEFT);
	}

}
