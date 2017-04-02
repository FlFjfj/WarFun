package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.fjfj.warfun.game.player.Player;
import com.fjfj.warfun.utils.Assets;

public class Tile {

	public static final int SIZE = 50;

	public static ShaderProgram tileShader = new ShaderProgram(Gdx.files.local("assets/shader/empty.vert"),
			Gdx.files.local("assets/shader/empty.frag"));
	static {
		System.out.println(tileShader.getLog());
	}
	public static int time_loc = tileShader.getUniformLocation("u_time");
	public static int player0_loc = tileShader.getUniformLocation("u_player0");
	public static int player1_loc = tileShader.getUniformLocation("u_player1");
	public static int tex0_loc = tileShader.getUniformLocation("u_texture");
	public static int tex1_loc = tileShader.getUniformLocation("u_texture1");

	static Texture rainbow = Assets.getTexture("rainbow");
	static TextureRegion rainbowtr = new TextureRegion(rainbow);
	boolean isRainbow = false;
	boolean rainbowUp = false;

	public enum TileType {
		Solid, Free
	}

	int x, y;

	public Player here = null;
	Texture tex0;
	Texture tex1;

	public TileType type;
	public boolean isRevealed = true;
	public Pill pill = null;

	public Tile(TileType type, int x, int y) {

		this.type = type;
		this.x = x;
		this.y = y;

		if (type == TileType.Solid) {
			tex0 = Assets.getTexture("tile");
			tex1 = Assets.getTexture("tile2");
		}
	}

	public void draw(SpriteBatch batch) {
		if (isRevealed && type == TileType.Solid) {

			if (pill != null) {
				tex1 = Assets.getTexture("pill");
				tex0 = Assets.getTexture("pill");
			}

			batch.setShader(tileShader);

			tileShader.setUniformf(time_loc, GamePlayState.time);
			tileShader.setUniform2fv(player0_loc, GamePlayState.player1.getPosition(), 0, 2);
			tileShader.setUniform2fv(player1_loc, GamePlayState.player2.getPosition(), 0, 2);
			tileShader.setUniformi(tex0_loc, 0);
			tileShader.setUniformi(tex1_loc, 1);

			tex1.bind(1);
			tex0.bind(0);
			batch.draw(tex0, (x - GamePlayState.tileWidth / 2) * SIZE, (y - GamePlayState.tileHeight / 2) * SIZE,
					Tile.SIZE, Tile.SIZE);

			batch.setShader(null);

		}
	}

	public void drawRainbow(SpriteBatch batch) {
		if (isRainbow) {
			if (!rainbowUp)
				batch.draw(rainbow, (x - GamePlayState.tileWidth / 2) * SIZE,
						(y - GamePlayState.tileHeight / 2) * SIZE);
			else
				batch.draw(rainbowtr, (x - GamePlayState.tileWidth / 2) * SIZE,
						(y - GamePlayState.tileHeight / 2) * SIZE, SIZE / 2, SIZE / 2, SIZE, SIZE, 1, 1, 90f);
		}
	}

	public boolean canWalk() {
		return TileType.Free == type && here == null;
	}

	public void setPlayer(Player player) {
		this.here = player;
		this.isRevealed = false;

		for (int i = Math.max(0, x - 3); i < Math.min(x + 3, GamePlayState.tileWidth); i++)
			for (int j = Math.max(0, y - 3); j < Math.min(y + 3, GamePlayState.tileHeight); j++) {
				if (Math.abs(i - x) + Math.abs(j - y) <= 5)
					GamePlayState.tiles[i][j].isRevealed = true;
			}
	}

	public void makeRainbow(int dx) {
		if (type == TileType.Free && here == null) {
			isRainbow = true;
			if (x > 0) {
				GamePlayState.tiles[x - 1][y].isRevealed = true;
			}
			if (x < GamePlayState.tileWidth - 1) {
				GamePlayState.tiles[x + 1][y].isRevealed = true;
			}

			isRevealed = true;
			if (dx != 0) {
				GamePlayState.tiles[x + dx][y].makeRainbow(dx);
				rainbowUp = false;
			} else {
				rainbowUp = true;
				GamePlayState.tiles[x][y + 1].makeRainbow(dx);
			}
		}
	}

	public void removeRainbow(int dx) {
		if (type == TileType.Free) {
			isRainbow = false;

			GamePlayState.tiles[x + dx][y].removeRainbow(dx);
		}
	}

}
