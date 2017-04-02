package com.fjfj.warfun.game.player;

import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.Assets;

public class RainbowPlayer extends Player {

	public RainbowPlayer(AbstractController control, int x, int y) {
		super(control, x, y);

		super.tex = Assets.getTexture("player");
	}

	@Override
	public void update() {

		if (controller.isActionLeftDown()) {
			GamePlayState.tiles[x][y].removeRainbow(1);
			GamePlayState.tiles[x][y].makeRainbow(-1);
		} else {
			GamePlayState.tiles[x][y].removeRainbow(-1);
			if (controller.isActionRightDown())
				GamePlayState.tiles[x][y].makeRainbow(1);
			else
				GamePlayState.tiles[x][y].removeRainbow(1);
		}

		super.update();
	}

}
