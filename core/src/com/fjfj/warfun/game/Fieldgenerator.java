package com.fjfj.warfun.game;


import com.badlogic.gdx.math.MathUtils;
import com.fjfj.warfun.game.Tile.TileType;
import com.fjfj.warfun.utils.Assets;

public class Fieldgenerator {
	public int w, h;
	public Tile field[][];
	public int param;
	public int placed;
	
	public Fieldgenerator(int _w, int _h) {
		w = _w;
		h = _h;

		param = 3;
	}

	public void blockgen(Tile[][] field, int x1, int y1, int x2, int y2) {
		int count = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		placed += count;
		
		field[x1][y1] = new Tile(TileType.Solid, x1, y1);
		field[x2][y2] = new Tile(TileType.Solid, x2, y2);
		count -= 2;
		
		int dx = (int) Math.signum(x2 - x1);
		int dy = (int) Math.signum(y2 - y1);
		
		int posx = x1;
		int posy = y1;
		
		int tick = 0;
		while(count > 0 && !(posx == x2 && posy == y2) && tick < 1000){
			//System.out.println(placed);
			tick++;
			float r = MathUtils.random();
			if(r < 0.8)
				posx += dx;
			if(r >= 0.8 && r <= 0.9f)
				posx -= dx;
			
			r = MathUtils.random();
			if(r < 0.8)
				posy += dy;
			if(r >= 0.8 && r <= 0.9f)
				posy -= dy;
			
			if(posx > 0 && posx < w - 1 && posy  > 0 && posy < h - 1)
				if(field[posx][posy].type == TileType.Free){
					count--;
					field[posx][posy] = new Tile(TileType.Solid, posx, posy);
				}else
					break;
		}
		
		placed -= count;
	}

	public Tile[][] generate() {
		
		field = new Tile[w][h];
		placed = 0;
		
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				field[i][j] = new Tile(TileType.Free, i, j);
					if(MathUtils.random(9) == 0){
						field[i][j].pill = new Pill(MathUtils.randomBoolean(),MathUtils.randomBoolean());
						if(field[i][j].pill.color == true)
							field[i][j].pilltex = Assets.getTexture("pill1");
						else{
							if(field[i][j].pill.geometry)
								field[i][j].pilltex = Assets.getTexture("tablet2");
							else
								field[i][j].pilltex = Assets.getTexture("tablet3");
						}
					}
				if (i * j == 0 || i == w - 1 || j == h - 1)
					field[i][j] = new Tile(TileType.Solid, i, j);
			}

		while(placed < w * h / 5)
			blockgen(field, MathUtils.random(w - 3 + 1), MathUtils.random(h - 3) + 1, 
							MathUtils.random(w - 3) + 1, MathUtils.random(h - 3) + 1);
		

		return field;
	}
}
