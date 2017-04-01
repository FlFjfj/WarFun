package com.fjfj.warfun.game;


import com.badlogic.gdx.math.MathUtils;
import com.fjfj.warfun.game.Tile.TileType;

public class Fieldgenerator {
	public int w, h;
	public Tile field[][];
	public int param;

	public Fieldgenerator(int _w, int _h) {
		w = _w;
		h = _h;

		param = 5;
	}

	public void blockgen(int xb, int yb, int wb, int hb, int xp, int yp) {
		if (field[xp][yp].type == TileType.Solid)
			return;
		field[xp][yp] = new Tile(TileType.Solid, xp, yp);
		int dx = MathUtils.random(1) - 1;
		int dy = MathUtils.random(1) - 1;
		while (MathUtils.random(param) != 0) {
			dx = MathUtils.random(1) - 1;
			dy = MathUtils.random(1) - 1;
			if ((xp + dx < wb + xb) && (yp + dy < hb + yb) && (xp + dx >= xb) && (yp + dy >= yb))
				blockgen(xb, yb, wb, hb, xp + dx, yp + dy);
		}

	}

	public Tile[][] generate() {
		int wb = 5;
		int hb = 5;
		int xb, yb;
		int xp, yp;
		field = new Tile[w][h];
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				field[i][j] = new Tile(TileType.Free, i, j);
				if (i * j == 0 || i == w - 1 || j == h - 1)
					field[i][j] = new Tile(TileType.Solid, i, j);
			}
		for (int i = 0; i < w / wb; i++)
			for (int j = 0; j < h / hb; j++) {
				xb = i * wb;
				yb = j * hb;
				xp = MathUtils.random(wb-1) + xb;
				yp = MathUtils.random(hb-1) + yb;
				blockgen(xb, yb, wb, hb, xp, yp);
			}

		return field;
	}
}
