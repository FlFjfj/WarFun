package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.game.control.AbstractController;
import com.fjfj.warfun.utils.Assets;

public class Player {

	AbstractController controller;
	
	int x, y;
	Texture tex;

	float offsetX = 0;
	float offsetY = 0;

	float startVelY = 800;
	float G = 2400;

	float velX = startVelY / 2;
	float velY = startVelY;

	public Player(AbstractController control, int x, int y) {
		this.x = x;
		this.y = y;

		this.controller = control;
		
		tex = Assets.getTexture("player");
	}

	public void draw(SpriteBatch batch) {
		batch.draw(tex, (x - GamePlayState.tileWidth / 2) * Tile.SIZE + offsetX,
				(y - GamePlayState.tileHeight / 2) * Tile.SIZE + offsetY);
	}

	public void update() {
	
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
			if (controller.isLeftDown())
				if (GamePlayState.tiles[x - 1][y].canWalk() &&
						(offsetY == 0 ||  GamePlayState.tiles[x-1][(int) (y + Math.signum(offsetY))].canWalk())) {
					GamePlayState.tiles[x][y].here = null;
					x--;
					GamePlayState.tiles[x][y].here = this;
					offsetX = Tile.SIZE;
				}
		if (offsetX == 0)

			if (controller.isRightDown() &&
					(offsetY == 0 ||  GamePlayState.tiles[x+1][(int) (y + Math.signum(offsetY))].canWalk()))
				if (GamePlayState.tiles[x + 1][y].canWalk()) {
					GamePlayState.tiles[x][y].here = null;
					x++;
					GamePlayState.tiles[x][y].here = this;
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
					if (velY * t - G * t * t / 2 >= Tile.SIZE && GamePlayState.tiles[x][y + 1].canWalk() &&
						(offsetX == 0 ||  GamePlayState.tiles[(int) (x+Math.signum(offsetX))][y].canWalk())){
						offsetY -= Tile.SIZE;
						GamePlayState.tiles[x][y].here = null;
						y++;
						GamePlayState.tiles[x][y].here = this;
					} else {
						offsetY = 0;
					}
				}
			}
			
			if(velY <= 0){
				if(offsetY <= 0)
					offsetY = 0;
			}
		}

		if (GamePlayState.tiles[x][y - 1].canWalk() && offsetY == 0) {
			GamePlayState.tiles[x][y].here = null;
			y--;
			GamePlayState.tiles[x][y].here = this;
			if (velY > 0)
				velY = 0;
			offsetY = Tile.SIZE;
		}

		if (offsetY == 0 && controller.isUpDown() && !GamePlayState.tiles[x][y - 1].canWalk()) {
			if (GamePlayState.tiles[x][y + 1].canWalk()) {
				offsetY = -Tile.SIZE;
				velY = startVelY;

				GamePlayState.tiles[x][y].here = null;
				y++;
				GamePlayState.tiles[x][y].here = this;
			}
		}

	}

}
