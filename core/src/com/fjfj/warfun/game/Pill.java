package com.fjfj.warfun.game;

import com.fjfj.warfun.utils.Assets;

public class Pill {
	public boolean geometry = false;
	public boolean color = false;
	public static int delta = 1;
	public Pill(boolean geometry,boolean color){
		this.geometry = geometry;
		this.color = color;
	}
	public void doEffect() {
		
		Assets.pill.play();
		
		Gui.score += 50;
		GamePlayState.radius += 10f;
		if(GamePlayState.colorParam >= 10 || GamePlayState.colorParam <= 0)
			delta *= -1.1;
		GamePlayState.colorParam += delta;
	}

}
