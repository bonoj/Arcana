package com.hk47.arcana;

import com.badlogic.gdx.Game;

public class ArcanaGame extends Game {

	@Override
	public void create() {
		setScreen(new GameplayScreen());
	}
}