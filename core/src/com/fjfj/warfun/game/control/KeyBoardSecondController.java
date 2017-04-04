package com.fjfj.warfun.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyBoardSecondController extends AbstractController {

	@Override
	public boolean isMoveLeftDown() {
		return Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.SPACE) && !Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}

	@Override
	public boolean isMoveRightDown() {
		return Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.SPACE) && !Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}
	
	@Override
	public boolean isActionLeftDown() {
		return Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isActionRightDown() {
		return Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionLeftDown() {
		return Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}

	@Override
	public boolean isSecondaryActionRightDown() {
		return Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}
	
	@Override
	public boolean isUpDown() {
		return Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isActionUpDown() {
		return Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionUpDown() {
		return Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}

	@Override
	public boolean isActionDownDown() {
		return Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean isSecondaryActionDownDown() {
		return Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.ALT_RIGHT);
	}

	@Override
	public boolean isActionDown() {
		return Gdx.input.isKeyPressed(Keys.SPACE);
	}

}
