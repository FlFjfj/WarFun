package com.fjfj.warfun.game.player;

import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.game.control.AbstractController;

public class Builder extends Player {
	boolean build;
	public Builder(AbstractController control, int x, int y) {
		super(control, x, y);
		build = true;
	}
	
	@Override
	public void update(){
		if(controller.isChangeDown())
			build = !build;
			
		if(controller.isActionDown()){
			if(controller.isLeftDown() && GamePlayState.tiles[x-1][y].here == null && (build ^ !GamePlayState.tiles[x-1][y].canWalk()))
				GamePlayState.tiles[x-1][y] = new Tile(build? TileType.Solid : TileType.Free,x-1,y);
			else if (controller.isRightDown() && GamePlayState.tiles[x+1][y].here == null && (build ^ !GamePlayState.tiles[x+1][y].canWalk()))
				GamePlayState.tiles[x-1][y] = new Tile(build? TileType.Solid : TileType.Free,x+1,y);
		}
		super.update();
	}

}
