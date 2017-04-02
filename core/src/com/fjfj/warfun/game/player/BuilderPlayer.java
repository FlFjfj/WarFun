package com.fjfj.warfun.game.player;

import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.AnimatedSprite;
import com.fjfj.warfun.utils.Assets;

public class BuilderPlayer extends Player {

	public BuilderPlayer(AbstractController control, int x, int y) {
		super(control, x, y);

		super.fly = Assets.getTexture("player_fly");
		
		super.tex = new AnimatedSprite(0, 0, 100, 100, Assets.getTexture("player"), 0);
		super.tex.setSize(Tile.SIZE, Tile.SIZE);
	}

	@Override
	public void update() {

		if (controller.isActionLeftDown() && GamePlayState.tiles[x - 1][y].here == null && x - 1 > 0)
			GamePlayState.tiles[x - 1][y] = new Tile(TileType.Solid, x - 1, y);
		else if (controller.isActionRightDown() && GamePlayState.tiles[x + 1][y].here == null
				&& x + 2 < GamePlayState.tileWidth)
			GamePlayState.tiles[x + 1][y] = new Tile(TileType.Solid, x + 1, y);
		else if (controller.isSecondaryActionLeftDown() && GamePlayState.tiles[x - 1][y].here == null && x - 1 > 0)
			GamePlayState.tiles[x - 1][y] = new Tile(TileType.Free, x - 1, y);
		else if (controller.isSecondaryActionRightDown() && GamePlayState.tiles[x + 1][y].here == null
				&& x + 2 < GamePlayState.tileWidth)
			GamePlayState.tiles[x + 1][y] = new Tile(TileType.Free, x + 1, y);
		else if (controller.isActionUpDown() && GamePlayState.tiles[x][y + 1].here == null
				&& y + 2 < GamePlayState.tileHeight)
			GamePlayState.tiles[x][y+1] = new Tile(TileType.Solid, x, y + 1);
		else if (controller.isSecondaryActionUpDown() && GamePlayState.tiles[x][y + 1].here == null
				&& y + 2 < GamePlayState.tileHeight)
			GamePlayState.tiles[x][y+1] = new Tile(TileType.Free, x, y + 1);
		GamePlayState.tiles[x + 1][y].isRevealed = true;
		GamePlayState.tiles[x - 1][y].isRevealed = true;
		GamePlayState.tiles[x][y + 1].isRevealed = true;
		super.update();
	}

}
