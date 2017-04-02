package com.fjfj.warfun.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.game.GamePlayState;
import com.fjfj.warfun.game.Tile;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.AnimatedSprite;

public abstract class Player {

	protected AbstractController controller;

	public int x;

	public int y;
	protected AnimatedSprite tex;
	protected Texture fly;
	protected boolean isLeft;
	public float offsetX = 0;
	public float offsetY = 0;

	float startVelY = 800;
	float G = 2400;

	float velX = startVelY / 2;
	float velY = startVelY;

	public Player(AbstractController control, int x, int y) {
		this.x = x;
		this.y = y;

		this.controller = control;
	}

	public void draw(SpriteBatch batch) {
		
		if(GamePlayState.tiles[x][y - 1].type == TileType.Free && GamePlayState.tiles[x][y - 1].here == null){
			batch.draw(fly, (x - GamePlayState.tileWidth / 2) * Tile.SIZE + offsetX,
					(y - GamePlayState.tileHeight / 2) * Tile.SIZE + offsetY, Tile.SIZE, Tile.SIZE, 0, 0, 100, 100, offsetX > 0, false);
			return;
		}
		
		tex.setPosition((x - GamePlayState.tileWidth / 2) * Tile.SIZE + offsetX,
				(y - GamePlayState.tileHeight / 2) * Tile.SIZE + offsetY);
		tex.draw(batch);
	}

	public void update() {

		if (offsetX > 0) {
			tex.setFlipped(true);
			tex.update(Gdx.graphics.getDeltaTime());
		}
		if (offsetX < 0) {
			tex.setFlipped(false);
			tex.update(Gdx.graphics.getDeltaTime());
		}

		if (offsetX != 0) {
			if (offsetX > 0) {
				offsetX -= Gdx.graphics.getDeltaTime() * velX;
				if (offsetX <= 0) {
					offsetX = 0;
				}
			} else {
				offsetX += Gdx.graphics.getDeltaTime() * velX;
				if (offsetX >= 0) {
					offsetX = 0;
				}
			}
		}

		if (offsetX == 0)
			if (controller.isMoveLeftDown() || GamePlayState.tiles[x + 1][y].isRainbow)
				if (GamePlayState.tiles[x - 1][y].canWalk() && (Math.abs(offsetY) <= Tile.SIZE / 5
						|| GamePlayState.tiles[x - 1][(int) (y + Math.signum(offsetY))].canWalk())) {
					GamePlayState.tiles[x][y].here = null;
					x--;
					GamePlayState.tiles[x][y].setPlayer(this);
					offsetX = Tile.SIZE;
				}
		if (offsetX == 0)
			if ((controller.isMoveRightDown() || GamePlayState.tiles[x - 1][y].isRainbow)
					&& (Math.abs(offsetY) <= Tile.SIZE / 5
							|| GamePlayState.tiles[x + 1][(int) (y + Math.signum(offsetY))].canWalk()))
				if (GamePlayState.tiles[x + 1][y].canWalk()) {
					GamePlayState.tiles[x][y].here = null;
					x++;
					GamePlayState.tiles[x][y].setPlayer(this);
					offsetX = -Tile.SIZE;
				}

		if (offsetY != 0) {

			if (velY < Gdx.graphics.getDeltaTime() * G && velY > 0)
				velY = 0;
			else
				velY -= Gdx.graphics.getDeltaTime() * G;
			offsetY += Gdx.graphics.getDeltaTime() * velY;

			if (velY > 0) {
				if (offsetY >= 0) {
					float t = velY / G;
					if (GamePlayState.tiles[x][y + 1].canWalk()) {
						if ((velY * t - G * t * t / 2 >= Tile.SIZE) || GamePlayState.tiles[x][y - 1].isRainbow) {
							if(GamePlayState.tiles[x][y - 1].isRainbow)
								velY = startVelY / 4;
							offsetY -= Tile.SIZE;
							GamePlayState.tiles[x][y].here = null;
							y++;
							GamePlayState.tiles[x][y].setPlayer(this);
						}
					} else {
						offsetY = 0;
					}
				}

			}

			if (velY <= 0) {
				if (offsetY <= 0)
					offsetY = 0;
			}
		}

		if (GamePlayState.tiles[x][y - 1].canWalk() && !GamePlayState.tiles[x][y - 1].isRainbow && offsetY == 0
				&& (Math.abs(offsetX) <= Tile.SIZE / 5
						|| GamePlayState.tiles[(int) (x + Math.signum(offsetX))][y - 1].canWalk())) {
			GamePlayState.tiles[x][y].here = null;
			y--;
			GamePlayState.tiles[x][y].setPlayer(this);
			if (velY > 0)
				velY = 0;
			offsetY = Tile.SIZE;
		}

		if ((offsetY == 0 && controller.isUpDown()
				&& (!GamePlayState.tiles[x][y - 1].canWalk() || (Math.abs(offsetX) >= Tile.SIZE / 5
						&& GamePlayState.tiles[(int) (x + Math.signum(offsetX))][y].canWalk())))
				|| GamePlayState.tiles[x][y - 1].isRainbow) {
			if (GamePlayState.tiles[x][y + 1].canWalk() && (Math.abs(offsetX) <= Tile.SIZE / 5
					|| GamePlayState.tiles[(int) (x + Math.signum(offsetX))][y + 1].canWalk())) {
				offsetY = -Tile.SIZE;
				velY = startVelY;

				GamePlayState.tiles[x][y].here = null;
				y++;
				GamePlayState.tiles[x][y].setPlayer(this);
			}
		}

	}

	public float[] getPosition() {
		return new float[] { (x - GamePlayState.tileWidth / 2) * Tile.SIZE + offsetX + Tile.SIZE / 2,
				(y - GamePlayState.tileHeight / 2) * Tile.SIZE + offsetY + Tile.SIZE / 2};
	}

}
