package com.fjfj.warfun.game.player;

import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.AnimatedSprite;
import com.fjfj.warfun.utils.Assets;

public class RainbowPlayer extends Player {

	public RainbowPlayer(AbstractController control, int x, int y) {
		super(control, x, y);

		super.tex = new AnimatedSprite(0, 0, 100, 100, Assets.getTexture("player"), 0);
		super.tex.setSize(Tile.SIZE, Tile.SIZE);
	}

	@Override
	public void update() {

		if (controller.isActionLeftDown())
			GamePlayState.tiles[x-1][y].makeRainbow(-1);
		else if (controller.isActionRightDown())
			GamePlayState.tiles[x+1][y].makeRainbow(1);
		else if (controller.isActionUpDown())
			GamePlayState.tiles[x][y+1].makeRainbow(0);

		super.update();
	}

}
