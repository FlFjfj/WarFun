package com.fjfj.warfun.game.player;

import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.Assets;

public class BuilderPlayer extends Player {


	public BuilderPlayer(AbstractController control, int x, int y) {
		super(control, x, y);
		
		super.tex = Assets.getTexture("player");
	}

	@Override
	public void update() {

		if (controller.isActionLeftDown() && GamePlayState.tiles[x-1][y].here == null && x-1 > 0)
			GamePlayState.tiles[x - 1][y] = new Tile(TileType.Solid, x - 1, y);
		else if(controller.isActionRightDown() && GamePlayState.tiles[x+1][y].here == null && x+2 < GamePlayState.tileWidth)
			GamePlayState.tiles[x + 1][y] = new Tile(TileType.Solid, x + 1, y);
		else if(controller.isSecondaryActionLeftDown() && GamePlayState.tiles[x-1][y].here == null && x-1 > 0)
			GamePlayState.tiles[x - 1][y] = new Tile(TileType.Free, x - 1, y);
		else if(controller.isSecondaryActionRightDown() && GamePlayState.tiles[x+1][y].here == null && x+2 < GamePlayState.tileWidth)
			GamePlayState.tiles[x + 1][y] = new Tile(TileType.Free, x + 1, y);

		GamePlayState.tiles[x + 1][y].isRevealed = true;
		GamePlayState.tiles[x - 1][y].isRevealed = true;
		
		super.update();
	}

}
