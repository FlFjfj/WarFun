package com.fjfj.warfun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fjfj.warfun.MainGame;
import com.fjfj.warfun.game.control.GamepadController;
import com.fjfj.warfun.game.control.KeyBoardController;
import com.fjfj.warfun.game.player.BuilderPlayer;
import com.fjfj.warfun.game.player.Player;
import com.fjfj.warfun.game.player.RainbowPlayer;
import com.fjfj.warfun.utils.GameState;
import com.fjfj.warfun.utils.StateBasedGame;

public class GamePlayState extends GameState {

	public static final int tileWidth = 60;
	public static final int tileHeight = 30;
	
	public static OrthographicCamera camera;
	
	public static Tile[][] tiles;
	Player player1;
	Player player2;
	
	float time = 0;
	
	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void init(StateBasedGame game) {
		camera = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
		
		Fieldgenerator fg = new Fieldgenerator(tileWidth,tileHeight);
		tiles = fg.generate();
		player1 = new RainbowPlayer(new KeyBoardController(), 30, 10);
		player2 = new BuilderPlayer(new GamepadController(Controllers.getControllers().first()), 24, 10);
		tiles[30][10].setPlayer(player1);
		tiles[24][10].setPlayer(player2);

	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		batch.setShader(Tile.tileShader);
		Tile.tileShader.begin();
		
		Tile.tileShader.setUniformf(Tile.tileShader.getUniformLocation("u_time"), time);
		Tile.tileShader.setUniform2fv(Tile.tileShader.getUniformLocation("u_player0"), 
				new float[]{(player1.x - GamePlayState.tileWidth / 2) * Tile.SIZE + player1.offsetX + Tile.SIZE / 2,
							(player1.y - GamePlayState.tileHeight / 2) * Tile.SIZE + player1.offsetY + Tile.SIZE / 2},
				0, 2);
		Tile.tileShader.setUniform2fv(Tile.tileShader.getUniformLocation("u_player1"), 
				new float[]{(player2.x - GamePlayState.tileWidth / 2) * Tile.SIZE + player2.offsetX + Tile.SIZE / 2,
							(player2.y - GamePlayState.tileHeight / 2) * Tile.SIZE + player2.offsetY + Tile.SIZE / 2},
				0, 2);
		
		Tile.tileShader.setUniformi(Tile.tileShader.getUniformLocation("u_texture1"), 1);
		
		for(int i = 0; i < tileWidth; i++)
			for(int j = 0; j < tileHeight; j++)
				tiles[i][j].draw(batch);
		
		Tile.tileShader.end();
		batch.setShader(null);
		
		for(int i = 0; i < tileWidth; i++)
			for(int j = 0; j < tileHeight; j++)
				tiles[i][j].drawRainbow(batch);
		
		player1.draw(batch);
		player2.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(StateBasedGame game) {
		
		time += Gdx.graphics.getDeltaTime();
		
		for(int i = 0; i < tileWidth; i++)
			for(int j = 0; j < tileHeight; j++)
				tiles[i][j].isRainbow = false;
		
		player1.update();
		player2.update();
		
		float x = (player1.offsetX + (player1.x - tileWidth / 2) * Tile.SIZE +
				   player2.offsetX + (player2.x - tileWidth / 2) * Tile.SIZE) / 2;
		float y = (player1.offsetY + (player1.y - tileHeight / 2) * Tile.SIZE +
				   player2.offsetY + (player2.y - tileHeight / 2) * Tile.SIZE) / 2;
		camera.position.x = MathUtils.clamp(x, (MainGame.WIDTH / Tile.SIZE / 2 - tileWidth / 2) * Tile.SIZE,
				-(MainGame.WIDTH / Tile.SIZE / 2 - tileWidth / 2) * Tile.SIZE);
		camera.position.y = MathUtils.clamp(y, (MainGame.HEIGHT / Tile.SIZE / 2 - tileHeight / 2) * Tile.SIZE,
				-(MainGame.HEIGHT / Tile.SIZE / 2 - tileHeight / 2) * Tile.SIZE);

		camera.update();
	}

	@Override
	public void dispose() {}

	@Override
	public void enter(StateBasedGame game) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void resize(int width, int height) {
		camera = new OrthographicCamera(MainGame.WIDTH, MainGame.WIDTH / (float)width * height);
	}

}
