package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.fjfj.warfun.utils.Assets;

public class Tile {

	public static final int SIZE = 50;

	public static ShaderProgram tileShader = new ShaderProgram(Gdx.files.local("assets/shader/empty.vert"),
			Gdx.files.local("assets/shader/empty.frag"));
	static {
		System.out.println(tileShader.getLog());
	}

	static Texture rainbow = Assets.getTexture("rainbow");
	boolean isRainbow = false;
	boolean isRainbowed = false;
	
	enum TileType {
		Solid, Free
	}

	int x, y;

	Player here = null;
	Texture tex0;
	Texture tex1;

	TileType type;
	boolean isRevealed = false;;

	
	
	public Tile(TileType type, int x, int y) {

		this.type = type;
		this.x = x;
		this.y = y;

		if (type == TileType.Solid) {
			tex0 = Assets.getTexture("tile");
			tex1 = Assets.getTexture("tile2");
		} else {
			tex0 = Assets.getTexture("free");
			tex1 = Assets.getTexture("free2");
		}
	}

	public void draw(SpriteBatch batch) {
		if (isRevealed) {
			tex1.bind(1);
			tex0.bind(0);
			batch.draw(tex0, (x - GamePlayState.tileWidth / 2) * SIZE, (y - GamePlayState.tileHeight / 2) * SIZE);
		}
	}

	public void drawRainbow(SpriteBatch batch) {
		
	}
	
	public boolean canWalk() {
		return TileType.Free == type && here == null;
	}

	public void setPlayer(Player player) {
		this.here = player;
		this.isRevealed = false;
		
		for(int i = Math.max(0, x - 3); i < Math.min(x + 3, GamePlayState.tileWidth); i++)
			for(int j = Math.max(0, y - 3); j < Math.min(y + 3, GamePlayState.tileHeight); j++){
				if(Math.abs(i - x) + Math.abs(j - y) <= 5)
					GamePlayState.tiles[i][j].isRevealed = true;
			}
	}

	void makeRainbow(int dx){
		
	}
	
	void removeRainbow(int dx){
		
	}
	
}
