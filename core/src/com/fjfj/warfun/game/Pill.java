package com.fjfj.warfun.game;

public class Pill {
	public boolean geometry = false;
	public boolean color = false;
	public static int delta = 1;
	public Pill(boolean geometry,boolean color){
		this.geometry = geometry;
		this.color = color;
	}
	public void doEffect() {
		Gui.score += 50;
		if(GamePlayState.colorParam >= 10 || GamePlayState.colorParam <= 0)
			delta *= -1.2;
		GamePlayState.colorParam += delta;
	}

}
