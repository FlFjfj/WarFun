
package com.fjfj.warfun.game.player;

import com.badlogic.gdx.Gdx;
import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.AnimatedSprite;
import com.fjfj.warfun.utils.Assets;

public class RainbowPlayer extends Player {

	public static AnimatedSprite rainbow;

	public RainbowPlayer(AbstractController control, int x, int y) {
		super(control, x, y);

		super.fly = Assets.getTexture("player_fly");

		super.tex = new AnimatedSprite(0, 0, 100, 100, Assets.getTexture("player"), 0);
		super.tex.setSize(Tile.SIZE, Tile.SIZE);

		rainbow = new AnimatedSprite(0, 0, 50, 50, Assets.getTexture("rainbow"), 0);
		rainbow.setPreferedDelta(0.2f);
	}

	@Override
	public void update() {

		rainbow.update(Gdx.graphics.getDeltaTime());
		if (mana >= 0.15) {
			if (controller.isActionLeftDown()) {
				Assets.rainbow.pause();
				Assets.rainbow.play();
				mana -= 30 * Gdx.graphics.getDeltaTime();
				rainbow.setFlipped(true);
				GamePlayState.tiles[x - 1][y].makeRainbow(-1, rainbow.getAnimNow());
			} else if (controller.isActionRightDown()) {
				Assets.rainbow.pause();
				Assets.rainbow.play();
				mana -= 30 * Gdx.graphics.getDeltaTime();
				rainbow.setFlipped(false);
				GamePlayState.tiles[x + 1][y].makeRainbow(1, rainbow.getAnimNow());
			} else if (controller.isActionUpDown()) {
				Assets.rainbow.pause();
				Assets.rainbow.play();
				rainbow.setFlipped(false);
				mana -= 30 * Gdx.graphics.getDeltaTime();
				GamePlayState.tiles[x][y + 1].makeRainbow(0, rainbow.getAnimNow());
			} else if (controller.isActionDownDown()) {
				Assets.rainbow.pause();
				Assets.rainbow.play();
				mana -= 30 * Gdx.graphics.getDeltaTime();
				rainbow.setFlipped(true);
				GamePlayState.tiles[x][y - 1].makeRainbow(3, rainbow.getAnimNow());
			}
		}
		super.update();
	}

}
