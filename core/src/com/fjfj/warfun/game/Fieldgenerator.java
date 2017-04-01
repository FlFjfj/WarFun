package com.fjfj.warfun.game;

import java.util.Random;

import com.fjfj.warfun.game.Tile.TileType;

public class Fieldgenerator {
	public int w, h;
	public Random rand;
	public Tile field[][];
	public int param;

	public Fieldgenerator(int _w, int _h) {
		w = _w;
		h = _h;
		param = 3;
		rand = new Random();
		field = new Tile[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++) {
				field[i][j] = new Tile(TileType.Free, i, j);
				if (i * j == 0 || i == h - 1 || j == w - 1)
					field[i][j] = new Tile(TileType.Solid, i, j);
			}
	}

	public void blockgen(int xb, int yb, int wb, int hb, int xp, int yp) {
		if (field[xp][yp].type == TileType.Solid)
			return;
		field[xp][yp] = new Tile(TileType.Solid, xp, yp);
		int dx = rand.nextInt(2) - 1;
		int dy = rand.nextInt(2) - 1;
		while (rand.nextInt(param) != 0) {
			dx = rand.nextInt(2) - 1;
			dy = rand.nextInt(2) - 1;
			if ((xp + dx < wb + xb) && (yp + dy < hb + yb) && (xp + dx >= xb) && (yp + dy >= yb))
				blockgen(xb, yb, wb, hb, xp + dx, yp + dy);
		}

	}

	public Tile[][] generate(int w, int h) {
		int wb = 5;
		int hb = 5;
		int xb, yb;
		int xp, yp;

		for (int i = 0; i < h / hb; i++)
			for (int j = 0; j < w / wb; j++) {
				xb = i * hb;
				yb = j * wb;
				xp = rand.nextInt(hb) + xb;
				yp = rand.nextInt(wb) + yb;
				System.out.println(xp);
				System.out.println(yp);
				blockgen(xb, yb, wb, hb, xp, yp);
			}

		return field;
	}
}
