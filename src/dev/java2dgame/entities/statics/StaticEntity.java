package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.entities.Entity;
import dev.java2dgame.main.Handler;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y) {
		super(handler, x, y);
	}
}
