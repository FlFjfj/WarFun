package com.fjfj.warfun.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fjfj.warfun.MainGame;
import com.fjfj.warfun.utils.Assets;

public class Background {

	Texture tex_bright;
	Texture tex_dark;
	
	public Background(){
		tex_bright = Assets.getTexture("back0");
		tex_dark = Assets.getTexture("back1");
	}
	
	public void draw(SpriteBatch batch){
		batch.setShader(Tile.tileShader);

		float[] p1 = GamePlayState.player1.getPosition();
		float[] p2 = GamePlayState.player2.getPosition();
		Tile.tileShader.setUniformf(Tile.radius_loc, GamePlayState.radius);
		Tile.tileShader.setUniformf(Tile.time_loc, GamePlayState.time);
		Tile.tileShader.setUniform2fv(Tile.player0_loc, p1, 0, 2);
		Tile.tileShader.setUniform2fv(Tile.player1_loc, p2, 0, 2);
		Tile.tileShader.setUniformi(Tile.tex0_loc, 0);
		Tile.tileShader.setUniformi(Tile.tex1_loc, 1);
		
		tex_dark.bind(1);
		tex_bright.bind(0);
		

		float cam_x = (p1[0] + p2[0]) / 2;
		float cam_y = (p1[1] + p2[1]) / 2;
		
		float x = MathUtils.lerp(-GamePlayState.tileWidth* Tile.SIZE / 4, GamePlayState.tileWidth * Tile.SIZE / 4, 
				unlerp(GamePlayState.camera.position.x, (MainGame.WIDTH / Tile.SIZE / 2 - GamePlayState.tileWidth / 2) * Tile.SIZE,
						-(MainGame.WIDTH / Tile.SIZE / 2 - GamePlayState.tileWidth / 2) * Tile.SIZE));
		float y = MathUtils.lerp(-GamePlayState.tileHeight * Tile.SIZE / 4, GamePlayState.tileHeight * Tile.SIZE / 4, 
				unlerp(GamePlayState.camera.position.y, (MainGame.HEIGHT / Tile.SIZE / 2 - GamePlayState.tileHeight / 2) * Tile.SIZE,
						-(MainGame.HEIGHT / Tile.SIZE / 2 - GamePlayState.tileHeight / 2) * Tile.SIZE));
		
		batch.draw(tex_bright, x - GamePlayState.tileWidth * Tile.SIZE / 4,y - GamePlayState.tileHeight * Tile.SIZE / 4,
				GamePlayState.tileWidth * Tile.SIZE / 2, GamePlayState.tileHeight * Tile.SIZE / 2);

		batch.setShader(null);
	}
	
	private float unlerp(float val, float min, float max){
		return (val - min) / (max - min);
	}
	
}
