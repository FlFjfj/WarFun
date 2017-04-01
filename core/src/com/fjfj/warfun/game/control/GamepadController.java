package com.fjfj.warfun.game.control;

import com.badlogic.gdx.controllers.ControlType;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;

public class GamepadController extends AbstractController {

	Controller control;
	
	public GamepadController(Controller control) {
		this.control = control;
	}
	
	@Override
	public boolean isLeftDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.4f;
	}

	@Override
	public boolean isRightDown() {
		return control.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.4f;
	}

	@Override
	public boolean isUpDown() {
		return control.getButton(Xbox.A);
	}

}
