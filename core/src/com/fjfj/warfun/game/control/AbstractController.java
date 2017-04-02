package com.fjfj.warfun.game.control;

public abstract class AbstractController {

	public abstract boolean isMoveLeftDown();
	public abstract boolean isMoveRightDown();
	
	public abstract boolean isActionLeftDown();
	public abstract boolean isActionRightDown();
	public abstract boolean isActionUpDown();
	
	public abstract boolean isSecondaryActionLeftDown();
	public abstract boolean isSecondaryActionRightDown();
	public abstract boolean isSecondaryActionUpDown();
	
	public abstract boolean isUpDown();
	
}
