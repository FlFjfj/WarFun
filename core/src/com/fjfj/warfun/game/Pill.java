package com.fjfj.warfun.game;

import com.badlogic.gdx.math.MathUtils;
import com.fjfj.warfun.utils.Assets;

public class Pill {
	public boolean geometry = false;
	public boolean color = false;
	public static float delta = 0.2f;
	public Pill(boolean geometry,boolean color){
		this.geometry = geometry;
		this.color = color;
	}
	public void doEffect() {
		
		Assets.pill.play();
		
		Gui.score += 50;
		if(delta < 5)
			delta += 0.2f;
		GamePlayState.colorParam  = MathUtils.random(delta * 2) - delta ;
	}

}
