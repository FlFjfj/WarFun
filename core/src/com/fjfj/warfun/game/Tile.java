package com.fjfj.warfun.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.utils.Assets;

public class Tile {

	public static final int SIZE = 50;
	
	
	enum TileType {
		Solid, Free
	}
	
	int x, y;
	
	Player here = null;
	Texture tex;
	
	TileType type;
	
	public Tile(TileType type, int x, int y){
		
		this.type = type;
		this.x = x;
		this.y = y;
		
		if(type == TileType.Solid)
			tex = Assets.getTexture("tile");
		else
			tex = Assets.getTexture("free");
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(tex, (x - GamePlayState.tileWidth / 2) * SIZE, (y - GamePlayState.tileHeight / 2) * SIZE);
	}
	
	boolean canWalk(){
		return TileType.Free == type && here == null;
	}
	
}
