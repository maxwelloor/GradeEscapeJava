package dev.java2dgame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.java2dgame.entities.creatures.Creature;
import dev.java2dgame.gfx.Dialogue;
import dev.java2dgame.main.Handler;

public class Teacher extends StaticEntity {
	
	private boolean showDialogue = false;
	private Dialogue[] dialogues = {new Dialogue(handler, "Hey there kid. If you want my key you are gonna have to   try to beat me in an arm wrestle. You wanna try?")};
	private int currentDialogue = 0;

	public Teacher(Handler handler, float x, float y) {
		super(handler, x, y);
		
		this.interactible = true;
		hitbox.width = 64;
		hitbox.height = 64;
	}

	@Override
	public void tick() {
		// Checks for the player interacting with the teacher
		if (checkForPlayerInteract(handler.getWorld().getEntityManager().getPlayer()) && !showDialogue) {
			getCurrentDialogue().setBuilding(true);
			showDialogue = true;
		}
		
		if (showDialogue) {
			getCurrentDialogue().tick();
			checkForDialogueEnd(getCurrentDialogue());
		}
		
		if (!getCurrentDialogue().isBuilding() && handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE))
			showDialogue = false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 64, 64);
		
		if (showDialogue)
			getCurrentDialogue().render(g);
	}
	
	private Dialogue getCurrentDialogue() {
		return dialogues[currentDialogue];
	}
	
	private void checkForDialogueEnd(Dialogue d) {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !d.isBuilding()) {
			showDialogue = false;
			d.reset();
			
			if (currentDialogue < dialogues.length - 1)
				currentDialogue++;
		}
	}
}
