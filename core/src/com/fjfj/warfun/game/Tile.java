package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.fjfj.warfun.game.player.Player;
import com.fjfj.warfun.game.player.RainbowPlayer;
import com.fjfj.warfun.utils.AnimatedSprite;
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

	int rainbow_num;
	public boolean isRainbow = false;
	boolean rainbowUp = false;

	public enum TileType {
		Solid, Free
	}

	int x, y;

	public Player here = null;
	Texture tex0;
	Texture tex1;

	public TileType type;
	public boolean isRevealed = false;
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
			if (!rainbowUp){
				RainbowPlayer.rainbow.img[rainbow_num].setOrigin(25, 25);
				RainbowPlayer.rainbow.img[rainbow_num].setRotation(0);
				RainbowPlayer.rainbow.img[rainbow_num].setPosition( (x - GamePlayState.tileWidth / 2) * SIZE,
						(y - GamePlayState.tileHeight / 2) * SIZE);
				RainbowPlayer.rainbow.img[rainbow_num].draw(batch);
			}
			else{
				RainbowPlayer.rainbow.img[rainbow_num].setOrigin(25, 25);
				RainbowPlayer.rainbow.img[rainbow_num].setRotation(90);
				RainbowPlayer.rainbow.img[rainbow_num].setPosition( (x - GamePlayState.tileWidth / 2) * SIZE,
						(y - GamePlayState.tileHeight / 2) * SIZE);
				RainbowPlayer.rainbow.img[rainbow_num].draw(batch);
			}
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

	public void makeRainbow(int dx, int delta) {
		if (type == TileType.Free && here == null) {
			isRainbow = true;
			rainbow_num = delta % RainbowPlayer.rainbow.getAnimCount();
			
			if (x > 0) {
				GamePlayState.tiles[x - 1][y].isRevealed = true;
			}
			if (x < GamePlayState.tileWidth - 1) {
				GamePlayState.tiles[x + 1][y].isRevealed = true;
			}

			isRevealed = true;
			if (Math.abs(dx) == 1 ) {
				GamePlayState.tiles[x + dx][y].makeRainbow(dx, delta + 1);
				rainbowUp = false;
			} else {
				rainbowUp = true;
				GamePlayState.tiles[x][y + (dx%2 == 0?1:-1)].makeRainbow(dx, delta + 1);
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
