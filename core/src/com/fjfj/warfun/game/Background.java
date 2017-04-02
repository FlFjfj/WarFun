package com.fjfj.warfun.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		
		Tile.tileShader.setUniformf(Tile.time_loc, GamePlayState.time);
		Tile.tileShader.setUniform2fv(Tile.player0_loc, GamePlayState.player1.getPosition(), 0, 2);
		Tile.tileShader.setUniform2fv(Tile.player1_loc, GamePlayState.player2.getPosition(), 0, 2);
		Tile.tileShader.setUniformi(Tile.tex0_loc, 0);
		Tile.tileShader.setUniformi(Tile.tex1_loc, 1);
		
		tex_dark.bind(1);
		tex_bright.bind(0);
		batch.draw(tex_bright, -GamePlayState.tileWidth / 4 * Tile.SIZE, -GamePlayState.tileHeight / 4 * Tile.SIZE, 
				GamePlayState.tileWidth * Tile.SIZE / 4, GamePlayState.tileHeight * Tile.SIZE / 4);

		batch.setShader(null);
	}
	
}
