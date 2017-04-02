package com.fjfj.warfun.game.control;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Xbox;

public class GamepadController extends AbstractController {

	Controller control;
	
	public GamepadController(Controller control) {
		this.control = control;
	}
	
	@Override
	public boolean isMoveLeftDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f && !control.getButton(Xbox.R_TRIGGER) && !control.getButton(Xbox.L_TRIGGER);
	}

	@Override
	public boolean isMoveRightDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f && !control.getButton(Xbox.R_TRIGGER) && !control.getButton(Xbox.L_TRIGGER);
	}

	@Override
	public boolean isUpDown() {
		return control.getButton(Xbox.A) && !control.getButton(Xbox.R_TRIGGER);
	}

	@Override
	public boolean isActionLeftDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f && control.getButton(Xbox.R_TRIGGER);
	}

	@Override
	public boolean isActionRightDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f && control.getButton(Xbox.R_TRIGGER);
	}

	@Override
	public boolean isSecondaryActionLeftDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f && control.getButton(Xbox.L_TRIGGER);
	}

	@Override
	public boolean isSecondaryActionRightDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f && control.getButton(Xbox.L_TRIGGER);
	}

	@Override
	public boolean isActionUpDown() {
		return control.getAxis(Xbox.L_STICK_VERTICAL_AXIS) < -0.4f && control.getButton(Xbox.R_TRIGGER);
	}

	@Override
	public boolean isSecondaryActionUpDown() {
		return control.getAxis(Xbox.L_STICK_VERTICAL_AXIS) < -0.4f && control.getButton(Xbox.L_TRIGGER);
	}

}
